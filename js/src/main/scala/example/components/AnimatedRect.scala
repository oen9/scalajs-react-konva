package example.components

import slinky.core.FunctionalComponent
import slinky.web.html._
import slinky.core.facade.Hooks._
import com.github.oen9.slinky.bridge.reactkonva.Rect
import com.github.oen9.slinky.bridge.konva.Konva
import slinky.core.annotations.react
import scala.scalajs.js

@react object AnimatedRect {
  type Props = Unit
  val component = FunctionalComponent[Props] { props =>
    val (color, setColor) = useState("green")

    useEffect(() => {
      val interval = js.timers.setInterval(500)(handleClick)
      () => js.timers.clearInterval(interval)
    })

    def handleClick() = {
      setColor(Konva.Util.getRandomColor())
    }

    Rect(
      x = 120,
      y = 120,
      width = 50,
      height = 50,
      fill = color,
      shadowBlur = 5,
      onClick = handleClick _
    )
  }
}
