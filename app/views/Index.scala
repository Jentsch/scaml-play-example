package views

import org.scaml.templates.Web
import org.scaml.attributes._
import controllers.routes.Application

object Index extends Web {
  
  headline"Mainpage"
  
  p"Create a new ${Link > Application.add().url} offer."

  p"Show first item ${Link > Application.show(1).url} ever."
}

