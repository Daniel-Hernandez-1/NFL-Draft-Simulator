// Daniel Hernandez
// 9/14/15
// CSC 311
// Project 1

// Importing required classes: Arrays, Collections, and Random
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class NFLDraftSimulator //begin class
{   
    public static void main (String[] args) // Main method
    {
        // Array declarations with elements already defined.
        String[] teams = {"Baltimore Ravens","Cincinnati Bengals","Cleaveland Browns","Pittsburgh Steelers",
                          "Chicago Bears","Detroit Lions","Green Bay Packers","Minnesota Vikings"};
        String[] players = {"Aaron Rogers","Tom Brady","Ben Roethlisberger","Matt Ryan","Andrew Luck", "Peyton Manning", "Ryan Tannehill", "Drew Brees",
                            "Russell Wilson","Tony Romo","Sam Bradford","Eli Manning","Carson Palmer","Teddy Bridgewater","Philip Rivers","Cam Newton",
                            "Matthew Stafford","Blake Bortles","Jay Cutler","Andy Dalton","Colin Kaepernick","Joe Flacco","Tyrod Taylor","Alex Smith",
                            "Mason Crosby","Cody Parkey","Justin Tucker","Matt Bryan","Dan Bailey","Matt Prater","Mike Nugent","Josh Scobee"};
        
        // Calls to methods, see below, with parameters being passed in as required.
        teamSort(teams);
        playerSort(players);
        draftDay();
    } // end main method
    
    /* teamSort method takes in an array of strings. Method assigns random win
    percentages to each team and then ranks the teams (sorts them) based on the
    order of the percentages from least to greatest. */
    public static void teamSort(String[] teams) // begin teamSort method
    {   
        System.arraycopy(teams, 0, teamWinLoss[0], 0, 8); // array copying.
        
        // For loop assigns a random win percentage number from 0 to 100 to the teams
        for (int i = 0; i < 8; i++)
        {
            winlosspercent = numberGenerator.nextInt((int) ((101-0) * 100 + 1) + 0*100) / 100.0; //RNG
            teamWinLoss[1][i] = String.valueOf(winlosspercent); // Converts integer value to String.
            
            System.out.println("The " + teamWinLoss[0][i] + " won " + teamWinLoss[1][i] + " of their games last season!"); //Output
        }
        
        // Array to hold numbers, this helps in the sorting of the teams by win percentage
        for(int i = 0; i < 8; i++)
        {
            winLossNumbers[i] = Double.parseDouble(teamWinLoss[1][i]);
        }
        
        Arrays.sort(winLossNumbers); // Sorts the array of numbers, used for ranking the teams
        
        System.out.println("\nBased on win percentage: \n");
        
        // Loop ranks each team based on win percentage and also outputs the ranks.
        for(int i = 0; i < 8; i++)
        {   
            for(int j = 0; j < 8; j++)
            {
                if(Double.parseDouble(teamWinLoss[1][i]) == winLossNumbers[j])
                {
                    rankedTeams[j] = teamWinLoss[0][i];
                    System.out.println("The " + teamWinLoss[0][i] + " is ranked " + (j+1));
                }
            }
        }
        
        System.out.println(); //blank line
    } // end teamSort method
    
    /* playerSort method takes in an array of Strings. This method randomly
    assigns a round to each player in which they can be drafted. Then based on
    the assigned round, players are grouped into four different arrays, one for
    each round.
    */
    public static void playerSort(String[] players) // begin playerSort method
    {   
        System.arraycopy(players, 0, playerRounds[0], 0, 32); // array copy.

        /* Do-while loop with condition ensure that all players receive a random
        round number. The For loop inside takes care of randomly assigning
        round numbers to each player.
        */
        do 
        {
            for (int i = 0; i < 32; i++)
            {   
                round = numberGenerator.nextInt(4) + 1;
            
                if(round == 1 && count1 < 8 && playerRounds[1][i] == null)
                {
                    playerRounds[1][i] = String.valueOf(round);
                    ++count1;
                }
            
                if(round == 2 && count2 < 8 && playerRounds[1][i] == null)
                {
                    playerRounds[1][i] = String.valueOf(round);
                    ++count2;
                }

                if(round == 3 && count3 < 8 && playerRounds[1][i] == null)
                {
                    playerRounds[1][i] = String.valueOf(round);
                    ++count3;
                }

                if(round == 4 && count4 < 8 && playerRounds[1][i] == null)
                {
                    playerRounds[1][i] = String.valueOf(round);
                    ++count4;
                }
            }
        }
        while (count1 < 8 || count2 < 8 || count3 < 8 || count4 < 8);
        
        // for loop prints out the results.
        for (int i = 0; i < 32; i++)
        {
            System.out.println(playerRounds[0][i] + " has been selected to drafted in round " + playerRounds[1][i]);
        }
        
        // for loop organizes the players into their appropriate round array.
        for (int i = 0; i < 32; i++)
        {
            if(Integer.parseInt(playerRounds[1][i]) == 1)
            {
                roundOnePlayers[counter1] = playerRounds[0][i];
                counter1++;
            }
            
            if(Integer.parseInt(playerRounds[1][i]) == 2)
            {
                roundTwoPlayers[counter2] = playerRounds[0][i];
                counter2++;
            }
            
            if(Integer.parseInt(playerRounds[1][i]) == 3)
            {
                roundThreePlayers[counter3] = playerRounds[0][i];
                counter3++;
            }
            
            if(Integer.parseInt(playerRounds[1][i]) == 4)
            {
                roundFourPlayers[counter4] = playerRounds[0][i];
                counter4++;
            }
        }
        
        System.out.println(); // blank line
    } // end playerSort method
    
    /* draftDay method that takes in no parameters. This method uses the
    playerPicks array which has been already prefilled with numbers 0 to 7. The
    array is shuffled for each round so that the teams in order select a random 
    player from the appropriate round array.
    */
    public static void draftDay() //begin draftDay method
    {
        Collections.shuffle(Arrays.asList(playerPicks)); // shuffle array containing numbers 0 to 7.
        
        // for loop outputs results of teams choosing a random player.
        for(int i = 0; i < 8; i++)
        {
            System.out.println("Round: 1 Team: " + rankedTeams[i] + " selected: " + roundOnePlayers[playerPicks[i]]);
        }
        
        Collections.shuffle(Arrays.asList(playerPicks)); // array shuffle again.
        System.out.println(); // blank line
        
        // for loop outputs results of teams choosing a random player.
        for(int i = 0; i < 8; i++)
        {
            System.out.println("Round: 2 Team: " + rankedTeams[i] + " selected: " + roundTwoPlayers[playerPicks[i]]);
        }
        
        Collections.shuffle(Arrays.asList(playerPicks)); // array shuffle again.
        System.out.println(); // blank line
        
        // for loop outputs results of teams choosing a random player.
        for(int i = 0; i < 8; i++)
        {
            System.out.println("Round: 3 Team: " + rankedTeams[i] + " selected: " + roundThreePlayers[playerPicks[i]]);
        }
        
        Collections.shuffle(Arrays.asList(playerPicks)); // array shuffle again, for last time.
        System.out.println(); // blank line.
        
        // for loop outputs results of teams choosing a random player.
        for(int i = 0; i < 8; i++)
        {
            System.out.println("Round: 4 Team: " + rankedTeams[i] + " selected: " + roundFourPlayers[playerPicks[i]]);
        }
    } // end draftDay method.
    
    // the following are all variables used for this program.
    private static final String[][] teamWinLoss = new String[2][8];
    private static final Random numberGenerator = new Random();
    private static double winlosspercent;
    private static final double[] winLossNumbers = new double[8];
    private static final String[] rankedTeams = new String[8];
    private static final String [][] playerRounds = new String[2][32];
    private static int round;
    private static final String[] roundOnePlayers = new String[8];
    private static final String[] roundTwoPlayers = new String[8];
    private static final String[] roundThreePlayers = new String[8];
    private static final String[] roundFourPlayers = new String[8];
    private static int count1 = 0;
    private static int count2 = 0;
    private static int count3 = 0;
    private static int count4 = 0;
    private static int counter1 = 0;
    private static int counter2 = 0;
    private static int counter3 = 0;
    private static int counter4 = 0;
    private static final Integer[] playerPicks = {0,1,2,3,4,5,6,7};
} //end class NFLDraftSimulator