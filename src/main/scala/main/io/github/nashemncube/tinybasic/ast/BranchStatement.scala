package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer._
/**
  * Created by nashe on 29/01/2018.
  *
  * statement ::= INPUT var-list
  *
  * var-list ::= var (, var)*
  *
  * var ::= A | B | C ... | Y | Z
  *
  *
  */
class BranchStatement(lexer:Lexer) extends Statement(lexer){
  override var args: Array[Either[Token, Expression]] = getArgs()

  override def getArgs(): Array[Either[Token, Expression]] = {
    throw new RuntimeException("Implement me")
  }
}
