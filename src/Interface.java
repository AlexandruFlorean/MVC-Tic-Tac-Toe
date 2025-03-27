import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel titleLabel = new JLabel();
    JPanel titlePanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][1];

    Interface() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        titleLabel.setBackground(Color.darkGray);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font ("Arial", Font.BOLD, 70));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setText("Tic-Tac-Toe");
        titleLabel.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 1));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        String[] texts = {"Player vs Player", "Player vs PC", "PC vs PC"};

        for (int i = 0; i < 3; i++) {
            JButton tile = new JButton(texts[i]);
            board[i][0] = tile;
            boardPanel.add(tile);

            tile.setBackground(Color.DARK_GRAY);
            tile.setForeground(Color.white);
            tile.setFont(new Font("Arial", Font.BOLD, 40));
            tile.setFocusable(false);

            int mode = i;

            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();

                    if (mode == 0) {
                        new PlayerVsPlayer();
                    } else if (mode == 1) {
                        new PlayervsPC();
                    } else if (mode == 2) {
                        new PCvsPC();
                    }
                }
            });
        }
    }
}
