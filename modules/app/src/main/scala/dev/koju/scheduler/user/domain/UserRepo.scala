package dev.koju.scheduler.user.domain

trait UserRepo[F[_]]:
  def create(user: CreateUser): F[Unit]
