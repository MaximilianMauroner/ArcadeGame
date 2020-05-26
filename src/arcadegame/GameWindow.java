/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadegame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.*;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.*;
import javax.imageio.ImageIO;

/**
 * @author Puff Gabriel
 */
public class GameWindow extends JFrame implements KeyListener {

    private GamePanel panel;
    private GameCage cage;
    private ArrayList<Shape> shapes;
    private JButton[] button = new JButton[10];
    private boolean[] charpressed = new boolean[5];
    private Player player;
    private Background bg;
    private Mobs[] mobies = new Mobs[10];
    private int animationC = 0;

    public GameWindow(int w, int h) {
        shapes = new ArrayList<Shape>();
        this.setSize(w, h);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);
        cage = new GameCage(w, h, Color.DARK_GRAY, Color.orange);
        player = new Player(5, 5, 20, Color.WHITE);
        panel = new GamePanel(w, h, cage, player);
        addKeyListener(this);
        bg = new Background();
        shapes.add(bg);
        panel.addShape(bg);
        player.setBackgrund(bg);
        bg.setBall(player);
        this.add(panel);
        for (int i = 0; i < 1; i++) {
            /*button[i] = new Items(i);
            GridBagConstraints grid = new GridBagConstraints();
            grid.gridy = i;
            grid.ipady = 10;
            grid.ipadx = 0;
            grid.weightx = 0;
            grid.weighty = 0;
            this.add(button[i]);
            button[i].setPreferredSize(new Dimension(55, 35));
            button[i].setText("" + i);
            button[i].setFocusable(false);
            panel.add(button[i], grid);*/
            addGoomba(bg);
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    public void gameStart() {
        Thread gameThread = new Thread() {
            public void run() {
                while (true) {
                    gameUpdate();
                    repaint();
                    checkifDIED();
                    removeHitShape();
                    removeUsedBullets();
                    if (bg.isWon()) {
                        dispose();
                        EndGUI end = new EndGUI(true);
                    }
                    for (int i = 1; i < shapes.size(); i++) {
                        if (shapes.get(i).getPosY() + shapes.get(i).getHeight() + shapes.get(i).getSpeedY() >= 700) {
                            panel.removeShape(shapes.get(i));
                            shapes.remove(shapes.get(i));
                        }
                    }
                    try {
                        Thread.sleep(1000 / 50);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        gameThread.start();
    }

    public void gameUpdate() {
        for (int i = 1; i < shapes.size(); i++) {
            shapes.get(i).move(cage);
        }
    }

    public void addGoomba(Background bg) {
        for (int i = 0; i < 1; i++) {
            mobies[i] = new Mobs(bg, player);
            shapes.add(mobies[i]);
            panel.addShape(mobies[i]);
        }
    }

    public int ran(int j) {
        int i = 0;
        Random rn = new Random(j);
        return i;
    }


    public void movingAnimation(char c) {

        String imgPath = "";
        if (c == 'd') {
            player.setLookingleft(false);
            if (animationC % 3 == 0) {
                imgPath = "charR3.png";
                animationC = 0;
            } else if (animationC % 2 == 0) {
                imgPath = "charR2.png";
            } else if (animationC % 1 == 0) {
                imgPath = "charR1.png";
            }
            animationC++;
        } else if (c == 'a') {
            player.setLookingleft(true);
            if (animationC % 3 == 0) {
                imgPath = "charL3.png";
                animationC = 0;
            } else if (animationC % 2 == 0) {
                imgPath = "charL2.png";
            } else if (animationC % 1 == 0) {
                imgPath = "charL1.png";
            }
            animationC++;
        }

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imgPath));
        } catch (IOException ex) {
            Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        player.setImage(image);
    }


    @Override
    public void keyPressed(KeyEvent ke) {

        if (ke.getKeyChar() == 'd') {
            charpressed[3] = true;
            if (charpressed[3]) {
                //cage.setPosX(cage.getPosX() - 10); //ball.move(cage, 'd');F
                bg.setChar('d');
                bg.move(cage);
                movingAnimation('d'); //ANIMATION
            }
        } else if (ke.getKeyChar() == 's') {
            charpressed[2] = true;
            if (charpressed[2]) {
                bg.setChar('s');
                bg.move(cage);
            }
        } else if (ke.getKeyChar() == 'w') {
            charpressed[0] = true;
            if (charpressed[0]) {
                bg.setChar('w');
                bg.move(cage);
            }
        } else if (ke.getKeyChar() == 'a') {
            charpressed[1] = true;
            if (charpressed[1]) {
                bg.setChar('a');
                bg.move(cage);
                //cage.setPosX(cage.getPosX() + 10);
                movingAnimation('a');
                /*ANIMATION*/
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            // System.out.println("OK");
            if (player.isLookingleft()) {
                Bullet bul = new Bullet(player.getPosX() - player.getWidth() / 2, player.getPosY() + 50, -15);
                shapes.add(bul);
                panel.addShape(bul);
            } else if (!player.isLookingleft()) {
                Bullet bul = new Bullet(player.getPosX() + player.getWidth() / 2, player.getPosY() + 50, 15);
                shapes.add(bul);
                panel.addShape(bul);
            }
        } else {
            bg.setChar('0');
        }
    }

    public void removeHitShape() {
        ArrayList<Shape> aux = new ArrayList();
        for (int i = 0; i < shapes.size(); i++) {
            Shape s = shapes.get(i);

            if (s instanceof Bullet) {
                Bullet b = (Bullet) s;
                for (int j = 0; j < shapes.size(); j++) {
                    Shape t = shapes.get(j);

                    if (!(t instanceof Bullet)) {
                        if (t instanceof Background) {
                            int bulletX = b.getPosX() + b.getWidth();
                            int bulletY = b.getPosY() + b.getHeight();
                            if (bulletX >= t.getPosX() && bulletX <= t.getPosX() + t.getWidth() / 2 && bulletY >= t.getPosY() && bulletY <= t.getPosY() + t.getHeight() / 2) {
                                aux.add(t);
                                aux.add(b);
                            }
                        } else {
                            int bulletX = b.getPosX() + b.getWidth();
                            int bulletY = b.getPosY() + b.getHeight();
                            if (bulletX >= t.getPosX() && bulletX <= t.getPosX() + t.getWidth() && bulletY >= t.getPosY() && bulletY <= t.getPosY() + t.getHeight()) {
                                aux.add(t);
                                aux.add(b);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < aux.size(); i++) {
            panel.removeShape(aux.get(i));
            shapes.remove(aux.get(i));
        }
    }

    private void checkifDIED() {
        for (int i = 1; i < shapes.size(); i++) {
            Shape s = shapes.get(i);
            if (s instanceof Mobs) {
                if (s.getPosX() == player.getPosX() + player.getWidth()) {
                    dispose();
                    EndGUI end = new EndGUI(false);
                } else if (s.getPosX() == player.getPosX()) {
                    dispose();
                    EndGUI end = new EndGUI(false);

                }

            }
        }
    }

    private void removeUsedBullets() {
        ArrayList<Bullet> aux = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++) {
            Shape s = shapes.get(i);
            if (s instanceof Bullet) {
                Bullet b = (Bullet) s;
                if (b.getPosX() + b.getSpeedX() <= 0 || !bg.getTransparent()[bg.getPosX() * -1 + b.getPosX()][b.getPosY()]) {
                    aux.add(b);
                }
            }
        }
        for (int i = 0; i < aux.size(); i++) {
            shapes.remove(aux.get(i));
            panel.removeShape(aux.get(i));
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyChar() == 'd') {
            charpressed[3] = false;
        } else if (ke.getKeyChar() == 's') {
            charpressed[2] = false;
        } else if (ke.getKeyChar() == 'w') {
            charpressed[0] = false;
        } else if (ke.getKeyChar() == 'a') {
            charpressed[1] = false;

        }
    }
}
