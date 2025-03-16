package dev.koju.scheduler

import cats.data.*
import cats.effect.{Async, Resource}
import cats.syntax.all.*
import com.comcast.ip4s.*
import fs2.io.net.Network
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits.*
import org.http4s.server.middleware.Logger

object Server:

  def run[F[_] : {Async, Network}]: F[Nothing] = {
    for {
      helloWorldAlg <- HelloWorld.impl[F].pure[Resource[F, *]]
      httpApp =
        Routes.helloWorldRoutes[F](helloWorldAlg)
          .orNotFound
      finalHttpApp = Logger.httpApp(true, true)(httpApp)
      _ <-
        EmberServerBuilder.default[F]
          .withHost(ipv4"0.0.0.0")
          .withPort(port"8080")
          .withHttpApp(finalHttpApp)
          .build
    } yield ()
  }.useForever
