package example.modules.konvashapes

import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Circle

@react object CirclePage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Circle"),
      div(className := "card-body",
        Stage(800, 600)(
          Layer(
            Circle(
              x = 290,
              y = 290,
              radius = 70,
              fill = "red",
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
