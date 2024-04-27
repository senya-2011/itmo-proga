package main.Swing.Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static main.Client.sendRequest;

public class MyActionListerner implements ActionListener {
    private String command;
    public MyActionListerner(String s){
        this.command = s;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        sendRequest(command);
    }
}
