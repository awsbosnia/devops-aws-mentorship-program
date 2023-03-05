# Security
**SSL certifikati (Secure Sockets Layer certifikati)** su digitalni certifikati koji se koriste za osiguravanje enkripcije podataka tijekom komunikacije između web preglednika i web servera. SSL certifikati omogućavaju web pregledniku da verificira autentičnost web servera i osigura da se podaci koji se razmjenjuju između web preglednika i servera enkriptiraju i ne mogu se neovlašteno čitati ili mijenjati.

Postoji nekoliko vrsta SSL certifikata koje web server može koristiti, uključujući pojedinačne SSL certifikate, wildcard SSL certifikate i SSL certifikate više domena.

Pojedinačni SSL certifikat izdaje se za pojedinačni domenu ili poddomenu web stranice, što znači da se certifikat može koristiti samo za taj domen ili poddomen. Wildcard SSL certifikat se koristi za više poddomena koji pripadaju jednom glavnom domenu, na primjer *.example.com. SSL certifikati više domena omogućuju web serveru da koristi jedan certifikat za više različitih domena.

SSL certifikati se izdaju od strane povjerljivih izdavača certifikata, koji provjeravaju autentičnost podataka o vlasniku domene prije izdavanja certifikata. SSL certifikati su najčešće plaćeni, ali postoje i besplatne opcije kao što su Let's Encrypt i CloudFlare SSL certifikati.

Kada se posjetitelj pokuša povezati sa SSL zaštićenom web stranicom, web server šalje SSL certifikat posjetitelju, koji ga preglednik koristi za verifikaciju autentičnosti web servera. Nakon verifikacije, preglednik i web server razmjenjuju ključeve za enkripciju podataka koji se šalju između njih. Enkriptirani podaci se zatim razmjenjuju između web preglednika i servera, osiguravajući da se podaci ne mogu neovlašteno čitati ili mijenjati.
## Encryption in Transit

## Encryption at Rest
