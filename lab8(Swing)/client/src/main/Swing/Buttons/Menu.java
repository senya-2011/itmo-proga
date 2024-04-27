package main.Swing.Buttons;

import javax.swing.*;
import java.awt.*;

public class Menu extends JMenu {
    private static Font font = new Font("Consolas", Font.BOLD, 15);
    public Menu(String s){
        super(s);
        super.setContentAreaFilled(false);
        super.setBackground(Color.DARK_GRAY);
        super.setForeground(Color.ORANGE);
        super.setFont(font);
        super.setBorderPainted(false);
    }
}
