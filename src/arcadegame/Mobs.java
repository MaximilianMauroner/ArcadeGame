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
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * @author user
 */
public class Mobs implements Shape {

    private Background bg = new Background();

    private int x, y = 720 / 2, speedX = 7, speedY = 7;
    private int startx, starty, startstartx;
    private int dmg;
    private int isRight = 1;
    private BufferedImage image = null;
    private int animationC = 0;
    private Player player;
    private boolean isDrawn = false;

    public Mobs(Background background, Player pl) {
        Random ran = new Random();
        this.bg = background;
        this.x = ran.nextInt(bg.getWidth() - 1000) + 500;
        this.startstartx = x;
        this.startx = x;
        this.dmg = 1000;
        this.player = pl;
        try {
            image = ImageIO.read(new File("monsterR1.png"));
        } catch (IOException e) {
        }
    }


    @Override
    public void move(GameCage cage) {
        this.startx = startstartx + bg.getPosX();
        if (startx - 600 > x || x > startx + 600) {
            System.out.println("X " + x + "\tStartX " + startx);
            speedX *= -1;
            x += speedX;
            isRight *= -1;
        }
        //System.out.println(y + " y\t" + x + "X");
        /*String imgPath ="monsterR1.png";
        if (animationC % 3 == 0) {
                imgPath = "monsterR3.png";
            } else if (animationC % 2 == 0) {
                imgPath = "monsterR2.png";
            } else if (animationC % 1 == 0) {
                imgPath = "monsterR1.png";
            }
            System.out.println(animationC);
            animationC++;
            
            BufferedImage image = null;
        try {
            this.image = ImageIO.read(new File(imgPath));
        } catch (IOException ex) {
        }*/
        /*
        if (bg.getWidth() > speedX + x + image.getWidth() + bg.getPosX() * -1) {
            speedX *= -1;
            isRight *= -1;
        } else if (x + speedX <= 0) {
            speedX *= -1;
            isRight *= -1;
        }*/

        if (x + image.getWidth() + speedX + bg.getPosX() * -1 < bg.getTransparent().length && bg.getTransparent()[x + image.getWidth() + speedX + bg.getPosX() * -1][y + image.getHeight()] &&
                bg.getTransparent()[x - image.getWidth() + speedX + bg.getPosX() * -1][y + image.getHeight()]) {
            x += this.speedX;
        } else if (isRight == -1 && bg.getTransparent()[x + bg.getPosX() * -1][y]
                != bg.getTransparent()[x + image.getWidth() + bg.getPosX() * -1][y + image.getHeight()]) {
            this.x += this.speedX;
            this.y -= 5;
        } else if (isRight == 1 && x + image.getWidth() + bg.getPosX() * -1 < bg.getTransparent().length && bg.getTransparent()[x + image.getWidth() + bg.getPosX() * -1][y]
                != bg.getTransparent()[x + image.getWidth() + bg.getPosX() * -1][y + image.getHeight()]) {
            this.x += this.speedX;
            this.y -= 5;
        } else {
            this.x += speedX;
        }
    }

    @Override
    public void draw(Graphics g) {
        //System.out.println(bg.getTransparent()[x + image.getWidth() / 2 + (bg.getPosX() * -1)][y + image.getHeight()]);

        if (!hitGround()) {
            while (bg.getTransparent()[x + image.getWidth() / 2 + (bg.getPosX() * -1)][y + image.getHeight()]) {
                y = y + speedY;
            }
            g.drawImage(image, x, y, null);
        }
        g.drawImage(image, x, y, null);
    }


    @Override
    public int getPosX() {

        return x;
    }

    @Override
    public int getPosY() {
        return y;
    }

    @Override
    public int getWidth() {

        return image.getWidth();
    }

    @Override
    public int getHeight() {

        return image.getHeight();
    }

    @Override
    public boolean gravitiyaffected() {
        return true;
    }

    public int getStartx() {
        return startx;
    }

    public void setPosYNotStart() {
        if (!hitGround()) {
            y--;
        }
    }

    public boolean hitGround() {
        if (y + image.getHeight() < bg.getTransparent().length && (x + image.getWidth() / 2 + (bg.getPosX() * -1)) < bg.getTransparent()[y + image.getHeight()].length) {
            if (bg.getTransparent()[x + image.getWidth() / 2 + (bg.getPosX() * -1)][y + image.getHeight()]) {
                return true;
            }
        }
        return false;
    }

    public void setPosX(int x) {
        this.startx = x;
    }

    public void setPosY(int y) {
        this.starty = y;
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
