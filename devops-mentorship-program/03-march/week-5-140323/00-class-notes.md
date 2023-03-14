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

### NGINX

Koraci za instalaciju Nginx-a na CentOS 7:
```bash
$ sudo yum install epel-release -y # instalacija EPEL repozitorija
$ sudo yum install nginx -y # instalacija Nginx-a
$ sudo systemctl start nginx # pokretanje Nginx-a
```
Defaultni root direktoriji nalazi se na lokaciji: `/usr/share/nginx/html`. Ova putanja definisana je unutar `server` bloka defaultnog nginx konfiguracijskog fajla koji se nalazi na lokaciji `/etc/nginx/nginx.conf`.
**nginx.conf** predstavlja globalni konfiguracijski fajl. U njemu se definisu globalne promenljive, globalni `http` blok, globalni `server` blok, itd.

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

### Aplikacijski server
Da bi smo u potpunosti razumijeli rad web servera neophodno je da razumijemo aplikacijski server. Osnovni zadatak aplikacijskog servera je da omoguci klijentima pristup onome sto cesto nazivamo **biznis logikom aplikacije** koja generise dinamicki sadrzaj. **Web Server** isporucuje staticki sadrzaj ukljucujuci HTML stranice, slike, video fajlove i druge tipove podataka ukljucene u web sajt. Aplikacijski server sa druge strane generise dinamicki sadrzaj koji se isporucuje klijentima. Aplikacijski server nikada ne moze biti zamjena za web servere, umjsto toga aplikacijski server i web server moraju da rade zajedno kako bi se klijent imao potpuno iskustvo prilikom koristenja web sajta. Bez aplikacijskog web servera, web aplikacije bi bile ogranicene na staticki sadrzaj i ne bi bile u mogucnosti da se prilagode promjenama u zahtjevima korisnika.

### Reverse Proxy 
Uzmimo za primjer da imamo Node.js aplikaciju koju zelimo da pokrenemo na nasem serveru (hostu). Da bi je pokrenuli potreban nam je `Node.js` server koji omogucava pokretanje `Node.js` aplikacije. `Node.js` server omogucava obradu `HTTP` zahtjeva koji stizu od klijenata i generisanje odgovora na osnovu Node.js koda. `Node.js` server pruza podrsku za razlicite HTTP metode, ukljucujuci GET, POST, PUT, DELETE, i druge. Kako bi omogucili posluzivanje statickih fajlova, bolju skalabilnost, sigurnost, jednostavniju konfiguraciju neophodno je da ispred naseg Node.js servera postavimo web server (u ovom primjeru Nginx) koji ce da obradi `HTTP` zahtjeve i proslijedi ih `Node.js` serveru. Ovaj tip arhitekture se naziva **Reverse Proxy** arhitektura. U ovom slucaju, **NGINX** server je **Reverse Proxy** server koji obradjuje `HTTP` zahtjeve i proslijedjuje ih `Node.js` serveru. `Node.js` server je u ovom slucaju **Backend** server.

#### Kako da konfigurisemo Nginx da bude Reverse Proxy server?
Da bi konfigurisali NGINX da bude Reverse Proxy server neophodno je da podesimo jednostavnu Node.js aplikaciju koja ce da vrati `Hello World` poruku kada se pozove. Ova aplikacija ce biti nas **Backend** server. Nakon toga potrebno je da podesimo NGINX da bude **Reverse Proxy** server koji ce da obradjuje `HTTP` zahtjeve i proslijedjuje ih nasem **Backend** serveru.

U tu svrhu cemo ispratiti tutorijal sa Digital Ocean stranice [How To Set Up a Node.js Application for Production on CentOS 7](https://www.digitalocean.com/community/tutorials/how-to-set-up-a-node-js-application-for-production-on-centos-7)
```bash
$ curl -L -o nodesource_setup.sh https://rpm.nodesource.com/setup_14.x # Download Node.js setup script
$ sudo bash nodesource_setup.sh # Run Node.js setup script
$ sudo yum clean all # Clean yum cache
$ sudo yum makecache fast # Make yum cache
$ sudo yum install -y gcc-c++ make # Install build tools
$ sudo yum install nodejs # Install Node.js
$ node -v # Check Node.js version

## Application setup

$ npm install -g pm2 # Install pm2 process manager
$ pm2 start server.js # Start Node.js application
```

### Forward Proxy

### Apache

### SSL/TLS Certifikati


## Ostale vrste servera

### Serveri za baze podataka

### E-mail serveri

### File serveri

### DNS serveri


[nginx documentation](http://nginx.org/en/docs/)

[:fast_forward: Class Notes](/devops-mentorship-program/03-march/week-5-140323/00-class-notes.md)  
[:fast_forward: Additional Reading](/devops-mentorship-program/03-march/week-5-140323/02-additional-reading.md)   
[:fast_forward: HOME - README.md](https://github.com/allops-solutions/devops-aws-mentorship-program#devops-mentorship-program)  
