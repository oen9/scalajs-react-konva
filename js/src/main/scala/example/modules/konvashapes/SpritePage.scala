package example.modules.konvashapes

import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Operations
import com.github.oen9.slinky.bridge.reactkonva.Sprite
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.useimage.UseImage._
import scalajs.js
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.core.facade.React
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object SpritePage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    val (image, _) = useImage("front-res/img/blob-sprite.png")
    val spriteRef = React.createRef[Operations.SpriteRef]

    useLayoutEffect(
      () => spriteRef.current.start(),
      Seq()
    )

    val animations2 = js.Dynamic.literal(
      idle = js.Array(
        2, 2, 70, 119,
        71, 2, 74, 119,
        146, 2, 81, 119,
        226, 2, 76, 119
      ),
      punch = js.Array(
        2, 138, 74, 122,
        76, 138, 84, 122,
        346, 138, 120, 122,
      )
    )

    def punch(): Unit = {
      val blob = spriteRef.current
      blob.animation("punch")
      blob.on("frameIndexChange.button", () => {
        if (blob.frameIndex() == 2) {
          js.timers.setTimeout(1000 / blob.frameRate()) {
            blob.animation("idle")
            blob.off(".button")
          }
        }
      })
    }

    div(className := "card",
      div(className := "card-header", "Sprite"),
      div(className := "card-body",
        button(className := "btn btn-primary", "punch", onClick := punch _),
        Stage(800, 600)(
          Layer(
            Sprite(
              x = 50,
              y = 50,
              image = image,
              animations = animations2,
              animation = "idle",
              frameRate = 7,
              frameIndex = 0,
              onClick = punch _,
              draggable = true,
            ).withRef(spriteRef),
          )
        )
      )
    )
  }
}
