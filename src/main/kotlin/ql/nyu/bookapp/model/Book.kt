package ql.nyu.bookapp.model

import org.jetbrains.exposed.sql.Table

data class Book(
  var name: String = "Unnamed",
  var isbn: String = "N/A",
  var owner: String = "Unknown",
  var isAvailable: Boolean = true,
  var id: Int = -1
)

object BookSchema: Table("books") {
  val id = integer("id").autoIncrement().primaryKey()
  val isbn = text("isbn")
  val name = text("name")
  val owner = text("owner") references UserSchema.email
  val availability = bool("availability").default(true)
}
