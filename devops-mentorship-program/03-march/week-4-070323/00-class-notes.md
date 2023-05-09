# 📝 Class notes
## 📅 Date: 07.03.2023.

## Racunarske Mreze
**Racunarske mreze** su skup povezanih racunara i drugih uredjaja koji su medjusobno povezani radi razmjene informacija i resursa. Ove mreze omogucuju racunarima da komuniciraju i razmjenjuju podatke jedni s drugima putem zicanih ili bezicnih veza.
Postoji nekoliko razlicitih vrsta racunarskih mreza, ukljucujuci lokalne mreze (LAN), mreze sireg podrucja (WAN), virtualne privatne mreze (VPN) itd.

Internet je racunarska mreza koja povezuje milione racunarskih uredjaja sirom svijeta.
![Internet](/devops-mentorship-program/03-march/week-4-070323/files/internet.png)

## OSI model
Medjunarodna organizacija za standardizaciju je krajem 70tih godina predlozila organizaciju racunarskih mreza u sedam slojeva. Takva arhitektura mreza dobila je naziv model otvorenog sistem za medjusobno povezivanje (Eng. Open System Interconnection - OSI).

![OSI Model](/devops-mentorship-program/03-march/week-4-070323/files/osi-model.jpg)

- Aplikativni sloj
- Prezentacijski sloj
- Sloj sesije
- Transportni sloj
- Mrezni sloj
- Sloj veze
- Fizicki sloj

![OSI Model Tabela](/devops-mentorship-program/03-march/week-4-070323/files/osi-model-tabela.png)
*Tabela i Slika su preuzeti sa stranice [Layers of OSI Model](https://www.geeksforgeeks.org/layers-of-osi-model/)

### Aplikativni sloj

Na aplikativnom sloju nalaze se mrezne aplikacije i njihovi protokoli aplikativnog sloja. Aplikativnom sloju pripadaju protokoli kao sto su `HTTP`, `SMTP`, `FTP`. Odredjene mrezne funkcionalnosti kao sto je npr: `DNS translacija` takodjer se obavljaju na aplikativnom sloju.

### Prezentacijski sloj

Uloga prezentacijskog sloja je da aplikacijama koje medjusobno komuniciraju, razmijenjuju podatke, obezbjedi usluge koje omogucavaju tumacenje znacenja ramzmijenjenih podataka.

### Sloj sesije

Sloj sesije obezbjedjuje razgranicavanje i sinhronizovanje razmjene podatka ukljucujuci i nacin za stvaranje tacke za oporavak u slucaju greske. Takodjer omogucava i seme za oporavak podataka.

### Transportni sloj

Transportni sloj prenosi poruke aplikativnog sloja izmedju krajnjih tacaka aplikacije. Protokoli transportnog sloja su `TCP` i `UDP`.
`TCP` protokol aplikacijama koje ga koriste nudi uslugu za uspostavljanjem veze. Ova usluga podrazumjeva garantovanu isporuku poruka aplikativnog sloja do odredista i kontrolu toka. Za razliku od `TCP` protokola, `UDP` protokol aplikacijama koje ga koriste nudi uslugu bez uspostavljanja veze sto ga cini manje pouzdanim jer nema kontrolu toka i kontrolu zagusenja.

### Mrezni sloj

Mrezni sloj odgovoran je za prenosenje paketa mreznog sloja poznatih kao `datagrami` od jednog racunara do drugog. **Datagrami** su osnovna jedinica za prenos podataka u mrezama koje koriste paketno prenosenje podataka. Datagrami su obicno sastavljeni od zaglavlja (header) i korisnih podataka (payload). Zaglavlje sadrzi informacije o adresama odredista i izvora, kao i druge kontrole informacija koje su potrebne za uspjesan prijenos podataka. Payload sadrzi stvarne podatke koji se prenose preko mreze. Protokol transportnog sloja u izvornom racunaru prosljedjuje paket (odnosno segment) transportnog sloja i odredisnu mreznu adresu. **Mrezni sloj je taj koji obezbjedjuje isporuku paketa do transportnog sloja na odredisnom racunaru.**

**IP protokol** pripada mreznom sloju. Mrezni sloj takodjer obuhvata protokole za rutiranje.

### Sloj veze

Mrezni sloj usmjerava datagrame kroz niz rutera izmedju izvora i odredista. Za prenosenje paketa od jednog covera (racunara ili rutera) do sljedeceg, mrezni sloj se oslanja na usluge sloja veze. Primjeri protokola sloja veze su `ethernet`, `wifi` kao i protokoli koji se koriste za kablovske pristupne mreze.

### Fizicki sloj

Zadatak fizickog sloja jeste prenosenje pojedinacnih bitova iz istog paketa prosljedjenog od strane sloja veze izmedju susjednih cvorova. Protokoli na ovom sloju zavise od vrste linka ali i medija uz pomoc kojeg je taj link ostvaren (opticka vlakna, bakrene parice i sl.)

## Protokoli
Mrezni protokol slican je pravilu ponasanja medju ljudima. **Svime sto se desava na internetu a obuhvata komunikaciju dva udaljena samostalna dijela ili vise njih upravlja neki protokol.**

Protokol definise format poruke i redoslijed po kojem se ta poruka razmijenjuje izmedju najmenje dvije zasebne cjeline koje medjusobno komuniciraju, kao i postupke koji se preduzimaju poslije slanja i/ili prijema odredjenih poruka ili nekog drugog dogadjaja.

Internet kao i sve druge racunarske mreze koriste protokole, prilikom komunikacije za ispunjavanje razlicitih zadataka koriste se razliciti protokoli.

Svaki od protokola pripada jednom od slojeva **OSI modela**. Protokol na nekom sloju moze da se sprovodi kako u softveru tako i u hardveru, ali i uporedo. Protokoli aplikativnog sloja (`HTTP`, `SMTP`) skoro uvijek se sprovode u softveru krajnjih sistema. Isti je slucaj i sa protokolima transportnog sloja. Posto su fizicki sloj i sloj veze podataka odgovorni za komunikaciju preko odredjenog linka, protokoli na njima obicno se sporovode u mreznoj kartici koja je povezana sa datim linkom. Protokoli na mreznom sloju obicno se istovremeno sprovode i u softveru i u hardveru. Svi protokoli zajedno nazivaju se skup protokola.

## IPv4 Adresiranje
Racunar obicno ima samo jedan link prema mrezi: **kada IP adresa u tom racunaru zeli da posalje datagram (paket mreznog sloja) ona to radi preko tog linka.**
Granica izmedju racunara i fizickog linka naziva se `interfejs`.

Posto je zadatak rutera da primi datagram sa jednog linka i proslijedi ga na drugi link, ruter obavezno mora da bude povezan sa najmanje dva linka. Granica izmedju rutera i bilo kojeg od njegovih linkova takodjer se naziva `interfejs`. **U skladu s tim ruter ima vise interfejsa, po jedan za svaki link**.

Posto svi racunari i ruteri mogu da primaju i salju `IP datagrame`, `IP` protokola zahtijeva da svi interfejsi racunara i rutera imaju vlastitu `IP adresu`.

`IP adresa` je tehnicki pridruzena interfejsu a ne racunaru ili ruteru na kome se nalazi taj intefejs.

**Svaka IP adresa dugacka je 32 bita.**

IP adrese se obicno pisu u decimalnoj notaciji sa tackama u kojoj se svaki bajt adrese zapisuje u decimalnom obliku a od ostalih bajtova u adresi razdvaja se tackom.

Uzmimo za primjer IP adresu `193.32.216.9` Broj `193` je vrijednost prvih `osam bitova` u adresi.
Prevedeno u binarni oblik, IP adresa `193.32.216.9` bi izgledalo ovako:
```
193     . 32     . 216    . 9
11000001 00100000 11011000 00001001
```

Svaki interfejs na svim racunarima i na ruterima mora da ima `IP adresu` koja je globalno jedinstvena osim interfejsa ispred kojih se koristi `NAT - Network Address Translator` odnosno prevodjenje adresa. Adrese **ne mogu** da se biraju nasumicno. **Dio IP adrese interfejsa odredjuje podmreza sa kojom je povezan.** U IP zargonu, mreza koja povezuje tri racunara i interfejse rutera predstavlja **podmrezu (subnet).** Podmreza se u literaturi naziva IP mreza ili samo mreza.

**Subnet Mask - MASKA PODMREZE** (npr: `/24`) znaci da 24 krajnja lijeva bita 32-bitne vrijednosti IP adrese predstavljaju adresu podmreze. Strategija dodjeljivanja adresa na interentu poznata je kao besklasno rutiranje izmedju domena **Classless Internetdomain Routing - CIDR**(cider blok).

`a.b.c.d / X`  `(a.b.c.d/24)`
`X` oznacava broj bitova u prvom dijelu adrese koji oznacavaju **MREZNI DIO IP ADRESE**, preostali biti su namjenjeni hostovim odnosno uredjajima unutar te podmreze.

**Ruteri** izvan ove organizacije sa kojim komunicira ova mreza u obzir uzimaju samo adresu podmreze odnosno mrezni prefix adrese. Ovo doprinosi smanjenu velicine ruting tabela, gdje ostale mreze sa kojima ova mreza komunicira ne moraju da znaju za ostale adrese unutar mreze, cesto i vise manjih podmreza unutar nje. Mogucnost da se korisiti jedan prefiks za predstavljanje vise mreza cesto se naziva agregacija adresa. Preostali `biti` unutar `IP adrese` odnose se na `hostove` i na osnovu njih se pravi razlika izmedju uredjaja unutar iste podmreze gdje svi ti hostovi imaju isti `mrezni prefix` odnosno istu `adresu podmreze`.

`255.255.255.255` - IP Adresa za difuzno emitovanje. Kada racunar posalje `datagram` sa adresom odredista `255.255.255.255` ta se poruka isporucuje svim racunarima unutar iste podmreze.

**Prije nego je usvojena CIDR sema, mrezni dio IP adrese morao je da bude duzine 8 bitova, 16 bitova ili 24 bita u semi poznatoj kao puno klasno adresiranje (classful addresing) jer su podmreze sa adresama od 8 bitova, 16 bitova i 24 bita bila poznate kao mreze klase A, B i C.**

- **Class A:** `10.0.0.0` to `10.255.255.255`
- **Class B:** `172.16.0.0` to `172.31.255.255`
- **Class C:** `192.168.0.0` to `192.168.255.255`

**Sve adrese koje pripadaju ovim klasama su privatne adrese i smatraju se non routable adresama.**
Sve adrese koje **ne pripadaju** nijednoj od ovih klasa su **javne adrese** / `PUBLIC IP` address.

Podmreza kalse C - `/24` je na osnovu toga mogla da obuhvati samo `2^8 - 2 = 254` racunara sto je premalo.
Podklasa mreze B - `/16` koja obuhvata `65634` adrese bila je prevelika za vecinu organizacija, sto je vodilo do lose iskoristenosti adresnog prostora klase B.

IP v4 adrese su ogranicene na 4.294.967.296 adrese.

### Specijalne IP adrese
Postoji nekoliko IPv4 adresa koje se koriste za posebne svrhe. Ove adrese se ne mogu koristiti za komunikaciju izmedju uredjaja na internetu.
![Sepecijalne IP adrese](/devops-mentorship-program/03-march/week-4-070323/files/special-ipv4.png)


### IPv6
**IPv6 (Internet Protocol version 6)** adresiranje je nasljednik IPv4 protokola. IPv6 koristi 128-bitne adrese, sto je znatno vise od IPv4 protokola koji koristi 32-bitne adrese. To znaci da IPv6 moze podrzati znatno veci broj mreznih uredjaja i povezanih uredjaja u poredjenju s IPv4.

IPv6 adrese se sastoje od 8 blokova koji se odvajaju sa dvije tacke. Svaki blok je zapisan sa 4 heksadecimalne znamenke, sto znaci da svaki blok ima vrijednost izmedju `0` i `FFFF`. Na primjer, IPv6 adresa moze izgledati ovako: `2001:0db8:85a3:0000:0000:8a2e:0370:7334`.

Jedna od glavnih prednosti IPv6 protokola je veca sigurnost, jer IPv6 ukljucuje ugradjenu enkripciju i autentifikaciju koja stiti od napada na mreznu sigurnost. Takodjer, IPv6 adresiranje omogucuje jednostavnije upravljanje mrezom i vecu ucinkovitost u usporedbi s IPv4, jer se ne moraju koristiti razni trikovi poput NAT-a (Network Address Translation) kako bi se omogucilo povezivanje velikog broja uredjaja na internet.
IPv6 adrese su velicine 128 bita i mogu da obuhvate `340.282.366.920.938.463.463.374.607.431.768.211.456` adresa.

## Web aplikacije

Web aplikacija ima dva nezavisna udaljena programa odnosno procesa koji komuniciraju jedan sa drugim preko mreze.
- browser (klijent / klijentski program / client)
- web server (server / serverski program / server)

**U Klijent-Server arhitekturi** postoji uvijek dostupan racunar odnosno server, cije usluge trazi vise drugih racunara koji se nazivaju klijenti. Kada od racunara klijenta stigne zahtjev za uslugom, web server na racunaru servera obradjuje zahtjev i vraca klijentu odgovor. Klijenti ne komuniciraju neposredno jedan sa drugim. Takodjer bitno je napomenuti da server ima stalnu IP adresu (ili domensko ime preko kojeg je dostupan).

U okviru komunikacione sesije izmedju dva procesa, proces koji inicira komunikaciju, tj. prvi kontaktira proces na pocetku sesije oznacava se kao **klijent**. Proces koji ceka na poziv da bi uspostavio komunikaciju oznacava se kao **server**. Sve poruke poslate od jednog procesa do drugog moraju da prodju kroz mrezu izmedju njih. Procesi salju poruke u mrezu i primaju poruke iz mreze preko softverskog interfejsa koji se zove `socket`.

![Aplikativni proces, soketi i transportni protokol na kome se zasnivaju](/devops-mentorship-program/03-march/week-4-070323/files/app-proces-socketi.png)

Na internetu se racunar identifikuje svojom IP adresom, pored toga sto zna adresu racunara kome je poruka namjenjena, predajni proces mora takodjer da identifikuje prijemni proces (tacnije prijemni soket) koji se izvrsava na tom racunaru za sta se koristi odredisni broj **porta**.

### TCP Protokol

**TCP (Transmission Control Protocol)** je jedan od osnovnih protokola na Internetu koji se koristi za uspostavljanje pouzdane veze izmedju uredjaja (npr. racunala, mobilnih uredjaja) putem mreze. TCP je odgovoran za sigurnu i pouzdanu isporuku podataka izmedju racunala preko mreze, a glavne znacajke TCP-a ukljucuju:

1. Pouzdana isporuka: TCP je dizajniran tako da osigura da se svi podaci isporucuju u pravom redoslijedu i da se svi podaci isporucuju bez gubitka ili dupliranja. Ovo je postignuto koristenjem razlicitih mehanizama poput potvrdjivanja (acknowledgments), ponovnog slanja (retransmission) i prozora (window) za kontrolu protoka.

2. Trosak: TCP odrzava razumnu kontrolu protoka kako bi se izbjegao zagusenje mreze i smanjio broj ponovnog slanja podataka.

3. Uspostava i prekidanje veze: TCP koristi trostepeni proces za uspostavu veze izmedju dvije strane, nakon cega se podaci mogu prenositi u oba smjera. Takodjer ima mehanizme za prekidanje veze kada je prijenos podataka zavrsen.

4. Pouzdanost: TCP provjerava integritet podataka kako bi se osiguralo da se preneseni podaci nisu promijenili ili ostetili tijekom prijenosa.

5. Poredak: TCP osigurava da se podaci isporucuju u pravom redoslijedu.

**TCP 3-Way Handshake Process** - Trostepeni proces za uspostavu veze izmedju dva racunala preko TCP-a sastoji se od:
- SYN
- SYN/ACK
- ACK
![TCP 3-Way Handshake Process](/devops-mentorship-program/03-march/week-4-070323/files/handshake-1.png)

![TCP 3-Way Handshake Process - 2](/devops-mentorship-program/03-march/week-4-070323/files/TCP-connection-1.png)


**Korak 1:** Sinhronizacija (SYN) - U ovom koraku klijent salje SYN paket posluzitelju kako bi inicirao uspostavu veze. SYN paket sadrzi vrijednost pocetnog broja (Initial Sequence Number - ISN), koja se koristi za identifikaciju pocetne pozicije podataka u sekvencijskom broju, i zatvaracki broj (window size), koji se koristi za kontrolu protoka podataka. Nakon sto posluzitelj primi SYN paket, on salje odgovor u koraku 2.

**Korak 2:** Potvrda (ACK i SYN) - U ovom koraku posluzitelj prima SYN paket od klijenta i salje ACK paket klijentu, potvrdjujuci da je primio SYN paket. ACK paket takodjer sadrzi vrijednost sekvencijskog broja koja je jednaka vrijednosti pocetnog broja klijenta povecana za jedan, sto oznacava sljedeci ocekivani broj koji ce klijent slati posluzitelju. Uz ACK paket, posluzitelj takodjer salje SYN paket klijentu kako bi inicirao uspostavu veze sa svoje strane. SYN paket od posluzitelja sadrzi vrijednosti pocetnog broja i zatvarackog broja koji su potrebni za uspostavu veze.

**Korak 3:** Potvrda (ACK) - U ovom koraku klijent prima SYN paket od posluzitelja i salje ACK paket posluzitelju kako bi potvrdio da je primio SYN paket. ACK paket od klijenta sadrzi vrijednost sekvencijskog broja koja je jednaka vrijednosti pocetnog broja posluzitelja povecana za jedan, sto oznacava sljedeci ocekivani broj koji ce posluzitelj slati klijentu.

Nakon sto su ti koraci izvrseni, veza izmedju klijenta i posluzitelja je uspostavljena i oba racunala su spremna za razmjenu podataka. Vazno je napomenuti da se isti trostepeni proces koristi za uspostavu veze u oba smjera, tako da je proces izvrsen u oba smjera prije nego sto zapocne razmjena podataka

#### HTTP Protokol
**HTTP (Hypertext Transfer Protocol)** je protokol kojim se komunicira izmedju klijenta i posluzitelja na webu. HTTP je protokol na aplikacijskom sloju, sto znaci da radi iznad protokola na nizim slojevima, poput TCP-a ili UDP-a, kako bi se omogucila komunikacija izmedju aplikacija. HTTP koristi TXP kao svoj osnovni transportni protokol. HTTP prvo uspostavlja TCP vezu izmedju klijenta i posluzitelja, a zatim se podaci prenose putem TCP veze.

HTTP koristi model zahtjev-odgovor (request-response model), gdje klijent salje zahtjev za odredjenim resursom na posluzitelju, a posluzitelj salje odgovor koji sadrzi trazene informacije. Zahtjev i odgovor sastoje se od headera i tijela.

Header sadrzi metodu zahtjeva, URL zahtjevanog resursa, verziju protokola, moguce opcije, kao i druge metapodatke. Tijelo sadrzi podatke koji se prenose putem HTTP-a, poput HTML koda, slike ili drugih vrsta datoteka.

HTTP metode zahtjeva ukljucuju GET, POST, PUT, DELETE, HEAD, OPTIONS i TRACE, a svaka metoda ima svoju specificnu svrhu. Primjerice, GET metoda se koristi za preuzimanje resursa sa servera, POST metoda se koristi za slanje podataka na server, PUT metoda se koristi za azuriranje postojeceg resursa, a DELETE metoda se koristi za brisanje resursa sa servera.

HTTP koristi cookie-je (kolacice) kako bi omogucio pohranjivanje informacija izmedju vise zahtjeva. Cookie-ji se koriste za pohranu korisnickih postavki, poput autentifikacijskih tokena, sto korisnicima omogucuje da se prijavljuju bez potrebe za svakim unosom podataka za prijavu.

Uz to, HTTP takodjer podrzava SSL (Secure Sockets Layer) i TLS (Transport Layer Security) protokole kako bi osigurao sigurnu komunikaciju putem interneta. Sigurna komunikacija se postize koristenjem kriptografije, sto stiti od prisluskivanja i manipulacije podacima.

Ukratko, HTTP je protokol kojim se omogucava komunikacija izmedju klijenta i posluzitelja na webu. HTTP koristi model zahtjev-odgovor, podrzava razlicite metode zahtjeva, koristi cookie-je za pohranu informacija izmedju zahtjeva i osigurava sigurnu komunikaciju putem interneta.

#### Cookies

Kada korisnik zapocne sesiju na eBay web stranici, server ce mu poslati kolacic koji ce sadrzavati jedinstveni identifikator sesije. Taj identifikator ce biti koristen za identifikaciju korisnika u buducim zahtjevima koje korisnik salje na server. Takodjer, kolacici se koriste za pohranjivanje postavki i preferencija korisnika, kao sto su preferirani jezik ili valuta.

Kada korisnik zatrazi neku stranicu na eBay web stranici, kolacici ce se poslati na server zajedno sa zahtjevom. Na taj nacin, server ce biti u mogucnosti prepoznati korisnika i pruziti mu personalizirano iskustvo na web stranici.

Takodjer, kolacici se koriste za pracenje aktivnosti korisnika na web stranici. Na primjer, eBay moze koristiti kolacice za pracenje koje su proizvode korisnici pregledali ili stavili u kosaricu za kupnju. Ove informacije se mogu koristiti za personaliziranje ponuda ili marketinskih poruka koje ce korisnik vidjeti na web stranici.

![Cookies](/devops-mentorship-program/03-march/week-4-070323/files/cookies.png)

#### HTTP metode zahtjeva
**GET** - Ova metoda se koristi za preuzimanje resursa sa servera. Primjerice, ako zelite preuzeti odredjenu web stranicu, mozete koristiti GET metodu kako biste zatrazili stranicu s posluzitelja. Primjer zahtjeva koji koristi GET metodu:

```
GET /index.html HTTP/1.1
Host: devops-mentorship-program.com
```
**POST** - Ova metoda se koristi za slanje podataka na server. Primjerice, ako zelite poslati podatke s obrasca na server, mozete koristiti POST metodu kako biste poslali podatke na server. Primjer zahtjeva koji koristi POST metodu:
```
POST /login HTTP/1.1
Host: www.example.com
Content-Type: application/x-www-form-urlencoded

username=john&password=secret
```
Ovaj primjer salje podatke za prijavu (korisnicko ime i lozinku) na server putem URL-kodiranih parametara.

**PUT** - Ova metoda se koristi za azuriranje postojeceg resursa na serveru. Primjerice, ako zelite azurirati odredjeni dokument na serveru, mozete koristiti PUT metodu kako biste azurirali taj dokument. Primjer zahtjeva koji koristi PUT metodu:

```
PUT /document.doc HTTP/1.1
Host: www.example.com
Content-Type: application/msword

... sadrzaj dokumenta ...
```
Ovaj primjer azurira postojeci dokument.doc s novim sadrzajem.

**DELETE** - Ova metoda se koristi za brisanje resursa sa servera. Primjerice, ako zelite izbrisati odredjeni dokument sa servera, mozete koristiti DELETE metodu kako biste izbrisali taj dokument. Primjer zahtjeva koji koristi DELETE metodu:

```
DELETE /file.txt HTTP/1.1
Host: www.example.com
```
Ovaj primjer brise datoteku.txt s posluzitelja.

#### HTTP Poruke
HTTP poruka se sastoji od tri dijela: pocetne linije (engl. start line), zaglavlja (engl. headers) i tijela (engl. body). Format HTTP poruke je strogo definiran i slijedi odredjena pravila.
Pocetna linija (start line) se sastoji od tri dijela: HTTP metode, URI i verzije HTTP protokola. Primjer pocetne linije za GET zahtjev za web stranicu `https://example.com/page.html` u verziji HTTP/1.1 bi bio:
```
GET /page.html HTTP/1.1
```
Zaglavlje (headers) dolazi nakon pocetne linije i sadrzi razne informacije o zahtjevu ili odgovoru. Zaglavlje se sastoji od polja (engl. fields) koja su organizirana u obliku kljuc-vrijednost parova. Svako polje se navodi u zasebnom redu i sastoji se od naziva polja i vrijednosti, odvojenih dvotackom. Primjer zaglavlja bi bio:


```
Host: example.com
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:88.0) Gecko/20100101 Firefox/88.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
```
Tijelo (body) dolazi nakon zaglavlja i sadrzi podatke koji se salju u zahtjevu ili odgovoru. Tijelo je opcionalno i moze biti prazno. Primjer tijela bi bio:
```
username=john&password=secret
```
Ukoliko se radi o HTTP odgovoru, pocetna linija ce sadrzavati statusni kod koji govori o uspjesnosti obrade zahtjeva. Primjer pocetne linije za HTTP odgovor sa statusnim kodom 200 (OK) bi bio:

```
HTTP/1.1 200 OK
```
![HTTP Req Headers](/devops-mentorship-program/03-march/week-4-070323/files/http_req_headers.jpeg)
#### HTTP Response Status Codes

HTTP kodovi su 3-znamenkasti kodovi koje server salje u odgovoru na zahtjev klijenta kako bi oznacio status odgovora. Ovdje su neki osnovni HTTP kodovi i njihovo znacenje:

- `200 OK`: Server je uspjesno obradio zahtjev i vratio ocekivani sadrzaj.
- `201 Created`: Server je uspjesno stvorio novi resurs u skladu s zahtjevom klijenta.
- `204 No Content`: Server je uspjesno obradio zahtjev, ali nema sadrzaja koji bi se vratio kao odgovor.
- `301 Moved Permanently`: Resurs koji je trazen je trajno premjesten na drugu lokaciju. Klijent treba koristiti novu adresu za daljnje zahtjeve prema tom resursu.
- `400 Bad Request`: Server nije uspio razumjeti zahtjev klijenta zbog pogresne sintakse ili nepotpunih podataka.
- `401 Unauthorized`: Klijent nije uspio autentificirati se prema serveru, odnosno nije poslao ispravne informacije za prijavu (npr. korisnicko ime i lozinka).
- `403 Forbidden`: Server je razumio zahtjev, ali ne dopusta pristup resursu zahtjevanom klijentu.
- `404 Not Found`: Server nije uspio pronaci trazeni resurs.
- `500 Internal Server Error`: Server je dozivio interno neplanirano ponasanje, sto je dovelo do pogreske u obradi zahtjeva.
- `503 Service Unavailable`: Server trenutno nije dostupan i ne moze obraditi zahtjev klijenta.

Postoji mnogo drugih HTTP kodova koji se koriste za razlicite scenarije. Razumijevanje ovih kodova moze pomoci klijentu da shvati sto se dogodilo s njihovim zahtjevom i serveru kako bi mogao reagirati na odredjene zahtjeve i situacije.

#### HTTP Headers
HTTP zaglavlje (engl. headers) je dio HTTP poruke koji se sastoji od polja koja prenose dodatne informacije o zahtjevu ili odgovoru. Zaglavlje je formatirano kao niz linija, pri cemu svaka linija predstavlja jedno polje. Svaki redak zaglavlja sadrzi naziv polja, dvotocku i vrijednost polja, odvojene razmakom.

Primjer zaglavlja za zahtjev moze biti sljedeci:
```
GET /index.html HTTP/1.1
Host: www.example.com
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0
Accept-Language: en-US,en;q=0.5
```
Ovaj primjer zaglavlja za zahtjev sadrzi sljedece polja:

- Metoda (GET) i URI (/index.html) su dio pocetne linije HTTP poruke.
- Host: specifikacija servera s kojim korisnik komunicira.
- User-Agent: informacija o pregledniku ili softveru koji korisnik koristi za pristup resursu.
- Accept-Language: informacija o prihvatljivim jezicima koje korisnik preferira.

Primjer zaglavlja za odgovor moze biti sljedeci:
```
HTTP/1.1 200 OK
Date: Mon, 10 Jun 2019 13:57:30 GMT
Server: Apache/2.2.22 (Debian)
Last-Modified: Tue, 23 Apr 2019 09:23:22 GMT
ETag: "abcde-7890-5a8b7c6d"
Content-Length: 16384
Content-Type: text/html; charset=UTF-8
```
Ovaj primjer zaglavlja za odgovor sadrzi sljedeca polja:

- Statusni kod (200 OK) je dio pocetne linije HTTP poruke.
- Date: datum i vrijeme kada je odgovor poslan.
- Server: naziv servera koji je odgovorio na zahtjev.
- Last-Modified: datum kada je resurs posljednji puta modificiran.
- ETag: identifikator entiteta (engl. entity tag) koji predstavlja verziju resursa.
- Content-Length: velicina tijela HTTP odgovora u bajtovima.
- Content-Type: vrsta medija (engl. media type) koja se prenosi kao tijelo HTTP odgovora, ovdje je to tekstualni HTML dokument s kodnom stranicom UTF-8.

Uz ova polja, u zaglavlju HTTP poruke se mogu nalaziti i druga polja koja se koriste za razlicite svrhe, kao sto su autentifikacija, kolacici (engl. cookies), cache kontrola i druge.

### SSL/TLS
**SSL (Secure Sockets Layer)** je kriptografski protokol koji se koristi za sigurnu komunikaciju izmedju racunara preko interneta. SSL se sastoji od dva sloja:
- protokola za upravljanje sigurnoscu i
- protokola za prijenos podataka.

SSL omogucuje kripotovanje podataka koji se prenose preko interneta, sto znaci da su podaci zasticeni od neovlastenog pristupa i manipulacije. Osim toga, SSL omogucuje provjeru identiteta web servera s kojim se korisnik povezuje, kako bi se sprijecilo da se podaci salju na lazni ili zlonamjerni server.

SSL certifikati su digitalni certifikati koji se koriste za autentifikaciju web servera i kriptiranje podataka koji se prenose izmedju korisnika i servera. Certifikati se izdaju od strane pouzdanih tijela za izdavanje certifikata (engl. Certificate Authorities) koja provjeravaju identitet vlasnika certifikata i izdaju digitalni potpis koji se koristi za autentifikaciju.

Kada korisnik pristupi web stranici koja koristi SSL, preglednik i web server uspostavljaju sigurnu vezu koja se naziva SSL sesija. Tijekom ove sesije, podaci se kriptiraju i dekriptiraju kako bi se osigurala sigurnost podataka. Preglednik prikazuje simbol zakljucanog lokota u traci adrese kako bi korisnik znao da se koristi SSL i da su podaci sigurni.

SSL je prethodnik protokola TLS (Transport Layer Security), koji je danas siroko koristen protokol za sigurnu komunikaciju izmedju racunala preko interneta.

**TLS (Transport Layer Security)** je kriptografski protokol koji se koristi za sigurnu komunikaciju izmedju racunala preko interneta. TLS se koristi za zastitu razlicitih protokola koji se koriste za prijenos podataka, kao sto su HTTP, SMTP, FTP itd.

TLS radi u tri glavna koraka:

1. Dogovor o parametrima kriptiranja: Klijent i server se dogovaraju o parametrima kriptiranja, ukljucujuci vrstu kriptografskog algoritma i duljinu kljuca.

2. Razmjena kljuceva: Klijent i server razmjenjuju javne kljuceve kako bi se osigurala sigurna komunikacija. Klijent generira jedinstveni kljuc za tu sesiju i salje ga serveru koji ga zatim koristi za kriptiranje podataka.

3. Autentifikacija: TLS koristi certifikate kako bi se osigurala autenticnost servera s kojim se klijent povezuje. Server salje svoj certifikat klijentu kako bi se klijent uvjerio da se povezuje s pravim serverom.

TLS takodjer podrzava razne sigurnosne mehanizme kao sto su provjera integriteta podataka, provjera autenticnosti klijenta i zastita od ponovnog reproduciranja (engl. replay attack).
![TLS Handshake](/devops-mentorship-program/03-march/week-4-070323/files/tls-sequence-diagram.png)

TLS handshake process je proces kojim se klijent i server dogovaraju o parametrima sigurne veze prije nego sto zapocnu prijenos podataka. U ovom procesu, klijent i server razmjenjuju informacije o algoritmima kriptiranja i autentifikaciji, te se dogovaraju oko kljuceva koji ce se koristiti za kriptiranje podataka. TLS handshake proces se sastoji od sljedecih koraka:

1. `Client Hello:` Klijent zapocinje TLS handshake slanjem Client Hello poruke serveru. Ova poruka sadrzi informacije o verziji TLS protokola koja se koristi, popis kriptografskih algoritama koje klijent podrzava i nasumicno generirani broj koji se koristi za stvaranje kasnije dogovorenog kljuca.

2. `Server Hello:` Nakon sto server primi Client Hello, server salje Server Hello poruku klijentu. Ova poruka sadrzi informacije o TLS verziji koju ce koristiti, izboru kriptografskih algoritama i nasumicno generirani broj koji ce se koristiti za dogovor o kljucu.

3. `Certificate:` Server salje svoj certifikat klijentu, koji sadrzi javni kljuc koji ce klijent kasnije koristiti za enkripciju podataka koje salje serveru.

4. `Server Key Exchange:` Ako je odabran Diffie-Hellman (DH) kriptografski algoritam, server salje svoj DH javni kljuc klijentu. Klijent tada generira svoj DH javni kljuc i salje ga serveru.

5. `Client Key Exchange:` Klijent salje serveru javni kljuc koji se koristi za stvaranje zajednickog tajnog kljuca.

6. `Certificate Verify:` Klijent potvrdjuje valjanost certifikata servera, koristeci javni kljuc iz certifikata.

7. `Change Cipher Spec:` Klijent i server se dogovaraju o tome koji ce kriptografski algoritam koristiti i prelaze na taj algoritam.

8. `Finished:` Nakon sto su klijent i server dogovorili parametre sigurne veze, oba uredjaja salju Finished poruke jedan drugome. Ove poruke sluze za potvrdu da je TLS handshake proces uspjesno zavrsen i da su klijent i server sada spremni za sigurnu komunikaciju.

Nakon uspjesno zavrsenog TLS handshake procesa, klijent i server koriste zajednicki tajni kljuc za kriptiranje i dekriptiranje podataka koji se prenose izmedju njih. TLS handshake proces se ponavlja za svaku novu sesiju komunikacije izmedju klijenta i servera kako bi se osigurala sigurna i privatna komunikacija.

### HTTPS
**HTTPS (Hypertext Transfer Protocol Secure)** je sigurna verzija HTTP protokola koja se koristi za enkripciju podataka koji se razmjenjuju izmedju web preglednika i web servera. HTTPS koristi SSL/TLS protokol za zastitu privatnosti podataka korisnika, osiguravajuci da se informacije ne mogu neovlasteno citati, mijenjati ili krivotvoriti.

Kada korisnik pokusa pristupiti HTTPS stranici, web server salje SSL/TLS certifikat pregledniku. Preglednik tada provjerava valjanost certifikata, provjeravajuci identitet web stranice. Nakon sto preglednik potvrdi valjanost certifikata, on koristi javni kljuc koji se nalazi u certifikatu za stvaranje tajnog kljuca koji ce se koristiti za enkripciju podataka.

Sve informacije koje se razmjenjuju izmedju preglednika i servera se sifriraju prije slanja, tako da samo preglednik i web server mogu razumjeti sadrzaj podataka. Ovo ukljucuje informacije kao sto su korisnicka imena i lozinke, podaci o kreditnoj kartici i drugi osjetljivi podaci.


### Ostali TCP Protokoli
Postoje mnogi popularni TCP protokoli osim HTTP-a i HTTPS-a, neki od njih su:
- **FTP (File Transfer Protocol)** - Protokol za prijenos datoteka izmedju racunala. FTP koristi TCP/IP za prijenos podataka izmedju klijenta i servera.


- **SSH (Secure Shell)** - Kriptografski siguran protokol za daljinsku administraciju i prijenos datoteka izmedju racunala. SSH koristi TCP/IP za sigurnu komunikaciju izmedju klijenta i servera.

- **Telnet** - Protokol za udaljeni pristup racunalu putem mreze. Telnet koristi TCP/IP za prijenos podataka izmedju klijenta i servera.


- **POP3** (Post Office Protocol version 3) - Protokol za primanje e-poste preko interneta. POP3 koristi TCP/IP za prijenos poruka izmedju klijenta i servera.

- **IMAP** (Internet Message Access Protocol) - Protokol za primanje i upravljanje e-postom na udaljenom serveru. IMAP koristi TCP/IP za prijenos poruka izmedju klijenta i servera.


- **LDAP (Lightweight Directory Access Protocol)** - Protokol za pristup i upravljanje direktorijima na mrezi. LDAP koristi TCP/IP za prijenos upita i odgovora izmedju klijenta i servera.

## UDP (User Datagram Protocol) Protokoli
UDP (User Datagram Protocol) je protokol na nizem sloju transportnog protokola u OSI (Open Systems Interconnection) modelu. Za razliku od TCP-a, koji pruza pouzdani prijenos podataka, UDP ne pruza garancije o isporuci paketa. UDP paketi se salju putem mreze bez ikakve garancije o tome da li ce paket stici na odrediste, hoce li se zagubiti ili duplicirati.

UDP protokol je cesto koristen za aplikacije koje zahtijevaju brz prijenos podataka, ali ne zahtijevaju pouzdanost prijenosa, kao sto su video streaming, online igre i druge interaktivne aplikacije. Buduci da UDP ne ukljucuje mehanizme kao sto su potvrda primitka i ponovno slanje paketa, to ga cini manje pouzdanim, ali brzim i laksim za uporabu.

- **DNS (Domain Name System)** - UDP se cesto koristi za DNS jer DNS paketi obicno nisu veliki, a brzina odgovora je kljucna. DNS je odgovoran za mapiranje IP adresa na ljudski citljive imena domena.

- **DHCP (Dynamic Host Configuration Protocol)** - DHCP se koristi za automatsku konfiguraciju mreznih postavki, kao sto su IP adrese, podmrezne mreze i zadane mrezne brzine. UDP se koristi za prenosenje DHCP poruka izmedju klijenata i DHCP posluzitelja.

- **TFTP (Trivial File Transfer Protocol)** - UDP se cesto koristi za TFTP, protokol koji se koristi za jednostavan prijenos datoteka izmedju racunala u mrezi. TFTP je jednostavan i ogranicen protokol, ali se cesto koristi za firmware i konfiguracijske datoteke.

- **SNMP (Simple Network Management Protocol)** - SNMP se koristi za upravljanje mrezama i prikupljanje informacija o mrezama. UDP se cesto koristi za prenosenje SNMP poruka izmedju mreznih uredjaja.

- **RIP (Routing Information Protocol)** - UDP se koristi za RIP, protokol za razmjenu informacija o rutiranju izmedju mreznih uredjaja. RIP je jedan od najjednostavnijih protokola za razmjenu informacija o rutiranju, ali nije najucinkovitiji i nije najbolji izbor za velike mreze.

- **Syslog** - UDP se cesto koristi za syslog, protokol za prikupljanje i slanje log datoteka sa razlicitih uredjaja u mrezi, kao sto su posluzitelji, mrezni uredjaji i sigurnosni uredjaji. Syslog omogucuje centralizirano upravljanje logovima i olaksava dijagnostiku problema u mrezi.

- **NTP (Network Time Protocol)** - UDP se koristi za NTP, protokol za sinkronizaciju vremena izmedju racunala u mrezi. NTP omogucuje racunalima u mrezi da se sinkroniziraju sa zajednickim referentnim vremenom, sto je kljucno za razlicite aplikacije koje se oslanjaju na tocno vrijeme, kao sto su bankarske transakcije ili mjerenje vremena u proizvodnoj liniji.

### Common TCP and UDP Ports
![Common TCP and UDP Ports](/devops-mentorship-program/03-march/week-4-070323/files/common-udp-tcp-protocols.png)

## DNS (Domain Name System)
DNS (Domain Name System) je strogo hijerarhijski distribuiran sistem koji povezuje ljudski citljiva domenska imene s njihovim pripadajucim IP adresama. DNS je kljucan za funkcioniranje Interneta jer omogucuje korisnicima da pristupe web stranicama, e-posti i drugim uslugama na Internetu pomocu lako pamtljivih imena umjesto slozenih IP adresa.

DNS se sastoji od nekoliko komponenti:

- **DNS posluzitelji:** Ovo su racunala koja cuvaju bazu podataka DNS-a i odgovaraju na upite koje im salju drugi racunala na Internetu. DNS posluzitelji se mogu podijeliti u tri kategorije: korijenski, autoritativni i posrednicki.

- **DNS rekordi:** Ovo su zapisi u bazi podataka DNS-a koji povezuju domenska imena s njihovim IP adresama. Postoje razliciti vrste DNS zapisa, ukljucujuci A zapis, CNAME zapis, MX zapis i PTR zapis, koji se koriste za razlicite svrhe.

- **DNS klijenti:** Ovo su racunala ili uredjaji koji salju upite DNS posluziteljima kako bi saznali IP adresu za odredjeno domensko ime. DNS klijenti mogu biti ugradjeni u web preglednike, operativne sustave ili druge aplikacije koje se koriste za pristup Internetu.

- **DNS protokoli:** Ovo su standardi za komunikaciju izmedju DNS posluzitelja i DNS klijenata. Postoje razliciti protokoli, ukljucujuci DNS protokol i DNSSEC protokol koji se koriste za osiguravanje integriteta i autenticnosti podataka u bazi podataka DNS-a.\

- **DNS caching:** DNS posluzitelji i klijenti obicno pohranjuju informacije o DNS upitima kako bi se smanjio broj upita koji se salju DNS posluziteljima i ubrzalo vrijeme odgovora. To se naziva DNS caching. DNS zapisi imaju svoje vrijeme zivota (TTL - Time to Live) koje se koristi za upravljanje caching-om, sto znaci da se informacije u cache-u automatski brisu nakon isteka TTL-a.

- **DNS hijacking:** Ovo je tehnika u kojoj napadac preusmjerava DNS upite s legitimne DNS adrese na laznu adresu. To omogucava napadacu da preuzme kontrolu nad komunikacijom izmedju DNS klijenta i posluzitelja te moze preusmjeravati korisnike na zlonamjerne web stranice ili kradju identiteta.

## Fully Qualified Domain Name (FQDN)

Fully Qualified Domain Name (FQDN) je naziv koji se koristi za potpuno identificiranje jedinstvene lokacije na internetu, a sastoji se od nekoliko dijelova u hijerarhijskom poretku. FQDN ukljucuje sve dijelove naziva domene, ukljucujuci naziv subdomene, naziv drugog nivoa i naziv domene vrha (top-level domain, TLD).

Primjer FQDN-a za web stranicu moze biti: `www.example.com`.

**Domena** se odnosi na ime koje se koristi za identifikaciju odredjene web stranice ili servisa na internetu. Domena je sastavljena od `jedinstvenog naziva` i `top-level domene` (npr. `.com`, `.org`, `.net` itd.), a koristi se kako bi se olaksalo pronalazenje web stranice ili servisa na internetu.

**Domensko ime** je dio domene i odnosi se na ime koje se koristi za identifikaciju pojedinog web mjesta. Domensko ime se nalazi prije top-level domene i sastoji se od naziva web stranice i naziva domene koja se nalazi nakon toga. Na primjer, u domeni `google.com`, `google` je naziv web stranice, a `.com` je top-level domena.

Komponente domene ukljucuju:


![Domain](/devops-mentorship-program/03-march/week-4-070323/files/domain-description-image.jpg)

- **Top-level domena (TLD):** TLD je najvisa razina domene u hijerarhiji domena. To su najcesce tri slova (npr. .com, .org, .net, .gov itd.) koja se nalaze nakon naziva web stranice.
**Postoje dvije vrste TLD (Top Level Domain) domena:**
    - genericke
    - geografski bazirane

    **Genericke domene** su one koje se mogu koristiti za bilo koju web stranicu, dok su geografski bazirane domene rezervirane za web stranice koje se odnose na pojedine zemlje. Na primjer, `.com` je genericka domena, dok je `.ba` geografska domena.

- **Druga razina domene (SLD):** SLD je dio domene koji se nalazi ispred top-level domene i koji obicno predstavlja naziv web mjesta. Na primjer, u domeni `google.com`, `google` je SLD.

- **Subdomena:** Subdomena je dio domene koji se nalazi ispred drugog nivoa domene i obicno se koristi za organiziranje web stranica na istom web mjestu. Na primjer, u domeni `docs.google.com`, `docs` je subdomena koja pokazuje na Googleov servis za obradu teksta.

Domena i domensko ime koriste se kako bi se olaksalo pronalazenje i pristupanje web stranicama i servisima na internetu. Svaka domena mora biti jedinstvena i registrirana kod registrara domena, koji upravlja domenom i odgovoran je za dodjelu domena korisnicima.

### Domenski registri
Slicno kao i za IP adrese, postoje domenski registri, baze podataka o domenama i odgovarajucim IP adresama, po jedan za svaku TLD. Oni kao uslugu daju domenska imena za vlastitu TLD te omogucavaju ostatku svijeta pregled informacija o registracijama pojedinih domena. Domenski registri se inace nazivaju NIC (Network Information Centre).
### DNS rezolucija

Svaki se funkcionalni DNS sistem sastoji se od tri dijela:

1.  **DNS klijent (engl. resolver)**, program koji se izvrsava na klijentskom racunaru i koji formira odredjeni DNS zahtjev. Takav program ne mora biti poseban servis, on je na vecini UNIX operativnih sistema najcesce ugradjen u standardnoj biblioteci u formi sistemskih poziva koje
pozivaju razliciti korisnicki programi,
2.  **Rekurzivni (engl. recursive) DNS posluzitelj**, koji nakon dobivenih
upita za klijenta obavlja pretrazivanje kroz DNS stablo i vraca nazad odgovore klijentima
3. **Autoritativni (engl. authoritative) DNS posluzitelj**, koji odgovara na upite rekurzivnih posluzitelja te vraca ili zavrsni odgovor ili zbog delegiranja vraca referencu na neki drugi autoritativni DNS posluzitelj.

Sam proces primanja zahtjeva i njihove obrade te vracanja odgovora se naziva **DNS rezolucija (engl. name resolution)**. Pojednostavljeno, osnovna rezolucija je proces pretvaranja domenskog imena u IP adresu: prvo trazimo autoritativni DNS posluzitelj, a zatim mu saljemo upit za adresom, na koji on odgovara sa trazenom adresom. Buduci da je **DNS strogo distribuirana baza**, ona je raspodijeljena po mnogo razlicitih posluzitelja. No, ocigledno je da zbog raspodijeljenosti rezolucija obicno ne moze biti obavljena kroz samo jedan upit i odgovor, vec najcesce zahtijeva duzu komunikaciju i niz upita i odgovora. Najcesca je situacija da klijent salje zahtjeve lokalnom DNS posluzitelju (nadlezan za klijentsko racunalo, obicno dodijeljen od ISP-a ili ustanove u kojoj se nalazi klijentsko racunalo), koji predstavlja rekurzivni posluzitelj i obavlja upite te zatim vraca odgovor klijentu. Dakle, najveci i najkompliciraniji dio procedure predstavlja trazenje autoritativnog posluzitelja u slozenoj DNS hijerarhiji.
Sto se samih tipova DNS rezolucije tice, postoje dva osnovna tipa prolaska kroz DNS hijerarhiju da bi se otkrio tocan zapis. Oni se razlikuju po tome tko obavlja vecinu posla oko saznavanja podataka i njihove obrade, a prvenstveno se pojavljuju kad obrada odredjenog DNS upita zahtijeva nekoliko koraka (dakle, lokalni DNS posluzitelj nema sve informacije):

- **Iterativni** - kada klijent salje doticne upite, posluzitelj mora odgovoriti jednim od dva moguca odgovora:
    a) odgovorom na zahtjev ili
    b) imenom drugog DNS posluzitelja (vrsi se delegiranje) koji ima vise podataka o trazenom upitu. U ovakvom tipu upita najveci dio posla obavlja klijent iterirajuci akcije upit-odgovor i prolazeci kroz DNS hijerarhiju.

- **Rekurzivni** - kada klijent salje rekurzivni upit, posluzitelj preuzima posao pronalazenja informacija o trazenom upitu. Dakle, ono sto je u iterativnom obavljao klijent, kod rekurzivnih upita obavlja posluzitelj - obradjuje informacije i salje nove upite drugim posluziteljima sve dok ne pronadje trazeno. Dakle, klijent salje svega jedan zahtjev te dobiva ili tocnu informaciju koju je trazio ili poruku o gresci.

Ocigledno je rekurzivan nacin pretrazivanja vrlo povoljan za klijente, ali moze znatno opteretiti DNS posluzitelje (na stranu i potencijalni problem trovanja DNS posluzitelja o kojem ce kasnije biti rijeci), pa se takve forme upita obicno eksplicitno dozvoljavaju samo racunalima iz lokalne mreze, dakle racunalima kojima je doticni DNS posluzitelj nadlezan. I iskljucivo njima.
### DNS Hijerarhija

**DNS (Domain Name System) hijerarhija** je organizacija domena na internetu koja omogucuje rjesavanje domenskih imena u IP adrese. DNS hijerarhija ima nekoliko razina domena, pri cemu svaka razina odgovara jednoj komponenti u domenskom imenu.

Najvisa razina u DNS hijerarhiji su `root serveri`, koji se nalaze na vrhu hijerarhijske strukture i upucuju na druge DNS posluzitelje koji su zaduzeni za rjesavanje domenskih imena u nizim razinama. **Root serveri** su razmjesteni sirom svijeta i upucuju na posluzitelje za `Top-Level Domain (TLD)` - **najvisu razinu domena**.

Nakon `root servera` dolaze `TLD` posluzitelji, koji upucuju na posluzitelje za drugu razinu domena (SLD), poput `.com`, `.org`, `.net` itd. `TLD` posluzitelji upucuju na posluzitelje za `SLD` domene, koji se nalaze na nizoj razini u hijerarhiji.

Posluzitelji za `SLD` domene upucuju na posluzitelje za subdomene, ukoliko su definirane. Npr, posluzitelj za domenu `example.com` bi uputio na posluzitelje za subdomene, kao sto su `mail.example.com` ili `support.example.com`.

Kada korisnik upise domensko ime u pregledniku, preglednik prvo upucuje DNS upit lokalnom DNS posluzitelju. Ako lokalni DNS posluzitelj nema trazeno rjesenje, on salje upit TLD posluzitelju, a zatim se upit salje posluzitelju za SLD domenu i tako dalje, sve dok se ne pronadje IP adresa povezana s trazenim domenskim imenom.

DNS hijerarhija omogucuje brzo i ucinkovito rjesavanje domenskih imena u IP adrese, sto je kljucno za rad interneta.

![DNS Query](/devops-mentorship-program/03-march/week-4-070323/files/Typical-DNS-request-and-response-cycle.jpg)

Postoje cetiri glavna tipa DNS posluzitelja:

- **Root posluzitelji:** Ovo su najvisi DNS posluzitelji u hijerarhiji i oni upucuju na posluzitelje TLD domena.

- **TLD posluzitelji:** Ovi posluzitelji upucuju na posluzitelje za drugu razinu domena (SLD), poput .com, .org, .net itd.

- **Posluzitelji za SLD domene:** Ovi posluzitelji upucuju na posluzitelje za subdomene, ukoliko su definirane.

- **Autoritativni posluzitelji:** Ovi posluzitelji su izvorni izvori informacija o DNS zapisima za odredjenu domenu i oni vracaju IP adrese za trazena domenska imena.

Kada se pronadje IP adresa povezana s trazenim domenskim imenom, DNS posluzitelj vraca tu adresu lokalnom DNS posluzitelju, koji je sprema u cache. U buducnosti, kada se ponovno zatrazi isto domensko ime, lokalni DNS posluzitelj vraca spremljenu IP adresu iz cache-a, umjesto da ponovo trazi adresu od DNS posluzitelja.

![DNS Hierarchy](/devops-mentorship-program/03-march/week-4-070323/files/dns-hierarchy.png)



![DNS Hierarchy](/devops-mentorship-program/03-march/week-4-070323/files/dns-hierarchy-tree.png)

## VPN (Virtual Private Network)

**VPN (Virtual Private Network)** je sigurna mrezna veza koja povezuje udaljene korisnike i uredjaje preko interneta kao da su direktno povezani u istoj lokalnoj mrezi. VPN se koristi za sigurno spajanje na udaljene mreze, pristup internetskim stranicama i servisima koji su inace nedostupni izvan odredjene lokacije, za zastitu privatnosti i anonimnosti, te za zastitu protoka podataka od neovlastenog pristupa.

Kada korisnik uspostavi VPN vezu, svi podaci koji se prenose izmedju korisnika i VPN posluzitelja su sifrirani, sto znaci da se informacije koje se prenose izmedju korisnika i VPN posluzitelja ne mogu citati ili pristupiti bilo kome drugom. VPN takodjer moze pruziti dodatnu sigurnost koristenjem protokola za autentifikaciju i enkripciju, kao sto su IPsec (Internet Protocol Security), SSL/TLS (Secure Sockets Layer/Transport Layer Security) i drugi.

VPN se moze koristiti na razlicite nacine, ukljucujuci:

- Za pristup udaljenim mrezama: VPN se koristi za povezivanje korisnika s udaljenim mrezama, kao sto su poslovne mreze, koje inace ne bi bile dostupne izvan mreze.

- Za zastitu privatnosti: VPN se moze koristiti za zastitu privatnosti i anonimnosti na internetu, tako da se korisnikovo mjesto i aktivnosti ne mogu pratiti.

- Za zastitu od neovlastenog pristupa: VPN se moze koristiti za zastitu protoka podataka od neovlastenog pristupa, kao sto su hakiranje ili prisluskivanje.

- Za pristup geografski ogranicenim uslugama: VPN se moze koristiti za pristup internetskim stranicama i servisima koji su inace dostupni samo u odredjenim geografskim podrucjima, kao sto su streaming servisi ili online trgovine.
## 📖 Reading materials
- [Networking Fundamentals - Adrian Cantrill](https://www.youtube.com/playlist?list=PLTk5ZYSbd9Mi_ya5tVFD8NFfU1YZOyml1)
- [IPv4 Addressing](https://www.tutorialspoint.com/ipv4/ipv4_addressing.htm)
- [Subnetting - Classful Netmasks - part 1](https://youtu.be/jFN9TNSPVj4)
- [Subnetting - ANDing - part2](https://youtu.be/KS3oz_D9FF8)
- [Learning Subnetting Part 2 - The Subnet Mask, the Network Address and ANDing](https://youtu.be/uE5gdwr1mE0)
- [Subnetting Cisco CCNA -Part 2 The Magic Number](https://youtu.be/84-zNmomYzk)
- [Everything You Need to Know About DNS (Domain Name System)](https://youtu.be/27r4Bzuj5NQ)
- [DNS 101 Miniseries - #1 - What does DNS do ?](https://youtu.be/zEmUuNFBgN8)
- [DNS 101 Miniseries - #2 - Why DNS needs a complex architecture!](https://youtu.be/QTu7yDnR_58)
- [DNS 101 Miniseries #3 - How DNS actually works ... walking the tree](https://youtu.be/xf01fJQsagQ)
- [DNS 101 Miniseries - #4 - What happens when a domain is registered?](https://youtu.be/-WWQzOwbth4)
- [DNS 101 Miniseries - #5 - Why do we need DNSSEC](https://youtu.be/thAUzOnUvP4)
- [DNS 101 Miniseries - #6 - How DNSSEC Works within a Zone](https://youtu.be/4qlIim15xwM)
- [DNS 101 Miniseries - #7 - DNSSEC Chain of Trust](https://youtu.be/YCk2WI-Fbtk)
- [DNS 101 Miniseries - #8 - The DNSSEC Root Signing Ceremony - the most important meeting ever](https://youtu.be/1VqscYMG_Rs)
- [A Beginner's Guide to WebSockets](https://youtu.be/8ARodQ4Wlf4)
- [DNS Prirucnik](/devops-mentorship-program/03-march/week-4-070323/files/DNS-prirucnik-1_5.pdf)
- [What happens when you type a URL into your browser?](https://aws.amazon.com/blogs/mobile/what-happens-when-you-type-a-url-into-your-browser/)

## 📹 Session recordings
- [**WEEK-4-tier-1-group-1 video session recording**](https://youtu.be/4coqHeNVeps)
- [**WEEK-4-tier-1-group-2 video session recording**](https://youtu.be/UlkPnSWJlH8)


[:fast_forward: Class Notes](/devops-mentorship-program/03-march/week-4-070323/00-class-notes.md)
[:fast_forward: Additional Reading](/devops-mentorship-program/03-march/week-4-070323/02-additional-reading.md)
[:fast_forward: HOME - README.md](../../../README.md)
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)

