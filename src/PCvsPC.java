import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class PCvsPC {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JPanel startPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton start = new JButton();
    JButton backButton = new JButton("←");


    ImageIcon icon = new ImageIcon("src/images/refresh-page-option.png");
    Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon refreshIcon = new ImageIcon(scaledImage);
    JButton refreshButton = new JButton(refreshIcon);

    JLabel[][] board = new JLabel[3][3];

    String playerX = "X";
    String player0 = "0";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;
    Timer timer;

    PCvsPC() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

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

        start.setBackground(Color.DARK_GRAY);
        start.setForeground(Color.white);
        start.setFont(new Font("Arial", Font.BOLD, 50));
        start.setFocusable(false);
        start.setText("Start");

        startPanel.setLayout(new BorderLayout());
        startPanel.add(start);
        startPanel.add(backButton, BorderLayout.WEST);
        startPanel.add(refreshButton, BorderLayout.EAST);
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

        refreshButton.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                frame.dispose();
                                                new PCvsPC();
                                            }
                                        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Interface();
            }
        });


        start.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    start.setEnabled(false);

                        timer = new Timer(1000, new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (gameOver) return;
                                Random rand = new Random();
                                JLabel tile;
                                do {
                                    int n = rand.nextInt(3);
                                    int m = rand.nextInt(3);
                                    tile = board[n][m];
                                } while (!tile.getText().isEmpty() && !gameOver);
                                if (tile.getText().isEmpty()) {
                                    tile.setText(currentPlayer);
                                    turns++;
                                    checkWinner();
                                    if (!gameOver) {
                                        currentPlayer = currentPlayer.equals(playerX) ? player0 : playerX;
                                        start.setText(currentPlayer + "'s turn.");
                                    }
                                }

                            }
                        });
                        timer.start();
                    }
                });
    }

    void checkWinner() {
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText().isEmpty()) continue;

            if (board[r][0].getText().equals(board[r][1].getText())
                    && board[r][1].getText().equals(board[r][2].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText().isEmpty()) continue;

            if (board[0][c].getText().equals(board[1][c].getText())
                    && board[1][c].getText().equals(board[2][c].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        if (board[0][0].getText().equals(board[1][1].getText())
                && board[0][0].getText().equals(board[2][2].getText())
                && !board[0][0].getText().isEmpty()) {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        if (board[0][2].getText().equals(board[1][1].getText())
                && board[0][2].getText().equals(board[2][0].getText())
                && !board[0][2].getText().isEmpty()) {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JLabel tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        start.setText(currentPlayer + " is the winner!");
        timer.stop();
    }

    void setTie(JLabel tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        start.setText("Tie");
        timer.stop();
    }
}