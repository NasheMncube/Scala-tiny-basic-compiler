package main.io.github.nashemncube.tinybasic.lexer

import java.io.{IOException, Reader, StringReader}

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
  * than TinyBASIC. A more detailed theortical definition of what is going on can
  * be found here assuming basic knowledge of CFGs : https://cs.stackexchange.com/a/28866
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
  def nextToken: Token = {
    val char = reader.read()
    if(char == -1) Token(EOF, Option.empty)
    else if(char == '\n'.toInt) Token(LF, Option.empty)
    else if(char == '+'.toInt)  Token(PLUS, Option.empty)
    else if(char == '-'.toInt)  Token(MINUS, Option.empty)
    else if(char == '/'.toInt)  Token(DIV, Option.empty)
    else if(char == '*'.toInt)  Token(MULT, Option.empty)
    else if(char == '='.toInt)  Token(EQ, Option.empty)
    else if(char == '('.toInt)  Token(LPAREN, Option.empty)
    else if(char == ')'.toInt)  Token(RPAREN, Option.empty)
    else if(char == ','.toInt)  Token(COMMA, Option.empty)
    else if(char == '\"'.toInt) nextStringToken(char)
    else if(char == '>'.toInt || char == '<'.toInt)  nextRelationalToken(char)
    else if(isAlpha(char) && !isAlpha(peek(reader)))
       Token(VAR, Option(char.toChar.toString))
    else if(isAlpha(char))
      nextKeywordToken(char)
    else if(isInteger(char))
      nextNumberToken(char)
    else if(isWhiteSpace(char))
      nextToken
    else
      throw new IOException("Couldn't parse input")

  }

  @throws
  def nextRelationalToken(first: Int): Token = {
    val second = peek(this.reader)

    if(first == '>'.toInt) {
      if (second == '='.toInt) {
        reader.skip(1)
        Token(GTE, Option.empty)
      }
      else if(second == '<') {
        reader.skip(1)
        Token(NE, Option.empty)
      } else {
        reader.skip(1)
        Token(GT, Option.empty)
      }
    }
    else {
      assert(first == '<'.toInt)

      if(second == '='.toInt){
        reader.skip(1)
        Token(LTE, Option.empty)
      }
      else if(second == '>'.toInt){
        reader.skip(1)
        Token(NE, Option.empty)
      } else {
        reader.skip(1)
        Token(LT, Option.empty)
      }

    }


  }

  @throws
  def nextStringToken(first: Int): Token = {
    var ret = ""
    var next: Int = -1

      while(!next.equals('\"'.toInt)){
        next = peek(this.reader)
        //print(ret)

        if(next == -1) throw new IOException("EOF found in input string")
        if(next.equals('\"'.toInt)) {
          reader.skip(1)
          return new Token(STRING, Option(ret))
        }


        reader.skip(1)
        ret += next.toChar.toString

      }


    throw new IOException("Syntax error: String missing terminating character")
    //return new Token(Type.STRING, Option(ret))
  }

  @throws
  def nextKeywordToken(first: Int): Token = {
    var ret = first.toChar.toString

    breakable {
      do{
        val next = peek(this.reader)
        if(!isAlpha(next)) {
          //reader.skip(1)
          break
        }

        reader.skip(1)
        ret += next.toChar.toString()
      } while(true)
    }

    Token(KEYWORD, Option(ret))
  }

  @throws
  def nextNumberToken(first: Int): Token ={
    var ret = first.toChar.toString
    breakable {
      do{
        val next = peek(this.reader)
        if(!isInteger(next)) {
          //reader.skip(1)
          break
        }

        reader.skip(1)
        ret += next.toChar.toString
      } while(true)
    }

    Token(NUMBER, Option(ret))
  }

}
