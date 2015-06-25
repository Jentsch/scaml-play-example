package views

import controllers.routes._
import models.Task
import org.scaml._
import org.scaml.attributes.Link
import views.Template._

object Show {

  def render(task: Task) = default( ml"""
      $title { ${task.name} (${if (task.open) "Open" else "Done"}) }

      $p{ Priority ${task.priority} }

      $p ${Link > Application.index()} { Back to main page }

      $p ${task.details}
   """)
}