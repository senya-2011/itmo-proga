package main.Swing;


import main.Swing.Buttons.MyActionListerner;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;

import static main.Client.messages;
import static main.Scripts.ScriptManager.file;
import static main.Swing.Main.frame;

import static main.Client.sendRequest;
import static main.Swing.Login.panel;


public class CollectionPanel {
    public static DefaultTableModel model;
    private static JButton sortButton;
    private static JButton addButton;
    private static JButton clearButton;
    private static JButton infoButton;
    private static JButton addIfButton;
    private static JButton countLessButton;
    private static JButton groupButton;
    private static JButton historyButton;
    private static JButton countWeapon;
    private static JButton scriptButton;
    private static JFormattedTextField countLessField;
    private static JComboBox countWeaponBox;
    private static Font font = new Font("Consolas", Font.PLAIN, 13);
    private static JTable table;
    private static String[][] data;
    public static String[] columnNames = {
            "id", "Name", "Coordinate",
            "Chapter", "Date", "Health",
            "HealthCount", "Weapon", "MeleeWeapon"
    };

    public static JLabel mainLabel= new JLabel();
    public CollectionPanel(){
        makePanel();
    }
    public void makePanel(){
        panel.removeAll();
        panel.setLayout(null);
        panel.setBackground(Color.orange);

        //buttons
        sortButton = madeButton(sortButton, "sort");
        sortButton.setBounds(10, 440, 80, 30);
        addButton = madeButton(addButton, "add");
        addButton.setBounds(100, 440, 80, 30);
        clearButton = madeButton(clearButton, "clear");
        clearButton.setBounds(190, 440, 80, 30);
        infoButton = madeButton(infoButton, "info");
        infoButton.setBounds(280, 440, 80, 30);
        addIfButton = madeButton(addIfButton, "addIfMax");
        addIfButton.setBounds(370, 440, 120,30);
        countLessButton = madeButton(countLessButton, "countLess");
        countLessButton.setBounds(500, 440, 120, 20);
        groupButton = madeButton(groupButton, "groupByChapter");
        groupButton.setBounds(630, 440, 140, 30);
        historyButton = madeButton(historyButton, "history");
        historyButton.setBounds(10, 500, 80, 30);
        countWeapon = madeButton(countWeapon, "Count greater weapon");
        countWeapon.setBounds(100, 500, 170, 20);
        scriptButton = madeButton(scriptButton, "execute script");
        scriptButton.setBounds(280, 500, 180, 30);

        panel.add(addButton);
        panel.add(sortButton);
        panel.add(clearButton);
        panel.add(infoButton);
        panel.add(addIfButton);
        panel.add(countLessButton);
        panel.add(groupButton);
        panel.add(historyButton);
        panel.add(countWeapon);
        panel.add(scriptButton);

        //fields && comboBoxes
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatterInt = new NumberFormatter(format);
        formatterInt.setValueClass(Integer.class);
//        formatterCoordinate.setMinimum(0);
        formatterInt.setMaximum(Integer.MAX_VALUE);
        formatterInt.setAllowsInvalid(false);
        countLessField = new JFormattedTextField(formatterInt);
        countLessField.setBounds(500, 460,  120, 20);

        String[] weaponType = {"1", "2", "3", "4", "5"};
        countWeaponBox = new JComboBox(weaponType);
        countWeaponBox.setBounds(100, 520, 170, 20);

        panel.add(countLessField);
        panel.add(countWeaponBox);

        //table
        table = new JTable(model);
        table.setBackground(Color.lightGray);
        table.setFont(font);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 20, 750, 400);
        panel.add(scrollPane);

        //label
        mainLabel.setBounds(40, 600, 600, 70);
        mainLabel.setFont(font);
        mainLabel.setBackground(Color.BLACK);
        mainLabel.setForeground(Color.WHITE);
        mainLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainLabel.setOpaque(true);
        mainLabel.setText(messages.getString("text"));
        JScrollPane scrollPane1 = new JScrollPane(mainLabel);
        scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane1.setBounds(40, 600, 600, 70);

        panel.add(scrollPane1);
        frame.add(panel);
        frame.setVisible(true);

        //actions
        infoButton.addActionListener(new MyActionListerner("info"));
        clearButton.addActionListener(new MyActionListerner("clear"));
        sortButton.addActionListener(new MyActionListerner("sort"));
        groupButton.addActionListener(new MyActionListerner("group_counting_by_chapter"));
        historyButton.addActionListener(new MyActionListerner("history"));

        countWeapon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendRequest("count_greater_than_weapon_type "+countWeaponBox.getSelectedItem());
            }
        });
        countLessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendRequest("count_less_than_chapter "+countLessField.getText().replaceAll(",", ""));
            }
        });

        scriptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if(f.getName().endsWith(".txt")){
                            return true;
                        }
                        return false;
                    }
                    @Override
                    public String getDescription() {
                        return null;
                    }
                });
                jFileChooser.showDialog(panel, "execute");
                File file1 = jFileChooser.getSelectedFile();
                if(file1!= null){
                    file = file1;
                    sendRequest("execute_script");
                }
            }
        });

        addIfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.remove(panel);
                new AddPanel(1);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.remove(panel);
                new AddPanel(0);
            }
        });
    }
    private JButton madeButton(JButton button, String name){
        button = new JButton(name);
        button.setBackground(Color.GRAY);
        button.setForeground(Color.ORANGE);
        return button;
    }
    private void updateTable(DefaultTableModel model, String[][] data){
        if(data!=null){
            model.setRowCount(0);
            for(String[] row: data){
                model.addRow(row);
            }
        }
    }
}
