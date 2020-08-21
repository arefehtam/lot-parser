package ir.pr.saman.parser.html.usecase

import ir.pr.saman.parser.html.contract.service.GetWorksService

trait GetWorksUseCase extends GetWorksService {
  /**
    * Get works of an artist including title, currency and totalLifetimeValue.
    *
    * @param Body .key a unique key (currently artist name) which should not come in result
    * @param Body .data list of all info of for one artist
    * @return works of an artist: all info except the key
    */
  override def call(body: GetWorksService.Body): List[Map[String, Any]] = {
    body.data map { artist =>
      artist - body.key
    }
  }

}

object GetWorksUseCase extends GetWorksUseCase
