package dev.koju.scheduler.user.api

import cats.*
import cats.effect.*
import cats.syntax.all.*
import dev.koju.scheduler.user.domain.{CreateUser, UserService}
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

final case class UserRoutes[F[_]: {MonadThrow, Concurrent}](
    userService: UserService[F]
) extends Http4sDsl[F]:

  val routes: HttpRoutes[F] = HttpRoutes.of[F] {
    case req @ POST -> Root / "users" =>
      for {
        createUser <- req.as[CreateUser]
        _ <- userService.createUser(createUser)
        response <- Created()
      } yield response
  }
