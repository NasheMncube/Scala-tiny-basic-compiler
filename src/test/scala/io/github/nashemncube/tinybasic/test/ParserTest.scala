package io.github.nashemncube.tinybasic.test

import com.sun.xml.internal.xsom.impl.scd.Iterators.Array
import main.io.github.nashemncube.tinybasic.ast.Statement.{EndStatement, PrintStatement, ReturnStatement}
import org.scalatest._
import main.io.github.nashemncube.tinybasic.lexer._
import main.io.github.nashemncube.tinybasic.parser._
import main.io.github.nashemncube.tinybasic.ast.Statement._
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
      "PRINT \"Hello world\" \n "
      +"PRINT \"Hello again\" \n "
      +"PRINT 12, (24)"
      )
    val parser = new Parser(lexer)

    val s1 = parser.statement()
    assert(s1.isInstanceOf[PrintStatement])
    assert(s1.args.get(0) == (Left(new Token(Type.STRING, Option("Hello world")))))

    parser.advance()
    val s2 = parser.statement()
    assert(s2.args.get(0) == Left(new Token(Type.STRING, Option("Hello again"))))

    parser.advance()
    val s3 = parser.statement()
    (s3.args.get(0).right.get.value.forEach(s => print(s + "\n")))



  }

}
