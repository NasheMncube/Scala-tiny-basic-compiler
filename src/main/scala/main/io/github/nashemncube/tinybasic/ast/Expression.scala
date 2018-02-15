package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer._
/**
  * Created by nashe on 29/01/2018.
  *
  * expression ::= (+|-|ε) term ((+|-) term)*

    term ::= factor ((*|/) factor)*

    factor ::= var | number | '('expression')'

    var ::= A | B | C ... | Y | Z

    number ::= digit digit*
  */


// TODO: Define expression class and methods for all expression types such that recursively obtain expressions
class Expression(lexer: Lexer, var currentToken: Token) {

  // TODO: Finish implementation such that it is testable against non-branch statements
  // TODO: Testing for unary and binary statements.

  // TODO: Consider expressions being described as below. Using array allows for a much easier recursion.
  //       this allows expressionss to be added by reading whole expression at once.
  // lExpr, rExpr: Array[Either[Term, BinaryOperator]]
  /**
    * If we enter an expression class, if the first thing we see is a plus or minus, we know that we have a unary
    * expression. This is of the form
    *   (+|-) term ::=
    *   (+|-) (factor ((*|/) factor)* ) ::=
    *                                   (+|-) (((var | number | (expression)) (*|/) var | number | (expression))
    *
    */

  val value: Array[Either[Operator, Term]]


  /**
    * This method should recursively build the entire abstract syntaxt for expression so as to handle
    * expression statements that are longer than just two terms and also longer than just two factors
    *
    * This should be some type of looping calling the above functions
    *
    * example
    *
    * term + term - term <epsilon>
    *
    * factor * factor / factor * factor <epsilon>
    *
    */
  def buildExpression(): Unit = {

    while (true) {
      currentToken.getType match {
        case Type.PLUS | Type.MINUS =>
          value :+ getOperatorType(currentToken.getValue.get)
          currentToken = lexer.nextToken()

        case Type.VAR | Type.NUMBER | Type.LPAREN =>
          value :+ Right(nextTerm)
          currentToken = lexer.nextToken

        case _ => return
      }
    }
  }

  def getOperatorType(valOfOp: String): Operator = {
    if (value.length == 0) {
      new UnaryOperator(valOfOp)
    }
    else {
      new BinaryOperator(valOfOp)
    }
  }

  case class Term(value: Array[Either[Factor, BinaryOperator ]])

  case class Factor(value: Either[String, Expression])

  def nextTerm: Term = {
    val factors: Array[Either[Factor, BinaryOperator]] = Array.empty

    while(true) {
      currentToken.getType match {
        case Type.VAR | Type.NUMBER =>
          factors :+ Left(Factor(Left(currentToken.getValue.get)))
          currentToken = lexer.nextToken()
        case Type.LPAREN =>
          factors :+ Left(Factor(Right(new Expression(lexer, lexer.nextToken()))))
          currentToken = lexer.nextToken()
        case Type.DIV | Type.MULT =>
          factors :+ Right(new BinaryOperator(currentToken.getValue.get))
          currentToken = lexer.nextToken()
        case Type.RPAREN => // Skip over RPAREN on end of expression in factors
          currentToken = lexer.nextToken()
          return Term(factors)
        case _ =>
          return Term(factors)

      }
    }
    Term(factors)
  }



}
