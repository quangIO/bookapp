package ql.nyu.bookapp.model

import org.jetbrains.exposed.sql.Table

data class User(
  val email: String,
  val address: String,
  val password: String
)

object UserSchema: Table("people") {
  val email = text("email").primaryKey()
  val address = text("address")
  val password = text("password")
}
