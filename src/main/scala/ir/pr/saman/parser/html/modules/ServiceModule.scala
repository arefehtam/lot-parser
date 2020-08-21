package ir.pr.saman.parser.html.modules

import ir.pr.saman.parser.html.contract.service.ExtractArtistLegacyInfoService
import ir.pr.saman.parser.html.usecase.ExtractArtistLegacyInfoUseCase

object ServiceModule {

  val extractArtistLegacyInfoService: ExtractArtistLegacyInfoService = ExtractArtistLegacyInfoUseCase

}
