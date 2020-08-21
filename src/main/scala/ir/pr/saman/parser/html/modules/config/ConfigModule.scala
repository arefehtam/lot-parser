package ir.pr.saman.parser.html.modules.config

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

trait ConfigModule {

  val config: Config = ConfigFactory.load().withFallback(ConfigFactory.defaultApplication()).resolve()

}
