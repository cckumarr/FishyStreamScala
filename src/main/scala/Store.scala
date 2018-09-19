import scala.util.Try

object Store {
  def fromString(s: String): Try[Store] = {
    val parts = s.split(" ")
    Try(
      new Store(
        parts(0),
        parts(1).toLong
      )
    )
  }
}

case class Store(typeOfObject: String, quantity: Long){

}
