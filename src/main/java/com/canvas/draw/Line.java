package com.canvas.draw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lazar.agatonovic
 */
public class Line implements Drawable {

    private List<Point> points;

    public Line(final Point startPoint, final Point endPoint) {
        initializePoints(startPoint, endPoint);
    }

    @Override
    public List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }

    private void initializePoints(Point startPoint, Point endPoint) {
        if (startPoint.getX() != endPoint.getX()
                && startPoint.getY() != endPoint.getY()
                && (startPoint.getX() - startPoint.getY() != endPoint.getX() - endPoint.getY()
                && (startPoint.getX() + startPoint.getY() != endPoint.getX() + endPoint.getY()))) {
            throw new IllegalArgumentException("Unable to draw straight line from " + startPoint + " to " + endPoint);
        }

        points = new ArrayList<>();
        if (startPoint.getX() == endPoint.getX()) {
            final int start = Math.min(startPoint.getY(), endPoint.getY());
            final int end = Math.max(startPoint.getY(), endPoint.getY());

            for (int i = start; i <= end; i++) {
                points.add(new Point(startPoint.getX(), i));
            }
        } else if (startPoint.getY() == endPoint.getY()) {
            final int start = Math.min(startPoint.getX(), endPoint.getX());
            final int end = Math.max(startPoint.getX(), endPoint.getX());

            for (int i = start; i <= end; i++) {
                points.add(new Point(i, startPoint.getY()));
            }
        } else {
            final Point minXPoint = startPoint.getX() <= endPoint.getX() ? startPoint : endPoint;
            final Point maxXPoint = startPoint.getX() <= endPoint.getX() ? endPoint : startPoint;
            int startX = minXPoint.getX();
            int endX = maxXPoint.getX();
            int startY = minXPoint.getY();
            final int yIncrement = minXPoint.getY() <= maxXPoint.getY() ? 1 : -1;

            points.add(minXPoint);
            points.add(maxXPoint);
            while (startX != endX) {
                startY += yIncrement;
                points.add(new Point(++startX, startY));
            }
        }

    }
}
