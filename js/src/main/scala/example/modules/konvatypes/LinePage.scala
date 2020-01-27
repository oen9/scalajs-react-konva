package example.modules.konvatypes

import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Line

@react object LinePage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Line"),
      div(className := "card-body",
        Stage(800, 600)(
          Layer(
            Line(IndexedSeq(300, 300, 350, 350, 400, 300), stroke = "blue", tension = 0.5, draggable = true),
            Line(IndexedSeq(5, 70, 140, 23, 250, 60, 300, 20), stroke = "magenta", tension = 1, x = 190, y = 190, strokeWidth = 20, draggable = true),
          )
        )
      )
    )
  }
}
