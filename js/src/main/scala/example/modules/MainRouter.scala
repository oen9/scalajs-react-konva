package example.modules

import example.bridges.reactrouter.RouteProps
import example.services.AppCircuit
import example.services.ReactDiode
import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.reactrouter.Route
import slinky.reactrouter.Switch

@react object MainRouter {
  type Props = RouteProps

  val component = FunctionalComponent[Props] { _ =>
    import konvatypes._
    val routerSwitch = Switch(
      Route(exact = true, path = Loc.home, component = KonvaShowcase.component),
      Route(exact = true, path = Loc.about, component = About.component),
      Route(exact = true, path = Loc.rect, component = RectPage.component),
      Route(exact = true, path = Loc.circle, component = CirclePage.component),
      Route(exact = true, path = Loc.text, component = TextPage.component),
      Route(exact = true, path = Loc.line, component = LinePage.component),
      Route(exact = true, path = Loc.ellipse, component = EllipsePage.component),
      Route(exact = true, path = Loc.wedge, component = WedgePage.component),
    )
    ReactDiode.diodeContext.Provider(AppCircuit)(
      Layout(routerSwitch)
    )
  }

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
