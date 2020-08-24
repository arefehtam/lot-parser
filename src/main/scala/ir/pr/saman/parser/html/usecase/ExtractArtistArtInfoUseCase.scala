package ir.pr.saman.parser.html.usecase

import ir.pr.saman.parser.html.contract.service.ExtractArtistArtInfoService
import ir.pr.saman.parser.html.contract.service.ExtractTotalSalesValueService
import ir.pr.saman.parser.html.contract.service.GetWorksService
import ir.pr.saman.parser.html.domain.ArtistArt
import ir.pr.saman.parser.html.modules.CallbackModule.documentCallback
import ir.pr.saman.parser.html.modules.ServiceModule.TotalValue.extractTotalSalesValueService
import ir.pr.saman.parser.html.modules.ServiceModule.Works.extractWorkService
import ir.pr.saman.parser.html.modules.config.pattern.PatternsModule._
import ir.pr.saman.parser.html.modules.config.pattern.filter.Filter
import ir.pr.saman.parser.html.modules.config.pattern.filter._
import ir.pr.saman.parser.html.modules.config.pattern.regex.RegularExpression
import ir.pr.saman.parser.html.util.Constants
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

trait ExtractArtistArtInfoUseCase extends ExtractArtistArtInfoService {

  import ExtractArtistArtInfoUseCase._

  /**
    * Extract artist work of art info based on info defined in pattern config module: `PatternModule`. Info includes (filters and regex).
    * First all files in all directories are converted to object `Document`, then for each document info will be extracted
    * finally duplicated info will be merged based on type of info, e.g info of artist comes only once but amount will be summed
    *
    * @param key the unique key to find duplicate info
    * @return list of distinct info as dictionaries
    */

  override def call(key: String = Constants.ARTIST): List[ArtistArt] = {
    val duplicateResult = for {
      document <- documentCallback.getAll
      result = filters map { case (k, v) =>
        val textBeforeRegex = extractPart(v)(document)
        val textAfterRegex = regex(k) match {
          case regExpr: RegularExpression if regExpr.replace => regExpr.expr.replaceFirstIn(textBeforeRegex, "")
          case o => o.expr.findFirstIn(textBeforeRegex) map castDataType getOrElse ""
        }
        k -> textAfterRegex
      }
      groupByKey = result getOrElse(key, "")
    } yield groupByKey -> result

    // Merge duplicate artists based on their key
    for {
      artists <- duplicateResult.groupBy(_._1).values.toList // ._1 is the same value for key in input param
      name = artists.map(_._1).headOption.getOrElse("")
      data = artists map (_._2) // ._2 is list of an artist info such as title, currency, etc with duplicate name
      totalValue = extractTotalSalesValueService.call(ExtractTotalSalesValueService.Body(data))
      works = extractWorkService call GetWorksService.Body(key, data)
    } yield ArtistArt(Map(key -> name, Constants.TOTAL_VALUE -> totalValue, Constants.WORKS -> works))
  }
}

object ExtractArtistArtInfoUseCase extends ExtractArtistArtInfoUseCase {
  /**
    * Extract text from specified part of html document using filter and html document.
    * part is defined in filter.
    *
    * @param filter   the Filter to filter html document, filter is recursive based on tags,
    *               e.g one tag can be inside another tag and text of the deepest levels will be returned
    * @param document the Document within which filter is applied
    * @return the text inside a specified part of html document
    */
  def extractPart(filter: Filter)(document: Document): String = {
    filter match {
      case Filter(Some(Tag(name, f)), _, _, _, _, _) =>
        val text = document.getElementsByTag(name).text
        f match {
          case Some(innerFilter) => extractPart(innerFilter)(Jsoup parse text)
          case None => text
        }
      case Filter(_, Some(ID(name)), _, _, _, _) => document.getElementById(name).text
      case Filter(_, _, Some(Clazz(name)), _, _, _) => document.getElementsByClass(name).text
      case Filter(_, _, _, Some(AttributeKey(name)), _, _) => document.getElementsByAttribute(name).text
      case Filter(_, _, _, _, Some(AttributeKeyValue(key, value)), _) => document.getElementsByAttributeValue(key, value).text
      case Filter(_, _, _, _, _, f) => f.map(extractPart(_)(document)).filterNot(_ == "") mkString ""
    }
  }

  /** convert value to basic data type **/
  def castDataType(value: Any): Any = value match {
    case s: Short => s
    case i: Int => i
    case l: Long => l
    case d: java.lang.Double => d
    case s: String => s
  }
}
