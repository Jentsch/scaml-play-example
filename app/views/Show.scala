package views

import assets.Task
import org.scaml.templates.Web

class Show(task: Task) extends Web {

  form {

    headline"${task.name}"
  
    if (task.isOpen)
      p"Is Open"
    else
      p"Done"
  }
}
