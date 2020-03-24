name := "AutoTest"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq (
  "org.scalatest"%%"scalatest"%"3.0.8"%Test,
  "org.pegdown" % "pegdown" % "1.6.0" % Test)

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports")