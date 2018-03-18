/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.util.Random;

/**
 * Stały bywalec restauracji, ma specjalne zniżki, zbiera punkty lojalnościowe.
 * @author Oliwia
 */
public class KlientStaly extends Klient{
    
    private int liczbaPunktowLojalnosciowych;
    private float znizka;
    public static final int MAX_PUNKTOW_LOJALNOSCIOWYCH = 1000; //po przekroczeniu tego progu klient dostaje znizke 100 zl
                                                          //przy nastepnym zamowieniu i punkty zmniejszaja sie o zadany prog
    
    public KlientStaly(Restauracja restauracja){
        super(restauracja);
        Random random = new Random();
        setLiczbaPunktowLojalnosciowych(random.nextInt(MAX_PUNKTOW_LOJALNOSCIOWYCH));
        setZnizka(random.nextFloat());
    }
    
    @Override
    public void wyswietlInformacje(){
        System.out.println("~~~Klient Staly~~~");
        super.wyswietlInformacje();
        System.out.println("Liczba punktow lojalnosciowych: " + getLiczbaPunktowLojalnosciowych());
        //System.out.println("Znizka: " + getZnizka());
        System.out.printf("Znizka: %.2f%%\n", getZnizka()*100);        
    }

    /**
     * @return the liczbaPunktowLojalnosciowych
     */
    public int getLiczbaPunktowLojalnosciowych() {
        return liczbaPunktowLojalnosciowych;
    }

    /**
     * @param liczbaPunktowLojalnosciowych the liczbaPunktowLojalnosciowych to set
     */
    public void setLiczbaPunktowLojalnosciowych(int liczbaPunktowLojalnosciowych) {
        this.liczbaPunktowLojalnosciowych = liczbaPunktowLojalnosciowych;
    }

    /**
     * @return the znizka
     */
    //@Override
    public float getZnizka() {
        return znizka;
    }

    /**
     * @param znizka the znizka to set
     */
    public void setZnizka(float znizka) {
        this.znizka = znizka;
    }
    

}
