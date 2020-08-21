package ir.pr.saman.parser.html.repository.file.adapter

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object DocumentFactory {

  def document(dto: String): Document = Jsoup.parse(dto)

}
