/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Klasa reprezentująca pojazdy. Przechowuje informacje o kat. prawa jazdy,
 * ładowności, maksymalnej prędkości.
 * 
 * Pojazd nie posiada położenia, rysowanie odbywa się poprzez dostawcę,
 * który w swoim wątku zmienia swoje położenie (nie ma potrzeby kopiowania tej informacji).
 *
 */
package restauracja;

import java.io.Serializable;

/**
 * Pojazd przechowuje informacje takie jak wymagana kategoria prawa jazdy,
 * ładowność aktualna, maksymalna, nr rejestracyjny, pojemność baku czy kierujący.
 * Nie ma nic wspólnego z rysowaniem.
 * @author Oliwia
 */
public abstract class Pojazd implements Serializable {

    private final KategoriaPrawaJazdy WYMAGANA_KATEGORIA_PRAWA_JAZDY;
    private final float LADOWNOSC_MAKS;
    private float ladownoscAktualna;
    private final float PREDKOSC_MAKS;
    private float predkoscAktualna;
    private String nrRejestracyjny;
    private final float POJEMNOSC_BAKU_MAKS;
    private float pojemnoscBakuAktualna;
    private Dostawca kierujacy;
    private KierunkiSwiata kierunek;
    private Restauracja restauracja;

    private boolean wolny = true;

    public Pojazd(Restauracja restauracja) {
        setRestauracja(restauracja);
        if (this instanceof Samochod) {
            WYMAGANA_KATEGORIA_PRAWA_JAZDY = KategoriaPrawaJazdy.B;
        } else if (this instanceof Skuter) {
            WYMAGANA_KATEGORIA_PRAWA_JAZDY = KategoriaPrawaJazdy.AM; //chyba takie trzeba na skuter?
        } else {
            WYMAGANA_KATEGORIA_PRAWA_JAZDY = KategoriaPrawaJazdy.B; // niech bedzie domyslnie B
        }
        setLadownoscAktualna(0);
        if(this instanceof Samochod){
         this.PREDKOSC_MAKS = 100;
         this.LADOWNOSC_MAKS = 10; //ile zamowien sie zmiesci
        }
        else{
            this.PREDKOSC_MAKS = 50;
            this.LADOWNOSC_MAKS = 5; //ile zamowien sie zmiesci
        }
        
        setPredkoscAktualna(0);
        setNrRejestracyjny("PO" + GeneratorPseudolosowy.generujLosowyNumer(5)); //a może PZ?
        this.POJEMNOSC_BAKU_MAKS = 200; //na ile wierzcholkow starczy
        setPojemnoscBakuAktualna(POJEMNOSC_BAKU_MAKS); //nowy zatankowany do pelna ;)
        setKierujacy(null);
        setKierunek(KierunkiSwiata.POLNOC); //cos trzeba ustawić
        wolny = true;
    }

    /**
     * Tankowanie pojazdu do pełna.
     */
    public void zatankujDoPelna() {
        setPojemnoscBakuAktualna(POJEMNOSC_BAKU_MAKS);
    }

    /**
     * Sprawdzanie, czy pojazd jest używany przez jakiegoś dostawcę (ma
     * kierującego).
     *
     * @return true, jesli pojazd ma kierującego, false w przeciwnym wypadku
     */
    public boolean czyUzywany() {
        return getKierujacy() != null;
    }

    /**
     * @return the LADOWNOSC_MAKS
     */
    public float getLADOWNOSC_MAKS() {
        return LADOWNOSC_MAKS;
    }

    /**
     * @return the ladownoscAktualna
     */
    public float getLadownoscAktualna() {
        return ladownoscAktualna;
    }

    /**
     * @param ladownoscAktualna the ladownoscAktualna to set
     */
    public void setLadownoscAktualna(float ladownoscAktualna) {
        this.ladownoscAktualna = ladownoscAktualna;
    }

    /**
     * @return the PREDKOSC_MAKS
     */
    public float getPREDKOSC_MAKS() {
        return PREDKOSC_MAKS;
    }

    /**
     * @return the predkoscAktualna
     */
    public float getPredkoscAktualna() {
        return predkoscAktualna;
    }

    /**
     * @param predkoscAktualna the predkoscAktualna to set
     */
    public void setPredkoscAktualna(float predkoscAktualna) {
        this.predkoscAktualna = predkoscAktualna;
    }

    /**
     * @return the nrRejestracyjny
     */
    public String getNrRejestracyjny() {
        return nrRejestracyjny;
    }

    /**
     * @param nrRejestracyjny the nrRejestracyjny to set
     */
    public void setNrRejestracyjny(String nrRejestracyjny) {
        this.nrRejestracyjny = nrRejestracyjny;
    }

    /**
     * @return the POJEMNOSC_BAKU_MAKS
     */
    public float getPOJEMNOSC_BAKU_MAKS() {
        return POJEMNOSC_BAKU_MAKS;
    }

    /**
     * @return the pojemnoscBakuAktualna
     */
    public float getPojemnoscBakuAktualna() {
        return pojemnoscBakuAktualna;
    }

    /**
     * @param pojemnoscBakuAktualna the pojemnoscBakuAktualna to set
     */
    public void setPojemnoscBakuAktualna(float pojemnoscBakuAktualna) {
        this.pojemnoscBakuAktualna = pojemnoscBakuAktualna;
    }

    /**
     * @return the kierujacy
     */
    public Dostawca getKierujacy() {
        return kierujacy;
    }

    /**
     * @param kierujacy the kierujacy to set
     */
    public void setKierujacy(Dostawca kierujacy) {
        this.kierujacy = kierujacy;
    }

    /**
     * @return the kierunek
     */
    public KierunkiSwiata getKierunek() {
        return kierunek;
    }

    /**
     * @param kierunek the kierunek to set
     */
    public void setKierunek(KierunkiSwiata kierunek) {
        this.kierunek = kierunek;
    }

    /**
     * @return the WYMAGANA_KATEGORIA_PRAWA_JAZDY
     */
    public KategoriaPrawaJazdy getWYMAGANA_KATEGORIA_PRAWA_JAZDY() {
        return WYMAGANA_KATEGORIA_PRAWA_JAZDY;
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
     * @return the wolny
     */
    public boolean isWolny() {
        return wolny;
    }

    /**
     * @param wolny the wolny to set
     */
    public void setWolny(boolean wolny) {
        this.wolny = wolny;
    }

}
