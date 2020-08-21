package ir.pr.saman.parser.html.usecase

import ir.pr.saman.parser.html.contract.service.ExtractArtistLegacyInfoService
import ir.pr.saman.parser.html.modules.CallbackModule.documentCallback
import ir.pr.saman.parser.html.modules.config.pattern.Filter
import ir.pr.saman.parser.html.modules.config.pattern._
import ir.pr.saman.parser.html.modules.config.pattern.PatternsModule._
import ir.pr.saman.parser.html.util.Constants
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import scala.collection.mutable.ListBuffer

trait ExtractArtistLegacyInfoUseCase extends ExtractArtistLegacyInfoService {

  import ExtractArtistLegacyInfoUseCase._
  /**
    * Extract artist legacy info based on info defined in pattern config module: `PatternModule`. Info includes (filters and regex).
    * First all files in all directories are converted to object `Document`, then for each document info will be extracted
    * finally duplicated info will be merged based on type of info, e.g info of artist comes only once but price will be summed
    *
    * @param key the unique key to find duplicate info
    * @return list of distinct info as dictionaries
    */

  override def call(key: String = "artist"): List[Map[String, Any]] = {
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
      artists <- duplicateResult.groupBy(_._1).values.toList // ._1 is the same key as input param
      works = ListBuffer.empty[Map[String, Any]]
      _ = artists map (_._2) foreach { artist => // ._2 is list of artist with duplicate name
        works.append(artist - key)
      }
      name = artists.map(_._1).headOption.getOrElse("")
    } yield  Map(key -> name, Constants.WORKS -> works)



//          case (k, v) if k == Constants.ARTIST => (k, v)
//          case (otherKey, otherValue) =>
//            result2 get otherKey match {
//              case Some(value) => otherValue match {
//                works += ()
////                case i: Int => (otherKey, value.toString.toInt + i)
////                case l: List[Any] => (otherKey, (value +: l).distinct)
////                case a: Any => (otherKey, List(a, value).distinct)
//              }
//              case None => (otherKey, otherValue)
//            }
//        }
//      }
  }
}

object ExtractArtistLegacyInfoUseCase extends ExtractArtistLegacyInfoUseCase {
  /**
    * Extract text from specified part of html document using filter and html document.
    * part is defined in filter.
    *
    * @param filter the Filter to filter html document, filter is recursive based on tags,
    *               e.g one tag can be inside another tag and text of the deepest level will be returned
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
      case Filter(_, _, _, _, _, f) => f.map(extractPart(_)(document)).filterNot(_ == "").headOption getOrElse ""
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
