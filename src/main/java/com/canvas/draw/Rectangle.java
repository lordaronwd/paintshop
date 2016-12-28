package com.canvas.draw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lazar.agatonovic
 */
public class Rectangle implements Drawable {

    private List<Point> points;

    public Rectangle(final Point vertex1, final Point vertex2) {
        initializePoints(vertex1, vertex2);
    }

    @Override
    public List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }

    private void initializePoints(final Point vertex1, final Point vertex2) {
        if (vertex1.getX() == vertex2.getX()
                || vertex1.getY() == vertex2.getY()
                || (vertex1.getX() - vertex1.getY() == vertex2.getX() - vertex2.getY()
                || (vertex1.getX() + vertex1.getY() == vertex2.getX() + vertex2.getY()))) {
            throw new IllegalArgumentException("Unable to draw a rectangle as vertexes "
                    + vertex1 + " and " + vertex2 + "are defining a line");
        }

        points = new ArrayList<>();

        int minX = Math.min(vertex1.getX(), vertex2.getX());
        int maxX = Math.max(vertex1.getX(), vertex2.getX());

        int minY = Math.min(vertex1.getY(), vertex2.getY());
        int maxY = Math.max(vertex1.getY(), vertex2.getY());

        for (int i = minY; i <= maxY; i++) {
            points.add(new Point(minX, i));
            points.add(new Point(maxX, i));

            if (i == minY || i == maxY) {
                for (int j = minX + 1; j < maxX; j++) {
                    points.add(new Point(j, i));
                }
            }
        }
    }
}

