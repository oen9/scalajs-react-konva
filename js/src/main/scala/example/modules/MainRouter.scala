package example.modules

import com.lambdaminute.slinkywrappers.reactrouter.RouteProps
import example.services.ReactDiode
import example.services.AppCircuit
import slinky.core.facade.ReactElement
import slinky.core.annotations.react
import slinky.core.StatelessComponent
import example.bridges.reactrouter.Switch
import com.lambdaminute.slinkywrappers.reactrouter.Route
import example.modules.MainRouter.Loc

@react class MainRouter extends StatelessComponent {
  type Props = RouteProps

  override def componentDidMount(): Unit = {
    super.componentDidMount()
    println("router mounted")
  }

  def render(): ReactElement = {
    val routerSwitch = Switch(
      Route(exact = true, path = Loc.home, render = _ => KonvaShowcase()),
      Route(exact = true, path = Loc.about, render = _ => About()),
    )
    ReactDiode.diodeContext.Provider(AppCircuit)(
      Layout(routerSwitch)
    )
  }
}

object MainRouter {
  case class MenuItem(idx: String, label: String, location: String)
  object Loc {
    val home = "/"
    val about = "/about"
  }
  val menuItems = Seq(
    MenuItem("0", "Konva Showcase", Loc.home),
    MenuItem("1", "About", Loc.about)
  )
}
