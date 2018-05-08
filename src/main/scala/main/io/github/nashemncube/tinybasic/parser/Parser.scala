package main.io.github.nashemncube.tinybasic.parser

import java.util._

import main.io.github.nashemncube.tinybasic.lexer.{Lexer, Token, Type}

// TODO: Testing of parser

/**
  * Created by nashe on 27/01/2018.
  *
  * The tinyBASIC grammar is defined below
  *
  * In the listing, an asterisk ("*") denotes zero or more of the object to its left —
  * except for the first asterisk in the definition of "term", which is the multiplication operator; parentheses
  * group objects; and an epsilon ("ε") signifies the empty set.
  *
  * line ::= number statement CR | statement CR

    statement ::= PRINT expr-list
                  IF expression relop expression THEN statement
                  GOTO expression

                  LET var = expression

                  RETURN // DONE
                  END // DONE

    expr-list ::= (string|expression) (, (string|expression) )

    expression ::= (+|-|ε) term ((+|-) term)*

    term ::= factor ((*|/) factor)*

    factor ::= var | number | (expression)

    var ::= A | B | C ... | Y | Z // TERMINAL

    number ::= digit digit* // TERMINAL

    digit ::= 0 | 1 | 2 | 3 | ... | 8 | 9 // TERMINAL

    relop ::= < (>|=|ε) | > (<|=|ε) | = // TERMINAL

    string ::= " (a|b|c ... |x|y|z|A|B|C ... |X|Y|Z|digit)* " // TERMINAL
  */
class Parser(lexer: Lexer) {

  type |[A, B] = Either[A, B]
  type Expression  = Array[Token | Expression]
  def Expression(x: Either[Token, Expression]*) = Array(x: _ *)

  var token: Token = lexer.nextToken()

  def advance(): Unit = {
    token = lexer.nextToken()
  }

  @throws
  def eat(t: Type): Token ={
    if(t == token.getType){
      token
    }
    else throw new RuntimeException("Type mismatch")
  }


  case class Line(v: Option[String], s: Statement)

  def line(): Line = {
    val value = token.getValue

    token.getType match {
      case Type.NUMBER =>
        eat(Type.NUMBER)
        Line(value, statement)
      case _ =>
        Line(Option.empty, statement) // Numberless line consists of only statement
    }
  }

  sealed trait Statement

  case object ReturnStatement extends Statement

  case object EndStatement    extends Statement

  case class PrintStatement(exList: Array[String | Expression]) extends Statement

  case class LetStatement(v: Token, x: Array[Token | Expression]) extends Statement

  case class IfStatement(x: Array[Token | Expression],
                         y: Array[Token | Expression],
                         op: Token, s: Statement) extends Statement

  case class GoTo(x: Array[Token | Expression]) extends Statement

  @throws
  def statement: Statement= {

    token.getValue.getOrElse("fail") match { // Statements correspond to keyword type

      case "PRINT"  =>
        advance()
        PrintStatement(exprList)

      case "GOTO"   =>
        advance()
        GoTo(expression)

      case "LET"    =>
        advance()
        val v = eat(Type.VAR); advance()
        LetStatement(v, expression)

      case "RETURN" =>
        advance()
        ReturnStatement

      case "END"    =>
        advance()
        EndStatement

      case "IF"     =>
      {
        val e1 = expression

        val op = token.getType match {
          case Type.GT
               | Type.GTE
               | Type.LT
               | Type.LTE
               | Type.EQ
               | Type.NE => token
          case _ => throw new RuntimeException("No valid op in 'IF' expression")
        }
        advance()

        val e2 = expression

        val s = token.getType match {
          case Type.KEYWORD =>
            if (token.getValue.get == "THEN") statement
            else throw new RuntimeException("No 'THEN' clause to 'IF' statement")
          case _ => throw new RuntimeException("No 'THEN' clause to 'IF' statement")
        }

        IfStatement(e1, e2, op, s)
      }

      case _        => throw new RuntimeException("Invalid statement in code " + token.getValue.getOrElse("NO STATEMENT"))
    }
  }

  @throws
  def expression: Array[Token | Expression] = {
    var collect = Array[Token | Expression]()
    while (true) {
      try {
        token.getType match {
          case Type.PLUS
               | Type.MINUS
               | Type.VAR
               | Type.NUMBER
               | Type.COMMA
               | Type.MULT
               | Type.DIV  =>
            collect :+ Left(token)
            advance()
          case Type.LPAREN =>
            advance()
            collect :+ Right(expression)
          case _ => return collect
        }

      } catch {
        case _: Exception => throw new RuntimeException("Failed to parse expression")
      }
    }

    collect
  }

  @throws
  def exprList: Array[String | Expression] = {

    var ret = Array[String | Expression]()
      token.getType match {
        case Type.STRING =>
          ret :+ Left(token)
          advance()
        case _ =>
          ret :+ Right(expression)
          advance()
      }

    if(token.getType == Type.COMMA) {
      ret :+ Left(token)
      advance()
      exprList.foreach(i => ret :+ i)
    }
    ret
  }

}
