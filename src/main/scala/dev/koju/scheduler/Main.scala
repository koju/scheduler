package dev.koju.scheduler

import cats.effect.{IO, IOApp}

object Main extends IOApp.Simple:
  override val run: IO[Unit] = SchedulerServer.run[IO]