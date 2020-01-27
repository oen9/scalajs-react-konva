package example.modules.konvatypes

import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Layer
import example.components.ColoredRect
import example.components.AnimatedRect
import example.components.AnimatedRectStrictMode

@react object RectPage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Rect"),
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
