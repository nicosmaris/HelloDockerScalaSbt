// build.sc
import mill._, scalalib._

object foo extends ScalaModule{
  def scalaVersion = "2.11.11"
  def ivyDeps = Agg(
    ivy"org.eclipse.tycho::tycho-compiler-jdt:0.21.0",
    ivy"org.apache.flink::flink-scala:1.5.0",
    ivy"org.apache.flink::flink-streaming-scala:1.5.0",
    ivy"org.apache.flink::flink-test-utils:1.5.0"
  )
  object test extends Tests { 
    def ivyDeps = Agg(ivy"com.lihaoyi::utest:0.6.0")
    def testFrameworks = Seq("utest.runner.Framework")
  }
}
