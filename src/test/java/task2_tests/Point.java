package task2_tests;

/**
 * Describes the coordinates of two points
 */
public class Point {

    private Integer X;

    private Integer Y;

    public Point(Integer x, Integer y) {
        X = x;
        Y = y;
    }

    public Integer getX() {
        return X;
    }

    public Integer getY() {
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
