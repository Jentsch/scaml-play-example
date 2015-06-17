package views

import assets.Task
import controllers.routes.Application
import org.scaml.attributes._

class Index extends Template {

  p"""
    $default {
      $title Mainpage

      $p{Create a new ${Link > Application.add()} task}

      $section{Unresolved Tasks}
    }
  """

  Task.unresolved.foreach { task =>
    p"${Link > Application.show(task.id).url}{${task.name}} (${task.duration})"
  }
}
