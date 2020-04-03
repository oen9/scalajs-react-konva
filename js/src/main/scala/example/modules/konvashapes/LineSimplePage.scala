package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Line
import com.github.oen9.slinky.bridge.reactkonva.Operations
import com.github.oen9.slinky.bridge.reactkonva.Stage
import slinky.core.annotations.react
import slinky.core.facade.React
import slinky.core.FunctionalComponent
import slinky.web.html._
import slinky.core.facade.Hooks._

@react object LineSimplePage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    val line1Ref = React.createRef[Operations.Ref]
    val line2Ref = React.createRef[Operations.Ref]
    val line3Ref = React.createRef[Operations.Ref]

    useLayoutEffect (
      () => {
        line1Ref.current.move(Operations.Point(0, 5))
        line2Ref.current.move(Operations.Point(0, 55))
        line3Ref.current.move(Operations.Point(0, 105))
      },
      Seq()
    )

    div(className := "card",
      div(className := "card-header", "Line - simple"),
      div(className := "card-body",
        Stage(800, 600)(
          Layer(
            Line(
              points = IndexedSeq(5, 70, 140, 23, 250, 60, 300, 20),
              stroke = "red",
              strokeWidth = 15,
              lineCap = "round",
              lineJoin = "round",
              draggable = true
            ).withRef(line1Ref),

            Line(
              points = IndexedSeq(5, 70, 140, 23, 250, 60, 300, 20),
              stroke = "green",
              strokeWidth = 2,
              lineJoin = "round",
              dash = List(33, 10),
              draggable = true
            ).withRef(line2Ref),

            Line(
              points = IndexedSeq(5, 70, 140, 23, 250, 60, 300, 20),
              stroke = "blue",
              strokeWidth = 10,
              lineCap = "round",
              lineJoin = "round",
              dash = List(29, 20, 0.001, 20),
              draggable = true
            ).withRef(line3Ref),
          )
        )
      )
    )
  }
}
