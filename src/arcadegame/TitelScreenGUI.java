/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadegame;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @author gabriel
 */
public class TitelScreenGUI {

    private JFrame frame;
    private ImagePanel panel;
    private BoxLayout boxlayout;
    private JLabel lbl1;
    private JLabel lbl2;
    private JLabel lbl3;
    private ImageIcon title;
    private ImageIcon play;
    private ImageIcon exit;

    public TitelScreenGUI() {
        initGUI();
    }

    public void setLbls() {
        lbl1 = new JLabel("");
        lbl1.setSize(1100, 300);
        title = new ImageIcon("title2.png");

        lbl2 = new JLabel("");
        lbl2.setSize(153, 81);
        play = new ImageIcon("play.png");

        lbl3 = new JLabel("");
        lbl3.setSize(153, 81);
        exit = new ImageIcon("exit.png");

        lbl1.setIcon(title);
        lbl2.setIcon(play);
        lbl3.setIcon(exit);
    }

    public void setPanel() {
        panel = new ImagePanel("titleScreenTest.png");
        boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setSize(1200, 720);
        panel.setBorder(new EmptyBorder(new Insets(50, 64, 50, 64)));


        panel.add(lbl1);
        panel.add(Box.createRigidArea(new Dimension(0, 150)));
        panel.add(lbl2);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lbl3);
    }

    public void initGUI() {
        frame = new JFrame("The Adventure of Arnulf");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLbls();
        setPanel();

        frame.setSize(1200, 720);
        frame.setBackground(Color.darkGray);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        frame.setVisible(true);

        lbl2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                GameWindow gw = new GameWindow(1200, 720);
                gw.pack();
                gw.setVisible(true);
                gw.gameStart();
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

        lbl3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
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
