package views

import assets.Item
import controllers.routes._
import org.scex.attributes.Link
import org.scex.templates.Web

class Show(item: Item) extends Web {

  headline"${item.name}"

  p"Buy if for only ${item.price.toString()}"

  p"Back to the ${Link > Application.index().toString()}{Main page}"
}
