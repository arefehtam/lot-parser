package ir.pr.saman.parser.html.util

import java.io.File

object FileUtil {

  implicit class DirToFileList(dir: File) {

    def getFiles(extensions: List[String], dir: File = this.dir): List[File] = {
      val files = dir.listFiles.toList
      files.filter(file => extensions exists (file.getName.endsWith(_))) ++ files.filter(_.isDirectory).flatMap(getFiles(extensions, _))
    }
  }

}
