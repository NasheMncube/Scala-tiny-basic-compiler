package main.io.github.nashemncube.tinybasic.ast;

/**
  * Created by nashe on 11/02/2018.
  */
public enum BinaryOperator implements Operator{
    PLUS('+'), MINUS('-'), MULT('*'), DIV('/');

    private final char character;

    BinaryOperator(char character) {
        this.character = character;
    }

}
