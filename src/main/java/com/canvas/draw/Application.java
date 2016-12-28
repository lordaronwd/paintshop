package com.canvas.draw;

import java.util.Scanner;

/**
 * @author lazar.agatonovic
 */
public class Application {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final Worksheet worksheet = new Worksheet();

        while (true) {
            final String command = scanner.nextLine();
            if (!worksheet.execute(command)) {
                break;
            }
        }
        System.out.print("Bye...");
//        Canvas canvas = new Canvas(20, 4);
//        Line line1 = new Line(new Point(1, 2), new Point(6, 2));
//        Line line2 = new Line(new Point(6, 3), new Point(6, 4));
//        Line line3 = new Line(new Point(4, 3), new Point(16, 3));
//        Rectangle rec1 = new Rectangle(new Point(16, 1), new Point(20, 3));
//        canvas.addDrawable(line1);
//        canvas.addDrawable(line2);
//        canvas.addDrawable(line3);
//        canvas.addDrawable(rec1);
//        canvas.paint(new Point(10, 2), 'o');
//        canvas.paint(new Point(6, 2), '-');
//        canvas.paint(new Point(1, 4), '8');
//        canvas.paint(new Point(15, 4), '*');
//        canvas.draw();
    }


}
