package views

import assets.Task
import controllers.routes.Application
import org.scaml._
import org.scaml.attributes._
import views.Template._

class Index extends Template(

  ml"""
    $title Mainpage

    $p{Create a new ${Link > Application.add()} task}

    $section{Unresolved Tasks}

    ${Task.unresolved.map { task =>
      ml"$p {${Link > Application.show(task.id).url}{${task.name}} (${task.duration})}"
    }}
  """
)
