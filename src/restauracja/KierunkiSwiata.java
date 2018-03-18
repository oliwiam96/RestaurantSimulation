/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.io.Serializable;

/**
 * Enum do przechowywania informacji, w którą stronę jedzie dostawca.
 *
 * @author Oliwia
 */
public enum KierunkiSwiata implements Serializable {
    
    POLNOC(0, 1),
    POLUDNIE(0, -1),
    ZACHOD(-1, 0),
    WSCHOD(1, 0);

    /**
     * @return the x
     */
    
    private final int x; //predkosc vx
    private final int y; //predkosc vy
    
    KierunkiSwiata(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    
    
}
