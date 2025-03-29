package player_vs_pc;

import common.*;

public class PlayerVsPcModel {
    private char currentPlayer = Constants.PLAYER_X;
    private int turns;
    private final char[][] boardMatrix = new char[3][3];
    private final HelperService helperService;
    private final MinMaxService minMaxService = new MinMaxService(Constants.PLAYER_0);

    public PlayerVsPcModel(HelperService helperService) {
        this.helperService = helperService;
    }

    public boolean validMove(int i, int j) {
        return boardMatrix[i][j] == Constants.EMPTY;
    }

    public boolean isGameOver() {
        return turns == 9;
    }

    public void setGameOver() {
        turns = 9;
    }

    public WinnerModel existsWinner() {
        var winnerModel = helperService.winnerExists(3, 3, boardMatrix);
        if (winnerModel.isExists()) {
            var winner = currentPlayer == Constants.PLAYER_X ? Constants.PLAYER_0 : Constants.PLAYER_X;
            winnerModel.setWinner(winner);
        }
        return winnerModel;
    }

    public char play(int i, int j) {
        boardMatrix[i][j] = currentPlayer;
        currentPlayer = currentPlayer == Constants.PLAYER_X ? Constants.PLAYER_0 : Constants.PLAYER_X;
        turns++;
        return currentPlayer;
    }

    public Position playComputer() {
        var position = minMaxService.minmax(boardMatrix, currentPlayer);
        boardMatrix[position.getI()][position.getJ()] = currentPlayer;
        currentPlayer = currentPlayer == Constants.PLAYER_X ? Constants.PLAYER_0 : Constants.PLAYER_X;
        turns++;
        return position;
    }
}
