package ir.pr.saman.parser.html

import ir.pr.saman.parser.html.usecase.GetArtistNameUseCase
import org.scalatest.WordSpec

class ParserSpec extends WordSpec {

  // Constants
  val artistName = "Pablo Picasso"
  // Tests

    "parser" should  {
      "extract the same artist name" in {
        val name = GetArtistNameUseCase.call("Pablo Picasso")
        assert(name === artistName)
      }
    }

}
