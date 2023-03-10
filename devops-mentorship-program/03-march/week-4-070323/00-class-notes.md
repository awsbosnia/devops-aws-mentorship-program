# 📝 Class notes    
## 📅 Date: 07.03.2023.    

## Racunarske Mreze
**Racunarske mreze** su skup povezanih racunara i drugih uredjaja koji su medjusobno povezani radi razmjene informacija i resursa. Ove mreze omogucuju racunarima da komuniciraju i razmjenjuju podatke jedni s drugima putem zicanih ili bezicnih veza.  
Postoji nekoliko razlicitih vrsta racunarskih mreza, ukljucujuci lokalne mreze (LAN), mreze sireg podrucja (WAN), virtualne privatne mreže (VPN) itd.  

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

Na aplikativnom sloju nalaze se mrezne aplikacije i njihovi protokoli aplikativnog sloja. Apikativnom sloju pripadaju protokoli kao sto su `HTTP`, `SMTP`, `FTP`. Odredjene mrezene funkcionalnosti kao sto je npr: `DNS translacija` takodjer se obavljaju na aplikativnom sloju.

### Prezentacijski sloj

Uloga prezentacijskog sloja je da aplikacijama koje medjusobno komuniciraju, razmijenjuju podatke, obezbjedi usluge koje omogucavaju tumacenje znacenja ramzmijenjenih podataka. 

### Sloj sesije

Sloj sesije obezbjedjuje razgranicavanje i sinhoronizovanje razmjene podatka ukljucujuci i nacin za stvaranje tacke za oporavak u slucaju greske. Takodjer omogucava i seme za oporavak podataka.

### Transportni sloj

Transportni sloj prenosi poruke aplikativnog sloja izmedju krajnjih tacaka aplikacije. Protokoli transportnog sloja su `TCP` i `UDP`.
`TCP` protokol aplikacijama koje ga koriste nudi uslugu sa uspostavljanjem veze. Ova usluga podrazumjeva garantovanu isporuku poruka aplikativnog sloja do odredista i kontrolu toka Za razliku od `TCP` protokola, `UDP` protokol aplikacijama koje ga koriste nudi uslugu bez uspostavljanja veze sto ga cini manje pouzdanim jer nema kontrolu toka i kontrolu zagusenja.

### Mrezni sloj

Mrezni sloj odgovoran je za prenosenje paketa mreznog sloja poznatih kao `datagrami` od jednog racunara do drugog. **Datagrami** su osnovna jedinica za prenos podataka u mrežama koje koriste paketno prenošenje podataka. Datagrami su obicno sastavljeni od zaglavlja (header) i korisnih podataka (payload). Zaglavlje sadrzi informacije o adresama odredista i izvora, kao i druge kontrole informacija koje su potrebne za uspjesan prijenos podataka. Payload sadrzi stvarne podatke koji se prenose preko mreze. Protokol transportnog sloja u izvornom racunaru prosljedjuje paket (odnosno segment) transportnog sloja i odredisnu mreznu adresu. **Mrezni sloj je taj koji obezbjedjuje isporuku paketa do transportnog sloja na odredisnom racunaru.**

**IP protokol** pripada mreznom sloju. Mrezni sloj takodjer obuhvata protokole za rutiranje.

### Sloj veze

Mrezni sloj usmjerava datagrame kroz niz rutera izmedju izvora i odredista. Za prednosenje paketa od jednog covera (racunara ili rutera) do sljedeceg, mrezni sloj se oslanja na usluge sloja veze. Primjeri protokola sloja veze su `ethernet`, `wifi` kao i protokoli koji se koriste za kablovske pristupne mreze.

### Fizicki sloj

Zadatak fizickog sloja jeste prenosenje pojedinacnih bitova iz istog paketa prosljedjenog od strane sloja veze izmedju susjetnih cvorova. Protokoli na ovom sloju zavise od vrste linka ali i medija uz pomoc kojeg je taj link ostvaren (opticka vlakna, bakrene parice i sl.)

## Protokoli
Mrezni protokol slican je pravilu ponasanja medju ljudima. **Svime sto se desava na internetu a obuhvata komunikaciju dva udaljena samostalna dijela ili vise njih upravlja neki protokol.**

Protokol definise format poruke i redoslijed po kojem se ta poruka ramijenjuje izmedju najmenje dvije zasebne cjeline koje medjusobno komuniciraju, kao i postupke koji se preduzimaju poslije slanja i/ili prijema odredjenih poruka ili nekog durgog dogadjaja.

Internet kao i sve druge racunarske mreze koriste protokole, prilikom komunikacije za ispunjavanje razlicitih zadataka koriste se razliciti protokoli.

Svaki od protokola pripada jednom od slojeva **OSI modela**. Prtokol na nekom sloju moze da se sprovodi kako u softveru tako i u hardveru, ali i uporedo. Protokoli aplikativnog sloja (`HTTP`, `SMTP`) skoro uvijek se sprovode u softveru krajnjih sistema. Isti je slucaj i sa protokolima transportnog sloja. Posto su fizicki sloj i sloj veze podataka odgovorni za komunikaciju preko odredjenog linka, protokoli na njima obicno se sporovode u mreznoj kartici koja je povezana sa datim linkom. Protokoli na mreznom sloju obicno se istovremeno sprovode i u softveru i u hardveru. Svi protokoli zajedno nazivaju se skup protokola.

## IPv4 Adresiranje
Racunar obicno ima samo jedan link prema mrezi: **kada IP adresa u tom racunaru zeli da posalje datagram (paket mreznog sloja) ona to radi preko tog linka.**
Granica izmedju racunara i fizickog linka naziva se `interfejs`.

Posto je zadatak rutera da primi datagram sa jednog linka i proslijedi ga na drugi link, ruter obavezno mora da bude povezan sa najmanje dva linka. Granica izmedju rutera i bilo kojeg od njegovih linkova takodjer se naziva `interfejs`. **U skladu s tim ruter ima vise interfejsa, po jedan za svaki link**. 

Posto svi racunari i ruteri mogu da primaju i salju `IP datagrame`, `IP` zatijeva da svi interfejsi racunara i rutera imaju vlastitu `IP adresu`. 

`IP adresa` je tehnicki pridruzena interfejsu a ne racunaru ili ruteru na kome se nalazi taj intefejs.

**Svaka IP adresa dugacka je 32 bita.**

IP adrese se obicno pisu u decimalnoj notaciji sa tackama u kojoj se svaki bajt adrese zapisuje u decimalnom obliku a od ostalih bajtova u adresi razdvaja se tackom. 

Uzmimo za primjer IP adresu `193.32.216.9` Broj `193` je vrijednost prvih `osam bitova` u adresi.  
Prevedeno u binarni oblik, IP adresa `193.32.216.9` bi izgledalo ovako:
```
193     . 32     . 216    . 9
11000001 00100000 11011000 00001001
```

Svaki interfejs na svim racunarima i na ruterima mora da ima `IP adresu` koja je globalno jedinstvena osim interfejsa ispred kojih se koriti `NAT - Network Address Translator` odnosno prevodjenje adresa. Adrese **ne mogu** da se biraju nasumicno. **Dio IP adrese interfejsa odredjuje podmreza sa kojom je povezan.** U IP zargonu  mreza koja povezuje intefejse tri racunara i intefejs rutera predstavlja **podmrezu (subnet).** Podmreza se u literaturi naziva IP mreza ili samo mreza.

**Subnet Mask - MASKA PODMREZE** (npr: `/24`) znaci da 24 krajnja lijeva bita 32-bitne vrijednosti IP adrese predstavljaju adresu podmreze. Strategija dodjeljivanja adresa na interentu poznata je kao besklasno rutiranje izmedju domena **Classless Internetdomain Routing - CIDR**(cider blok).

`a.b.c.d / X`  `(a.b.c.d/24)`
`X` oznacava broj bitova u prvom dijelu adrese koji oznacavaju **MREZNI DIO IP ADRESE**, preostali biti su namjenjeni hostovim odnosno uredjajima unutar te podmreze.

**Ruteri** izvan ove organizacije sa kojim komunicira ova mreza u obzir uzimaju samo adresu podmreze odnosno mrezni prefix adrese. Ovo doprinosi smanjenu velicine ruting tabela, gdje ostatale mreze sa kojima ova mreza komunicira ne moraju da znaju za ostale adrese unutar mreze, cesto i vise manjih podmreza unutar nje. Mogucnost da se korisiti jedan prefiks za predstavljanje vise mreza cesto se naziva agregacija adresa. Preostali `biti` unutar `IP adrese` odnose se na `hostove` i na osnovu njih se pravi razlika izmedju uredjaja unutar iste podmreze gdje svi ti hostovi imaju isti `mrezni prefix` odnosno istu `adresu podmreze`.

`255.255.255.255` - IP Adresa za difuzno emitovanje. Kada racunar posalje `datagram` sa odresom odredista `255.255.255.255` ta se poruka isporucuje svim racunarima u istoj podmrezi. 

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
Postoji nekoliko IPv4 adresa koje se koriste za posebne svrhe. Ove adrese se ne mogu koristiti za komunikaciju između uređaja na internetu.  
![Sepecijalne IP adrese](/devops-mentorship-program/03-march/week-4-070323/files/special-ipv4.png)


### IPv6
**IPv6 (Internet Protocol version 6)** adresiranje je nasljednik IPv4 protokola. IPv6 koristi 128-bitne adrese, što je znatno više od IPv4 protokola koji koristi 32-bitne adrese. To znači da IPv6 može podržati znatno veći broj mrežnih uređaja i povezanih uređaja u usporedbi s IPv4.

IPv6 adrese se sastoje od 8 blokova koji se odvajaju dvotočkom. Svaki blok je zapisan kao 4 heksadecimalne znamenke, što znači da svaki blok ima vrijednost između `0` i `FFFF`. Na primjer, IPv6 adresa može izgledati ovako: `2001:0db8:85a3:0000:0000:8a2e:0370:7334`.

Jedna od glavnih prednosti IPv6 protokola je veća sigurnost, jer IPv6 uključuje ugrađenu enkripciju i autentifikaciju koja štiti od napada na mrežnu sigurnost. Također, IPv6 adresiranje omogućuje jednostavnije upravljanje mrežom i veću učinkovitost u usporedbi s IPv4, jer se ne moraju koristiti razni trikovi poput NAT-a (Network Address Translation) kako bi se omogućilo povezivanje velikog broja uređaja na internet.
IPv6 adrese su duzine 128 bita i mogu da obuhvate `340.282.366.920.938.463.463.374.607.431.768.211.456` adresa.

## Web aplikacije

Web aplikacija ima dva nezavisna udaljena programa odnosno procesa koji komuniciraju jedan sa drugim preko mreze.
- browser (klijent / klijentski program / client)
- web server (server / serverski program / server)

**U Klijent-Server arhitekturi** postoji uvijek dostupan racunar odnosno server, cije usluge trazi vise drugih racunara koji se nazivaju klijenti. Kada od racunara klijenta stigne zahtjev za uslugom, web server na racunaru servera obradjuje zahtjev i vraca klijentu odgovor. Klijenti ne komuniciraju neposredno jedan sa drugim. Takodjer bitno je napomenuti da server ima stalnu IP adresu (ili domensko ime preko kojeg je dostupan).

U okviru komunikacione sesije izmedju dva procesa, proces koji prekece komunikaciju, tj. prvi kontaktira proces na pocetku sesije oznacava se kao **klijent**. Proces koji ceka na poziv da bi uspostavio komunikaciju oznacava se kao **server**. Sve poruke poslate od jednog procesa do drugog moraju da prodju kroz mrezu izmedju njih. Procesi salju poruke u mrezu i primaju poruke iz mreze preko softverskog interfejsa koji se zove `socket`.

![Aplikativni proces, soketi i transportni protokol na kome se zasnivaju](/devops-mentorship-program/03-march/week-4-070323/files/app-proces-socketi.png)  

Na internetu se racunar identifikuje svojom IP adresom, pored toga sto zna adresu racunara kome je poruka namjenjena, predajni proces mora takodjer da identifikuje prijemni proces (tacnije prijemni soket) koji se izvrsava na tom racunaru za sta se koristi odredisni broj **porta**. 

### TCP Protokol

**TCP (Transmission Control Protocol)** je jedan od osnovnih protokola na Internetu koji se koristi za uspostavljanje pouzdane veze između uređaja (npr. računala, mobilnih uređaja) putem mreže. TCP je odgovoran za sigurnu i pouzdanu isporuku podataka između računala preko mreže, a glavne značajke TCP-a uključuju:

1. Pouzdana isporuka: TCP je dizajniran tako da osigura da se svi podaci isporučuju u pravom redoslijedu i da se svi podaci isporučuju bez gubitka ili dupliranja. Ovo je postignuto korištenjem različitih mehanizama poput potvrđivanja (acknowledgments), ponovnog slanja (retransmission) i prozora (window) za kontrolu protoka.

2. Trošak: TCP održava razumnu kontrolu protoka kako bi se izbjegao zagušenje mreže i smanjio broj ponovnog slanja podataka.

3. Uspostava i prekidanje veze: TCP koristi trostepeni proces za uspostavu veze između dvije strane, nakon čega se podaci mogu prenositi u oba smjera. Također ima mehanizme za prekidanje veze kada je prijenos podataka završen.

4. Pouzdanost: TCP provjerava integritet podataka kako bi se osiguralo da se preneseni podaci nisu promijenili ili oštetili tijekom prijenosa.

5. Poredak: TCP osigurava da se podaci isporučuju u pravom redoslijedu.

**TCP 3-Way Handshake Process** - Trostepeni proces za uspostavu veze između dva računala preko TCP-a sastoji se od:
- SYN 
- SYN/ACK
- ACK
![TCP 3-Way Handshake Process](/devops-mentorship-program/03-march/week-4-070323/files/handshake-1.png)

![TCP 3-Way Handshake Process - 2](/devops-mentorship-program/03-march/week-4-070323/files/TCP-connection-1.png)


**Korak 1:** Sinhronizacija (SYN) - U ovom koraku klijent šalje SYN paket poslužitelju kako bi inicirao uspostavu veze. SYN paket sadrži vrijednost početnog broja (Initial Sequence Number - ISN), koja se koristi za identifikaciju početne pozicije podataka u sekvencijskom broju, i zatvarački broj (window size), koji se koristi za kontrolu protoka podataka. Nakon što poslužitelj primi SYN paket, on šalje odgovor u koraku 2.

**Korak 2:** Potvrda (ACK i SYN) - U ovom koraku poslužitelj prima SYN paket od klijenta i šalje ACK paket klijentu, potvrđujući da je primio SYN paket. ACK paket također sadrži vrijednost sekvencijskog broja koja je jednaka vrijednosti početnog broja klijenta povećana za jedan, što označava sljedeći očekivani broj koji će klijent slati poslužitelju. Uz ACK paket, poslužitelj također šalje SYN paket klijentu kako bi inicirao uspostavu veze sa svoje strane. SYN paket od poslužitelja sadrži vrijednosti početnog broja i zatvaračkog broja koji su potrebni za uspostavu veze.

**Korak 3:** Potvrda (ACK) - U ovom koraku klijent prima SYN paket od poslužitelja i šalje ACK paket poslužitelju kako bi potvrdio da je primio SYN paket. ACK paket od klijenta sadrži vrijednost sekvencijskog broja koja je jednaka vrijednosti početnog broja poslužitelja povećana za jedan, što označava sljedeći očekivani broj koji će poslužitelj slati klijentu.

Nakon što su ti koraci izvršeni, veza između klijenta i poslužitelja je uspostavljena i oba računala su spremna za razmjenu podataka. Važno je napomenuti da se isti trostepeni proces koristi za uspostavu veze u oba smjera, tako da je proces izvršen u oba smjera prije nego što započne razmjena podataka

#### HTTP Protokol
**HTTP (Hypertext Transfer Protocol)** je protokol kojim se komunicira između klijenta i poslužitelja na webu. HTTP je protokol na aplikacijskom sloju, što znači da radi iznad protokola na nižim slojevima, poput TCP-a ili UDP-a, kako bi se omogućila komunikacija između aplikacija. HTTP koristi TXP kao svoj osnovni transportni protokol. HTTP prvo uspostavlja TCP vezu između klijenta i poslužitelja, a zatim se podaci prenose putem TCP veze.

HTTP koristi model zahtjev-odgovor (request-response model), gdje klijent šalje zahtjev za određenim resursom na poslužitelju, a poslužitelj šalje odgovor koji sadrži tražene informacije. Zahtjev i odgovor sastoje se od headera i tijela.

Header sadrži metodu zahtjeva, URL zahtjevanog resursa, verziju protokola, moguće opcije, kao i druge metapodatke. Tijelo sadrži podatke koji se prenose putem HTTP-a, poput HTML koda, slike ili drugih vrsta datoteka.

HTTP metode zahtjeva uključuju GET, POST, PUT, DELETE, HEAD, OPTIONS i TRACE, a svaka metoda ima svoju specifičnu svrhu. Primjerice, GET metoda se koristi za preuzimanje resursa sa servera, POST metoda se koristi za slanje podataka na server, PUT metoda se koristi za ažuriranje postojećeg resursa, a DELETE metoda se koristi za brisanje resursa sa servera.

HTTP koristi cookie-je (kolačiće) kako bi omogućio pohranjivanje informacija između više zahtjeva. Cookie-ji se koriste za pohranu korisničkih postavki, poput autentifikacijskih tokena, što korisnicima omogućuje da se prijavljuju bez potrebe za svakim unosom podataka za prijavu.

Uz to, HTTP također podržava SSL (Secure Sockets Layer) i TLS (Transport Layer Security) protokole kako bi osigurao sigurnu komunikaciju putem interneta. Sigurna komunikacija se postiže korištenjem kriptografije, što štiti od prisluškivanja i manipulacije podacima.

Ukratko, HTTP je protokol kojim se omogućava komunikacija između klijenta i poslužitelja na webu. HTTP koristi model zahtjev-odgovor, podržava različite metode zahtjeva, koristi cookie-je za pohranu informacija između zahtjeva i osigurava sigurnu komunikaciju putem interneta.

#### Cookies 

Kada korisnik započne sesiju na eBay web stranici, server će mu poslati kolačić koji će sadržavati jedinstveni identifikator sesije. Taj identifikator će biti korišten za identifikaciju korisnika u budućim zahtjevima koje korisnik šalje na server. Također, kolačići se koriste za pohranjivanje postavki i preferencija korisnika, kao što su preferirani jezik ili valuta.

Kada korisnik zatraži neku stranicu na eBay web stranici, kolačići će se poslati na server zajedno sa zahtjevom. Na taj način, server će biti u mogućnosti prepoznati korisnika i pružiti mu personalizirano iskustvo na web stranici.

Također, kolačići se koriste za praćenje aktivnosti korisnika na web stranici. Na primjer, eBay može koristiti kolačiće za praćenje koje su proizvode korisnici pregledali ili stavili u košaricu za kupnju. Ove informacije se mogu koristiti za personaliziranje ponuda ili marketinških poruka koje će korisnik vidjeti na web stranici.  

![Cookies](/devops-mentorship-program/03-march/week-4-070323/files/cookies.png)

#### HTTP metode zahtjeva
**GET** - Ova metoda se koristi za preuzimanje resursa sa servera. Primjerice, ako želite preuzeti određenu web stranicu, možete koristiti GET metodu kako biste zatražili stranicu s poslužitelja. Primjer zahtjeva koji koristi GET metodu:

```
GET /index.html HTTP/1.1
Host: devops-mentorship-program.com
```
**POST** - Ova metoda se koristi za slanje podataka na server. Primjerice, ako želite poslati podatke s obrasca na server, možete koristiti POST metodu kako biste poslali podatke na server. Primjer zahtjeva koji koristi POST metodu:
```
POST /login HTTP/1.1
Host: www.example.com
Content-Type: application/x-www-form-urlencoded

username=john&password=secret
```
Ovaj primjer šalje podatke za prijavu (korisničko ime i lozinku) na server putem URL-kodiranih parametara.

**PUT** - Ova metoda se koristi za ažuriranje postojećeg resursa na serveru. Primjerice, ako želite ažurirati određeni dokument na serveru, možete koristiti PUT metodu kako biste ažurirali taj dokument. Primjer zahtjeva koji koristi PUT metodu:

```
PUT /document.doc HTTP/1.1
Host: www.example.com
Content-Type: application/msword

... sadrzaj dokumenta ...
```
Ovaj primjer ažurira postojeći dokument.doc s novim sadržajem.

**DELETE** - Ova metoda se koristi za brisanje resursa sa servera. Primjerice, ako želite izbrisati određeni dokument sa servera, možete koristiti DELETE metodu kako biste izbrisali taj dokument. Primjer zahtjeva koji koristi DELETE metodu:

```
DELETE /file.txt HTTP/1.1
Host: www.example.com
```
Ovaj primjer briše datoteku.txt s poslužitelja.  

#### HTTP Poruke 
HTTP poruka se sastoji od tri dijela: početne linije (engl. start line), zaglavlja (engl. headers) i tijela (engl. body). Format HTTP poruke je strogo definiran i slijedi određena pravila. 
Početna linija (start line) se sastoji od tri dijela: HTTP metode, URI i verzije HTTP protokola. Primjer početne linije za GET zahtjev za web stranicu `https://example.com/page.html` u verziji HTTP/1.1 bi bio:
```
GET /page.html HTTP/1.1
```
Zaglavlje (headers) dolazi nakon početne linije i sadrži razne informacije o zahtjevu ili odgovoru. Zaglavlje se sastoji od polja (engl. fields) koja su organizirana u obliku ključ-vrijednost parova. Svako polje se navodi u zasebnom redu i sastoji se od naziva polja i vrijednosti, odvojenih dvotočkom. Primjer zaglavlja bi bio:


```
Host: example.com
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:88.0) Gecko/20100101 Firefox/88.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
```
Tijelo (body) dolazi nakon zaglavlja i sadrži podatke koji se šalju u zahtjevu ili odgovoru. Tijelo je opcionalno i može biti prazno. Primjer tijela bi bio:
```
username=john&password=secret
```
Ukoliko se radi o HTTP odgovoru, početna linija će sadržavati statusni kod koji govori o uspješnosti obrade zahtjeva. Primjer početne linije za HTTP odgovor sa statusnim kodom 200 (OK) bi bio:

```
HTTP/1.1 200 OK
```
#### HTTP Response Status Codes

HTTP kodovi su 3-znamenkasti kodovi koje server šalje u odgovoru na zahtjev klijenta kako bi označio status odgovora. Ovdje su neki osnovni HTTP kodovi i njihovo značenje:

- `200 OK`: Server je uspješno obradio zahtjev i vratio očekivani sadržaj.
- `201 Created`: Server je uspješno stvorio novi resurs u skladu s zahtjevom klijenta.
- `204 No Content`: Server je uspješno obradio zahtjev, ali nema sadržaja koji bi se vratio kao odgovor.
- `301 Moved Permanently`: Resurs koji je tražen je trajno premješten na drugu lokaciju. Klijent treba koristiti novu adresu za daljnje zahtjeve prema tom resursu.
- `400 Bad Request`: Server nije uspio razumjeti zahtjev klijenta zbog pogrešne sintakse ili nepotpunih podataka.
- `401 Unauthorized`: Klijent nije uspio autentificirati se prema serveru, odnosno nije poslao ispravne informacije za prijavu (npr. korisničko ime i lozinka).
- `403 Forbidden`: Server je razumio zahtjev, ali ne dopušta pristup resursu zahtjevanom klijentu.
- `404 Not Found`: Server nije uspio pronaći traženi resurs.
- `500 Internal Server Error`: Server je doživio interno neplanirano ponašanje, što je dovelo do pogreške u obradi zahtjeva.
- `503 Service Unavailable`: Server trenutno nije dostupan i ne može obraditi zahtjev klijenta.  

Postoji mnogo drugih HTTP kodova koji se koriste za različite scenarije. Razumijevanje ovih kodova može pomoći klijentu da shvati što se dogodilo s njihovim zahtjevom i serveru kako bi mogao reagirati na određene zahtjeve i situacije.  

#### HTTP Headers
HTTP zaglavlje (engl. headers) je dio HTTP poruke koji se sastoji od polja koja prenose dodatne informacije o zahtjevu ili odgovoru. Zaglavlje je formatirano kao niz linija, pri čemu svaka linija predstavlja jedno polje. Svaki redak zaglavlja sadrži naziv polja, dvotočku i vrijednost polja, odvojene razmakom.

Primjer zaglavlja za zahtjev može biti sljedeći:
```
GET /index.html HTTP/1.1
Host: www.example.com
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0
Accept-Language: en-US,en;q=0.5
```
Ovaj primjer zaglavlja za zahtjev sadrži sljedeće polja:

- Metoda (GET) i URI (/index.html) su dio početne linije HTTP poruke.
- Host: specifikacija servera s kojim korisnik komunicira.
- User-Agent: informacija o pregledniku ili softveru koji korisnik koristi za pristup resursu.
- Accept-Language: informacija o prihvatljivim jezicima koje korisnik preferira.  

Primjer zaglavlja za odgovor može biti sljedeći:
```
HTTP/1.1 200 OK
Date: Mon, 10 Jun 2019 13:57:30 GMT
Server: Apache/2.2.22 (Debian)
Last-Modified: Tue, 23 Apr 2019 09:23:22 GMT
ETag: "abcde-7890-5a8b7c6d"
Content-Length: 16384
Content-Type: text/html; charset=UTF-8
```
Ovaj primjer zaglavlja za odgovor sadrži sljedeća polja:

- Statusni kod (200 OK) je dio početne linije HTTP poruke.
- Date: datum i vrijeme kada je odgovor poslan.
- Server: naziv servera koji je odgovorio na zahtjev.
- Last-Modified: datum kada je resurs posljednji puta modificiran.
- ETag: identifikator entiteta (engl. entity tag) koji predstavlja verziju resursa.
- Content-Length: veličina tijela HTTP odgovora u bajtovima.
- Content-Type: vrsta medija (engl. media type) koja se prenosi kao tijelo HTTP odgovora, ovdje je to tekstualni HTML dokument s kodnom stranicom UTF-8.

Uz ova polja, u zaglavlju HTTP poruke se mogu nalaziti i druga polja koja se koriste za različite svrhe, kao što su autentifikacija, kolačići (engl. cookies), cache kontrola i druge.

### SSL/TLS
**SSL (Secure Sockets Layer)** je kriptografski protokol koji se koristi za sigurnu komunikaciju izmedju racunara preko interneta. SSL se sastoji od dva sloja: 
- protokola za upravljanje sigurnoscu i 
- protokola za prijenos podataka.  

SSL omogućuje kripotovanje podataka koji se prenose preko interneta, što znači da su podaci zasticeni od neovlastenog pristupa i manipulacije. Osim toga, SSL omogućuje provjeru identiteta web servera s kojim se korisnik povezuje, kako bi se spriječilo da se podaci šalju na lažni ili zlonamjerni server.

SSL certifikati su digitalni certifikati koji se koriste za autentifikaciju web servera i kriptiranje podataka koji se prenose između korisnika i servera. Certifikati se izdaju od strane pouzdanih tijela za izdavanje certifikata (engl. Certificate Authorities) koja provjeravaju identitet vlasnika certifikata i izdaju digitalni potpis koji se koristi za autentifikaciju.

Kada korisnik pristupi web stranici koja koristi SSL, preglednik i web server uspostavljaju sigurnu vezu koja se naziva SSL sesija. Tijekom ove sesije, podaci se kriptiraju i dekriptiraju kako bi se osigurala sigurnost podataka. Preglednik prikazuje simbol zaključanog lokota u traci adrese kako bi korisnik znao da se koristi SSL i da su podaci sigurni.

SSL je prethodnik protokola TLS (Transport Layer Security), koji je danas široko korišten protokol za sigurnu komunikaciju između računala preko interneta.

**TLS (Transport Layer Security)** je kriptografski protokol koji se koristi za sigurnu komunikaciju između računala preko interneta. TLS se koristi za zaštitu različitih protokola koji se koriste za prijenos podataka, kao što su HTTP, SMTP, FTP itd.

TLS radi u tri glavna koraka:

1. Dogovor o parametrima kriptiranja: Klijent i server se dogovaraju o parametrima kriptiranja, uključujući vrstu kriptografskog algoritma i duljinu ključa.

2. Razmjena ključeva: Klijent i server razmjenjuju javne ključeve kako bi se osigurala sigurna komunikacija. Klijent generira jedinstveni ključ za tu sesiju i šalje ga serveru koji ga zatim koristi za kriptiranje podataka.

3. Autentifikacija: TLS koristi certifikate kako bi se osigurala autentičnost servera s kojim se klijent povezuje. Server šalje svoj certifikat klijentu kako bi se klijent uvjerio da se povezuje s pravim serverom.

TLS također podržava razne sigurnosne mehanizme kao što su provjera integriteta podataka, provjera autentičnosti klijenta i zaštita od ponovnog reproduciranja (engl. replay attack).
![TLS Handshake](/devops-mentorship-program/03-march/week-4-070323/files/tls-sequence-diagram.png)  

TLS handshake process je proces kojim se klijent i server dogovaraju o parametrima sigurne veze prije nego što započnu prijenos podataka. U ovom procesu, klijent i server razmjenjuju informacije o algoritmima kriptiranja i autentifikaciji, te se dogovaraju oko ključeva koji će se koristiti za kriptiranje podataka. TLS handshake proces se sastoji od sljedećih koraka:

1. `Client Hello:` Klijent započinje TLS handshake slanjem Client Hello poruke serveru. Ova poruka sadrži informacije o verziji TLS protokola koja se koristi, popis kriptografskih algoritama koje klijent podržava i nasumično generirani broj koji se koristi za stvaranje kasnije dogovorenog ključa.

2. `Server Hello:` Nakon što server primi Client Hello, server šalje Server Hello poruku klijentu. Ova poruka sadrži informacije o TLS verziji koju će koristiti, izboru kriptografskih algoritama i nasumično generirani broj koji će se koristiti za dogovor o ključu.

3. `Certificate:` Server šalje svoj certifikat klijentu, koji sadrži javni ključ koji će klijent kasnije koristiti za enkripciju podataka koje šalje serveru.

4. `Server Key Exchange:` Ako je odabran Diffie-Hellman (DH) kriptografski algoritam, server šalje svoj DH javni ključ klijentu. Klijent tada generira svoj DH javni ključ i šalje ga serveru.

5. `Client Key Exchange:` Klijent šalje serveru javni ključ koji se koristi za stvaranje zajedničkog tajnog ključa.

6. `Certificate Verify:` Klijent potvrđuje valjanost certifikata servera, koristeći javni ključ iz certifikata.

7. `Change Cipher Spec:` Klijent i server se dogovaraju o tome koji će kriptografski algoritam koristiti i prelaze na taj algoritam.

8. `Finished:` Nakon što su klijent i server dogovorili parametre sigurne veze, oba uređaja šalju Finished poruke jedan drugome. Ove poruke služe za potvrdu da je TLS handshake proces uspješno završen i da su klijent i server sada spremni za sigurnu komunikaciju.

Nakon uspješno završenog TLS handshake procesa, klijent i server koriste zajednički tajni ključ za kriptiranje i dekriptiranje podataka koji se prenose između njih. TLS handshake proces se ponavlja za svaku novu sesiju komunikacije između klijenta i servera kako bi se osigurala sigurna i privatna komunikacija.

### HTTPS
**HTTPS (Hypertext Transfer Protocol Secure)** je sigurna verzija HTTP protokola koja se koristi za enkripciju podataka koji se razmjenjuju između web preglednika i web servera. HTTPS koristi SSL/TLS protokol za zaštitu privatnosti podataka korisnika, osiguravajući da se informacije ne mogu neovlašteno čitati, mijenjati ili krivotvoriti.

Kada korisnik pokuša pristupiti HTTPS stranici, web server šalje SSL/TLS certifikat pregledniku. Preglednik tada provjerava valjanost certifikata, provjeravajući identitet web stranice. Nakon što preglednik potvrdi valjanost certifikata, on koristi javni ključ koji se nalazi u certifikatu za stvaranje tajnog ključa koji će se koristiti za enkripciju podataka.

Sve informacije koje se razmjenjuju između preglednika i servera se šifriraju prije slanja, tako da samo preglednik i web server mogu razumjeti sadržaj podataka. Ovo uključuje informacije kao što su korisnička imena i lozinke, podaci o kreditnoj kartici i drugi osjetljivi podaci.


### Ostali TCP Protokoli
Postoje mnogi popularni TCP protokoli osim HTTP-a i HTTPS-a, neki od njih su:
- **FTP (File Transfer Protocol)** - Protokol za prijenos datoteka između računala. FTP koristi TCP/IP za prijenos podataka između klijenta i servera.


- **SSH (Secure Shell)** - Kriptografski siguran protokol za daljinsku administraciju i prijenos datoteka između računala. SSH koristi TCP/IP za sigurnu komunikaciju između klijenta i servera.

- **Telnet** - Protokol za udaljeni pristup računalu putem mreže. Telnet koristi TCP/IP za prijenos podataka između klijenta i servera.


- **POP3** (Post Office Protocol version 3) - Protokol za primanje e-pošte preko interneta. POP3 koristi TCP/IP za prijenos poruka između klijenta i servera.

- **IMAP** (Internet Message Access Protocol) - Protokol za primanje i upravljanje e-poštom na udaljenom serveru. IMAP koristi TCP/IP za prijenos poruka između klijenta i servera.


- **LDAP (Lightweight Directory Access Protocol)** - Protokol za pristup i upravljanje direktorijima na mreži. LDAP koristi TCP/IP za prijenos upita i odgovora između klijenta i servera.

## UDP (User Datagram Protocol) Protokoli
UDP (User Datagram Protocol) je protokol na nižem sloju transportnog protokola u OSI (Open Systems Interconnection) modelu. Za razliku od TCP-a, koji pruža pouzdani prijenos podataka, UDP ne pruža garancije o isporuci paketa. UDP paketi se šalju putem mreže bez ikakvog garancija o tome da li će paket stići na odredište, hoće li se zagubiti ili duplicirati.

UDP protokol je često korišten za aplikacije koje zahtijevaju brz prijenos podataka, ali ne zahtijevaju pouzdanost prijenosa, kao što su video streaming, online igre i druge interaktivne aplikacije. Budući da UDP ne uključuje mehanizme kao što su potvrda primitka i ponovno slanje paketa, to ga čini manje pouzdanim, ali bržim i lakšim za uporabu.  

- **DNS (Domain Name System)** - UDP se često koristi za DNS jer DNS paketi obično nisu veliki, a brzina odgovora je ključna. DNS je odgovoran za mapiranje IP adresa na ljudski čitljive imena domena.

- **DHCP (Dynamic Host Configuration Protocol)** - DHCP se koristi za automatsku konfiguraciju mrežnih postavki, kao što su IP adrese, podmrežne mreže i zadane mrežne brzine. UDP se koristi za prenošenje DHCP poruka između klijenata i DHCP poslužitelja.

- **TFTP (Trivial File Transfer Protocol)** - UDP se često koristi za TFTP, protokol koji se koristi za jednostavan prijenos datoteka između računala u mreži. TFTP je jednostavan i ograničen protokol, ali se često koristi za firmware i konfiguracijske datoteke.

- **SNMP (Simple Network Management Protocol)** - SNMP se koristi za upravljanje mrežama i prikupljanje informacija o mrežama. UDP se često koristi za prenošenje SNMP poruka između mrežnih uređaja.

- **RIP (Routing Information Protocol)** - UDP se koristi za RIP, protokol za razmjenu informacija o rutiranju između mrežnih uređaja. RIP je jedan od najjednostavnijih protokola za razmjenu informacija o rutiranju, ali nije najučinkovitiji i nije najbolji izbor za velike mreže.

- **Syslog** - UDP se često koristi za syslog, protokol za prikupljanje i slanje log datoteka sa različitih uređaja u mreži, kao što su poslužitelji, mrežni uređaji i sigurnosni uređaji. Syslog omogućuje centralizirano upravljanje logovima i olakšava dijagnostiku problema u mreži.

- **NTP (Network Time Protocol)** - UDP se koristi za NTP, protokol za sinkronizaciju vremena između računala u mreži. NTP omogućuje računalima u mreži da se sinkroniziraju sa zajedničkim referentnim vremenom, što je ključno za različite aplikacije koje se oslanjaju na točno vrijeme, kao što su bankarske transakcije ili mjerenje vremena u proizvodnoj liniji.

### Common TCP and UDP Ports
![Common TCP and UDP Ports](/devops-mentorship-program/03-march/week-4-070323/files/common-udp-tcp-protocols.png)

## DNS (Domain Name System)
DNS (Domain Name System) je strogo hijerarhijski distribuiran sistem koji povezuje ljudski čitljiva domenska imene s njihovim pripadajucim IP adresama. DNS je ključan za funkcioniranje Interneta jer omogućuje korisnicima da pristupe web stranicama, e-pošti i drugim uslugama na Internetu pomoću lako pamtljivih imena umjesto složenih IP adresa.

DNS se sastoji od nekoliko komponenti:

- **DNS poslužitelji:** Ovo su računala koja čuvaju bazu podataka DNS-a i odgovaraju na upite koje im šalju drugi računala na Internetu. DNS poslužitelji se mogu podijeliti u tri kategorije: korijenski, autoritativni i posrednički.

- **DNS rekordi:** Ovo su zapisi u bazi podataka DNS-a koji povezuju domenska imena s njihovim IP adresama. Postoje različiti vrste DNS zapisa, uključujući A zapis, CNAME zapis, MX zapis i PTR zapis, koji se koriste za različite svrhe.

- **DNS klijenti:** Ovo su računala ili uređaji koji šalju upite DNS poslužiteljima kako bi saznali IP adresu za određeno domensko ime. DNS klijenti mogu biti ugrađeni u web preglednike, operativne sustave ili druge aplikacije koje se koriste za pristup Internetu.

- **DNS protokoli:** Ovo su standardi za komunikaciju između DNS poslužitelja i DNS klijenata. Postoje različiti protokoli, uključujući DNS protokol i DNSSEC protokol koji se koriste za osiguravanje integriteta i autentičnosti podataka u bazi podataka DNS-a.\

- **DNS caching:** DNS poslužitelji i klijenti obično pohranjuju informacije o DNS upitima kako bi se smanjio broj upita koji se šalju DNS poslužiteljima i ubrzalo vrijeme odgovora. To se naziva DNS caching. DNS zapisi imaju svoje vrijeme života (TTL - Time to Live) koje se koristi za upravljanje caching-om, što znači da se informacije u cache-u automatski brišu nakon isteka TTL-a.

- **DNS hijacking:** Ovo je tehnika u kojoj napadač preusmjerava DNS upite s legitimne DNS adrese na lažnu adresu. To omogućava napadaču da preuzme kontrolu nad komunikacijom između DNS klijenta i poslužitelja te može preusmjeravati korisnike na zlonamjerne web stranice ili krađu identiteta.

## Fully Qualified Domain Name (FQDN)

Fully Qualified Domain Name (FQDN) je naziv koji se koristi za potpuno identificiranje jedinstvene lokacije na internetu, a sastoji se od nekoliko dijelova u hijerarhijskom poretku. FQDN uključuje sve dijelove naziva domene, uključujući naziv subdomene, naziv drugog nivoa i naziv domene vrha (top-level domain, TLD).  

Primjer FQDN-a za web stranicu može biti: `www.example.com`.

**Domena** se odnosi na ime koje se koristi za identifikaciju određene web stranice ili servisa na internetu. Domena je sastavljena od `jedinstvenog naziva` i `top-level domene` (npr. `.com`, `.org`, `.net` itd.), a koristi se kako bi se olakšalo pronalaženje web stranice ili servisa na internetu.

**Domensko ime** je dio domene i odnosi se na ime koje se koristi za identifikaciju pojedinog web mjesta. Domensko ime se nalazi prije top-level domene i sastoji se od naziva web stranice i naziva domene koja se nalazi nakon toga. Na primjer, u domeni `google.com`, `google` je naziv web stranice, a `.com` je top-level domena.
 
Komponente domene uključuju:


![Domain](/devops-mentorship-program/03-march/week-4-070323/files/domain-description-image.jpg)

- **Top-level domena (TLD):** TLD je najviša razina domene u hijerarhiji domena. To su najčešće tri slova (npr. .com, .org, .net, .gov itd.) koja se nalaze nakon naziva web stranice.
**Postoje dvije vrste TLD (Top Level Domain) domena:**
    - generičke 
    - geografski bazirane   

    **Generičke domene** su one koje se mogu koristiti za bilo koju web stranicu, dok su geografski bazirane domene rezervirane za web stranice koje se odnose na pojedine zemlje. Na primjer, `.com` je generička domena, dok je `.ba` geografska domena.

- **Druga razina domene (SLD):** SLD je dio domene koji se nalazi ispred top-level domene i koji obično predstavlja naziv web mjesta. Na primjer, u domeni `google.com`, `google` je SLD.

- **Subdomena:** Subdomena je dio domene koji se nalazi ispred drugog nivoa domene i obično se koristi za organiziranje web stranica na istom web mjestu. Na primjer, u domeni `docs.google.com`, `docs` je subdomena koja pokazuje na Googleov servis za obradu teksta.

Domena i domensko ime koriste se kako bi se olakšalo pronalaženje i pristupanje web stranicama i servisima na internetu. Svaka domena mora biti jedinstvena i registrirana kod registrara domena, koji upravlja domenom i odgovoran je za dodjelu domena korisnicima.

### Domenski registri
Slino kao i za IP adrese, postoje domenski registri, baze podataka o domenama i odgovarajućim IP adresama, po jedan za svaku TLD. Oni kao uslugu daju domenska imena za vlastitu TLD te omogućavaju ostatku svijeta pregled informacija o registracijama pojedinih domena. Domenski registri se inače nazivaju NIC (Network Information Centre).
### DNS rezolucija

Svaki se funkcionalni DNS sistem sastoji se od tri dijela:

1.  **DNS klijent (engl. resolver)**, program koji se izvršava na klijentskom racunaru i koji formira određeni DNS zahtjev. Takav program ne mora biti poseban servis, on je na većini UNIX operativnih sistema najčešće ugrađen u standardnoj biblioteci u formi sistemskih poziva koje
pozivaju različiti korisnički programi,
2.  **Rekurzivni (engl. recursive) DNS poslužitelj**, koji nakon dobivenih
upita za klijenta obavlja pretraživanje kroz DNS stablo i vraća nazad odgovore klijentima
3. **Autoritativni (engl. authoritative) DNS poslužitelj**, koji odgovara na upite rekurzivnih poslužitelja te vraća ili završni odgovor ili zbog delegiranja vraća referencu na neki drugi autoritativni DNS poslužitelj.

Sam proces primanja zahtjeva i njihove obrade te vraćanja odgovora se naziva **DNS rezolucija (engl. name resolution)**. Pojednostavljeno, osnovna rezolucija je proces pretvaranja domenskog imena u IP adresu: prvo tražimo autoritativni DNS poslužitelj, a zatim mu šaljemo upit za adresom, na koji on odgovara sa traženom adresom. Budući da je **DNS strogo distribuirana baza**, ona je raspodijeljena po mnogo različitih poslužitelja. No, očigledno je da zbog raspodijeljenosti rezolucija obično ne može biti obavljena kroz samo jedan upit i odgovor, već najčešće zahtijeva dužu komunikaciju i niz upita i odgovora. Najčešća je situacija da klijent šalje zahtjeve lokalnom DNS poslužitelju (nadležan za klijentsko računalo, obično dodijeljen od ISP-a ili ustanove u kojoj se nalazi klijentsko računalo), koji predstavlja rekurzivni poslužitelj i obavlja upite te zatim vraća odgovor klijentu. Dakle, najveći i najkompliciraniji dio procedure predstavlja traženje autoritativnog poslužitelja u složenoj DNS hijerarhiji.  
Što se samih tipova DNS rezolucije tiče, postoje dva osnovna tipa prolaska kroz DNS hijerarhiju da bi se otkrio točan zapis. Oni se razlikuju po tome tko obavlja većinu posla oko saznavanja podataka i njihove obrade, a prvenstveno se pojavljuju kad obrada određenog DNS upita zahtijeva nekoliko koraka (dakle, lokalni DNS poslužitelj nema sve informacije):  

- **Iterativni** - kada klijent šalje dotične upite, poslužitelj mora odgovoriti jednim od dva moguća odgovora: 
    a) odgovorom na zahtjev ili 
    b) imenom drugog DNS poslužitelja (vrši se delegiranje) koji ima više podataka o traženom upitu. U ovakvom tipu upita najveći dio posla obavlja klijent iterirajući akcije upit-odgovor i prolazeći kroz DNS hijerarhiju.    

- **Rekurzivni** - kada klijent šalje rekurzivni upit, poslužitelj preuzima posao pronalaženja informacija o traženom upitu. Dakle, ono što je u iterativnom obavljao klijent, kod rekurzivnih upita obavlja poslužitelj - obrađuje informacije i šalje nove upite drugim poslužiteljima sve dok ne pronađe traženo. Dakle, klijent šalje svega jedan zahtjev te dobiva ili točnu informaciju koju je tražio ili poruku o grešci.  

Očigledno je rekurzivan način pretraživanja vrlo povoljan za klijente, ali može znatno opteretiti DNS poslužitelje (na stranu i potencijalni problem trovanja DNS poslužitelja o kojem će kasnije biti riječi), pa se takve forme upita obično eksplicitno dozvoljavaju samo računalima iz lokalne mreže, dakle računalima kojima je dotični DNS poslužitelj nadležan. I isključivo njima.
### DNS Hijerarhija

**DNS (Domain Name System) hijerarhija** je organizacija domena na internetu koja omogućuje rješavanje domenskih imena u IP adrese. DNS hijerarhija ima nekoliko razina domena, pri čemu svaka razina odgovara jednoj komponenti u domenskom imenu.

Najviša razina u DNS hijerarhiji su `root serveri`, koji se nalaze na vrhu hijerarhijske strukture i upucuju na druge DNS poslužitelje koji su zaduženi za rješavanje domenskih imena u nižim razinama. **Root serveri** su razmješteni sirom svijeta i upucuju na posluzitelje za `Top-Level Domain (TLD)` - **najvišu razinu domena**.

Nakon `root servera` dolaze `TLD` poslužitelji, koji upućuju na poslužitelje za drugu razinu domena (SLD), poput `.com`, `.org`, `.net` itd. `TLD` poslužitelji upućuju na poslužitelje za `SLD` domene, koji se nalaze na nižoj razini u hijerarhiji.

Poslužitelji za `SLD` domene upućuju na poslužitelje za subdomene, ukoliko su definirane. Npr, poslužitelj za domenu `example.com` bi uputio na poslužitelje za subdomene, kao što su `mail.example.com` ili `support.example.com`.

Kada korisnik upiše domensko ime u pregledniku, preglednik prvo upućuje DNS upit lokalnom DNS poslužitelju. Ako lokalni DNS poslužitelj nema traženo rješenje, on šalje upit TLD poslužitelju, a zatim se upit šalje poslužitelju za SLD domenu i tako dalje, sve dok se ne pronađe IP adresa povezana s traženim domenskim imenom.

DNS hijerarhija omogućuje brzo i učinkovito rješavanje domenskih imena u IP adrese, što je ključno za rad interneta.

![DNS Query](/devops-mentorship-program/03-march/week-4-070323/files/Typical-DNS-request-and-response-cycle.jpg)

Postoje četiri glavna tipa DNS poslužitelja:

- **Root poslužitelji:** Ovo su najviši DNS poslužitelji u hijerarhiji i oni upućuju na poslužitelje TLD domena.

- **TLD poslužitelji:** Ovi poslužitelji upućuju na poslužitelje za drugu razinu domena (SLD), poput .com, .org, .net itd.

- **Poslužitelji za SLD domene:** Ovi poslužitelji upućuju na poslužitelje za subdomene, ukoliko su definirane.

- **Autoritativni poslužitelji:** Ovi poslužitelji su izvorni izvori informacija o DNS zapisima za određenu domenu i oni vraćaju IP adrese za tražena domenska imena.

Kada se pronađe IP adresa povezana s traženim domenskim imenom, DNS poslužitelj vraća tu adresu lokalnom DNS poslužitelju, koji je sprema u cache. U budućnosti, kada se ponovno zatraži isto domensko ime, lokalni DNS poslužitelj vraća spremljenu IP adresu iz cache-a, umjesto da ponovo traži adresu od DNS poslužitelja.  

![DNS Hierarchy](/devops-mentorship-program/03-march/week-4-070323/files/dns-hierarchy.png)



![DNS Hierarchy](/devops-mentorship-program/03-march/week-4-070323/files/dns-hierarchy-tree.png)

## VPN (Virtual Private Network)

**VPN (Virtual Private Network)** je sigurna mrežna veza koja povezuje udaljene korisnike i uređaje preko interneta kao da su direktno povezani u istoj lokalnoj mreži. VPN se koristi za sigurno spajanje na udaljene mreže, pristup internetskim stranicama i servisima koji su inače nedostupni izvan određene lokacije, za zaštitu privatnosti i anonimnosti, te za zaštitu protoka podataka od neovlaštenog pristupa.

Kada korisnik uspostavi VPN vezu, svi podaci koji se prenose između korisnika i VPN poslužitelja su šifrirani, što znači da se informacije koje se prenose između korisnika i VPN poslužitelja ne mogu čitati ili pristupiti bilo kome drugom. VPN također može pružiti dodatnu sigurnost korištenjem protokola za autentifikaciju i enkripciju, kao što su IPsec (Internet Protocol Security), SSL/TLS (Secure Sockets Layer/Transport Layer Security) i drugi.

VPN se može koristiti na različite načine, uključujući:

- Za pristup udaljenim mrežama: VPN se koristi za povezivanje korisnika s udaljenim mrežama, kao što su poslovne mreže, koje inače ne bi bile dostupne izvan mreže.

- Za zaštitu privatnosti: VPN se može koristiti za zaštitu privatnosti i anonimnosti na internetu, tako da se korisnikovo mjesto i aktivnosti ne mogu pratiti.

- Za zaštitu od neovlaštenog pristupa: VPN se može koristiti za zaštitu protoka podataka od neovlaštenog pristupa, kao što su hakiranje ili prisluškivanje.

- Za pristup geografski ograničenim uslugama: VPN se može koristiti za pristup internetskim stranicama i servisima koji su inače dostupni samo u određenim geografskim područjima, kao što su streaming servisi ili online trgovine.
## 📖 Reading materials 
- [Networking Fundamentals - Adrian Cantrill](https://www.youtube.com/playlist?list=PLTk5ZYSbd9Mi_ya5tVFD8NFfU1YZOyml1)
- [IPv4 Addressing](https://www.tutorialspoint.com/ipv4/ipv4_addressing.htm)
- [Subnetting - Classful Netmasks - part 1](https://youtu.be/jFN9TNSPVj4)
- [Subnetting - ANDing - part2](https://youtu.be/KS3oz_D9FF8)
- [Learning Subnetting Part 2 - The Subnet Mask, the Network Address and ANDing](https://youtu.be/uE5gdwr1mE0)
- [Subnetting Cisco CCNA -Part 2 The Magic Number](https://youtu.be/84-zNmomYzk)
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
[:fast_forward: HOME - README.md](https://github.com/allops-solutions/devops-aws-mentorship-program#devops-mentorship-program)  

