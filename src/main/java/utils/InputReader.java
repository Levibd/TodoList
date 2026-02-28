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

    public String readValidDate(){
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4} ([01][0-9]|2[0-3]):([0-5][0-9])$";

       while (true){
          String input = scanner.nextLine();

          if(!input.matches(regex)){
              throw new IllegalArgumentException();
          }
           return input;
       }

    }
}