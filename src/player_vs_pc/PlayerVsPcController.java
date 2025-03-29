package player_vs_pc;

import common.Constants;
import common.HelperService;
import start_window.StartController;
import start_window.StartView;

import javax.swing.*;

public class PlayerVsPcController {
    private final PlayerVsPcView view;
    private final PlayerVsPcModel model;

    public PlayerVsPcController(PlayerVsPcView view, PlayerVsPcModel model) {
        this.view = view;
        this.model = model;
    }

    public void start() {
        view.init();
        addTilesListeners();
        addRefreshListener();
        addBackListener();
    }

    private void addTilesListeners() {
        var board = view.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int finalI = i;
                int finalJ = j;
                board[i][j].addActionListener(e -> {
                    if (!model.validMove(finalI, finalJ) || model.isGameOver()) return;
                    var currentPlayer = model.play(finalI, finalJ);
                    var previousPlayer = currentPlayer == Constants.PLAYER_X ? Constants.PLAYER_0 : Constants.PLAYER_X;
                    view.updateTile((JButton)e.getSource(), previousPlayer);
                    var winnerModel = model.existsWinner();
                    if (model.isGameOver()) {
                        view.setTie();
                        return;
                    }
                    if (!winnerModel.isExists()) {
                        view.updateTitle("Computer's turn");

                        var position = model.playComputer();
                        view.updateTile(position, Constants.PLAYER_0);
                        if (model.isGameOver()) {
                            view.setTie();
                            return;
                        }
                        winnerModel = model.existsWinner();
                        if (winnerModel.isExists()) {
                            model.setGameOver();
                            view.updateWinningBoard(winnerModel.getPositions(), winnerModel.getWinner());
                            return;
                        }
                        view.updateTitle(String.format("%c's turn", currentPlayer));
                        return;
                    }
                    model.setGameOver();
                    view.updateWinningBoard(winnerModel.getPositions(), winnerModel.getWinner());
                });
            }
        }
    }

    private void addRefreshListener() {
        view.getRefreshButton().addActionListener(e -> {
            view.closeWindow();
            new PlayerVsPcController(new PlayerVsPcView(), new PlayerVsPcModel(new HelperService())).start();
        });
    }

    private void addBackListener() {
        view.getBackButton().addActionListener(e -> {
            view.closeWindow();
            new StartController(new StartView()).startGame();
        });
    }

}
