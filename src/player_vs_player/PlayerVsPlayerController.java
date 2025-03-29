package player_vs_player;

import common.Constants;
import common.HelperService;
import start_window.StartController;
import start_window.StartView;

import javax.swing.*;

public class PlayerVsPlayerController {
    private final PlayerVsPlayerView view;
    private final PlayerVsPlayerModel model;

    public PlayerVsPlayerController (PlayerVsPlayerView playerVsPlayerView, PlayerVsPlayerModel playerVsPlayerModel) {
        this.view = playerVsPlayerView;
        this.model = playerVsPlayerModel;
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
                    if (model.isGameOver()) return;
                    if (!model.validMove(finalI, finalJ)) return;
                    var currentPlayer = model.play(finalI, finalJ);
                    var previousPlayer = currentPlayer == Constants.PLAYER_X ? Constants.PLAYER_0 : Constants.PLAYER_X;
                    view.updateTile((JButton)e.getSource(), previousPlayer);
                    var winnerModel = model.existsWinner();
                    if (!winnerModel.isExists()) {
                        if (model.isGameOver()) {
                            view.setTie();
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
            new PlayerVsPlayerController(new PlayerVsPlayerView(), new PlayerVsPlayerModel(new HelperService())).start();
        });
    }

    private void addBackListener() {
        view.getBackButton().addActionListener(e -> {
            view.closeWindow();
            new StartController(new StartView()).startGame();
        });
    }

}
