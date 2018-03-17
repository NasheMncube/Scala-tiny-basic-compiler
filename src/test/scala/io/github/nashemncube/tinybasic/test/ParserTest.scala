package io.github.nashemncube.tinybasic.test

import org.scalatest._

import main.io.github.nashemncube.tinybasic.lexer._
import main.io.github.nashemncube.tinybasic.parser._
import main.io.github.nashemncube.tinybasic.ast._


class ParserTest extends FunSuite{

  test("Test EndStatement return from parser") {
    val lexer  = new Lexer("END")
    val parser = new Parser(lexer)

    assert(parser.statement.isInstanceOf[EndStatement])
  }

  test("Test ReturnStatement return from parser") {
    val lexer  = new Lexer("RETURN")
    val parser = new Parser(lexer)

    assert(parser.statement().isInstanceOf[ReturnStatement])
  }

  test("Test PrintStatement return from parser for strings") {
    val lexer = new Lexer(
      "PRINT \"Hello world\" \n " //+
      //"PRINT 12, 34, 567\n " +
       // "PRINT "
      )
    val parser = new Parser(lexer)

    val s1 = parser.statement()
    assert(s1.isInstanceOf[PrintStatement])
    assert(s1.args == Left(new Token(Type.STRING, Option("Hello world"))))


  }

}
