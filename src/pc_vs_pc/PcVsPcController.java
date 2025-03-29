package pc_vs_pc;

import common.services.ModelService;
import common.models.Position;
import start_window.StartController;
import start_window.StartView;

import javax.swing.*;
import java.util.Random;

public class PcVsPcController {
    private final PcVsPcView view;
    private final PcVsPcModel model;

    private Random rand;
    private int n;
    private int m;

    public PcVsPcController(PcVsPcView view, PcVsPcModel model) {
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
        view.getStart().addActionListener(e -> {
            view.getStart().setEnabled(false);

            var timer = new Timer(1000, e1 -> {
                if (model.isGameOver()) return;
                rand = new Random();
                do {
                    n = rand.nextInt(3);
                    m = rand.nextInt(3);
                } while (!model.validMove(n, m) && !model.isGameOver());
                var previousPlayer = model.getCurrentPlayer();
                model.play(n, m);
                view.updateTile(new Position(n, m), previousPlayer);
                if (!model.isGameOver()) {
                    view.updateTitle(model.getCurrentPlayer() + "'s turn.");
                }
                var winnerModel = model.existsWinner();
                if (winnerModel.isExists()) {
                    model.setGameOver();
                    view.updateWinningBoard(winnerModel.getPositions(), winnerModel.getWinner());
                    return;
                }
                if (model.isGameOver()) {
                    view.setTie();
                }
            });
            timer.start();
        });
    }


    private void addRefreshListener() {
        view.getRefreshButton().addActionListener(e -> {
            view.closeWindow();
            new PcVsPcController(new PcVsPcView(), new PcVsPcModel(new ModelService())).start();
        });
    }

    private void addBackListener() {
        view.getBackButton().addActionListener(e -> {
            view.closeWindow();
            new StartController(new StartView()).startGame();
        });
    }
}
