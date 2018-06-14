package main.io.github.nashemncube.tinybasic.parser

import main.io.github.nashemncube.tinybasic.lexer.Token
import main.io.github.nashemncube.tinybasic.lexer.Type._
import main.io.github.nashemncube.tinybasic.parser.Statement.{Expression, |}

case class Line(v: Option[String], s: Statement)

sealed trait Statement{

	def args: Array[Token | Expression]
	def compile: String
}

sealed trait Expr {
	type |[A, B] = Either[A, B]
	def args: Array[Token | Expression]
	def compile: String = {
		var ret: String = ""

		args.foreach[Unit](tOre =>
			tOre.toOption.get match {
				case  e: Expression =>
					ret = ret +  e.compile
				case t: Token       =>
					ret = ret + t.value.get + " "
			}
		)

		ret
	}
}

object Statement {
	type |[A, B] = Either[A, B]
  case class Expression(args: Array[Token | Expression]) extends Expr

  case class ExprList(args: Array[Token | Expression]) extends Expr

	case object Epsilon extends Statement {
		val args = Array.empty

		def compile: String = "\n"
	}

	case object ReturnStatement extends Statement {
		val args = Array.empty

		def compile: String = "return\n"
	}

	case object EndStatement extends Statement {
		val args = Array.empty

		def compile: String = "\n"
	}

	case class PrintStatement(exList: ExprList) extends Statement {
		val args = exList.args

		def compile: String = "print( " + exList.compile + ")\n"
	}

	case class LetStatement(v: Token, x: Expression) extends Statement {
		val args = Array(Left(v), Right(x))

		def compile: String  = {
			""
		}
	}

	case class IfStatement(x: Expression,
                         y: Expression,
                         op: Token, s: Statement) extends Statement {

		val args = Array(Right(x), Right(y), Left(op))

		def compile: String = {
			val (e1: String, e2: String) = (x.compile, y.compile)

			val relop: String = op.value.get

			"if " + e1 + " " + relop + " " + e2 + ":\n\t\t " + s.compile + "\n"

		}

	}

	case class GoTo(x: Expression) extends Statement {
		val args = Array(Right(x))


	}


}