/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcadegame;

import javax.swing.JButton;

/**
 * @author user
 */
public class Items extends JButton {

    private boolean isEmpty;
    private String item;
    int posX = 0;

    public Items(int x) {
        super();
        this.posX = x;
    }

    public boolean isIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

}
