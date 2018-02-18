package io.github.nashemncube.tinybasic.test

import org.scalatest._

import main.io.github.nashemncube.tinybasic.lexer._
import main.io.github.nashemncube.tinybasic.parser._
import main.io.github.nashemncube.tinybasic.ast._


class ParserTest extends FunSuite{

  test("Test EndStatement return from parser") {
    val lexer = new Lexer("END")
    val parser = new Parser(lexer)

    assert(parser.statement.isInstanceOf[EndStatement])
  }

}
