package common.services;

import common.Constants;
import common.models.Position;
import common.ui_components.BackButton;
import common.ui_components.RefreshButton;

import javax.swing.*;
import java.awt.*;

public class ViewService {
    public void setTie(JLabel[][] board, JButton button) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setForeground(Color.ORANGE);
                board[i][j].setBackground(Color.GRAY);
                button.setText("Tie");
            }
        }
    }

    public void setTie(JButton[][] board, JLabel button) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setForeground(Color.ORANGE);
                board[i][j].setBackground(Color.GRAY);
                button.setText("Tie");
            }
        }
    }

    public void updateWinningBoard(java.util.List<Position> positions, char winner, JLabel[][] board, JButton button) {
        for (int i = 0; i < 3; i++){
            board[positions.get(i).i()][positions.get(i).j()].setForeground(Color.GREEN);
        }
        button.setText(String.format("%c is the winner!", winner));
    }

    public void updateWinningBoard(java.util.List<Position> positions, char winner, JButton[][] board, JLabel button) {
        for (int i = 0; i < 3; i++){
            board[positions.get(i).i()][positions.get(i).j()].setForeground(Color.GREEN);
        }
        button.setText(String.format("%c is the winner!", winner));
    }

    public void initializeFrame(JFrame frame) {
        frame.setVisible(true);
        frame.setSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    public void initializeStart(JButton start) {
        start.setBackground(Color.DARK_GRAY);
        start.setForeground(Color.white);
        start.setFont(new Font("Arial", Font.BOLD, 50));
        start.setFocusable(false);
        start.setText("Start");
    }

    public void initializeBackButton(BackButton Button) {
        var backButton = Button.getBackButton();
        backButton.setFont(new Font("Arial", Font.PLAIN, 30));
        backButton.setBackground(Color.darkGray);
        backButton.setForeground(Color.white);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setFocusable(false);
    }

    public void initializeRefreshButton(RefreshButton Button) {
        var refreshButton = Button.getRefreshButton();
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 30));
        refreshButton.setBackground(Color.darkGray);
        refreshButton.setForeground(Color.white);
        refreshButton.setBorderPainted(false);
        refreshButton.setFocusPainted(false);
        refreshButton.setFocusable(false);
    }

    public void initializeBoardPanel(JPanel boardPanel) {
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
    }

    public void initializeTextLabel(JLabel textLabel) {
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);
    }


}
