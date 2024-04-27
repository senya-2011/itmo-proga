package main.Swing;

import main.Swing.Buttons.LangActionListerner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import static main.Client.messages;
import static main.Client.registration;

public class Login {
    public static JPanel panel;
    private static JFrame frame;
    private static JButton loginButton;
    private static JLabel loginLabel;
    private static JLabel passwordLabel;
    private static JTextField loginField;
    private static JPasswordField passwordField;
    private static JButton ru;
    private static JButton en;
    private static JButton fi;
    private static JButton cat;
    private static Font mainfont;
    private static URL urlAmong;
    private static ImageIcon imgAmong;
    public static boolean serverStatus;
    private static JLabel answer;
    public Login(JFrame jFrame){
        this.frame = jFrame;
        this.frame.setSize(280, 220);
        frame.setTitle(messages.getString("loginWindow"));
        makePanel();
    }
    private void makePanel(){
        mainfont = new Font("Consolas", Font.BOLD, 15);
        frame.setIconImage(new ImageIcon(this.getClass().getResource("/mainicon.png")).getImage());
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.orange);

        //labels
        loginLabel = new JLabel(messages.getString("login"));
        loginLabel.setBounds(10, 20, 80, 30);
        loginLabel.setFont(mainfont);
        panel.add(loginLabel);
        passwordLabel = new JLabel(messages.getString("password"));
        passwordLabel.setBounds(10, 60, 80, 30);
        passwordLabel.setFont(mainfont);
        panel.add(passwordLabel);

        answer = new JLabel();
        answer.setBounds(10, 150, 200, 25);
        answer.setForeground(Color.red);
        answer.setFont(mainfont);
        panel.add(answer);

        //Fields
        loginField = new JTextField();
        loginField.setBounds(100, 20, 125, 30);
        loginField.setBackground(Color.lightGray);
        panel.add(loginField);
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 60, 125, 30);
        passwordField.setBackground(Color.lightGray);
        panel.add(passwordField);

        //button
        ImageIcon img = new ImageIcon(this.getClass().getResource("/login.png"));
        loginButton = new JButton(messages.getString("loginButton"));
        loginButton.setBounds(20, 100, 125, 40);
        loginButton.setBackground(Color.lightGray);
        loginButton.setFont(mainfont);
        loginButton.setIcon(img);
        panel.add(loginButton);

        //lang
        ru = new JButton();
        ru.setBounds(210, 160, 20, 20);
        ru.setIcon(new ImageIcon(this.getClass().getResource("/ru.png")));
        panel.add(ru);
        en = new JButton();
        en.setBounds(240, 160, 20, 20);
        en.setIcon(new ImageIcon(this.getClass().getResource("/en.png")));
        panel.add(en);
        fi = new JButton();
        fi.setBounds(180, 160, 20, 20);
        fi.setIcon(new ImageIcon(this.getClass().getResource("/fi.png")));
        panel.add(fi);
        cat = new JButton();
        cat.setBounds(150, 160, 20, 20);
        cat.setIcon(new ImageIcon(this.getClass().getResource("/cat.png")));
        panel.add(cat);

        ru.addActionListener(new LangActionListerner("ru"));
        en.addActionListener(new LangActionListerner("en"));
        fi.addActionListener(new LangActionListerner("fi"));
        cat.addActionListener(new LangActionListerner("cat"));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serverStatus=false;
                    boolean check;
                    if(loginField.getText().length()<3 || passwordField.getText().length()<3){
                        check=false;
                    }else{
                        check = checkLogin(loginField.getText(), passwordField.getText());
                    }

                    if (check){
                        answer.setForeground(Color.green);
                        frame.setVisible(false);
                        frame.remove(panel);
                        App app = new App(loginField.getText());
                    }else{
                        answer.setForeground(Color.red);
                        if(serverStatus){
                            answer.setText(messages.getString("serverError"));
                        }else{
                            answer.setText(messages.getString("error"));
                        }

                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frame.add(panel);
        frame.setVisible(true);
    }

    private boolean checkLogin(String login, String password) throws IOException, InterruptedException, ClassNotFoundException {
        return registration(login, password);
    }
    public static void updateLang(){
        loginLabel.setText(messages.getString("login"));
        passwordLabel.setText(messages.getString("password"));
        loginButton.setText(messages.getString("loginButton"));
        frame.setTitle(messages.getString("loginWindow"));
    }
}
