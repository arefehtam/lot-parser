package ir.pr.saman.parser.html.modules.config.pattern

object PatternsModule {

  lazy val regex: Map[String, RegularExpression] = Map(
    "artist" -> RegularExpression(replace = true, expr = """(\s*\([0-9]{2,4}\-[0-9]{2,4}\))?(\:).*""".r),
    "title" -> RegularExpression(replace = true, expr = """([a-zA-Z]|\s|\.)*(\([0-9]{2,4}\-[0-9]{2,4}\))?(\:)(\s)?""".r),
    "amount" -> RegularExpression(replace = false, expr = """[0-9]+(\,)[0-9\,]+""".r),
    "currency" -> RegularExpression(replace = false, expr = """[A-Z]{3}""".r)
  )

  lazy val filters: Map[String, Filter] = Map(
    "artist" -> Filter(tag = Some(Tag("title"))),
    "title" -> Filter(tag = Some(Tag("title"))),
    "amount" -> Filter(filters =
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

}
