package ir.pr.saman.parser.html.usecase

import ir.pr.saman.parser.html.contract.service.ExtractTotalSalesValueService
import ir.pr.saman.parser.html.util.Constants

import scala.language.postfixOps

trait ExtractTotalSalesValueUseCase extends ExtractTotalSalesValueService {
  /**
    * Extract total sales value for every artist based on the amount and currency of their work.
    * First, all amount value is converted to number, then based on their currency, conversion rate is done
    * and all amounts are summed
    *
    * @param Body.data list of all work of art for one artist
    * @return calculated total sales value in appropriate format: USD xxxxxxxxx
    */
  override def call(body: ExtractTotalSalesValueService.Body): String = {
    val totalSalesValue = body.data.map { artist =>
      val saleValue = """\d*""".r.findAllIn(artist getOrElse(Constants.TOTAL_LIFETIME_VALUE, "0") toString).mkString("") toDouble
      val currency = artist.getOrElse(Constants.CURRENCY, Constants.GBP).toString
      if (currency equalsIgnoreCase Constants.GBP) saleValue * Constants.CONVERSION_RATE else saleValue
    }.sum

    "USD %7.3f".format(totalSalesValue)
  }

}

object ExtractTotalSalesValueUseCase extends ExtractTotalSalesValueUseCase

