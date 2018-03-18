/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.io.Serializable;

/**
 * Abstrakcyjna klasa, z której dziedziczy dostawca, przechowuje podstawowe informacje o człowieku.
 * @author Oliwia
 */
public abstract class Czlowiek implements Serializable {

    private String imie;
    private String nazwisko;
    private String PESEL;

    public Czlowiek() {
        setImie(GeneratorPseudolosowy.generujLosowyNapisPierwszaWielka(3, 5));
        setNazwisko(GeneratorPseudolosowy.generujLosowyNapisPierwszaWielka(3, 7));
        setPESEL(GeneratorPseudolosowy.generujLosowyNumer(11));
    }

    public Czlowiek(String imie, String nazwisko, String PESEL) {
        setImie(imie);
        setNazwisko(nazwisko);
        setPESEL(PESEL);
    }

    /**
     * @return the imie
     */
    public String getImie() {
        return imie;
    }

    /**
     * @param imie the imie to set
     */
    public void setImie(String imie) {
        this.imie = imie;
    }

    /**
     * @return the nazwisko
     */
    public String getNazwisko() {
        return nazwisko;
    }

    /**
     * @param nazwisko the nazwisko to set
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    /**
     * @return the PESEL
     */
    public String getPESEL() {
        return PESEL;
    }

    /**
     * @param PESEL the PESEL to set
     */
    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public void wyswietlInformacje() {
        System.out.println("Imie: " + getImie());
        System.out.println("Nazwisko: " + getNazwisko());
        System.out.println("PESEL: " + getPESEL());
    }

}
