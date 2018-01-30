package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer.Lexer
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
class BranchStatement(lexer:Lexer) extends Statement{
  override var args: Array[Any] = _
}
