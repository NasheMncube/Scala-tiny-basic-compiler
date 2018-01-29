package main.io.github.nashemncube.tinybasic.ast

/**
  * Created by nashe on 29/01/2018.
  */
class EndStatement extends Statement{
  override var args: Option[String] = Option.empty
  override var expr: Option[Expression] = Option.empty
}
