/**
 * A Yahtzee Player program.
 * Purdue University -- CS18000 -- Fall 2024 -- Homework 07 -- Challenge
 *
 * @author Naomi Chirawala, L34
 * @version October 8, 2024
 */


public class YahtzeePlayer {
    private int[][] rollResults;
    private int total;
    private int fourOfAKindTotal;
    private boolean fullHouse;
    private boolean largeStraight;
    private boolean yahtzee;

    public YahtzeePlayer(int[][] rollResults) {
        this.rollResults = rollResults;
        this.total = 0;
        this.fourOfAKindTotal = 0;
        this.fullHouse = false;
        this.largeStraight = false;
        this.yahtzee = false;
    }

    public int getFourOfAKindTotal() {
        return fourOfAKindTotal;
    }

    public boolean hasFullHouse() {
        return fullHouse;
    }

    public boolean hasLargeStraight() {
        return largeStraight;
    }

    public boolean hasYahtzee() {
        return yahtzee;
    }

    public int getTotal() {
        return total;
    }

    public void checkFourOfAKind() {
        int num1 = 0;
        int num2 = 0;
        int count1 = 0;
        int count2 = 0;
        int fourSum = 0;
        boolean fourYes = false;
        for (int i = 0; i < rollResults.length; i++) {
            num1 = 0;
            num2 = 0;
            count1 = 0;
            count2 = 0;
            fourSum = 0;
            for (int j = 0; j < rollResults[i].length; j++) {
                num1 = rollResults[i][0];
                for (int k = 0; k < rollResults[i].length; k++) {
                    if (rollResults[i][k] != num1) {
                        num2 = rollResults[i][k];
                    }
                }
                for (int k = 0; k < rollResults[i].length; k++) {
                    if (rollResults[i][k] == num1) {
                        count1++;
                    }
                    if (rollResults[i][k] == num2) {
                        count2++;
                    }
                }
                if ((count1 == 1 && count2 == 4) || (count1 == 4 && count2 == 1)) {
                    for (int m = 0; m < rollResults[i].length; m++) {
                        fourSum += rollResults[i][m];
                    }
                    this.fourOfAKindTotal = fourSum;
                    fourYes = true;
                    break;
                }
            }
            if (fourYes) {
                break;
            }
        }
    }

    public void checkFullHouse() {
        int num1 = 0;
        int num2 = 0;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < rollResults.length; i++) {
            num1 = 0;
            num2 = 0;
            count1 = 0;
            count2 = 0;
            for (int j = 0; j < rollResults[i].length; j++) {
                num1 = rollResults[i][0];
                for (int k = 0; k < rollResults[i].length; k++) {
                    if (rollResults[i][k] != num1) {
                        num2 = rollResults[i][k];
                    }
                }
                for (int k = 0; k < rollResults[i].length; k++) {
                    if (rollResults[i][k] == num1) {
                        count1++;
                    }
                    if (rollResults[i][k] == num2) {
                        count2++;
                    }
                }
                if ((count1 == 2 && count2 == 3) || (count1 == 3 && count2 == 2)) {
                    fullHouse = true;
                }
            }
        }
    }

    public void checkLargeStraight() {
        int count = 0;
        for (int i = 0; i < rollResults.length; i++) {
            count = 0;
            for (int j = 0; j < (rollResults[i].length - 1); j++) {
                if (rollResults[i][j] == rollResults[i][j + 1] - 1) {
                    count++;
                }
            }
            if (count == 4) {
                largeStraight = true;
            }
        }
    }

    public void checkYahtzee() {
        int count = 0;
        for (int i = 0; i < rollResults.length; i++) {
            count = 0;
            for (int j = 0; j < (rollResults[i].length - 1); j++) {
                if (rollResults[i][j] == rollResults[i][j + 1]) {
                    count++;
                }
            }
            if (count == 4) {
                yahtzee = true;
            }
        }
    }

    public void calculateTotal() {
        total += fourOfAKindTotal;
        if (fullHouse) {
            total += 25;
        }
        if (largeStraight) {
            total += 40;
        }
        if (yahtzee) {
            total += 50;
        }
    }
}
