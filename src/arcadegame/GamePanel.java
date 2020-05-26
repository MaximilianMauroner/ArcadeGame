/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadegame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * @author Puff Gabriel
 */
public class GamePanel extends JPanel {

    private int panelWidth;
    private int panelHeight;
    private BufferedImage pic;
    private ArrayList<Shape> shapes;
    private GameCage cage;
    private Player player;

    public GamePanel(int w, int h, GameCage cage, Player s) {

        panelHeight = h;
        panelWidth = w;
        this.cage = cage;
        shapes = new ArrayList();
        player = s;
    }

    public int getGroesse() {
        return shapes.size();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        cage.draw(g);

        if (shapes.size() > 0) {

            for (int i = 0; i < shapes.size(); i++) {
                shapes.get(i).draw(g);
            }
        }

        player.draw(g);
        g.drawString((shapes.get(0).getPosX() * -1 + player.getPosX()) + "x\t" + (player.getPosY() + player.getHeight()) + "Y", 20, 60);
    }

    public void removeShape(Shape b) {
        shapes.remove(b);
    }

    public void addShape(Shape b) {
        shapes.add(b);
    }

    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(panelWidth, panelHeight));
    }

}
