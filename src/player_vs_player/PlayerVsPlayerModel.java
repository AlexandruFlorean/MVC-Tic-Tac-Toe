package player_vs_player;

import common.Constants;
import common.services.ModelService;
import common.models.WinnerModel;

public class PlayerVsPlayerModel {
    private char currentPlayer = Constants.PLAYER_X;
    private int turns;
    private final char[][] boardMatrix = new char[3][3];
    private final ModelService helperService;

    public PlayerVsPlayerModel(ModelService helperService) {
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
        turns++;
        currentPlayer = currentPlayer == Constants.PLAYER_X ? Constants.PLAYER_0 : Constants.PLAYER_X;
        return currentPlayer;
    }
}
