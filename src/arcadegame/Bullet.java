package arcadegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.*;

public class Bullet implements Shape {

    int speedY = 0, radius = 5, y, x;
    int speedX;
    Color c = new Color(255, 255, 255);
    BufferedImage image = null;

    public Bullet(int x, int y, int speedX) {
        this.y = y;
        this.x = x + 31;
        this.speedX = speedX;
        try {
            image = ImageIO.read(new File("bullet.png"));

        } catch (IOException e) {
            System.out.println("asdökfj");
        }
    }

    @Override
    public boolean gravitiyaffected() {
        return false;
    }

    @Override
    public void move(GameCage cage) {
        this.x += this.speedX;

    }

    @Override
    public void draw(Graphics g) {
        //g.setColor(c);
        //g.fillOval(x, y, radius * 2, radius * 2);
        g.drawImage(image, x, y, null);

    }

    @Override
    public int getPosY() {
        return this.y;
    }

    @Override
    public int getPosX() {
        return this.x;

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
    public int getSpeedX() {
        return speedX;
    }

    @Override
    public int getSpeedY() {
        return speedY;
    }
}
