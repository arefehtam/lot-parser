package ir.pr.saman.parser.html.modules.config.pattern

import scala.util.matching.Regex

case class RegularExpression(replace: Boolean, expr: Regex)
