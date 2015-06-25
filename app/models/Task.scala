package models

import models.Duration._

import scala.concurrent.stm.Ref

case class Task(name: String, duration: Duration, details: String = "", priority: Priority = Priority(0), done: Boolean = false, id: Int = 0) {
  def open = !done
}

/** All tasks ordered by priority */
object Task extends collection.Iterable[Task] {
  def open = filter(_.open)
  def done = filter(_.done)

  implicit val Ordering: Ordering[Task] = scala.Ordering.by(_.priority)

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
    this += Task("Buy milk", minutes(45), priority = Priority(9))
    this += Task("Repair the bike", minutes(120), priority = Priority(5), done = true)
    this += Task("Rule the world", minutes(20), "If some time left", Priority(3))
    this += Task("Think about 42", minutes(120), priority = Priority(9))
    this += Task("Be borne", minutes(9 * 30 * 24 * 60), done = true)
  }

}
