package restauracja;

import java.io.Serializable;
import java.time.LocalTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * To mogła być piękna klasa do przechowywania informacji o godzinach i dniach pracy pracowników.
 * Starannie przeszukałam dokumentację, by wybrać najlepsze typy dat i czasu do reprezentacji dni i godzin.
 * Niestety później już mi nie starczyło czasu, by rozwinąć ten pomysł.
 * @author Oliwia
 */
public class DzienPracy implements Serializable {

    private LocalTime godzRozpoczecia;
    private LocalTime godzZakonczenia;

    public DzienPracy() {
        setGodzRozpoczecia(LocalTime.of(12, 0)); //domyslnie pracuje 12-20
        setGodzZakonczenia(LocalTime.of(20, 0));
    }

    public DzienPracy(LocalTime godzRozpoczecia, LocalTime godzZakonczenia) {
        setGodzRozpoczecia(godzRozpoczecia);
        setGodzZakonczenia(godzZakonczenia);
    }

    /**
     * @return the godzRozpoczecia
     */
    public LocalTime getGodzRozpoczecia() {
        return godzRozpoczecia;
    }

    /**
     * @param godzRozpoczecia the godzRozpoczecia to set
     */
    public void setGodzRozpoczecia(LocalTime godzRozpoczecia) {
        this.godzRozpoczecia = godzRozpoczecia;
    }

    /**
     * @return the godzZakonczenia
     */
    public LocalTime getGodzZakonczenia() {
        return godzZakonczenia;
    }

    /**
     * @param godzZakonczenia the godzZakonczenia to set
     */
    public void setGodzZakonczenia(LocalTime godzZakonczenia) {
        this.godzZakonczenia = godzZakonczenia;
    }

}
