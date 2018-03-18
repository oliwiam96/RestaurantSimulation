/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restauracja;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oliwia
 */
public class Dostawca extends Czlowiek implements Runnable, Serializable {

    /**
     * @return the powrotAwaryjny
     */
    public boolean isPowrotAwaryjny() {
        return powrotAwaryjny;
    }

    /**
     * @param powrotAwaryjny the powrotAwaryjny to set
     */
    public void setPowrotAwaryjny(boolean powrotAwaryjny) {
        this.powrotAwaryjny = powrotAwaryjny;
    }

    private Pojazd uzywanyPojazd;
    private Set<KategoriaPrawaJazdy> posiadaneKategriePrawaJazdy = new HashSet<KategoriaPrawaJazdy>();
    private List<Zamowienie> dostarczaneZamowienia = new ArrayList<Zamowienie>();
    private Map<DayOfWeek, DzienPracy> dniPracy = new HashMap<DayOfWeek, DzienPracy>();
    private Ochroniarz zamek;
    private Restauracja restauracja;

    private boolean wtrakcieDostarczania; //cos z tym trezba zrobic
    private boolean prosto;
    private List<Wierzcholek> sciezka;
    private Punkt polozenie;
    private Wierzcholek wierzcholekPoprzedni;
    private Wierzcholek wierzcholekNastepny;
    private KierunkiSwiata kierunek;
    private boolean running;
    private boolean powrotAwaryjny;
    private boolean powrotPaliwo;
    private int indeksSciezki;

    public void run() {
        while (isRunning()) {
            zdobadzPojazd(); //jeśli już go ma nic się nie stanie
            if (!isWtrakcieDostarczania()) { //jest w restauracji, poproś o nowe zamówienie (ma już pojazd)
                for (int i = 0; i < uzywanyPojazd.getLADOWNOSC_MAKS()
                        && uzywanyPojazd.getLadownoscAktualna() < uzywanyPojazd.getLADOWNOSC_MAKS(); i++) {
                    if (poprosZamowienie()) { //zwiększamy o 1 załadowanie pojazdu
                        uzywanyPojazd.setLadownoscAktualna(uzywanyPojazd.getLadownoscAktualna() + 1);
                        setWtrakcieDostarczania(true);
                    }
                }
            } else { //po wznowieniu symulacji albo po udanej min. 1 próbie przyjęcia zamówienia
                dostarczZamowienia();
                setWtrakcieDostarczania(false); //powrocil już do restauracji
            }
        }
        this.powrotDoRestauracji();
    }

    /**
     * Dostarczenie po kolei wszystkich zebranych zamówień do klientóws.
     */
    private void dostarczZamowienia() {
        System.out.println("Mam do dostarczenia " + dostarczaneZamowienia.size() + "zamowien");
        //System.out.println(dostarczaneZamowienia.get(dostarczaneZamowienia.size() - 1).getListaPosilkow().get(0).getNazwa());
        //System.out.println(dostarczaneZamowienia.get(dostarczaneZamowienia.size() - 1).getGodzinaPrzyjecia());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
        }

        //jeszcze co z paliwem!!! do zrobienia!!!
        // ustawianie poczatkowego polozenia, klienta wierzcholek
        for (int i = 0; i < dostarczaneZamowienia.size(); i++) { //bede zmieniac te liste, wiec foreach sie nie sprawdzi
            Wierzcholek cel = dostarczaneZamowienia.get(i).getZamawiajacy().getAdres().getWierzcholek();
            jedzDoCelu(cel);
            // zaśnij na 1s, żeby dostarczyć zamówienie
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
            }
            dostarczaneZamowienia.remove(i);
            uzywanyPojazd.setLadownoscAktualna(
                    uzywanyPojazd.getLadownoscAktualna() - 1);//zmien załadowanie pojazdu
            System.out.println("Pozostalo mi do dostarczenia " + dostarczaneZamowienia.size());
        }
        powrotDoRestauracji();
        //zasnij na 1 s
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Pobranie przez dostawcę nowego zamówienia, jeśli jest jakieś
     * nieobsłużone. Metoda synchronizowana.
     *
     * @return true, jeśli prośba się udała, false w przeciwnym wypadku
     */
    private boolean poprosZamowienie() {
        synchronized (getZamek()) {
            if (!restauracja.getZamowieniaGotowe().isEmpty()) {
                Zamowienie zamowienie = restauracja.getZamowieniaGotowe().get(0);
                dostarczaneZamowienia.add(zamowienie);
                restauracja.getZamowieniaGotowe().remove(zamowienie);
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda, która do skutku próbuje pobrać pojazd z restuaracji. Aktywne
     * czekanie, zasypianie na chwilę po każdej nieudanej próbie.
     */
    private void zdobadzPojazd() {
        while (this.uzywanyPojazd == null) {
            for (KategoriaPrawaJazdy k : posiadaneKategriePrawaJazdy) {
                uzywanyPojazd = restauracja.wydajPojazd(k);
                if (uzywanyPojazd != null) {
                    uzywanyPojazd.setKierujacy(this);
                    uzywanyPojazd.setWolny(false);
                    break;
                }
                System.out.println("Czekam na pojazd");
            }
            //zaśnij na chwile i sprawdz ponownie, czy cos sie zmienilo
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Realizacja drogi od obecnego wierzchołka do celu. (Część związana z
     * wyznaczaniem ściezki, zmianą współrzędnych, obrotami przy skrętach itp.)
     *
     * @param cel
     */
    private void jedzDoCelu(Wierzcholek cel) {
        
        int jj;
        boolean wczytano = false;
        
        if(sciezka != null && wierzcholekNastepny == null){
            System.out.println("TAKIE RZECZY SIE ZDARZAJA");
        }
        if(sciezka == null || wierzcholekNastepny == null){
            jj = 1;
            this.setSciezka(restauracja.getMapa().obliczNajkrotszaSciezke(getWierzcholekPoprzedni(), cel));
            if(getSciezka().get(getSciezka().size()-1)!= restauracja.getPOLOZENIE_RESTAURACJI()){
                getSciezka().remove(getSciezka().size() - 1); //usuwam ostatni element, bo jest nim domek (nie robimy wjazdu na podwórko do klienta)
            }
        }
        
        else{
            System.out.println("Wczytalem sobie- serio");
            wczytano = true;
            jj = sciezka.indexOf(wierzcholekPoprzedni) + 1;// ???
        }
        if(sciezka != null && wierzcholekNastepny == wierzcholekPoprzedni){
            System.out.println("poprzedni == nastepny!");
            wczytano = false; //musisz miec zgode na nast
        }
        if(powrotAwaryjny){
            powrotAwaryjny = false;
            sciezka = restauracja.getMapa().obliczNajkrotszaSciezke(
                    getWierzcholekPoprzedni(), restauracja.getPOLOZENIE_RESTAURACJI());
        }
        
        for (int j = jj; j < getSciezka().size(); j++) {
            if (powrotAwaryjny) {
                System.out.println("Awaryjny!!!");
                powrotDoRestauracji();
                break;
            }
            /*boolean wczytano = false;
            if((getSciezka().get(j-1).getPunkt().getX() != polozenie.getX()
                    || getSciezka().get(j-1).getPunkt().getY() != polozenie.getY())
                    && j==1){
                wczytano = true;
                System.out.println("Wczytano");
            }
            */
            if(!wczytano){
                
                while (!restauracja.getMapa().zgodaNaNast(sciezka, j)) //puste, aktywne czekanie, trochę słabo, ale już nie zdążę raczej tego zrobić na sygnałach
                        ;
                this.setWierzcholekNastepny(getSciezka().get(j));
            }else{
                wczytano = false; //tylko raz tak mozna
                
            }
            /*
            while (!restauracja.getMapa().zgodaNaNast(sciezka, j)) //puste, aktywne czekanie, trochę słabo, ale już nie zd
                ;
            */
            
            
            uzywanyPojazd.setPojemnoscBakuAktualna(
                    uzywanyPojazd.getPojemnoscBakuAktualna() - 1); //kazdy wierzcholek to koszt 1 jednostki
            
            if (getWierzcholekNastepny().isSkrzyzowanie()) {
                /* musimy zmienić współrzędne tego wierzchołka,
                        a w zasadzie jego kopii (nie chcemy zmienić trwale grafu),
                        żeby zagwarantować poprawnie wykonany manewr
                        (bez ściągania do osi jezdni)
                        
                        założenie- skrzyżowanie nigdy nie jest pierwszym ani ostatnim wierzchołkiem w ścieżce (nawet po usunięciu wierzchołka podwórka)
                 */

                
                    //jazda prosto (bez skretu), nie zmieniaj kierunku:
                    // prawo/lewo
            if (getSciezka().get(j - 1).getPunkt().getY() == getSciezka().get(j + 1).getPunkt().getY()) {
                this.setWierzcholekNastepny(new Wierzcholek(getSciezka().get(j).getPunkt().getX(), getSciezka().get(j - 1).getPunkt().getY(), false, null));
                    setProsto(true);
                //zawracanie
                if (getKierunek() == KierunkiSwiata.POLNOC) {
                        setKierunek(KierunkiSwiata.POLUDNIE);
                } else if (getKierunek() == KierunkiSwiata.POLUDNIE) {
                        setKierunek(KierunkiSwiata.POLNOC);
                }

            } // gora/dol
            else if (getSciezka().get(j - 1).getPunkt().getX() == getSciezka().get(j + 1).getPunkt().getX()) {
                this.setWierzcholekNastepny(new Wierzcholek(getSciezka().get(j - 1).getPunkt().getX(), getSciezka().get(j).getPunkt().getY(), false, null));
                    setProsto(true);
                //zawracanie
                if (getKierunek() == KierunkiSwiata.ZACHOD) {
                        setKierunek(KierunkiSwiata.WSCHOD);
                } else if (getKierunek() == KierunkiSwiata.WSCHOD) {
                        setKierunek(KierunkiSwiata.ZACHOD);
                }

            } //skret, zmiana kierunku
            else {
                    setProsto(false);
                if (getKierunek() == KierunkiSwiata.POLNOC || getKierunek() == KierunkiSwiata.POLUDNIE) {
                    //(x1, y2)
                    this.setWierzcholekNastepny(new Wierzcholek(getSciezka().get(j - 1).getPunkt().getX(), getSciezka().get(j + 1).getPunkt().getY(), false, null));
                    this.getWierzcholekNastepny().setSkrzyzowanie(true);
                } else {//if (kierunek == KierunkiSwiata.ZACHOD || kierunek == KierunkiSwiata.WSCHOD) {
                    //(x2, y1)
                    this.setWierzcholekNastepny(new Wierzcholek(getSciezka().get(j + 1).getPunkt().getX(), getSciezka().get(j - 1).getPunkt().getY(), false, null));
                    this.getWierzcholekNastepny().setSkrzyzowanie(true);
                }
            }
        }

        if (getWierzcholekPoprzedni().isSkrzyzowanie() && !isProsto()) { //&& !prosto
            if (getKierunek() == KierunkiSwiata.POLNOC || getKierunek() == KierunkiSwiata.POLUDNIE) {
                if (getWierzcholekPoprzedni().getPunkt().getX() < getWierzcholekNastepny().getPunkt().getX()) {
                        setKierunek(KierunkiSwiata.WSCHOD);
                } else {
                        setKierunek(KierunkiSwiata.ZACHOD);
                }
            } else {//if (kierunek == KierunkiSwiata.WSCHOD || kierunek == KierunkiSwiata.ZACHOD) {
                if (getWierzcholekPoprzedni().getPunkt().getY() < getWierzcholekNastepny().getPunkt().getY()) {
                        setKierunek(KierunkiSwiata.POLUDNIE);
                } else {
                        setKierunek(KierunkiSwiata.POLNOC);
                }
            }
        }

        while (polozenie.getX() != getWierzcholekNastepny().getPunkt().getX()
                || polozenie.getY() != getWierzcholekNastepny().getPunkt().getY()) {

            if (polozenie.getX() != getWierzcholekNastepny().getPunkt().getX()) {
                if (getWierzcholekPoprzedni().getPunkt().getX() < getWierzcholekNastepny().getPunkt().getX()) {
                    polozenie.setX(polozenie.getX() + 1);
                } else {
                    polozenie.setX(polozenie.getX() - 1);
                }
            }
            if (polozenie.getY() != getWierzcholekNastepny().getPunkt().getY()) {
                if (getWierzcholekPoprzedni().getPunkt().getY() < getWierzcholekNastepny().getPunkt().getY()) {
                    polozenie.setY(polozenie.getY() + 1);
                } else {
                    polozenie.setY(polozenie.getY() - 1);
                }
            }
            //System.out.println(this.getImie()+ " x "+ polozenie.getX()+ " y " + polozenie.getY());
            //System.out.println("polozenie: "+polozenie);
            try {
                float czas = 20*100 / uzywanyPojazd.getPREDKOSC_MAKS();
                Thread.sleep((int) czas); //getPredkosc auta
            } catch (InterruptedException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        setWierzcholekPoprzedni(getSciezka().get(j));
        if (uzywanyPojazd.getPojemnoscBakuAktualna() < 30 && !isPowrotPaliwo()) { //uznajmy, że od 30 rezerwa
            System.out.println("Powrot paliwo");
                setPowrotPaliwo(true);
            powrotDoRestauracji();
                setPowrotPaliwo(false);
            break;
        }

    }
    sciezka = null;
}

public Dostawca(Restauracja restauracja) {
        super();
        setRestauracja(restauracja);
        inicjacja();

    }

    public Dostawca(Restauracja restauracja, Pojazd uzywanyPojazd) {
        super();
        setRestauracja(restauracja);
        inicjacja();
        setUzywanyPojazd(uzywanyPojazd);
    }

    /**
     * Inicjująca obiekt dostawcy metoda wywoływana w konstruktorach.
     */
    private void inicjacja() {
        //czlowiek- orkiestra :), domyślnie jeździ wszystkim
        this.posiadaneKategriePrawaJazdy.add(KategoriaPrawaJazdy.B);
        this.posiadaneKategriePrawaJazdy.add(KategoriaPrawaJazdy.AM);
        polozenie = new Punkt(restauracja.getPOLOZENIE_RESTAURACJI().getPunkt());
        this.setWtrakcieDostarczania(false);
        setWierzcholekPoprzedni(restauracja.getPOLOZENIE_RESTAURACJI());
        setKierunek(KierunkiSwiata.POLUDNIE);//POLNOC; //wyjeżdżając z garażu jedzie się na południe
        setZamek(restauracja.getOchroniarz());
        setRunning(true);

    }

    /**
     * @return the uzywanyPojazd
     */
    public Pojazd getUzywanyPojazd() {
        return uzywanyPojazd;
    }

    /**
     * Ustawia używany pojazd dostawcy, również pojazdowi przypisuje referncje
     * na dostawcę, ustawia, że pojazd nie jest już wolny.
     *
     * @param uzywanyPojazd the uzywanyPojazd to set
     */
    public void setUzywanyPojazd(Pojazd uzywanyPojazd) {
        uzywanyPojazd.setKierujacy(this);
        uzywanyPojazd.setWolny(false);
        this.uzywanyPojazd = uzywanyPojazd;
    }

    /**
     * @return the posiadaneKategriePrawaJazdy
     */
    public Set<KategoriaPrawaJazdy> getPosiadaneKategriePrawaJazdy() {
        return posiadaneKategriePrawaJazdy;
    }

    /**
     * @param posiadaneKategriePrawaJazdy the posiadaneKategriePrawaJazdy to set
     */
    public void setPosiadaneKategriePrawaJazdy(Set<KategoriaPrawaJazdy> posiadaneKategriePrawaJazdy) {
        this.posiadaneKategriePrawaJazdy = posiadaneKategriePrawaJazdy;
    }

    /**
     * @return the dostarczaneZamowienia
     */
    public List<Zamowienie> getDostarczaneZamowienia() {
        return dostarczaneZamowienia;
    }

    /**
     * @param dostarczaneZamowienia the dostarczaneZamowienia to set
     */
    public void setDostarczaneZamowienia(List<Zamowienie> dostarczaneZamowienia) {
        this.dostarczaneZamowienia = dostarczaneZamowienia;
    }

    /**
     * @return the dniPracy
     */
    public Map<DayOfWeek, DzienPracy> getDniPracy() {
        return dniPracy;
    }

    /**
     * @param dniPracy the dniPracy to set
     */
    public void setDniPracy(Map<DayOfWeek, DzienPracy> dniPracy) {
        this.dniPracy = dniPracy;
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
     * @return the polozenie
     */
    public Punkt getPolozenie() {
        return polozenie;
    }

    /**
     * @param polozenie the polozenie to set
     */
    public void setPolozenie(Punkt polozenie) {
        this.polozenie = polozenie;
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

    private void powrotDoRestauracji() {
        jedzDoCelu(restauracja.getPOLOZENIE_RESTAURACJI()); //powrót do restauracji
        uzywanyPojazd.zatankujDoPelna(); //w restauracji tankowanie
    }
    /**
     * Na chwilę przed usunięciem dostawcy należy wykonać tę metodę, by zwrócił dostarczane zamówienia do restauracji.
     */
    public void przekazSwojeZamowieniaZPowrotemDoRestauracji(){
        synchronized(getZamek()){
            for(Zamowienie z: this.dostarczaneZamowienia){
                restauracja.getZamowieniaGotowe().add(z);
            }
        }
    }

    /**
     * @return the wtrakcieDostarczania
     */
    public boolean isWtrakcieDostarczania() {
        return wtrakcieDostarczania;
    }

    /**
     * @param wtrakcieDostarczania the wtrakcieDostarczania to set
     */
    public void setWtrakcieDostarczania(boolean wtrakcieDostarczania) {
        this.wtrakcieDostarczania = wtrakcieDostarczania;
    }

    /**
     * @return the prosto
     */
    public boolean isProsto() {
        return prosto;
    }

    /**
     * @param prosto the prosto to set
     */
    public void setProsto(boolean prosto) {
        this.prosto = prosto;
    }

    /**
     * @return the sciezka
     */
    public List<Wierzcholek> getSciezka() {
        return sciezka;
    }

    /**
     * @param sciezka the sciezka to set
     */
    public void setSciezka(List<Wierzcholek> sciezka) {
        this.sciezka = sciezka;
    }

    /**
     * @return the wierzcholekPoprzedni
     */
    public Wierzcholek getWierzcholekPoprzedni() {
        return wierzcholekPoprzedni;
    }

    /**
     * @param wierzcholekPoprzedni the wierzcholekPoprzedni to set
     */
    public void setWierzcholekPoprzedni(Wierzcholek wierzcholekPoprzedni) {
        this.wierzcholekPoprzedni = wierzcholekPoprzedni;
    }

    /**
     * @return the wierzcholekNastepny
     */
    public Wierzcholek getWierzcholekNastepny() {
        return wierzcholekNastepny;
    }

    /**
     * @param wierzcholekNastepny the wierzcholekNastepny to set
     */
    public void setWierzcholekNastepny(Wierzcholek wierzcholekNastepny) {
        this.wierzcholekNastepny = wierzcholekNastepny;
    }

    /**
     * @return the kierunek
     */
    public KierunkiSwiata getKierunek() {
        return kierunek;
    }

    /**
     * @param kierunek the kierunek to set
     */
    public void setKierunek(KierunkiSwiata kierunek) {
        this.kierunek = kierunek;
    }

    /**
     * @return the powrotPaliwo
     */
    public boolean isPowrotPaliwo() {
        return powrotPaliwo;
    }

    /**
     * @param powrotPaliwo the powrotPaliwo to set
     */
    public void setPowrotPaliwo(boolean powrotPaliwo) {
        this.powrotPaliwo = powrotPaliwo;
    }

}
