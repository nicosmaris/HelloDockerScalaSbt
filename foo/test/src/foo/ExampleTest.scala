package foo

import utest._
import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import scala.collection.mutable.ListBuffer

import org.apache.flink.api.java.typeutils.ResultTypeQueryable
import org.apache.flink.api.java.typeutils.TypeExtractor
import org.apache.flink.api.common.typeinfo.TypeInformation
//import org.apache.flink.test.util.AbstractTestBase

object Companion {
    // must be static
    val values: ListBuffer[Long] = new ListBuffer[Long]()
}
/* UUT */
class MultiplyByTwo extends MapFunction[Long, Long] with ResultTypeQueryable[Long] {
    override def getProducedType: TypeInformation[Long] = TypeExtractor.getForClass(classOf[Long])
    override def map(value: Long): Long = {
        value * 2
    }
}

// create a testing sink
class CollectSink extends SinkFunction[Long] {
    override def invoke(value: Long): Unit = {
        synchronized {
            Companion.values.append(value)
        }
    }
}

object ExampleTests extends TestSuite {
  val tests = Tests{
    'test1 - {
        val env = StreamExecutionEnvironment.getExecutionEnvironment
        // configure your test environment
        env.setParallelism(1)
        // values are collected in a static variable
        Companion.values.clear()
        // create a stream of custom elements and apply transformations
        env
            .fromElements(1L, 21L, 22L)
            .map(new MultiplyByTwo())
            .addSink(new CollectSink())
        // execute
        env.execute()
       // verify your results
        assert(ListBuffer(2L, 42L, 44L) == Companion.values)
    }
  }
}

