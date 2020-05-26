/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadegame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author MaxiMauroner
 */
public class Background implements Shape {

    private BufferedImage image = null;
    private int x = 0;
    private int y = 0;
    private int speedX = 7;
    private int speedY = 0;
    private char c;
    private char[] arr = new char[4];
    private boolean[][] transparent;
    private Player player;
    private boolean won = false;

    public Background() {
        try {
            image = ImageIO.read(new File("map.png"));
            transparent = new boolean[image.getWidth()][image.getHeight()];
        } catch (IOException e) {
        }
    }

    public void setChar(char c) {
        this.c = c;

    }

    public void fillarr() {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                if (isTransparent(image, j, i)) {
                    transparent[j][i] = true;
                }
            }
        }
    }

    public boolean isTransparent(BufferedImage image, int x, int y) {
        int pixel = image.getRGB(x, y);
        return (pixel >> 24) == 0x00;
    }

    public boolean[][] getTransparent() {
        return transparent;
    }

    public void setTransparent(boolean[][] transparent) {
        this.transparent = transparent;
    }

    public void draw(Graphics g) {
        if (x - player.getPosX() == -10463) {
            JOptionPane.showMessageDialog(null, "YOU WIN");
            won = true;
            System.exit(0);
        }
        g.drawImage(image, x, y, null);
    }

    public int getPosX() {
        return x;
    }

    public int getPosY() {
        return y;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public boolean isWon() {
        return won;
    }

    @Override
    public void move(GameCage cage) {
        System.out.println(x - player.getPosX());
        char[] arr = {'w', 'a', 'd', 's'};
//        System.out.println(x-player.getPosX());
        if (player.getPosX() + (player.getWidth() / 2) != 300) { //checks if player is left or right to see if the player moves or the background moves
            if (player.getPosX() + player.getWidth() / 2 > 300) {
                player.isRight();
            } else {
                player.isLeft();
            }
        }
        if (player.isLeft() || player.isRight()) {
            if (player.isLeft()) {
                if (player.getPosX() + (player.getWidth() / 2) < 300 + player.getSpeedX() && player.getPosX() + (player.getWidth() / 2) > 0) {
                } else {
                    player.setLeft(false);
                }
            } else if (player.isRight()) {
                if (player.getPosX() + (player.getWidth() / 2) > 300 - player.getSpeedX() && player.getPosX() + (player.getWidth() / 2) < cage.width) {
                } else {
                    player.setRight(false);
                }
            }
            player.move(cage, c);
        } else if (arr[1] == c && transparent[player.getPosX() + x * -1][player.getPosY()]
                != transparent[player.getPosX() + player.getWidth() + x * -1][player.getPosY() + player.getHeight()]) {
            //links nach oben
            x = x + speedX;
            player.setY(player.getPosY() - 8);
        } else if (arr[1] == c && transparent[player.getPosX() - player.getWidth() + x * -1][player.getPosY()]) {
            //links
            if (x - (cage.width / 2) >= (-cage.width / 2)) {
                player.move(cage, 'a');
                player.setLeft(true);
            } else {
                x = x + speedX;
                c = '0';
            }
        } else if (arr[2] == c && transparent[player.getPosX() + player.getWidth() + x * -1][player.getPosY()]) {
            //rechts
            if (arr[2] == c && transparent[player.getPosX() + player.getWidth() + x * -1][player.getPosY() - 5]
                    != transparent[player.getPosX() + player.getWidth() + x * -1][player.getPosY() + player.getHeight()]) {
                // rechts schräg nach oben
                x = x - speedX;
                player.setY(player.getPosY() - 8);

            } else if (((x - (cage.width / 2)) < (image.getWidth() - (cage.width / 2)) * -1)) {//same as above but other side
                player.move(cage, 'd');
                player.setRight(true);
            } else {
                x = x - speedX;
                c = '0';
            }
        } else if (arr[0] == c) {//teleport ahead; is fixed; have to add at every single drop;
            if (x <= -1975 && x >= -2025) {
                //System.out.println("IM IN");
                x = -2250;
            } else if (x <= -3700 && x >= -3850) {
                x -= 350;
            }
        }

    }

    public Player setBall(Player b) {
        return player = b;
    }

    @Override
    public boolean gravitiyaffected() {
        return false;
    }

    @Override
    public int getSpeedX() {
        return speedX;
    }

    @Override
    public int getSpeedY() {
        return speedY;
    }
}
