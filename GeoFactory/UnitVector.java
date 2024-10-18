/**
 * A Geometrical Unit Vector Creation program.
 * Purdue University -- CS18000 -- Fall 2024 -- Project 2 -- Challenge
 *
 * @author Naomi Chirawala, L34
 * @version October 13, 2024
 */

public class UnitVector {
    private double i;
    private double j;
    private double k;
    private static final double EPSILON = 0.0001;

    public UnitVector(double i, double j, double k) {
        this.i = i;
        this.j = j;
        this.k = k;

        double mag = Math.pow((Math.pow(i, 2) + Math.pow(j, 2) + Math.pow(k, 2)), 0.5);

        if (mag - 0 <= EPSILON) {
            this.i = 0.000;
            this.j = 0.000;
            this.k = 0.000;
        } else {
            mag = Math.abs(mag);
            this.i = this.i / mag;
            this.j = this.j / mag;
            this.k = this.k / mag;
        }
    }

    public UnitVector(Point start, Point end) {
        this.i = end.getX() - start.getX();
        this.j = end.getY() - start.getY();
        this.k = end.getZ() - start.getZ();

        double mag = Math.pow((Math.pow(this.i, 2) + Math.pow(this.j, 2) + Math.pow(this.k, 2)), 0.5);

        if (mag - 0.000 <= EPSILON) {
            this.i = 0.000;
            this.j = 0.000;
            this.k = 0.000;
        } else {
            mag = Math.abs(mag);
            this.i = this.i / mag;
            this.j = this.j / mag;
            this.k = this.k / mag;
        }
    }

    public UnitVector() {
        this.i = 0.000;
        this.j = 0.000;
        this.k = 0.000;
    }

    public double getI() {
        return this.i;
    }

    public double getJ() {
        return this.j;
    }

    public double getK() {
        return this.k;
    }

    public void setI(double i) {
        this.i = i;
    }

    public void setJ(double j) {
        this.j = j;
    }

    public void setK(double k) {
        this.k = k;
    }

    public boolean compareWith(UnitVector vector) {
        double iDiff = Math.abs(this.i - vector.getI());
        double jDiff = Math.abs(this.j - vector.getJ());
        double kDiff = Math.abs(this.k - vector.getK());

        return (iDiff <= EPSILON) && (jDiff <= EPSILON) && (kDiff <= EPSILON);
    }

    public UnitVector crossProduct(UnitVector b) {
        double crossI = this.j * b.getK() - this.k * b.getJ();
        double crossJ = this.k * b.getI() - this.i * b.getK();
        double crossK = this.i * b.getJ() - this.j * b.getI();

        UnitVector cross = new UnitVector(crossI, crossJ, crossK);

        double mag = Math.pow((Math.pow(cross.getI(), 2) + Math.pow(cross.getJ(), 2) + Math.pow(cross.getK(), 2)), 0.5);

        if (mag - 0 <= EPSILON) {
            cross.setI(0.000);
            cross.setJ(0.000);
            cross.setK(0.000);
        } else {
            mag = Math.abs(mag);
            cross.setI(cross.getI() / mag);
            cross.setJ(cross.getJ() / mag);
            cross.setK(cross.getK() / mag);
        }
        return cross;
    }

    public String toString() {

        String finalString = "";
        double mag = Math.pow((Math.pow(this.i, 2) + Math.pow(this.j, 2) + Math.pow(this.k, 2)), 0.5);

        boolean cond1 = (Math.abs((this.i - 0.000)) <= EPSILON);
        boolean cond2 = (Math.abs((this.j - 0.000)) <= EPSILON);
        boolean cond3 = (Math.abs((this.k - 0.000)) <= EPSILON);

        if (cond1 && cond2 && cond3) {
            return "<InvalidUnitVector>";
        } else {
            String iString = String.format("%.3f", this.i);
            String jString = String.format("%.3f", this.j);
            String kString = String.format("%.3f", this.k);
            finalString = "<" + iString + "i, " + jString + "j, " + kString + "k>";

            return finalString;
        }
    }
}
