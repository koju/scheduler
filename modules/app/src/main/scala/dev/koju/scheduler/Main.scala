package dev.koju.scheduler

import cats.effect.{IO, IOApp}

object Main extends IOApp.Simple:
  val run = Server.run[IO]
