package main.Swing;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static Font font = new Font("Consolas", Font.PLAIN, 13);
    public static JFrame frame;
    static Login login;

    public static void start() {
        frame = getFrame();
        login = new Login(frame);
    }

    static JFrame getFrame () {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(280, 220);
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("lab8");
        return jFrame;
    }
}



