package ir.pr.saman.parser.html.contract.service

trait GetWorksService extends Service[GetWorksService.Body, List[Map[String, Any]]]

object GetWorksService {

  case class Body(key: String, data: List[Map[String, Any]])

}
