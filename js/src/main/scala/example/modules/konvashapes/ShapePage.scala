package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Operations.ShapeRef
import com.github.oen9.slinky.bridge.reactkonva.ReactKonvaDOM.Context
import com.github.oen9.slinky.bridge.reactkonva.Shape
import com.github.oen9.slinky.bridge.reactkonva.Stage
import slinky.core.annotations.react
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object ShapePage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    def paintShape(context: Context, shape: ShapeRef): Unit = {
      context.beginPath();
      context.moveTo(20, 50);
      context.lineTo(220, 80);
      context.quadraticCurveTo(150, 100, 260, 170);
      context.closePath();

      context.fillStrokeShape(shape);
    }

    div(className := "card",
      div(className := "card-header", "Shape"),
      div(className := "card-body",
        Stage(width = 800, height = 600)(
          Layer(
            Shape(
              x = 100,
              y = 50,
              fill = "#00D2FF",
              stroke = "black",
              strokeWidth = 4,
              sceneFunc = paintShape _,
              draggable = true,
            ),
          )
        )
      )
    )
  }
}
