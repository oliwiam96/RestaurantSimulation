/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa, która z pliku odczytuje pozycje w menu i przechowuje je w liście posiłków.
 * @author Oliwia
 */
public class Menu implements Serializable {

    public static final List<Posilek> listaPosilkow = new ArrayList<Posilek>();

    public static void wczytajMenu() throws FileNotFoundException {
        Scanner linia = new Scanner(new File("posilki.txt"));
        while (linia.hasNextLine()) {
            Scanner odczyt = new Scanner(linia.nextLine());
            String nazwa = odczyt.next();
            float cena = odczyt.nextFloat();
            String kategoria = odczyt.next();
            String rozmiarPorcji = odczyt.next();
            List<String> listaSkladnikow = new ArrayList<String>();
            // Set<String> listaSkladnikow = new HashSet<String>();
            while (odczyt.hasNext()) {
                listaSkladnikow.add(odczyt.next());
            }
            Posilek posilek = new Posilek(nazwa, cena, kategoria, rozmiarPorcji, listaSkladnikow);
            listaPosilkow.add(posilek);
        }
        
    }
}
