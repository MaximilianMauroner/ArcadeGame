package arcadegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Puff Gabriel
 */
public class GameCage {

    int width, height;
    private Color colorFilled;
    private Color colorBorder;
    private BufferedImage image = null;
    private int i = 0;
    private int posX;
    private int posY;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public GameCage(int width, int height, Color colorFilled, Color colorBorder) {
        this.width = width;
        this.height = height;
        this.colorFilled = colorFilled;
        this.colorBorder = colorBorder;
    }

    public void draw(Graphics g) {
        g.setColor(colorFilled);
        g.fillRect(1, 1, width - 1, height - 1);

        g.setColor(colorBorder);
        g.drawRect(1, 1, width - 1, height - 1);

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
