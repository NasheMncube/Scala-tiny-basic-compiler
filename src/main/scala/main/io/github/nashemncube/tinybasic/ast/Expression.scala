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
// TODO: Define expression class and methods for all expression types
class Expression(lexer: Lexer, currentToken: Token) {

  //var term: Option[String]

  currentToken.getType match {
    case Type.PLUS   => // Handle plus operator
    case Type.MINUS  => // Handle minus operator
    case Type.VAR    => // Handle var
    case Type.NUMBER => // Handle number
    case _           => throw new RuntimeException("Couldn't handle expression")

  }

  // TODO: Define the recursive functions which handle case statements abov
  // TODO: Consider not making class abstract but a general concrete type
  /** TODO: Consider making expressions an easily mappable array for their description*This will generalise
    * to larger expressions and removes the need for trivial object descriptions
    * for different types of expressions. Similarly do the same for the case of
    * statements and their behaviour
   */




}
