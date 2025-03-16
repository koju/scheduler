val V = new {
  val Http4s = "0.23.30"
  val Log4Cats = "2.7.0"
  val Logback = "1.5.17"
  val Jansi = "2.4.1"
}

lazy val root = (project in file("."))
  .settings(
    organization := "dev.koju",
    name := "scheduler",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "3.6.4",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % V.Http4s,
      "org.http4s" %% "http4s-circe" % V.Http4s,
      "org.http4s" %% "http4s-dsl" % V.Http4s,
      "org.typelevel" %% "log4cats-slf4j" % V.Log4Cats,
      "ch.qos.logback" % "logback-classic" % V.Logback,
      "org.fusesource.jansi" % "jansi" % V.Jansi
    )
  )
