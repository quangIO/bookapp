package ql.nyu.bookapp

import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ql.nyu.bookapp.config.DataSourceConfig
import ql.nyu.bookapp.helper.endWithJson
import ql.nyu.bookapp.helper.toBook
import ql.nyu.bookapp.model.Book
import ql.nyu.bookapp.model.BookSchema
import ql.nyu.bookapp.model.UserSchema

data class UserDemo(val name: String, val age: Int)

class MainVerticle : CoroutineVerticle() {
  override suspend fun start() {
    val router = Router.router(vertx)
    router.route().handler(BodyHandler.create())
    val port = System.getenv("PORT")?.toInt() ?: 8080

    Database.connect(DataSourceConfig.getDataSource())

    transaction {
      drop(UserSchema, BookSchema)
      create(UserSchema, BookSchema)
      UserSchema.insert {
        it[UserSchema.email] = "qtl206"
        it[UserSchema.address] = "NYU"
        it[UserSchema.password] = "test"
      }
    }
    router.get("/list").handler { context ->
      val books = transaction {
        BookSchema.selectAll().map(ResultRow::toBook)
      }
      context.response().endWithJson(books)
    }

    router.post("/add").handler { context ->
      val book = context.bodyAsJson.mapTo(Book::class.java)
      transaction {
        BookSchema.insert {
          it[BookSchema.name] = book.name
          it[BookSchema.availability] = book.isAvailable
          it[BookSchema.isbn] = book.isbn
          it[BookSchema.owner] = book.owner
        }
      }
      context.response().endWithJson(book)
    }

    vertx.createHttpServer().requestHandler(router::accept).listen(port) { res ->
      if (res.failed()) res.cause().printStackTrace() else println("start at port $port")
    }
  }
}


