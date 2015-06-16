package views

import assets.Task
import org.scaml.templates.Web
import org.scaml.attributes._
import controllers.routes.Application

object Index extends Web {
  
  headline"Mainpage"
  
  p"Create a new ${Link > Application.add().url} task"

  section"Unresolved Tasks"

  Task.unresolved.foreach { task =>
    p"${Link > Application.show(task.id).url}{${task.name}} (${task.duration})"
  }
}
