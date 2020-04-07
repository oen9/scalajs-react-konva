package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Rect
import com.github.oen9.slinky.bridge.reactkonva.Stage
import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object Rect2Page {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Rect2"),
      div(className := "card-body",
        Stage(width = 800, height = 600)(
          Layer(
            Rect(
              x = 20,
              y = 20,
              width = 100,
              height = 50,
              fill = "green",
              stroke = "black",
              strokeWidth = 4,
              draggable = true
            ),

            Rect(
              x = 150,
              y = 40,
              width = 100,
              height = 50,
              fill = "red",
              shadowBlur = 10,
              cornerRadius = 10,
              draggable = true
            ),

            Rect(
              x = 50,
              y = 120,
              width = 100,
              height = 100,
              fill = "blue",
              cornerRadius = List(0, 10, 20, 30),
              draggable = true
            ),
          )
        )
      )
    )
  }
}
