package main.io.github.nashemncube.tinybasic.ast;

/**
  * Created by nashe on 11/02/2018.
  */
public class BinaryOperator implements Operator{
    // PLUS('+'), MINUS('-'), MULT('*'), DIV('/');

    private final String character;

    BinaryOperator(String character) {
        this.character = character;
    }

}
