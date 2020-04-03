package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Arrow
import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object ArrowPage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Arrow"),
      div(className := "card-body",
        Stage(800, 600)(
          Layer(
            Arrow(
              x = 100,
              y = 50,
              points = Seq(0, 0, 300, 100),
              pointerLength = 20,
              pointerWidth = 20,
              fill = "black",
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
