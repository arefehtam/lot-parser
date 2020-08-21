package ir.pr.saman.parser.html.modules.config.pattern

object PatternsModule {

  lazy val regex: Map[String, RegularExpression] = Map(
    "artist" -> RegularExpression(replace = true, expr = """(\s*\([0-9]{2,4}\-[0-9]{2,4}\))?(\:).*""".r),
    "works" -> RegularExpression(replace = true, expr = """([a-zA-Z]|\s|\.)*(\([0-9]{2,4}\-[0-9]{2,4}\))?(\:)""".r)
  )

  lazy val filters: Map[String, Filter] = Map(
    "artist" -> Filter(tag = Some(Tag("title"))),
    "works" -> Filter(tag = Some(Tag("title")))
  )

}
