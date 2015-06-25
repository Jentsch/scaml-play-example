package models

import org.scaml._
import org.scaml.attributes._

import scala.language.implicitConversions

class Priority private(val value: Int) extends AnyVal {
  def compare(that: Priority) =
    this.value compare that.value
}

object Priority {

  def apply(priority: Int) = {
    require(priority < 10 & priority >= 0)
    new Priority(priority)
  }

  implicit def inline(duration: Priority): Node = duration.value match {
    case 0 | 1 | 2 => ml"${TextColor > green}{ Low (${duration.value}) }"
    case 3 | 4 | 5 | 6 => ml"${TextColor > blue}{ Medium (${duration.value}) }"
    case 7 | 8 | 9 => ml"${TextColor > green}{ High (${duration.value}) }"
  }

  implicit val Ordering: Ordering[Priority] =
    implicitly[Ordering[Long]].on {
      _.value
    }
}