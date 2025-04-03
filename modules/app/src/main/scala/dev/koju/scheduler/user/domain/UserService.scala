package dev.koju.scheduler.user.domain

trait UserService[F[_]]:
  def createUser(user: CreateUser): F[Unit]

