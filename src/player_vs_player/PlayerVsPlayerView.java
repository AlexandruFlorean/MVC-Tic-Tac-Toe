package player_vs_player;

import common.models.Position;
import common.services.ViewService;
import common.ui_components.BackButton;
import common.ui_components.RefreshButton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerVsPlayerView {
    private final ViewService viewService = new ViewService();

    private final JFrame frame = new JFrame("Tic-Tac-Toe");
    private final JLabel textLabel = new JLabel();
    private final JPanel textPanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final BackButton backButton = new BackButton();
    private final JButton[][] board = new JButton[3][3];
    private final RefreshButton refreshButton = new RefreshButton();

    public void init() {
        initializeUIComponents();
    }

    public JButton[][] getBoard() {
        return board;
    }

    public JButton getBackButton() {
        return backButton.getBackButton();
    }

    public JButton getRefreshButton() {
        return refreshButton.getRefreshButton();
    }

    public void closeWindow() {
        frame.dispose();
    }

    public void updateTile(JButton tile, char player) {
        tile.setText(String.valueOf(player));
    }

    public void updateTitle(String title) {
        textLabel.setText(title);
    }

    public void updateWinningBoard(List<Position> positions, char winner) {
        viewService.updateWinningBoard(positions, winner, board, textLabel);
    }

    public void setTie() {
        viewService.setTie(board, textLabel);
    }

    private void initializeUIComponents() {
        viewService.initializeFrame(frame);
        viewService.initializeTextLabel(textLabel);
        viewService.initializeBackButton(backButton);
        viewService.initializeRefreshButton(refreshButton);
        viewService.initializeBoardPanel(boardPanel);
        frame.add(boardPanel);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.add(backButton.getBackButton(), BorderLayout.WEST);
        textPanel.add(refreshButton.getRefreshButton(), BorderLayout.EAST);
        frame.add(textPanel, BorderLayout.NORTH);


        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                board[r][c] = tile;
                boardPanel.add(tile);
            }
        }
    }
}
