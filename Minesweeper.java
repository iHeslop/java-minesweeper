import java.util.Scanner;

public class Minesweeper {
    private Board board;
    private Scanner scanner;
    String acceptableChars = "ABCDEFGHIJ";

    public Minesweeper(int width, int bombAmount) {
        this.board = new Board(width, bombAmount);
        this.scanner = new Scanner(System.in);
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

            if (board.getGrid()[row][acceptableChars.indexOf(col)].hasBomb()) {
                System.out.println("Boom! You hit a mine. Game Over!");
                break;
            }
            revealSquare(row, col);
        }
        scanner.close();
    }

    private void printGrid() {
        System.out.println("  A B C D E F G H I J");
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

    private void revealSquare(int row, String col) {
        // If square already revealed just return
        if (board.getGrid()[row][acceptableChars.indexOf(col)].isRevealed()) {
            return;
        }

        // Set revealed to true for chosen square
        board.getGrid()[row][acceptableChars.indexOf(col)].setRevealed(true);

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
        Minesweeper game = new Minesweeper(10, 20);
        game.playGame();
    }
}