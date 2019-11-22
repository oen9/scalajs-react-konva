package example.modules

import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._
import example.bridges.reactkonva.Stage
import example.bridges.reactkonva.Layer
import example.bridges.reactkonva.Text
import example.components.ColoredRect

@react object KonvaShowcase {
  type Props = Unit
  val component = FunctionalComponent[Props] { props =>
    div(className := "card",
      div(className := "card-header", "react-konva"),
      div(className := "card-body",
        Stage(800, 600)(
          Layer(
            Text("Hello world!"),
            ColoredRect()
          )
        )
      )
    )
  }
}
