# SSL - Secure Sockets Layer

**SSL** ili **Secure Sockets Layer** je protokol koji omogucava sigurnu komunikaciju te predstavlja unaprijedjenje TCP protokola na nacin da uvodi povjerljivost, integritet podataka, autentifikaciju servera i autentifikaciju klijenta. 
SSL se koristi za bezbijednost saobracaja koji se odvija preko HTTP protkola. Kako je SSL obezbjedjen od strane TCP 
protkola moze ga koristiti svaka aplikacija koja koristi, odnosno izvrsava se preko TCP protokola. SSL nudi jednostavan API sa soketima koji je slican API-ju koji nudi TCP protokol. Kada neka aplikacija hoce da koristi SSL ukljucuje se SSL klase i biblioteke. SSL se tehnicki nalazi u aplikacionom sloju ali iz perspektive programera to je transportni protokol koji koji pruza TCP usluge obogacene uslugama bezbjednosti. 
Na sljedecoj slici mozete vidjeti gdje se SSL nalazi unutar OSI modela.
![SSL u OSI Modelu](/resources/images/ssl1.jpeg)  

Prilikom klijent server komunikacije preko SSL protokola komunikacija se odvija na sljedeci nacin:
1. Klijent salje spisak kriptografskih algoritama koje podrzava, zajedno sa klijentovim jednokratnim brojem
2. Server is tog spiska bira jedan simetrican kriptografski algoritam (npr: AES) i jedan algoritam javnih kljuceva (npr: RSA) i jedan MAC algoritam. Server nakon toga vraca klijentu svoj izbor, zajedno sa svojim jednokratnim brojem i certifikatom.
3. Klijent provjerava certifikat, izvlaci serverov javni kljuc, pravi PMS (PreMaster Secret) i kriptuje ga javnim kljucem servera. Nakon toga klijent salje serveru svoj izbor, zajedno sa svojim jednokratnim brojem i kriptovanom PMS.
4. Istom funkcijom za pripremu kljuce, klijent i server svako za sebe izracunava MS (Master Secret) na osnovu PMS i jednokratnih brojeva. Zatim se MS dijeli na dva kljuca za sifrovanje i dva MAC kljuca.  Sve buduce poruke izmedju servera i klijenta ce biti sifrovane i autentifikovane pomoca MAC kljuca.
5. Klijent salje posebnu MAC poruku serveru, koja se koristi za potvrdu integriteta podataka.
6. Server salje klijentu posebnu MAC poruku, koja se koristi za potvrdu integriteta podataka.

Na sljedecoj slici je prikazano kako izgleda SSL TLS handshake.
![SSL Handshake](/resources/images/ssl-hanshake.gif)  
*Slika preuzeta sa stranice [An overview of the SSL or TLS handshake](http://docs.blueworx.com/BVR/InfoCenter/V7/AIX/help/index.jsp?topic=%2Fcom.ibm.wvraix.voip.doc%2Fq009930_.html)  

## How does HTTPS work?
Preduslov za sigurnu komunikaciju je da "vjerujete" da public key kriptografija i potpisivanje digitalnim potpisom rade kako treba. To znaci da su "vjerujete" sljedecim principima:
- **Svaka poruka enkriptovana Serverovim javnim kljucem moze biti dekriptovana samo od strane Serverovog privatnog kljuca**
- **Bilo ko sa pristupom Serverovom javnom kljucu moze verifikovati da je poruka (signature) mogla biti kreirana samo od strane nekoga ko ima pristup Serverovom privatnom kljucu.**

Na sljedecoj slici mozete vidjeti pojednostavljeni prikaz HTTPS komunikacije.
![HTTPS](/resources/images/https-communication.jpeg)

## Certificate Authority
**Certificate Authority CA** je entitet koji pohranjuje, potpisuje i izdaje digitalne certifikate. Kako bi mogli utvrditi da je server onaj za kojeg se predstavlja neophodan nam je Certificate Authority kako bi potvrdio njegov identitet. U primjeru sa slike iznad smo imali certifikat koji je potpisan od strane Google CA koji je potvrdio da je server koji salje poruku zaista server na kojem se nalazi web stranica sa domenskim imenom moja-web-stranica.com. 
Google CA se smatra Certified Authoritijem kojem mozemo vjerovati na interentu. Kao i bilo koji drugi CA, Google CA ima svoj par kljuceva. Jedan javni kljuc koji se koristi za potpisivanje certifikata, a drugi privatni kljuc koji se koristi za dekripciju poruka.
Ako zelimo da nas web server koristi HTTPS vezu neophodno je da kreira svoj par kljuceva i da ga registruje kod CA. CA ce potpisati javni kljuc i vratiti nam certifikat koji mozemo koristiti za potvrdu identiteta servera. Proces registracije kljuca kod CA se zove Certificate Signing Request (CSR) i prikazan je na slici ispod.

![Certificate Signing Request](/resources/images/certificate-signing-request.png)

Zahvaljujuci tome sto je Certificate Authority potpisao kljuceve koje server koristi, moze da se sprijeci Man in the Middle Attack gdje bi neka druga stranica / server mogao da se pretvara da je moja-web-stranica.com

Na sljedecem [linku](https://docs.google.com/spreadsheets/d/e/2PACX-1vQ7Jtb4NxCSaEtCaisz2u3NQZcHejDUjI3Q-utBnL-C5E7w4crv6QZ9GRDb2bFGbLgUQsgQyF0Y8eoN/pubhtml) mozete vidjeti listu Certified Authoritija kojima vjeruje Chrome browser.
## Self Signed Certficates
Ponekad nije neophodno da imate SSL certifikat potpisan od strane poznatog Certified Authoritija. Uzmimo za prijmjer da imate development okruzenje gdje zelite da enkriptujete svu komunikaciju. Potrebno je da kreirate par privatnog i javnog kljuca i nakon toga da kreirate CRS koji ce biti potpisan od strane vaseg vlastitog CA. Vlastiti CA kreirate na nacin da ste kreirali par kljuceva koji ce poptpisati kljuceve kreirane od strane servera u development okruzenju. Kako se radi o vasem vlastitom CA, tom certifikatu nece biti vjerovano ali kako se radi o development okruzenju vi mozete reci vasoj aplikaciji da vjeruje CA koji je koristen za potpisivanje certifikata.

## SSL Cahin Of Trust
**Chain of trust (lanac povjerenja)** u kontekstu SSL certifikata odnosi se na proces provjere valjanosti certifikata kako bi se osigurala sigurna veza izmedju browsera/klijenta i web servera. Lanac povjerenja sastoji se od niza certifikata koji se proteže od SSL certifikata krajnjeg korisnika do root certifikata, preko jednog ili više intermediate certifikata.

![SSL Chaing of trust](/resources/images/ssl-chain-of-trust.jpeg)

**Evo kako lanac povjerenja funkcionise za SSL certifikate:**

- **Root certifikat:** Na vrhu lanca povjerenja je root certifikat, koji izdaje Certificate Authority (CA). Root certifikati su ugradjeni u web preglednike i operativne sisteme kao dio njihove liste pouzdanih certifikata.

- **Intermediate certifikat(i):** Root CA izdaje intermediate certifikate, koji služe kao posrednici između root CA i krajnjih korisnika (SSL certifikata). Intermediate CA moze izdati SSL certifikate direktno krajnjim korisnicima ili drugim intermediate CAs, stvarajući lanac medjuposrednickih certifikata.

- **SSL certifikat:** Na kraju lanca povjerenja je SSL certifikat koji je izdan od strane Intermediate CA. SSL certifikat se koristi za autentifikaciju web servera i šifriranje komunikacije izmedju browser i servera.

Kada browser/klijent pokusava uspostaviti SSL/TLS vezu s web serverom, server salje svoj SSL certifikat, zajedno s potrebnim intermediate certifikatima. Browser provjerava lanac povjerenja na sljedeci nacin:

- **Provjera valjanosti SSL certifikata:** Preglednik provjerava je li SSL certifikat važeći (npr. nije istekao) i potpisan od strane poznatog intermediate CA.

- **Provjera intermediate certifikata:** Browser provjerava je li intermediate certifikat vazeci i potpisan od strane poznatog root CA ili drugog intermediate CA.

- **Provjera root certifikata:** Browser uporedjuje root certifikat s onima iz svoje liste pouzdanih certifikata. Ako se root certifikat podudara s jednim od unaprijed instaliranih certifikata, lanac povjerenja smatra se ispravnim.

Ako je lanac povjerenja ispravan, browser uspostavlja sifriranu vezu sa serverom koristeći SSL/TLS protokol. Ako lanac povjerenja nije ispravan, browser prikazuje upozorenje o sigurnosti, obavjestavajuci korisnika o potencijalnom problemu sa certifikatom ili sigurnoscu veze.

Postoji nekoliko razloga zašto su intermediate certifikati važni:

- **Dodatna sigurnost:** Koristenje intermediate certifikata pomaze u zastiti root CA (Certificate Authority) tako što se privatni kljuc root CA može drzati offline i na sigurnom mjestu. Ako bi privatni ključ root CA bio kompromitiran, to bi ugrozilo cijelu hijerarhiju povjerenja. Držanjem root CA offline, smanjuje se rizik od kompromitacije.

- **Fleksibilnost i organizacija:** Intermediate certifikati omogucuju CA da organiziraju izdavanje certifikata po razlicitim kategorijama i namjenama. Npr, jedan intermediate CA moze biti zaduzen za izdavanje certifikata za e-trgovinu, dok drugi može biti zaduzen za izdavanje certifikata za bankarstvo. Ovo omogucuje bolju organizaciju i upravljanje certifikatima.

- **Smanjenje rizika:** U slucaju da neki intermediate CA bude kompromitiran, posljedice su manje katastrofalne nego kad bi root CA bio kompromitiran. Ako je kompromitiran samo jedan intermediate CA, moze se povuci i zamijeniti, a ostatak PKI infrastrukture ostaje netaknut.

- **Skalabilnost:** Koristenjem intermediate certifikata, CA može prosiriti svoju infrastrukturu kako bi podržao rast i razvoj poslovanja, bez potrebe za izmjenom root CA.

Ukratko, intermediate certifikati dodaju dodatni sloj sigurnosti u hijerarhiju povjerenja, omogućavaju bolju organizaciju i upravljanje certifikatima, te pruzaju veću skalabilnost i fleksibilnost PKI infrastrukture.
Neki od najpoznatijih root CA koji izdaju intermediate certifikate su DigiCert, Symantec, GoDaddy, Let's Encrypt, Comodo, GlobalSign, Thawte, i Entrust.

## Leaf Certificates
**Leaf certifikati**, takodjer poznat kao end-entity certificate ili krajnji korisnicki certifikati, su SSL certifikati koji se koriste za autentifikaciju web servera i šifriranje komunikacije između web browsera i servera. Leaf certifikati se izdaju od strane intermediate CA, a ne od strane root CA. Leaf certifikati su najcesce SSL certifikati koji se koriste za HTTPS web stranice. 
U hijerarhiji povjerenja, leaf certifikat je izdan od strane intermediate CA ili u nekim slučajevima direktno od root CA. Kada web browser pokusava uspostaviti SSL/TLS vezu s web serverom, server salje svoj leaf certifikat i potrebne intermediate certifikate browseru kako bi se provjerila autenticnost certifikata kroz lanac povjerenja.

Leaf certifikati sadrze sljedece informacije:

- **Informacije o vlasniku:** Ime, organizacija, adresa i druge informacije o vlasniku certifikata.
- **Javni kljuc:** Javni kljuc koji se koristi za sifriranje podataka u SSL/TLS komunikaciji.
- **CA informacije:** Informacije o autoritetu za certificiranje koji je izdao certifikat (root CA ili intermediate CA).
- **Vrijeme vazenja:** Period vazenja certifikata, nakon kojeg certifikat vise nije valjan i treba ga obnoviti.
- **Digitalni potpis:** Digitalni potpis koji potvrduje autenticnost certifikata.
U kontekstu PKI (Public Key Infrastructure), leaf certifikati su najniži u hijerarhiji povjerenja, ali igraju ključnu ulogu u šifriranju i autentifikaciji internetskog prometa.

## Setup Let's Encrypt SSL Certificate on EC2 Amazon Linux AMI3 (Nginx)
U sljedecem primjeru cemo pokazati na koji nacin mozemo konfigurisati SSL Let's Encrypt certifikat na EC2 Amazon Linux 3 serveru koristeci Nginx web server. Za ovaj primjer cemo koristiti web aplikaciju i znanje koje smo stekli u tokom [Week-5](/devops-mentorship-program/03-march/week-5-140323/00-class-notes.md) predavanja iz serije predavanja odrzanih tokom DevOps Mentorship Programa.
To podrazumijeva da imamo EC2 instancu sa Amazon Linux 3 operativnim sistemom, Nginx web serverom i web aplikaciju koja je dostupna po HTTP protokolu na portu 80 koristeci nasu javnu IP adresu. 

1. Kreirali smo Route 53 DNS zapis `http://ssl.awsbosnia.com/` i usmerili ga na nasu Public IP adresu nase EC2 instance na kojoj se nalazi nasa aplikacija.

2. Za kreiranje Lets Encrypt certifikata iskorisiti cemo [certbot](https://certbot.eff.org/) alat. Certbot je alat koji nam omogucava da automatski generisemo i instaliramo SSL certifikat.
Na Amazon Linux Ami 3 cert bot cemo instalirati koristeci pip alat.
```bash
$ sudo dnf install python3 augeas-libs
$ sudo python3 -m venv /opt/certbot/
$ sudo /opt/certbot/bin/pip install --upgrade pip
$ sudo /opt/certbot/bin/pip install certbot certbot-nginx
$ sudo ln -s /opt/certbot/bin/certbot /usr/bin/certbot
$ sudo certbot certonly --nginx # I am feeling conservative :) you can use sudo certbot --nginx instead to automatize the process
```
Output komande `sudo certbot certonly --nginx`:
```bash
[root@ip-172-31-91-148 ~]# sudo certbot certonly --nginx
Saving debug log to /var/log/letsencrypt/letsencrypt.log
Please enter the domain name(s) you would like on your certificate (comma and/or
space separated) (Enter 'c' to cancel): ssl.awsbosnia.com, www.ssl.awsbosnia.com
Requesting a certificate for ssl.awsbosnia.com and www.ssl.awsbosnia.com

Successfully received certificate.
Certificate is saved at: /etc/letsencrypt/live/ssl.awsbosnia.com/fullchain.pem
Key is saved at:         /etc/letsencrypt/live/ssl.awsbosnia.com/privkey.pem
This certificate expires on 2023-07-14.
These files will be updated when the certificate renews.

NEXT STEPS:
- The certificate will need to be renewed before it expires. Certbot can automatically renew the certificate in the 
background, but you may need to take steps to enable that functionality. See https://certbot.org/renewal-setup for instructions.

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
If you like Certbot, please consider supporting our work by:
 * Donating to ISRG / Let's Encrypt:   https://letsencrypt.org/donate
 * Donating to EFF:                    https://eff.org/donate-le
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
``` 
Vidimo da su nasli ccertifikati na sljedecoj lokaciji:
```
Certificate is saved at: /etc/letsencrypt/live/ssl.awsbosnia.com/fullchain.pem
Key is saved at:         /etc/letsencrypt/live/ssl.awsbosnia.com/privkey.pem
```
3. Kako bi NGINX mogao da primi HTTPS zahtijeve neophodno je da azuriramo NGINX konfiguraciju. 
```bash
$ cp node-app.conf node-app.conf.bak # backup originalne konfiguracije
```
Nakon sto smo napravili backup upisujemo novi konfiguracijski fajl `node-app.conf`:
```bash
server {
  listen 80;
  server_name <domensko-ime.com>;
  return 301 https://$server_name$request_uri;
}

server {
  listen 443 ssl;
  server_name <domensko-ime.com>;

  ssl_certificate /etc/letsencrypt/live/<domensko-ime.com>/fullchain.pem;
  ssl_certificate_key /etc/letsencrypt/live/<domensko-ime.com>/privkey.pem;

  location / {
    proxy_pass http://127.0.0.1:8008;
    proxy_http_version 1.1;
    proxy_set_header Upgrade $http_upgrade;
    proxy_set_header Connection 'upgrade';
    proxy_set_header Host $host;
    proxy_cache_bypass $http_upgrade;
  }
}
```
4. Restartujemo NGINX web server:
```bash
$ sudo systemctl restart nginx
```
Nakon toga vidimo da mozemo pristupiti nasoj web aplikaciji preko sigurne HTTPS veze koristeci domensko ime`https://<domensko-ime.com>/`.  

## Import Lets Encrypt into AWS Certificate Manager
```
# pozicioniramo se u folder sa certifikatima koje je generisao Lets Encrypt
$ cd /etc/letsencrypt/live/ssl.awsbosnia.com/ 
```
Dokumentacija za importovanje certifikata u AWS Certificate Manager se nalazi na [Importing certificates into AWS Certificate Manager](https://docs.aws.amazon.com/acm/latest/userguide/import-certificate.html).

## Useful openssl commands
**OpenSSL** je open source alat ([github repo](https://github.com/openssl/openssl) | [dokumentacija]()) koji se moze korisiti za upravljanje kljucevima i certifikatima, potpisivanje digiralnih potpisa i provjeru autenticnosti certifikata.  
U nastavku su date neke od komandi koje se mogu koristiti za upravljanje certifikatima i kljucevima.  
```bash
#
$
```
Ako zelite da saznate vise o OpenSSL komandama preporuka je da pogledate knjigu [Library: OpenSSL Cookbook  3rd Edition by Ivan Ristic](resources/books/openssl-cookbook-3ed.pdf)

# 📖 Learning materials  

- [📹 DevOps Mentorship Program - Office Hours 15.04.2023 (SSL, AWSCertificateManager(ACM),Lets Encrypt](https://youtu.be/w4GP-jHYffI)
- [certbot - Intructions](https://certbot.eff.org/instructions?ws=nginx&os=pip)
- [certbot - Setting up automated SSL cert renewal](https://eff-certbot.readthedocs.io/en/stable/using.html#setting-up-automated-renewal)
- [Configure SSL/TLS on Amazon Linux 2023](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/SSL-on-amazon-linux-2023.html)
- [📹 MIT OPEN COURSEWARE - SSL and HTTPS](https://youtu.be/q1OF_0ICt9A)
- [Apache SSL](https://httpd.apache.org/docs/2.4/ssl/ssl_faq.html#aboutcerts)
- [OpenSSL Certificate Authority - Create Self Signed Certificate](https://jamielinux.com/docs/openssl-certificate-authority/)
- [:infinity: DevOps Learning Path - AWS Certificate Manager](/aws/aws-service-notes/acm.md)

# Page Navigation

[:fast_forward: :infinity: DevOps Learning Path - Sadrzaj](../table-of-contents.md)
[:fast_forward: HOME - README.md](../README.md)
