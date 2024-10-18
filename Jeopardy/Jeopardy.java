import java.util.Scanner;
import java.util.ArrayList;

/**
 * A Jeopardy Game program.
 * Purdue University -- CS18000 -- Fall 2024 -- Homework 06 -- Challenge
 *
 * @author Naomi Chirawala, L34
 * @version September 26, 2024
 */

public class Jeopardy {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Jeopardy!");
        System.out.println("Moderator - Please enter the categories, point values, questions, and answers below.");

        String category = "";
        int points = 0;
        String clue = "";
        String correctResponse = "";
        boolean completed  = false;
        ArrayList<ClueInfo> allClues = new ArrayList<ClueInfo>();
        int j;


        allClues.add(new ClueInfo("Geography", 100, "This is the capital of Florida.", "What is Tallahasee?"));
        allClues.add(new ClueInfo("Sports", 200, "This is the only college basketball team to make the Elite 8 as a 15 seed.", "What is Saint Peter's?"));
        allClues.add(new ClueInfo("English", 300, "Here and hear are this type of words.", "What are homophones?"));
        allClues.add(new ClueInfo("Calendar", 400, "February had this many days in 2000.", "What is 28?"));
        allClues.add(new ClueInfo("Game Shows", 500, "This person was the original host of Jeopardy.", "Who is Art Fleming?"));

        for (ClueInfo k : allClues) {
            System.out.print(k.getCategory() + " ");
            System.out.print(k.getPoints() + " ");
            System.out.print(k.getClue() + " ");
            System.out.print(k.getCorrectResponse() + " ");
            System.out.print(k.isCompleted() + "\n");
        }


        String choicePlayer = "Player 1";
        String categoryChoice = "";
        int choiceIndex = 0;
        String p1Response = "";
        String p2Response = "";
        int p1Score = 0;
        int p2Score = 0;
        boolean p1right = false;
        boolean p2right = false;

        for (int i = 1; i <= 5; i++) {
            j = 0;
            p1right = false;
            p2right = false;
            System.out.printf("%s, choose a category for the next clue. Below are the options:\n", choicePlayer);

            for  (ClueInfo indiClue : allClues) {
                System.out.printf("%s for %d\n", indiClue.getCategory(), indiClue.getPoints());
            }
            categoryChoice = scan.nextLine();
            for (ClueInfo indiClue : allClues) {
                if (indiClue.getCategory().equals(categoryChoice)) {
                    choiceIndex = j;
                }
                j++;
            }

            System.out.printf("The clue is: %s\n", (allClues.get(choiceIndex )).getClue());
            System.out.println("Player 1, enter your response");
            p1Response = scan.nextLine();
            System.out.println("Player 2, enter your response");
            p2Response = scan.nextLine();

            if (p1Response.equals(allClues.get(choiceIndex).getCorrectResponse())) {
                p1Score += allClues.get(choiceIndex).getPoints();
                System.out.println("Player 1 is correct!");
                p1right = true;
            }

            if (p2Response.equals(allClues.get(choiceIndex).getCorrectResponse())) {
                p2Score += allClues.get(choiceIndex).getPoints();
                System.out.println("Player 2 is correct!");
                p2right = true;
            }

            if (p1right == true && p2right == false) {
                choicePlayer = "Player 1";
            } else if (p1right == false && p2right == true) {
                choicePlayer = "Player 2";
            }

            System.out.printf("Player 1 current score: %d\n", p1Score);
            System.out.printf("Player 2 current score: %d\n", p2Score);
            allClues.remove((allClues.get(choiceIndex)));

        }

        if (p1Score > p2Score) {
            System.out.print("Congratulations Player 1! You are the winner! ");
            System.out.printf("The final score is %d-%d!", p1Score, p2Score);
        } else if (p2Score > p1Score) {
            System.out.print("Congratulations Player 2! You are the winner! ");
            System.out.printf("The final score is %d-%d!", p2Score, p1Score);
        }
    }
}
