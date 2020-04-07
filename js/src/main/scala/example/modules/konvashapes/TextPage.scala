package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Operations
import com.github.oen9.slinky.bridge.reactkonva.Rect
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Text
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.core.facade.React
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object TextPage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    val (complexTextHeight, setComplexTextHeight) = useState(0)
    val complexTextRef = React.createRef[Operations.ShapeRef]

    useLayoutEffect(
      () => setComplexTextHeight(complexTextRef.current.height()),
      Seq()
    )

    div(className := "card",
      div(className := "card-header", "Text"),
      div(className := "card-body",
        Stage(width = 800, height = 600)(
          Layer(
            Text(
              x = 200,
              y = 100,
              text = "Simple Text",
              fontSize = 30,
              fontFamily = "Calibri",
              fill = "green"
            ),
            Rect(
              x = 120,
              y = 145,
              stroke = "#555",
              strokeWidth = 5,
              fill = "#ddd",
              width = 300,
              height = complexTextHeight,
              shadowColor = "black",
              shadowBlur = 10,
              shadowOffsetX = 10,
              shadowOffsetY = 10,
              shadowOpacity = 0.2,
              cornerRadius = 10
            ),
            Text(
              x = 120,
              y = 145,
              text = "COMPLEX TEXT\n\nAll the world's a stage, and all the men and women merely players. They have their exits and their entrances.",
              fontSize = 18,
              fontFamily = "Calibri",
              fill = "#555",
              width = 300,
              padding = 20,
              align = "center"
            ).withRef(complexTextRef)
          )
        )
      )
    )
  }
}
