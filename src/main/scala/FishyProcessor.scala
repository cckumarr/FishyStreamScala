import org.apache.flink.api.common.functions.{AbstractRichFunction, RichFlatMapFunction}
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.co.{CoProcessFunction, RichCoFlatMapFunction}
import org.apache.flink.util.Collector

object FishyProcessor{
  def apply() = new FishyProcessor
}

class FishyProcessor extends RichCoFlatMapFunction[Boat,Store,FishyResult]{

  var fishState: ValueState[FishyResult] = _

  val fishyResult = new FishyResult("something",0)

  override def flatMap1(value: Boat, out: Collector[FishyResult]): Unit = {

    var result = fishyResult

    if(fishState.value() != null){
      result = fishState.value()
    }

    fishState.update(result.copy(quantity = result.quantity + value.quantity))
    println("in flatmap1 : " + fishState.value())
  }

  override def flatMap2(value: Store, out: Collector[FishyResult]): Unit = {
    val result = fishState.value()
    fishState.update(result.copy(quantity = result.quantity - value.quantity))
    println("in flatmap2 : " + fishState.value())
  }

  override def open(parameters: Configuration): Unit = {
    val state = new ValueStateDescriptor[FishyResult]("fishyResult", createTypeInformation[FishyResult],fishyResult)
    state.setQueryable("fishyResult")
    fishState = getRuntimeContext.getState(state)
  }
}
