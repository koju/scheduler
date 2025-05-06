package dev.koju.scheduler.user.domain

import java.util.UUID

opaque type UserId = UUID

case class User(
    id: UserId,
    firstName: String,
    lastName: String,
    email: String,
    password: String,
)
