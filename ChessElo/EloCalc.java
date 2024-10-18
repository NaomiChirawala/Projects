import java.lang.Math;

/**
 * A Chess Elo Calculator program.
 * Purdue University -- CS18000 -- Fall 2024 -- Project 1 -- supporting class
 *
 * @author Naomi Chirawala, L34
 * @version September 29, 2024
 */

public class EloCalc {
    private double p1OldElo;
    private double p2OldElo;
    private double p1R;
    private double p2R;
    private double p1E;
    private double p2E;
    private int p1NewElo;
    private int p2NewElo;
    private String result;

    public EloCalc(double p1OldElo, String result, double p2OldElo) {
        this.p1OldElo = p1OldElo;
        this.p2OldElo = p2OldElo;
        this.result = result;
    }

    public void calcElo() {
        p1R = java.lang.Math.pow(10, (this.p1OldElo / 400.00));
        p2R = java.lang.Math.pow(10, (this.p2OldElo / 400.00));
        p1E = p1R / (p1R + p2R);
        p2E = p2R / (p1R + p2R);

        int intP1OldElo = (int) p1OldElo;
        int intP2OldElo = (int) p2OldElo;
        if (result.equals("W")) {
            p1NewElo = (int) (intP1OldElo + 25 * (1 - p1E));
            p2NewElo = (int) (intP2OldElo + 25 * (0 - p2E));
        } else if (result.equals("L")) {
            p1NewElo = (int) (intP1OldElo + 25 * (0 - p1E));
            p2NewElo = (int) (intP2OldElo + 25 * (1 - p2E));
        }
    }

    public int getP1NewElo() {
        return this.p1NewElo;
    }

    public int getP2NewElo() {
        return this.p2NewElo;
    }
}
