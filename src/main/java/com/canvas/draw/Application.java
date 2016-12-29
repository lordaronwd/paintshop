package com.canvas.draw;

import java.util.Scanner;

/**
 * @author lazar.agatonovic
 */
public class Application {

    private static final String HELP_COMMAND = "help";
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final Worksheet worksheet = new Worksheet();

        while (true) {
            final String command = scanner.nextLine();
            if (command.equals(HELP_COMMAND)) {
                displayHelp();
            } else if (!worksheet.execute(command)) {
                break;
            }
        }
        System.out.print("Bye...");
    }

    private static void displayHelp() {
        System.out.println("C w h          Creates a new canvas of width w and height h.");
        System.out.println("L x1 y1 x2 y2  Creates a line from (x1, y1) to (x2, y2) using `x` character.");
        System.out.println("R x1 y1 x2 y2  Creates a rectangle whose diagonal vertexes are (x1, y1) to (x2, y2)" +
                " using `x` character for the outline.");
        System.out.println("B x y c        Fills in entire area connected to (x, y) using colour 'c'.");
        System.out.println("Q              Exits the program.");

    }

}
