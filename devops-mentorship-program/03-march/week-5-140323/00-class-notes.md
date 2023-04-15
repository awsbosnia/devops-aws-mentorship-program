# 📝 Class notes
## 📅 Date: 14.03.2023.

## Server
Server je racunarski sistem ili program koji pruza usluge drugim racunarima ili programima u mrezi. Server moze biti svaki fizicki racunar ili virtuelna masina. Serverima se nazivaju i fizicki racunari koji se nalaze u on-premise data centrima a na kojima je pokrenut serverski program.

Neke od osnovnih karakteristika servera su:

- Pouzdanost
- Perfromanse
- Sigurnost
- Skalabilnost
- Visoka dostupnost
- Upravljivost
- Mrezna povezanost

Za razliku od racunara za licnu upotrebu, hardwer racunara koji se koriste kao serveri su obicno dizajnirani da budu pouzdani i da imaju visoku dostupnost. Serveri su obicno opremljeni sa dodatnim hardwerom za povecanje performansi i sigurnosti.

![HP Server](/devops-mentorship-program/03-march/week-5-140323/files/hp-server.jpeg)
![HP Server Komponente](/devops-mentorship-program/03-march/week-5-140323/files/hp-server-komponente.png)

## Data Centar
Data centar je fizicki objekat koji sadrzi servere, mreznu opremu, klimatizaciju, UPS-e, generatora, kablovsku mrezu, itd. Data centri obicno imaju fizicke i digitalne sigurnosne mjere kako bi se zastitili podaci i oprema. Ovo moze ukljucivati kamere za nadzor, sisteme za pristup i identifikaciju, kao i razne fizicke barijere.

[AWS Data Centers](https://aws.amazon.com/compliance/data-center/data-centers/)

## Server Operativni Sistem

Server OS ima nekoliko karakteristika koje ga razlikuju od Operatinih Sistema na racunarima za licnu upotrebu.
- Pouzdanost
- Skalabilnost (podrska za multi-core procesore, multi-threading, itd.)
- Perfromanse
- Upravljanje resursima
- Sigurnost
- Udaljeno upravljanje
### Web Serveri
**Web serveri** su racunarski programi / servisi koji su dizajnirani za obradjivanje HTTP zahtjeva i slanje HTTP odgovora korisnicima koji pregledavaju web stranice ili pristupaju web aplikacijama. Web serveri obicno se koriste za posluzivanje statickih web stranica, dinamickih web stranica i drugih vrsta web sadrzaja. Web serveri moze biti bilo koji racunar dostupan na Internetu, ali se obicno koriste posebni racunari odnosno serveri koje su optimizovani za brz i siguran rad. Trenutno najpopularniji web serveri su **Apache, Nginx i Microsoft IIS**.
![How Web Server Works](/devops-mentorship-program/03-march/week-5-140323/files/how-web-server-works.png)
### NGINX
**NGINX** (izgovara se "Engine X") je open-source web server i reverse proxy server. Zahvaljujuci svojoj jednostavnosti koristenja, brzini, skalabilnosti i sigurnosti postao je jedan od najpopularnijih web servera. NGINX ima i svoju placenu verziju koja se naziva **NGINX Plus**. Iako NGINX Plus ima odredjene funkcionalnosti koje nisu dostupne u open-source verziji, vecina korisnika i dalje nalazi dovoljnim open-source verziju NGINX-a.

#### Instalacija NGINX-a na CentOS 7
Koraci za instalaciju Nginx-a na CentOS 7:
```bash
$ sudo yum install epel-release -y # instalacija EPEL repozitorija
$ sudo yum install nginx -y # instalacija Nginx-a
$ sudo systemctl start nginx # pokretanje Nginx-a
```
Defaultni root direktoriji nalazi se na lokaciji: `/usr/share/nginx/html`. Ova putanja definisana je unutar `server` bloka defaultnog nginx konfiguracijskog fajla koji se nalazi na lokaciji `/etc/nginx/nginx.conf`.
**nginx.conf** predstavlja globalni konfiguracijski fajl. U njemu se definisu globalne promenljive, globalni `http` blok, globalni `server` blok, itd.

Koraci za automatsko pokretanje Nginx-a nakon restarta servera:
```bash
$ sudo systemctl enable nginx # da se Nginx automatski pokrece nakon restarta servera
$ systemctl is-enabled nginx # provjera da li je nginx podesen za automatsko startanje
```

#### NGINX Konfiguracijski fajlovi
Za svaki pojedinacni web sajt / web servis koji hostujete na serveru potrebno je kreirati zaseban `server` blok unutar konfiguracijskog fajla. Preporuka je da se ti konfiguracijski fajlovi nalaze u direktorijumu `/etc/nginx/conf.d/` i da imaju ekstenziju `.conf`. Za svaki sajt kreirate poseban konfiguracijski fajl.
U NGINX-u, direktive (eng. directives) su komande koje definisu kako Nginx obradjuje HTTP zahtjeve. Svaka direktiva se sastoji od naziva i vrijednosti koja se dodeljuje tom nazivu. Direktive se koriste u konfiguracijskim fajlovima da bi se definisala podesavanja za Nginx web server, kao sto su server blokovi, lokacije i ostale opcije.

Primjer jedne direktive bi mogao biti:
```bash
listen 80;
```
U ovom primjeru, "listen" je naziv direktive, a "80" je vrijednost koja se dodeljuje toj direktivi. Ova direktiva definise da Nginx slusa na portu 80 za dolazne HTTP zahtjeve.

**Konteksti (eng. contexts)** su oblasti u kojima se koriste direktive. Svaka direktiva se nalazi u nekom kontekstu, a kontekst definise koja se podesavanja primjenjuju na koji dio Nginx konfiguracije. Postoje tri vrste konteksta u Nginx-u:

- `Main Context` - Ovo je glavni kontekst koji obuhvata podesavanja koja se primjenjuju globalno na Nginx server.

- `Server Context` - Ovaj kontekst obuhvata podesavanja koja se primjenjuju na pojedinacne server blokove u konfiguraciji.

- `Location Context` - Ovaj kontekst obuhvata podesavanja koja se primjenjuju na pojedinacne URL-ove na serveru.

Primjer server konteksta bi mogao biti:
```bash
server {
    listen 80;
    server_name example.com;
    root /var/www/example.com;
    index index.html;
    location / {
        try_files $uri $uri/ /index.html;
    }
}
```
U ovom primjeru, sve direktive koje se nalaze u bloku `server` primjenjuju se na taj pojedinacni server blok. Direktive koje se nalaze u bloku `location` primjenjuju se samo na URL-ove koji odgovaraju putanji /. U ovom slucaju, direktiva `try_files` definise da Nginx treba da pokusa da pronadje datoteku koja odgovara URL-u koji je klijent poslao. Ako datoteka ne postoji, Nginx ce pokusati da pronadje datoteku koja odgovara URL-u koji je klijent poslao, ali sa dodatnim znakom /. Ako ni ta datoteka ne postoji, Nginx ce pokusati da pronadje datoteku koja se zove index.html.
**Razumijevanje konteksta i direktiva u Nginx-u je kljucno za kreiranje ispravne konfiguracije i definisanje podesavanja za vas web server.**

Da bi izmjene koje ste napravili u konfiguraciji Nginx-a bile vidljive, potrebno je ponovo pokrenuti Nginx. Ovo se moze uraditi sa sledecom komandom:
```bash
$ sudo systemctl restart nginx
```
Pored nginx.conf fajla, postoje i drugi konfiguracijski fajlovi koji se nalaze u direktorijumu `/etc/nginx/`. Ovi fajlovi se koriste za dodatna podesavanja i konfiguracije.

To su sljedeci fajlovi:
- `mime.types` - Ovaj fajl sadrzi listu MIME tipova koji se koriste za odredjivanje tipa sadrzaja koji se vraca klijentu sto omogucava klijentima da pravilno prikazu sadrzaj web stranica. Npr. MIME tip za HTML fajl je `text/html`, a za JPEG sliku `image/jpeg`. Ako server salje HTML fajl sa MIME tipom `text/html`, browser ce ga pravilno interpretirati i prikazati HTML stranicu. Slicno tome, ako server salje sliku sa MIME tipom `image/jpeg`, browser ce prikazati sliku na odgovarajuci nacin.
- `fastcgi.conf` - Ovaj fajl sadrzi podesavanja za `FastCGI` procese. `FastCGI` procesi se koriste za obradu dinamickog sadrzaja na web sajtu. `FastCGI` je protokol koji omogucava web serveru da uspostavi vezu sa FastCGI procesom koji izvrsava aplikaciju. FastCGI procesi se koriste za generisanje dinamickog sadrzaja na web sajtu, kao sto su skripte za generisanje HTML stranica, **PHP skripte** i drugi programski jezici.
- `scgi_params` - **SCGI (Simple Common Gateway Interface)** je jednostavan protokol za komunikaciju izmedju web servera i aplikacijskog servera koji se koristi za generisanje dinamickog sadrzaja na web sajtovima. SCGI je slican FastCGI protokolu, ali je jednostavniji i manje fleksibilan. `SCGI` procesi se koriste za generisanje dinamickog sadrzaja na web sajtu, kao sto su skripte za generisanje HTML stranica, **Python skripte** i drugi programski jezici.
- `uwsgi_params` - Ovaj fajl sadrzi podesavanja za `uWSGI` procese. `uWSGI` je jedan od najpopularnijh **WSGI (Web Server Gateway Interface)** servera. `uWSGI` procesi se koriste za obradu dinamickog sadrzaja na web sajtu. `uWSGI` je protokol koji omogucava web serveru da uspostavi vezu sa `uWSGI` procesom koji izvrsava aplikaciju. `uWSGI` procesi se koriste za generisanje dinamickog sadrzaja na web sajtu, kao sto su skripte za generisanje HTML stranica, **Python skripte** i drugi programski jezici. Preporuka je da pogledate sljedecu stranicu [Why is WSGI necessary?](https://www.fullstackpython.com/wsgi-servers.html) kako bi razumjeli zasto je potreban WSGI protokol.
- `*-utf` - UTF metode omogucavaju enkodiranje znakova razlicitih jezika sto omogucava njihovo prikazivanje.

**NAPOMENA:** Instalacija Nginx-a na ostalim Linux/Unix distribucijama moze da kreira dodatne direktorije poput `sites-available` i `sites-enabled`. Ovi direktoriji mogu da sadrze dodatne `.conf` fajlove koji se koriste za dodatna podesavanja i konfiguracije. Kod CentOS 7 OS-a konfiguracije za razlicite sajtove se nalaze unutar direktorijuma `/etc/nginx/conf.d/`.

### Aplikacijski server
Da bi smo u potpunosti razumijeli rad web servera neophodno je da razumijemo aplikacijski server. Osnovni zadatak aplikacijskog servera je da omoguci klijentima pristup onome sto cesto nazivamo **biznis logikom aplikacije** koja generise dinamicki sadrzaj. **Web Server** isporucuje staticki sadrzaj ukljucujuci HTML stranice, slike, video fajlove i druge tipove podataka ukljucene u web sajt. Aplikacijski server sa druge strane generise dinamicki sadrzaj koji se isporucuje klijentima. Aplikacijski server nikada ne moze biti zamjena za web servere, umjsto toga aplikacijski server i web server moraju da rade zajedno kako bi se klijent imao potpuno iskustvo prilikom koristenja web sajta. Bez aplikacijskog web servera, web aplikacije bi bile ogranicene na staticki sadrzaj i ne bi bile u mogucnosti da se prilagode promjenama u zahtjevima korisnika.

### Reverse Proxy
Uzmimo za primjer da imamo Node.js aplikaciju koju zelimo da pokrenemo na nasem serveru (hostu). Da bi je pokrenuli potreban nam je `Node.js` server koji omogucava pokretanje `Node.js` aplikacije. `Node.js` server omogucava obradu `HTTP` zahtjeva koji stizu od klijenata i generisanje odgovora na osnovu Node.js koda. `Node.js` server pruza podrsku za razlicite `HTTP` metode, ukljucujuci `GET`, `POST`, `PUT`, `DELETE`, i druge. Kako bi omogucili posluzivanje statickih fajlova, bolju skalabilnost, sigurnost, jednostavniju konfiguraciju neophodno je da ispred naseg Node.js servera postavimo web server (u ovom primjeru Nginx) koji ce da obradi `HTTP` zahtjeve i proslijedi ih `Node.js` serveru. Ovaj tip arhitekture se naziva **Reverse Proxy** arhitektura. U ovom slucaju, **NGINX** server je **Reverse Proxy** server koji obradjuje `HTTP` zahtjeve i proslijedjuje ih `Node.js` serveru. `Node.js` server je u ovom slucaju **Backend** server koji obradjuje `HTTP` zahtjeve i generise odgovore na osnovu Node.js koda koje proslijedjuje **NGINX** serveru koji ih isporucuje klijentima.

![Reverse Proxy](/devops-mentorship-program/03-march/week-5-140323/files/reverse-proxy-1.png)

#### Kako da konfigurisemo Nginx da bude Reverse Proxy server?
Da bi konfigurisali **NGINX** da bude **Reverse Proxy** server neophodno je da podesimo jednostavnu Node.js aplikaciju koja ce da vrati `Hello World` poruku kada se pozove. Ova aplikacija ce biti nas **Backend** server. Nakon toga potrebno je da podesimo NGINX da bude **Reverse Proxy** server koji ce da obradjuje `HTTP` zahtjeve i proslijedjuje ih nasem **Backend** serveru.

Postoji vise razloga zasto bi koristili reverse proxy, a neki od njih su:
- **Skalabilnost** - Reverse proxy serveri mogu da podrze veliki broj klijenata i da se lako skaliraju.
- **Sigurnost** - Reverse proxy moze da sprijeci napade na backend servere.
- **Jednostavnost** - Reverse proxy serveri mogu da se konfigurisu jednostavnije nego backend odnosno aplikacijski serveri.
- **Brzina** - Reverse proxy serveri mogu da se konfigurisu da podrze cache-ovanje i da omoguce brze isporuke sadrzaja klijentima.
- **Load Balancing** - Reverse proxy serveri mogu da podrze load balancing i da rasporede zahteve klijenata na razlicite backend odnosno aplikacijske servere.
- **SSL Termination** - Reverse proxy serveri mogu da podrze SSL terminaciju i da omoguce klijentima da komuniciraju sa backend serverima preko HTTPS protokola.


U tu svrhu cemo ispratiti tutorijal sa Digital Ocean stranice [How To Set Up a Node.js Application for Production on CentOS 7](https://www.digitalocean.com/community/tutorials/how-to-set-up-a-node-js-application-for-production-on-centos-7)
```bash
$ curl -L -o nodesource_setup.sh https://rpm.nodesource.com/setup_14.x # Download Node.js 14 setup script
$ sudo bash nodesource_setup.sh # Run Node.js setup script
$ sudo yum clean all # Clean yum cache
$ sudo yum makecache fast # Make yum cache
$ sudo yum install -y gcc-c++ make # Install build tools
$ sudo yum install nodejs # Install Node.js
$ node -v # Check Node.js version
```
Ukoliko dobijete sljedecu gresku:
```bash
## You don't appear to be running a supported version of Enterprise Linux. Please contact NodeSource at https://github.com/nodesource/distributions/issues if you think this is incorrect or would like your architecture to be considered for support. Include your 'distribution package' name: no package provides redhat-release
no package provides centos-release
no package provides cloudlinux-release
no package provides sl-release
```
Node JS mozete instalirati koristeci node version manager (nvm) alat na sljedeci nacin:
```bash
# Install node version manager (nvm) by typing the following at the command line.

$ curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.3/install.sh | bash

# Activate nvm by typing the following at the command line.
$ . ~/.nvm/nvm.sh

# Use nvm to install the version 14 of Node.js by typing the following at the command line.
$ nvm install 14

# Test that Node.js is installed and running correctly by typing the following at the command line.
$ node -e "console.log('Running Node.js ' + process.version)"
```
Svakako je preporuka da pogledate [node github repozitoriji](https://github.com/nodesource/distributions) za instrukcije za instalaciju na razlicitim operativnim sistemima.
#### Application setup
```bash
$ npm install -g pm2 # Install pm2 process manager
$ pm2 start server.js # Start Node.js application
```
Koraci za automatsko pokretanje pm2 nakon restarta servera:
```bash
$ pm2 startup # Da se generise startup skripta.
$ pm2 save # Da se sacuva trenutna lista procesa kojima upravlja pm2 i koji ce se automatski startati prilikom svakog restarta servera.
```

Nakon izvrsenja komande `pm2 startup` prikazuje se komanda koju trebamo pokrenuti kao root user ili sa sudo privilegijama. A ukoliko smo komandu `pm2 startup` izvrsili kao root korisnik onda se ta komanda izvrsava automatski i ne trebamo je ponovo izvrsavati. Primjer takve komande koja se generise je:

```shell
sudo env PATH=$PATH:/usr/bin /usr/lib/node_modules/pm2/bin/pm2 startup systemd -u your_user --hp /home/your_user
```
* Komanda kreira `systemd` servis koji ce pokrenuti `pm2` pri sistemskom boot-u i izvrsiti je kao navedeni korisnik
`your_user` username koji koristimo za pokretanje Node.js aplikacije
`-u your_user` opcija `-u` specificira korisnika s cijim će se username-om izvrsiti navedena komanda
`env PATH=$PATH:/usr/bin` postavljamo enviromental varijablu `PATH` da ukljucuje `/usr/bin` direktorij gdje se nalazi `node` binary executable file
* `node` komanda se koristi za izvršavanje fajla. Na primjer `$ node app.js`
`/usr/lib/node_modules/pm2/bin/pm2` je putanja do executable PM2 fajla
`--hp /home/your_user` specificira gdje se nalazi `home` direktorij korisnika, kako bi pm2 znao gdje se nalaze korisnikovi
konfiguracijski fajlovi i log fajlovi

Defaultno se **pm2** servis pokrece kao root korisnik, ali je preporuka iz sigurnosnih razloga da koristimo non-root korisnike sa ogranicenim permisijama i zato se navodi putanja do `home` direktorija.

Kao poruku da je komanda izvrsena uspjesno dobijemo nesto slicno "**Systemd process manager installed**" , sto znaci da je skripta instalirana i `pm2` ce se pokretati automatski pri sistemskom boot-u.

Za provjeru koristimo komande:
```shell
$ systemctl status pm2-root # Provjera da li je proces aktivan.
$ pm2 unstartup # Ako zelimo onemoguciti startup system.
```
#### NGINX setup
```bash
$ cd /etc/nginx/conf.d
$ vi node-app.conf
```
gdje je `node-app.conf`:

```nginx
server {
  listen 80;
  server_name 3.68.91.255;

  location / {
    proxy_pass http://127.0.0.1:3000;
    proxy_http_version 1.1;
    proxy_set_header Upgrade $http_upgrade;
    proxy_set_header Connection 'upgrade';
    proxy_set_header Host $host;
    proxy_cache_bypass $http_upgrade;
  }
}
```
`node-app.conf` - predstavlja konfiguracijski fajl gdje smo unutar `server` bloka definisali jedan virtuealni server koji ce da obradjuje `HTTP` zahtjeve koji stizu na port `80` i proslijedjuje ih na `Node.js` server koji slusa na portu `3000`

- `server` - pocetak definicije bloka konfiguracije za virtualni server.
- `listen 80` - Nginx ce slusati dolazne zahtjeve na portu 80.
- `server_name 3.68.91.255` - ovaj virtualni server ce odgovoriti na zahtjeve sa hostom 3.68.91.255
- `location /` - sva podudaranja putanja ce biti preusmjerena na proxy server.
- `proxy_pass http://127.0.0.1:3000` - svi dolazni zahtjevi ce biti proslijedjeni na adresu `127.0.0.1:3000` gdje se pokrece vasa aplikacija, to je ustvari `localhost` adresa pa ste mogli da koristite i `proxy_pass http://localhost:3000`
- `proxy_http_version 1.1` - verzija HTTP protokola koja ce se koristiti za komunikaciju izmedju Nginx i aplikacije.
- `proxy_set_header Upgrade $http_upgrade;` - podesava zaglavlje Upgrade koje omogucava koristenje WebSocket komunikacije.
- `proxy_set_header Connection 'upgrade';` - podesava zaglavlje Connection koje omogucava koristenje WebSocket komunikacije.
- `proxy_set_header Host $host;` - podesava zaglavlje Host na vrijednost primljenog zahtjeva.
- `proxy_cache_bypass $http_upgrade;` - onemogucuje koristenje kesa za WebSocket komunikaciju.
#### Troubleshooting

Pogledajte NGINX logove i greske:
```bash
$ cat /var/log/nginx/error.log
```
```
[crit] 19207#19207: *23 connect() to 127.0.0.1:3000 failed (13: Permission denied) while connecting to upstream,
client: 185.58.94.229, server: 3.68.91.255, request: "GET / HTTP/1.1", upstream: "http://127.0.0.1:3000/",
host: "3.68.91.255"
```
**Korisne komande za troubleshooting:**
```bash
$ curl -l http://localhost:3000 # Check if Node.js application is running

$ pm2 list # Check if Node.js application is running

$ ps aux | grep node # Check if Node.js application is running

$ sudo netstat -tulpn | grep :3000 # Check if Node.js application is running on port 3000

$ systemctl status nginx # Check if NGINX is running

$ sudo systemctl reload nginx # Reload NGINX configuration without stopping/restarting the NGINX service and without losing any active connections.

$ sudo systemctl stop/start/restart nginx # Stop NGINX service / Start NGINX service / Restart NGINX service

$ sudo nginx -t # Test NGINX configuration

$ sudo nginx -s reload # Reload NGINX configuration, this command is used to reload NGINX configuration after making changes to the configuration file without restarting the NGINX service.
$ sudo journalctl -u nginx # Shop NGINX logs and errors. journalctl is a command line tool for viewing and querying the systemd journal.

$ sudo systemctl reload nginx # Reload NGINX configuration without stopping/restarting the NGINX service.

$ semanage port --list # SELinux port list

$ semanage port --list | grep http_port_t # Check if port 80 is allowed
```

### Forward Proxy
Za razliku od reverse proxy-a koji sluzi za zastitu servera, **forward proxy** sluzi za zastitu klijenta.
Forward proxy je server koji se nalazi izmedju klijenta ili grupe klijenta i interneta. Kada klijenti naprave zahtijev odnosno request prema internetu, oni se prvo salju na forward proxy server, koji ih proslijedi na internet. Forward proxy ce presresti zahtijev i "razgovarati" ce sa web serverom u ime klijenta.

![Forward Proxy](/devops-mentorship-program/03-march/week-5-140323/files/forward_proxy.png)
Nekoliko je razloga zasto bi klijent zelio da koristi forward proxy server:
- **Zastita privatnosti** - klijent moze da sakrije svoju IP adresu i da koristi IP adresu forward proxy servera.
- **Zaobilazak zabrana** - klijent moze da koristi forward proxy server da bi zaobilazio zabrane koje su postavljene na strani servera.
- **Blokira pristup** - forward proxy server moze da zabrani klijentima pristup odredjenim web stranicama.

### Apache
**Apache HTTP Server** ili **Apache** ili **Apache HTTPD** je besplatni, open-source web server koji se koristi za hostovanje web stranica i web aplikacija.

####Instalacija Apache-a i osnovni konfiguracijski fajlovi
Instalacija na CentOS 7:
```bash
$ sudo yum install httpd
```
Lokacija na kojoj je instaliran je `/etc/httpd/`.

#### Konfiguracijski direktoriji i fajlovi

Folderi koje vidimo na ovoj putanji su:
- `conf` - folder koji sadrzi konfiguracijske fajlove za Apache server `httpd.conf` i `magic` fajlove.
  - `httpd.conf` - glavni konfiguracijski fajl za Apache server
  - `magic` - fajl koji sadrzi informacije o tipovima fajlova odnosno datoteka koje se prenose preko web-a. Apache koristi magic datoteku kako bi utvrdio vrstu datoteke koja se prenosi, a zatim pravilno poslao odgovarajući MIME tip u HTTP zaglavlje. MIME tip omogućuje klijentima da znaju kako da obrade datoteke koje primaju, što je posebno važno kada se radi o binarnim datotekama, poput izvršnih datoteka ili arhiva.
- `conf.d` - folder koji sadrzi konfiguracijske fajlove za Apache server kreirane od strane korisnika
- `modules` - Ovaj direktorij se koristi za pohranu modula koje Apache koristi. Ovdje se nalaze datoteke koje definiraju funkcionalnost Apache modula. To su prekompilirane datoteke s ekstenzijom `.so` za Unix sisteme ili `.dll` za Windows.
- `conf.modules.d` -  direktorij se koristi za pohranu konfiguracijskih datoteka za Apache module. Ove datoteke opisuju kako se moduli učitavaju i konfigurisu. Sadrži datoteke s nazivom u obliku `XX-naziv_modula.conf`, gdje `XX` oznacava redoslijed u kojem se moduli učitavaju. Datoteke u ovom direktoriju obično sadrže `LoadModule` direktive koje specificiraju putanju do modula i naziv modula.


#### Virtual Hosts / Virtuelni hostovi
Zahvaljujuci virtuelnim hostovi, Apache server moze da hostuje vise web stranica na jednom serveru. Svaka web stranica ima svoj virtuelni host. Virtuelni hostovi se konfigurisu u `httpd.conf` fajlu. Ukoliko je potrebno da se konfigurise vise virtuelnih hostova, onda se konfiguracija virtuelnih hostova izdvoji u posebane fajlove koji se nalaze u `conf.d` direktoriju.

```conf
<VirtualHost *:80>
    ServerName www.prva-stranica.com
    DocumentRoot /var/www/prva-stranica.com
</VirtualHost>

<VirtualHost *:80>
    ServerName www.druga-stranica.com
    DocumentRoot /var/www/druga-stranica.com
</VirtualHost>
```


#### Reverse Proxy
```conf
<VirtualHost *:80>
    ServerName example.com
    ServerAlias www.example.com

    # Proxy settings for Node.js app
    ProxyPreserveHost On
    ProxyPass / http://localhost:3000/
    ProxyPassReverse / http://localhost:3000/
    ProxyRequests Off

</VirtualHost>
```
- `ProxyPreserveHost On` - ova opcija omogucava da se host header proslijedi na Node.js aplikaciju
- `ProxyPass / http://localhost:3000/` - ova opcija proslijedjuje sve zahtjeve koji dolaze na Apache server na Node.js aplikaciju
- `ProxyPassReverse / http://localhost:3000/` - ova opcija proslijedjuje sve odgovore koji dolaze od Node.js aplikacije na Apache server
- `ProxyRequests Off` - zabranjuje koristenje apache web servera kao forward proxy servera

### Apache Tomcat
**Apache Tomcat** ili samo **Tomcat** je open-source web server koji se koristi za posluzivanje web aplikacija koje su napisane u **Javi**. Tomcat se obicno koristi za posluzivanje dinamickih web stranica koje koriste **JSP (JavaServer Pages)** i servlet tehnologije.

**Tomcat** je razvijen na bazi **Apache web servera** i sadrzi mnoge od njegovih funkcionalnosti, ali se razlikuje po tome sto je specijaliziran za posluzivanje Java aplikacija. Tomcat takodjer ima svoje vlastite funkcionalnosti, ukljucujuci ugradjenu podrsku za **Java Servlet API**, **JavaServer Pages (JSP)**, **WebSockets** i **Java Server Faces (JSF)**.

## Ostale vrste servera

- **Serveri za baze podataka** - Koriste se za pohranu i upravljanje podacima u bazi podataka, neki od popularnih servera baza podataka (eng. database server) su:
    - MySQL
    - PostgreSQL
    - MariaDB
    - Microsoft SQL Server
    - Oracle Database
Vise o serverima baza podataka je receno tokom predavavanja u [**WEEK-9**](/devops-mentorship-program/04-april/week-9-110423/00-class-notes.md) snimak tog predavanja mozete pogledati na [**WEEK-9-tier-1-group-2 video session recording**](https://youtu.be/wsxwo_stIC0).

- **E-mail serveri** - Koriste se za slanje i primanje e-poste.
- **File serveri** - Koriste se za pohranu i dijeljenje fajlova.
- **FTP serveri** - Koriste se za slanje i primanje fajlova preko `FTP (File Transfer Protocol)` protokola.
- **DNS serveri** - Koriste se za razrjesavanje domenskih imena u IP adrese.

## 📹 Session recordings
- [**WEEK-5-tier-1-group-1 video session recording**](https://youtu.be/agT0spYqHP4)
- [**WEEK-5-tier-1-group-2 video session recording**](https://youtu.be/qhzWUF5mpWU)

## :infinity: DevOps Learning Path
- [:infinity: DevOps Learning Path - SSL](/devops-learning-path/ssl.md)

## 📖 Reading materials
- [DevOps Learning Path - Linux/UNIX OS](../../../devops-learning-path/web-servers.md)
- [Proxy vs Reverse Proxy (Real-world Examples)](https://youtu.be/4NB0NDtOwIQ)
- [nginx documentation](http://nginx.org/en/docs/)
- [How nginx processes a request](http://nginx.org/en/docs/http/request_processing.html)
- [nginx server names](http://nginx.org/en/docs/http/server_names.html)
- [Avoiding the Top 10 NGINX Configuration Mistakes](https://www.nginx.com/blog/avoiding-top-10-nginx-configuration-mistakes/)
- [Apache HTTP Server Project](https://httpd.apache.org/)

[:fast_forward: Class Notes](/devops-mentorship-program/03-march/week-5-140323/00-class-notes.md)
[:fast_forward: Additional Reading](/devops-mentorship-program/03-march/week-5-140323/02-additional-reading.md)
[:fast_forward: HOME - README.md](../../../README.md)
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)
