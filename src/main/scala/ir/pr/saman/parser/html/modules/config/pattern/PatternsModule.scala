package ir.pr.saman.parser.html.modules.config.pattern

object PatternsModule {

  lazy val regex: Map[String, RegularExpression] = Map(
    "artist" -> RegularExpression(replace = true, expr ="""(\s*\([0-9]{2,4}\-[0-9]{2,4}\))?(\:).*""".r))

  lazy val filters: Map[String, Filter] = Map(
    "artist" -> Filter(tag = Some(Tag("Title")))
  )

}
