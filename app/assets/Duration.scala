package assets

import org.scaml.Node

import scala.language.implicitConversions

case class Duration(seconds: Int) extends AnyVal with Ordered[Duration] {
  def minutes = seconds / 60

  def hours = minutes / 60

  def days = hours / 24

  def workDays = hours / 8

  def workWeeks = workDays / 5

  def compare(that: Duration) =
    this.seconds compare that.seconds

  def niceString =
    if (seconds < 60) {
      seconds.toString + " s"
    } else if (seconds % 60 == 0) {
      f"$minutes min"
    } else {
      f"$minutes:${seconds % 60}%02d min"
    }
}

object Duration {
  implicit val Ordering: Ordering[Duration] =
    implicitly[Ordering[Long]].on {
      _.seconds
    }

  def minutes(minutes: Int) =
    Duration(minutes * 60)

  implicit def inline(duration: Duration): Node =
    duration.niceString
}
