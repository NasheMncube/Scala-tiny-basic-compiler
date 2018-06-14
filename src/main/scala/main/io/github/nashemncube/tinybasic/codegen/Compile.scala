package main.io.github.nashemncube.tinybasic.codegen

import java.io._

import main.io.github.nashemncube.tinybasic.parser._
import main.io.github.nashemncube.tinybasic.lexer._

class Compile(lexer: Lexer, filePath: String) {
	val parser: Parser = new Parser(lexer)

	val lines: Array[Line] = parser.lines

	val file             = new File(filePath)
	val bw               = new BufferedWriter(new FileWriter(file))

	def write_line(line: String): Unit = { bw.write(line) }

	def compile_lines: Unit = {
		write_line("def main():\n\t")
		lines.foreach[Unit](l => write_line("\t" + l.s.compile))
		write_line("if __name__ == \"__main__: \n\tmain()" )
		bw.close()
	}


}
