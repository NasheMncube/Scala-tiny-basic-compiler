package main.io.github.nashemncube.tinybasic.parser

import main.io.github.nashemncube.tinybasic.lexer._
import main.io.github.nashemncube.tinybasic.parser.Statement._
import main.io.github.nashemncube.tinybasic.lexer.Type._

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

  var token: Token = lexer.nextToken

  def advance(): Unit = {
    token = lexer.nextToken
  }

  @throws
  def eat(t: Type): Token ={
    if(t == token){
      token
    }
    else throw new RuntimeException("Type mismatch")
  }


  case class Line(v: Option[String], s: Statement)

  def line: Line = {
    val value = token.value

    token.t match {
      case NUMBER =>
        eat(NUMBER)
        Line(value, statement)
      case _ =>
        Line(Option.empty, statement) // Numberless line consists of only statement
    }
  }


  @throws
  def statement: Statement= {

	  if(token.t == LF || token.t == EOF) return Epsilon

    token.value.getOrElse("fail") match { // Statements correspond to keyword type

      case "PRINT"  =>
        advance()
        PrintStatement(exprList)

      case "GOTO"   =>
        advance()
        GoTo(expression)

      case "LET"    =>
        advance()
        val v = eat(VAR); advance()
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

        val op = token.t match {
          case GT
               | GTE
               | LT
               | LTE
               | EQ
               | NE => token
          case _ => throw new RuntimeException("No valid op in 'IF' expression")
        }
        advance()

        val e2 = expression

        val s = token.t match {
          case KEYWORD =>
            if (token.value.getOrElse("Fail") == "THEN") statement
            else throw new RuntimeException("No 'THEN' clause to 'IF' statement")
          case _ => throw new RuntimeException("No 'THEN' clause to 'IF' statement")
        }

        IfStatement(e1, e2, op, s)
      }

      case _        =>
        throw new RuntimeException("Invalid statement in code " + token.value.getOrElse("NO STATEMENT"))
    }
  }

  @throws
  def expression: Expression = {
    var collect = Array[Token | Expression]()
    while (true) {
      try {
        token.t match {
          case PLUS
               | MINUS
               | VAR
               | NUMBER
               | COMMA
               | MULT
               | DIV  =>
            collect ++ Array(Left(token))
            advance()
          case LPAREN =>
	          collect ++ Array(Left(token))
            advance()
            collect ++ Array(Right(expression))
          case RPAREN =>
	          collect ++ Array(Left(token))
	          advance()
          case _ => return Expression(collect)
        }

      } catch {
        case _: Exception => throw new RuntimeException("Failed to parse expression")
      }
    }

    Expression(collect)
  }

  @throws
  def exprList: ExprList= {

    var ret = Array[Token | Expression]()
    token.t match {
      case STRING =>
        ret ++ Array(Left(token))
        advance()
      case _ =>
        ret ++ Array(Right(expression))
        advance()
    }

    if(token.t == COMMA) {
      ret ++ Array(Left(token))
      advance()
      exprList.args.foreach(i => ret ++ Array(i))
    }
    ExprList(ret)
  }

}
