package views

import assets.Task
import controllers.routes._
import org.scaml._
import org.scaml.attributes.Link
import views.Template._

class Show(task: Task) extends Template(

  ml"""
      $title {${task.name} (${if (task.isOpen) "Open" else "Done"})}

      $p ${Link > Application.index()} {Back to main page}

      $p ${task.details}
   """
)
