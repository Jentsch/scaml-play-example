package controllers

import play.api._
import play.api.mvc._
import views._
import play.api.http.Writeable
import org.scex.Builder
import org.scex.generators.HTML
import play.api.http.ContentTypeOf

object Application extends Controller {

  def index = Action {
    Ok(Index)
  }

  def add = Action {
    NotImplemented
  }
  
  def show(id: Int) = Action {
    NotImplemented
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
  
  
  implicit val ScexWriteable = Writeable[Builder]({(doc: Builder) => org.scex.generators.HTML(doc).toString.getBytes}, Some("text/html"))

}