package main.Swing;

import main.Swing.Buttons.Menu;
import main.Swing.Buttons.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static main.Client.messages;
import static main.Client.sendRequest;
import static main.Swing.CanvasPanel.myPanel;
import static main.Swing.Login.panel;
import static main.Swing.Main.frame;

public class App implements ActionListener{
    private static String username;
    private static Font font;

    public static JMenuBar menuBar;
    private static JMenu choice;
    private static JMenuItem myItem;
    private static JMenuItem collectionItem;
    private static JMenuItem canvasItem;
    private static JMenuItem exitItem;
    private static JMenu userMenu;
    private static JMenuItem logoutItem;
    private Timer timer = new Timer(5000, this);

    public App(String username){
        this.username = username;
        timer.start();
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setTitle(messages.getString("title"));
        makePanel();
    }
    private void makePanel(){
        font = new Font("Consolas", Font.ITALIC, 50);
        panel.removeAll();
        panel.setLayout(null);
        panel.setBackground(Color.orange);

        //MainMenu
        choice = new main.Swing.Buttons.Menu(messages.getString("menu"));
        choice.setIcon(new ImageIcon(this.getClass().getResource("/menu.png")));
        myItem = new MenuItem(messages.getString("myItem"));
        collectionItem = new MenuItem(messages.getString("colletionItem"));
        canvasItem = new MenuItem(messages.getString("canvasItem"));
        exitItem = new MenuItem(messages.getString("exitItem"));

        choice.add(myItem);
        choice.add(collectionItem);
        choice.add(canvasItem);
        choice.setBorderPainted(true);
        choice.addSeparator();
        choice.add(exitItem);

        menuBar = new JMenuBar();
        menuBar.setBackground(Color.darkGray);
        menuBar.add(choice);
        menuBar.setBorderPainted(false);

        //userMenu
        userMenu = new Menu(username);
        userMenu.setIcon(new ImageIcon(this.getClass().getResource("/user.png")));

        logoutItem = new MenuItem(messages.getString("logout"));
        logoutItem.setIcon(new ImageIcon(this.getClass().getResource("/logout.png")));
        userMenu.add(logoutItem);

        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(userMenu);

        JLabel imageLabel = new JLabel(new ImageIcon(this.getClass().getResource("/cheetos.png")));
        imageLabel.setBounds(100, 100, 800, 800);
        panel.add(imageLabel);
        JLabel mainLabel = new JLabel(messages.getString("cheetos"));
        mainLabel.setFont(font);
        mainLabel.setForeground(Color.RED);
        mainLabel.setBounds(10, 40, 500, 60);
        panel.add(mainLabel);

        //add panel
        frame.setJMenuBar(menuBar);
        frame.add(panel);
        frame.setVisible(true);


        //Actions

        canvasItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.setJMenuBar(null);
                panel.removeAll();
                frame.remove(panel);
                frame.remove(myPanel);
                new CanvasPanel();
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(-1);
            }
        });
        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.setJMenuBar(null);
                panel.removeAll();
                frame.remove(myPanel);
                frame.remove(panel);
                new Login(frame);
            }
        });
        collectionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.remove(panel);
                frame.remove(myPanel);
                new CollectionPanel();
            }
        });
        myItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.remove(panel);
                frame.remove(myPanel);
                new My(frame);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sendRequest("return");
    }
}
