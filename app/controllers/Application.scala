package controllers

import assets.Task
import org.scaml.{Builder, HTML => HtmlGenrator}
import play.api.http.Writeable
import play.api.mvc._
import views._

object Application extends Controller {

  implicit val ScamlWriteable =
    Writeable[Builder]({ (doc: Builder) => HtmlGenrator(doc).toString().getBytes }, Some("text/html"))

  def index = Action {
    Ok(Index)
  }

  def add = Action {
    Ok(Add)
  }

  def show(id: Int) = Action {
    Task.get(id).map { task =>
      Ok(new Show(task))
    }.getOrElse {
      NotFound("No with ID " + id)
    }
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

}
