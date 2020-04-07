package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Ring
import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object RingPage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Ring"),
      div(className := "card-body",
        Stage(width = 800, height = 600)(
          Layer(
            Ring(
              x = 100,
              y = 100,
              innerRadius = 40,
              outerRadius = 70,
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
