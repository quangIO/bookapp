package ql.nyu.bookapp.helper

import org.jetbrains.exposed.sql.ResultRow
import ql.nyu.bookapp.model.Book
import ql.nyu.bookapp.model.BookSchema
import ql.nyu.bookapp.model.User
import ql.nyu.bookapp.model.UserSchema

fun ResultRow.toBook() = Book(
  this[BookSchema.name], this[BookSchema.isbn], this[BookSchema.owner], this[BookSchema.availability], this[BookSchema.id]
)

fun ResultRow.toUser() = User(
  this[UserSchema.email], this[UserSchema.address], this[UserSchema.password]
)
