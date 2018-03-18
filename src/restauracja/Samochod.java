/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.io.Serializable;

/**
 * Auto- wymaga kategorii B.
 *
 * @author Oliwia
 */
public class Samochod extends Pojazd implements Serializable {

    public Samochod(Restauracja restauracja) {
        super(restauracja);
    }

}
