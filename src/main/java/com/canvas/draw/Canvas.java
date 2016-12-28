package com.canvas.draw;

/**
 * @author lazar.agatonovic
 */
public class Canvas {

    private static final char HORIZONTAL_BORDER = '-';
    private static final char VERTICAL_BORDER = '|';
    private final int borderedWidth;
    private final int borderedHeight;
    private char[][] coloredMatrix;

    public Canvas(int width, int height) {
        this.borderedWidth = width + 2;
        this.borderedHeight = height + 2;
        initializeMatrix();
        draw();
    }

    public int getDrawableWidth() {
        return borderedWidth - 2;
    }

    public int getDrawableHeight() {
        return borderedHeight - 2;
    }

    private void initializeMatrix() {
        this.coloredMatrix = new char[borderedHeight][borderedWidth];

        for (int i = 0; i < borderedHeight; i++) {
            for (int j = 0; j < borderedWidth; j++) {
                if (i == 0 || i == borderedHeight - 1) {
                    coloredMatrix[i][j] = HORIZONTAL_BORDER;
                } else if (j == 0 || j == borderedWidth - 1) {
                    coloredMatrix[i][j] = VERTICAL_BORDER;
                } else {
                    coloredMatrix[i][j] = ' ';
                }
            }
        }
    }

    private void draw() {
        for (int i = 0; i < borderedHeight; i++) {
            for (int j = 0; j < borderedWidth; j++) {
                System.out.print(coloredMatrix[i][j]);
            }
            System.out.print("\n");
        }
    }

    public void addDrawable(final Drawable line) {
        line.getPoints().forEach(point -> coloredMatrix[point.getY()][point.getX()] = 'x');
        draw();
    }

    public void paint(final Point point, final char newColor) {
        final char currentColor = coloredMatrix[point.getY()][point.getX()];
        if (currentColor == newColor) {
            return;
        }
        paint(currentColor, newColor, point.getX(), point.getY());
        draw();
    }

    private void paint(final char currentColor, final char newColor, final int x, final int y) {
        coloredMatrix[y][x] = newColor;
        if (borderedHeight != y + 1 && coloredMatrix[y + 1][x] == currentColor) {
            paint(currentColor, newColor, x, y + 1);
        }
        if (borderedHeight != y - 1 && coloredMatrix[y - 1][x] == currentColor) {
            paint(currentColor, newColor, x, y - 1);
        }

        if (borderedWidth != x + 1 && coloredMatrix[y][x + 1] == currentColor) {
            paint(currentColor, newColor, x + 1, y);
        }
        if (borderedWidth != x + 1 && coloredMatrix[y][x - 1] == currentColor) {
            paint(currentColor, newColor, x - 1, y);
        }
    }

}
