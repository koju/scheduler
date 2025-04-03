package dev.koju.scheduler.user.domain

import cats.effect.kernel.Concurrent
import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import org.http4s.EntityDecoder
import org.http4s.circe.jsonOf

case class CreateUser(
    firstName: String,
    lastName: String,
    email: String,
    password: String,
)

object CreateUser:
  given Decoder[CreateUser] = deriveDecoder
  given [F[_]: Concurrent]: EntityDecoder[F, CreateUser] = jsonOf
