package example.modules.konvatypes

import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Ellipse

@react object EllipsePage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Ellipse"),
      div(className := "card-body",
        Stage(800, 600)(
          Layer(
            Ellipse(290, 150, radiusX = 50, radiusY = 100, fill = "grey", stroke = "pink", strokeWidth = 10, draggable = true),
          )
        )
      )
    )
  }
}
