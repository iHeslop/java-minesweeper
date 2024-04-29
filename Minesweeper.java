import java.util.Scanner;

public class Minesweeper {
    private Board board;
    private Scanner scanner;

    public Minesweeper(int width, int bombAmount) {
        this.board = new Board(width, bombAmount);
        this.scanner = new Scanner(System.in);
    }

    public void playGame() {
        printGrid();
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