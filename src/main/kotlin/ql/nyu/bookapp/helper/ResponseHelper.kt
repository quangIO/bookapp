package ql.nyu.bookapp.helper

import io.vertx.core.http.HttpHeaders
import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.Json

fun HttpServerResponse.endWithJson(obj: Any) =
  putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaders.createOptimized("application/json; charset=utf-8"))
    .end(Json.encodePrettily(obj))
