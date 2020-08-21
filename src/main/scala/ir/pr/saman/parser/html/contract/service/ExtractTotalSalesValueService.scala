package ir.pr.saman.parser.html.contract.service

trait ExtractTotalSalesValueService extends Service[ExtractTotalSalesValueService.Body, String]

object ExtractTotalSalesValueService {

  case class Body(data: List[Map[String, Any]])

}
