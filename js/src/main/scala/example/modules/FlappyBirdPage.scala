package example.modules.konvashapes

import cats.implicits._
import com.github.oen9.slinky.bridge.konva.Konva
import com.github.oen9.slinky.bridge.konva.Konva.Animation
import com.github.oen9.slinky.bridge.reactkonva.Group
import com.github.oen9.slinky.bridge.reactkonva.Image
import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Operations
import com.github.oen9.slinky.bridge.reactkonva.Sprite
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.reactkonva.Text
import com.github.oen9.slinky.bridge.useimage.UseImage._
import com.softwaremill.quicklens._
import example.services.flappy.GameLogic
import scalajs.js
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.core.facade.React
import slinky.core.FunctionalComponent
import slinky.core.TagElement
import slinky.core.TagMod
import slinky.web.html._

@react object FlappyBirdPage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    val (birdImg, _) = useImage("front-res/img/flappy/bird.png")
    val (background, _) = useImage("front-res/img/flappy/background.png")
    val (groundImg, _) = useImage("front-res/img/flappy/ground.png")
    val (pipeImg, _) = useImage("front-res/img/flappy/pipe.png")
    val (restartImg, _) = useImage("front-res/img/flappy/restart.png")
    val (scoreImg, _) = useImage("front-res/img/flappy/score.png")

    val (gs, setGs) = useState(GameLogic.GameState())
    val (bestScore, setBestScore) = useState(0)
    val (anim, setAnim) = useState(none[Animation])

    val birdRef = React.createRef[Operations.SpriteRef]
    val layoutRef = React.createRef[Operations.Ref]
    val upperPipe1 = React.createRef[Operations.Ref]
    val lowerPipe1 = React.createRef[Operations.Ref]
    val upperPipe2 = React.createRef[Operations.Ref]
    val lowerPipe2 = React.createRef[Operations.Ref]

    val animations = js.Dynamic.literal(
      idle = js.Array(
        0, 0, 92, 64,
        92, 0, 92, 64,
        184, 0, 92, 64,
      )
    )

    def checkCollision(): Boolean = { // do not use it inside timer
      val birdRect = birdRef.current.getClientRect
      val upper1Rect = upperPipe1.current.getClientRect
      val lower1Rect = lowerPipe1.current.getClientRect
      val upper2Rect = upperPipe2.current.getClientRect
      val lower2Rect = lowerPipe2.current.getClientRect

      import Konva.Util.haveIntersection

      haveIntersection(birdRect, upper1Rect) ||
      haveIntersection(birdRect, lower1Rect) ||
      haveIntersection(birdRect, upper2Rect) ||
      haveIntersection(birdRect, lower2Rect) ||
      gs.bird.y > (GameLogic.groundY - GameLogic.birdWidth)
    }

    useLayoutEffect(
      () => birdRef.current.start(),
      Seq()
    )

    useLayoutEffect(() => {
      val anim = new Animation(frame => {
        setGs(gs => {
          if (!gs.gameOver) GameLogic.loop(frame, gs)
          else gs
        })
      }, Seq(layoutRef.current))
      setAnim(anim.some)
      () => anim.stop()
    }, Seq())

    useLayoutEffect(() => {
      if (!gs.gameOver) {
        if (checkCollision) {
          setGs(gs => gs.modify(_.gameOver).setTo(true))
          anim match {
            case Some(value) => value.stop()
            case None =>
          }
          if (gs.score > bestScore) setBestScore(gs.score)
        }
        birdRef.current.rotation(gs.bird.angle)
      }
    }, Seq(gs))

    useLayoutEffect(() => {
      upperPipe1.current.rotation(180)
      upperPipe2.current.rotation(180)
    }, Seq())

    def handleClick(): Unit = {
      gs.gameOver match {
        case false =>
          anim match {
            case Some(value) =>
              if (!value.isRunning()) value.start()
            case None =>
          }
          setGs(currGs => currGs.modify(_.bird.upAcceleration).setTo(GameLogic.upStep))
        case true =>
      }
    }

    def handleClickRestart(): Unit = {
      anim match {
        case Some(value) =>
          if (!value.isRunning()) value.start()
        case None =>
      }
      setGs(GameLogic.GameState())
    }

    div(className := "card",
      div(className := "card-header", "Flappy Bird"),
      div(className := "card-body text-center row justify-content-center",
        Stage(GameLogic.width, GameLogic.height,
            onClick = handleClick _,
            onTap = handleClick _
          )(
          Layer(
            Image(
              image = background,
              width = GameLogic.width,
              height = GameLogic.height,
            ),
            Sprite(
              x = 50,
              y = gs.bird.y,
              width = 64,
              height = 32,
              image = birdImg,
              animations = animations,
              animation = "idle",
              frameRate = 8,
              frameIndex = 0,
              offsetX = 46,
              offsetY = 32,
              scaleX = 0.5,
              scaleY = 0.5,
            ).withRef(birdRef),
            Image(
              image = pipeImg,
              x = gs.pipe1.x,
              y = gs.pipe1.y,
              scaleX = 0.5,
              scaleY = 0.5,
              offsetX = GameLogic.pipeWidth * 2,
            ).withRef(upperPipe1),
            Image(
              image = pipeImg,
              x = gs.pipe1.x,
              y = gs.pipe1.y + GameLogic.holeSize,
              scaleX = 0.5,
              scaleY = 0.5,
            ).withRef(lowerPipe1),
            Image(
              image = pipeImg,
              x = gs.pipe2.x,
              y = gs.pipe2.y,
              scaleX = 0.5,
              scaleY = 0.5,
              offsetX = GameLogic.pipeWidth * 2,
            ).withRef(upperPipe2),
            Image(
              image = pipeImg,
              x = gs.pipe2.x,
              y = gs.pipe2.y + GameLogic.holeSize,
              scaleX = 0.5,
              scaleY = 0.5,
            ).withRef(lowerPipe2),
            (0 to 28).map { i =>
              Image(
                image = groundImg,
                x = i * GameLogic.groundWidth + gs.groundShift,
                y = GameLogic.groundY,
                scaleX = 0.5,
                scaleY = 0.5,
              ).withKey(i.toString())
            },
            Text(
              text = s"fps: ${gs.fps}",
              x = 20,
              y = 20,
              fontSize = 14,
            ),
            Text(
              text = s"score: ${gs.score}",
              x = 20,
              y = 40,
              fontSize = 14,
            ),
            if (gs.gameOver) {
              Group(
                Image(
                  image = scoreImg,
                  x = GameLogic.width / 2 - 43,
                  y = GameLogic.height / 2 - 100,
                  scaleX = 0.5,
                  scaleY = 0.5,
                ),
                Image(
                  image = restartImg,
                  x = GameLogic.width / 2 - 54,
                  y = GameLogic.height / 2 + 50,
                  scaleX = 0.5,
                  scaleY = 0.5,
                  onClick = handleClickRestart _,
                  onTap = handleClickRestart _,
                ),
                Text(
                  x = GameLogic.width / 2 - 43,
                  y = GameLogic.height / 2 - 125,
                  width = 86,
                  height = 144,
                  align = "center",
                  verticalAlign = "middle",
                  text = s"${gs.score}",
                  fontSize = 24,
                  fill = "black",
                ),
                Text(
                  x = GameLogic.width / 2 - 43,
                  y = GameLogic.height / 2 - 80,
                  width = 86,
                  height = 144,
                  align = "center",
                  verticalAlign = "middle",
                  text = s"$bestScore",
                  fontSize = 24,
                  fill = "black",
                )
              )
            } else {
              Group(): TagMod[TagElement]
            }
          ).withRef(layoutRef)
        )
      )
    )
  }
}
