package views

import assets.Task
import controllers.routes._
import org.scaml.attributes.Link

class Show(task: Task) extends Template {

  p"""
    $default {
      $title {${task.name} (${if (task.isOpen) "Open" else "Done"})}

      $p ${Link > Application.index()} {Back to main page}

      $p ${task.details}
    }
   """
}
