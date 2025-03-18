package dev.koju.scheduler

import cats.data.*
import cats.effect.{Async, Resource}
import dev.koju.scheduler.config.AppConfig
import fs2.io.net.Network
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits.*
import org.http4s.server.middleware.Logger

object Server:

  def run[F[_]: {Async, Network}]: F[Nothing] = {
    for {
      config <- Resource.eval(AppConfig.load[F])
      helloWorldAlg = HelloWorld.impl[F]
      httpApp = Routes.helloWorldRoutes[F](helloWorldAlg).orNotFound
      finalHttpApp = Logger.httpApp(true, true)(httpApp)
      _ <-
        EmberServerBuilder
          .default[F]
          .withHost(config.server.host)
          .withPort(config.server.port)
          .withHttpApp(finalHttpApp)
          .build
    } yield ()
  }.useForever
