package ir.pr.saman.parser.html.contract.callback

import org.jsoup.nodes.Document

trait DocumentCallback {

  def getAll: List[Document]

}
