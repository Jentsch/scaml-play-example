package assets

case class Duration(val seconds: Int) extends AnyVal with Ordered[Duration] {
  def minutes = seconds / 60
  def hours = minutes / 60
  def days = hours / 24
  def workDays = hours / 8
  def workWeeks = workDays / 5
  
  def compare(that: Duration) =
    this.seconds compare that.seconds
}

object Duration {
  implicit val Ordering: Ordering[Duration] =
    implicitly[Ordering[Long]].on { _.seconds }

  def minutes(minutes: Int) =
    Duration(minutes * 60)
}
