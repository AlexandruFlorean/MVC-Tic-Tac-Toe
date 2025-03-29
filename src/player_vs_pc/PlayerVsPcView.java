package player_vs_pc;

import common.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerVsPcView {

    private final JFrame frame = new JFrame("Tic-Tac-Toe");
    private final JLabel textLabel = new JLabel();
    private final JPanel textPanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final JButton[][] board = new JButton[3][3];
    private final BackButton backButton = new BackButton();
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

    public void updateTile(Position position, char player) {
        board[position.getI()][position.getJ()].setText(String.valueOf(player));
    }

    public void updateTitle(String title) {
        textLabel.setText(title);
    }

    public void updateWinningBoard(List<Position> positions, char winner) {
        board[positions.get(0).getI()][positions.get(0).getJ()].setForeground(Color.GREEN);
        board[positions.get(1).getI()][positions.get(1).getJ()].setForeground(Color.GREEN);
        board[positions.get(2).getI()][positions.get(2).getJ()].setForeground(Color.GREEN);
        textLabel.setText(String.format("%c is the winner!", winner));
    }

    public void setTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setForeground(Color.ORANGE);
                board[i][j].setBackground(Color.GRAY);
                textLabel.setText("Tie");
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

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

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

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.add(backButton.getBackButton(), BorderLayout.WEST);
        textPanel.add(refreshButton.getRefreshButton(), BorderLayout.EAST);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);
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
