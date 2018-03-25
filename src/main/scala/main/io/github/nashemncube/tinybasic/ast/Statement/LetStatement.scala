package main.io.github.nashemncube.tinybasic.ast.Statement

import java.util.ArrayList

import main.io.github.nashemncube.tinybasic.ast.Expression
import main.io.github.nashemncube.tinybasic.lexer._
/**
  *
  * Created by nashe on 30/01/2018.
  *
  * statement ::= LET var = expression
  *
  * expression ::= (+|-|ε) term ((+|-) term)*

    term ::= factor ((*|/) factor)*

    factor ::= var | number | (expression)

    var ::= A | B | C ... | Y | Z

    number ::= digit digit*
  *
  */

// TODO: Deal with parentheses for nested expressions
class LetStatement(lexer: Lexer) extends Statement(lexer) {
  override var args: ArrayList[Either[Token, Expression]] = getArgs()
  var currentToken: Token = lexer.nextToken
  var inExpr = false // Necessary for pattern matching var in arguments to let statement and not to expression and vice versa

  /*def apply(): Unit = {
    currentToken.getType match {
      case Type.EQ =>
        args :+ Left(currentToken)
        currentToken = lexer.nextToken()
        //this.apply
      case Type.VAR if !inExpr =>
        args :+ Left(currentToken)
        currentToken = lexer.nextToken()
        inExpr = true
        //this.apply()
      case Type.PLUS | Type.MINUS | Type.VAR | Type.NUMBER | Type.LPAREN => //Expression conditions
        args :+ Right(new Expression(lexer, currentToken))
        currentToken = lexer.nextToken()
        //this.apply()
    }
  }*/

  override def getArgs(): ArrayList[Either[Token, Expression]] = {
    throw new RuntimeException("Implement")
  }
}
