package example.bridges.reactkonva

import slinky.core.ExternalComponent
import slinky.core.annotations.react

@react object Rect extends ExternalComponent {
  case class Props(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    fill: String,
    shadowBlur: Int,
    onClick: () => Unit
  )
  override val component = ReactKonvaDOM.Rect
}
