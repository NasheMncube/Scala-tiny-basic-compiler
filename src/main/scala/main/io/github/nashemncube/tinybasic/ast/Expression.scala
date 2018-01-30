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

  //var term: Option[String]

  currentToken.getType match {
    case Type.PLUS   => // Handle plus operator
    case Type.MINUS  => // Handle minus operator
    case Type.VAR    => // Handle var
    case Type.NUMBER => // Handle number
    case _           => throw new RuntimeException("Couldn't handle expression")

  }

  // TODO: Define the recursive functions which handle case statements above
  // TODO: Consider not making class abstract but a general concrete type
  // TODO: Finish implementation such that it is testable against non-branch statements




}
