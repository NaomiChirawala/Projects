/**
 * A Geometrical Face Creation program.
 * Purdue University -- CS18000 -- Fall 2024 -- Project 2 -- Challenge
 *
 * @author Naomi Chirawala, L34
 * @version October 13, 2024
 */

public class Face {
    private Triangle[] mesh;
    private UnitVector surfaceNormal;

    public Face(Triangle one, Triangle two) {
        Point[] t1 = one.getVertices();
        Point[] t2 = two.getVertices();
        boolean oneSame = false;
        boolean twoSame = false;
        for (Point vertex1 : t1) {
            for (Point vertex2 : t2) {
                if (vertex1.compareWith(vertex2)) {
                    if (oneSame == true) {
                        twoSame = true;
                    } else {
                        oneSame = true;
                    }
                }
            }
        }

        boolean cond1 = oneSame && twoSame;
        boolean cond2 = (one.getSurfaceNormal()).compareWith(two.getSurfaceNormal());

        mesh = new Triangle[2];
        if (cond1 && cond2) {
            mesh[0] = one;
            mesh[1] = two;
            surfaceNormal = one.getSurfaceNormal();
        } else {
            mesh[0] = new Triangle();
            mesh[1] = new Triangle();
            surfaceNormal = new UnitVector();
        }
    }

    public Face() {
        mesh = new Triangle[2];
        mesh[0] = new Triangle();
        mesh[1] = new Triangle();
        surfaceNormal = new UnitVector();
    }

    public Triangle[] getMesh() {
        return mesh;
    }

    public UnitVector getSurfaceNormal() {
        return surfaceNormal;
    }

    public boolean compareWith(Face face) {
        boolean cond1 = this.getMesh()[0].compareWith(face.getMesh()[0]);
        boolean cond2 = this.getMesh()[1].compareWith(face.getMesh()[1]);
        boolean cond3 = this.getSurfaceNormal().compareWith(face.getSurfaceNormal());

        return cond1 && cond2 && cond3;
    }

    public String toString() {

        String finalString = "";
        boolean cond1 = ((mesh[0].toString()).equals("[InvalidTriangle]"));
        boolean cond2 = ((mesh[1].toString()).equals("[InvalidTriangle]"));
        boolean cond3 = (surfaceNormal.toString()).equals("<InvalidUnitVector>");

        if (cond1 || cond2 || cond3) {
            return "{InvalidFace}";
        } else {
            finalString = "{F" + mesh[0].toString().substring(0, mesh[0].toString().indexOf("N") - 2);
            finalString += "] " + mesh[1].toString().substring(0, mesh[1].toString().indexOf("N") - 2);
            finalString += "] N" + surfaceNormal.toString() + "}";

            return finalString;
        }
    }
}
