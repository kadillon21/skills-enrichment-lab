package com.pluralsight.cli.util;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInput {
    static Scanner scanner = new Scanner(System.in); // Only instance of scanner being called in program
    public static final String arrow = ConsoleUtilities.ACCENT + "❯ " + ConsoleUtilities.RESET; // Arrow key used

    public UserInput(){

    }

    /* This method prompts a user for string, uses a try catch to attempt
       to parse string into an int and loops until a valid input is given.
     */
    public static int promptForInt(String prompt, int min, int max) {
        String input;
        int value = 0;
        boolean inputValid = false;
        prompt += arrow;
        System.out.print(prompt);

        while (!inputValid) {
            input = scanner.nextLine(); // Takes in user input as a String
            try{
                value = Integer.parseInt(input); // tries to parse string into an int
                // if statements verify if parsed int meets the required minimums and maximum
                // Keeps looping until inputValid is = true
                if (value >= min && value <= max) {
                    inputValid = true;
                } else if (value <= min) {
                    System.out.print(ConsoleUtilities.ERROR);
                    System.out.println(" The value you entered was too little...");
                    System.out.print(prompt);
                } else {
                    System.out.print(ConsoleUtilities.ERROR);
                    System.out.println(" The value you entered was too high...");
                    System.out.print(prompt);
                }
            } catch(Exception e) { // Tells user that the input provided was not valid.
                System.out.print(ConsoleUtilities.ERROR);
                System.out.println("You did not provide a valid input try again...");
                System.out.print(prompt);
            }
        }
        return value;
    }

    // Unused method, same as above but just for doubles instead of integers
    public static double promptForDouble(String prompt, double min, double max) {
        String input;
        double value = 0;
        boolean inputValid = false;
        prompt += arrow;
        System.out.print(prompt);

        while (!inputValid) {
            input = scanner.nextLine();
            try {
                value = Double.parseDouble(input);
                if (value >= min && value <= max) {
                    inputValid = true;
                } else if (value <= min) {
                    System.out.print(ConsoleUtilities.ERROR);
                    System.out.println(" The value you entered was too little...");
                    System.out.print(prompt);
                } else {
                    System.out.print(ConsoleUtilities.ERROR);
                    System.out.println(" The value you entered was too high...");
                    System.out.print(prompt);
                }
            } catch (Exception e){
                System.out.print(ConsoleUtilities.ERROR);
                System.out.println("You did not provide a valid input try again...");
                System.out.print(prompt);
            }
        }
        return value;
    }

    // Prompts user for double but only takes in a minimum as a parameter
    public static double promptForDouble(String prompt, double min) {
        String input;
        double value = 0;
        boolean inputValid = false;
        prompt += arrow;
        System.out.print(prompt);


        while (!inputValid) {
            input = scanner.nextLine();
            try {
                value = Double.parseDouble(input);
                if (value >= min) {
                    inputValid = true;
                } else {
                    System.out.print(ConsoleUtilities.ERROR);
                    System.out.println(" The value you entered was too little...");
                    System.out.print(prompt);
                }
            } catch (NumberFormatException e) {
                System.out.print(ConsoleUtilities.ERROR);
                System.out.println("You did not provide a valid input try again...");
                System.out.print(prompt);
            }
        }
        return value;
    }

    // Prompts user for String returns the users input.
    public static String promptForString(String prompt) {
        prompt += arrow;
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Prompts user for a String and validates that an actual character was provided
    public static char promptForChar(String prompt){
        char returnValue = 0;
        boolean inputValid = false;
        prompt += arrow;
        System.out.print(prompt);

        while (!inputValid) {
            String input = scanner.nextLine().toUpperCase();
            if (input.length() == 1) { // if the length of the string is equal to one its consider a char
                returnValue = input.charAt(0);
                inputValid = true;
            } else { // if the input is less than 1 or greater than 1 then it gives the user an error messages and loops back over.
                System.out.println(ConsoleUtilities.ERROR + "You did not enter a valid menu option");
                System.out.println(prompt);
            }
        }
        return returnValue;
    }

    // Uses the other promptForChar method to prompt for a char and takes valid in a string of valid chars to compare to
    public static char promptForChar(String prompt, String validChars) {
        String valid = validChars.toUpperCase();
        while (true) {
            char input = promptForChar(prompt);
            if (valid.indexOf(input) >= 0) { // Checks if the CHAR provided is found in the String of chars provided.
                return input; // returns char
            }
            System.out.println(ConsoleUtilities.ERROR + " Invalid choice... Try again");
        }
    }


    // Prompts user for a date and takes it in as a String and attempts to parse it into a LocalDate variable and returns that variable
    public static LocalDate promptForDate(String prompt){
        boolean inputValid = false;
        prompt += arrow;
        System.out.print(prompt);
        LocalDate date = LocalDate.now(); // Creates local date variable

        while (!inputValid){
            String input = scanner.nextLine();
            try {
                date = LocalDate.parse(input);
                inputValid = true;
                return date;

            }catch (Exception e){
                System.out.print(ConsoleUtilities.ERROR);
                System.out.println(" You did not enter a valid date try again...");
                System.out.println(prompt);
            }
        }

        return date; // returns users input as a LocalDate variable
    }

}
