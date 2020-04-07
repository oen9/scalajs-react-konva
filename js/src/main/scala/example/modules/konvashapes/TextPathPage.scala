package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.TextPath
import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object TextPathPage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "TextPath"),
      div(className := "card-body",
        Stage(width = 800, height = 600)(
          Layer(
            TextPath(
              x = 0,
              y = 50,
              fill = "#333",
              fontSize = 16,
              fontFamily = "Arial",
              text = "All the world's a stage, and all the men and women merely players.",
              data = "M10,10 C0,0 10,150 100,100 S300,150 500,100",
            ),
          )
        )
      )
    )
  }
}
