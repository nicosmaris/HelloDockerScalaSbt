package foo

import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import scala.collection.mutable.ListBuffer

import org.apache.flink.api.java.typeutils.ResultTypeQueryable
import org.apache.flink.api.common.typeinfo.BasicTypeInfo
import org.apache.flink.api.common.typeinfo.TypeInformation

class MultiplyByTwo extends MapFunction[Long, Long] with ResultTypeQueryable[Long] {
    override def getProducedType: TypeInformation[Long] =
      BasicTypeInfo.LONG_TYPE_INFO.asInstanceOf[TypeInformation[Long]]
    override def map(value: Long): Long = {
        value * 2
    }
}
