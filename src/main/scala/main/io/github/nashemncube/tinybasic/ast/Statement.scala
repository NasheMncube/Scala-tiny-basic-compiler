package main.io.github.nashemncube.tinybasic.ast

import main.io.github.nashemncube.tinybasic.lexer._
import java.util.ArrayList
/**
  * Created by nashe on 27/01/2018.
  */

// TODO: Test the non-conditional statements
// TODO: Write the conditional statements
// TODO: Test conditional statements
abstract class Statement(lexer: Lexer) {

  // Args can be expressions, varlists, exprlists, other statements and strings
  var args: ArrayList[Either[Token, Expression]]

  def getArgs(): ArrayList[Either[Token, Expression]]

}
