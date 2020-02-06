/**
 * Name: Alfonso Beltran
 * Due Date: February 6th, 2020
 * Objective: Write and test a method that emulates a general finite automaton. The input to this method should include:
 *
 * The number N of states q0, ..., qN − 1; we assume that q0 is the start state;
 * The number M of symbols s0, ... sM − 1;
 * An integer array state[n][m] that describes to what state the finite automaton moves if it was in the state qn and sees the symbol sm;
 * A  boolean array final[n] that tells us, for each state, whether this state is final or not.
 */

package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)throws Exception {

        Scanner scanner = new Scanner(System.in);

        int N;  //Integer that declares the number of STATES
        int M;  // Integer that declares the number of SYMBOLS

        //Prompt user for N and M
        System.out.print("Please enter number of STATES: ");
        N = scanner.nextInt();
        System.out.print("Please enter number of SYMBOLS: ");
        M = scanner.nextInt();
        scanner.nextLine();

        //Create arrays that become the states, symbols and final states
        int[] q = new int[N];
        String[] s = new String[M];
        int[][] state = new int[N][M];
        boolean[] finals = new boolean[N];

        //populate q with integers
        for(int i=0; i<N ; i++){
            q[i]=i;
        }

        //populate symbol array by entering and assigning all symbols
        for (int i=0; i<M; i++){
            System.out.print("Please enter character for symbol "+i+": ");
            s[i] = scanner.nextLine();
        }

        //Populating the delta table by assigning which state to go to when seeing a specific symbol
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                int holder; //variable that holds temporary integer to assign to state.

                System.out.print("Enter to what state q" + i +" goes to when it sees " + s[j] +": ");
                holder = scanner.nextInt();
                state[i][j] =  holder;
            }
        }
        System.out.println();

        System.out.println("DELTA TABLE");
        //The following loops are used to display the delta table
        for (int i=0; i<N; i++){        //This loop prints out the states in one line
            System.out.print("\tq"+q[i]);
        }
        System.out.println();
        for (int i=0; i<M; i++){        //This loop builds the rest of the table
            System.out.print(s[i]+"\t");
            for (int j=0; j<N; j++){

                System.out.print(state[j][i] + "\t");

            }
            System.out.println();
        }
        System.out.println();

        //Set all states that are considered final states
        boolean continueToken = true;
        int userFinal;
        while(continueToken){
            System.out.print("Enter a state within bounds that will be a final state, enter -1 to exit: ");
            int userInput = scanner.nextInt();
            if(userInput != -1) {
                userFinal = userInput;
                finals[userFinal] = true;
            }else
                continueToken = false;

        }

        //Enter a string of characters to see if they are accepted by the automaton
        boolean exitLoop = false;
        while(!exitLoop){       //This enables the user to enter as many string as they want
            int userInputLen;

            /**
             * This loop allows the user to exit the loop and to determine the length of the string
             * of characters that they want to input
             */
            System.out.println();
            System.out.print("Please enter length of input or -1 to exit: ");
            userInputLen = scanner.nextInt();
            scanner.nextLine();

            if(userInputLen == -1){
                exitLoop = true;
            }else{
                int currentState = 0;
                for (int i=0; i<userInputLen; i++){         //Ask for user input
                    String userInput;
                    System.out.print("Enter symbol: ");
                    userInput = scanner.nextLine();

                    boolean symExists = checkSymbol(s, userInput);      //Check if symbol exists
                    if (symExists){
                        int newM = getSymbolLocation(s, userInput);
                            //System.out.println("Old current State in loop: " + currentState);     -old state Check
                        currentState = state[currentState][newM];
                            //System.out.println("new M is: " + newM);                              -new M check
                            //System.out.println("Current state in loop is: " + currentState);      -new state check
                    }else{
                        System.out.println("Symbol does not exists therefore it is not accepted by the automaton.");
                        i = userInputLen;   //this forces the loop to end and to start a new series of characters
                    }
                }//end for loop
                System.out.println("The current is: " + currentState);
                System.out.println();
                //Check if string of characters ends on final state
                if(finals[currentState])
                    System.out.println("Combination of symbols is ACCEPTED by automaton");
                else
                    System.out.println("Combination of symbols is NOT ACCEPTED by automaton");

            }

        }
    }

    /**
     * This method is used to find the location of a specific symbol
     * @param s         The array of symbols passed down by the main method
     * @param userInput The input given by the user
     * @return          an integer location which gives the location of userInput in the symbol set
     */
    private static int getSymbolLocation(String[] s, String userInput) {
        int location = 0;

        boolean found = false;
        for (int i=0; i<s.length; i++) {
            found = s[i].equals(userInput);
            if (found)
                location = i;
        }
        return location;
    }

    /**
     * This method is used to make sure that the user input is in the set of symbols that were
     * previously set.
     * @param symbol  The array of symbols given by the main method
     * @param token   The user input
     * @return        Boolean symExists that is true is token is in symbol set
     */
    public static boolean checkSymbol(String[] symbol, String token){
        boolean symExists = false;
        for (String s : symbol) {
            symExists = s.equals(token);
            if (symExists)
                return symExists;
        }
        return symExists;
    }
}
