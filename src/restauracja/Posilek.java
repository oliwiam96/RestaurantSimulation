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
 * Posiłek (danie)- pozycja w menu.
 * @author Oliwia
 */
public class Posilek implements Serializable {

    private String nazwa;
    private float cena;
    private String kategoria;
    private String rozmiarPorcji;
    private List<String> listaSkladnikow = new ArrayList<String>();

    public Posilek(String nazwa, float cena, String kategoria, String rozmiarPorcji,
            List<String> listaSkladnikow) {
        setCena(cena);
        setKategoria(kategoria);
        setListaSkladnikow(listaSkladnikow);
        setNazwa(nazwa);
        setRozmiarPorcji(rozmiarPorcji);
    }

    /**
     * @return the nazwa
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * @param nazwa the nazwa to set
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * @return the cena
     */
    public float getCena() {
        return cena;
    }

    /**
     * @param cena the cena to set
     */
    public void setCena(float cena) {
        this.cena = cena;
    }

    /**
     * @return the kategoria
     */
    public String getKategoria() {
        return kategoria;
    }

    /**
     * @param kategoria the kategoria to set
     */
    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    /**
     * @return the rozmiarPorcji
     */
    public String getRozmiarPorcji() {
        return rozmiarPorcji;
    }

    /**
     * @param rozmiarPorcji the rozmiarPorcji to set
     */
    public void setRozmiarPorcji(String rozmiarPorcji) {
        this.rozmiarPorcji = rozmiarPorcji;
    }

    /**
     * @return the listaSkladnikow
     */
    public List<String> getListaSkladnikow() {
        return listaSkladnikow;
    }

    /**
     * @param listaSkladnikow the listaSkladnikow to set
     */
    public void setListaSkladnikow(List<String> listaSkladnikow) {
        this.listaSkladnikow = listaSkladnikow;
    }

}
