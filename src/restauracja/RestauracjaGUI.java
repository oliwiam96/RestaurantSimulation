/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Główna klasa projektu, te elmenty GUI wyklikałam w Netbeansie.
 * @author Oliwia
 */
public class RestauracjaGUI extends javax.swing.JFrame {

    private Restauracja restauracja;

    public static int y = 0;

    private static Klient nowyKlient;

    /**
     * Creates new form RestauracjaJFrame
     */
    public RestauracjaGUI() {

        
        restauracja = new Restauracja();

        restauracja.getListaKlientow().add(new KlientFirmowy(restauracja));
        

        restauracja.getListaPojazdow().add(new Samochod(restauracja));
        restauracja.getListaPojazdow().add(new Skuter(restauracja));

        restauracja.getListaDostawcow().add(new Dostawca(restauracja));
       
        /*
        String nazwaPliku = "restauracja.ser";
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(nazwaPliku)));
            try {
                restauracja = ((Restauracja) in.readObject());
                System.out.println("Wczytalem sobie");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RestauracjaGUI.class.getName()).log(Level.SEVERE, null, ex);
                restauracja = new Restauracja();
            }
        } catch (IOException ex) {
            Logger.getLogger(RestauracjaGUI.class.getName()).log(Level.SEVERE, null, ex);
            restauracja = new Restauracja();

        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(RestauracjaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println(restauracja.getZamowieniaGotowe().size());
       
        */
        //*** tutaj staly element kodu***//
        for (Dostawca d : restauracja.getListaDostawcow()) {
            (new Thread(d)).start();
            
        }
       
        
        for (Klient k : restauracja.getListaKlientow()){
            (new Thread(k)).start();
        }
        (new Thread(restauracja.getKuchnia())).start();
            
            
            /* 
       String nazwaPliku = "restauracja.ser";
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(nazwaPliku)));
            try {
                restauracja = ((Restauracja) in.readObject());
                System.out.println("Wczytalem sobie");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RestauracjaGUI.class.getName()).log(Level.SEVERE, null, ex);
                restauracja = new Restauracja();
            }
        } catch (IOException ex) {
            Logger.getLogger(RestauracjaGUI.class.getName()).log(Level.SEVERE, null, ex);
            restauracja = new Restauracja();

        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(RestauracjaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println(restauracja.getZamowieniaGotowe().size());
         */ {
            initComponents();
        }

    }

    public void pom() {

        (new Thread(new Dostawca(restauracja))).start();
        (new Thread(new KlientStaly(restauracja))).start();
        (new Thread(new Kuchnia(restauracja))).start();

    }

    public void zapiszDoPliku() {

        //System.out.println("Elo " + restauracja.getZamowieniaGotowe().size());
        String nazwaPliku = "restauracja.ser";
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(nazwaPliku)));
            out.writeObject(restauracja);

        } catch (IOException ex) {
            Logger.getLogger(RestauracjaGUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(RestauracjaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okienkoDodajKlienta = new javax.swing.JFrame();
        warstwowyPanel1 = new javax.swing.JLayeredPane();
        panelWyboruKlienta = new javax.swing.JPanel();
        radioButtonStaly = new javax.swing.JRadioButton();
        guzikDalej = new javax.swing.JButton();
        radioButtonFirmowy = new javax.swing.JRadioButton();
        radioButtonOkazjonalny = new javax.swing.JRadioButton();
        warstwowyPanel2 = new javax.swing.JLayeredPane();
        panelKlientOgolnie = new javax.swing.JPanel();
        etykietaTytul = new javax.swing.JLabel();
        etykietaNazwa = new javax.swing.JLabel();
        poleNazwa = new javax.swing.JFormattedTextField();
        etykietaNrTelefonu = new javax.swing.JLabel();
        poleNrTelefonu = new javax.swing.JFormattedTextField();
        etykietaEmail = new javax.swing.JLabel();
        poleEmail = new javax.swing.JFormattedTextField();
        etykietaAdres = new javax.swing.JLabel();
        etykietaNrDomu = new javax.swing.JLabel();
        poleNrDomu = new javax.swing.JFormattedTextField();
        panelKlientaFirmowego = new javax.swing.JPanel();
        etykietaREGON = new javax.swing.JLabel();
        etykietaNrKontaBankowego = new javax.swing.JLabel();
        poleREGON = new javax.swing.JFormattedTextField();
        poleNrKontaBankowego = new javax.swing.JFormattedTextField();
        etykietaAdresFirmowy = new javax.swing.JLabel();
        etykietaNrDomuFirmowy = new javax.swing.JLabel();
        poleNrDomuFirmowy = new javax.swing.JFormattedTextField();
        panelKlientaStalego = new javax.swing.JPanel();
        etykietaPunktyLojalnosciowe = new javax.swing.JLabel();
        etykietaZnizka = new javax.swing.JLabel();
        polePunktyLojalnosciowe = new javax.swing.JFormattedTextField();
        poleZnizka = new javax.swing.JFormattedTextField();
        guzikZatwierdz = new javax.swing.JButton();
        buttonGroupRodzajKlienta = new javax.swing.ButtonGroup();
        okienkoKlikKlient = new javax.swing.JFrame();
        Panel = new javax.swing.JTabbedPane();
        Klienci = new javax.swing.JPanel();
        guzikDodajKlienta = new javax.swing.JButton();
        Dostawcy = new javax.swing.JPanel();
        guzikDodajDostawce = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        guzikDodajSamochod = new javax.swing.JButton();
        guzikDodajSkuter = new javax.swing.JButton();
        try {
            bruce1 = new restauracja.Symulacja(restauracja);

            warstwowyPanel1.setPreferredSize(new java.awt.Dimension(300, 400));
            warstwowyPanel1.setRequestFocusEnabled(false);

            panelWyboruKlienta.setPreferredSize(new java.awt.Dimension(300, 350));

            buttonGroupRodzajKlienta.add(radioButtonStaly);
            radioButtonStaly.setText("Klient Stały");

            guzikDalej.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            guzikDalej.setText("Dalej");
            guzikDalej.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    guzikDalejActionPerformed(evt);
                }
            });

            buttonGroupRodzajKlienta.add(radioButtonFirmowy);
            radioButtonFirmowy.setSelected(true);
            radioButtonFirmowy.setText("Klient Firmowy");

            buttonGroupRodzajKlienta.add(radioButtonOkazjonalny);
            radioButtonOkazjonalny.setText("Klient Okazjonalny");

            javax.swing.GroupLayout panelWyboruKlientaLayout = new javax.swing.GroupLayout(panelWyboruKlienta);
            panelWyboruKlienta.setLayout(panelWyboruKlientaLayout);
            panelWyboruKlientaLayout.setHorizontalGroup(
                panelWyboruKlientaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelWyboruKlientaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelWyboruKlientaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(guzikDalej, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(radioButtonFirmowy)
                        .addComponent(radioButtonOkazjonalny)
                        .addComponent(radioButtonStaly))
                    .addContainerGap(49, Short.MAX_VALUE))
            );
            panelWyboruKlientaLayout.setVerticalGroup(
                panelWyboruKlientaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelWyboruKlientaLayout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(radioButtonOkazjonalny)
                    .addGap(33, 33, 33)
                    .addComponent(radioButtonStaly)
                    .addGap(44, 44, 44)
                    .addComponent(radioButtonFirmowy)
                    .addGap(55, 55, 55)
                    .addComponent(guzikDalej)
                    .addContainerGap(23, Short.MAX_VALUE))
            );

            etykietaTytul.setText("Formularz Klienta");

            etykietaNazwa.setText("Nazwa");

            poleNazwa.setText("jFormattedTextField1");

            etykietaNrTelefonu.setText("Numer telefonu");

            poleNrTelefonu.setText("jFormattedTextField1");

            etykietaEmail.setText("e-mail");

            poleEmail.setText("jFormattedTextField1");

            etykietaAdres.setText("Adres");

            etykietaNrDomu.setText("Nr domu");

            poleNrDomu.setText("jFormattedTextField1");
            poleNrDomu.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    poleNrDomuActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelKlientOgolnieLayout = new javax.swing.GroupLayout(panelKlientOgolnie);
            panelKlientOgolnie.setLayout(panelKlientOgolnieLayout);
            panelKlientOgolnieLayout.setHorizontalGroup(
                panelKlientOgolnieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelKlientOgolnieLayout.createSequentialGroup()
                    .addGroup(panelKlientOgolnieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelKlientOgolnieLayout.createSequentialGroup()
                            .addGap(101, 101, 101)
                            .addComponent(etykietaTytul))
                        .addGroup(panelKlientOgolnieLayout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addGroup(panelKlientOgolnieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelKlientOgolnieLayout.createSequentialGroup()
                                    .addComponent(etykietaEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(62, 62, 62)
                                    .addComponent(poleEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelKlientOgolnieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelKlientOgolnieLayout.createSequentialGroup()
                                        .addComponent(etykietaNrTelefonu, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(poleNrTelefonu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelKlientOgolnieLayout.createSequentialGroup()
                                        .addComponent(etykietaNazwa, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62)
                                        .addComponent(poleNazwa)))
                                .addGroup(panelKlientOgolnieLayout.createSequentialGroup()
                                    .addComponent(etykietaAdres, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(171, 171, 171))))
                        .addGroup(panelKlientOgolnieLayout.createSequentialGroup()
                            .addGap(107, 107, 107)
                            .addComponent(etykietaNrDomu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(poleNrDomu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(61, Short.MAX_VALUE))
            );
            panelKlientOgolnieLayout.setVerticalGroup(
                panelKlientOgolnieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelKlientOgolnieLayout.createSequentialGroup()
                    .addComponent(etykietaTytul)
                    .addGap(18, 18, 18)
                    .addGroup(panelKlientOgolnieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etykietaNazwa)
                        .addComponent(poleNazwa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelKlientOgolnieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etykietaNrTelefonu)
                        .addComponent(poleNrTelefonu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelKlientOgolnieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etykietaEmail)
                        .addComponent(poleEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(etykietaAdres)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelKlientOgolnieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etykietaNrDomu)
                        .addComponent(poleNrDomu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(45, Short.MAX_VALUE))
            );

            etykietaREGON.setText("REGON");

            etykietaNrKontaBankowego.setText("Nr konta bankowego");

            poleREGON.setText("jFormattedTextField1");

            poleNrKontaBankowego.setText("jFormattedTextField2");

            etykietaAdresFirmowy.setText("Adres korespondencyjny");

            etykietaNrDomuFirmowy.setText("Nr domu");

            poleNrDomuFirmowy.setText("jFormattedTextField2");

            javax.swing.GroupLayout panelKlientaFirmowegoLayout = new javax.swing.GroupLayout(panelKlientaFirmowego);
            panelKlientaFirmowego.setLayout(panelKlientaFirmowegoLayout);
            panelKlientaFirmowegoLayout.setHorizontalGroup(
                panelKlientaFirmowegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelKlientaFirmowegoLayout.createSequentialGroup()
                    .addGroup(panelKlientaFirmowegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelKlientaFirmowegoLayout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(panelKlientaFirmowegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelKlientaFirmowegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(etykietaREGON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(etykietaNrKontaBankowego, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(etykietaAdresFirmowy))
                            .addGap(35, 35, 35))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKlientaFirmowegoLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(etykietaNrDomuFirmowy)
                            .addGap(49, 49, 49)))
                    .addGroup(panelKlientaFirmowegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(poleREGON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(poleNrDomuFirmowy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(poleNrKontaBankowego, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(21, Short.MAX_VALUE))
            );
            panelKlientaFirmowegoLayout.setVerticalGroup(
                panelKlientaFirmowegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelKlientaFirmowegoLayout.createSequentialGroup()
                    .addContainerGap(48, Short.MAX_VALUE)
                    .addGroup(panelKlientaFirmowegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etykietaREGON, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(poleREGON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelKlientaFirmowegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etykietaNrKontaBankowego, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(poleNrKontaBankowego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(etykietaAdresFirmowy)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelKlientaFirmowegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etykietaNrDomuFirmowy)
                        .addComponent(poleNrDomuFirmowy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(37, 37, 37))
            );

            etykietaPunktyLojalnosciowe.setText("punkty lojalnościowe");

            etykietaZnizka.setText("zniżka");

            polePunktyLojalnosciowe.setText("jFormattedTextField1");

            poleZnizka.setText("jFormattedTextField2");

            javax.swing.GroupLayout panelKlientaStalegoLayout = new javax.swing.GroupLayout(panelKlientaStalego);
            panelKlientaStalego.setLayout(panelKlientaStalegoLayout);
            panelKlientaStalegoLayout.setHorizontalGroup(
                panelKlientaStalegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelKlientaStalegoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelKlientaStalegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etykietaPunktyLojalnosciowe, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etykietaZnizka, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(35, 35, 35)
                    .addGroup(panelKlientaStalegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(poleZnizka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(polePunktyLojalnosciowe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(64, Short.MAX_VALUE))
            );
            panelKlientaStalegoLayout.setVerticalGroup(
                panelKlientaStalegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelKlientaStalegoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelKlientaStalegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etykietaPunktyLojalnosciowe, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(polePunktyLojalnosciowe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelKlientaStalegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etykietaZnizka, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(poleZnizka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            guzikZatwierdz.setText("Zatwierdź");
            guzikZatwierdz.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    guzikZatwierdzActionPerformed(evt);
                }
            });

            warstwowyPanel2.setLayer(panelKlientOgolnie, javax.swing.JLayeredPane.DEFAULT_LAYER);
            warstwowyPanel2.setLayer(panelKlientaFirmowego, javax.swing.JLayeredPane.DEFAULT_LAYER);
            warstwowyPanel2.setLayer(panelKlientaStalego, javax.swing.JLayeredPane.DEFAULT_LAYER);
            warstwowyPanel2.setLayer(guzikZatwierdz, javax.swing.JLayeredPane.DEFAULT_LAYER);

            javax.swing.GroupLayout warstwowyPanel2Layout = new javax.swing.GroupLayout(warstwowyPanel2);
            warstwowyPanel2.setLayout(warstwowyPanel2Layout);
            warstwowyPanel2Layout.setHorizontalGroup(
                warstwowyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(warstwowyPanel2Layout.createSequentialGroup()
                    .addGroup(warstwowyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(panelKlientaStalego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(warstwowyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(warstwowyPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(panelKlientOgolnie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(warstwowyPanel2Layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(guzikZatwierdz, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(warstwowyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(warstwowyPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(panelKlientaFirmowego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(27, Short.MAX_VALUE)))
            );
            warstwowyPanel2Layout.setVerticalGroup(
                warstwowyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(warstwowyPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelKlientOgolnie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(panelKlientaStalego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                    .addComponent(guzikZatwierdz, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addGroup(warstwowyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(warstwowyPanel2Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(panelKlientaFirmowego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(51, Short.MAX_VALUE)))
            );

            warstwowyPanel1.setLayer(panelWyboruKlienta, javax.swing.JLayeredPane.DEFAULT_LAYER);
            warstwowyPanel1.setLayer(warstwowyPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

            javax.swing.GroupLayout warstwowyPanel1Layout = new javax.swing.GroupLayout(warstwowyPanel1);
            warstwowyPanel1.setLayout(warstwowyPanel1Layout);
            warstwowyPanel1Layout.setHorizontalGroup(
                warstwowyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(warstwowyPanel1Layout.createSequentialGroup()
                    .addGap(64, 64, 64)
                    .addComponent(panelWyboruKlienta, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(107, Short.MAX_VALUE))
                .addGroup(warstwowyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(warstwowyPanel2))
            );
            warstwowyPanel1Layout.setVerticalGroup(
                warstwowyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(warstwowyPanel1Layout.createSequentialGroup()
                    .addGap(53, 53, 53)
                    .addComponent(panelWyboruKlienta, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(65, Short.MAX_VALUE))
                .addGroup(warstwowyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(warstwowyPanel2))
            );

            javax.swing.GroupLayout okienkoDodajKlientaLayout = new javax.swing.GroupLayout(okienkoDodajKlienta.getContentPane());
            okienkoDodajKlienta.getContentPane().setLayout(okienkoDodajKlientaLayout);
            okienkoDodajKlientaLayout.setHorizontalGroup(
                okienkoDodajKlientaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(warstwowyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
            );
            okienkoDodajKlientaLayout.setVerticalGroup(
                okienkoDodajKlientaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(warstwowyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout okienkoKlikKlientLayout = new javax.swing.GroupLayout(okienkoKlikKlient.getContentPane());
            okienkoKlikKlient.getContentPane().setLayout(okienkoKlikKlientLayout);
            okienkoKlikKlientLayout.setHorizontalGroup(
                okienkoKlikKlientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 400, Short.MAX_VALUE)
            );
            okienkoKlikKlientLayout.setVerticalGroup(
                okienkoKlikKlientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 300, Short.MAX_VALUE)
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setTitle("Restauracja");
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    formWindowClosing(evt);
                }
            });

            Panel.setPreferredSize(new java.awt.Dimension(800, 600));

            Klienci.setForeground(new java.awt.Color(153, 153, 153));
            Klienci.setToolTipText("");

            guzikDodajKlienta.setText("Dodaj klienta");
            guzikDodajKlienta.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    guzikDodajKlientaActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout KlienciLayout = new javax.swing.GroupLayout(Klienci);
            Klienci.setLayout(KlienciLayout);
            KlienciLayout.setHorizontalGroup(
                KlienciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(KlienciLayout.createSequentialGroup()
                    .addGap(96, 96, 96)
                    .addComponent(guzikDodajKlienta, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(578, Short.MAX_VALUE))
            );
            KlienciLayout.setVerticalGroup(
                KlienciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(KlienciLayout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(guzikDodajKlienta, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(384, Short.MAX_VALUE))
            );

            Panel.addTab("Klienci", Klienci);

            guzikDodajDostawce.setText("Dodaj dostawcę");
            guzikDodajDostawce.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    guzikDodajDostawceActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout DostawcyLayout = new javax.swing.GroupLayout(Dostawcy);
            Dostawcy.setLayout(DostawcyLayout);
            DostawcyLayout.setHorizontalGroup(
                DostawcyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DostawcyLayout.createSequentialGroup()
                    .addGap(138, 138, 138)
                    .addComponent(guzikDodajDostawce, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(510, Short.MAX_VALUE))
            );
            DostawcyLayout.setVerticalGroup(
                DostawcyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DostawcyLayout.createSequentialGroup()
                    .addGap(76, 76, 76)
                    .addComponent(guzikDodajDostawce, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(360, Short.MAX_VALUE))
            );

            guzikDodajDostawce.getAccessibleContext().setAccessibleName("dodaj");

            Panel.addTab("Dostawcy", Dostawcy);

            guzikDodajSamochod.setText("Dodaj samochód");
            guzikDodajSamochod.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    guzikDodajSamochodActionPerformed(evt);
                }
            });

            guzikDodajSkuter.setText("Dodaj skuter");
            guzikDodajSkuter.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    guzikDodajSkuterActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(137, 137, 137)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(guzikDodajSkuter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(guzikDodajSamochod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(547, Short.MAX_VALUE))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(79, 79, 79)
                    .addComponent(guzikDodajSamochod, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33)
                    .addComponent(guzikDodajSkuter, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(274, Short.MAX_VALUE))
            );

            Panel.addTab("Pojazdy", jPanel2);

        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }

        javax.swing.GroupLayout bruce1Layout = new javax.swing.GroupLayout(bruce1);
        bruce1.setLayout(bruce1Layout);
        bruce1Layout.setHorizontalGroup(
            bruce1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 795, Short.MAX_VALUE)
        );
        bruce1Layout.setVerticalGroup(
            bruce1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );

        Panel.addTab("Symulacja", bruce1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guzikDalejActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guzikDalejActionPerformed
        // TODO add your handling code here:
        if (radioButtonStaly.isSelected()) {
            nowyKlient = new KlientStaly(this.restauracja);
            polePunktyLojalnosciowe.setText(
                    Integer.toString(((KlientStaly) nowyKlient).getLiczbaPunktowLojalnosciowych())
            );
            poleZnizka.setText(
                    Float.toString(((KlientStaly) nowyKlient).getZnizka())
            );
            panelKlientaStalego.setVisible(true);
            panelKlientaFirmowego.setVisible(false);
            System.out.println("staly");
        } else if (radioButtonFirmowy.isSelected()) {
            nowyKlient = new KlientFirmowy(this.restauracja);
            poleREGON.setText(
                    ((KlientFirmowy) nowyKlient).getREGON());
            poleNrKontaBankowego.setText(
                    ((KlientFirmowy) nowyKlient).getIndywidualnyNrKontaBankowego());
            poleNrDomuFirmowy.setText(
                    Integer.toString(((KlientFirmowy) nowyKlient).getAdresKorespondencyjny().getNrDomu()));
            panelKlientaStalego.setVisible(false);
            panelKlientaFirmowego.setVisible(true);
            System.out.println("firmowy");
        } else if (radioButtonOkazjonalny.isSelected()) {
            nowyKlient = new KlientOkazjonalny(this.restauracja);
            panelKlientaStalego.setVisible(false);
            panelKlientaFirmowego.setVisible(false);
            System.out.println("okazjonalny");
        }
        poleNazwa.setText(nowyKlient.getNazwa());
        poleNrTelefonu.setText(nowyKlient.getNrTelefonu());
        poleEmail.setText(nowyKlient.getEmail());
        poleNrDomu.setText(Integer.toString(nowyKlient.getAdres().getNrDomu()));

        panelWyboruKlienta.setVisible(false);
        warstwowyPanel2.setVisible(true);
        //okienkoDodajKlienta.dispatchEvent(new WindowEvent(okienkoDodajKlienta, WindowEvent.WINDOW_CLOSING));       

    }//GEN-LAST:event_guzikDalejActionPerformed

    private void poleNrDomuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poleNrDomuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_poleNrDomuActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        System.out.println("zamykam");
        zapiszDoPliku();
    }//GEN-LAST:event_formWindowClosing

    private void guzikZatwierdzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guzikZatwierdzActionPerformed
        //tutaj dodawanie klienta ze zmienonymi parametrami
        nowyKlient.setNazwa(poleNazwa.getText());
        nowyKlient.setEmail(poleEmail.getText());
        nowyKlient.setNrTelefonu(poleNrTelefonu.getText());
        nowyKlient.getAdres().setNrDomu(
                Integer.parseInt(poleNrDomu.getText()));
        if (nowyKlient instanceof KlientStaly) {
            ((KlientStaly) nowyKlient).setLiczbaPunktowLojalnosciowych(
                    Integer.parseInt(polePunktyLojalnosciowe.getText()));
            ((KlientStaly) nowyKlient).setZnizka(
                    Float.parseFloat(poleZnizka.getText()));
            System.out.println("staly");
        }
        if (nowyKlient instanceof KlientFirmowy) {
            ((KlientFirmowy) nowyKlient).setREGON(poleREGON.getText());
            ((KlientFirmowy) nowyKlient).setIndywidualnyNrKontaBankowego(
                    poleNrKontaBankowego.getText());
            ((KlientFirmowy) nowyKlient).getAdresKorespondencyjny().setNrDomu(
                    Integer.parseInt(poleNrDomuFirmowy.getText()));
            System.out.println("firmowy");

        }
        restauracja.getListaKlientow().add(nowyKlient);
        (new Thread(nowyKlient)).start();
        System.out.println("liczba klientow " + restauracja.getListaKlientow().size());
        okienkoDodajKlienta.dispatchEvent(new WindowEvent(okienkoDodajKlienta, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_guzikZatwierdzActionPerformed

    private void guzikDodajKlientaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guzikDodajKlientaActionPerformed
        // TODO add your handling code here:
        okienkoDodajKlienta.setVisible(true);
        panelWyboruKlienta.setVisible(true);
        warstwowyPanel2.setVisible(false);
        okienkoDodajKlienta.setSize(500, 600);
    }//GEN-LAST:event_guzikDodajKlientaActionPerformed

    private void guzikDodajDostawceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guzikDodajDostawceActionPerformed
        // TODO add your handling code here:
        Dostawca nowyDostawca = new Dostawca(restauracja);
        restauracja.getListaDostawcow().add(nowyDostawca);
        (new Thread(nowyDostawca)).start();
    }//GEN-LAST:event_guzikDodajDostawceActionPerformed

    private void guzikDodajSamochodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guzikDodajSamochodActionPerformed
        // TODO add your handling code here:
        restauracja.getListaPojazdow().add(new Samochod(restauracja));
    }//GEN-LAST:event_guzikDodajSamochodActionPerformed

    private void guzikDodajSkuterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guzikDodajSkuterActionPerformed
        // TODO add your handling code here:
        restauracja.getListaPojazdow().add(new Skuter(restauracja));
    }//GEN-LAST:event_guzikDodajSkuterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //Restauracja r = new Restauracja();
        /**
         * normalne rzeczy
         */
        //RestauracjaGUI gui = new RestauracjaGUI();

        try {
            Menu.wczytajMenu();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RestauracjaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Menu.listaPosilkow.get(5).getNazwa());

        /*try {
            Thread.sleep(15000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RestauracjaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Zapis");
        System.out.println("Rozmiar" + gui.restauracja.getZamowieniaGotowe().size());
        gui.zapiszDoPliku();*/
 /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RestauracjaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RestauracjaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RestauracjaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RestauracjaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RestauracjaGUI().setVisible(true);
                /*(new Thread(new Dostawca(restauracja))).start();
                (new Thread(new KlientStaly(restauracja))).start();
                (new Thread(new Kuchnia(restauracja))).start();*/
                //bruce1.repaint();
                /*(new Thread(new Test(1, mapa))).start();
                (new Thread(new Test(2, mapa))).start();
                (new Thread(new Test(3, mapa))).start();*/

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Dostawcy;
    private javax.swing.JPanel Klienci;
    private javax.swing.JTabbedPane Panel;
    private restauracja.Symulacja bruce1;
    private javax.swing.ButtonGroup buttonGroupRodzajKlienta;
    private javax.swing.JLabel etykietaAdres;
    private javax.swing.JLabel etykietaAdresFirmowy;
    private javax.swing.JLabel etykietaEmail;
    private javax.swing.JLabel etykietaNazwa;
    private javax.swing.JLabel etykietaNrDomu;
    private javax.swing.JLabel etykietaNrDomuFirmowy;
    private javax.swing.JLabel etykietaNrKontaBankowego;
    private javax.swing.JLabel etykietaNrTelefonu;
    private javax.swing.JLabel etykietaPunktyLojalnosciowe;
    private javax.swing.JLabel etykietaREGON;
    private javax.swing.JLabel etykietaTytul;
    private javax.swing.JLabel etykietaZnizka;
    private javax.swing.JButton guzikDalej;
    private javax.swing.JButton guzikDodajDostawce;
    private javax.swing.JButton guzikDodajKlienta;
    private javax.swing.JButton guzikDodajSamochod;
    private javax.swing.JButton guzikDodajSkuter;
    private javax.swing.JButton guzikZatwierdz;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JFrame okienkoDodajKlienta;
    private javax.swing.JFrame okienkoKlikKlient;
    private javax.swing.JPanel panelKlientOgolnie;
    private javax.swing.JPanel panelKlientaFirmowego;
    private javax.swing.JPanel panelKlientaStalego;
    private javax.swing.JPanel panelWyboruKlienta;
    private javax.swing.JFormattedTextField poleEmail;
    private javax.swing.JFormattedTextField poleNazwa;
    private javax.swing.JFormattedTextField poleNrDomu;
    private javax.swing.JFormattedTextField poleNrDomuFirmowy;
    private javax.swing.JFormattedTextField poleNrKontaBankowego;
    private javax.swing.JFormattedTextField poleNrTelefonu;
    private javax.swing.JFormattedTextField polePunktyLojalnosciowe;
    private javax.swing.JFormattedTextField poleREGON;
    private javax.swing.JFormattedTextField poleZnizka;
    private javax.swing.JRadioButton radioButtonFirmowy;
    private javax.swing.JRadioButton radioButtonOkazjonalny;
    private javax.swing.JRadioButton radioButtonStaly;
    private javax.swing.JLayeredPane warstwowyPanel1;
    private javax.swing.JLayeredPane warstwowyPanel2;
    // End of variables declaration//GEN-END:variables

}
