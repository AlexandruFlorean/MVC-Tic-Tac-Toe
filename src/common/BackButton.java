package common;

import javax.swing.*;

public class BackButton {
    private final JButton backButton;

    public BackButton() {
        backButton = new JButton("←");
    }

    public JButton getBackButton() {
        return backButton;
    }
}
