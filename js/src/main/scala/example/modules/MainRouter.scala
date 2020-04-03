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
    import konvashapes._
    val routerSwitch = Switch(
      Route(exact = true, path = Loc.home, component = KonvaShowcase.component),
      Route(exact = true, path = Loc.about, component = About.component),
      Route(exact = true, path = Loc.rect, component = Rect1Page.component),
      Route(exact = true, path = Loc.rect2, component = Rect2Page.component),
      Route(exact = true, path = Loc.circle, component = CirclePage.component),
      Route(exact = true, path = Loc.ellipse, component = EllipsePage.component),
      Route(exact = true, path = Loc.wedge, component = WedgePage.component),
      Route(exact = true, path = Loc.line, component = LinePage.component),
      Route(exact = true, path = Loc.linePolygon, component = LinePolygonPage.component),
      Route(exact = true, path = Loc.lineSimple, component = LineSimplePage.component),
      Route(exact = true, path = Loc.lineSpline, component = LineSplinePage.component),
      Route(exact = true, path = Loc.lineBlob, component = LineBlobPage.component),
      Route(exact = true, path = Loc.image, component = ImagePage.component),
      Route(exact = true, path = Loc.text, component = TextPage.component),
      Route(exact = true, path = Loc.textPath, component = TextPathPage.component),
      Route(exact = true, path = Loc.star, component = StarPage.component),
      Route(exact = true, path = Loc.ring, component = RingPage.component),
      Route(exact = true, path = Loc.arc, component = ArcPage.component),
      Route(exact = true, path = Loc.label, component = LabelPage.component),
      Route(exact = true, path = Loc.path, component = PathPage.component),
      Route(exact = true, path = Loc.regularPolygon, component = RegularPolygonPage.component),
      Route(exact = true, path = Loc.arrow, component = ArrowPage.component),
      Route(exact = true, path = Loc.shape, component = ShapePage.component),
      Route(exact = true, path = Loc.sprite, component = SpritePage.component),
    )
    ReactDiode.diodeContext.Provider(AppCircuit)(
      Layout(routerSwitch)
    )
  }

  object Loc {
    val home = "/"
    val about = "/about"
    val rect = "/rect"
    val rect2 = "/rect2"
    val circle = "/circle"
    val ellipse = "/ellipse"
    val wedge = "/wedge"
    val line = "/line"
    val lineSimple = "/line-simple"
    val linePolygon = "/line-polygon"
    val lineSpline = "/line-spline"
    val lineBlob = "/line-blob"
    val sprite = "/sprite"
    val image = "/image"
    val text = "/text"
    val textPath = "/text-path"
    val star = "/star"
    val ring = "/ring"
    val arc = "/arc"
    val label = "/label"
    val path = "/path"
    val regularPolygon = "/regularPolygon"
    val arrow = "/arrow"
    val shape = "/shape"
  }

  sealed trait MenuItemType
  case class RegularMenuItem(idx: String, label: String, location: String) extends MenuItemType
  case class DropDownMenuItems(idx: String, items: Seq[RegularMenuItem]) extends MenuItemType

  val menuItems = Seq(
    RegularMenuItem("0", "Konva Showcase", Loc.home),
    DropDownMenuItems("1", Seq(
      RegularMenuItem("2", "Rect", Loc.rect),
      RegularMenuItem("3", "Rect2", Loc.rect2),
      RegularMenuItem("4", "Circle", Loc.circle),
      RegularMenuItem("5", "Ellipse", Loc.ellipse),
      RegularMenuItem("6", "Wedge", Loc.wedge),
      RegularMenuItem("7", "Line", Loc.line),
      RegularMenuItem("8", "Line - Simple Line", Loc.lineSimple),
      RegularMenuItem("9", "Line - Polygon", Loc.linePolygon),
      RegularMenuItem("10", "Line - Spline", Loc.lineSpline),
      RegularMenuItem("11", "Line - Blob", Loc.lineBlob),
      RegularMenuItem("12", "Sprite", Loc.sprite),
      RegularMenuItem("13", "Image", Loc.image),
      RegularMenuItem("14", "Text", Loc.text),
      RegularMenuItem("15", "TextPath", Loc.textPath),
      RegularMenuItem("16", "Star", Loc.star),
      RegularMenuItem("17", "Ring", Loc.ring),
      RegularMenuItem("18", "Arc", Loc.arc),
      RegularMenuItem("19", "Label", Loc.label),
      RegularMenuItem("20", "Path", Loc.path),
      RegularMenuItem("21", "RegularPolygon", Loc.regularPolygon),
      RegularMenuItem("22", "Arrow", Loc.arrow),
      RegularMenuItem("23", "Shape", Loc.shape),
    )),
    RegularMenuItem("8888", "About", Loc.about)
  )
}
