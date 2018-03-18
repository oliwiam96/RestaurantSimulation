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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Zbiera informacje o grafie, położeniu resturacji,
 * umożliwia generowanie najkrótszych ścieżek i translacje nr domów na wierzchołki w grafie.
 * @author Oliwia
 */
public class Mapa implements Serializable {
    
    public static final int LICZBA_NR_DOMOW = 18;//liczba domków
    private List<Wierzcholek> listaWierzcholkow;
    Restauracja restauracja;

    /**
     * @return the listaWierzcholkow
     */
    public List<Wierzcholek> getListaWierzcholkow() {
        return listaWierzcholkow;
    }

    /**
     * @param listaWierzcholkow the listaWierzcholkow to set
     */
    public void setListaWierzcholkow(List<Wierzcholek> listaWierzcholkow) {
        this.listaWierzcholkow = listaWierzcholkow;
    }
    
    
    public Mapa(Restauracja restauracja) {
        //tylko na poczatku
        listaWierzcholkow = new ArrayList<Wierzcholek>();
        this.restauracja = restauracja;
        try {
            wczytajZPliku();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Zwraca referencje na wierzcholek, ktory reprezentuje dany nr Domu na mapie.
     * @param nrDomu nr Domu
     * 
     * Funkcja odwzorowuje nrDomu na wierzchołek.
     */
    public Wierzcholek getWierzcholekDomu(int nrDomu){
        //return listaWierzcholkow.get(184 - nrDomu);
        return listaWierzcholkow.get(184 + nrDomu);
    }

    private void wczytajZPliku() throws FileNotFoundException {
        Scanner linia = new Scanner(new File("graf1.txt"));
        int liczbaWierzcholkow = Integer.parseInt(linia.nextLine()); //ile wierzcholkow w grafie
        System.out.println("liczba wierzcholkow " + liczbaWierzcholkow);
        for (int i = 0; i < liczbaWierzcholkow; i++) {
            getListaWierzcholkow().add(new Wierzcholek());
        }
        while (linia.hasNextLine()) {
            Scanner odczyt = new Scanner(linia.nextLine());
            int nrWierz = odczyt.nextInt();
            listaWierzcholkow.get(nrWierz).getPunkt().setX(odczyt.nextInt());
            listaWierzcholkow.get(nrWierz).getPunkt().setY(odczyt.nextInt());
            listaWierzcholkow.get(nrWierz).setWolny(true);
            if(odczyt.nextInt() == 1){
                listaWierzcholkow.get(nrWierz).setSkrzyzowanie(true);
            }else{
                listaWierzcholkow.get(nrWierz).setSkrzyzowanie(false);
            }

            List<Wierzcholek> listaNastepnikow = new ArrayList<Wierzcholek>();

            while (odczyt.hasNext()) {
                listaNastepnikow.add(getListaWierzcholkow().get(odczyt.nextInt()));
            }
            getListaWierzcholkow().get(nrWierz).setListaNastepnikow(listaNastepnikow);
            System.out.println("Wierzcholek " + nrWierz + " " + listaNastepnikow.size() );
            
        }

    }

    /**
     *
     * Zwraca następny wierzchołek na trasie (od razu)
     *
     * @param poprzedni wierzcholek, którego następnik będzie zwrócony
     * @param cel wierzchołek, do którego ostatecznie zmierzamy
     * @return następny wierzchołek na drodze do celu
     */
    private Wierzcholek getNast(Wierzcholek poprzedni, Wierzcholek cel) {
        return null;

    }

    /**
     * Synchronizowana metoda, zwraca następny wierzchołek na trasie, jeśli jest
     * wolny, w przeciwnym wypadku ten sam, na jakim pojazd się znajduje (pojazd
     * stoi w miejscu i czeka na zwolnienie zasobu).
     *
     * Funkcja ta umożliwia wyłączny dostęp do skrzyżowań, zapobiega
     * przejeżdżaniu szybszych pojazdów przez wolniejsze.
     *
     * Możliwe zakleszczenie, gdy w mieście pojawi się za dużo pojazdów- i
     * dobrze, w normalnym świecie też może dojść do paraliżu, gdy wszystkie
     * ulice są zakorkowane.
     *
     * Dotyczy to także wyjazdu z restauracji, pojazd czeka aż zwolni się
     * wierzchołek, przy którym znajduje się restauracja.
     *
     * @param poprzedni
     * @param cel wierzchołek, do którego ostatecznie zmierzamy
     * @return
     */
    public synchronized Wierzcholek getNastWierz(Wierzcholek poprzedni, Wierzcholek cel) {
        Wierzcholek nast = getNast(poprzedni, cel);
        if (nast.isWolny()) {
            return nast;
        } else {
            return poprzedni;
        }
    }
    
    public synchronized boolean zgodaNaNast(List<Wierzcholek> sciezka, int indexNast) {
        Wierzcholek nastepny = sciezka.get(indexNast);
        Wierzcholek poprzedni = sciezka.get(indexNast-1);
        if(nastepny == restauracja.getPOLOZENIE_RESTAURACJI()){
            poprzedni.setWolny(true);
            return true;
        }
        if (nastepny.isWolny()) {
            nastepny.setWolny(false);
            poprzedni.setWolny(true);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Znajduje najkrótszą ścieżkę (algorytm Dijkstry)
     *
     * @param cel wierzchołek, do którego chcemy dotrzec
     * @param start wierzchołek, od którego zaczynamy jazdę
     * @return lista z wierzchołkami, jakie trzeba po kolei przejść
     */
    public List<Wierzcholek> obliczNajkrotszaSciezke(Wierzcholek start, Wierzcholek cel) {
        //zrobimy to na tablicach, bo graf w trakcie programu nie powinien się zmieniać (stały rozmiar itp.)

        int[] koszt = new int[this.getListaWierzcholkow().size()]; //koszt dojscia do danego wierzcholka
        int[] poprzednik = new int[this.getListaWierzcholkow().size()]; //tablica poprzednikow
        boolean[] sprawdzony = new boolean[this.getListaWierzcholkow().size()]; //czy sprawdzony

        //przygotowanie zmiennych
        for (int i = 0; i < this.getListaWierzcholkow().size(); i++) {
            koszt[i] = Integer.MAX_VALUE - 1000; //licznik sie przekrecal bez tego -1000
            poprzednik[i] = -1;
            sprawdzony[i] = false;
        }

        //rozwiazanie
        koszt[getListaWierzcholkow().indexOf(start)] = 0; //koszt dojscia do startu jest rowny 0
        for (int i = 0; i < getListaWierzcholkow().size(); i++) {
            int u = -1; //nr wierzcholka o najmniejszym koszcie dojscia w zbiorze niesprawdzonych wierzcholkow
            for (int j = 0; j < getListaWierzcholkow().size(); j++) {
                if (!sprawdzony[j] && ((u == -1) || koszt[j] < koszt[u])) {
                    u = j;
                }
            }
            sprawdzony[u] = true;
           // System.out.println("u = " + u);
            //modyfikacja wszystkich sasiadow u, ktorzy nie sa sprawdzeni
            for (Wierzcholek w : getListaWierzcholkow().get(u).getListaNastepnikow()) {
                if (!sprawdzony[listaWierzcholkow.indexOf(w)]
                        && (koszt[getListaWierzcholkow().indexOf(w)] > koszt[u] + 1)) {//UWAGA! tutaj jest zalozenie, ze kazda krawedz ma wage jeden (wierzcholki sa w rownych odeloglosciach), ale zmiana tego nie bylaby duzym problemem
                    koszt[getListaWierzcholkow().indexOf(w)] = koszt[u] + 1;
                    poprzednik[getListaWierzcholkow().indexOf(w)] = u;
                }

            }

        }
        // utworzenie tablicy po kolei (z tablicy poprzednicy trzeba je odczytac od konca)
        List<Wierzcholek> sciezka = new ArrayList(); //nie chce wykorzystywac stosu, wole zwykla liste, bo nie wiem, co dalej z nia zrobie (to swiadomy wybor)
        if (poprzednik[getListaWierzcholkow().indexOf(cel)] == -1) {
            return null; //nie udalo sie znalezc sciezki
        } else {
            Wierzcholek w = cel;
            while (w != start) { //sprawdzam referncje, wiec ==
                sciezka.add(0, w);
                w = getListaWierzcholkow().get(poprzednik[getListaWierzcholkow().indexOf(w)]); //???
            }
            sciezka.add(0, start);
            return sciezka;
        }
    }
}
