Oliwia Masian
127324
Informatyka, WI, studia stacjonarne
Grupa I6, poniedzia�ki 11:45-13:15

Instrukcja:
W zak�adkach Klienci/Dostawcy/Pojazdy s� odpowiednie guziki pozwalaj�ce na dodawanie nowych obiekt�w. Dodawanie klienta jest rozbudowane  (mo�na polom nada� w�asne nazwy). W zak�adce symulacja jest rysowane miasto. Po klikni�ciu na klienta/ dostawc� (troch� trudno trafi�) pojawiaj� si� okna informacyjne. Domy�lnie dostawca ma obydwie kategorie prawa jazdy, ale nie otrzymuje pojazdu (je�li jest mniej pojazd�w ni� dostawc�w, dostawcy bez pojazd�w b�d� czeka� na dodanie nowych pojazd�w).
Je�li chodzi o serializacj�, awaryjny powr�t klienta  do restauracji  i usuwanie klienta� mo�e si� to wydaje �atwe (z pocz�tku mo�na by pomy�le�, �e wystarczy zapisa� restauracj� do pliku w przypadku serializacji albo ustawi� jak�� zmienn� na inn� warto�� po klikni�ciu mysz�)- wcale nieprawda. Pos�ugiwanie si� tym grafem, w�tkami i monitorami, obs�uga skr�t�w- wszystko to jest ci�giem przetwarzania, kt�rego przerwanie dla zapisu/usuni�cia w�tku/powrotu do restauracji, powoduje zawalenie si� ca�ego systemu.  Niestety musz� si� przyzna� do pora�ki na tym polu- jedna naprawa b��du generuje kolejne i tak w k�ko. Czasem to dzia�a, je�li nie trafimy akurat na jaki� kluczowy moment przetwarzania, jak generacja nowej �cie�ki, skr�t i zwi�zana z tym zmiana kierunku- ale to nie jest rozwi�zanie poprawne. Wysy�am to Panu w takiej formie, jakiej mam, wszystkie te opcje s� dost�pne, ale zwykle nie dzia�aj�- jaki� wierzcho�ek w grafie nie zostaje zwolniony i mamy zakleszczenie.  Nie wyobra�am sobie u�y� innej struktury ni� grafu do  rozwi�zania tego problemu- postawi� Pan zbyt wiele wymaga� (wy��czny dost�p do wszystkich skrzy�owa�, wolna jazda szybszego samochodu za wolniejszym motocyklem, a jak s� skrzy�owania to przyda�yby si� te� skr�ty i obracanie pojazd�w- nie chcia�am rysowa� samych kwadrat�w), a na tej niestety nie uda�o mi si� tego dokona�. Kiedy zda�am sobie spraw�, �e to nie dzia�a, niestety nie mog�am ju� odej�� od grafu na koszt jakiej� innej struktury (rezygnuj�c z cz�ci wymaga�), bo wymaga�oby to przebudowania ca�ego projektu.  Dlatego wysy�am Panu dwie wersj�- bez serializacji i z serializacj� (nie dzia�a, ale chocia� pr�bowa�am).
Uwagi:

Po spadku aktulanej pojemno�ci  w baku poni�ej 30,0 jednostek paliwa nast�puje powr�t do restauracji. Ka�dy wierzcho�ek kosztuje 1,0 jednostk� paliwa. Po powrocie do restauracji pojazd jest tankowany do pe�na (200,0 jednostek).
Maksymalna �adowno�� pojazdu to: dla samochodu 10 jednostek, dla skutera 5 jednostek. Jedno zam�wienie (niezale�nie co w nim jest) kosztuje 1 jednostk� �adowno�ci.
Skutery je�d�� 2 razy wolniej ni� auta.
Zawraca� mo�na tylko na skrzy�owaniach.
Czasem (rzadko, ale jednak), jeden pojazd delikatnie �wje�d�a w ty�/bok� drugiego, nie jest to b��d synchronizacji (dost�p do wierzcho�k�w jest wy��czny), ale wynik tego, �e wsp�rz�dne wpisywa�am r�cznie  i czasem odleg�o�� mi�dzy wierzcho�kami jest mniejsza ni� d�ugo�� pojazdu (nie mam ju� czasu tego poprawia�, a a� tak to nie razi).

W moim miasteczku nie wymy�lono jeszcze kierunkowskaz�w, a �e kierowcy nie czytaj� sobie w my�lach (by�o by to dziwne) i jednocze�nie s� bardzo ostro�ni (nie chc� �adnych kolizji), nawet w sytuacjach mo�liwego bezkolizyjnego przejazdu przez skrzy�owanie, wol� poczeka� na swoj� kolej.
Prosz� zauwa�y�, �e naro�niki tak�e uzna�am za skrzy�owanie (kto wie co jest poza map�).
Po klikni�ciu na domek klienta/dostawc�, pojawia si� okno informacyjne (dostawcy si� poruszaj�, dlatego czasem trudno w nich trafi�).
Pod jednym adresem mo�e mieszka� wielu klient�w, a domek ma kolor  odpowiedni do ostatnio zamieszkanego w nim rodzaju klienta .
Za ka�de zam�wienie klient dostaje 100 punkt�w lojalno�ciowych.  Metoda getCenaZam�wienia uwzgl�dnia wszystkie zni�ki. Dolicza te� cen� za dow�z � 10z�(poni�ej zam�wienia 50 z� lub adresu klienta b�d�cego daleko od restuarcji- je�li droga liczy > 10 wierzcho�k�w). Nie by�o sprecyzowane, czy promocje si� ��cz�- zni�ka obejmuje dow�z itp., dlatego zrobi�am to wed�ug uznania- dok�adniej wida� to w tej metodzie- najpierw dodawana jest cena za dow�z, potem obni�ki (wi�c dla klienta sta�ego wychodzi taniej).
Brakuje mi tworzenia zam�wienia w panelu kontrolnym i mo�liwo�ci stworzenia dostawcy z w�asn� zawarto�ci� p�l (ale dla klienta to jest).  
Jeszcze raz zaznaczam, �e ikonki dostawc�w s� ma�e(zw�aszcza skuter) i si� poruszaj�- trudno w nie trafi�, ale prosz� si� nie zniech�ca�. 
W projekcie wykorzysta�am swoje rysunki oraz kilka ze strony: all-free-download.com 