package start_window;

import common.Constants;
import common.PlayMode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StartView {
    private final String[] texts = {PlayMode.PLAYER_VS_PLAYER.toString(), PlayMode.PLAYER_VS_PC.toString(), PlayMode.PC_VS_PC.toString()};
    private final JFrame frame = new JFrame("Tic-Tac-Toe");
    private final JLabel titleLabel = new JLabel();
    private final JPanel titlePanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final List<JButton> playModeButtons = new ArrayList<>();

    public void init() {
        initializeUIComponents();
    }

    public List<JButton> getPlayModeButtons() {
        return playModeButtons;
    }

    public void closeWindow() {
        frame.dispose();
    }

    private void initializeUIComponents() {
        frame.setVisible(true);
        frame.setSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
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

        for (int i = 0; i < 3; i++) {
            JButton button = new JButton(texts[i]);
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.white);
            button.setFont(new Font("Arial", Font.BOLD, 40));
            button.setFocusable(false);
            playModeButtons.add(button);
            boardPanel.add(button);
        }
    }
}
