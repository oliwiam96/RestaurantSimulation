/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.io.Serializable;
import java.util.Random;

/**
 * Klasa reprezentująca adres w mieście (nr domu i odpowiadający mu wierzchołek w grafie).
 * @author Oliwia
 */
public class Adres implements Serializable {

    private int nrDomu; //nr kolumny na mapie
    private Wierzcholek wierzcholek;
    Mapa mapa;

    public Adres(Mapa mapa) {
        this.mapa = mapa;
        Random random = new Random();
        setNrDomu(random.nextInt(Mapa.LICZBA_NR_DOMOW));
        System.out.println("nr domu " + nrDomu);
    }
    
    //specjalnie nie ma settera, zeby tego nie zmieniła jakaś żyrafa (ta klasa sama sobie zadba o referncję na wierzchołek)
    
    /**
     * @return the wierzcholek
     */
    public Wierzcholek getWierzcholek() {
        return wierzcholek;
    }

    /**
     * @return the nrDomu
     */
    public int getNrDomu() {
        return nrDomu;
    }

    /** Ustawia też odpowiednio wierzchołek.
     * @param nrDomu the nrDomu to set
     */
    public void setNrDomu(int nrDomu) {
        this.nrDomu = Integer.min(nrDomu, 10);
        this.wierzcholek = mapa.getWierzcholekDomu(nrDomu);
    }

    public void wyswietlInformacje() {
        System.out.println("Adres: " + getNrDomu());
    }
}
