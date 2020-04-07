package example.components

import com.github.oen9.slinky.bridge.konva.Konva
import com.github.oen9.slinky.bridge.reactkonva.Operations
import com.github.oen9.slinky.bridge.reactkonva.Rect
import com.github.oen9.slinky.bridge.reactkonva.Text
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.core.facade.Hooks._
import slinky.core.facade.React
import slinky.core.FunctionalComponent

@react object ColoredRect {
  type Props = Unit
  val component = FunctionalComponent[Props] { _ =>
    val (color, setColor) = useState("navy")
    val (intersection, setIntersection) = useState(true)
    val rec1Ref = React.createRef[Operations.ShapeRef]
    val rec2Ref = React.createRef[Operations.ShapeRef]

    def handleClick(): Unit = {
      val newIntersection = Konva.Util.haveIntersection(rec1Ref.current.getClientRect(), rec2Ref.current.getClientRect())
      setIntersection(newIntersection)
      setColor(Konva.Util.getRandomColor())
    }

    Fragment(
      Text(text = s"click rect below to check intersection: $intersection"),
      Rect(
        x = 20,
        y = 20,
        width = 50,
        height = 50,
        fill = color,
        shadowBlur = 5,
        onClick = handleClick _,
      ).withRef(rec1Ref)

      ,
      Rect(
        x = 30,
        y = 30,
        width = 50,
        height = 50,
        fill = color,
        shadowBlur = 5,
        draggable = true,
      ).withRef(rec2Ref)
    )
  }
}
