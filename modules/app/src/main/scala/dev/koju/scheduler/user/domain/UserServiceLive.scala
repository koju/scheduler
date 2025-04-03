package dev.koju.scheduler.user.domain

final case class UserServiceLive[F[_]](userRepo: UserRepo[F])
    extends UserService[F]:

  override def createUser(user: CreateUser): F[Unit] = userRepo.create(user)
