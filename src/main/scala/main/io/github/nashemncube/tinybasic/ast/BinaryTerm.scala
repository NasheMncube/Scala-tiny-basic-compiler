package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer._

class BinaryTerm(leftTerm: Either[Token, Expression], op: Token, lexer: Lexer) extends Term {
  val rightTerm: Either[Token, Expression] = getRightTerm

  def getRightTerm: Either[Token, Expression] = {
    val nextToken = lexer.nextToken

    nextToken.getType match {
      case Type.VAR | Type.NUMBER =>
        Left(nextToken)
      case Type.LPAREN =>
        Right(new Expression(lexer, lexer.nextToken()))
    }
  }
}
