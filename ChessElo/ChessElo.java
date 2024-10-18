import java.util.Scanner;

/**
 * A Chess Elo Calculator program.
 * Purdue University -- CS18000 -- Fall 2024 -- Project 1
 *
 * @author Naomi Chirawala, L34
 * @version September 29, 2024
 */

public class ChessElo {

    public static final String WELCOME_MESSAGE = "Welcome to the Chess Elo Calculator!";
    public static final String MAIN_MENU = "Please Select an Operation\n" + "1 - Single Match\n" +
            "2 - Tournament Results\n" + "3 - Exit";

    public static final String SINGLE_MATCH = "Please Enter Player 1's Elo followed by a hyphen '-' " +
            "then the Outcome followed by Player 2's Elo.";
    public static final String TOURNAMENT_RESULTS = "Please Enter the Tournament String to be Calculated.";
    public static final String MATCH_OUTCOME = "The Result of the Single Match is ";
    public static final String TOURNAMENT_OUTCOME = "The Final Tournament Results are ";
    public static final String CONFIRMATION = "Are You Sure You Want to Exit?";
    public static final String INVALID_INPUT = "Invalid Input! Try again";
    public static final String THANK_YOU = "Thank You For Using the Chess Elo Calculator!";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(WELCOME_MESSAGE);
        while (true) {
            System.out.println(MAIN_MENU);
            if (scan.hasNextInt()) {
                int menu = scan.nextInt();
                scan.nextLine();
                if (!((menu == 1) || (menu == 2) || (menu == 3))) {
                    System.out.println(INVALID_INPUT);
                } else if (menu == 1) {
                    System.out.println(SINGLE_MATCH);
                    String single = scan.nextLine();
                    String strP1EloInitial = "";
                    String strP2EloInitial = "";
                    String singleOutcome = "";
                    for (int i = 0 ; i < single.length() ; i++) {
                        if (String.valueOf(single.charAt(i)).equals("-")) {
                            singleOutcome = single.substring(i + 1, i + 2);
                            strP2EloInitial = single.substring(i + 2);
                            break;
                        }
                        strP1EloInitial = strP1EloInitial + String.valueOf(single.charAt(i));
                    }
                    double p1EloInitial = Double.parseDouble(strP1EloInitial);
                    double p2EloInitial = Double.parseDouble(strP2EloInitial);
                    EloCalc singleMatch = new EloCalc(p1EloInitial, singleOutcome, p2EloInitial);
                    singleMatch.calcElo();
                    String p1NewElo = Integer.toString(singleMatch.getP1NewElo());
                    String p2NewElo = Integer.toString(singleMatch.getP2NewElo());
                    System.out.println(MATCH_OUTCOME + p1NewElo + '-' + p2NewElo);

                } else if (menu == 2) {
                    System.out.println(TOURNAMENT_RESULTS);
                    String tournamentResult = scan.nextLine();

                    String strP1EloInitial = "";
                    for (int i = 0 ; i < tournamentResult.length() ; i++) {
                        if (String.valueOf(tournamentResult.charAt(i)).equals("-")) {
                            //tournOutcome = tournamentResult.substring(i + 1, i + 2);
                            tournamentResult = tournamentResult.substring(i + 1);
                            break;
                        }
                        strP1EloInitial = strP1EloInitial + String.valueOf(tournamentResult.charAt(i));
                    }
                    double player = Double.parseDouble(strP1EloInitial);
                    //tournamentResult = tournamentResult.substring(4) + '-';
                    String indiResult = "";
                    String strOpp = "";
                    double opp = 0.0;
                    String finalResult = "";
                    boolean check = false;

                    while (true) {
                        strOpp = "";
                        indiResult = tournamentResult.substring(0, 1);
                        tournamentResult = tournamentResult.substring(1);

                        for (int i = 0 ; i < tournamentResult.length() ; i++) {
                            if (String.valueOf(tournamentResult.charAt(i)).equals("-")) {
                                tournamentResult = tournamentResult.substring(i + 1);
                                break;
                            }
                            if (tournamentResult.length() < 6) {
                                check = true;
                            }
                            strOpp = strOpp + String.valueOf(tournamentResult.charAt(i));
                        }
                        opp = Double.parseDouble(strOpp);
                        EloCalc tournament = new EloCalc(player, indiResult, opp);
                        tournament.calcElo();
                        finalResult = finalResult + '-' + tournament.getP2NewElo();
                        player = (double) tournament.getP1NewElo();

                        if (check) {
                            break;
                        }

                    }
                    String finalPlayer = Integer.toString((int) player);
                    finalResult = finalPlayer + finalResult.substring(0, finalResult.length());
                    System.out.println(TOURNAMENT_OUTCOME + finalResult);

                } else if (menu == 3) {
                    boolean finalExit = false;
                    while (true) {
                        System.out.println(CONFIRMATION);
                        String exit = scan.nextLine();
                        boolean c1 = exit.equalsIgnoreCase("no") || exit.equalsIgnoreCase("n");
                        boolean c2 = exit.equalsIgnoreCase("yes") || exit.equalsIgnoreCase("y");
                        if (c1 || c2) {
                            if (c1) {
                                break;
                            } else if (c2) {
                                finalExit = true;
                                break;
                            }
                        } else {
                            System.out.println(INVALID_INPUT);
                        }
                    }
                    if (finalExit) {
                        System.out.println(THANK_YOU);
                        break;
                    }
                }
            } else {
                scan.nextLine();
                System.out.println(INVALID_INPUT);
            }
        }

    } // End main
} // End class
