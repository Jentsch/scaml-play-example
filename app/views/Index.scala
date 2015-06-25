package views

import controllers.routes.Application
import models.Task
import org.scaml._
import org.scaml.attributes._
import views.Template._

object Index {
  def render = default( ml"""
    $title Mainpage

    $p{ Create a new ${Link > Application.add()} task }

    $section{ Unresolved Tasks }

    ${
      Task.open.map { task =>
        ml"$p{ ${Link > Application.show(task.id)}{${task.name}} (${task.duration}) }"
      }
    }

    $p ${Link > Application.done()}{ See resolved Task }
  """)
}
