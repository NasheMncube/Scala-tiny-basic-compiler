package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer._
/**
  * Created by nashe on 29/01/2018.
  *
  * expression ::= (+|-|ε) term ((+|-) term)*

    term ::= factor ((*|/) factor)*

    factor ::= var | number | (expression)

    var ::= A | B | C ... | Y | Z

    number ::= digit digit*
  */


// TODO: Define expression class and methods for all expression types such that recursively obtain expressions
class Expression(lexer: Lexer, currentToken: Token) {

  // Rexpr essentially deals with right hand recursion, lExpr is a expression definition
  // Rexpr can also be a simple definition, but by design recursion will be forced to rexpr terms as
  // by definition of the grammar

  // DONE: Considered eliminating lExpr recursion using Chomsky normal form
  // Above is done, asssuming that a the grammar is defined such that we can't derive expression ::= expression
  // but rather expression ::= (expression). Parentheses group objects, eliminating lExpr recursion

  var lExpr, rExpr: Either[Token, Expression]

  currentToken.getType match {
    case Type.PLUS | Type.MINUS   =>
      lExpr = {
        if (currentToken.getType == Type.PLUS)
          Right(new UnaryExpression(UnaryOperator.PLUS, nextTerm))
        else
          Right(new UnaryExpression(UnaryOperator.MINUS, nextTerm))
      }
      
      rExpr = Right(nextExpr)

    case Type.VAR    =>
      lExpr = Left(currentToken)
      rExpr = Right(nextExpr)

    case Type.NUMBER =>
      lExpr = Left(currentToken)
      rExpr = Right(nextExpr)

    case _           => throw new RuntimeException("Couldn't handle expression")
  }

  def nextExpr: Expression = {


  }

  def nextTerm: Either[Token, Expression] = {
    val nextToken = lexer.nextToken()

    nextToken.getType match {
      case Type.VAR | Type.NUMBER => Left(nextToken)
      case Type.LPAREN            =>
        val expr = nextExpr
        Right(expr)
    }


  }

  // TODO: Define the recursive functions which handle case statements above
  // TODO: Consider not making class abstract but a general concrete type
  // TODO: Finish implementation such that it is testable against non-branch statements




}
