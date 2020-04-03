package example.modules.konvashapes

import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Layer
import example.components.ColoredRect
import example.components.AnimatedRect
import example.components.AnimatedRectStrictMode

@react object Rect1Page {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Rect1"),
      div(className := "card-body",
        Stage(800, 600)(
          Layer(
            ColoredRect(),
            AnimatedRect(),
            AnimatedRectStrictMode(),
          )
        )
      )
    )
  }
}
