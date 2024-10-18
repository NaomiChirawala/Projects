/**
 * A Geometrical Point Creation program.
 * Purdue University -- CS18000 -- Fall 2024 -- Project 2 -- Challenge
 *
 * @author Naomi Chirawala, L34
 * @version October 13, 2024
 */

public class Point {
    private double x;
    private double y;
    private double z;
    private static final double EPSILON = 0.0001;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public boolean compareWith(Point point) {
        double xDiff = Math.abs(this.x - point.getX());
        double yDiff = Math.abs(this.y - point.getY());
        double zDiff = Math.abs(this.z - point.getZ());

        return (xDiff <= EPSILON) && (yDiff <= EPSILON) && (zDiff <= EPSILON);
    }

    public String toString() {
        String xString = String.format("%.3f", this.x);
        String yString = String.format("%.3f", this.y);
        String zString = String.format("%.3f", this.z);
        String finalString = "(x" + xString + ", y" + yString + ", z" + zString + ")";

        return finalString;
    }
}
