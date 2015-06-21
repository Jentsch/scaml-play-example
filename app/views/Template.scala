package views

import org.scaml._
import org.scaml.attributes._
import play.api.mvc.Call

import scala.language.implicitConversions

object Template extends WebElements {

  val lightGray = rgb(200, 200, 200)

  override def headline = super.headline &
    FontFamily > "Ubuntu Condensed"

  override def p = super.p &
    FontFamily > "Verdana"

  implicit def callToUrl(call: Call): String =
    call.url

  def default(content: Node) = ml"""
       ${header & BackgroundColor > lightGray}{Your task manager}
       $content
       ${footer & BackgroundColor > lightGray}{${Link > "mailto:webmaster@example.com"} Contact}
     """
}
