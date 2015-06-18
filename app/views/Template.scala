package views

import org.scaml._
import org.scaml.attributes._
import play.api.mvc.Call

import scala.language.implicitConversions

object Template extends WebElements {
  implicit def callToUrl(call: Call): String =
    call.url


  val lightGray = rgb(200, 200, 200)

  override def headline = super.headline &
    FontFamily > "Ubuntu Condensed"

  override def p = super.p &
    FontFamily > "Verdana"
}

class Template(val content: Node) {
  import Template._

  def document = ml"""
       ${header & BackgroundColor > lightGray}{Your task manager}
       $content
       ${footer & BackgroundColor > lightGray}{${Link > "mailto:webmaster@example.com"} Contact}
     """
}
