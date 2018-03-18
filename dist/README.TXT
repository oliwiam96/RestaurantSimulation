Oliwia Masian
127324
Informatyka, WI, studia stacjonarne
Grupa I6, poniedzia³ki 11:45-13:15

Instrukcja:
W zak³adkach Klienci/Dostawcy/Pojazdy s¹ odpowiednie guziki pozwalaj¹ce na dodawanie nowych obiektów. Dodawanie klienta jest rozbudowane  (mo¿na polom nadaæ w³asne nazwy). W zak³adce symulacja jest rysowane miasto. Po klikniêciu na klienta/ dostawcê (trochê trudno trafiæ) pojawiaj¹ siê okna informacyjne. Domyœlnie dostawca ma obydwie kategorie prawa jazdy, ale nie otrzymuje pojazdu (jeœli jest mniej pojazdów ni¿ dostawców, dostawcy bez pojazdów bêd¹ czekaæ na dodanie nowych pojazdów).
Jeœli chodzi o serializacjê, awaryjny powrót klienta  do restauracji  i usuwanie klienta… mo¿e siê to wydaje ³atwe (z pocz¹tku mo¿na by pomyœleæ, ¿e wystarczy zapisaæ restauracjê do pliku w przypadku serializacji albo ustawiæ jak¹œ zmienn¹ na inn¹ wartoœæ po klikniêciu mysz¹)- wcale nieprawda. Pos³ugiwanie siê tym grafem, w¹tkami i monitorami, obs³uga skrêtów- wszystko to jest ci¹giem przetwarzania, którego przerwanie dla zapisu/usuniêcia w¹tku/powrotu do restauracji, powoduje zawalenie siê ca³ego systemu.  Niestety muszê siê przyznaæ do pora¿ki na tym polu- jedna naprawa b³êdu generuje kolejne i tak w kó³ko. Czasem to dzia³a, jeœli nie trafimy akurat na jakiœ kluczowy moment przetwarzania, jak generacja nowej œcie¿ki, skrêt i zwi¹zana z tym zmiana kierunku- ale to nie jest rozwi¹zanie poprawne. Wysy³am to Panu w takiej formie, jakiej mam, wszystkie te opcje s¹ dostêpne, ale zwykle nie dzia³aj¹- jakiœ wierzcho³ek w grafie nie zostaje zwolniony i mamy zakleszczenie.  Nie wyobra¿am sobie u¿yæ innej struktury ni¿ grafu do  rozwi¹zania tego problemu- postawi³ Pan zbyt wiele wymagañ (wy³¹czny dostêp do wszystkich skrzy¿owañ, wolna jazda szybszego samochodu za wolniejszym motocyklem, a jak s¹ skrzy¿owania to przyda³yby siê te¿ skrêty i obracanie pojazdów- nie chcia³am rysowaæ samych kwadratów), a na tej niestety nie uda³o mi siê tego dokonaæ. Kiedy zda³am sobie sprawê, ¿e to nie dzia³a, niestety nie mog³am ju¿ odejœæ od grafu na koszt jakiejœ innej struktury (rezygnuj¹c z czêœci wymagañ), bo wymaga³oby to przebudowania ca³ego projektu.  Dlatego wysy³am Panu dwie wersjê- bez serializacji i z serializacj¹ (nie dzia³a, ale chocia¿ próbowa³am).
Uwagi:

Po spadku aktulanej pojemnoœci  w baku poni¿ej 30,0 jednostek paliwa nastêpuje powrót do restauracji. Ka¿dy wierzcho³ek kosztuje 1,0 jednostkê paliwa. Po powrocie do restauracji pojazd jest tankowany do pe³na (200,0 jednostek).
Maksymalna ³adownoœæ pojazdu to: dla samochodu 10 jednostek, dla skutera 5 jednostek. Jedno zamówienie (niezale¿nie co w nim jest) kosztuje 1 jednostkê ³adownoœci.
Skutery je¿d¿¹ 2 razy wolniej ni¿ auta.
Zawracaæ mo¿na tylko na skrzy¿owaniach.
Czasem (rzadko, ale jednak), jeden pojazd delikatnie „wje¿d¿a w ty³/bok” drugiego, nie jest to b³¹d synchronizacji (dostêp do wierzcho³ków jest wy³¹czny), ale wynik tego, ¿e wspó³rzêdne wpisywa³am rêcznie  i czasem odleg³oœæ miêdzy wierzcho³kami jest mniejsza ni¿ d³ugoœæ pojazdu (nie mam ju¿ czasu tego poprawiaæ, a a¿ tak to nie razi).

W moim miasteczku nie wymyœlono jeszcze kierunkowskazów, a ¿e kierowcy nie czytaj¹ sobie w myœlach (by³o by to dziwne) i jednoczeœnie s¹ bardzo ostro¿ni (nie chc¹ ¿adnych kolizji), nawet w sytuacjach mo¿liwego bezkolizyjnego przejazdu przez skrzy¿owanie, wol¹ poczekaæ na swoj¹ kolej.
Proszê zauwa¿yæ, ¿e naro¿niki tak¿e uzna³am za skrzy¿owanie (kto wie co jest poza map¹).
Po klikniêciu na domek klienta/dostawcê, pojawia siê okno informacyjne (dostawcy siê poruszaj¹, dlatego czasem trudno w nich trafiæ).
Pod jednym adresem mo¿e mieszkaæ wielu klientów, a domek ma kolor  odpowiedni do ostatnio zamieszkanego w nim rodzaju klienta .
Za ka¿de zamówienie klient dostaje 100 punktów lojalnoœciowych.  Metoda getCenaZamówienia uwzglêdnia wszystkie zni¿ki. Dolicza te¿ cenê za dowóz – 10z³(poni¿ej zamówienia 50 z³ lub adresu klienta bêd¹cego daleko od restuarcji- jeœli droga liczy > 10 wierzcho³ków). Nie by³o sprecyzowane, czy promocje siê ³¹cz¹- zni¿ka obejmuje dowóz itp., dlatego zrobi³am to wed³ug uznania- dok³adniej widaæ to w tej metodzie- najpierw dodawana jest cena za dowóz, potem obni¿ki (wiêc dla klienta sta³ego wychodzi taniej).
Brakuje mi tworzenia zamówienia w panelu kontrolnym i mo¿liwoœci stworzenia dostawcy z w³asn¹ zawartoœci¹ pól (ale dla klienta to jest).  
Jeszcze raz zaznaczam, ¿e ikonki dostawców s¹ ma³e(zw³aszcza skuter) i siê poruszaj¹- trudno w nie trafiæ, ale proszê siê nie zniechêcaæ. 
W projekcie wykorzysta³am swoje rysunki oraz kilka ze strony: all-free-download.com 