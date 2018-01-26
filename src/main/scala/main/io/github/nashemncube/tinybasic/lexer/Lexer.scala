package main.io.github.nashemncube.tinybasic.lexer

import java.io.{IOException, Reader, StringReader}

import scala.util.matching.Regex.Match
import util.control.Breaks._


/**
 * Created by nashe on 23/01/2018.
  *
  * This is the main lexer code.
  *
  * It functions by taking in something of type Reader into to it's
  * constructer. This reader provides the character stream which
  * corresponds to the input source code for the compiler.
  *
  * The lexer steps and tokenizes the input. Definitions of the
  * token class is given in the Token.scala file.
  *
  * This is the first phase in compilation. It would have been possible
  * to skip this process and use the parser to define the grammar on
  * the fly, without tokenizing the input source. However this would have
  * been very complex, especially if the language was more complicated than
  * the TinyBasic version.
  *
  *
 */
class Lexer(reader: Reader) {

  def this(str: String) = this(new StringReader(str))

  def isInteger(char: Int): Boolean = {
    '0'.toInt <= char && char <= '9'.toInt
  }

  def isAlpha(char: Int): Boolean = {
    'A'.toInt <= char && char <= 'Z'.toInt
  }

  def isWhiteSpace(char: Int): Boolean = {
    if(Character.isWhitespace(char)) {
      true
    } else {
      false
    }
  }

  @throws
  def peek(reader: Reader): Int = {
    reader.mark(1)
    try{
      reader.read()
    } finally {
      reader.reset()
    }
  }

  @throws
  def nextToken(): Token = {
    val char = reader.read()
    if(char == -1) new Token(Type.EOF)
    else if(char == '\n'.toInt) new Token(Type.LF)
    else if(char == '+'.toInt) new Token(Type.PLUS)
    else if(char == '-'.toInt) new Token(Type.MINUS)
    else if(char == '/'.toInt) new Token(Type.DIV)
    else if(char == '*'.toInt) new Token(Type.MULT)
    else if(char == '='.toInt) new Token(Type.EQ)
    else if(char == '('.toInt) new Token(Type.LPAREN)
    else if(char == ')'.toInt) new Token(Type.RPAREN)
    else if(char == ','.toInt) new Token(Type.COMMA)
    else if(char == '\"'.toInt) nextStringToken(char)
    else if(char == '>'.toInt || char == '<'.toInt)  nextRelationalToken(char)
    else if(isAlpha(char) && !isAlpha(peek(reader)))
      new Token(Type.VAR, Option(char.toChar.toString))
    else if(isAlpha(char))
      nextKeywordToken(char)
    else if(isInteger(char))
      nextNumberToken(char)
    else if(isWhiteSpace(char))
      nextToken()
    else
      throw new IOException("Couldn't parse input")

  }

  @throws
  def nextRelationalToken(first: Int): Token = {
    val second = peek(this.reader)

    if(first == '>'.toInt) {
      if (second == '='.toInt) {
        reader.skip(1)
        new Token(Type.GTE)
      }
      else if(second == '<') {
        reader.skip(1)
        new Token(Type.NE)
      } else {
        reader.skip(1)
        new Token(Type.GT)
      }
    }
    else {
      assert(first == '<'.toInt)

      if(second == '='.toInt){
        reader.skip(1)
        new Token(Type.LTE)
      }
      else if(second == '>'.toInt){
        reader.skip(1)
        new Token(Type.NE)
      } else {
        reader.skip(1)
        new Token(Type.LT)
      }

    }


  }

  @throws
  def nextStringToken(first: Integer): Token = {
    var ret = first.toChar.toString

    breakable {
      do{
        val next = peek(this.reader)
        if(next == -1) throw new IOException("EOF found in input string")
        else if(next == '\"') break

        reader.skip(1)
        ret += next.toChar.toString
      } while(true)
    }

    new Token(Type.STRING, Option(ret))
  }

  @throws
  def nextKeywordToken(first: Int): Token = {
    var ret = first.toString

    breakable {
      do{
        val next = peek(this.reader)
        if(!isAlpha(next)) break

        reader.skip(1)
        ret += next.toString()
      } while(true)
    }

    new Token(Type.KEYWORD, Option(ret))
  }

  @throws
  def nextNumberToken(first: Integer): Token ={
    var ret = first.toString()

    breakable {
      do{
        val next = peek(this.reader)
        if(!isInteger(next)) break

        reader.skip(1)
        ret += next.toString()
      } while(true)
    }

    new Token(Type.NUMBER, Option(ret))
  }


}