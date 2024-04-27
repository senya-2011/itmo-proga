package main.Swing;

import main.SpaceMarines.SpaceMarine;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import static main.Client.messages;
import static main.Client.sendRequest;
import static main.Swing.AddPanel.spaceMarine;
import static main.Swing.Login.panel;
import static main.Swing.Main.frame;

public class CirclePanel {
    private static Font font = new Font("Consolas", Font.PLAIN, 13);
    private static JTextField nameField;
    private static JFormattedTextField xField;
    private static JFormattedTextField yField;
    private static JFormattedTextField healthField;
    private static JComboBox heartBox;
    private static JComboBox weaponBox;
    private static JComboBox meleeBox;
    private static JTextField chapterNameField;
    private static JTextField chapterLegionField;
    private static JFormattedTextField marinesField;
    private static JButton updateButton;
    private static JButton removeButton;

    private static String[] heartCount = {"1", "2", "3"};
    private static String[] weaponType = {"null" ,"PLASMA_GUN", "GRENADE_LAUNCHER", "INFERNO_PISTOL", "MULTI_MELTA"};
    private static String[] meleeWeapon = {"POWER_SWORD", "MANREAPER", "POWER_FIST"};
    private static String[] data;
    private static String chapterName1;
    private static String chapterLegion1;
    private static String chapterMarines1;
    public CirclePanel(String[] circleData){
        this.data = circleData;
        makePanel();
    }
    private void makePanel(){
        //Поля которые были

        String name1 = data[1];
        String x1 = data[2].split("[, :]+")[1];
        String y1 = data[2].split("[, :]+")[3];
        String health1 = data[5];
        String heartCount1 = data[6];
        String weapon1 = data[7];
        String melee1 = data[8];
        if(data[3].trim().equals("null")){
            chapterName1 = data[3].trim();
            chapterLegion1 = data[3].trim();
            chapterMarines1 = "0";
        }else{
            String chapter = data[3].trim();
            chapterName1 = chapter.split("\n")[0].split(":")[1].trim();
            chapterLegion1 = chapter.split("\n")[1].split(":")[1].trim();
            chapterMarines1 = chapter.split("\n")[2].split(":")[1].trim();
        }



        panel.removeAll();
        panel.setLayout(null);
        panel.setBackground(Color.orange);

        //labels
        JLabel nameLabel = new JLabel(messages.getString("Name"));
        nameLabel.setBounds(10, 10, 150, 30);
        nameLabel = makeLabel(nameLabel);
        panel.add(nameLabel);

        JLabel xLabel = new JLabel("X");
        xLabel.setBounds(10, 70, 150, 30);
        xLabel = makeLabel(xLabel);
        panel.add(xLabel);

        JLabel yLabel = new JLabel("Y");
        yLabel.setBounds(10, 130, 150, 30);
        yLabel = makeLabel(yLabel);
        panel.add(yLabel);

        JLabel healthLabel = new JLabel(messages.getString("Health"));
        healthLabel.setBounds(10, 190, 150, 30);
        healthLabel = makeLabel(healthLabel);
        panel.add(healthLabel);

        JLabel healthCountLabel = new JLabel(messages.getString("heartCount"));
        healthCountLabel.setBounds(10, 250, 150, 30);
        healthCountLabel = makeLabel(healthCountLabel);
        panel.add(healthCountLabel);

        JLabel weaponLabel = new JLabel(messages.getString("weapon"));
        weaponLabel.setBounds(10, 310, 150, 30);
        weaponLabel = makeLabel(weaponLabel);
        panel.add(weaponLabel);

        JLabel meleeWeaponLabel = new JLabel(messages.getString("melee"));
        meleeWeaponLabel.setBounds(10, 370, 150, 30);
        meleeWeaponLabel = makeLabel(meleeWeaponLabel);
        panel.add(meleeWeaponLabel);

        JLabel chapterNameLabel = new JLabel(messages.getString("chapterName"));
        chapterNameLabel.setBounds(10, 430, 150, 30);
        chapterNameLabel = makeLabel(chapterNameLabel);
        panel.add(chapterNameLabel);

        JLabel chapterLegionLabel = new JLabel(messages.getString("legion"));
        chapterLegionLabel.setBounds(10, 490, 150, 30);
        chapterLegionLabel = makeLabel(chapterLegionLabel);
        panel.add(chapterLegionLabel);

        JLabel marinesCountLabel = new JLabel(messages.getString("marines"));
        marinesCountLabel.setBounds(10, 550, 150, 30);
        marinesCountLabel = makeLabel(marinesCountLabel);
        panel.add(marinesCountLabel);

        //форматоры
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatterCoordinate = new NumberFormatter(format);
        formatterCoordinate.setValueClass(Integer.class);
//        formatterCoordinate.setMinimum(0);
//        formatterCoordinate.setMaximum(Integer.MAX_VALUE);
        formatterCoordinate.setAllowsInvalid(false);

        NumberFormat format1 = NumberFormat.getInstance();
        NumberFormatter formatterHealth = new NumberFormatter(format1);
        formatterHealth.setValueClass(Integer.class);
        formatterHealth.setMinimum(1);
        formatterHealth.setMaximum(Integer.MAX_VALUE);
        formatterHealth.setAllowsInvalid(false);

        NumberFormat format2 = NumberFormat.getInstance();
        NumberFormatter formatterMarines = new NumberFormatter(format2);
        formatterMarines.setValueClass(Integer.class);
        formatterMarines.setMinimum(1);
        formatterMarines.setMaximum(1000);
        formatterMarines.setAllowsInvalid(false);

        //заполнение полей
        nameField = new JTextField(name1);
        nameField.setBounds(200, 10, 200, 30);
        panel.add(nameField);

        xField = new JFormattedTextField(formatterCoordinate);
        xField.setValue(Integer.parseInt(x1));
        xField.setBounds(200, 70, 200, 30);
        panel.add(xField);

        yField = new JFormattedTextField(formatterCoordinate);
        yField.setValue(Integer.parseInt(y1));
        yField.setBounds(200, 130, 200, 30);
        panel.add(yField);

        healthField = new JFormattedTextField(formatterHealth);
        healthField.setValue(Integer.parseInt(health1));
        healthField.setBounds(200, 190, 200, 30);
        panel.add(healthField);

        heartBox = new JComboBox(heartCount);
        heartBox.setSelectedItem(heartCount1);
        heartBox.setBounds(200, 250, 200, 30);
        panel.add(heartBox);

        weaponBox = new JComboBox(weaponType);
        weaponBox.setSelectedItem(weapon1);
        weaponBox.setBounds(200, 310, 200, 30);
        panel.add(weaponBox);

        meleeBox = new JComboBox(meleeWeapon);
        meleeBox.setSelectedItem(melee1);
        meleeBox.setBounds(200, 370, 200, 30);
        panel.add(meleeBox);

        chapterNameField = new JTextField(chapterName1);
        chapterNameField.setBounds(200, 430, 200, 30);
        panel.add(chapterNameField);

        chapterLegionField = new JTextField(chapterLegion1);
        chapterLegionField.setBounds(200, 490, 200, 30);
        panel.add(chapterLegionField);

        marinesField = new JFormattedTextField(formatterMarines);
        marinesField.setValue(Integer.parseInt(chapterMarines1));
        marinesField.setBounds(200, 550, 200, 30);
        panel.add(marinesField);

        //button
        updateButton = new JButton(messages.getString("update"));
        updateButton.setBounds(300, 610, 100, 30);
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.ORANGE);
        panel.add(updateButton);

        removeButton = new JButton(messages.getString("remove"));
        removeButton.setBounds(10, 610, 100, 30);
        removeButton.setBackground(Color.BLACK);
        removeButton.setForeground(Color.ORANGE);
        panel.add(removeButton);

        //images
        JLabel imageLabel = new JLabel(new ImageIcon(this.getClass().getResource("/sponge.png")));
        imageLabel.setBounds(550, 280, 200, 210);
        panel.add(imageLabel);
        JLabel image1Label = new JLabel(new ImageIcon(this.getClass().getResource("/patrik.png")));
        image1Label.setBounds(550, 30, 200, 220);
        panel.add(image1Label);
        JLabel image2Label = new JLabel(new ImageIcon(this.getClass().getResource("/squid.png")));
        image2Label.setBounds(550, 530, 200, 235);
        panel.add(image2Label);

        frame.add(panel);
        frame.setVisible(true);

        //Action
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String x = xField.getText();
                String y = yField.getText();
                String health = healthField.getText();
                String heartCount = (String) heartBox.getSelectedItem();
                String weapon = (String) weaponBox.getSelectedItem();
                String melee = (String) meleeBox.getSelectedItem();
                String chapterName = chapterNameField.getText();
                String chapterLegion = chapterLegionField.getText();
                String chapterMarines = marinesField.getText();
                spaceMarine = new SpaceMarine(name, x, y, health, heartCount, weapon, melee, chapterName, chapterLegion, chapterMarines);
                spaceMarine.setId(Integer.parseInt(data[0]));
                sendRequest("remove_by_id "+data[0]);
                sendRequest("add");
                frame.setVisible(false);
                frame.remove(panel);
                new CollectionPanel();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendRequest("remove_by_id "+data[0]);
                frame.setVisible(false);
                frame.remove(panel);
                new CollectionPanel();
            }
        });
    }
    private JLabel makeLabel(JLabel label){
        label.setBackground(Color.DARK_GRAY);
        label.setForeground(Color.orange);
        label.setOpaque(true);
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }
}
