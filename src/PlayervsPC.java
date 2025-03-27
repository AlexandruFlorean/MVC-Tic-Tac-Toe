import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PlayervsPC {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String player0 = "0";
    String currentPlayer = playerX;

    JButton backButton = new JButton("←");


    ImageIcon icon = new ImageIcon("src/images/refresh-page-option.png");
    Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon refreshIcon = new ImageIcon(scaledImage);
    JButton refreshButton = new JButton(refreshIcon);

    boolean gameOver = false;
    int turns = 0;

    PlayervsPC() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
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
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().isEmpty()) {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer.equals(playerX) ? player0 : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }

                        Random rand = new Random();
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
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new PlayervsPC();
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Interface();
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

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie");
    }

//    private SimpleEntry<Integer, Integer> getPossibleActions() {
//        SimpleEntry<Integer, Integer> listPossibleActions = new SimpleEntry<Integer, Integer>;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (board[i][j].getText().isEmpty())
//                    listPossibleActions[0] = i;
//            }
//        }
//        return listPossibleActions;
//    }

//    private JButton[][] result(JButton[][] board, JButton[][] possibleActions) {
//        JButton[] x = possibleActions[0];
//        JButton[] y = possibleActions[1];
//        board[x][y] = currentPlayer;
//    }


//    def player(board):
//    Xs = 0
//    Os = 0
//            # simply iterate over the given 2D array and calculate how many Xs and Os are there
//    for y_axis in board:
//            for x_axis in y_axis:
//            if x_axis == X:
//    Xs += 1
//    elif x_axis == O:
//    Os += 1
//            # if numer of Xs is smaller or equal to Os, it is a turn for an X because it always goes first
//    if Xs <= Os:
//            return X
//    else:  # otherwise it is a turn for an O
//        return O


//
/*    def actions(board):
    possible_actions = set() # set is used just to be sure there will only be unique tuples
    for y, y_axis in enumerate(board):
            for x, x_axis in enumerate(y_axis):
            # initial implementation puts variable EMPTY in all cells, which is equal to None
            if x_axis == EMPTY:
            possible_actions.add((y, x))
            return possible_actions

    //


    def result(board, action):
            if len(action) != 2:  # check if given action is a tuple of two elements
    # check if given action is within the boundaries of the board (3x3)
    raise Exception("result function: incorrect action")
    if action[0] < 0 or action[0] > 2 or action[1] < 0 or action[1] > 2:
    raise Exception("result function: incorrect action value")
    y, x = action[0], action[1]
    board_copy = copy.deepcopy(board) # using the imported library 'copy'
            # check if action is already there (even though we will call 'actions' before it)
    if board_copy[y][x] != EMPTY:
    raise Exception("suggested action has already been taken")
    else:  # here we use the player function to know which letter to put in the copy
    board_copy[y][x] = player(board)
    return board_copy

    Pair actions() {

    }*/

}