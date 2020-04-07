package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Arc
import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object ArcPage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Arc"),
      div(className := "card-body",
        Stage(width = 800, height = 600)(
          Layer(
            Arc(
              x = 100,
              y = 100,
              innerRadius = 40,
              outerRadius = 70,
              angle = 60,
              fill = "yellow",
              stroke = "black",
              strokeWidth = 4,
              draggable = true,
            ),
          )
        )
      )
    )
  }
}
