package ir.pr.saman.parser.html

import ir.pr.saman.parser.html.usecase.ExtractArtistLegacyInfoUseCase
import org.scalatest.WordSpec

class ParserSpec extends WordSpec {

  // Constants
  val artistName = "Pablo Picasso"
  val title = "Quatre Femmes nues et Tête sculptée, from: La Suite Vollard"
  // Tests

    "parser" should  {
      "extract the same artist name" in {
        val names = ExtractArtistLegacyInfoUseCase.call("artist") map (f => f getOrElse ( "artist", "")) filter (_ == artistName)
        assert(names.headOption.getOrElse("") === artistName)
      }

      "contains specified title" in {
        val works = ExtractArtistLegacyInfoUseCase.call("artist") map(f => f getOrElse("works", ""))
        val titles = works flatMap(_.asInstanceOf[List[Map[String, Any]]]) map(_.getOrElse("title", ""))
        assert(titles contains title)
      }
    }

}
