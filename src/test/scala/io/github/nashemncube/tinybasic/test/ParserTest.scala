package io.github.nashemncube.tinybasic.test

import main.io.github.nashemncube.tinybasic.parser._
import main.io.github.nashemncube.tinybasic.lexer._
import main.io.github.nashemncube.tinybasic.lexer.Type._
import main.io.github.nashemncube.tinybasic.parser.Statement._
import org.scalatest._


class ParserTest extends FunSuite{

  test("Non argument statements"){
    val input  = "END RETURN" // TODO
    val lexer  = new Lexer (input)
    val parser = new Parser(lexer)

    assert(parser.statement == EndStatement)
    assert(parser.statement == ReturnStatement)
  }

	test("Test parsing of expressions") {
		val input = "1 * ( 2 + 4 )\n"

		val lexer = new Lexer(input)
		val parser = new Parser(lexer)

	}

  test("Test PRINT statements"){
	  val input = "PRINT \"Hello world\", A\n"

	  val lexer = new Lexer(input)
	  val parser = new Parser(lexer)

	  assert(parser.statement.args ==
		  Array(
			  Left(Token(STRING, Option("Hello world"))))
  }


}
