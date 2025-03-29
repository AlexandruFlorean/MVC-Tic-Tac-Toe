package pc_vs_pc;

import common.BackButton;
import common.Constants;
import common.Position;
import common.RefreshButton;

import javax.swing.*;
import java.awt.*;

public class PcVsPcView {
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
        board[position.getI()][position.getJ()].setText(String.valueOf(player));
    }

    public void updateTitle(String title) {
        start.setText(title);
    }

    public void updateWinningBoard(java.util.List<Position> positions, char winner) {
        board[positions.get(0).getI()][positions.get(0).getJ()].setForeground(Color.GREEN);
        board[positions.get(1).getI()][positions.get(1).getJ()].setForeground(Color.GREEN);
        board[positions.get(2).getI()][positions.get(2).getJ()].setForeground(Color.GREEN);
        start.setText(String.format("%c is the winner!", winner));
    }

    public void setTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setForeground(Color.ORANGE);
                board[i][j].setBackground(Color.GRAY);
                start.setText("Tie");
            }
        }
    }

    private void initializeUIComponents() {
        frame.setVisible(true);
        frame.setSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        start.setBackground(Color.DARK_GRAY);
        start.setForeground(Color.white);
        start.setFont(new Font("Arial", Font.BOLD, 50));
        start.setFocusable(false);
        start.setText("Start");

        backButton.getBackButton().setFont(new Font("Arial", Font.PLAIN, 30));
        backButton.getBackButton().setBackground(Color.darkGray);
        backButton.getBackButton().setForeground(Color.white);
        backButton.getBackButton().setBorderPainted(false);
        backButton.getBackButton().setFocusPainted(false);
        backButton.getBackButton().setFocusable(false);

        refreshButton.getRefreshButton().setFont(new Font("Arial", Font.PLAIN, 30));
        refreshButton.getRefreshButton().setBackground(Color.darkGray);
        refreshButton.getRefreshButton().setForeground(Color.white);
        refreshButton.getRefreshButton().setBorderPainted(false);
        refreshButton.getRefreshButton().setFocusPainted(false);
        refreshButton.getRefreshButton().setFocusable(false);

        startPanel.setLayout(new BorderLayout());
        startPanel.add(start);
        startPanel.add(backButton.getBackButton(), BorderLayout.WEST);
        startPanel.add(refreshButton.getRefreshButton(), BorderLayout.EAST);
        frame.add(startPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

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