package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer._
/**
  *
  * Created by nashe on 11/02/2018.
  *
  * When called, we need to build a binary expression's right hand term
  */
class BinaryExpression(lExpr: Option[Expression], operatorType: Type, lexer: Lexer/*, lExpr: Expression, rExpr: Expression*/) extends Expression{

  override var rExpr: Option[Expression] = nextExpr

  val operator: BinaryOperator = {
    operatorType match {
      case Type.MULT  => BinaryOperator.MULT
      case Type.DIV   => BinaryOperator.DIV
      case Type.PLUS  => BinaryOperator.PLUS
      case Type.MINUS => BinaryOperator.MINUS
    }
  }

  override def nextExpr: Option[Expression] = {

  }

    //TODO: Modified super methods to recursively define sub expressions


}
