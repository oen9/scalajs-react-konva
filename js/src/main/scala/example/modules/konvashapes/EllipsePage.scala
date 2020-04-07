package example.modules.konvashapes

import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Ellipse

@react object EllipsePage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Ellipse"),
      div(className := "card-body",
        Stage(width = 800, height = 600)(
          Layer(
            Ellipse(
              x = 290,
              y = 150,
              radiusX = 100,
              radiusY = 50,
              fill = "yellow",
              stroke = "black",
              strokeWidth = 4,
              draggable = true
            )
          )
        )
      )
    )
  }
}
