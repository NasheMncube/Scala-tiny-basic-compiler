package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer._
/**
  * Created by nashe on 29/01/2018.
  */
class ReturnStatement(lexer: Lexer) extends Statement(lexer){
  override var args: Array[Either[Token, Expression]] = Array.empty

  override def getArgs(): Array[Either[Token, Expression]] = {
    throw new RuntimeException("Return statement has no args, functions should not be called")
  }
}
