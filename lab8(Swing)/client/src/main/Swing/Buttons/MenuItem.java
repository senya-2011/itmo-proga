package main.Swing.Buttons;

import javax.swing.*;
import java.awt.*;

public class MenuItem extends JMenuItem {
    private static Font font = new Font("Consolas", Font.PLAIN, 13);
    public MenuItem(String text) {
        super(text);
        super.setContentAreaFilled(false);
        super.setBackground(Color.DARK_GRAY);
        super.setForeground(Color.ORANGE);
        super.setFont(font);
        super.setBorderPainted(false);
    }

}
