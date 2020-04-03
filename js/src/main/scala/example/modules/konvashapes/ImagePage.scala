package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.useimage.UseImage._
import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._
import com.github.oen9.slinky.bridge.reactkonva.Image

@react object ImagePage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    val (yodaImg, _) = useImage("front-res/img/yoda.jpg")
    val (darthImg, _) = useImage("front-res/img/darth-vader.jpg")

    div(className := "card",
      div(className := "card-header", "Image"),
      div(className := "card-body",
        Stage(800, 600)(
          Layer(
            Image(
              x = 50,
              y = 50,
              image = yodaImg,
              width = 106,
              height = 118,
              draggable = true
            ),
            Image(
              x = 200,
              y = 50,
              image = darthImg,
              scaleX = 0.5,
              scaleY = 0.5,
              draggable = true
            ),
          )
        )
      )
    )
  }
}
