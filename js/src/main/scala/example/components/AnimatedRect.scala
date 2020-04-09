package example.components

import com.github.oen9.slinky.bridge.konva.Konva
import com.github.oen9.slinky.bridge.konva.Konva.KonvaEventObject
import com.github.oen9.slinky.bridge.reactkonva.Rect
import org.scalajs.dom.raw.MouseEvent
import scala.scalajs.js
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.core.FunctionalComponent

@react object AnimatedRect {
  type Props = Unit
  val component = FunctionalComponent[Props] { props =>
    val (color, setColor) = useState("green")

    useEffect(() => {
      val interval = js.timers.setInterval(500)(setRandomColor)
      () => js.timers.clearInterval(interval)
    })

    def handleClick(e: KonvaEventObject[MouseEvent]): Unit = setRandomColor()
    def setRandomColor(): Unit = {
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
