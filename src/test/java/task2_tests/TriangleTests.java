package task2_tests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import task2.ITriangle;
import task2.ITriangleProvider;

public class TriangleTests {

    @Mock
    private ITriangle Triangle;

    @Mock
    private ITriangleProvider TriangleProvider;

    /**
     * Base initialization for all tests
     */
    @BeforeEach
    private void InitTriangle() {
        MockitoAnnotations.initMocks(this);

        setTriangleTestData(1, 1, 1, 3, 3, 1);
        when(TriangleProvider.getTriangle()).thenReturn(Triangle);
    }

    @Test
    @DisplayName("All vertices are different")
    public void testAllVerticesAreDifferent() {
        ITriangle triangle = TriangleProvider.getTriangle();
        Point[] vertices = getVertices(triangle);

        String message = "Some of vertices are the same: x=%d, y=%d";
        assertFalse(vertices[0].equals(vertices[1]), String.format(message, vertices[0].getX(), vertices[0].getY()));
        assertFalse(vertices[1].equals(vertices[2]), String.format(message, vertices[0].getX(), vertices[0].getY()));
        assertFalse(vertices[2].equals(vertices[0]), String.format(message, vertices[0].getX(), vertices[0].getY()));
    }

    @Test
    @DisplayName("All vertices are not on the same line")
    public void testAllVerticesNotOnLine() {
        ITriangle triangle = TriangleProvider.getTriangle();
        Point[] vertices = getVertices(triangle);

        // Check tangent of any two vectors
        Point vector1 = getVectorByVertices(vertices[0], vertices[1]);
        Point vector2 = getVectorByVertices(vertices[1], vertices[2]);
        double firstTang = GetTangent(vector1.getY(),vector1.getX());
        double secondTang = GetTangent(vector2.getY(),vector2.getX());

        double epsilon = 1e-10;
        assertFalse(Math.abs(firstTang - secondTang) <= epsilon, "All vertices are on same line");
    }

    @Test
    @DisplayName("Triangle is rectangular")
    public void testTriangleIsRectangular() {
        ITriangle triangle = TriangleProvider.getTriangle();
        Point[] vertices = getVertices(triangle);

        // Represent triangle sides as vectors
        Point vector1 = getVectorByVertices(vertices[0], vertices[1]);
        Point vector2 = getVectorByVertices(vertices[1], vertices[2]);
        Point vector3 = getVectorByVertices(vertices[2], vertices[0]);

        // Triangle is rectangular, if any of two vectors (triangle sides) are orthogonal
        boolean isTriangleOrthogonal =
            isVectorsOrthogonal(vector1, vector2) ||
            isVectorsOrthogonal(vector2, vector3) ||
            isVectorsOrthogonal(vector3, vector1);

        assertTrue(isTriangleOrthogonal, "Triangle is not rectangular");
    }

    /**
     * Set triangle vertices coordinates, which will be used in tests
     * @param x1 - Axis X coordinate of vertex 1
     * @param y1 - Axis Y coordinate of vertex 1
     * @param x2 - Axis X coordinate of vertex 2
     * @param y2 - Axis Y coordinate of vertex 2
     * @param x3 - Axis X coordinate of vertex 3
     * @param y3 - Axis Y coordinate of vertex 3
     */
    private void setTriangleTestData(int x1, int y1, int x2, int y2, int x3, int y3) {
        when(Triangle.getX1()).thenReturn(x1);
        when(Triangle.getY1()).thenReturn(y1);
        when(Triangle.getX2()).thenReturn(x2);
        when(Triangle.getY2()).thenReturn(y2);
        when(Triangle.getX3()).thenReturn(x3);
        when(Triangle.getY3()).thenReturn(y3);
    }

    /**
     * Get array of triangle vertices
     * @param triangle - Triangle
     * @return Array of vertices coordinates
     */
    private Point[] getVertices(ITriangle triangle) {
        Point[] result = new Point[3];

        result[0] = new Point(triangle.getX1(), triangle.getY1());
        result[1] = new Point(triangle.getX2(), triangle.getY2());
        result[2] = new Point(triangle.getX3(), triangle.getY3());

        return result;
    }

    /**
     * Method return coordinates of the vector, adjusted by coordinates of two points
     * @param a - coordinates of point 1
     * @param b - coordinates of point 2
     * @return Coordinates of vector vertex
     */
    private Point getVectorByVertices(Point a, Point b) {
        return new Point(b.getX() - a.getX(), b.getY() - a.getY());
    }

    /**
     * Check orthogonality of two vectors
     * @param vectorA - coordinates of vector vertex 1
     * @param vectorB - coordinates of vector vertex 2
     * @return If vectors are orthogonal - return True, otherwise - False
     */
    private boolean isVectorsOrthogonal(Point vectorA, Point vectorB) {
        return vectorA.getX()*vectorB.getX() + vectorA.getY()*vectorB.getY() == 0;
    }

    /**
     * Calculate tangent of vector angle
     * @param vectorX - Axis X coordinate of vector
     * @param vectorY - Axis Y coordinate of vector
     * @return Tangent of vector angle
     */
    private double GetTangent(int vectorX, int vectorY) {
        return (double)(vectorY)/(vectorX);
    }
}
