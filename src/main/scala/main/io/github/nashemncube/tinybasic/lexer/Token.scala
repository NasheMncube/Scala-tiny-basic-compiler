package main.io.github.nashemncube.tinybasic.lexer

/**
  * Created by nashe on 23/01/2018.
  */
class Token(t: Type, value: Option[String]) {

  def this(t: Type) = this(t, Option.empty)

  //override def toString() {print("Type: " + t + "Val: " + value.get)}

  def canEqual(a: Any) = a.isInstanceOf[Token]

  override def equals(that: Any): Boolean = {
    that match {
      case that: Token => that.canEqual(this) && this.hashCode == that.hashCode
      case _ => false
    }
  }

  override def hashCode: Int = {
    t match {
      case Type.EOF     => 0
      case Type.LF      => 1
      case Type.VAR     => 2 + hashString(value.get)
      case Type.KEYWORD => 3 + hashString(value.get)
      case Type.NUMBER  => 4 + hashString(value.get)
      case Type.STRING  => 5 + hashString(value.get)
      case Type.PLUS    => 6
      case Type.MINUS   => 7
      case Type.DIV     => 8
      case Type.LPAREN  => 9
      case Type.RPAREN  => 10
      case Type.EQ      => 11
      case Type.NE      => 12
      case Type.GT      => 13
      case Type.GTE     => 14
      case Type.LT      => 15
      case Type.LTE     => 16
      case Type.COMMA   => 17
    }
  }

  def hashString(str: String): Int = {
    str.map(_.toInt).sum
  }

  def getValue(): String = {
    value.getOrElse(null)
  }

}
