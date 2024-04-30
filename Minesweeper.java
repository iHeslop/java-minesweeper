import java.util.Scanner;

public class Minesweeper {
    private Board board;
    private Scanner scanner;

    public Minesweeper(int width, int bombAmount) {
        this.board = new Board(width, bombAmount);
        this.scanner = new Scanner(System.in);
    }

    public void playGame() {
        while (true) {
            printGrid();
            String acceptableChars = "ABCDEFGHIJ";
            System.out.print("Enter column coordinate (letters): ");
            String col = scanner.nextLine();
            if (!acceptableChars.contains(col.toUpperCase())) {
                System.out.println("Invalid coordinate. Please try again.");
            } else {
                System.out.println(col);
            }

            System.out.print("Enter row coordinate (numbers): ");
            int row = scanner.nextInt();
            if (row < 0 || row >= board.getWidth()) {
                System.out.println("Invalid coordinate. Please try again.");
            } else {
                System.out.println(row);
                continue;
            }
            if (board.getGrid()[row][acceptableChars.indexOf(col)].hasBomb()) {
                System.out.println("Boom! You hit a mine. Game Over!");
                break;
            }
            // revealSquare(row, col);
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

    public static void main(String[] args) {
        Minesweeper game = new Minesweeper(10, 20);
        game.playGame();
    }
}