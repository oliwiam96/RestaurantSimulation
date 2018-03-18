/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstrakcyjna klasa reprezentująca klienta restauracji.
 * @author Oliwia
 */
public abstract class Klient implements Runnable, Serializable {

    private String nazwa;
    private String nrTelefonu;
    private String email;
    private Adres adres;
    private Ochroniarz zamek;
    private Restauracja restauracja;
    private boolean running;
    List<Zamowienie> zamowienia = new ArrayList<>();
    //  TODO jeszcze dodac liste z zamowieniami klienta czekajacymi na odbior

    public Klient(Restauracja restauracja) {
        setRestauracja(restauracja);
        setZamek(restauracja.getOchroniarz());
        setNazwa(GeneratorPseudolosowy.generujLosowyNapisPierwszaWielka(5, 5));
        setNrTelefonu(GeneratorPseudolosowy.generujLosowyNumer("6", 8));
        setEmail(GeneratorPseudolosowy.generujLosowyNapis(5, 5)
                + '@'
                + GeneratorPseudolosowy.generujLosowyNapis(2, 1)
                + ".pl");
        setAdres(new Adres(restauracja.getMapa()));
        setRunning(true);
    }

    public void run() {
        while (this.isRunning()) {
            //System.out.println("Klient");
            Zamowienie zamowienie = new Zamowienie(this);
            if (this instanceof KlientStaly) {
                ((KlientStaly) this).setLiczbaPunktowLojalnosciowych(
                        ((KlientStaly) this).getLiczbaPunktowLojalnosciowych() + 100);
            }
            //$
            synchronized (getZamek()) {
                restauracja.getZamowieniaPrzygotywane().add(zamowienie);
            }
            //$
            try {
                Random random = new Random();
                int x = random.nextInt(5) + 3; //co losową liczbę sekund generuje nowe zamówienie
                Thread.sleep(x * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Klient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the adres
     */
    public Adres getAdres() {
        return adres;
    }

    /**
     * @param adres the adres to set
     */
    public void setAdres(Adres adres) {
        this.adres = adres;
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
     * @return the nrTelefonu
     */
    public String getNrTelefonu() {
        return nrTelefonu;
    }

    /**
     * @param nrTelefonu the nrTelefonu to set
     */
    public void setNrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public void wyswietlInformacje() {
        System.out.println("nazwa: " + getNazwa());
        System.out.println("nrTelefonu: " + getNrTelefonu());
        System.out.println("email: " + getEmail());
        getAdres().wyswietlInformacje();
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
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @param running the running to set
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

}
