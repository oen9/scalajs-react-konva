package example

import example.bridges.reactrouter.HashRouter
import example.bridges.reactrouter.HashRouter
import example.bridges.reactrouter.ReactRouterDOM.withRouter
import example.modules.MainRouter
import example.modules.MainRouter
import org.scalajs.dom.document
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.LinkingInfo
import slinky.web.ReactDOM

object ScalaJSExample {

  @JSImport("bootstrap", JSImport.Default)
  @js.native
  object Bootstrap extends js.Object

  def main(args: Array[String]): Unit = {
    val target = document.getElementById("main")

    Bootstrap

    if (LinkingInfo.developmentMode) {
      println("dev mode")
    }

    ReactDOM.render(HashRouter(withRouter(MainRouter.component)(js.Object)), target)
  }
}
