package example.modules.konvatypes

import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Text

@react object TextPage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Text"),
      div(className := "card-body",
        Stage(800, 600)(
          Layer(
            Text("Hello world!"),
            Text("Lorem ipsum ", x = 50, y = 260, fontSize = 18),
          )
        )
      )
    )
  }
}
