/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

/**
 * Klient okazjonalny, o nic nie rozszerza zwykłego klienta.
 * @author Oliwia
 */
public class KlientOkazjonalny extends Klient{
    
    public KlientOkazjonalny(Restauracja restauracja){
        super(restauracja);
    }
    
    
    @Override
    public void wyswietlInformacje(){ 
        System.out.println("~~~Klient Okazjonalny~~~");
        super.wyswietlInformacje();
    }
}
