package ql.nyu.bookapp

import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import io.vertx.kotlin.coroutines.CoroutineVerticle


class MainVerticle : CoroutineVerticle() {
  override suspend fun start() {
    val router = Router.router(vertx)
    // your code goes here...
    vertx.createHttpServer().requestHandler { req ->
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!")
    }.listen(8080) { res ->
      if (res.failed()) {
        res.cause().printStackTrace()
      } else {
        System.out.println("Server listening at: http://localhost:8080/")
      }
    }
  }
}
