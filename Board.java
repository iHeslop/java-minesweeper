import java.util.Random;

public class Board {
    private int width;
    private int bombAmount;
    private Square[][] grid;

    public Board(int width, int bombAmount) {
        this.width = width;
        this.bombAmount = bombAmount;
        this.grid = new Square[width][width];
        createBoard();
    }

    public int getWidth() {
        return width;
    }

    public Square[][] getGrid() {
        return grid;
    }

    private void createBoard() {
        // initialize board with empty grid of Squares
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < width; ++j) {
                grid[i][j] = new Square();
            }
        }

        // randomly place bombs within the grid
        Random random = new Random();
        int bombsPlaced = 0;
        while (bombsPlaced < bombAmount) {
            int x = random.nextInt(width);
            int y = random.nextInt(width);
            if (!grid[x][y].hasBomb()) {
                grid[x][y].setBomb(true);
                bombsPlaced++;
            }
        }

        // Calculate adjacent bomb count for each square
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                int adjacentBombs = countAdjacentBombs(i, j);
                grid[i][j].setAdjacentBombs(adjacentBombs);
            }
        }
    }

    // Count adjacent bombs
    private int countAdjacentBombs(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < width && j >= 0 && j < width && grid[i][j].hasBomb()) {
                    count++;
                }
            }
        }
        return count;
    }
}