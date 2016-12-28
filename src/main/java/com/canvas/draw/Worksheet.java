package com.canvas.draw;

import java.util.Arrays;

/**
 * @author lazar.agatonovic
 */
public class Worksheet {

    private static final String QUIT = "Q";
    private static final String CANVAS = "C ";
    private static final String LINE = "L ";
    private static final String RECTANGLE= "R ";
    private static final String BUCKET = "B ";
    private static final String BAD_ARGUMENTS = "Wrong number of arguments. ";
    private static final String BAD_ARGUMENT_VALUES = "Coordinates and dimensions must be integers. ";
    private static final String BAD_COORDINATE = "Bad coordinate %s = %d. Please use value within [%d, %d]";
    private static final String BAD_COLOR_VALUE = "Bucket color value must be a single character. ";
    private static final String HELP_MESSAGE = "Please use `help` command to see the list of available commands.";
    private static final String CANVAS_NOT_INITIALIZED = "Canvas must be created first. ";
    private static final String UNKNOWN_COMMAND = "Unknown command. ";

    private Canvas canvas;

    public Worksheet() {
        super();
    }

    public boolean execute(final String command) {
        try {
            if (isQuitCommand(command)) {
                return false;
            }

            if (isCanvasCommand(command)) {
                drawCanvas(command);
            } else if (isLineCommand(command)) {
                drawLine(command);
            } else if (isRectangleCommand(command)) {
                drawRectangle(command);
            } else if (isBucketCommand(command)) {
                bucketPaint(command);
            } else {
                throw new IllegalArgumentException(UNKNOWN_COMMAND + HELP_MESSAGE);
            }
        } catch (final IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    private boolean isCanvasCommand(final String command) {
        return command.startsWith(CANVAS);
    }

    private boolean isLineCommand(final String command) {
        return command.startsWith(LINE);
    }

    private boolean isRectangleCommand(final String command) {
        return command.startsWith(RECTANGLE);
    }

    private boolean isBucketCommand(final String command) {
        return command.startsWith(BUCKET);
    }

    private boolean isQuitCommand(final String command) {
        return command.equals(QUIT);
    }

    private void drawCanvas(final String command) {
        if (canvas != null) {
            throw new IllegalArgumentException("Canvas has already been created.");
        }

        final String[] tokens = tokenizeCommand(command, 2);
        canvas = new Canvas(parseNumericToken(tokens[0]), parseNumericToken(tokens[1]));
    }

    private void drawLine(final String command) {
        assertCanvasCreated(canvas);
        final String[] tokens = tokenizeCommand(command, 4);

        final Point startPoint = new Point(getXCoordinate(tokens[0]), getYCoordinate(tokens[1]));
        final Point endPoint = new Point(getXCoordinate(tokens[2]), getYCoordinate(tokens[3]));
        final Line line = new Line(startPoint, endPoint);

        canvas.addDrawable(line);
    }

    private void drawRectangle(final String command) {
        assertCanvasCreated(canvas);
        final String[] tokens = tokenizeCommand(command, 4);

        final Point vertex1 = new Point(getXCoordinate(tokens[0]), getYCoordinate(tokens[1]));
        final Point vertex2 = new Point(getXCoordinate(tokens[2]), getYCoordinate(tokens[3]));
        final Rectangle rectangle = new Rectangle(vertex1, vertex2);

        canvas.addDrawable(rectangle);
    }

    private void bucketPaint(final String command) {
        assertCanvasCreated(canvas);
        final String[] tokens = tokenizeCommand(command, 3);

        final String color = tokens[2];
        if (color.length() != 1) {
            throw new IllegalArgumentException(BAD_COLOR_VALUE + HELP_MESSAGE);
        }

        final Point initialPoint = new Point(getXCoordinate(tokens[0]), getYCoordinate(tokens[1]));
        canvas.paint(initialPoint, color.charAt(0));
    }

    private String[] tokenizeCommand(final String command, final int tokensCount) {
        final String[] tokens = command.split(" ");
        final String[] relevantTokens = Arrays.copyOfRange(tokens, 1, tokens.length);

        if (relevantTokens.length != tokensCount) {
            throw new IllegalArgumentException(BAD_ARGUMENTS + HELP_MESSAGE);
        }

        return relevantTokens;
    }

    private int getXCoordinate(final String xCoordinate) {
        final int x = parseNumericToken(xCoordinate);
        if (x < 1 || x > canvas.getDrawableWidth()) {
            throw new IllegalArgumentException(String.format(BAD_COORDINATE, "x", x, 1, canvas.getDrawableWidth()));
        }

        return x;
    }

    private int getYCoordinate(final String yCoordinate) {
        final int y = parseNumericToken(yCoordinate);
        if (y < 1 || y > canvas.getDrawableHeight()) {
            throw new IllegalArgumentException(String.format(BAD_COORDINATE, "y", y, 1, canvas.getDrawableHeight()));
        }

        return y;
    }

    private void assertCanvasCreated(final Canvas canvas) {
        if (canvas == null) {
            throw new IllegalStateException(CANVAS_NOT_INITIALIZED + HELP_MESSAGE);
        }
    }

    private Integer parseNumericToken(final String token) {
        try {
            return Integer.valueOf(token);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(BAD_ARGUMENT_VALUES + HELP_MESSAGE);
        }
    }

}
