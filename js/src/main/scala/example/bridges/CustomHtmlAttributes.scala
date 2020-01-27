package example.bridges

import slinky.core.CustomAttribute

object CustomHtmlAttributes {
  val role = new CustomAttribute[String]("role")
  val dataToggle = new CustomAttribute[String]("data-toggle")
  val ariaHaspopup = new CustomAttribute[String]("aria-haspopup")
  val ariaExpanded = new CustomAttribute[String]("aria-expanded")
  val ariaLabelledby = new CustomAttribute[String]("aria-labelledby")
}
