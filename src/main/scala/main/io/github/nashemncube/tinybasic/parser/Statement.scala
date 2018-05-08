package main.io.github.nashemncube.tinybasic.parser

import main.io.github.nashemncube.tinybasic.lexer.Token



sealed trait Statement

object Statement {

  type |[A, B] = Either[A, B]

  case class Expression(args: Array[Token | Expression])

  case class ExprList(args: Array[String | Expression])

  case object ReturnStatement extends Statement

  case object EndStatement extends Statement

  case class PrintStatement(exList: ExprList) extends Statement

  case class LetStatement(v: Token, x: Expression) extends Statement

  case class IfStatement(x: Expression,
                         y: Expression,
                         op: Token, s: Statement) extends Statement

  case class GoTo(x: Expression) extends Statement


}