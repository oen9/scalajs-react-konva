package example.bridges.reactkonva

import slinky.core.ExternalComponent
import slinky.core.annotations.react

@react object Text extends ExternalComponent {
  case class Props(text: String)
  override val component = ReactKonvaDOM.Text
}
