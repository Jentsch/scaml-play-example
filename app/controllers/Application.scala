package controllers

import java.util.Currency

import assets.Item
import org.scaml.Builder
import org.scaml.generators.{HTML => HtmlGenrator}
import play.api.http.Writeable
import play.api.mvc._
import views._

object Application extends Controller {

  def index = Action {
    Ok(Index)
  }

  def add = Action {
    Ok(Add)
  }
  
  def show(id: Int) = Action {
    Item.get(id).map {
      item =>
        Ok(new Show(item))
    }.getOrElse {
      NotFound("Not found")
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

  implicit val ScamlWriteable = Writeable[Builder]({(doc: Builder) => HtmlGenrator(doc).toString().getBytes}, Some("text/html"))

}

