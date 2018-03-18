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
 * Zbiera wszystkie informacje: listy klientów, dostawców, mapa, kuchnia. To ten
 * obiekt jest poddawany serializacji.
 *
 * @author Oliwia
 */
public class Restauracja implements Serializable {

    private final Wierzcholek POLOZENIE_RESTAURACJI; //wspolrzedne restauracji
    private List<Zamowienie> zamowieniaGotowe = new ArrayList<Zamowienie>();
    private List<Zamowienie> zamowieniaPrzygotywane = new ArrayList<Zamowienie>();
    private List<Klient> listaKlientow = new ArrayList<Klient>();
    private List<Dostawca> listaDostawcow = new ArrayList<Dostawca>();
    private List<Pojazd> listaPojazdow = new ArrayList<Pojazd>();
    private Ochroniarz ochroniarz;
    private Mapa mapa;
    private Kuchnia kuchnia;

    public Restauracja() {
        ochroniarz = new Ochroniarz();
        mapa = new Mapa(this);
        kuchnia = new Kuchnia(this);
        POLOZENIE_RESTAURACJI = mapa.getListaWierzcholkow().get(202); //ważne, by zrobić to po utworzeniu mapy
    }

    /**
     * @return the listaDostawcow
     */
    public List<Dostawca> getListaDostawcow() {
        return listaDostawcow;
    }

    /**
     * @param listaDostawcow the listaDostawcow to set
     */
    public void setListaDostawcow(List<Dostawca> listaDostawcow) {
        this.listaDostawcow = listaDostawcow;
    }

    /**
     * @return the listaPojazdow
     */
    public List<Pojazd> getListaPojazdow() {
        return listaPojazdow;
    }

    /**
     * @param listaPojazdow the listaPojazdow to set
     */
    public void setListaPojazdow(List<Pojazd> listaPojazdow) {
        this.listaPojazdow = listaPojazdow;
    }

    /**
     * @return the zamowieniaGotowe
     */
    public List<Zamowienie> getZamowieniaGotowe() {
        return zamowieniaGotowe;
    }

    /**
     * @param zamowieniaGotowe the zamowieniaGotowe to set
     */
    public void setZamowieniaGotowe(List<Zamowienie> zamowieniaGotowe) {
        this.zamowieniaGotowe = zamowieniaGotowe;
    }

    /**
     * @return the zamowieniaPrzygotywane
     */
    public List<Zamowienie> getZamowieniaPrzygotywane() {
        return zamowieniaPrzygotywane;
    }

    /**
     * @param zamowieniaPrzygotywane the zamowieniaPrzygotywane to set
     */
    public void setZamowieniaPrzygotywane(List<Zamowienie> zamowieniaPrzygotywane) {
        this.zamowieniaPrzygotywane = zamowieniaPrzygotywane;
    }

    /**
     * @return the listaKlientow
     */
    public List<Klient> getListaKlientow() {
        return listaKlientow;
    }

    /**
     * @param listaKlientow the listaKlientow to set
     */
    public void setListaKlientow(List<Klient> listaKlientow) {
        this.listaKlientow = listaKlientow;
    }

    /**
     * @return the ochroniarz
     */
    public Ochroniarz getOchroniarz() {
        return ochroniarz;
    }

    /**
     * @param ochroniarz the ochroniarz to set
     */
    public void setOchroniarz(Ochroniarz ochroniarz) {
        this.ochroniarz = ochroniarz;
    }

    /**
     * @return the mapa
     */
    public Mapa getMapa() {
        return mapa;
    }

    /**
     * @param mapa the mapa to set
     */
    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    /**
     * @return the kuchnia
     */
    public Kuchnia getKuchnia() {
        return kuchnia;
    }

    /**
     * @param kuchnia the kuchnia to set
     */
    public void setKuchnia(Kuchnia kuchnia) {
        this.kuchnia = kuchnia;
    }

    /**
     * Zwraca pojazd o pożądanej kategorii prawa jazdy, jeśli jest w garażu
     * restauracji, w przeciwnym wypadku null. W przypadku znalezienia pojazdu,
     * ustawia, że nie jest już wolny.
     *
     * @param kategoria kategoria prawa jazdy na pojazd, który dostawca chce
     * dostać
     * @return pojazd lub null
     */
    public synchronized Pojazd wydajPojazd(KategoriaPrawaJazdy kategoria) {
        for (Pojazd p : listaPojazdow) {
            if (p.getWYMAGANA_KATEGORIA_PRAWA_JAZDY() == kategoria && p.isWolny()) {
                p.setWolny(false);
                return p;
            }
        }
        return null;
    }

    /**
     * @return the POLOZENIE_RESTAURACJI
     */
    public Wierzcholek getPOLOZENIE_RESTAURACJI() {
        return POLOZENIE_RESTAURACJI;
    }

}
