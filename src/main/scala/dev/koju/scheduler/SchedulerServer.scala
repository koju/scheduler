package dev.koju.scheduler

import cats.data.*
import cats.effect.{Async, Resource}
import cats.syntax.all.*
import com.comcast.ip4s.*
import fs2.io.net.Network
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits.*
import org.http4s.server.middleware.Logger
import org.typelevel.log4cats.*
import org.typelevel.log4cats.slf4j.Slf4jFactory

object SchedulerServer:

  def run[F[_] : {Async, Network}]: F[Nothing] = {
    given LoggerFactory[F] = Slf4jFactory.create[F]

    for {
      helloWorldAlg <- HelloWorld.impl[F].pure[Resource[F, *]]
      httpApp =
        SchedulerRoutes.helloWorldRoutes[F](helloWorldAlg)
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
