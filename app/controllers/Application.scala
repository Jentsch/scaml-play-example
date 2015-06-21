package controllers

import assets.{Duration, Task}
import org.scaml.{HTML => HtmlGenrator, Node}
import play.api.http.Writeable
import play.api.mvc._
import views._

import scala.util.Try

object Application extends Controller {

  implicit val ScamlWriteable =
    Writeable({ (doc: Node) => HtmlGenrator(doc).toString().getBytes }, Some("text/html"))

  def index() = Action {
    Ok(Index.render)
  }

  def add() = Action {
    Ok(Add.render)
  }

  def done() = Action {
    Ok(Done.render)
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
        Ok(Add.render)
    }
  }

  def show(id: Int) = Action {
    Task.get(id).map { task =>
      Ok(Show.render(task))
    }.getOrElse {
      NotFound("No with ID " + id)
    }
  }

  def toggle(id: Int) = Action {req =>
    Task.get(id) match {
      case Some(task) =>
        Task += task.copy(done = !task.done)
        Ok(Show.render(task))
      case None =>
        NotFound("No task with ID " + id)
    }
  }

}
