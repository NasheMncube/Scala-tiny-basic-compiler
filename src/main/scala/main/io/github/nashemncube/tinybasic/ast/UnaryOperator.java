package main.io.github.nashemncube.tinybasic.ast;

/**
  * Created by nashe on 11/02/2018.
  */
public enum UnaryOperator implements Operator {
    PLUS('+'), MINUS('-');

    private final char character;

    UnaryOperator(char character){
        this.character = character;
    }

}
