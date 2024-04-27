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
import static main.Swing.Login.panel;
import static main.Swing.Main.frame;

public class AddPanel {
    public static SpaceMarine spaceMarine;
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
    private static JButton submit;

    private static String[] heartCount = {"1", "2", "3"};
    private static String[] weaponType = {"null" ,"PLASMA_GUN", "GRENADE_LAUNCHER", "INFERNO_PISTOL", "MULTI_MELTA"};
    private static String[] meleeWeapon = {"POWER_SWORD", "MANREAPER", "POWER_FIST"};
    private int status;
    public AddPanel(int status){
        this.status = status; //if - 1 просто add - 0
        makePanel();
    }
    private void makePanel(){
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
        nameField = new JTextField();
        nameField.setBounds(200, 10, 200, 30);
        panel.add(nameField);

        xField = new JFormattedTextField(formatterCoordinate);
        xField.setBounds(200, 70, 200, 30);
        panel.add(xField);

        yField = new JFormattedTextField(formatterCoordinate);
        yField.setBounds(200, 130, 200, 30);
        panel.add(yField);

        healthField = new JFormattedTextField(formatterHealth);
        healthField.setBounds(200, 190, 200, 30);
        panel.add(healthField);

        heartBox = new JComboBox(heartCount);
        heartBox.setBounds(200, 250, 200, 30);
        panel.add(heartBox);

        weaponBox = new JComboBox(weaponType);
        weaponBox.setBounds(200, 310, 200, 30);
        panel.add(weaponBox);

        meleeBox = new JComboBox(meleeWeapon);
        meleeBox.setBounds(200, 370, 200, 30);
        panel.add(meleeBox);

        chapterNameField = new JTextField();
        chapterNameField.setBounds(200, 430, 200, 30);
        panel.add(chapterNameField);

        chapterLegionField = new JTextField();
        chapterLegionField.setBounds(200, 490, 200, 30);
        panel.add(chapterLegionField);

        marinesField = new JFormattedTextField(formatterMarines);
        marinesField.setBounds(200, 550, 200, 30);
        panel.add(marinesField);

        //button
        submit = new JButton(messages.getString("sumbit"));
        submit.setBounds(300, 610, 100, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.ORANGE);
        panel.add(submit);

        frame.add(panel);
        frame.setVisible(true);

        //imgs
        JLabel imageLabel = new JLabel(new ImageIcon(this.getClass().getResource("/grayPony.png")));
        imageLabel.setBounds(550, 280, 200, 210);
        panel.add(imageLabel);
        JLabel image1Label = new JLabel(new ImageIcon(this.getClass().getResource("/whitePony.png")));
        image1Label.setBounds(550, 30, 200, 220);
        panel.add(image1Label);
        JLabel image2Label = new JLabel(new ImageIcon(this.getClass().getResource("/pinkPony.png")));
        image2Label.setBounds(550, 530, 200, 235);
        panel.add(image2Label);

        //Action
        submit.addActionListener(new ActionListener() {
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
                if(status==1){
                    sendRequest("add_if_max");
                }else{
                    sendRequest("add");
                }
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
