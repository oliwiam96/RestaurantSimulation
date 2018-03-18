/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Zbiór posiłków i zestawów.
 * @author Oliwia
 */
public class Zamowienie implements Serializable {

    private List<Posilek> listaPosilkow = new ArrayList<Posilek>();
    private List<Zestaw> listaZestawow = new ArrayList<Zestaw>();
    private Klient zamawiajacy;
    private LocalTime godzinaPrzyjecia = LocalTime.now();

    public Zamowienie(List<Posilek> listaPosilkow, List<Zestaw> listaZestawow, Klient zamawiajacy) {
        setListaPosilkow(listaPosilkow);
        setListaZestawow(listaZestawow);
        setZamawiajacy(zamawiajacy);
    }

    public Zamowienie(Klient zamawiajacy) {
        setZamawiajacy(zamawiajacy);
        Random random = new Random();
        int n = Integer.max(1, Integer.min(random.nextInt(Menu.listaPosilkow.size()), 5));
        for (int i = 0; i < n; i++) {
            int indeks = random.nextInt(Menu.listaPosilkow.size());
            Posilek posilek = Menu.listaPosilkow.get(indeks);
            listaPosilkow.add(posilek);
        }
        
    }
    
    /**
     * Obliczanie ceny zamówienia. Metoda ta uwzględnia zniżkę stałego klienta i punkty lojalnościowe.
     * @return 
     */

    public float getCenaZamowienia() {
        float suma = 0;
        for (Posilek posilek : listaPosilkow) {
            suma += posilek.getCena();
        }
        for (Zestaw zestaw : listaZestawow) {
            suma += zestaw.getCenaZestawu();
        }
        Wierzcholek cel = zamawiajacy.getAdres().getWierzcholek();
        Wierzcholek start = zamawiajacy.getRestauracja().getPOLOZENIE_RESTAURACJI();
        int odleglosc = zamawiajacy.getRestauracja().getMapa().obliczNajkrotszaSciezke(start, cel).size();
        if(suma < 50 || odleglosc > 10){ //dodaj cene za dowóz 10zl
            suma += 10;
        }
        if(zamawiajacy instanceof KlientStaly){
            suma = suma*(1 - ((KlientStaly)zamawiajacy).getZnizka());
            if(((KlientStaly)zamawiajacy).getLiczbaPunktowLojalnosciowych() > 
                    KlientStaly.MAX_PUNKTOW_LOJALNOSCIOWYCH)
            {
                ((KlientStaly) zamawiajacy).setLiczbaPunktowLojalnosciowych(
                        ((KlientStaly) zamawiajacy).getLiczbaPunktowLojalnosciowych()
                         - KlientStaly.MAX_PUNKTOW_LOJALNOSCIOWYCH);
                suma = Float.max(0, suma-100);
            }
        }
        return suma;
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

    /**
     * @return the listaZestawow
     */
    public List<Zestaw> getListaZestawow() {
        return listaZestawow;
    }

    /**
     * @param listaZestawow the listaZestawow to set
     */
    public void setListaZestawow(List<Zestaw> listaZestawow) {
        this.listaZestawow = listaZestawow;
    }

    /**
     * @return the zamawiajacy
     */
    public Klient getZamawiajacy() {
        return zamawiajacy;
    }

    /**
     * @param zamawiajacy the zamawiajacy to set
     */
    public void setZamawiajacy(Klient zamawiajacy) {
        this.zamawiajacy = zamawiajacy;
    }

    /**
     * @return the godzinaPrzyjecia
     */
    public LocalTime getGodzinaPrzyjecia() {
        return godzinaPrzyjecia;
    }

    /**
     * @param godzinaPrzyjecia the godzinaPrzyjecia to set
     */
    public void setGodzinaPrzyjecia(LocalTime godzinaPrzyjecia) {
        this.godzinaPrzyjecia = godzinaPrzyjecia;
    }

}
