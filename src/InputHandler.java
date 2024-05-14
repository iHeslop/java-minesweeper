package src;

import java.util.Scanner;

public class InputHandler implements InputInterface {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getInputString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.matches(".*\\d.*")) { // Check if input contains any digits - regex fancy ooh la la
                return input.toUpperCase();
            } else {
                System.out.println("Invalid input. Please enter a valid string.");
            }
        }
    }

    @Override
    public int getInputInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}