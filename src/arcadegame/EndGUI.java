/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadegame;

import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author leirb
 */
public class EndGUI {

    private JFrame frame;
    private JPanel panel;
    private BoxLayout boxlayout;
    private JLabel lbl1;
    private ImageIcon pic;
    private int x;
    private int y;

    public EndGUI(boolean victory) {
        setPic(victory);
        setPicXY();
        initGui();
    }

    public void setPic(boolean victory) {
        if (victory) {
            pic = new ImageIcon("vic.png");
        } else {
            pic = new ImageIcon("def.png");
        }
    }

    public void setPicXY() {
        x = pic.getIconWidth() + 60;
        y = pic.getIconHeight() + 60;
    }

    public void panel() {
        panel = new JPanel();
        boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setSize(x, y);
        panel.setBorder(new EmptyBorder(new Insets(15, 15, 15, 15)));//top left bot right
        panel.add(lbl1);
    }

    public void lbl() {
        lbl1 = new JLabel("");
        lbl1.setSize(x, y);
        lbl1.setIcon(pic);
    }

    public void initGui() {
        frame = new JFrame("The Adventure of Arnulf");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lbl();
        panel();

        frame.setSize(x, y);
        frame.setResizable(false);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        lbl1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                TitelScreenGUI gameGUI = new TitelScreenGUI();
                gameGUI.initGUI();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

    }

}
