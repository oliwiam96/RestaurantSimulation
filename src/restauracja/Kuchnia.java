/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wątek kuchni przesuwa zamówienia złożone przez klientów (przygotowywane) do
 * listy zamówień gotowych, z której mogą je pobrać dostawcy.
 * Domyślnie 5 s na przygotowanie zamówienia.
 *
 * @author Oliwia
 */
public class Kuchnia implements Runnable, Serializable {

    private Ochroniarz zamek;
    private int sekNaPrzygotowanie;
    private Restauracja restauracja;

    /**
     * domyślnie 5s na przygotowanie zamówienia
     *
     * @param restauracja referncja na restaurację
     */
    public Kuchnia(Restauracja restauracja) {
        setRestauracja(restauracja);
        setZamek(restauracja.getOchroniarz());
        setSekNaPrzygotowanie(5); //domyślnie 5s
    }

    public Kuchnia(Restauracja restauracja, int sekNaPrzygotowanie) {
        setRestauracja(restauracja);
        setZamek(restauracja.getOchroniarz());
        setSekNaPrzygotowanie(sekNaPrzygotowanie);
    }

    /**
     * Metoda run w kuchni sprawdza, czy lista zamówień przygotowywanych jest
     * pusta, jeśli nie, wówczas sprawdza, czy są na tej liście jakieś
     * zamówienia, które zostały złożone ponad sekNaPrzygotowanie (sekund) temu.
     * W przypadku takich zamówień usuwa je z listy zamówień do przygotowania i
     * dodaje do listy zamówień gotowych.
     */
    @Override
    public void run() {
        while (true) {
            //$
            synchronized (getZamek()) {
                if (!restauracja.getZamowieniaPrzygotywane().isEmpty()) {
                    LocalTime teraz = LocalTime.now();
                    for (Iterator<Zamowienie> it = restauracja.getZamowieniaPrzygotywane().iterator(); it.hasNext();) {
                        Zamowienie zamowienie = it.next();
                        if (zamowienie.getGodzinaPrzyjecia().until(teraz, ChronoUnit.SECONDS) > this.getSekNaPrzygotowanie()) { // jezeli uplynelo wiecej niz 5s
                            //wydaj je z kuchni do dostarczenia
                            it.remove(); //usun z listy zamowien przygotowywanych
                            restauracja.getZamowieniaGotowe().add(zamowienie); //dodaj do listy zamowien gotowych do dostarczenia        
                        }
                    }
                }
            }
        }
    }

    /**
     * @return the zamek
     */
    public Ochroniarz getZamek() {
        return zamek;
    }

    /**
     * @param zamek the zamek to set
     */
    public void setZamek(Ochroniarz zamek) {
        this.zamek = zamek;
    }

    /**
     * @return the restauracja
     */
    public Restauracja getRestauracja() {
        return restauracja;
    }

    /**
     * @param restauracja the restauracja to set
     */
    public void setRestauracja(Restauracja restauracja) {
        this.restauracja = restauracja;
    }

    /**
     * @return the sekNaPrzygotowanie
     */
    public int getSekNaPrzygotowanie() {
        return sekNaPrzygotowanie;
    }

    /**
     * @param sekNaPrzygotowanie the sekNaPrzygotowanie to set
     */
    public void setSekNaPrzygotowanie(int sekNaPrzygotowanie) {
        this.sekNaPrzygotowanie = sekNaPrzygotowanie;
    }

}
