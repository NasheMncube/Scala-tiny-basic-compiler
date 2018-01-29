package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer.Lexer
/**
  * Created by nashe on 29/01/2018.
  */
class BranchStatement(lexer:Lexer) extends Statement{
  override var args: Array[Option[Any]] = _
}
