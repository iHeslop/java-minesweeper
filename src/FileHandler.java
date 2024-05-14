package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    private File winLossFile;

    public FileHandler(String filePath) {
        this.winLossFile = new File(filePath);
    }

    // Reads current wins and losses from file and saves in variables
    public int[] readWinLossFromFile() {
        int[] winLossData = new int[2];
        try {
            Scanner fileScanner = new Scanner(winLossFile);
            String winsLine = fileScanner.nextLine();
            String lossesLine = fileScanner.nextLine();
            winLossData[0] = Integer.parseInt(winsLine.split(": ")[1]);
            winLossData[1] = Integer.parseInt(lossesLine.split(": ")[1]);
            fileScanner.close();
        } catch (FileNotFoundException e) {
            // File not found, return default values
            winLossData[0] = 0;
            winLossData[1] = 0;
        }
        return winLossData;
    }

    // Updates results file with current wins and losses at end of game
    public void updateWinLossToFile(int wins, int losses) {
        try {
            FileWriter fileWriter = new FileWriter(winLossFile);
            fileWriter.write("Total wins: " + wins + "\n");
            fileWriter.write("Total losses: " + losses);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}