package rs.raf;

import org.antlr.v4.runtime.Token;
import rs.raf.calculator.Calculator;
import rs.raf.calculator.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Main {
    private static final Calculator calculator = new Calculator();
    static boolean hadError = false;
    static boolean hadRuntimeError = false;


    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    private static void runFile(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            run(content.toString());
        }

        if (hadError) System.exit(65);
        if (hadRuntimeError) System.exit(70);
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        while (true) {
            System.out.print("> ");
            String line = reader.readLine();

            if (line == null || line.equalsIgnoreCase("exit")) {
                break;
            }

            run(line);
            hadError = false;
        }
    }

    private static void run(String source) {
        Scanner scanner = new Scanner();
        List<? extends Token> tokens = scanner.getAllTokens(source);

        if (hadError) return;
        System.out.println(tokens);
    }

}