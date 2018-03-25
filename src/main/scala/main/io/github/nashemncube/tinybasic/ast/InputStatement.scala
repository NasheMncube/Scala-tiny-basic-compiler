package main.io.github.nashemncube.tinybasic.ast


import main.io.github.nashemncube.tinybasic.lexer._

import java.util.ArrayList
/**
  * Created by nashe on 30/01/2018.
  *
  * statement ::= INPUT var-list
  *
  * var-list ::= var (, var)*
  * var ::= A | B | C ... | Y | Z
  *
  */
class InputStatement(lexer: Lexer) extends Statement(lexer) {

  override var args: ArrayList[Either[Token, Expression]] = getArgs()
  var currentToken: Token = lexer.nextToken()

  /*f apply(): Unit = {
    currentToken.getType match {
      case Type.COMMA =>
        args :+ ","
        currentToken = lexer.nextToken()
        this.apply
      case Type.VAR   =>
        args :+ currentToken.getValue.get
        currentToken = lexer.nextToken()
        this.apply
      case _          =>
        return
    }
  }*/

  override def getArgs(): ArrayList[Either[Token, Expression]] = {
    throw new RuntimeException("Implement me")
  }

}
