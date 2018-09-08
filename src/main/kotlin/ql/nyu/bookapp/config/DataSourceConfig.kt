package ql.nyu.bookapp.config

import com.zaxxer.hikari.HikariDataSource

object DataSourceConfig {
  fun getDataSource(): HikariDataSource {
    val dataSource = HikariDataSource()
    dataSource.jdbcUrl = System.getenv("JDBC_DATABASE_URL") ?: "jdbc:postgresql://localhost:5432/testposgis-dev?username=test"
    dataSource.driverClassName = "org.postgresql.Driver"
    return dataSource
  }
}
