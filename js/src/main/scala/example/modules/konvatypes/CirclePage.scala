package example.modules.konvatypes

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
            Circle(290, 290, radius = 50, stroke = "blue", strokeWidth = 15, draggable = true)
          )
        )
      )
    )
  }
}
