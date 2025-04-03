package dev.koju.scheduler.user

import cats.Applicative
import cats.syntax.all.*
import dev.koju.scheduler.user.domain.{CreateUser, UserRepo}

class UserRepoLive[F[_]: Applicative] extends UserRepo[F]:
  override def create(user: CreateUser): F[Unit] = {
    println(s"User created: $user").pure[F]
  }
