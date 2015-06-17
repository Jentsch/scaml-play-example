package views

import org.scaml._
import org.scaml.attributes._
import org.scaml.templates.Web
import play.api.mvc.Call

import scala.language.implicitConversions

trait Template extends Web {
  implicit def callToUrl(call: Call): String =
    call.url

  private implicit val puppet = new Builder {}


  val lightGray = rgb(200, 200, 200)

  override def headline = super.headline &
    FontFamily > "Ubuntu Condensed"

  override def p = super.p &
    FontFamily > "Verdana"



  val default = (content: Node) =>
    p"""
       ${header & BackgroundColor > lightGray}{Your task manager}
       $content
       ${footer & BackgroundColor > lightGray}{${Link > "mailto:webmaster@example.com"} Contact}
     """
}
