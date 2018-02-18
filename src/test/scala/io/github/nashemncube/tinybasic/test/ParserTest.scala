package io.github.nashemncube.tinybasic.test

import org.scalatest._
import main.io.github.nashemncube.tinybasic.{lexer, parser}


class ParserTest {

  test("Test END statement"){
    val lexer = new Lexer("END")
    val parser = new Parser(lexer)

    assert(new EndStatment() == parser.statement)
  }

}
