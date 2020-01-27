package example.modules

import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object KonvaShowcase {
  type Props = Unit
  val component = FunctionalComponent[Props] { props =>
    div(className := "card",
      div(className := "card-header", "react-konva"),
      div(className := "card-body",
        div(
          p("This app is just ",
          a(className := "btn btn-outline-dark", href := "https://github.com/oen9/slinky-bridge-react-konva", target := "_blank", "slinky-bridge-react-konva"),
          " showcase"),
          p("Check the menu!")
        )
      )
    )
  }
}
