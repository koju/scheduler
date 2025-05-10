package dev.koju.scheduler.config

import cats.effect.*

final case class AppConfig(server: ServerConfig)

object AppConfig:
  def load[F[_]: Async]: F[AppConfig] =
    ServerConfig.read.map(AppConfig.apply).load[F]
