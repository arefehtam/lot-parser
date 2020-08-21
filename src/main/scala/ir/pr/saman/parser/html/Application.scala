package ir.pr.saman.parser.html

import com.typesafe.scalalogging.Logger
import ir.pr.saman.parser.html.modules.ServiceModule.extractArtistLegacyInfoService
import org.json4s.DefaultFormats
import org.json4s.native.Json

object Application extends App {
  val logger: Logger = Logger("Application")
  implicit val jsonFormat = Json(DefaultFormats)

  logger.info(
    jsonFormat.writePretty(
         extractArtistLegacyInfoService call "artist"
    )
  )
}
