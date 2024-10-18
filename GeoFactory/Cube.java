import java.util.*;

/**
 * A Geometrical Cube Creation program.
 * Purdue University -- CS18000 -- Fall 2024 -- Project 2 -- Challenge
 *
 * @author Naomi Chirawala, L34
 * @version October 13, 2024
 */

public class Cube {
    private Face[] mesh = new Face[]{new Face(), new Face(), new Face(), new Face(), new Face(), new Face()};
    private UnitVector[] f1T1 = new UnitVector[3];
    private UnitVector[] f1T2 = new UnitVector[3];
    private UnitVector[][] edges = new UnitVector[6][4];
    private Point[][] facePoints = new Point[6][4];
    private Point[] cubeVertices = new Point[8];

    public Cube() {
        for (int i = 0; i < 6; i++) {
            mesh[i] = new Face();
        }
    }

    public Cube(Face one, Face two, Face three, Face four, Face five, Face six) {

        Face[] allFaces = new Face[]{one, two, three, four, five, six};
        int faceNum = 0;

        for (Face face : allFaces) {

            Point[] t1 = (face.getMesh()[0]).getVertices();
            Point[] t2 = (face.getMesh()[1]).getVertices();

            Point[] uniquePoints = new Point[2];
            Point[] samePoints = new Point[2];
            int sameIndex = 0;
            int uniqueIndex = 0;
            boolean isSame = false;
            for (Point p1 : t1) {
                isSame = false;
                for (Point p2 : t2) {
                    if (p1.compareWith(p2)) {
                        samePoints[sameIndex] = p1;
                        sameIndex++;
                        isSame = true;
                    }
                }
                if (!isSame) {
                    uniquePoints[uniqueIndex] = p1;
                    uniqueIndex++;
                }
            }

            for (Point p2 : t2) {
                isSame = false;
                for (Point p1 : t1) {
                    if (p2.compareWith(p1)) {
                        isSame = true;
                    }
                }
                if (!isSame) {
                    uniquePoints[uniqueIndex] = p2;
                    uniqueIndex++;
                }
            }

            facePoints[faceNum][0] = uniquePoints[0];
            facePoints[faceNum][1] = samePoints[0];
            facePoints[faceNum][2] = uniquePoints[1];
            facePoints[faceNum][3] = samePoints[1];

            int index = 0;
            for (Point pu : uniquePoints) {
                for (Point ps : samePoints) {
                    edges[faceNum][index] = new UnitVector(pu, ps);
                    index++;
                }
            }

            faceNum++;
        }


        for (int i = 0; i < 8; i++) {
            cubeVertices[i] = new Point();
        }

        int index = 0;
        boolean found = false;
        for (Point[] eachFace : facePoints) {
            for (Point eachPoint : eachFace) {
                found = false;
                for (Point vertex : cubeVertices) {
                    if (eachPoint.compareWith(vertex)) {
                        found = true;
                        break;
                    }
                }
                try {
                    if (!found) {
                        cubeVertices[index] = eachPoint;
                        index++;
                    }
                } catch (IndexOutOfBoundsException e) {
                    for (int i = 0; i < 6; i++) {
                        mesh[i] = new Face();
                    }
                    return;
                }
            }
        }

        Point a = cubeVertices[0];
        Point b = cubeVertices[1];
        Point c = cubeVertices[2];
        Point d = cubeVertices[3];
        Point e = cubeVertices[4];
        Point f = cubeVertices[5];
        Point g = cubeVertices[6];
        Point h = cubeVertices[7];

        String pointName = "";
        String[] faceNames = new String[6];
        index = 0;
        for (Point[] eachFace : facePoints) {
            pointName = "";
            for (Point eachPoint : eachFace) {
                if (eachPoint.compareWith(a)) {
                    pointName += "A";
                } else if (eachPoint.compareWith(b)) {
                    pointName += "B";
                } else if (eachPoint.compareWith(c)) {
                    pointName += "C";
                } else if (eachPoint.compareWith(d)) {
                    pointName += "D";
                } else if (eachPoint.compareWith(e)) {
                    pointName += "E";
                } else if (eachPoint.compareWith(f)) {
                    pointName += "F";
                } else if (eachPoint.compareWith(g)) {
                    pointName += "G";
                } else if (eachPoint.compareWith(h)) {
                    pointName += "H";
                }
            }
            faceNames[index] = pointName;
            index++;
        }

        String[][] edgeNames = new String[6][4];
        index = 0;
        for (String eachFaceName : faceNames) {

            String edge1 = eachFaceName.substring(0, 2);
            char[] edgeOrder = edge1.toCharArray();
            Arrays.sort(edgeOrder);
            String sortedEdge = new String(edgeOrder);
            edgeNames[index][0] = sortedEdge;

            edge1 = eachFaceName.substring(1, 3);
            edgeOrder = edge1.toCharArray();
            Arrays.sort(edgeOrder);
            sortedEdge = new String(edgeOrder);
            edgeNames[index][1] = sortedEdge;

            edge1 = eachFaceName.substring(2);
            edgeOrder = edge1.toCharArray();
            Arrays.sort(edgeOrder);
            sortedEdge = new String(edgeOrder);
            edgeNames[index][2] = sortedEdge;

            edge1 = eachFaceName.substring(0, 1) + eachFaceName.substring(3);
            edgeOrder = edge1.toCharArray();
            Arrays.sort(edgeOrder);
            sortedEdge = new String(edgeOrder);
            edgeNames[index][3] = sortedEdge;

            index++;
        }

        Boolean[] finalCheck = new Boolean[]{false, false, false, false, false, false};
        faceNum = 0;
        Boolean[] compare = new Boolean[6];
        for (String[] oneFace : edgeNames) {
            for (int i = 0; i < 6; i++) {
                if (i == faceNum) {
                    compare[i] = true;
                } else {
                    compare[i] = false;
                }
            }

            int compInd = 0;
            int edgenum = 1;
            for (String edge1: oneFace) {
                compInd = 0;
                for (String[] twoFace : edgeNames) {
                    boolean alreadyTrue = false;
                    if (compInd == faceNum) {
                        compInd++;
                        continue;
                    }

                    for (String edge2 : twoFace) {
                        alreadyTrue = false;
                        if ((edge1.equals(edge2))) {
                            compare[compInd] = true;
                            compInd++;
                            alreadyTrue = true;
                            break;
                        }
                    }
                    if (alreadyTrue) {
                        break;
                    }
                    compInd++;
                }
                edgenum++;
            }

            int countFalse = 0;
            int falseIndex = -1;
            for (int j = 0; j < 6; j++) {
                if (!compare[j]) {
                    falseIndex = j;
                    countFalse++;
                }
            }

            if (countFalse == 1) {
                UnitVector currentSN = allFaces[faceNum].getSurfaceNormal();
                UnitVector compareSN = allFaces[falseIndex].getSurfaceNormal();
                UnitVector compareSNOpp = new UnitVector(-compareSN.getI(), -compareSN.getJ(), -compareSN.getK());
                if (currentSN.compareWith(compareSNOpp)) {
                    finalCheck[faceNum] = true;
                }
            }
            faceNum++;
        }

        boolean allTrue = true;
        for (boolean check : finalCheck) {
            if (!check) {
                allTrue = false;
                break;
            }
        }

        if (allTrue) {
            mesh[0] = one;
            mesh[1] = two;
            mesh[2] = three;
            mesh[3] = four;
            mesh[4] = five;
            mesh[5] = six;
        } else {
            for (int i = 0; i < 6; i++) {
                mesh[i] = new Face();
            }
        }
    }

    public Face[] getMesh() {
        return mesh;
    }

    public String toString() {

        String finalString = "";

        for (Face face : mesh) {
            if ((face.toString()).equals("{InvalidFace}")) {
                return "|InvalidCube|";
            }
        }

        finalString = "|C";
        for (Face face : mesh) {
            finalString += face.toString() + " ";
        }
        finalString = finalString.substring(0, finalString.length() - 1);
        finalString += "|";
        return finalString;
    }
}
