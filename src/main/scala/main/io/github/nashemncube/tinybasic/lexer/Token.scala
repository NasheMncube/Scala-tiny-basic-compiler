package main.io.github.nashemncube.tinybasic.lexer

/**
  * Created by nashe on 23/01/2018.
  */

case class Token(t: Type, value: Option[String]) {

  def canEqual(a: Any): Boolean = a.isInstanceOf[Token]

  override def equals(that: Any): Boolean = {
    that match {
      case that: Token => that.canEqual(this) && this.hashCode == that.hashCode
      case _ => false
    }
  }

  override def hashCode: Int = {
    t match {
      case EOF     => 0
      case LF      => 1
      case VAR     => 2 + hashString(value.get)
      case KEYWORD => 3 + hashString(value.get)
      case NUMBER  => 4 + hashString(value.get)
      case STRING  => 5 + hashString(value.get)
      case PLUS    => 6
      case MINUS   => 7
      case DIV     => 8
      case LPAREN  => 9
      case RPAREN  => 10
      case EQ      => 11
      case NE      => 12
      case GT      => 13
      case GTE     => 14
      case LT      => 15
      case LTE     => 16
      case COMMA   => 17
      case MULT    => 18
    }
  }

  def hashString(str: String): Int = {
    str.map(_.toInt).sum
  }

}

