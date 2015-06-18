package assets

import assets.Duration._

import scala.concurrent.stm.Ref

case class Task(name: String, duration: Duration, details: String = "", priority: Int = 0, isResolved: Boolean = false, id: Int = 0) {
  def isOpen = !isResolved

  def easierTask: Task =
    Task.unresolved.filter(_.duration < this.duration).max
}

/** All tasks ordered by priority */
object Task extends collection.Iterable[Task] {
  def unresolved = filter(_.isOpen)

  implicit val Ordering: Ordering[Task] = implicitly[Ordering[Int]].on(_.priority)

  private[Task] val store = Ref(Map.empty[Int, Task]).single

  // Members declared in scala.collection.IterableLike
  def iterator: Iterator[Task] = store.get.values.to[Seq].sorted.iterator

  override def size: Int = store.get.size

  def +=(task: Task): Int =
    if (task.id == 0) {
      store.transformAndExtract { tasks =>
        val newId = tasks.keys.fold(0)(_ max _) + 1
        (tasks + (newId -> task.copy(id = newId)), newId)
      }
    } else {
      store.transform(_ + (task.id -> task))
      task.id
    }

  def get(id: Int): Option[Task] =
    store.get.get(id)

  createTestData()

  private def createTestData(): Unit = {
    this += Task("Buy milk", minutes(45), priority = 10)
    this += Task("Repair the bike", minutes(120), priority = 5, isResolved = true)
    this += Task("Rule the world", minutes(20), "If some time left", 3)
  }

}
