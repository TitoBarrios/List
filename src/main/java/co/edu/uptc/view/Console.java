package co.edu.uptc.view;

import java.util.Scanner;

public class Console {
    private static Scanner console;

    public static void initialize() {
        console = new Scanner(System.in);
    }

    public static String readData() {
        return console.nextLine();
    }

    public static int readNumber() throws NumberFormatException {
        return Integer.parseInt(console.nextLine());
    }

    public static void log(String message) {
        System.out.println(message);
    }
}
