package ir.pr.saman.parser.html.modules.config.pattern.filter

case class Filter(
                   tag: Option[Tag] = None,
                   id: Option[ID] = None,
                   `class`: Option[Clazz] = None,
                   attributeKey: Option[AttributeKey] = None,
                   attributeKeyValue: Option[AttributeKeyValue] = None,
                   filters: List[Filter] = List.empty[Filter]
                 )
