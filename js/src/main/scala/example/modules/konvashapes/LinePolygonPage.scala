package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Line
import com.github.oen9.slinky.bridge.reactkonva.Stage
import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object LinePolygonPage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Line - polygon"),
      div(className := "card-body",
        Stage(width = 800, height = 600)(
          Layer(
            Line(
              points = IndexedSeq(23, 20, 23, 160, 70, 93, 150, 109, 290, 139, 270, 93),
              fill = "#00D2FF",
              stroke = "black",
              strokeWidth = 5,
              closed = true,
              draggable = true
            ),
          )
        )
      )
    )
  }
}
