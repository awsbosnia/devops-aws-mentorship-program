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

**NAPOMENA:** Instalacija Nginx-a na ostalim Linux/Unix distribucijama moze da kreira dodatne direktorije poput `sites-available` i `sites-enabled`. Ovi direktoriji mogu da sadrze dodatne `.conf` fajlove koji se koriste za dodatna podešavanja i konfiguracije. Kod CentOS 7 OS-a konfiguracije za razlicite sajtove se nalaze unutar direktorijuma `/etc/nginx/conf.d/`.

### Aplikacijski server
Da bi smo u potpunosti razumijeli rad web servera neophodno je da razumijemo aplikacijski server. Osnovni zadatak aplikacijskog servera je da omoguci klijentima pristup onome sto cesto nazivamo **biznis logikom aplikacije** koja generise dinamicki sadrzaj. **Web Server** isporucuje staticki sadrzaj ukljucujuci HTML stranice, slike, video fajlove i druge tipove podataka ukljucene u web sajt. Aplikacijski server sa druge strane generise dinamicki sadrzaj koji se isporucuje klijentima. Aplikacijski server nikada ne moze biti zamjena za web servere, umjsto toga aplikacijski server i web server moraju da rade zajedno kako bi se klijent imao potpuno iskustvo prilikom korištenja web sajta. Bez aplikacijskog web servera, web aplikacije bi bile ograničene na statički sadržaj i ne bi bile u mogućnosti da se prilagode promjenama u zahtjevima korisnika.

### Reverse Proxy 
Uzmimo za primjer da imamo Node.js aplikaciju koju zelimo da pokrenemo na nasem serveru (hostu). Da bi je pokrenuli potreban nam je `Node.js` server koji omogucava pokretanje `Node.js` aplikacije. `Node.js` server omogucava obradu `HTTP` zahtjeva koji stižu od klijenata i generisanje odgovora na osnovu Node.js koda. `Node.js` server pruža podršku za različite `HTTP` metode, uključujući `GET`, `POST`, `PUT`, `DELETE`, i druge. Kako bi omogucili posluzivanje statickih fajlova, bolju skalabilnost, sigurnost, jednostavniju konfiguraciju neophodno je da ispred naseg Node.js servera postavimo web server (u ovom primjeru Nginx) koji ce da obradi `HTTP` zahtjeve i proslijedi ih `Node.js` serveru. Ovaj tip arhitekture se naziva **Reverse Proxy** arhitektura. U ovom slučaju, **NGINX** server je **Reverse Proxy** server koji obradjuje `HTTP` zahtjeve i proslijedjuje ih `Node.js` serveru. `Node.js` server je u ovom slučaju **Backend** server koji obradjuje `HTTP` zahtjeve i generise odgovore na osnovu Node.js koda koje proslijedjuje **NGINX** serveru koji ih isporucuje klijentima.

![Reverse Proxy](/devops-mentorship-program/03-march/week-5-140323/files/reverse-proxy-1.png)

#### Kako da konfigurišemo Nginx da bude Reverse Proxy server?
Da bi konfigurisali **NGINX** da bude **Reverse Proxy** server neophodno je da podesimo jednostavnu Node.js aplikaciju koja ce da vrati `Hello World` poruku kada se pozove. Ova aplikacija ce biti naš **Backend** server. Nakon toga potrebno je da podesimo NGINX da bude **Reverse Proxy** server koji ce da obradjuje `HTTP` zahtjeve i proslijedjuje ih našem **Backend** serveru.  

Postoji vise razloga zasto bi koristili reverse proxy, a neki od njih su:
- **Skalabilnost** - Reverse proxy serveri mogu da podrže veliki broj klijenata i da se lako skaliraju.
- **Sigurnost** - Reverse proxy moze da sprijeci napade na backend servere.
- **Jednostavnost** - Reverse proxy serveri mogu da se konfigurišu jednostavnije nego backend odnosno aplikacijski serveri.
- **Brzina** - Reverse proxy serveri mogu da se konfigurišu da podrže cache-ovanje i da omoguće brže isporuke sadržaja klijentima.
- **Load Balancing** - Reverse proxy serveri mogu da podrže load balancing i da rasporede zahteve klijenata na različite backend odnosno aplikacijske servere.
- **SSL Termination** - Reverse proxy serveri mogu da podrže SSL terminaciju i da omoguće klijentima da komuniciraju sa backend serverima preko HTTPS protokola.


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

#### Application setup
``` 
$ npm install -g pm2 # Install pm2 process manager
$ pm2 start server.js # Start Node.js application
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
- `location /` - sva podudaranja putanja će biti preusmjerena na proxy server.
- `proxy_pass http://127.0.0.1:3000` - svi dolazni zahtjevi će biti proslijeđeni na adresu `127.0.0.1:3000` gdje se pokrece vasa aplikacija, to je ustvari `localhost` adresa pa ste mogli da koristite i `proxy_pass http://localhost:3000`
- `proxy_http_version 1.1` - verzija HTTP protokola koja će se koristiti za komunikaciju između Nginx i aplikacije.
- `proxy_set_header Upgrade $http_upgrade;` - podesava zaglavlje Upgrade koje omogucava koristenje WebSocket komunikacije.
- `proxy_set_header Connection 'upgrade';` - podesava zaglavlje Connection koje omogućava koristenje WebSocket komunikacije.
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

## Ostale vrste servera

### Serveri za baze podataka

### E-mail serveri

### File serveri

### DNS serveri  

## 📖 Reading materials   
- [DevOps Learning Path - Linux/UNIX OS](../../../devops-tools/web-servers.md)
- [Proxy vs Reverse Proxy (Real-world Examples)](https://youtu.be/4NB0NDtOwIQ)
- [nginx documentation](http://nginx.org/en/docs/)  
- [How nginx processes a request](http://nginx.org/en/docs/http/request_processing.html)  
- [nginx server names](http://nginx.org/en/docs/http/server_names.html)  
- [Avoiding the Top 10 NGINX Configuration Mistakes](https://www.nginx.com/blog/avoiding-top-10-nginx-configuration-mistakes/)

## 📹 Session recordings  
- [**WEEK-5-tier-1-group-1 video session recording**]()   
- [**WEEK-5-tier-1-group-2 video session recording**]()


[:fast_forward: Class Notes](/devops-mentorship-program/03-march/week-5-140323/00-class-notes.md)  
[:fast_forward: Additional Reading](/devops-mentorship-program/03-march/week-5-140323/02-additional-reading.md)   
[:fast_forward: HOME - README.md](../../../README.md)  
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)  
