package example.modules.konvatypes

import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Wedge

@react object WedgePage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Wedge"),
      div(className := "card-body",
        Stage(800, 600)(
          Layer(
            Wedge(400, 150, radius = 100, angle = 60, fill = "yellow", draggable = true)
          )
        )
      )
    )
  }
}
