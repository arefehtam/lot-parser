package ir.pr.saman.parser.html.modules

import ir.pr.saman.parser.html.contract.service
import ir.pr.saman.parser.html.usecase

object ServiceModule {

  import service.ExtractArtistArtInfoService
  import usecase.ExtractArtistArtInfoUseCase

  val extractArtistLegacyInfoService: ExtractArtistArtInfoService = ExtractArtistArtInfoUseCase

  object TotalValue {

    import service.ExtractTotalSalesValueService
    import usecase.ExtractTotalSalesValueUseCase

    val extractTotalSalesValueService: ExtractTotalSalesValueService = ExtractTotalSalesValueUseCase
  }

  object Works {

    import service.GetWorksService
    import usecase.GetWorksUseCase

    val extractWorkService: GetWorksService = GetWorksUseCase
  }

}
