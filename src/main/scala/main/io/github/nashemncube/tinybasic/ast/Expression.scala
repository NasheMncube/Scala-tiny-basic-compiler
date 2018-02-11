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

  // Rexpr essentially deals with right hand recursion, lexpr is a expression definition
  // Rexpr can also be a simple definition, but by design recursion will be forced to rexpr terms as
  // by definition of the grammar

  // DONE: Considered eliminating left recursion using Chomsky normal form
  // Above is done, asssuming that a the grammar is defined such that we can't derive expression ::= expression
  // but rather expression ::= (expression). Parentheses group objects, eliminating left recursion

  var lExpr, rExpr: Option[Expression]

  currentToken.getType match {
    case Type.PLUS | Type.MINUS   =>
      lExpr = new UnaryExpression()

    case Type.VAR    => // Handle var
    case Type.NUMBER => // Handle number
    case _           => throw new RuntimeException("Couldn't handle expression")
  }

  def nextExpr(): Expression = {


  }

  def nextTerm(): Expression = {

  }

  // TODO: Define the recursive functions which handle case statements above
  // TODO: Consider not making class abstract but a general concrete type
  // TODO: Finish implementation such that it is testable against non-branch statements




}
