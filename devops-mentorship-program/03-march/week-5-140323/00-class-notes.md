# 📝 Class notes    
## 📅 Date: 14.03.2023.    

## Server  
Server je racunarski sistem ili program koji pruža usluge drugim racunarima ili programima u mrezi. Server moze biti svaki fizicki racunar ili virtuelna masina. Serverima se nazivaju i fizicki racunari koji se nalaze u on-premise data centrima a na kojima je pokrenut serverski program. 

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
Data centar je fizicki objekat koji sadrzi servere, mreznu opremu, klimatizaciju, UPS-e, generatora, kablovsku mrezu, itd. Data centri obicno imaju fizicke i digitalne sigurnosne mjere kako bi se zastitili podaci i oprema. Ovo može ukljucivati kamere za nadzor, sisteme za pristup i identifikaciju, kao i razne fizicke barijere.

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
U NGINX-u, direktive (eng. directives) su komande koje definišu kako Nginx obrađuje HTTP zahtjeve. Svaka direktiva se sastoji od naziva i vrijednosti koja se dodeljuje tom nazivu. Direktive se koriste u konfiguracijskim fajlovima da bi se definišala podesavanja za Nginx web server, kao što su server blokovi, lokacije i ostale opcije.

Primjer jedne direktive bi mogao biti:
```bash
listen 80;
```
U ovom primjeru, "listen" je naziv direktive, a "80" je vrijednost koja se dodeljuje toj direktivi. Ova direktiva definiše da Nginx sluša na portu 80 za dolazne HTTP zahtjeve.

**Konteksti (eng. contexts)** su oblasti u kojima se koriste direktive. Svaka direktiva se nalazi u nekom kontekstu, a kontekst definise koja se podešavanja primjenjuju na koji dio Nginx konfiguracije. Postoje tri vrste konteksta u Nginx-u:

- `Main Context` - Ovo je glavni kontekst koji obuhvata podešavanja koja se primjenjuju globalno na Nginx server.  

- `Server Context` - Ovaj kontekst obuhvata podešavanja koja se primjenjuju na pojedinačne server blokove u konfiguraciji.

- `Location Context` - Ovaj kontekst obuhvata podešavanja koja se primjenjuju na pojedinačne URL-ove na serveru.

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
U ovom primjeru, sve direktive koje se nalaze u bloku `server` primjenjuju se na taj pojedinačni server blok. Direktive koje se nalaze u bloku `location` primjenjuju se samo na URL-ove koji odgovaraju putanji /. U ovom slučaju, direktiva `try_files` definiše da Nginx treba da pokuša da pronađe datoteku koja odgovara URL-u koji je klijent poslao. Ako datoteka ne postoji, Nginx će pokušati da pronađe datoteku koja odgovara URL-u koji je klijent poslao, ali sa dodatnim znakom /. Ako ni ta datoteka ne postoji, Nginx će pokušati da pronađe datoteku koja se zove index.html.
**Razumijevanje konteksta i direktiva u Nginx-u je ključno za kreiranje ispravne konfiguracije i definisanje podešavanja za vaš web server.**

Da bi izmjene koje ste napravili u konfiguraciji Nginx-a bile vidljive, potrebno je ponovo pokrenuti Nginx. Ovo se može uraditi sa sledećom komandom:
```bash 
$ sudo systemctl restart nginx
```
Pored nginx.conf fajla, postoje i drugi konfiguracijski fajlovi koji se nalaze u direktorijumu `/etc/nginx/`. Ovi fajlovi se koriste za dodatna podešavanja i konfiguracije.

To su sljedeci fajlovi:
- `mime.types` - Ovaj fajl sadrži listu MIME tipova koji se koriste za određivanje tipa sadržaja koji se vraća klijentu sto omogućava klijentima da pravilno prikažu sadržaj web stranica. Npr. MIME tip za HTML fajl je `text/html`, a za JPEG sliku `image/jpeg`. Ako server šalje HTML fajl sa MIME tipom `text/html`, browser će ga pravilno interpretirati i prikazati HTML stranicu. Slično tome, ako server šalje sliku sa MIME tipom `image/jpeg`, browser će prikazati sliku na odgovarajući način.  
- `fastcgi.conf` - Ovaj fajl sadrži podešavanja za `FastCGI` procese. `FastCGI` procesi se koriste za obradu dinamičkog sadržaja na web sajtu. `FastCGI` je protokol koji omogućava web serveru da uspostavi vezu sa FastCGI procesom koji izvršava aplikaciju. FastCGI procesi se koriste za generisanje dinamickog sadržaja na web sajtu, kao sto su skripte za generisanje HTML stranica, **PHP skripte** i drugi programski jezici.
- `scgi_params` - **SCGI (Simple Common Gateway Interface)** je jednostavan protokol za komunikaciju između web servera i aplikacijskog servera koji se koristi za generisanje dinamičkog sadržaja na web sajtovima. SCGI je sličan FastCGI protokolu, ali je jednostavniji i manje fleksibilan. `SCGI` procesi se koriste za generisanje dinamickog sadržaja na web sajtu, kao sto su skripte za generisanje HTML stranica, **Python skripte** i drugi programski jezici.
- `uwsgi_params` - Ovaj fajl sadrži podešavanja za `uWSGI` procese. `uWSGI` je jedan od najpopularnijh **WSGI (Web Server Gateway Interface)** servera. `uWSGI` procesi se koriste za obradu dinamičkog sadržaja na web sajtu. `uWSGI` je protokol koji omogućava web serveru da uspostavi vezu sa `uWSGI` procesom koji izvršava aplikaciju. `uWSGI` procesi se koriste za generisanje dinamickog sadržaja na web sajtu, kao sto su skripte za generisanje HTML stranica, **Python skripte** i drugi programski jezici. Preporuka je da pogledate sljedecu stranicu [Why is WSGI necessary?](https://www.fullstackpython.com/wsgi-servers.html) kako bi razumjeli zašto je potreban WSGI protokol.
- `*-utf` - UTF metode omogucavaju enkodiranje znakova razlicitih jezika sto omogucava njihovo prikazivanje. 

### Aplikacijski server
Da bi smo u potpunosti razumijeli rad web servera neophodno je da razumijemo aplikacijski server. Osnovni zadatak aplikacijskog servera je da omoguci klijentima pristup onome sto cesto nazivamo **biznis logikom aplikacije** koja generise dinamicki sadrzaj. **Web Server** isporucuje staticki sadrzaj ukljucujuci HTML stranice, slike, video fajlove i druge tipove podataka ukljucene u web sajt. Aplikacijski server sa druge strane generise dinamicki sadrzaj koji se isporucuje klijentima. Aplikacijski server nikada ne moze biti zamjena za web servere, umjsto toga aplikacijski server i web server moraju da rade zajedno kako bi se klijent imao potpuno iskustvo prilikom korištenja web sajta. Bez aplikacijskog web servera, web aplikacije bi bile ograničene na statički sadržaj i ne bi bile u mogućnosti da se prilagode promjenama u zahtjevima korisnika.

### Reverse Proxy 
Uzmimo za primjer da imamo Node.js aplikaciju koju zelimo da pokrenemo na nasem serveru (hostu). Da bi je pokrenuli potreban nam je `Node.js` server koji omogucava pokretanje `Node.js` aplikacije. `Node.js` server omogucava obradu `HTTP` zahtjeva koji stižu od klijenata i generisanje odgovora na osnovu Node.js koda. `Node.js` server pruža podršku za različite HTTP metode, uključujući GET, POST, PUT, DELETE, i druge. Kako bi omogucili posluzivanje statickih fajlova, bolju skalabilnost, sigurnost, jednostavniju konfiguraciju neophodno je da ispred naseg Node.js servera postavimo web server (u ovom primjeru Nginx) koji ce da obradi `HTTP` zahtjeve i proslijedi ih `Node.js` serveru. Ovaj tip arhitekture se naziva **Reverse Proxy** arhitektura. U ovom slučaju, **NGINX** server je **Reverse Proxy** server koji obradjuje `HTTP` zahtjeve i proslijedjuje ih `Node.js` serveru. `Node.js` server je u ovom slučaju **Backend** server.

#### Kako da konfigurišemo Nginx da bude Reverse Proxy server?
Da bi konfigurisali NGINX da bude Reverse Proxy server neophodno je da podesimo jednostavnu Node.js aplikaciju koja ce da vrati `Hello World` poruku kada se pozove. Ova aplikacija ce biti naš **Backend** server. Nakon toga potrebno je da podesimo NGINX da bude **Reverse Proxy** server koji ce da obradjuje `HTTP` zahtjeve i proslijedjuje ih našem **Backend** serveru.

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
