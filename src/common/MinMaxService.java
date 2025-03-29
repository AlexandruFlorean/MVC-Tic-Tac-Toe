package common;

import java.util.ArrayList;
import java.util.List;

public class MinMaxService {
    private char computerPlayer;

    public MinMaxService(char computerPlayer) {
        this.computerPlayer = computerPlayer;
    }

    public Position minmax(char[][] board, char player) {
        Position actionToTake = null;
        int score = Integer.MIN_VALUE;
        for (var action : getPossibleActions(board)) {
            var newBoard = addMove(board, action, player);
            var computedScore = computeMinMaxScore(newBoard, getNextPlayer(player));
            if (computedScore > score) {
                score = computedScore;
                actionToTake = action;
            }
        }
        return actionToTake;
    }

    private int computeMinMaxScore(char[][] board, char player) {
        if (isTerminal(board)) {
            return getScore(board);
        }
        int best = player == computerPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (var action : getPossibleActions(board)) {
            var newBoard = addMove(board, action, player);
            int score = computeMinMaxScore(newBoard, getNextPlayer(player));
            if (player == computerPlayer && score > best) {
                best = score;
            } else if (player != computerPlayer && score < best) {
                best = score;
            }
        }
        return best;
    }

    private char getNextPlayer(char currentPlayer) {
        return currentPlayer == Constants.PLAYER_X ? Constants.PLAYER_0 : Constants.PLAYER_X;
    }

    private char[][] addMove(char[][] board, Position action, char player) {
        var newBoard = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, 3);
        }
        newBoard[action.getI()][action.getJ()] = player;
        return newBoard;
    }

    private boolean isTerminal(char[][] board) {
        var winner = winner(board);
        if (winner == Constants.PLAYER_X || winner == Constants.PLAYER_0)
            return true;
        return getPossibleActions(board).isEmpty();
    }

    private int getScore(char[][] board) {
        var winner = winner(board);
        if (winner == Constants.EMPTY) {
            return 0;
        }
        return winner == computerPlayer ? 1 : -1;
    }

    private List<Position> getPossibleActions(char[][] board) {
        List<Position> possibleActions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Constants.EMPTY)
                    possibleActions.add(new Position(i, j));
            }
        }
        return possibleActions;
    }

    private char winner(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != Constants.EMPTY && board[i][0] == board[i][1] && board[i][0] == board[i][2])
                return board[i][0];
            if (board[0][i] != Constants.EMPTY && board[0][i] == board[1][i] && board[0][i] == board[2][i])
                return board[0][i];
        }
        if (board[1][1] != Constants.EMPTY &&
                ((board[0][0] == board[1][1] && board[0][0] == board[2][2])
                        ||
                        (board[0][2] == board[1][1] && board[0][2] == board[2][0])))
            return board[1][1];
        return Constants.EMPTY;
    }
}
