# Linux/UNIX OS [WIP]
Linux i Unix Operativne Sisteme smo obradjivali u sklopu DevOps Mentorship programa. Predavanja iz tog dijela mozete pogledati u dijelu 📝 [Class notes]().

## Processes [WIP]

Postoji razlika izmedju zaustavljanja procesa i zaustavljanja servisa.

Zaustavljanje procesa znači da se ubija proces ili program koji se izvodi u operativnom sustavu. Ovo ce zaustaviti proces bez obzira na to radi li se o servisu ili ne. Na primjer, možete koristiti naredbu `kill` ili `pkill` da biste zaustavili proces Nginx-a, bez obzira na to radi li se o procesu servisa ili ne. Međutim, to nije preporučljivo, jer će se servis ponovno pokrenuti nakon što se server ponovno pokrene, a ako postoji neki problem u konfiguraciji, moglo bi doći do problema u radu servisa.

**Zaustavljanje servisa znaci da se zaustavlja cijeli servis, zajedno s povezanim procesima i uslugama koje se izvrsavaju na serveru.** Ovo je najsigurniji nacin za zaustavljanje servisa, jer će se svi procesi, usluge i drugi elementi vezani za servis pravilno zaustaviti, a nakon toga će se servis ugasiti. Na primjer, mozete koristiti naredbe systemctl stop nginx ili service nginx stop da biste zaustavili Nginx servis.

Ukratko, zaustavljanje procesa i zaustavljanje servisa su dvije razlicite naredbe koje imaju razlicite učinke na procese i usluge koje se izvode na serveru. Zaustavljanje servisa je preporucljivo jer ce sigurno zaustaviti sve povezane procese i usluge i omoguciti sigurno zaustavljanje servisa.

TO-DO:
- Proces menadzment
- systemd journal
- Sticky bit

## Security-Enhanced Linux (SELinux)
**Security-Enhanced Linux (SELinux)** je sigurnosni modul za Linux operativne sisteme koji je razvijen u suradnji s Nacionalnom sigurnosnom agencijom (NSA) Sjedinjenih Americkih Drzava.

SELinux dodaje dodatni sloj sigurnosti na Linux sustav, s ciljem sprecavanja napadaca da zloupotrijebe ranjivosti softvera. Radi tako da osigurava izolaciju procesa, kriptiranje podataka i dodatne mehanizme za provjeru autenticnosti.

Kao što je već spomenuto, SELinux ogranicava pristup resursima na temelju sigurnosne politike, a zatim dodjeljuje sigurnosne kontekste za procese i datoteke na sustavu. Na primjer, SELinux može ograničiti pristup mreznim resursima na temelju vrste aplikacije i njenih konteksta.

SELinux se moze konfigurisati i prilagoditi za razlicite zahtjeve sigurnosti. Npr. administratori sistema mogu odabrati sigurnosnu politiku koja se prilagodjava okruzenju sistema ili promijeniti sigurnosni kontekst resursa kako bi se osiguralo da samo određeni procesi imaju pristup odredjenim datotekama.

Ukratko, SELinux pruža dodatni sloj sigurnosti na Linux sustavu koji ograničava pristup resursima i pomaže u sprečavanju napada na ranjivosti softvera.

Za vise informacija pogledati knjigu **SELinux System Administration - Third Edition** - [Safari Books Online](https://learning.oreilly.com/library/view/selinux-system-administration/9781800201477/) | [Amazon](https://www.amazon.com/SELinux-System-Administration-applications-information/dp/1800201478)

[📹 Security-Enhanced Linux for mere mortals - Red Hat Summit](https://youtu.be/_WOKRaM-HI4)

[man](http://man.cat-v.org/unix_10th/)
[Red Hat - System Administrator’s Guide](https://access.redhat.com/documentation/en-us/red_hat_enterprise_linux/7/html/system_administrators_guide/index)

# Page Navigation

[:fast_forward: Sadrzaj - DevOps Learning Path](../table-of-contents.md)
[:fast_forward: HOME - README.md](../README.md)
