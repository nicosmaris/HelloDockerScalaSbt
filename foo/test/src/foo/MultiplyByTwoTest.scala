package foo

import utest._
import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import scala.collection.mutable.ListBuffer

import org.apache.flink.api.java.typeutils.ResultTypeQueryable
import org.apache.flink.api.common.typeinfo.BasicTypeInfo
import org.apache.flink.api.common.typeinfo.TypeInformation
//import org.apache.flink.test.util.AbstractTestBase

object Companion {
    // must be static
    val values: ListBuffer[Long] = new ListBuffer[Long]()
}

// create a testing sink
class CollectSink extends SinkFunction[Long] with ResultTypeQueryable[Long] {
    override def getProducedType: TypeInformation[Long] =
      BasicTypeInfo.LONG_TYPE_INFO.asInstanceOf[TypeInformation[Long]]
    override def invoke(value: Long): Unit = {
        synchronized {
            Companion.values.append(value)
        }
    }
}

object MultiplyByTwoTests extends TestSuite {
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

