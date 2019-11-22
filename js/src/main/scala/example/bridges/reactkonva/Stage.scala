package example.bridges.reactkonva

import slinky.core.ExternalComponent
import slinky.core.annotations.react

@react object Stage extends ExternalComponent {
  case class Props(width: Int, height: Int)
  override val component = ReactKonvaDOM.Stage
}
