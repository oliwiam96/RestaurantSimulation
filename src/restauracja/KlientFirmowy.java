/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

/**
 * Klient firmowy, posiada dodatkowe pola dot. firmy.
 * @author Oliwia
 */
public class KlientFirmowy extends Klient {

    private Adres adresKorespondencyjny;
    private String indywidualnyNrKontaBankowego;
    private String REGON;

    public KlientFirmowy(Restauracja restauracja) {
        super(restauracja);
        setAdresKorespondencyjny(new Adres(getRestauracja().getMapa()));
        setIndywidualnyNrKontaBankowego(GeneratorPseudolosowy.generujLosowyNumer(26));
        setREGON(GeneratorPseudolosowy.generujLosowyNumer(9));
    }


    /**
     * @return the adresKorespondencyjny
     */
    public Adres getAdresKorespondencyjny() {
        return adresKorespondencyjny;
    }

    /**
     * @param adresKorespondencyjny the adresKorespondencyjny to set
     */
    public void setAdresKorespondencyjny(Adres adresKorespondencyjny) {
        this.adresKorespondencyjny = adresKorespondencyjny;
    }

    /**
     * @return the indywidualnyNrKontaBankowego
     */
    public String getIndywidualnyNrKontaBankowego() {
        return indywidualnyNrKontaBankowego;
    }

    /**
     * @param indywidualnyNrKontaBankowego the indywidualnyNrKontaBankowego to
     * set
     */
    public void setIndywidualnyNrKontaBankowego(String indywidualnyNrKontaBankowego) {
        this.indywidualnyNrKontaBankowego = indywidualnyNrKontaBankowego;
    }

    /**
     * @return the REGON
     */
    public String getREGON() {
        return REGON;
    }

    /**
     * @param REGON the REGON to set
     */
    public void setREGON(String REGON) {
        this.REGON = REGON;
    }

    @Override
    public void wyswietlInformacje() {
        System.out.println("~~~Klient Firmowy~~~");
        super.wyswietlInformacje();
        System.out.print("Korespondencja ");
        adresKorespondencyjny.wyswietlInformacje();
        System.out.println("Indywidualny nr konta bankowego: " + getIndywidualnyNrKontaBankowego());
        System.out.println("REGON: " + getREGON());
    }

}
