package views

import org.scaml.templates.Web

object Add extends Web {

  headline"Create new item"

  form {
    p"Item name"

    p"Item price"
  }
}

