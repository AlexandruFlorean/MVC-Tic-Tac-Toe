package player_vs_player;

import common.Constants;
import common.Position;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerVsPlayerView {


    private final JFrame frame = new JFrame("Tic-Tac-Toe");
    private final JLabel textLabel = new JLabel();
    private final JPanel textPanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final JButton backButton = new JButton("←");
    private final JButton[][] board = new JButton[3][3];
    private final ImageIcon icon = new ImageIcon("src/images/refresh-page-option.png");
    private final Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    private final ImageIcon refreshIcon = new ImageIcon(scaledImage);
    private final JButton refreshButton = new JButton(refreshIcon);

    public void init() {
        initializeUIComponents();
    }

    public JButton[][] getBoard() {
        return board;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
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
        textLabel.setFont(new Font ("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        backButton.setFont(new Font("Arial", Font.PLAIN, 30));
        backButton.setBackground(Color.darkGray);
        backButton.setForeground(Color.white);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setFocusable(false);

        refreshButton.setFont(new Font("Arial", Font.PLAIN, 30));
        refreshButton.setBackground(Color.darkGray);
        refreshButton.setForeground(Color.white);
        refreshButton.setBorderPainted(false);
        refreshButton.setFocusPainted(false);
        refreshButton.setFocusable(false);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.add(backButton, BorderLayout.WEST);
        textPanel.add(refreshButton, BorderLayout.EAST);
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
