package dev.koju.scheduler.config

import cats.*
import cats.implicits.*
import ciris.*
import com.comcast.ip4s.*

final case class ServerConfig(
    host: Host,
    port: Port,
)

object ServerConfig:
  given ConfigDecoder[String, Host] =
    ConfigDecoder[String].mapOption("com.comcast.ip4s.Host")(Host.fromString)

  given ConfigDecoder[String, Port] =
    ConfigDecoder[String].mapOption("com.comcast.ip4s.Port")(Port.fromString)

  def read[F[_]]: ConfigValue[F, ServerConfig] =
    (
      prop("server.host").as[Host].default(ipv4"0.0.0.0"),
      prop("server.port").as[Port].default(port"8080"),
    ).parMapN(ServerConfig.apply)
