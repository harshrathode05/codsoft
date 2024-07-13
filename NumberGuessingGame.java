import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between 1 and 100.");
        
        int score = 0; // To keep track of rounds won
        
        boolean playAgain = true;
        while (playAgain) {
            int targetNumber = random.nextInt(100) + 1; // Generate random number between 1 and 100
            int attempts = 0;
            boolean guessedCorrectly = false;
            
            System.out.println("\nRound " + (score + 1));
            System.out.println("----------------------------");
            
            while (!guessedCorrectly && attempts < 10) { // Limiting attempts to 10
                System.out.print("Enter your guess (between 1 and 100): ");
                int guess = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                
                attempts++;
                
                if (guess < targetNumber) {
                    System.out.println("Your guess is too low. Try again.");
                } else if (guess > targetNumber) {
                    System.out.println("Your guess is too high. Try again.");
                } else {
                    System.out.println("Congratulations! You've guessed the number " + targetNumber + " correctly in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    score++; // Increase score for correct guess
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("Sorry, you did not guess the number. The correct number was " + targetNumber);
            }
            
            System.out.print("Do you want to play again? (yes/no): ");
            String playChoice = scanner.nextLine().toLowerCase();
            
            if (!playChoice.equals("yes")) {
                playAgain = false;
            }
        }
        
        System.out.println("\nGame Over!");
        System.out.println("You won " + score + " rounds.");
        
        scanner.close();
    }
}
