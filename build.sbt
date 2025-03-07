val V = new {
  val Http4s = "1.0.0-M44"
  val Munit = "1.1.0"
  val Log4Cats = "2.7.0"
  val Logback = "1.5.17"
  val MunitCatsEffect = "2.0.0"
  val Jansi = "2.4.1"
}

lazy val root = (project in file("."))
  .settings(
    organization := "dev.koju",
    name := "scheduler",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "3.6.3",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % V.Http4s,
      "org.http4s" %% "http4s-ember-client" % V.Http4s,
      "org.http4s" %% "http4s-circe" % V.Http4s,
      "org.http4s" %% "http4s-dsl" % V.Http4s,
      "org.scalameta" %% "munit" % V.Munit % Test,
      "org.typelevel" %% "munit-cats-effect" % V.MunitCatsEffect % Test,
      "org.typelevel" %% "log4cats-slf4j" % V.Log4Cats,
      "ch.qos.logback" % "logback-classic" % V.Logback,
      "org.fusesource.jansi" % "jansi" % V.Jansi
    ),
    testFrameworks += new TestFramework("munit.Framework")
  )
