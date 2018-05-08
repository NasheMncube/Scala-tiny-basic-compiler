package io.github.nashemncube.tinybasic.test

import main.io.github.nashemncube.tinybasic.parser._
import main.io.github.nashemncube.tinybasic.lexer._
import main.io.github.nashemncube.tinybasic.parser.Statement._
import org.scalatest._


class ParserTest extends FunSuite{

  test("Non argument statements"){
    val input  = ??? // TODO
    val lexer  = new Lexer (input)
    val parser = new Parser(lexer)

    assert(parser.line.s == EndStatement)
    assert(parser.line.s == ReturnStatement)
  }


}
