package pc_vs_pc;

import common.services.ViewService;
import common.ui_components.BackButton;
import common.models.Position;
import common.ui_components.RefreshButton;

import javax.swing.*;
import java.awt.*;

public class PcVsPcView {
    private final ViewService viewService = new ViewService();

    private final JFrame frame = new JFrame("Tic-Tac-Toe");
    private final JPanel startPanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final JButton start = new JButton();
    private final RefreshButton refreshButton = new RefreshButton();
    private final BackButton backButton = new BackButton();
    private final JLabel[][] board = new JLabel[3][3];

    public void init() {
        initializeUIComponents();
    }

    public JButton getStart() {
        return start;
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

    public void updateTile(Position position, char player) {
        board[position.i()][position.j()].setText(String.valueOf(player));
    }

    public void updateTitle(String title) {
        start.setText(title);
    }

    public void updateWinningBoard(java.util.List<Position> positions, char winner) {
        viewService.updateWinningBoard(positions, winner, board, start);
    }

    public void setTie() {
        viewService.setTie(board, start);
    }

    private void initializeUIComponents() {
        viewService.initializeFrame(frame);
        viewService.initializeStart(start);
        viewService.initializeBackButton(backButton);
        viewService.initializeRefreshButton(refreshButton);
        viewService.initializeBoardPanel(boardPanel);
        frame.add(boardPanel);

        startPanel.setLayout(new BorderLayout());
        startPanel.add(start);
        startPanel.add(backButton.getBackButton(), BorderLayout.WEST);
        startPanel.add(refreshButton.getRefreshButton(), BorderLayout.EAST);
        frame.add(startPanel, BorderLayout.NORTH);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JLabel tile = new JLabel();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.setHorizontalAlignment(SwingConstants.CENTER);
                tile.setOpaque(true);
                tile.setBorder(BorderFactory.createMatteBorder(
                        r == 0 ? 0 : 3,
                        c == 0 ? 0 : 3,
                        r == 2 ? 0 : 3,
                        c == 2 ? 0 : 3,
                        Color.WHITE
                ));
            }
        }
    }
}