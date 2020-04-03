package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Label
import com.github.oen9.slinky.bridge.reactkonva.Tag
import com.github.oen9.slinky.bridge.reactkonva.Text
import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object LabelPage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    div(className := "card",
      div(className := "card-header", "Label"),
      div(className := "card-body",
        Stage(800, 600)(
          Layer(
            Label(x = 170, y = 75, opacity = 0.75, draggable = true) (
              Tag(
                fill = "black",
                pointerDirection = "down",
                pointerWidth = 10,
                pointerHeight = 10,
                lineJoin = "round",
                shadowColor = "black",
                shadowBlur = 10,
                shadowOffsetX = 10,
                shadowOffsetY = 10,
                shadowOpacity = 0.5,
              ),
              Text(
                text = "Tooltip pointing down",
                fontFamily = "Calibri",
                fontSize = 18,
                padding = 5,
                fill = "white",
              )
            ),
            Label(x = 20, y = 130, opacity = 0.75, draggable = true) (
              Tag(
                fill = "green",
                pointerDirection = "left",
                pointerWidth = 20,
                pointerHeight = 28,
                lineJoin = "round"
              ),
              Text(
                text = "Label pointing left",
                fontFamily = "Calibri",
                fontSize = 18,
                padding = 5,
                fill = "white",
              )
            ),
            Label(x = 180, y = 150, opacity = 0.75, draggable = true) (
              Tag(fill = "yellow"),
              Text(
                text = "Simple label",
                fontFamily = "Calibri",
                fontSize = 18,
                padding = 5,
                fill = "black",
              )
            ),
          )
        )
      )
    )
  }
}
