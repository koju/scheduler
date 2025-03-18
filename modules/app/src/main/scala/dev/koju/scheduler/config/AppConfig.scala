package dev.koju.scheduler.config

import cats.*
import cats.effect.*
import cats.implicits.*

final case class AppConfig(server: ServerConfig)

object AppConfig:
  def load[F[_]: Async]: F[AppConfig] =
    ServerConfig.read.map(AppConfig.apply).load[F]
