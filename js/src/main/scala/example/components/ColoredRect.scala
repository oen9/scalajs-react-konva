package example.components

import slinky.core.FunctionalComponent
import slinky.web.html._
import slinky.core.facade.Hooks._
import com.github.oen9.slinky.bridge.reactkonva.Rect
import com.github.oen9.slinky.bridge.konva.Konva
import slinky.core.annotations.react
import com.github.oen9.slinky.bridge.reactkonva.Operations
import slinky.core.facade.Fragment
import slinky.core.facade.React
import com.github.oen9.slinky.bridge.reactkonva.Text

@react object ColoredRect {
  type Props = Unit
  val component = FunctionalComponent[Props] { _ =>
    val (color, setColor) = useState("navy")
    val (intersection, setIntersection) = useState(true)
    val rec1Ref = React.createRef[Operations.RefOperations]
    val rec2Ref = React.createRef[Operations.RefOperations]

    def handleClick(): Unit = {
      val newIntersection = Konva.Util.haveIntersection(rec1Ref.current.getClientRect(), rec2Ref.current.getClientRect())
      setIntersection(newIntersection)
      setColor(Konva.Util.getRandomColor())
    }

    Fragment(
      Text("click rect below to check intersection: " + intersection),
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
