package src;

public class Square {
    private boolean hasBomb;
    private boolean revealed;
    private boolean flagged;
    private int adjacentBombs;

    public Square() {
        this.hasBomb = false;
        this.revealed = false;
        this.adjacentBombs = 0;
        this.flagged = false;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void toggleFlag() {
        flagged = !flagged;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public void setBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public int getAdjacentBombs() {
        return adjacentBombs;
    }

    public void setAdjacentBombs(int adjacentBombs) {
        this.adjacentBombs = adjacentBombs;
    }
}