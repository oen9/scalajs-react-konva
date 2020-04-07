package example.modules.konvashapes

import cats.implicits._
import com.github.oen9.slinky.bridge.konva.Konva
import com.github.oen9.slinky.bridge.konva.Konva.Animation
import com.github.oen9.slinky.bridge.reactkonva.Image
import com.github.oen9.slinky.bridge.reactkonva.Layer
import com.github.oen9.slinky.bridge.reactkonva.Operations
import com.github.oen9.slinky.bridge.reactkonva.Stage
import com.github.oen9.slinky.bridge.useimage.UseImage._
import com.softwaremill.quicklens._
import example.components.flappy.Bird
import example.components.flappy.Ground
import example.components.flappy.Hood
import example.components.flappy.PipeElem
import example.components.flappy.Scoreboard
import example.services.flappy.GameLogic
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.core.facade.React
import slinky.core.FunctionalComponent
import slinky.web.html._

@react object FlappyBirdPage {
  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    val (background, _) = useImage("front-res/img/flappy/background.png")

    val (gs, setGs) = useState(GameLogic.GameState())
    val (bestScore, setBestScore) = useState(0)
    val (anim, setAnim) = useState(none[Animation])

    val birdRef = React.createRef[Operations.SpriteRef]
    val layoutRef = React.createRef[Operations.ShapeRef]
    val upperPipe1 = React.createRef[Operations.ShapeRef]
    val lowerPipe1 = React.createRef[Operations.ShapeRef]
    val upperPipe2 = React.createRef[Operations.ShapeRef]
    val lowerPipe2 = React.createRef[Operations.ShapeRef]

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
      birdRect.y > (GameLogic.groundY - birdRect.height)
    }

    useLayoutEffect(
      () => birdRef.current.start(),
      Seq()
    )

    useLayoutEffect(() => {
      val anim = new Animation(
        frame => {
          setGs(gs => {
            if (!gs.gameOver) GameLogic.loop(frame, gs)
            else gs
          })
        },
        Seq(layoutRef.current)
      )
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
      }
    }, Seq(gs))

    def handleClick(): Unit = {
      gs.gameOver match {
        case false =>
          anim match {
            case Some(value) =>
              if (!value.isRunning()) value.start()
            case None =>
          }
          setGs(currGs => currGs.modify(_.bird.upAcceleration).setTo(gs.opt.upStep))
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
        Stage(
          width = GameLogic.width,
          height = GameLogic.height,
          onClick = handleClick _,
          onTap = handleClick _
        )(
          Layer(
            Image(
              image = background,
              width = GameLogic.width,
              height = GameLogic.height,
            ),
            Bird(
              angle = gs.bird.angle,
              y = gs.bird.y,
              ref =  birdRef,
              debug = gs.opt.debug,
            ),
            PipeElem(
              pipe = gs.pipe1,
              holeSize = gs.opt.holeSize,
              upperPipeRef = upperPipe1,
              lowerPipeRef = lowerPipe1,
              debug = gs.opt.debug,
            ),
            PipeElem(
              pipe = gs.pipe2,
              holeSize = gs.opt.holeSize,
              upperPipeRef = upperPipe2,
              lowerPipeRef = lowerPipe2,
              debug = gs.opt.debug,
            ),
            Ground(
              groundShift = gs.groundShift,
              debug = gs.opt.debug,
            ),
            Hood(
              fps = gs.fps,
              score = gs.score,
            ),
            Scoreboard(
              gameOver = gs.gameOver,
              score = gs.score,
              bestScore = bestScore,
              onClickRestart = handleClickRestart _,
            ),
          ).withRef(layoutRef)
        )
      )
    )
  }
}
