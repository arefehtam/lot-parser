package ir.pr.saman.parser.html.usecase

import ir.pr.saman.parser.html.contract.service.GetArtistNameService

trait GetArtistNameUseCase extends GetArtistNameService {
  override def call(body: String): String = {
    body
  }
}

object GetArtistNameUseCase extends GetArtistNameUseCase
