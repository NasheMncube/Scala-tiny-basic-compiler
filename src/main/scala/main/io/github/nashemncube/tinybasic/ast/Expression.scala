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
class Expression(lexer: Lexer, currentToken: Token) {

  /*// Rexpr essentially deals with right hand recursion, lExpr is a expression definition
  // Rexpr can also be a simple definition, but by design recursion will be forced to rexpr terms as
  // by definition of the grammar

  // DONE: Considered eliminating lExpr recursion using Chomsky normal form
  // Above is done, asssuming that a the grammar is defined such that we can't derive expression ::= expression
  // but rather expression ::= (expression). Parentheses group objects, eliminating lExpr recursion

  // TODO: Deal with multiplication and division in factor definition
  var lExpr, rExpr: Either[Token, Expression]

  currentToken.getType match {
    case Type.PLUS | Type.MINUS   =>
      lExpr = Right(new UnaryExpression(Type, nextTerm))
      rExpr = Right(nextExpr)

    case Type.VAR    =>
      lExpr = Left(currentToken)
      //rExpr = Right(nextExpr) Should handle binary expression

    case Type.NUMBER =>
      lExpr = Left(currentToken)
      //rExpr = Right(nextExpr) should handle binary expression possibility

    case Type.MULT | Type.DIV  => _ // Theoretically this is handled by nextTerm

  }

  def nextExpr: Expression = {
    new Expression(lexer, lexer.nextToken())
  }

  def nextTerm: Either[Token, Expression] = {
    val nextToken = lexer.nextToken()

    nextToken.getType match {
      case Type.VAR | Type.NUMBER                         =>
        Left(nextToken)
      case Type.MULT | Type.DIV | Type.PLUS | Type.MINUS  =>
        Right(new BinaryExpression(nextToken.getType, lexer))//TODO: Correctly recursively define a binary expression. Perhaps remove
      case Type.LPAREN                                    =>
        val expr = nextExpr
        Right(expr)
    }

  }*/

  /**
    * If we enter an expression class, if the first thing we see is a plus or minus, we know that we have a unary
    * expression. This is of the form
    *   (+|-) term ::=
    *   (+|-) (factor ((*|/) factor)* ) ::=
    *                                   (+|-) (((var | number | (expression)) (*|/) var | number | (expression))
    *
    */

  var lExpr, rExpr: Option[Expression]

  currentToken.getType match {
    case Type.PLUS | Type.MINUS =>
      lExpr = Option(new UnaryExpression(currentToken.getType, nextTerm))
      rExpr = nextExpr

    case Type.VAR | Type.NUMBER =>
      lExpr = Option(new UnaryExpression(currentToken.getType, Left(currentToken)))
      rExpr = nextExpr

    /*case Type.LPAREN =>
      lExpr = nextExpr*/
  }

  /**
    * Passed to sub expressions in tree so as to recursively build the AST
    * @return Next term may be a token or a nested expression, hidden by parentheses
    */

  def nextTerm: Either[Token, Expression] = {
    val nextToken = lexer.nextToken()

    nextToken.getType match {
      case Type.VAR | Type.NUMBER =>
        val buf = lexer.nextToken()
        buf.getType match {
          case Type.MULT | Type.DIV => Right(new BinaryExpression(nextToken, buf.getType, lexer))
          case _ => Left(nextToken)
        }

      case Type.LPAREN =>
        val expr = new Expression(lexer, nextToken)
        assert(lexer.nextToken().getType == Type.RPAREN)
        val buf = lexer.nextToken()
        buf.getType match {
          case Type.MULT | Type.DIV => Right(new BinaryExpression(Option(expr), buf.getType, lexer))
          case _                    => Right(expr)
        }
    }


  }

  /**
    * Read next token in stream, and recursively obtain the right expression.
    *
    * Left recursion is eliminated via this method.
    * @return Definition for the right expression
    */

  def nextExpr: Option[Expression] = {
    val nextToken = lexer.nextToken()

    nextToken.getType match {
      case Type.PLUS | Type.MINUS =>
        Option(new BinaryExpression(lExpr, nextToken.getType, lexer))

      case _ => Option.empty
    }

  }

  // DONE: Define the recursive functions which handle case statements above
  // DONE: Consider not making class abstract but a general concrete type
  // TODO: Finish implementation such that it is testable against non-branch statements
  // TODO: Testing for unary and binary statements.




}
