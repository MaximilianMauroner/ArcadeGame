/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @author user
 */
public class Player {

    // Ball's center x and y (package access)
    private BufferedImage image = null;
    private int speedX, speedY = 10; // Ball's speed per step in x and y (package access)
    private int actualSpeedY;
    private boolean left = false, right = false;
    private Color color;
    private Background background;
    private int x = 300, y = 500;
    private boolean jumping;
    private boolean falling;
    private double gravity = 0.01;
    private boolean gravitiyaffected = true;

    public void setLookingleft(boolean lookingleft) {
        this.lookingleft = lookingleft;
    }

    public boolean isLookingleft() {
        return lookingleft;
    }

    private boolean lookingleft = false;

    public Player(int speedX, int speedY, int radius, Color color) {
        try {
            image = ImageIO.read(new File("charR1.png"));
        } catch (IOException e) {
        }
        this.speedX = speedX;
        this.color = color;
    }

    public void draw(Graphics g) {
        if (hitGround() && !jumping) {
            while (hitGround()) {
                y = y + speedY;
                g.drawImage(image, x, y, null);
            }
        } else {
            g.drawImage(image, x, y, null);
        }

    }

    public void setX(int x) {
        this.x = x;
    }

    public void move(GameCage cage, char c) {
        char[] arr = {'w', 'a', 's', 'd'};

        if (y + image.getHeight() + speedY < cage.getHeight() && y > 0) {
            if (c == arr[0]) {
                if (background.isWon()) {

                } else {
                    background.move(cage);
                    background.setChar('d');
                }
            } else if (c == arr[2]) {
                background.move(cage);
                background.setChar('a');
            } else if (c == arr[1] && x - image.getWidth() > 0 && background.getTransparent()[x - image.getWidth() + background.getPosX() * -1][y - image.getHeight()]) {
                this.x -= speedX;
            } else if (c == arr[3] && x + image.getWidth() < cage.width
                    && background.getTransparent()[x - image.getWidth() + background.getPosX() * -1][y]
                    && !background.getTransparent()[x - image.getWidth() + background.getPosX() * -1][y - image.getHeight() / 2]) {
                this.x += speedX;
                this.y += 3;
            } else if (c == arr[3] && x + image.getWidth() < cage.width && background.getTransparent()[x + image.getWidth() + background.getPosX() * -1][y - image.getHeight()]) {
                this.x += speedX;
            }
        }
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public boolean isGravitiyaffected() {
        return gravitiyaffected;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPosX() {
        return this.x;
    }

    public int getPosY() {
        return this.y;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public void setBackgrund(Background bg) {
        this.background = bg;
        background.fillarr();
    }

    public boolean hitGround() {

        if (background.getTransparent()[x + image.getWidth() + background.getPosX() * -1][y + image.getHeight() + 5]) {
            //System.out.println(" HIT GROUND TRUE\t" + background.getTransparent()[x + image.getWidth() + background.getPosX() * -1][y + image.getHeight()]);
            return true;
        }
        return false;
    }

    public boolean hitGround(int xplus, int yplus) {

        if (background.getTransparent()[x + image.getWidth() + background.getPosX() * -1 + xplus][y + image.getHeight() + 8 + yplus]) {
            //System.out.println(" HIT GROUND TRUE\t" + background.getTransparent()[x + image.getWidth() + background.getPosX() * -1][y + image.getHeight()]);
            return true;
        }
        return false;
    }

    public void inclinemoving() {
        if (background.getTransparent()[x + image.getWidth() + 2 + background.getPosX() * -1][y - image.getHeight()]) {

        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
