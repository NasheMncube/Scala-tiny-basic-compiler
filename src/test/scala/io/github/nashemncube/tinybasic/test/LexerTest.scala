package io.github.nashemncube.tinybasic.test

import org.scalatest._
import main.io.github.nashemncube.tinybasic.lexer._
/**
  * Created by nashe on 26/01/2018.
  *
  * The main tests for the Lexer
  */
class LexerTest extends FunSuite {

  test("TestEOF") {
    val lexer = new Lexer("")
    assert(new Token(Type.EOF) == lexer.nextToken())
  }

  test("TestLF") {
    val lexer = new Lexer("\n")
    assert(new Token(Type.LF) == lexer.nextToken())
  }

  test("TestValuelessOperatorsAndCharacters"){
    val lexer = new Lexer("+ - / * = , ( ) >= <> >< <= > <")
    assert(new Token(Type.PLUS) == lexer.nextToken())
    assert(new Token(Type.MINUS) == lexer.nextToken())
    assert(new Token(Type.DIV) == lexer.nextToken())
    assert(new Token(Type.MULT) == lexer.nextToken())
    assert(new Token(Type.EQ) == lexer.nextToken())
    assert(new Token(Type.COMMA) == lexer.nextToken())
    assert(new Token(Type.LPAREN) == lexer.nextToken())
    assert(new Token(Type.RPAREN) == lexer.nextToken())
    assert(new Token(Type.GTE) == lexer.nextToken())
    assert(new Token(Type.NE) == lexer.nextToken() && new Token(Type.NE) == lexer.nextToken())
    assert(new Token(Type.LTE) == lexer.nextToken())
    assert(new Token(Type.GT) == lexer.nextToken())
    assert(new Token(Type.LT) == lexer.nextToken())
  }

  test("TestVAR") {
    val lexer = new Lexer("G")
    assert(new Token(Type.VAR, Option("G")) == lexer.nextToken)
  }

  test("TestString") {
    val lexer = new Lexer("\"Hello world\"")
    assert(new Token(Type.STRING, Option("Hello world")) == lexer.nextToken())
  }

  test("TestNumber"){
    val lexer = new Lexer("1234")
    assert(new Token(Type.NUMBER, Option("1234")) == lexer.nextToken())
  }

  test("TestKeyword") {
    val lexer = new Lexer("PRINT")
    assert(new Token(Type.KEYWORD, Option("PRINT")) == lexer.nextToken())
  }

  //test()


}
