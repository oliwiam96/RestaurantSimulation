/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa testująca rysowanie, potem nieużywana w projekcie.
 *
 * @author Oliwia
 */
public class Test implements Runnable {

    int ID;
    Mapa mapa;
    Wierzcholek wierzcholekNast;
    Wierzcholek wierzcholekPoprzedni;
    int x;
    int y;
    KierunkiSwiata kierunek;
    double predkosc;

    public Test(int ID, Mapa mapa) {
        this.ID = ID;
        this.mapa = mapa;
    }

    @SuppressWarnings("empty-statement")
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

        Random r = new Random();
        Wierzcholek skad = mapa.getListaWierzcholkow().get(r.nextInt(180) + 1);

        Wierzcholek dokad = mapa.getListaWierzcholkow().get(r.nextInt(180) + 1);
        List<Wierzcholek> sciezka = mapa.obliczNajkrotszaSciezke(skad, dokad);
        for (int i = 0; i < sciezka.size(); i++) {
            System.out.println(ID + " sciezka " + mapa.getListaWierzcholkow().indexOf(sciezka.get(i)));

        }
        //while (true) {

        //for(Wierzcholek w: sciezka){
        wierzcholekPoprzedni = sciezka.get(0);
        boolean sciaganieX = false;
        boolean sciaganieXbylo = false;

        for (int i = 1; i < sciezka.size(); i++) {
            if (sciaganieX) {
                sciaganieXbylo = true;
            } else {
                sciaganieXbylo = false;
            }
            while (!mapa.zgodaNaNast(sciezka, i)) //puste
                    ;
            wierzcholekNast = sciezka.get(i);
            if (sciezka.size() > i + 1
                    && //zeby usunac do sciaganie do srodka drogi
                    (sciezka.get(i + 1).getPunkt().getX() == sciezka.get(i - 1).getPunkt().getX()
                    && sciezka.get(i).getPunkt().getX() != sciezka.get(i - 1).getPunkt().getX())) {
                sciaganieX = true;
            } else {
                sciaganieX = false;
            }

            System.out.println("ID " + this.ID + " wierz " + mapa.getListaWierzcholkow().indexOf(sciezka.get(i)));

            x = wierzcholekPoprzedni.getPunkt().getX();
            y = wierzcholekPoprzedni.getPunkt().getY();
            System.out.println("Start x " + x);
            System.out.println("Start y " + y);
            if (sciaganieX) {
                while (y != wierzcholekNast.getPunkt().getY()) {
                    System.out.println("Sciaganie x" + y);
                    if (wierzcholekPoprzedni.getPunkt().getY() < wierzcholekNast.getPunkt().getY()) {
                        y += 1;
                    } else {
                        y -= 1;
                    }

                    try {
                        Thread.sleep(50); //getPredkosc auta
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (sciaganieXbylo) {
                x = wierzcholekNast.getPunkt().getX();
                while (y != wierzcholekNast.getPunkt().getY()) {
                    if (wierzcholekPoprzedni.getPunkt().getY() < wierzcholekNast.getPunkt().getY()) {
                        y += 1;
                    } else {
                        y -= 1;
                    }

                    try {
                        Thread.sleep(50); //getPredkosc auta
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {

                while (x != wierzcholekNast.getPunkt().getX() || y != wierzcholekNast.getPunkt().getY()) {
                    if (x != wierzcholekNast.getPunkt().getX()) {
                        if (wierzcholekPoprzedni.getPunkt().getX() < wierzcholekNast.getPunkt().getX()) {
                            x += 1;
                        } else {
                            x -= 1;
                        }
                    }
                    if (y != wierzcholekNast.getPunkt().getY()) {
                        if (wierzcholekPoprzedni.getPunkt().getY() < wierzcholekNast.getPunkt().getY()) {
                            y += 1;
                        } else {
                            y -= 1;
                        }
                    }
                    System.out.println("x " + x);
                    System.out.println("y " + y);
                    try {
                        Thread.sleep(50); //getPredkosc auta
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            wierzcholekPoprzedni = sciezka.get(i);
        }
        //}
    }

}
