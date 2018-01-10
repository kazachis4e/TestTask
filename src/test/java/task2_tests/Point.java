package task2_tests;

/**
 * Describes the coordinates of two points
 */
public class Point {

    private int X;

    private int Y;

    public Point(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Point)) return false;

        Point otherPoint = (Point)other;

        return this.getX() == otherPoint.getX() && this.getY() == otherPoint.getY();
    }
}
