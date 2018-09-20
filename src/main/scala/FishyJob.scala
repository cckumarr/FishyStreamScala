import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FishyJob {

  def main(args: Array[String]) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val boatPort = 2000
    val storePort = 3000
    val boatHost = "localhost"
    val storeHost = "localhost"
    val strTocompareWith = "salmon,tuna,cod"

    println("starting the job.")

    val boats = env.socketTextStream(boatHost, boatPort).map(Boat.fromString(_))
      .flatMap(DataCollector[Boat])
      .filter(boat => strTocompareWith.contains(boat.typeOfObject))
      .keyBy(_.typeOfObject)

    val stores = env.socketTextStream(storeHost, storePort).map(Store.fromString(_))
      .flatMap(DataCollector[Store])
      .keyBy(_.typeOfObject)

    boats.connect(stores).flatMap(FishyProcessor()).print()

    env.execute()
  }
}
