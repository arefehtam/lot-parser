package ir.pr.saman.parser.html.modules.config.pattern

import ir.pr.saman.parser.html.modules.config.pattern.filter._
import ir.pr.saman.parser.html.modules.config.pattern.regex._

/**
  * This module is the main config module for defining html elements such as tag, class, id, ect, as well as regex to match
  * on the raw html text after parser finds an extract the html part.
  * filters: filter is for determining the place of html part so the parser can reach that part.
  * use one of the filter object each time,e.g Tag, ID, Clazz, etc, otherwise if you have multiple filters then use filters: List(Filter).
  * List is used when there are more than one pattern for text, e,g price value can be found both in `span` and `div` tag
  * In this case, result of all filters merged together
  * regex: it has one option: if replace is true, reg expression is applied on text and matched part is removed so the remaining
  * part is considered otherwise only the matched part is extracted from raw text
  * Note: any new regex and filtered added here comes to the works field in the output.
  */
object PatternsModule {

  lazy val filters: Map[String, Filter] = Map(
    "artist" -> Filter(tag = Some(Tag("title"))),
    "title" -> Filter(tag = Some(Tag("title"))),
    "totalLifetimeValue" -> Filter(filters =
      List(
        Filter(tag = Some(Tag("div", filter = Some(Filter(tag = Some(Tag("span"))))))),
        Filter(tag = Some(Tag("div")))
      )
    ),
    "currency" -> Filter(filters =
      List(
        Filter(tag = Some(Tag("div", filter = Some(Filter(tag = Some(Tag("span", filter = Some(Filter(`class` = Some(Clazz("currency"))))))))))),
        Filter(tag = Some(Tag("div")))
      )
    )
  )

  lazy val regex: Map[String, RegularExpression] = Map(
    "artist" -> RegularExpression(replace = true, expr = """(\s*\([0-9]{2,4}\-[0-9]{2,4}\))?(\:).*""".r),
    "title" -> RegularExpression(replace = true, expr = """([a-zA-Z]|\s|\.)*(\([0-9]{2,4}\-[0-9]{2,4}\))?(\:)(\s)?""".r),
    "totalLifetimeValue" -> RegularExpression(replace = false, expr = """[0-9]+(\,)[0-9\,]+""".r),
    "currency" -> RegularExpression(replace = false, expr = """[A-Z]{3}""".r)
  )

}
