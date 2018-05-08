package io.github.nashemncube.tinybasic.test

import java.io.StringReader

import org.scalatest._
import main.io.github.nashemncube.tinybasic.lexer._
/**
  * Created by nashe on 26/01/2018.
  *
  * The main tests for the Lexer
  */
class LexerTest extends FunSuite {

  test("Token returns"){
    val input = "+ - / * = ( ) , \"Hello world\" > <  <= >= <> ><\n" +
                "A PRINT 123 "
    val lexer = new Lexer(new StringReader(input))

    assert(lexer.nextToken.t == PLUS)
    assert(lexer.nextToken.t == MINUS)
    assert(lexer.nextToken.t == DIV)
    assert(lexer.nextToken.t == MULT)
    assert(lexer.nextToken.t == EQ)
    assert(lexer.nextToken.t == LPAREN)
    assert(lexer.nextToken.t == RPAREN)
    assert(lexer.nextToken.t == COMMA)
    assert(lexer.nextToken == Token(STRING, Option("Hello world")) )
    assert(lexer.nextToken.t == GT)
    assert(lexer.nextToken.t == LT)
    assert(lexer.nextToken.t == LTE)
    assert(lexer.nextToken.t == GTE)
    assert(lexer.nextToken.t == NE)
    assert(lexer.nextToken.t == NE)
    assert(lexer.nextToken.t == LF)
    assert(lexer.nextToken   == Token(VAR, Option("A")))
    assert(lexer.nextToken   == Token(KEYWORD, Option("PRINT")))
    assert(lexer.nextToken   == Token(NUMBER, Option("123")))
    assert(lexer.nextToken.t == EOF)

  }
}
