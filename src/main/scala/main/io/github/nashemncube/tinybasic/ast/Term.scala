package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer._
abstract class Term {
  val leftTerm, rightTerm: Either[Token, Expression]
}
