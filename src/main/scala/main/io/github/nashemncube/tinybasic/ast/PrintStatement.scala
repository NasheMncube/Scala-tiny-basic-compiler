package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer._
import main.io.github.nashemncube.tinybasic.ast.Expression
/**
  * Created by nashe on 29/01/2018.
  *
  * statement ::= PRINT expr-list
  * expr-list ::= (string|expression) (, (string|expression) )*
  * string ::= " (a|b|c ... |x|y|z|A|B|C ... |X|Y|Z|digit)* "
  * expression ::= (+|-|ε) term ((+|-) term)*
  *
  * term ::= factor ((*|/) factor)*

    factor ::= var | number | (expression)

    var ::= A | B | C ... | Y | Z

    number ::= digit digit*

    digit ::= 0 | 1 | 2 | 3 | ... | 8 | 9
  */
class PrintStatement(lexer: Lexer) extends Statement {

  /**
    * We need to recursively define the values expr-list
    * in statement as it could be nested.
    *
    * Expr-list is defined as comma seperateed strings/expressions.
    *
    */

  var currentToken: Token = lexer.nextToken()
  override var args: Array[Either[Token, Expression]]

  def apply(): Unit = {
    currentToken.getType match {
      case Type.COMMA                                      =>
        args :+ Left(currentToken)
        currentToken = lexer.nextToken()
        this.apply()
      case Type.STRING                                     =>
        args :+ Left(currentToken)
        currentToken = lexer.nextToken()
        this.apply()
      case Type.PLUS | Type.MINUS | Type.VAR | Type.NUMBER =>
        args :+ Right(new Expression(lexer, currentToken))
        currentToken = lexer.nextToken()
        this.apply()
      case _ =>
        return
  }



}
