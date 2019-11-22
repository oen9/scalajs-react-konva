package example.components

import slinky.core.FunctionalComponent
import slinky.web.html._
import slinky.core.facade.Hooks._
import example.bridges.reactkonva.Rect
import slinky.core.annotations.react
import example.bridges.reactrouter.Konva

@react object ColoredRect {
  type Props = Unit
  val component = FunctionalComponent[Props] { props =>
    val (color, setColor) = useState("green")

    def handleClick() = {
      setColor(Konva.Util.getRandomColor())
    }

    Rect(
      x = 20,
      y = 20,
      width = 50,
      height = 50,
      fill = color,
      shadowBlur = 5,
      onClick = handleClick
    )
  }
}
