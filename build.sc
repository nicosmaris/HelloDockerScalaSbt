import mill._, scalalib._

object tdd extends ScalaModule{
  def scalaVersion = "2.11.11"
  def ivyDeps = Agg(
    ivy"com.twitter::scalding-core_2.10:0.10.0",
    ivy"com.twitter::scalding-commons_2.10:0.10.0",
    ivy"org.apache.hadoop::hadoop-core:2.0.0-mr1-cdh4.6.0",
    ivy"org.apache.hadoop::hadoop-common:2.0.0-cdh4.6.0"
  )
  object test extends Tests { 
    def ivyDeps = Agg(
      ivy"org.scalatest::scalatest_2.10:2.1.7",
      ivy"junit::junit:4.11",
      ivy"org.spec2::spec2_2.10:2.3.4",
      ivy"com.twitter::chill_2.10:0.3.6"
    )
    def testFrameworks = Seq("org.specs2.runner.Specs2Framework")
  }
}
