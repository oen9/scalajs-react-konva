package example.components

import slinky.core.FunctionalComponent
import slinky.web.html._
import slinky.core.facade.Hooks._
import com.github.oen9.slinky.bridge.reactkonva.Rect
import slinky.core.annotations.react
import example.bridges.reactrouter.Konva

@react object AnimatedRectStrictMode {
  type Props = Unit
  val component = FunctionalComponent[Props] { props =>
    val (color, setColor) = useState("green")

    Rect(
      x = 240,
      y = 240,
      width = 50,
      height = 50,
      fill = color,
      shadowBlur = 5,
      draggable = true,
      onDragEnd = {() => {
        setColor(Konva.Util.getRandomColor());
      }}
    )
  }
}
