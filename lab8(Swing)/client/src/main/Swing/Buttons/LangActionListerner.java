package main.Swing.Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import static main.Client.messages;
import static main.Swing.Login.updateLang;

public class LangActionListerner implements ActionListener {
    private String lang;
    public LangActionListerner(String lang){
        this.lang = lang;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(lang.equals("ru")){
            messages = ResourceBundle.getBundle("messages", new Locale(lang));
        }else{
            messages = ResourceBundle.getBundle("messages", new Locale(lang));
        }
        updateLang();
    }
}
