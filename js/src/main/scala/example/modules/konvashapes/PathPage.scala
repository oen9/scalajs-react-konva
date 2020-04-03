package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Path
import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object PathPage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Path"),
      div(className := "card-body",
        Stage(800, 600)(
          Layer(
            Path(
              x = 50,
              y = 40,
              data = "M12.582,9.551C3.251,16.237,0.921,29.021,7.08,38.564l-2.36,1.689l4.893,2.262l4.893,2.262l-0.568-5.36l-0.567-5.359l-2.365,1.694c-4.657-7.375-2.83-17.185,4.352-22.33c7.4.2.2.338,17.817-3.625,23.156,3.824c5.337,7.449,3.625,17.813-3.821,23.152l2.857,3.988c9.617-6.893,11.827-20.277,4.935-29.896C35.591,4.87,22.204,2.658,12.582,9.551z",
              fill = "green",
              scaleX = 2,
              scaleY = 2,
            ),
          )
        )
      )
    )
  }
}
