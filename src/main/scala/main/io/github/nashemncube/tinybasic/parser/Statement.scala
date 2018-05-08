package main.io.github.nashemncube.tinybasic.parser

import main.io.github.nashemncube.tinybasic.lexer.Token
import main.io.github.nashemncube.tinybasic.parser.Statement.Expression



sealed trait Statement{
	type |[A, B] = Either[A, B]
	def args: Array[Token | Expression]
}

object Statement {

	type |[A, B] = Either[A, B]

  case class Expression(args: Array[Token | Expression])

  case class ExprList(args: Array[Token | Expression])

	case object Epsilon extends Statement {val args = Array.empty}

	case object ReturnStatement extends Statement {val args = Array.empty}

	case object EndStatement extends Statement {val args = Array.empty}

	case class PrintStatement(exList: ExprList) extends Statement {val args = exList.args}

	case class LetStatement(v: Token, x: Expression) extends Statement {
		val args = Array(Left(v), Right(x))
	}

	case class IfStatement(x: Expression,
                         y: Expression,
                         op: Token, s: Statement) extends Statement {
		val args = Array(Right(x), Right(y), Left(op))
	}

	case class GoTo(x: Expression) extends Statement {val args = Array(Right(x))}


}