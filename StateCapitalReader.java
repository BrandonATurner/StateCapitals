/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StateCapitals2;

// Importing the things I will need for my program.
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 *
 * @author andri
 */
public class StateCapitalReader {
    
    // Creating my main method that throws an Exception to allow me to read in files.
    public static void main(String[] args) throws Exception {
        
        // Initiating my random and Scanner sc to take in user input.
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        
        //Creatubg a HashMap to store states and capitals.
        Map<String, String> stateCapitals = new HashMap<>();
        
        // Initiatiting a new Scanner scan and reading in the StateCapitals text file.
        Scanner scan = new Scanner(
                new BufferedReader(new FileReader("StateCapitals.txt")));
        
        // Created a String array to take in the state and capital after it has been split at '::' for each line.
        String[] stateCap = new String[2];
        
        /* While text has next line, scanning in line splitting at the '::' and storing placing in my stateCap String array
            after it is in the string array storing both indexes in stateCapitals HashMap*/
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();
            stateCap = currentLine.split("::");
            stateCapitals.put(stateCap[0], stateCap[1]);
        }
        
        // Creatint a keySet for stateCapitals HashMap
        Set<String> keys = stateCapitals.keySet();
        
        // Creating an ArrayList of states and using keyset and a foreach loop to add each key to the states ArrayList.
        ArrayList<String> states = new ArrayList<>();
       for(String k : keys){
            states.add(k);
        }
       
       // Sorting the states in the ArrayList to get an alphabetical order.
       Collections.sort(states);
       
       // Printing out how many states and capitals are loaded.
       System.out.println(stateCapitals.size() + " STATES & CAPITALS ARE LOADED.");
       System.out.println("=======");
       
       // Using a for loop and states size to print out each state.
       for(int i = 0 ; i < states.size(); i++){
           // Using an if statement with modulus 10 to print a new line every 10 states.
           if(i%10==0){
               System.out.println();
           }
           // Using an if statement to print the very last state without the comma.
           if(i==states.size()-1){
               System.out.println(states.get(i));
           } 
           else{ // Printing States
           System.out.print(states.get(i) + ", ");
           }
       }
           
        // Using the Random method to pick 1 of the 50 states at random and storing in currentState variable.
        String currentState = states.get(rand.nextInt(50));
        String currentCapital = stateCapitals.get(currentState); // Storing currentState's capital in currentCapital variable.
        System.out.println();
        
        // Requesting input and storing in answer variable to answer what is the capital of my currentState
        System.out.println("READY TO TEST YOUR KNOWLEDGE? WHAT IS THE CAPITAL OF '" + currentState + "'?");
        String answer = sc.nextLine();
        
        // Using an if-else structure to print if the answer was correct or wrong, and removing currentState from ArrayList.
        if(answer.equals(currentCapital)){
            System.out.println("NICE WORK! " + currentCapital + " IS CORRECT!");
        } else{
            System.out.println("Wrong it's: " + currentCapital);
        }
        states.remove(currentState);
        
        int score = 0; // Created an int variable to keep score.
        System.out.println("\nLet's play a game, how many states would you like to guess? ");
        int guesses = sc.nextInt();
        
        // Created a for loop to play a game for the requested amount of guess.
        for(int i = 0 ; i < guesses; i++){
            
            /* Using random method minus i to pick a random state without giving an
                index out of bounds exception since each loop is removing a state.
            */
            currentState = states.get(rand.nextInt(49 - i)); 
            currentCapital = stateCapitals.get(currentState);
            System.out.println("\nWHAT IS THE CAPITAL OF '" + currentState + "'?");
            answer = sc.next();
            sc.nextLine();
            if(answer.equals(currentCapital)){ // If correct increment score by 1.
            System.out.println("NICE WORK! " + currentCapital + " IS CORRECT!");
            score++;
            } 
            else{ // If wrong print "Wrong" and corrct answer and decrement score by 1.
            System.out.println("Wrong it's: " + currentCapital);
            score--;
            }
            states.remove(currentState); // Removed currentState so will not be duplicates.
        }
        
        // Printing score and "Thanks for playing".
        System.out.println("\nYOUR SCORE IS: " + score);
        System.out.println("THANKS FOR PLAYING!");
    }    
}
