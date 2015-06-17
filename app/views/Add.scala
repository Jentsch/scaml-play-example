package views

import controllers.routes._
import org.scaml.attributes.Link

object Add extends Template {
  p"""
    $default {
      $title{Create new task}
      ${postForm(Application.add().url)} {
        $p{Name ${textInput("name")}}
        $p{Expected duration ${textInput(name = "duration")} in seconds}

        $p $button Submit
      }
      $p ${Link > Application.index().url}{Cancel and go back to main page}
    }
  """
}

