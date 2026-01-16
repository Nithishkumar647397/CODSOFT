import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int min = 1;
        int max = 100;
        int maxAttempts = 5;
        int attemptsUsed = 0;

        int targetNumber = random.nextInt(max - min + 1) + min;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess a number between " + min + " and " + max);
        System.out.println("You have " + maxAttempts + " attempts.\n");

        while (attemptsUsed < maxAttempts) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();

            // Input validation
            if (userGuess < min || userGuess > max) {
                System.out.println("Please enter a number between " + min + " and " + max);
                continue;
            }

            attemptsUsed++;

            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You guessed the number in " 
                                   + attemptsUsed + " attempts.");
                break;
            } else if (userGuess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }

        if (attemptsUsed == maxAttempts) {
            System.out.println("\nGame Over! The number was: " + targetNumber);
        }

        scanner.close();
    }
}
