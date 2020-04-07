package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.RegularPolygon
import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object RegularPolygonPage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "RegularPolygon"),
      div(className := "card-body",
        Stage(width = 800, height = 600)(
          Layer(
            RegularPolygon(
              x = 100,
              y = 150,
              sides = 6,
              radius = 70,
              fill = "red",
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
