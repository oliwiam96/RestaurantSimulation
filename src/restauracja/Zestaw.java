/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Zbiór posiłków. Posiada zniżkę.
 * @author Oliwia
 */
public class Zestaw implements Serializable {

    private float znizka;
    private List<Posilek> listaPosilkow = new ArrayList<Posilek>();
    /**
     * Oblicza należność za zestaw.
     * @return cena zestawu po uzwględnieniu zniżki
     */
    public float getCenaZestawu() {
        float suma = 0;
        for (Posilek posilek : listaPosilkow) {
            suma += posilek.getCena();
        }
        return suma * (1 - this.getZnizka());
    }

    /**
     * @return the znizka
     */
    public float getZnizka() {
        return znizka;
    }

    /**
     * @param znizka the znizka to set
     */
    public void setZnizka(float znizka) {
        this.znizka = znizka;
    }

    /**
     * @return the listaPosilkow
     */
    public List<Posilek> getListaPosilkow() {
        return listaPosilkow;
    }

    /**
     * @param listaPosilkow the listaPosilkow to set
     */
    public void setListaPosilkow(List<Posilek> listaPosilkow) {
        this.listaPosilkow = listaPosilkow;
    }

}
