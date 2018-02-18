package main.io.github.nashemncube.tinybasic.ast;

/**
  * Created by nashe on 11/02/2018.
  */
public class UnaryOperator implements Operator {
    //PLUS('+'), MINUS('-');

    private final String character;

    UnaryOperator(String character){
        this.character = character;
    }

}
