package main.io.github.nashemncube.tinybasic.ast.Statement

import java.util.ArrayList

import main.io.github.nashemncube.tinybasic.ast.Expression
import main.io.github.nashemncube.tinybasic.lexer._
/**
  * Created by nashe on 29/01/2018.
  */
class EndStatement(lexer: Lexer) extends Statement(lexer){
  override var args: ArrayList[Either[Token, Expression]] = _

  override def getArgs(): ArrayList[Either[Token, Expression]] = {
    throw new RuntimeException("End statement has no args, function getArgs should not be called")
  }
}
