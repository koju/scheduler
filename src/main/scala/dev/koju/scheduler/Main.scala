package dev.koju.scheduler

import cats.effect.{IO, IOApp}
import org.typelevel.log4cats.LoggerFactory
import org.typelevel.log4cats.slf4j.Slf4jFactory

object Main extends IOApp.Simple:
  override val run: IO[Unit] =
    given LoggerFactory[IO] = Slf4jFactory.create[IO]

    SchedulerServer.run[IO]