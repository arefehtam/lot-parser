package ir.pr.saman.parser.html.modules.config

object FileModule extends ConfigModule {

  import collection.JavaConverters._

  lazy val dirPath: String = config getString "file.dirPath"
  lazy val fileExtensions: List[String] = config.getStringList("file.fileExtensions").asScala.toList

}
