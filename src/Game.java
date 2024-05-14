package src;

public class Game {
    private Board board;
    String acceptableChars;
    int safeSquaresRemaining;
    InputInterface inputInterface;
    int bombAmount;
    int wins;
    int losses;
    FileHandler fileHandler;

    public Game(InputInterface inputInterface) {
        this.inputInterface = inputInterface;
        this.fileHandler = new FileHandler("results.txt");
        int[] winLossData = fileHandler.readWinLossFromFile();
        this.wins = winLossData[0];
        this.losses = winLossData[1];
    }

    private void updateWinLossToFile() {
        fileHandler.updateWinLossToFile(wins, losses);
    }

    // Start Program with Menu
    public int startMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. View Results");
        System.out.println("2. Play Minesweeper");
        System.out.println("3. Exit");

        return inputInterface.getInputInt("Enter your choice: ");
    }

    // View Results in CLI
    public void viewResults() {
        System.out.println("\n-------------------------------------------");
        System.out.println("Results:");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("-------------------------------------------");
    }

    // Play Game Functionality
    public void playGame(int width, int bombAmount) {
        this.board = new Board(width, bombAmount);
        this.acceptableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(0, width);
        this.safeSquaresRemaining = board.getWidth() * board.getWidth() - board.getBombAmount();
        while (true) {
            System.out.println("\n-------------------------------------------");
            printGrid();

            String col;
            int row;
            String action;

            // Loop for column coordinate input
            while (true) {
                col = inputInterface.getInputString("\nEnter column coordinate (letters): ");
                if (acceptableChars.contains(col)) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println("Invalid column coordinate. Please enter a valid column coordinate.");
                }
            }

            // Loop until valid input for row coordinate is provided
            while (true) {
                row = inputInterface.getInputInt("\nEnter row coordinate (numbers): ");
                if (row >= 0 && row < board.getWidth()) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println("Invalid row coordinate. Please enter a valid row coordinate.");
                }
            }

            // Loop until valid input for action is provided
            while (true) {
                action = inputInterface.getInputString("\nEnter 'R' to reveal or 'F' to flag: ");
                if (action.equals("R") || action.equals("F")) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println("Invalid action. Please enter 'R' to reveal or 'F' to flag.");
                }
            }

            // Perform action based on user input
            if (action.equals("R")) {
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
            } else if (action.equals("F")) {
                // Implement flagging functionality
                board.getGrid()[row][acceptableChars.indexOf(col)].toggleFlag();
            }

            // Check for win condition
            if (safeSquaresRemaining == 0) {
                System.out.println("\n-------------------------------------------");
                System.out.println("\r\n" + //
                        " __  __  ______  __  __       __     __  __  __   __    \r\n" + //
                        "/\\ \\_\\ \\/\\  __ \\/\\ \\/\\ \\     /\\ \\  _ \\ \\/\\ \\/\\ \"-.\\ \\   \r\n" + //
                        "\\ \\____ \\ \\ \\/\\ \\ \\ \\_\\ \\    \\ \\ \\/ \".\\ \\ \\ \\ \\ \\-.  \\  \r\n" + //
                        " \\/\\_____\\ \\_____\\ \\_____\\    \\ \\__/\".~\\_\\ \\_\\ \\_\\\\\"\\_\\ \r\n" + //
                        "  \\/_____/\\/_____/\\/_____/     \\/_/   \\/_/\\/_/\\/_/ \\/_/ \r\n" + //
                        "                                                        \r\n" + //
                        "");
                System.out.println("Congratulations! You've won the game!\n");
                System.out.println("\n-------------------------------------------");
                wins++;
                updateWinLossToFile();
                break;
            }
        }
    }

    // Print grid to console
    private void printGrid() {
        System.out.print("\n   ");
        for (int i = 0; i < acceptableChars.length(); i++) {
            System.out.print(acceptableChars.charAt(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < board.getWidth(); i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < board.getWidth(); j++) {
                Square square = board.getGrid()[i][j];
                if (square.isRevealed()) {
                    System.out.print(square.getAdjacentBombs() + " ");
                } else if (square.isFlagged()) {
                    System.out.print("X ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.print("\nSafe squares remaining: " + safeSquaresRemaining + "\n");
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