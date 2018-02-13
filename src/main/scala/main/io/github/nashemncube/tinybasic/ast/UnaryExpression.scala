package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer._
/**
  * Created by nashe on 11/02/2018.
  */

/*
  It makes sense that within a unary expression we can obtain a binary expression using this as the left expresison
  and recursively obtaining the right expression.
 */

// TODO: Move computation of whether we have a binary or unary to the parser class solely. It's not clean but more
// understandable
class UnaryExpression(operatorType: Type, term: Either[Token, Expression]) extends Expression {


  val operator: UnaryOperator = {
    operatorType match {
      case Type.PLUS              =>  UnaryOperator.PLUS
      case Type.MINUS             => UnaryOperator.MINUS
      case Type.NUMBER | Type.VAR => null
      case _          => throw new RuntimeException("Incorrect type passed to unary expression")
    }
  }

}
