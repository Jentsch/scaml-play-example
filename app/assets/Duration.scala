package assets

import org.scaml.Node

import scala.language.implicitConversions

case class Duration(seconds: Int) extends AnyVal with Ordered[Duration] {
  def minutes = seconds / 60

  def hours = minutes / 60

  def days = hours / 24

  def workDays = hours / 8

  def workWeeks = workDays / 5

  def months = days / 30

  def compare(that: Duration) =
    this.seconds compare that.seconds

  def +(that: Duration) =
    new Duration(this.seconds + that.seconds)
}

object Duration {
  implicit val Ordering: Ordering[Duration] =
    implicitly[Ordering[Long]].on {
      _.seconds
    }

  def minutes(minutes: Int) =
    Duration(minutes * 60)

  implicit def inline(duration: Duration): Node =
    if (duration.seconds < 60) {
      duration.seconds.toString + " s"
    } else if (duration.seconds % 60 == 0) {
      f"${duration.minutes} min"
    } else {
      f"${duration.minutes}:${duration.seconds % 60}%02d min"
    }
}
