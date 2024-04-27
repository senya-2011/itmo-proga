package main.Swing;

import main.Swing.Buttons.MyPanel;

import java.awt.*;


import static main.Swing.App.menuBar;
import static main.Swing.Main.frame;

public class CanvasPanel{

    public static MyPanel myPanel = new MyPanel();
    public CanvasPanel(){
       makePanel();
    }
    private void makePanel(){

        myPanel.setLayout(null);
        myPanel.setBackground(Color.orange);

        myPanel.timer.start();

        frame.setJMenuBar(menuBar);
        frame.add(myPanel);
        frame.setVisible(true);
    }

}
