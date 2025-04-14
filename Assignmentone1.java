/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author RC_Student_lab
 */

import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Login {
    private static HashMap<String, UserInfo> users = new HashMap<>();

    private static class UserInfo {
        String password;
        String firstName;
        String lastName;

        public UserInfo(String password, String firstName, String lastName) {
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    static {
        users.put("john_", new UserInfo("P@ssw0rd", "John", "Smith"));
        users.put("jane_", new UserInfo("Secure1!", "Jane", "Doe"));
        users.put("bob_1", new UserInfo("B0b@Pass", "Bob", "Johnson"));
    }

    public static boolean validateUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public static boolean validatePassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$";
        return Pattern.matches(regex, password);
    }

    public static boolean validateCellPhone(String cellPhone) {
        String regex = "^\\+\\d{1,3}\\d{1,10}$";
        return Pattern.matches(regex, cellPhone) && cellPhone.length() <= 14;
    }

    public static boolean checkUserName(String username, String password) {
        if (users.containsKey(username)) {
            UserInfo userInfo = users.get(username);
            if (userInfo.password.equals(password)) {
                System.out.println("Welcome " + userInfo.firstName + " " + userInfo.lastName + ", it is great to see you again");
                return true;
            }
        }
        System.out.println("Username or password incorrect. Please try again.");
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username = getUsername(scanner);
        String password = getPassword(scanner);
        String cellPhone = getCellPhone(scanner);
        login(scanner);
        scanner.close();
    }

    private static String getUsername(Scanner scanner) {
        while (true) {
            System.out.println("Enter username: ");
            String username = scanner.nextLine();
            if (validateUsername(username)) {
                System.out.println("Username successfully captured");
                return username;
            } else {
                System.out.println("Username is not correctly formatted. Please ensure that the username contains an underscore and is no more than 5 characters long.");
            }
        }
    }

    private static String getPassword(Scanner scanner) {
        while (true) {
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            if (validatePassword(password)) {
                System.out.println("Password successfully captured.");
                return password;
            } else {
                System.out.println("Password is not correctly formatted. Please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }
    }

    private static String getCellPhone(Scanner scanner) {
        while (true) {
            System.out.print("Enter your cell phone number (with international code): ");
            String cellPhone = scanner.nextLine();
            if (validateCellPhone(cellPhone)) {
                System.out.println("Cell phone number successfully added");
                return cellPhone;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code");
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.println("\n--- Login to your account ---");
        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();
        boolean loginSuccessful = checkUserName(loginUsername, loginPassword);
        if (loginSuccessful) {
            System.out.println("Login successful! Access granted.");
        } else {
            System.out.println("Login failed. Access denied.");
        }
        System.out.println("\nThis program was enhanced with help from Claude AI (Anthropic, 2025)");
    }
}