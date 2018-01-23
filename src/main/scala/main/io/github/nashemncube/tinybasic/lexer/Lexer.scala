package main.io.github.nashemncube.tinybasic.lexer

import java.io.{IOException, Reader}
import util.control.Breaks._


/**
 * Created by nashe on 23/01/2018.
 */
public class Lexer(reader: Reader) {

  def isInteger(char: Int): Boolean = {
    '0'.toInt <= char && char <= '9'.toInt
  }

  def isAlpha(char: Int): Boolean = {
    'A'.toInt <= char && char <= 'Z'.toInt
  }

  def isWhiteSpace(char: Character): Boolean = {
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
  def nextToken(): Unit = {

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
    var ret = first.toString

    breakable {
      do{
        var next = peek(this.reader)
        if(next == -1) throw new IOException("EOF found in input string")
        else if(next == '"') break

        ret += next.toString()
      } while(true)
    }

    new Token(Type.STRING, Option(ret))
  }

  @throws
  def nextKeywordToken(first: Int): Token = {
    var ret = first.toString

    breakable {
      do{
        var next = peek(this.reader)
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
        var next = peek(this.reader)
        if(!isInteger(next)) break

        reader.skip(1)
        ret += next.toString()
      } while(true)
    }

    new Token(Type.NUMBER, Option(ret))
  }



}
