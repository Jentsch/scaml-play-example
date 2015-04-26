package controllers

import play.api._
import play.api.mvc._
import views._
import play.api.http.Writeable
import org.scex.Builder
import org.scex.generators.{HTML => HtmlGenrator}

object Application extends Controller {

  def index = Action {
    Ok(Index)
  }

  def add = Action {
    Ok(Add)
  }
  
  def show(id: Int) = Action {
    Ok(new Show(id))
  }
  
  def update(id: Int) = Action {
    NotImplemented
  }
  
  def remove(id: Int) = Action {
    NotImplemented
  }
  
  def search = Action {
    NotImplemented
  }

  implicit val ScexWriteable = Writeable[Builder]({(doc: Builder) => HtmlGenrator(doc).toString().getBytes}, Some("text/html"))

}