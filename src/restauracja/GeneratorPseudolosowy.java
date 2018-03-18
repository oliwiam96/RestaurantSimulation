/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.util.Random;

/**
 * Klasa pomocna przy generowaniu losowych ciągów znaków o różnych parametrach.
 * @author Oliwia
 */
public class GeneratorPseudolosowy {

    /**
     * Tworzy losowy napis zaczynający sie od wielkiej litery, pozostale litery
     * małe.
     * <p>
     * Liczba liter w napisie wynosi od minLiter do minLiter+zakres.
     *
     * @param minLiter minimalna liczba liter, jaką ma zawierać napis
     * @param zakres zakres, o jaki możemy powiększyć minimalną liczbę liter
     * @return obiekt String będący wylosowanym napisem
     */
    public static String generujLosowyNapisPierwszaWielka(int minLiter, int zakres) {
        Random random = new Random();
        String napis = "" + (char) (random.nextInt(26) + 'A');
        return (napis + generujLosowyNapis(minLiter - 1, zakres));
    }

    /**
     * Tworzy losowy napis złożony z małych liter.
     * <p>
     * Liczba liter w napisie wynosi od minLiter do minLiter+zakres.
     *
     * @param minLiter minimalna liczba liter, jaką ma zawierać napis
     * @param zakres zakres, o jaki możemy powiększyć minimalną liczbę liter
     * @return obiekt String będący wylosowanym napisem
     */
    public static String generujLosowyNapis(int minLiter, int zakres) {
        Random random = new Random();
        String napis = "";
        for (int i = 0; i < random.nextInt(zakres + 1) + minLiter; i++) {
            napis += (char) (random.nextInt(26) + 'a');
        }
        return napis;
    }

    /**
     * Generuje losowy String składający się z określonej liczby cyfr,
     * zaczynający się od podanego ciągu.
     *
     * @param ciagPierwszychCyfr początkowy ciąg, od którego zacznie się numer
     * @param ilePozostalychCyfr liczba cyfr, jakie mają być dopisane do
     * początkowego ciągu
     * @return obiekt String zawierający wylosowany ciąg
     */
    public static String generujLosowyNumer(String ciagPierwszychCyfr, int ilePozostalychCyfr) {
        Random random = new Random();
        String nrTelefonu = ciagPierwszychCyfr;
        for (int i = 0; i < ilePozostalychCyfr; i++) {
            nrTelefonu += (char) (random.nextInt(10) + '0');
        }
        return nrTelefonu;
    }

    /**
     * Generuje losowy String składający się z określonej liczby cyfr.
     *
     * @param ileCyfr liczba cyfr, jakie mają się znaleźć w ciągu
     * @return obiekt String zawierający wylosowany ciąg
     */
    public static String generujLosowyNumer(int ileCyfr) {
        return generujLosowyNumer("", ileCyfr);
    }

}
