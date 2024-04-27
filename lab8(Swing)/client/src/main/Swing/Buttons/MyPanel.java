package main.Swing.Buttons;

import main.Swing.CirclePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

import static main.Client.data;
import static main.Client.myData;
import static main.Swing.CanvasPanel.myPanel;
import static main.Swing.Login.panel;
import static main.Swing.Main.frame;

public class MyPanel extends JPanel implements ActionListener {
    public Timer timer = new Timer(800, this);
    private static Font font = new Font("Consolas", Font.BOLD, 13);
    private static Vector<Ellipse2D.Double> circles = new Vector<>();
    private static Vector<String[]> dataVector = new Vector<>();
    private static int radius = 50;

    public MyPanel(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Ellipse2D.Double circle : circles) {
                    if (circle.contains(e.getPoint())) {
                        frame.setVisible(false);
                        myPanel.removeAll();
                        panel.removeAll();
                        frame.remove(panel);
                        frame.remove(myPanel);
                        new CirclePanel(dataVector.get(circles.indexOf(circle)));
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        circles = new Vector<>();
        dataVector = new Vector<>();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int count = 0;
        int y = 30;
        int x = 30;// Начальная координата X
        boolean isMyCircle;
        Color color = Color.darkGray;
        for (int i = 0; i < data.length; i++) {
            isMyCircle = false;
            color = Color.darkGray;
            String text = data[i][0];
            if(myData!=null){
                for(int j=0; j<myData.length; j++){
                    if (myData[j][0].equals(data[i][0])){
                        color = Color.GREEN;
                        isMyCircle = true;
                        break;
                    }
                }
            }
            y = 30 + count * 110;
            if (y > 600) {
                y = 30;
                x += 120;
                count = 0;
            }
            count++;

            Ellipse2D.Double circle = new Ellipse2D.Double(x, y, radius * 2, radius * 2);
            if(isMyCircle){
                circles.add(circle);
                dataVector.add(data[i]);
            }
            g2d.setColor(color);
            g2d.fill(circle);


            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();
            g2d.setColor(Color.red);
            g2d.setFont(font);
            g2d.drawString(text, x + radius - textWidth / 2, y + radius + textHeight / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(radius==50){
            radius=48;
        }else{
            radius = 50;
        }
        repaint();
    }
}
