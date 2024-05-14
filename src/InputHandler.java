package src;

import java.util.Scanner;

public class InputHandler implements InputInterface {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getInputString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public int getInputInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    public void closeScanner() {
        scanner.close();
    }
}