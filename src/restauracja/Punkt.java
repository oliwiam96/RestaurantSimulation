/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.io.Serializable;

/**
 * Punkt dwuwymiarowy (x, y).
 * @author Oliwia
 */
public class Punkt implements Serializable {

    private int x;
    private int y;

    Punkt(int x, int y) {
        setX(x);
        setY(y);
    }

    Punkt() {
        setX(0); //domyślnie (0,0)
        setY(0);
    }

    Punkt(Punkt punkt) {
        setX(punkt.getX());
        setY(punkt.getY());
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

}
