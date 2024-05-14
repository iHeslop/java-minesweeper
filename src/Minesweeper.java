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

        System.out.println("**Welcome to Java CLI Minesweeper**");
        System.out.println("!Reveal all bomb-less cells to win!");

        // Game functionality
        Game game = new Game(inputHandler);

        while (true) {
            int choice = game.startMenu();
            switch (choice) {
                case 1:
                    game.viewResults();
                    break;
                case 2:
                    System.out.println("\n-------------------------------------------");
                    System.out.println("\r\n" + //
                            "   __   ______________  ___  __   _____  __\r\n" + //
                            "  / /  / __/_  __/ __/ / _ \\/ /  / _ \\ \\/ /\r\n" + //
                            " / /__/ _/  / / _\\ \\  / ___/ /__/ __ |\\  / \r\n" + //
                            "/____/___/ /_/ /___/ /_/  /____/_/ |_|/_/  \r\n" + //
                            "                                           \r" + //
                            "");
                    // Set grid width
                    int width = 0;
                    while (true) {
                        width = inputHandler.getInputInt("\nEnter the width of the board (max. 26): ");
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
                            System.out.println(
                                    "Number of bombs must be between 1 and " + maxBombs + ". Please try again.");
                        }
                    }

                    game.playGame(width, bombAmount);
                    return;
                case 3:
                    System.out.println("\r\n" + //
                            "    _______  __ _______________   ________   _________    __  _________   \r\n" + //
                            "   / ____/ |/ //  _/_  __/  _/ | / / ____/  / ____/   |  /  |/  / ____/   \r\n" + //
                            "  / __/  |   / / /  / /  / //  |/ / / __   / / __/ /| | / /|_/ / __/      \r\n" + //
                            " / /___ /   |_/ /  / / _/ // /|  / /_/ /  / /_/ / ___ |/ /  / / /____ _ _ \r\n" + //
                            "/_____//_/|_/___/ /_/ /___/_/ |_/\\____/   \\____/_/  |_/_/  /_/_____(_|_|_)\r\n" + //
                            "                                                                          \r\n" + //
                            "");
                    inputHandler.closeScanner();
                    return;
                default:
                    System.out.println("\nInvalid choice. Please enter a number from 1 to 3.");
            }
        }
    }
}