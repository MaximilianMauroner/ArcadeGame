/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadegame;

import java.awt.Graphics;

/**
 * @author user
 */
public interface Shape {

    public boolean gravitiyaffected();

    public void move(GameCage cage);

    public void draw(Graphics g);

    public int getPosX();

    public int getPosY();

    public int getWidth();

    public int getHeight();

    public int getSpeedX();

    public int getSpeedY();
}
