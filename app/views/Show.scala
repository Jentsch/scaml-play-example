package views

import org.scex.templates.Web

class Show(id: Int) extends Web {

  headline"Item ${id.toString}"
}
