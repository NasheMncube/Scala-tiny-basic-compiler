package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer.Token
/**
  * Created by nashe on 11/02/2018.
  */

/*
  It makes sense that within a unary expression we can obtain a binary expression using this as the left expresison
  and recursively obtaining the right expression.
 */

// TODO: Move computation of whether we have a binary or unary to the parser class solely. It's not clean but more
// understandable
class UnaryExpression(operator: UnaryOperator, term: Either[Token, Expression]) extends Expression {

  override def nextExpr: Expression = {

  }

  override def nextTerm: Either[Token, Expression] =  {

  }

}
