

package com.mycompany.st10448858_poe;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part_1 {
    private static Map<String, String[]> userDatabase = new HashMap<>();
        
    public void run() {
        System.out.println("Running Part_1...");
        Scanner scanner = new Scanner(System.in);
        
        // Sign up
        signUp(scanner);

        // Login
        login(scanner);
    }

    public static void signUp(Scanner scanner) {
        System.out.println("Welcome to the Sign Up program!"); 

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        
        // This method will allow the user to input their personal information into the program.
        System.out.print("Enter username (must be no more than 5 characters long with an underscore): ");
        String username = scanner.nextLine();
        while (!isValidUsername(username)) {
            System.out.println("Invalid username! Please try again.");
            System.out.print("Enter username: ");
            username = scanner.nextLine();
        }
        
        System.out.print("Enter password (must be no more than 8 characters long, contain a capital letter, a number, and a special character): ");
        String password = scanner.nextLine();
        while (!isValidPassword(password)) {
            System.out.println("Invalid password! Please try again.");
            System.out.print("Enter password: ");
            password = scanner.nextLine();
        }

        // This will save the users information into the system. 
        String[] userInfo = {firstName, lastName, password};
        userDatabase.put(username, userInfo);

        System.out.println("Account created successfully!");
    }

    public static void login(Scanner scanner) {
        System.out.println("\nWelcome!!! To Login please Enter your Username and Password");

        // Get user input for login
        System.out.print(" Please Enter Your username: ");
        String username = scanner.nextLine();

        System.out.print("Please Enter Your password: ");
        String password = scanner.nextLine();

        // This method will check the users login credentials to make sure they are correct.
        if (userDatabase.containsKey(username)) {
            String[] userInfo = userDatabase.get(username);
            if (userInfo[2].equals(password)) {
                System.out.println("Login successful! Welcome, " + userInfo[0] + " " + "!");
            } else {
                System.out.println("Incorrect password. Access denied.");
            }
        } else {
            System.out.println("User not found. Access denied.");
        }
    }

    // This a boolean method to check if username is valid or invalid( true or false). 
public static boolean isValidUsername(String username) {
    if (username.length() == 5) {
        for (char c : username.toCharArray()) {
            if (!Character.isDigit(c) && !Character.isLetter(c) && c != '_') {
                return false; // Return false if any character is not a digit, letter, or underscore
            }
        }
        return true; // The User's made username meets all of the criteria
    }
    return false; // The username made is not 5 characters long and doesnt include a underscore.
}

    // This also a boolean method to check if password is valid or invalid. 
    public static boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()\\-_+=?<>].*");
    }

}


