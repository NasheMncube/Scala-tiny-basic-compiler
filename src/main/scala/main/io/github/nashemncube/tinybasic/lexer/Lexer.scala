package main.io.github.nashemncube.tinybasic.lexer

import java.io.{IOException, Reader}

import com.sun.tools.hat.internal.model.Snapshot


/**
 * Created by nashe on 23/01/2018.
 */
public class Lexer(reader: Reader) {

  def isInteger(char: Char): Boolean = {
    '0' <= char && char <= '9'
  }

  def isAlpha(char: Char): Boolean = {
    'A' <= char && char <= 'Z'
  }

  def isWhiteSpace(char: Character): Boolean = {
    if(Character.isWhitespace(char)) {
      true
    } else {
      false
    }
  }

  @throws(IOException)
  def peek(reader: Reader): Int = {
    reader.mark(1)
    try{
      reader.read()
    } finally {
      reader.reset()
    }
  }

  def nextNumberToken(first: Integer): Token ={
    var ret = first.toString()

    do{
      val p = peek(this.reader)
      if(!isInteger(p))

    }while(true)
  }



}
