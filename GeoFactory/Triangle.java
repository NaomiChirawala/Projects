/**
 * A Geometrical Triangle Creation program.
 * Purdue University -- CS18000 -- Fall 2024 -- Project 2 -- Challenge
 *
 * @author Naomi Chirawala, L34
 * @version October 13, 2024
 */

public class Triangle {
    private Point vertexA;
    private Point vertexB;
    private Point vertexC;
    private UnitVector surfaceNormal;
    private static final double EPSILON = 0.0001;

    public Triangle(Point vertA, Point vertB, Point vertC) {
        this.vertexA = vertA;
        this.vertexB = vertB;
        this.vertexC = vertC;
        UnitVector ab = new UnitVector(this.vertexA, this.vertexB);
        UnitVector ac = new UnitVector(this.vertexA, this.vertexC);
        surfaceNormal = ab.crossProduct(ac);
    }

    public Triangle() {
        vertexA = new Point();
        vertexB = new Point();
        vertexC = new Point();
        surfaceNormal = new UnitVector();
    }

    public Point getVertexA() {
        return vertexA;
    }

    public Point getVertexB() {
        return vertexB;
    }

    public Point getVertexC() {
        return vertexC;
    }

    public UnitVector getSurfaceNormal() {
        return surfaceNormal;
    }

    public Point[] getVertices() {
        Point[] vertices = new Point[3];
        vertices[0] = this.vertexA;
        vertices[1] = this.vertexB;
        vertices[2] = this.vertexC;

        return vertices;
    }

    public boolean compareWith(Triangle triangle) {
        boolean v1 = this.vertexA.compareWith(triangle.getVertexA());
        boolean v2 = this.vertexB.compareWith(triangle.getVertexB());
        boolean v3 = this.vertexC.compareWith(triangle.getVertexC());
        boolean sn = this.surfaceNormal.compareWith(triangle.getSurfaceNormal());

        return v1 && v2 && v3 && sn;
    }

    public String toString() {

        String finalString = "";
        boolean cond1 = vertexA.compareWith(vertexB) || vertexB.compareWith(vertexC) || vertexC.compareWith(vertexA);
        boolean cond2 = (surfaceNormal.toString()).equals("<InvalidUnitVector>");

        if (cond1 || cond2) {
            return "[InvalidTriangle]";
        } else {
            finalString = "[A" + vertexA.toString() + "; B" + vertexB.toString() + "; C" + vertexC.toString() + "; ";
            finalString += "N" + surfaceNormal.toString() + "]";

            return finalString;
        }
    }
}
