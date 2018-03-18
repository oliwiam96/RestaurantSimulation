/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.io.Serializable;

/**
 * Skuter- wymaga kat. AM.
 *
 * @author Oliwia
 */
public class Skuter extends Pojazd implements Serializable {

    public Skuter(Restauracja restauracja) {
        super(restauracja);
    }

}
