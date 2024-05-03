import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Minesweeper {
    private Board board;
    private Scanner scanner;
    String acceptableChars;
    int safeSquaresRemaining;
    int wins;
    int losses;
    File winLossFile;

    public Minesweeper(int width, int bombAmount) {
        this.board = new Board(width, bombAmount);
        this.scanner = new Scanner(System.in);
        this.acceptableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(0, width);
        this.safeSquaresRemaining = board.getWidth() * board.getWidth() - board.getBombAmount();
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
            String col;
            while (true) {
                System.out.print("Enter column coordinate (letters): ");
                col = scanner.nextLine();
                if (!acceptableChars.contains(col.toUpperCase())) {
                    System.out.println("Invalid coordinate. Please try again.");
                } else {
                    break;
                }
            }

            // Loop for row coordinate input
            int row;
            while (true) {
                System.out.print("Enter row coordinate (numbers): ");
                row = Integer.parseInt(scanner.nextLine());
                if (row < 0 || row >= board.getWidth()) {
                    System.out.println("Invalid coordinate. Please try again.");
                } else {
                    break;
                }
            }

            // Check for loss condition
            if (board.getGrid()[row][acceptableChars.indexOf(col)].hasBomb()) {
                System.out.println("Boom! You hit a mine. Game Over!");
                losses++;
                updateWinLossToFile();
                break;
            }
            revealSquare(row, col);

            // Check for win condition
            if (safeSquaresRemaining == 0) {
                System.out.println("Congratulations! You've won the game!");
                wins++;
                updateWinLossToFile();
                break;
            }
        }
        scanner.close();
    }

    // Print grid to console
    private void printGrid() {
        System.out.print("  ");
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

    public static void main(String[] args) {
        // Added user ability to choose game size and bomb amount
        Scanner scanner = new Scanner(System.in);

        int width;
        while (true) {
            System.out.print("Enter the width of the board (max. 26): ");
            width = scanner.nextInt();
            if (width <= 0 || width > 26) {
                System.out.println("Width must be between 1 and 26. Please try again.");
            } else {
                break;
            }
        }

        int maxBombs = width * width - 1;
        int bombAmount;

        while (true) {
            System.out.print("Enter the number of bombs (max. " + maxBombs + "): ");
            bombAmount = scanner.nextInt();
            if (bombAmount <= 0 || bombAmount > maxBombs) {
                System.out.println("Number of bombs must be between 1 and " + maxBombs + ". Please try again.");
            } else {
                break;
            }
        }

        Minesweeper game = new Minesweeper(width, bombAmount);
        game.playGame();
        scanner.close();
    }
}