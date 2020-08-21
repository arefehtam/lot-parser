package ir.pr.saman.parser.html.contract.service

trait Service[In, Out] {
  def call(body: In): Out
}
