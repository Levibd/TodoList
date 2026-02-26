package utils;

import java.util.Scanner;

public class InputReader {

    private static InputReader instance;
    private final Scanner scanner;


    private InputReader() {
        this.scanner = new Scanner(System.in);
    }


    public static InputReader getInstance() {
        if (instance == null) {
            instance = new InputReader();
        }
        return instance;
    }


    public String readString() {
        return scanner.nextLine();
    }

    public int readInt() {
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
}