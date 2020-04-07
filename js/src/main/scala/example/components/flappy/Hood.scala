package example.components.flappy

import com.github.oen9.slinky.bridge.reactkonva.Group
import com.github.oen9.slinky.bridge.reactkonva.Text
import slinky.core.annotations.react
import slinky.core.FunctionalComponent

@react object Hood {
  case class Props(fps: Double, score: Int)

  val component = FunctionalComponent[Props] { props =>
    Group(
      Text(
        text = s"fps: ${props.fps}",
        x = 20,
        y = 20,
        fontSize = 14,
      ),
      Text(
        text = s"score: ${props.score}",
        x = 20,
        y = 40,
        fontSize = 14,
      ),
    )
  }
}
