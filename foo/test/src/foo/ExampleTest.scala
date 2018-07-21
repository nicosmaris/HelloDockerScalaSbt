package foo

import utest._

object ExampleTests extends TestSuite {
  val tests = Tests{
    'test1 - {
      assert(1+1 == 2) 
    }
  }
}
  /*
    @Test
    def testMultiply(): Unit = {
        val env = StreamExecutionEnvironment.getExecutionEnvironment
        // configure your test environment
        env.setParallelism(1)
        // values are collected in a static variable
        CollectSink.values.clear()
        // create a stream of custom elements and apply transformations
        env
            .fromElements(1L, 21L, 22L)
            .map(new MultiplyByTwo())
            .addSink(new CollectSink())
        // execute
        env.execute()
        // verify your results
        assertEquals(Lists.newArrayList(2L, 42L, 44L), CollectSink.values)
    }
}    

// create a testing sink
class CollectSink extends SinkFunction[Long] {
    override def invoke(value: java.lang.Long): Unit = {
        synchronized {
            values.add(value)
        }
    }
}

object CollectSink {
    // must be static
    val values: List[Long] = new ArrayList()
}
*/
