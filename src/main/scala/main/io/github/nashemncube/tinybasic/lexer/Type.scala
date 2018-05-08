package main.io.github.nashemncube.tinybasic.lexer;

/**
 * Created by nashe on 23/01/2018.
 */
sealed trait Type

object Type {

  case object EOF extends Type

  case object LF extends Type

  case object VAR extends Type

  case object KEYWORD extends Type

  case object NUMBER extends Type

  case object STRING extends Type

  case object PLUS extends Type

  case object MINUS extends Type

  case object DIV extends Type

  case object LPAREN extends Type

  case object RPAREN extends Type

  case object EQ extends Type

  case object NE extends Type

  case object GT extends Type

  case object GTE extends Type

  case object LT extends Type

  case object LTE extends Type

  case object COMMA extends Type

  case object MULT extends Type

}

