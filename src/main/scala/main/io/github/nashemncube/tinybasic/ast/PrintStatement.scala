package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer._
/**
  * Created by nashe on 29/01/2018.
  *
  * statement ::= PRINT expr-list
  * expr-list ::= (string|expression) (, (string|expression) )*
  * string ::= " (a|b|c ... |x|y|z|A|B|C ... |X|Y|Z|digit)* "
  * expression ::= (+|-|ε) term ((+|-) term)*
  */
class PrintStatement(lexer: Lexer) extends Statement {

  /**
    * We need to recursively define the values expr-list
    * in statement as it could be nested.
    *
    * Expr-list is defined as comma seperateed strings/expressions.
    *
    */
  def defineValues(): Unit = {
    throw new RuntimeException("Implement me")
  }



}
