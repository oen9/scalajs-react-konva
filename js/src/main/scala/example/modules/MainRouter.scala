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
    import konvatypes._
    val routerSwitch = Switch(
      Route(exact = true, path = Loc.home, render = _ => KonvaShowcase()),
      Route(exact = true, path = Loc.about, render = _ => About()),
      Route(exact = true, path = Loc.rect, render = _ => RectPage()),
      Route(exact = true, path = Loc.circle, render = _ => CirclePage()),
      Route(exact = true, path = Loc.text, render = _ => TextPage()),
      Route(exact = true, path = Loc.line, render = _ => LinePage()),
      Route(exact = true, path = Loc.ellipse, render = _ => EllipsePage()),
      Route(exact = true, path = Loc.wedge, render = _ => WedgePage()),
    )
    ReactDiode.diodeContext.Provider(AppCircuit)(
      Layout(routerSwitch)
    )
  }
}

object MainRouter {
  object Loc {
    val home = "/"
    val about = "/about"
    val rect = "/rect"
    val circle = "/circle"
    val text = "/text"
    val line = "/line"
    val ellipse = "/ellipse"
    val wedge = "/wedge"
  }

  sealed trait MenuItemType
  case class RegularMenuItem(idx: String, label: String, location: String) extends MenuItemType
  case class DropDownMenuItems(idx: String, items: Seq[RegularMenuItem]) extends MenuItemType

  val menuItems = Seq(
    RegularMenuItem("0", "Konva Showcase", Loc.home),
    DropDownMenuItems("1", Seq(
      RegularMenuItem("2", "Rect", Loc.rect),
      RegularMenuItem("3", "Circle", Loc.circle),
      RegularMenuItem("4", "Text", Loc.text),
      RegularMenuItem("5", "Line", Loc.line),
      RegularMenuItem("6", "Ellipse", Loc.ellipse),
      RegularMenuItem("7", "Wedge", Loc.wedge),
    )),
    RegularMenuItem("8888", "About", Loc.about)
  )
}
