package ir.pr.saman.parser.html

import ir.pr.saman.parser.html.usecase.ExtractArtistArtInfoUseCase
import ir.pr.saman.parser.html.util.Constants
import org.scalatest.WordSpec

class ParserSpec extends WordSpec {

  // Constants
  val artistName = "Pablo Picasso"
  val title = "Quatre Femmes nues et Tête sculptée, from: La Suite Vollard"
  val totalLifetimeValue = "6,875"
  // Tests

    "parser" should  {
      "extract the same artist name" in {
        val names = ExtractArtistArtInfoUseCase.call(Constants.ARTIST) map (f => f getOrElse ( Constants.ARTIST, "")) filter (_ == artistName)
        assert(names.headOption.getOrElse("") === artistName)
      }

      "contains specified title" in {
        val works = ExtractArtistArtInfoUseCase.call(Constants.ARTIST) map(f => f getOrElse(Constants.WORKS, ""))
        val titles = works flatMap(_.asInstanceOf[List[Map[String, Any]]]) map(_.getOrElse(Constants.TITLE, ""))
        assert(titles contains title)
      }

      "contains specified totalLifetimeValue" in {
        val works = ExtractArtistArtInfoUseCase.call(Constants.ARTIST) map(f => f getOrElse(Constants.WORKS, ""))
        val titles = works flatMap(_.asInstanceOf[List[Map[String, Any]]]) map(_.getOrElse(Constants.TOTAL_LIFETIME_VALUE, ""))
        assert(titles contains totalLifetimeValue)
      }
    }

}
