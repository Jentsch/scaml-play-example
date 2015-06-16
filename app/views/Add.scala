package views

import controllers.routes._
import org.scaml.attributes.Link
import org.scaml.templates.Web

object Add extends Web {

  headline"Create new item"

  form"""
    $p{Item name $input(name='name)}

    $p{Item price $input(name='price)}
  """
  
  p"${Link > Application.index().toString()}{Cancel and go back to main page}"
}

