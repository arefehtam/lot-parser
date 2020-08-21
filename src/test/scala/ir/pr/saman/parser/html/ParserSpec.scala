package ir.pr.saman.parser.html

import ir.pr.saman.parser.html.usecase.ExtractArtistLegacyInfoUseCase
import org.scalatest.WordSpec

class ParserSpec extends WordSpec {

  // Constants
  val artistName = "Pablo Picasso"
  // Tests

    "parser" should  {
      "extract the same artist name" in {
        val name = ExtractArtistLegacyInfoUseCase.call("Pablo Picasso")
        assert(name === artistName)
      }
    }

}
