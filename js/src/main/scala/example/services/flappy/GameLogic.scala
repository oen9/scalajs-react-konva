package example.services.flappy

import scala.util.Random
import com.github.oen9.slinky.bridge.konva.Konva.Frame
import com.softwaremill.quicklens._

object GameLogic {
  val width = 480
  val height = 640
  val groundY = 576
  val birdWidth = 23
  val groundWidth = 18
  val pipeWidth = 69

  val holeSize = 200
  val distanceBetweenPipes = 250
  val pipeStartAtX = 500

  val gravitationStep = 5
  val upStep = 20
  val rotationAngle = 30
  val groundSpeed = 2
  val upSlowdown = 2

  case class Pipe(x: Int , y: Int)
  case class Bird(
    y: Int = height / 2,
    angle: Int = 0,
    upAcceleration: Int = 0,
  )
  case class GameState(
    gameOver: Boolean = false,
    fps: Double = 0,
    score: Int = 0,
    groundShift: Int = 0,
    bird: Bird = Bird(),
    pipe1: Pipe = generateNewPipe(pipeStartAtX),
    pipe2: Pipe = generateNewPipe(pipeStartAtX + distanceBetweenPipes),
  )

  def generateNewPipe(startX: Int): Pipe = {
    val holePosition = Random.nextInt(groundY - holeSize)
    Pipe(x = startX, y = holePosition)
  }


  def movePipe(pipe: Pipe): (Pipe, Int) = {
    if (pipe.x <= -pipeWidth)
      (generateNewPipe(pipeStartAtX), 1)
    else
      (pipe.copy(x = pipe.x - groundSpeed), 0)
  }

  def loop(frame: Frame, gs: GameState): GameState = {
    val newUpAcc =
      if ((gs.bird.upAcceleration - upSlowdown) > 0) {
        gs.bird.upAcceleration - upSlowdown
      } else 0

    val currentAcc = gravitationStep - newUpAcc
    val newBirdY = gs.bird.y + currentAcc

    val newAngle =
      if (currentAcc > 0) rotationAngle
      else if (currentAcc < 0) -rotationAngle
      else 0

    val newGroundShift = if (gs.groundShift < -15) 0 else gs.groundShift - groundSpeed

    val (newPipe1, pipe1Score) = movePipe(gs.pipe1)
    val (newPipe2, pipe2Score) = movePipe(gs.pipe2)

    val newScore = gs.score + pipe1Score + pipe2Score

    gs.modify(_.bird.angle).setTo(newAngle)
      .modify(_.bird.upAcceleration).setTo(newUpAcc)
      .modify(_.bird.y).setTo(newBirdY)
      .modify(_.fps).setTo((1000 / frame.timeDiff).toInt)
      .modify(_.groundShift).setTo(newGroundShift)
      .modify(_.score).setTo(newScore)
      .modify(_.pipe1).setTo(newPipe1)
      .modify(_.pipe2).setTo(newPipe2)
  }
}
