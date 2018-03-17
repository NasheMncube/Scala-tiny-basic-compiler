package main.io.github.nashemncube.tinybasic.ast


import main.io.github.nashemncube.tinybasic.lexer._

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
class PrintStatement(lexer: Lexer) extends Statement(lexer = lexer) {

  /**
    * We need to recursively define the values expr-list
    * in statement as it could be nested.
    *
    * Expr-list is defined as comma seperateed strings/expressions.
    *
    */
  // TODO: Deal with parentheses indication nested expressions
  var currentToken: Token = lexer.nextToken()
  override var args: Array[Either[Token, Expression]] = getArgs()

  override def getArgs(): Array[Either[Token, Expression]] = {

    var ret: Array[Either[Token, Expression]] = Array.empty

    currentToken.getType match {

      case Type.EOF | Type.LF   =>
        return ret

      case Type.COMMA           =>
        currentToken = lexer.nextToken()
        ret ++ getArgs()

      case Type.STRING          =>
        ret :+ Left(currentToken)
        currentToken = lexer.nextToken()
        ret ++ getArgs()

      case _                    =>
        ret :+ Right(new Expression(lexer, currentToken))

    }

    ret
  }

}
