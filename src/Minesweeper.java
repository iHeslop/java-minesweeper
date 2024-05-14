package src;

public class Minesweeper {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        System.out.println(
                "\n   __  ______  __   ________       __    __  __  __   __  ______  ______  __     __  ______  ______  ______ ______  ______    \r\n"
                        + //
                        "  /\\ \\/\\  __ \\/\\ \\ / /\\  __ \\     /\\ \"-./  \\/\\ \\/\\ \"-.\\ \\/\\  ___\\/\\  ___\\/\\ \\  _ \\ \\/\\  ___\\/\\  ___\\/\\  == /\\  ___\\/\\  == \\   \r\n"
                        + //
                        " _\\_\\ \\ \\  __ \\ \\ \\'/\\ \\  __ \\    \\ \\ \\-./\\ \\ \\ \\ \\ \\-.  \\ \\  __\\\\ \\___  \\ \\ \\/ \".\\ \\ \\  __\\\\ \\  __\\\\ \\  _-\\ \\  __\\\\ \\  __<   \r\n"
                        + //
                        "/\\_____\\ \\_\\ \\_\\ \\__| \\ \\_\\ \\_\\    \\ \\_\\ \\ \\_\\ \\_\\ \\_\\\\\"\\_\\ \\_____\\/\\_____\\ \\__/\".~\\_\\ \\_____\\ \\_____\\ \\_\\  \\ \\_____\\ \\_\\ \\_\\ \r\n"
                        + //
                        "\\/_____/\\/_/\\/_/\\/_/   \\/_/\\/_/     \\/_/  \\/_/\\/_/\\/_/ \\/_/\\/_____/\\/_____/\\/_/   \\/_/\\/_____/\\/_____/\\/_/   \\/_____/\\/_/ /_/  \n");
        System.out.println("Welcome to Java CLI Minesweeper! \n");
        // Set grid width
        int width = 0;
        while (true) {
            width = inputHandler.getInputInt("Enter the width of the board (max. 26): ");
            if (width > 1 && width <= 26) {
                break;
            } else {
                System.out.println("Width must be between 2 and 26. Please try again.");
            }
        }

        int maxBombs = width * width - 1;
        int bombAmount = 0;

        // Set bombs within grid
        while (true) {
            bombAmount = inputHandler.getInputInt("Enter the number of bombs (max. " + maxBombs + "): ");
            if (bombAmount > 0 && bombAmount <= maxBombs) {
                break;
            } else {
                System.out.println("Number of bombs must be between 1 and " + maxBombs + ". Please try again.");
            }
        }

        // Game functionality
        Game game = new Game(width, bombAmount, inputHandler);
        game.playGame();
        inputHandler.closeScanner();
    }
}