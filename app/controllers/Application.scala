package controllers

import assets.{Duration, Task}
import org.scaml.{Builder, HTML => HtmlGenrator}
import play.api.http.Writeable
import play.api.mvc._
import views._

import scala.util.Try

object Application extends Controller {

  implicit val ScamlWriteable =
    Writeable[Builder]({ (doc: Builder) => HtmlGenrator(doc).toString().getBytes }, Some("text/html"))

  def index = Action {
    Ok(Index)
  }

  def add = Action {
    Ok(Add)
  }

  def create = Action { req =>
    val result = for (
      request <- req.body.asFormUrlEncoded;
      name <- request.getOrElse("name", Nil).headOption;
      durationString <- request.getOrElse("duration", Nil).headOption;
      duration <- Try(Duration(durationString.toInt)).toOption
    ) yield Task(name, duration)

    result match {
      case Some(task) =>
        val id = Task += task
        Redirect(routes.Application.show(id))
      case None =>
        Ok(Add)
    }
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

  def resolved() = Action {
    Ok(new TaskList("Resolved tasks", Task.filter(_.isResolved).to[List]))
  }

}
