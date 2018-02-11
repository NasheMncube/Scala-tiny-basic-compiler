package main.io.github.nashemncube.tinybasic.ast

/**
  * Created by nashe on 11/02/2018.
  */
public enum BinaryOperator{
    PLUS('+'), MINUS('-'), MULT('*'), DIV('/');

    private final char character;

    private BinaryOperator(char character) {
        this.character = character
    }


}
