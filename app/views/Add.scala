package views

import org.scex.templates.Web

object Add extends Web {

  headline"Create new item"

  form {
    p"Item name"

    p"Item price"
  }
}