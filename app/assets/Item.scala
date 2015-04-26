package assets

import java.util.concurrent.atomic.AtomicInteger

import scala.collection.parallel.mutable.ParTrieMap

class Item private (val id: Int, val name: String, val price: BigDecimal) {
  def copy(name: String, price: BigDecimal) =
    new Item(id, name, price)
}

object Item {
  private val store = ParTrieMap.empty[Int, Item]
  private val lastId = new AtomicInteger

  createTestData

  def add(name: String, price: BigDecimal): Item = {
    val id = lastId.incrementAndGet()
    val item = new Item(id, name, price)
    store(id) = item
    item
  }

  def update(item: Item): Unit =
    store(item.id) = item

  def remove(item: Item):Unit =
    store.remove(item.id)

  def get(id: Int): Option[Item] = {
    store.get(id)
  }

  def createTestData = {
    add("Blue car", 10000)
    add("Red car", 12000)
    add("Green car", 11000)
  }


}
