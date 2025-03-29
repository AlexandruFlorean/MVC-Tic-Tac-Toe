package common;

import java.util.List;

public class WinnerModel {
    private boolean exists;
    private char winner;
    private List<Position> positions;

    public WinnerModel() {
    }

    public WinnerModel(List<Position> positions) {
        exists = true;
        this.positions = positions;
    }

    public boolean isExists() {
        return exists;
    }

    public char getWinner() {
        return winner;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setWinner(char winner) {
        this.winner = winner;
    }
}
