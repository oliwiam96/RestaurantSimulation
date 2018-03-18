/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Reprezenruje wierzchołek grafu (skierowanego).
 *
 * @author Oliwia
 */
public class Wierzcholek implements Serializable {

    private Punkt punkt;
    private boolean wolny;
    private List<Wierzcholek> listaNastepnikow;
    private boolean skrzyzowanie;

    public Wierzcholek() {
        this.punkt = new Punkt();

    }

    public Wierzcholek(int x, int y, boolean wolny, List<Wierzcholek> listaNastepnikow) {
        this.punkt = new Punkt(x, y);
        this.wolny = wolny;
        this.listaNastepnikow = listaNastepnikow;
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

    /**
     * @return the listaNastepnikow
     */
    public List<Wierzcholek> getListaNastepnikow() {
        return listaNastepnikow;
    }

    /**
     * @param listaNastepnikow the listaNastepnikow to set
     */
    public void setListaNastepnikow(List<Wierzcholek> listaNastepnikow) {
        this.listaNastepnikow = listaNastepnikow;
    }

    /**
     * @return the punkt
     */
    public Punkt getPunkt() {
        return punkt;
    }

    /**
     * @param punkt the punkt to set
     */
    public void setPunkt(Punkt punkt) {
        this.punkt = punkt;
    }

    /**
     * @return the skrzyzowanie
     */
    public boolean isSkrzyzowanie() {
        return skrzyzowanie;
    }

    /**
     * @param skrzyzowanie the skrzyzowanie to set
     */
    public void setSkrzyzowanie(boolean skrzyzowanie) {
        this.skrzyzowanie = skrzyzowanie;
    }

}
