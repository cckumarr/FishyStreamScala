import scala.util.Try

object Boat {
  def fromString(s: String): Try[Boat] = {
    val parts = s.split(" ")
    Try(
      new Boat(
        parts(0),
        parts(1).toLong
      )
    )
  }
}

case class Boat(typeOfObject: String, quantity: Long){

}
