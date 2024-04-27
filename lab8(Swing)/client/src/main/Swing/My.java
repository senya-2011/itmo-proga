package main.Swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static main.Client.messages;
import static main.Swing.CanvasPanel.myPanel;
import static main.Swing.Login.panel;

public class My {
    private static JFrame frame;
    public static DefaultTableModel myModel;
    private static JTable table;
    private static Font font = new Font("Consolas", Font.ROMAN_BASELINE, 13);
    private static Font font1 = new Font("Consolas", Font.ITALIC, 30);

    public My(JFrame frame){
        this.frame = frame;
        makePanel();
    }
    private void makePanel(){
        panel.removeAll();
        panel.setLayout(null);
        panel.setBackground(Color.orange);

        //images && labels
        JLabel imageLabel = new JLabel(new ImageIcon(this.getClass().getResource("/duck.png")));
        imageLabel.setBounds(550, 450, 200, 200);
        panel.add(imageLabel);

        JLabel mainLabel = new JLabel(messages.getString("myMessage"));
        mainLabel.setFont(font1);
        mainLabel.setBounds(100, 450, 400, 75);
        mainLabel.setForeground(Color.red);
        panel.add(mainLabel);

        //table
        table = new JTable(myModel);
        table.setBackground(Color.lightGray);
        table.setFont(font);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 20, 750, 400);
        panel.add(scrollPane);

//        frame.getContentPane().add(scrollPane);
//        frame.pack();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = table.rowAtPoint(e.getPoint());
                    String[] rowData = new String[table.getColumnCount()];

                    for (int i = 0; i < table.getColumnCount(); i++) {
                        rowData[i] = table.getValueAt(row, i).toString();
                    }
                    frame.setVisible(false);
                    myPanel.removeAll();
                    panel.removeAll();
                    frame.remove(panel);
                    frame.remove(myPanel);
                    new CirclePanel(rowData);
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
