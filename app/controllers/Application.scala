package controllers

import assets.{Duration, Task}
import org.scaml.{HTML => HtmlGenrator}
import play.api.http.Writeable
import play.api.mvc._
import views._

import scala.util.Try

object Application extends Controller {

  implicit val ScamlWriteable =
    Writeable({ (doc: Template) => HtmlGenrator(doc.document).toString().getBytes }, Some("text/html"))

  def index = Action {
    Ok(new Index)
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

  def toggle(id: Int) = Action {req =>
    Task.get(id) match {
      case Some(task) =>
        Task += task.copy(isResolved = !task.isResolved)
        Ok(new Show(task))
      case None =>
        NotFound("No task with ID " + id)
    }
  }

}
