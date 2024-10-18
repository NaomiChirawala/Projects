/**
 * A Clue Class program.
 * Purdue University -- CS18000 -- Fall 2024 -- Homework 06 -- Challenge
 *
 * @author Naomi Chirawala, L34
 * @version September 26, 2024
 */

public class ClueInfo {
    private String category;
    private int points;
    private String clue;
    private String correctResponse;
    private boolean completed;

    public ClueInfo(String category, int points, String clue, String correctResponse) {
        this.category = category;
        this.points = points;
        this.clue = clue;
        this.correctResponse = correctResponse;
        this.completed = false;
    }

    public String getCategory() {
        return category;
    }

    public int getPoints() {
        return points;
    }

    public String getClue() {
        return clue;
    }

    public String getCorrectResponse() {
        return correctResponse;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
