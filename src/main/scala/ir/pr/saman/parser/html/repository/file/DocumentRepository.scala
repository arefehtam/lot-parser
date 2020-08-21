package ir.pr.saman.parser.html.repository.file

import java.io.File

import ir.pr.saman.parser.html.contract.callback.DocumentCallback
import ir.pr.saman.parser.html.modules.config.File._
import ir.pr.saman.parser.html.repository.file.adapter.DocumentFactory
import ir.pr.saman.parser.html.util.FileUtil._
import org.jsoup.nodes.Document

import scala.io.Source

trait DocumentRepository extends DocumentCallback {

  override def getAll: List[Document] = {

    for {
      file <- DocumentRepository.filesDirectory getFiles fileExtensions
      fileContent = Source.fromFile(file.getPath).getLines.mkString
      doc = DocumentFactory document fileContent
    } yield doc

  }
}

object DocumentRepository extends DocumentRepository {

  val filesDirectory = new File(dirPath)
}
