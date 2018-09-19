import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.util.Collector

import scala.util.{Failure, Success, Try}

object DataCollector {
  def apply[T] = new DataCollector[T]
}

class DataCollector[T] extends FlatMapFunction[Try[T], T] {
  override def flatMap(t: Try[T], out: Collector[T]): Unit = {
    t match {
      case Success(t) => out.collect(t)
      case Failure(e) => println(s"BAD DATA: ${e.getMessage}")
    }
  }
}