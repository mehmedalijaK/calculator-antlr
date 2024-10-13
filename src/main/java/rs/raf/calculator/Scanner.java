package rs.raf.calculator;

import calculator.parser.Calculator;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Scanner {

    public List<? extends Token> getAllTokens(String expression) {
        CharStream chars = CharStreams.fromString(expression);
        Lexer lexer = new Calculator(chars);
        return lexer.getAllTokens();
    }

}
