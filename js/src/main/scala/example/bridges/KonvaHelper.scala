package example.bridges

import com.github.oen9.slinky.bridge.konva.Konva.Util.ClientRect
import scalajs.js

object KonvaHelper {
  def createClientRect(): ClientRect = createClientRect(0, 0, 0, 0)
  def createClientRect(x: Double, y: Double, width: Double, height: Double): ClientRect = js.Dynamic.literal(
    x = x,
    y = y,
    width = width,
    height = height,
  ).asInstanceOf[ClientRect]
}
