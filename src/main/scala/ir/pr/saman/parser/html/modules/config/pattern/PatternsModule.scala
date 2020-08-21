package ir.pr.saman.parser.html.modules.config.pattern

object PatternsModule {

  lazy val regex: Map[String, RegularExpression] = Map(
    "artist" -> RegularExpression(replace = true, expr = """(\s*\([0-9]{2,4}\-[0-9]{2,4}\))?(\:).*""".r),
    "title" -> RegularExpression(replace = true, expr = """([a-zA-Z]|\s|\.)*(\([0-9]{2,4}\-[0-9]{2,4}\))?(\:)""".r),
    "price" -> RegularExpression(replace = false, expr = """([A-Z]{3})(\s)?([0-9\,])*""".r),
  )

  lazy val filters: Map[String, Filter] = Map(
    "artist" -> Filter(tag = Some(Tag("title"))),
    "title" -> Filter(tag = Some(Tag("title"))),
    "price" -> Filter(filters =
      List(
        Filter(tag = Some(Tag("div", filter = Some(Filter(Some(Tag("span"))))))),
        Filter(tag = Some(Tag("div")))
      )
    )
  )

}
