// Game.java
package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    private Board board;
    String acceptableChars;
    int safeSquaresRemaining;
    InputInterface inputInterface;
    int bombAmount;
    int wins;
    int losses;
    File winLossFile;

    public Game(int width, int bombAmount, InputInterface inputInterface) {
        this.board = new Board(width, bombAmount);
        this.acceptableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(0, width);
        this.safeSquaresRemaining = board.getWidth() * board.getWidth() - board.getBombAmount();
        this.inputInterface = inputInterface;
        this.winLossFile = new File("winloss.txt");
        readWinLossFromFile();
    }

    private void readWinLossFromFile() {
        try {
            Scanner fileScanner = new Scanner(winLossFile);
            String winsLine = fileScanner.nextLine();
            String lossesLine = fileScanner.nextLine();
            wins = Integer.parseInt(winsLine.split(": ")[1]);
            losses = Integer.parseInt(lossesLine.split(": ")[1]);
            fileScanner.close();
        } catch (FileNotFoundException e) {
            wins = 0;
            losses = 0;
        }
    }

    private void updateWinLossToFile() {
        try {
            FileWriter fileWriter = new FileWriter(winLossFile);
            fileWriter.write("Total wins: " + wins + "\n");
            fileWriter.write("Total losses: " + losses);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playGame() {
        while (true) {
            printGrid();

            // Loop for column coordinate input
            String col = inputInterface.getInputString("\nEnter column coordinate (letters): ");

            // Loop for row coordinate input
            int row = inputInterface.getInputInt("\nEnter row coordinate (numbers): ");

            // Check for loss condition
            if (board.getGrid()[row][acceptableChars.indexOf(col)].hasBomb()) {
                System.out.println("\n          _ ._  _ , _ ._\r\n" + //
                        "        (_ ' ( `  )_  .__)\r\n" + //
                        "      ( (  (    )   `)  ) _)\r\n" + //
                        "     (__ (_   (_ . _) _) ,__)\r\n" + //
                        "         `~~`\\ ' . /`~~`\r\n" + //
                        "              ;   ;\r\n" + //
                        "              /   \\\r\n" + //
                        "_____________/_ __ \\_____________");
                System.out.println("\nBoom! You hit a mine. Game Over!\n");
                losses++;
                updateWinLossToFile();
                break;
            }
            revealSquare(row, col);

            // Check for win condition
            if (safeSquaresRemaining == 0) {
                System.out.println("\r\n" + //
                        " __  __  ______  __  __       __     __  __  __   __    \r\n" + //
                        "/\\ \\_\\ \\/\\  __ \\/\\ \\/\\ \\     /\\ \\  _ \\ \\/\\ \\/\\ \"-.\\ \\   \r\n" + //
                        "\\ \\____ \\ \\ \\/\\ \\ \\ \\_\\ \\    \\ \\ \\/ \".\\ \\ \\ \\ \\ \\-.  \\  \r\n" + //
                        " \\/\\_____\\ \\_____\\ \\_____\\    \\ \\__/\".~\\_\\ \\_\\ \\_\\\\\"\\_\\ \r\n" + //
                        "  \\/_____/\\/_____/\\/_____/     \\/_/   \\/_/\\/_/\\/_/ \\/_/ \r\n" + //
                        "                                                        \r\n" + //
                        "");
                System.out.println("\nCongratulations! You've won the game!\n");
                wins++;
                updateWinLossToFile();
                break;
            }
        }
    }

    // Print grid to console
    private void printGrid() {
        System.out.print("\n  ");
        for (int i = 0; i < acceptableChars.length(); i++) {
            System.out.print(acceptableChars.charAt(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < board.getWidth(); i++) {
            System.out.print(i + " ");
            for (int j = 0; j < board.getWidth(); j++) {
                Square square = board.getGrid()[i][j];
                if (square.isRevealed()) {
                    System.out.print(square.getAdjacentBombs() + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.print("\nSafe squares Remaining: " + safeSquaresRemaining + "\n");
    }

    // Check square method to check reveal square content
    private void revealSquare(int row, String col) {
        // If square already revealed just return
        if (board.getGrid()[row][acceptableChars.indexOf(col)].isRevealed()) {
            return;
        }

        // Set revealed to true for chosen square
        board.getGrid()[row][acceptableChars.indexOf(col)].setRevealed(true);
        safeSquaresRemaining--;
        // If cell has no adjacent bombs, we reveal adjacent cells recursively
        if (board.getGrid()[row][acceptableChars.indexOf(col)].getAdjacentBombs() == 0) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = acceptableChars.indexOf(col) - 1; j <= acceptableChars.indexOf(col) + 1; j++) {
                    if (i >= 0 && i < board.getWidth() && j >= 0 && j < board.getWidth()) {
                        revealSquare(i, acceptableChars.substring(j, j + 1));
                    }
                }
            }
        }
    }
}