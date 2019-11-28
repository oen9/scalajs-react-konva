package example.modules

import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._
import example.components.ColoredRect
import example.components.AnimatedRect
import example.components.AnimatedRectStrictMode
import com.github.oen9.slinky.bridge.reactkonva._

@react object KonvaShowcase {
  type Props = Unit
  val component = FunctionalComponent[Props] { props =>
    div(className := "card",
      div(className := "card-header", "react-konva"),
      div(className := "card-body",
        Stage(800, 600)(
          Layer(
            Text("Hello world!"),
            ColoredRect(),
            AnimatedRect(),
            Text("try drag and drop --> ", x = 50, y = 260, fontSize = 18),
            AnimatedRectStrictMode(),
            Line(IndexedSeq(300, 300, 350, 350, 400, 300), stroke = "blue", tension = 0.5, draggable = true),
            Line(IndexedSeq(5, 70, 140, 23, 250, 60, 300, 20), stroke = "magenta", tension = 1, x = 190, y = 190, strokeWidth = 20, draggable = true),
            Circle(290, 290, radius = 50, stroke = "blue", strokeWidth = 15, draggable = true),
            Ellipse(290, 150, radiusX = 50, radiusY = 100, fill = "grey", stroke = "pink", strokeWidth = 10, draggable = true),
            Wedge(400, 150, radius = 100, angle = 60, fill = "yellow", draggable = true)
          )
        )
      )
    )
  }
}
