package restauracja;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.transform.Affine;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * Klasa rysująca na JPanelu całe miasto (dostawców, klientów, restaurację),
 * ten element GUI napisałam sama (nie wyklikałam).
 *
 *
 */
public class Symulacja extends JPanel {


    private Restauracja restauracja;
    private BufferedImage prostokat;

    private BufferedImage skuterPOLNOC;
    private BufferedImage skuterPOLUDNIE;
    private BufferedImage skuterWSCHOD;
    private BufferedImage skuterZACHOD;

    private BufferedImage autkoPOLUDNIE;
    private BufferedImage autkoPOLNOC;
    private BufferedImage autkoZACHOD;
    private BufferedImage autkoWSCHOD;

    private BufferedImage firmowy;
    private BufferedImage staly;
    private BufferedImage okazjonalny;
    
    private BufferedImage garaz;
    private BufferedImage mattoli;

    Mapa mapa;
    List<Test> listaTest = new ArrayList();

    JFrame okienkoInfoKlient;
    JFrame okienkoInfoDostawca;

    /**
     * Pomocnicza metoda używana przy testowaniu, rysuje wierzchołki i
     * połączenia w grafie
     *
     * @param g to, na czym ciągle się rysuje (grafika)
     */
    public void rysujWierzcholkiGrafu(Graphics g) {
        for (Wierzcholek w : mapa.getListaWierzcholkow()) {
            g.drawOval(w.getPunkt().getX() - 2, w.getPunkt().getY() - 2, 4, 4);
            for (Wierzcholek u : w.getListaNastepnikow()) {
                g.drawLine(w.getPunkt().getX(), w.getPunkt().getY(), u.getPunkt().getX(), u.getPunkt().getY());
            }
        }
    }

    /**
     * Metoda główna programu.
     *
     * @param args paprametry wejściowe (nieużywane)
     */
    /**
     * Przesłonięta metoda paintComponent(). To ta metoda rysuje komponenet, w
     * tym przypadku JPanel.
     */
    @Override
    public void paintComponent(Graphics g) {

        g.clearRect(0, 0, this.getWidth(), this.getHeight());

        g.drawImage(prostokat, 0, 0, null);
        //rysujWierzcholkiGrafu(g);

        for (Klient k : restauracja.getListaKlientow()) {
            BufferedImage obrazekKlienta;
            if (k instanceof KlientFirmowy) {
                obrazekKlienta = firmowy;
            } else if (k instanceof KlientOkazjonalny) {
                obrazekKlienta = okazjonalny;
            } else {
                obrazekKlienta = staly;
            }
            g.drawImage(obrazekKlienta, k.getAdres().getWierzcholek().getPunkt().getX(),
                    k.getAdres().getWierzcholek().getPunkt().getY(), null);
        }
        /*
        cos nie działa to obracanie tym sposobem, już lepiej wykorzystam 4 różne obrazki
        AffineTransform at = AffineTransform.getTranslateInstance(100, 100);
        at.rotate(Math.toRadians(90));
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        autko =  op.filter(autko, null);        
        
        double rotationRequired = Math.toRadians(90);
        double locationX = autko.getWidth()/2;
        double locationY = autko.getHeight()/2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g.drawImage(op.filter(autko, null), 200, 200, null);
         */
        for (Dostawca d : restauracja.getListaDostawcow()) {
            if (d.getUzywanyPojazd() instanceof Samochod) {
                if (d.getKierunek() == KierunkiSwiata.POLNOC) {
                    g.drawImage(autkoPOLNOC, d.getPolozenie().getX() - 8, d.getPolozenie().getY() - 8, null);
                } else if (d.getKierunek() == KierunkiSwiata.POLUDNIE) {
                    g.drawImage(autkoPOLUDNIE, d.getPolozenie().getX() - 8, d.getPolozenie().getY() - 8, null);
                } else if (d.getKierunek() == KierunkiSwiata.ZACHOD) {
                    g.drawImage(autkoZACHOD, d.getPolozenie().getX() - 8, d.getPolozenie().getY() - 8, null);
                } else //if(d.kierunek == KierunkiSwiata.WSCHOD)
                {
                    g.drawImage(autkoWSCHOD, d.getPolozenie().getX() - 8, d.getPolozenie().getY() - 8, null);
                }
            } else {
                if (d.getKierunek() == KierunkiSwiata.POLNOC) {
                    g.drawImage(skuterPOLNOC, d.getPolozenie().getX() - 8, d.getPolozenie().getY() - 8, null);
                } else if (d.getKierunek() == KierunkiSwiata.POLUDNIE) {
                    g.drawImage(skuterPOLUDNIE, d.getPolozenie().getX() - 8, d.getPolozenie().getY() - 8, null);
                } else if (d.getKierunek() == KierunkiSwiata.ZACHOD) {
                    g.drawImage(skuterZACHOD, d.getPolozenie().getX() - 8, d.getPolozenie().getY() - 8, null);
                } else //if(d.kierunek == KierunkiSwiata.WSCHOD)
                {
                    g.drawImage(skuterWSCHOD, d.getPolozenie().getX() - 8, d.getPolozenie().getY() - 8, null);
                }
            }
        }
        g.drawImage(garaz, 300, 250, this);
        g.drawImage(mattoli, 340, 250, this);

    }

    /**
     *
     * @throws IOException klauzula throws mówi, że może pojawić się wyjątek.
     * Wyjątek dotyczy otwierania pliku, podczas którego może pojawić się wiele
     * problemów (np. brak pliku, plik zablokowany).
     */
    public Symulacja() throws IOException {
        System.out.println("Domyslny konstruktor bezparametrowy");

    }

    public Symulacja(Restauracja restauracja) throws IOException {
        this.restauracja = restauracja;
        this.mapa = restauracja.getMapa();

        prostokat = ImageIO.read(new File("mapa1.png"));
        garaz = ImageIO.read(new File("garaz.png"));
        mattoli = ImageIO.read(new File("mattoli.png"));

        autkoPOLUDNIE = ImageIO.read(new File("autkoPOLUDNIE.png"));
        autkoPOLNOC = ImageIO.read(new File("autkoPOLNOC.png"));
        autkoWSCHOD = ImageIO.read(new File("autkoWSCHOD.png"));
        autkoZACHOD = ImageIO.read(new File("autkoZACHOD.png"));

        skuterPOLNOC = ImageIO.read(new File("skuterPOLNOC.png"));
        skuterPOLUDNIE = ImageIO.read(new File("skuterPOLUDNIE.png"));
        skuterWSCHOD = ImageIO.read(new File("skuterWSCHOD.png"));
        skuterZACHOD = ImageIO.read(new File("skuterZACHOD.png"));

        firmowy = ImageIO.read(new File("firmowy.png"));
        staly = ImageIO.read(new File("staly.png"));
        okazjonalny = ImageIO.read(new File("okazjonalny.png"));

        Timer timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                
            }

        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent eMysz) {
                System.out.println("Klik!");
                klikKlient(eMysz.getX(), eMysz.getY());
                klikDostawca(eMysz.getX(), eMysz.getY());

            }
        });

    }

    private void klikKlient(int mouseX, int mouseY) {
        // moze bedziemy edytować listę, więc foreach może się nie sprawdzić
        for (int i = 0; i < restauracja.getListaKlientow().size(); i++) {
            Klient k = restauracja.getListaKlientow().get(i);
            if (mouseX >= k.getAdres().getWierzcholek().getPunkt().getX()
                    && mouseX <= k.getAdres().getWierzcholek().getPunkt().getX() + staly.getWidth()
                    && mouseY >= k.getAdres().getWierzcholek().getPunkt().getY()
                    && mouseY <= k.getAdres().getWierzcholek().getPunkt().getY() + staly.getHeight()) //wszystkie obrazki klientow maja tę samą szerokość i wysokość
            {
                System.out.println("Klient " + k.getNazwa());
                okienkoInfoKlient(k);

            }

        }
    }

    private void klikDostawca(int mouseX, int mouseY) {
        for (int i = 0; i < restauracja.getListaDostawcow().size(); i++) {
            Dostawca d = restauracja.getListaDostawcow().get(i);
            if (mouseX >= d.getPolozenie().getX()
                    && mouseX <= d.getPolozenie().getX() + autkoPOLUDNIE.getWidth()
                    && mouseY >= d.getPolozenie().getY()
                    && mouseY <= d.getPolozenie().getY() + autkoPOLUDNIE.getWidth()) //weźmiemy ten mniejszy wymiar, by nie zależało to od orientacji obrazka, i tak klika się w środek
            {
                okienkoInfoDostawca(d);
                System.out.println("Dostawca " + d.getImie() + " " + d.getNazwisko());
            }

        }
    }

    private void okienkoInfoDostawca(Dostawca d) {
        okienkoInfoDostawca = new JFrame("Dostawca i pojazd");
        okienkoInfoDostawca.setSize(300, 400);
        okienkoInfoDostawca.setVisible(true);
        okienkoInfoDostawca.setLayout(new GridLayout(10, 1));
        JLabel imie = new JLabel("Imię: " + d.getImie());
        okienkoInfoDostawca.add(imie);
        JLabel nazwisko = new JLabel("Nazwisko: " + d.getNazwisko());
        okienkoInfoDostawca.add(nazwisko);
        /*JCheckBox katB = new JCheckBox("B");
        if(d.getPosiadaneKategriePrawaJazdy().contains(KategoriaPrawaJazdy.B)){
            katB.setSelected(true);
        }
        katB.setEnabled(false);
        okienkoInfoDostawca.add(katB);*/
        String kategorieString = "";
        for (KategoriaPrawaJazdy k : d.getPosiadaneKategriePrawaJazdy()) {
            kategorieString += k.toString() + " ";
        }
        JLabel kategorie = new JLabel("Posiadane kategorie prawa jazdy: " + kategorieString);
        okienkoInfoDostawca.add(kategorie);
        JLabel nrRejestracyjny = new JLabel("Nr rejestracyjny używanego pojazdu: "
                + d.getUzywanyPojazd().getNrRejestracyjny());
        okienkoInfoDostawca.add(nrRejestracyjny);
        JLabel paliwo = new JLabel("Pojemność baku: "
                + d.getUzywanyPojazd().getPojemnoscBakuAktualna() + "/"
                + d.getUzywanyPojazd().getPOJEMNOSC_BAKU_MAKS());
        okienkoInfoDostawca.add(paliwo);
        JLabel ladownosc = new JLabel("Ładowność: "
                + d.getUzywanyPojazd().getLadownoscAktualna()
                + "/" + d.getUzywanyPojazd().getLADOWNOSC_MAKS());
        okienkoInfoDostawca.add(ladownosc);
        
        if (d.getDostarczaneZamowienia().size() > 0) {
            JLabel jadeDo;
            jadeDo = new JLabel("Jadę do klienta: " + d.getDostarczaneZamowienia().get(0).getZamawiajacy().getNazwa());
            okienkoInfoDostawca.add(jadeDo);
            JButton guzikZamowienie = new JButton("Informacje o dostarczanym zamówieniu");
            okienkoInfoDostawca.add(guzikZamowienie);
            guzikZamowienie.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    okienkoZamowienie(d.getDostarczaneZamowienia().get(0));

                }
            });
        }

        JButton guzikUsunDostawce = new JButton("Usuń dostawcę");
        okienkoInfoDostawca.add(guzikUsunDostawce);
        guzikUsunDostawce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                d.getUzywanyPojazd().setWolny(true);
                d.getUzywanyPojazd().setKierujacy(null);
                /*if(d.getWierzcholekNastepny() == d.getWierzcholekPoprzedni()){ //tu na pewno nie jest spełniony warunek bezpieczeństwa
                    
                }*/
                //d.getWierzcholekNastepny().setWolny(true); tak nie wolno przecież
                d.setPowrotAwaryjny(true);
                d.przekazSwojeZamowieniaZPowrotemDoRestauracji(); //jakże miły gest
                restauracja.getListaDostawcow().remove(d);
                d.setRunning(false);
                okienkoInfoDostawca.setVisible(false);

            }
        });
        JButton guzikPowrot = new JButton("Awaryjny powrót do restauracji");
        okienkoInfoDostawca.add(guzikPowrot);
        guzikPowrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Awaryjny powrót");
                d.setPowrotAwaryjny(true);
                //okienkoInfoDostawca.setVisible(false);

            }
        });

    }

    private void okienkoInfoKlient(Klient k) {
        okienkoInfoKlient = new JFrame("Informacje o kliencie");
        //okienkoInfoKlient.dispatchEvent(new WindowEvent(okienkoInfoKlient, WindowEvent.WINDOW_CLOSING));
        okienkoInfoKlient.setSize(300, 400);
        okienkoInfoKlient.setVisible(true);
        JLabel rodzaj;
        if (k instanceof KlientFirmowy) {
            rodzaj = new JLabel("Klient firmowy", new ImageIcon(firmowy), JLabel.LEFT);
            okienkoInfoKlient.setLayout(new GridLayout(10, 1));
        } else if (k instanceof KlientOkazjonalny) {
            okienkoInfoKlient.setLayout(new GridLayout(6, 1));
            rodzaj = new JLabel("Klient okazjonalny", new ImageIcon(okazjonalny), JLabel.LEFT);
        } else {
            okienkoInfoKlient.setLayout(new GridLayout(8, 1));
            rodzaj = new JLabel("Klient stały", new ImageIcon(staly), JLabel.LEFT);

        }
        okienkoInfoKlient.add(rodzaj);
        JLabel nazwa = new JLabel("Nazwa: " + k.getNazwa());
        okienkoInfoKlient.add(nazwa);
        JLabel nrTelefonu = new JLabel("Nr Telefonu: " + k.getNrTelefonu());
        okienkoInfoKlient.add(nrTelefonu);
        JLabel email = new JLabel("Adres e-mail: " + k.getEmail());
        okienkoInfoKlient.add(email);
        JLabel adres = new JLabel("Adres (nr domu): " + k.getAdres().getNrDomu());
        okienkoInfoKlient.add(adres);
        if (k instanceof KlientFirmowy) {
            JLabel adresKorespondecyjny = new JLabel("Adres korespondencyjny (nr domu): "
                    + ((KlientFirmowy) k).getAdresKorespondencyjny().getNrDomu());
            okienkoInfoKlient.add(adresKorespondecyjny);
            JLabel nrKonta = new JLabel("Nr konta: " + ((KlientFirmowy) k).getIndywidualnyNrKontaBankowego());
            okienkoInfoKlient.add(nrKonta);
            JLabel REGON = new JLabel("REGON: " + ((KlientFirmowy) k).getREGON());
            okienkoInfoKlient.add(REGON);
        } else if (k instanceof KlientStaly) {
            JLabel punkty = new JLabel("Liczba punktów lojalnościowych: " + ((KlientStaly) k).getLiczbaPunktowLojalnosciowych());
            okienkoInfoKlient.add(punkty);
            String znizkaString = String.format("%.2f%%\n", ((KlientStaly) k).getZnizka() * 100);
            JLabel znizka = new JLabel("Zniżka: " + znizkaString);
            okienkoInfoKlient.add(znizka);
        }
        JButton guzikUsun = new JButton("Usuń klienta");
        okienkoInfoKlient.add(guzikUsun);
        guzikUsun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                k.setRunning(false);
                restauracja.getListaKlientow().remove(k);
                okienkoInfoKlient.setVisible(false);

            }
        });

    }
    
    private void okienkoZamowienie(Zamowienie z){
        JFrame okienko = new JFrame("Zamówienie");
        okienko.setSize(300, 400);
        okienko.setVisible(true);
        okienko.setLayout(new GridLayout(5, 1));
        JLabel nazwaZam = new JLabel("Zamawiający: "+ z.getZamawiajacy().getNazwa());
        okienko.add(nazwaZam);
        JLabel godzina = new JLabel("Godzina złożenia: " + z.getGodzinaPrzyjecia());
        okienko.add(godzina);
        JLabel cena = new JLabel("Cena: " + z.getCenaZamowienia());
        okienko.add(cena);
        String nazwa = "";
        for(Posilek p: z.getListaPosilkow()){
            nazwa += p.getNazwa();
            nazwa += " ";
            nazwa += p.getKategoria();
            nazwa += " ";
            nazwa += p.getRozmiarPorcji();
            nazwa += " ";
            nazwa += p.getCena();
            nazwa += "\n";
        }
        
        JTextArea tekst = new JTextArea(nazwa);
        tekst.setEditable(false);
        JScrollPane sp = new JScrollPane(tekst);
        
        
        okienko.add(sp);
        
        
    }

}
