package views

import org.scaml.templates.Web
import org.scaml.attributes._
import controllers.routes.Application

object Index extends Web {
  
  headline"Mainpage"
  
  p"Create a new ${Link > Application.add().url} tas.k"

  section"Unresolved Tasks"

  assets.Task.filter(! _.isResolved).foreach { task =>
    p"${Link > Application.show(task.id).url}{${task.name}} (${task.duration.minutes.toString})"
  }
}
