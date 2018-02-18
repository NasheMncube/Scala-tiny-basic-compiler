package main.io.github.nashemncube.tinybasic.parser

import main.io.github.nashemncube.tinybasic.lexer.{Lexer, Token, Type}
import main.io.github.nashemncube.tinybasic.ast._

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
                  INPUT var-list
                  LET var = expression
                  GOSUB expression
                  RETURN
                  /*CLEAR*/ Removed
                  /*LIST*/ Removed
                  END

    expr-list ::= (string|expression) (, (string|expression) )*

    var-list ::= var (, var)*

    expression ::= (+|-|ε) term ((+|-) term)*

    term ::= factor ((*|/) factor)*

    factor ::= var | number | (expression)

    var ::= A | B | C ... | Y | Z

    number ::= digit digit*

    digit ::= 0 | 1 | 2 | 3 | ... | 8 | 9

    relop ::= < (>|=|ε) | > (<|=|ε) | =

    string ::= " (a|b|c ... |x|y|z|A|B|C ... |X|Y|Z|digit)* "
  */
class Parser(lexer: Lexer) {

  var token: Token = lexer.nextToken()

  def advance(): Unit = {
    token = lexer.nextToken()
  }

  @throws
  def eat(t: Type): Boolean  ={
    if(t == token.getType){
      advance()
      true
    }
    else false
  }


  def line(): Line = {
    val value = token.getValue

    token.getType match {
      case Type.NUMBER =>
        eat(Type.NUMBER)
        new Line(value, statement())
      case _ =>
        advance()
        new Line(statement())// Numberless line consists of only statement
    }
  }

  @throws
  def statement(): Statement = {

    token.getValue.get match { // Statements correspond to keyword type
      case "PRINT"  => new PrintStatement(lexer)
      case "IF"     => throw new RuntimeException("Implement me")
      case "GOTO"   => throw new RuntimeException("Implement me")
      case "INPUT"  => new InputStatement(lexer)
      case "LET"    => new LetStatement(lexer)
      case "GOSUB"  => throw new RuntimeException("Implement me")
      case "RETURN" => new ReturnStatement(lexer)
      case "END"    => new EndStatement(lexer)
      case _        => throw new RuntimeException("Invalid statement in code " + token.getValue.getOrElse("NO STATEMENT"))
    }
  }



}
