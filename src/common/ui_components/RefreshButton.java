package common.ui_components;

import javax.swing.*;
import java.awt.*;

public class RefreshButton {

    private final JButton refreshButton;

    public RefreshButton() {
        ImageIcon icon = new ImageIcon("src/common/images/refresh-page-option.png");
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon refreshIcon = new ImageIcon(scaledImage);
        refreshButton = new JButton(refreshIcon);
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}
