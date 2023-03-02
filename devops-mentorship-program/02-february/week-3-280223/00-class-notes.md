# 📝 Class notes  
## 📅 Date: 28.02.2023.  
### Bash / Shell Scripting

**Shell** je program koji obezbjedjuje koriisniku direktnu interakciju sa operativnim sistemom. Na pocetku je Unix OS koristio shell program pod nazivom **Bourne shell**. Danas se koristi **Bash** shell program koji je kompatibilan sa Bourne shell-om. **Bash** shell je standardni shell program na vecini Linux distribucija.  
Postoji vise razlicith shell programa koje su razvijane za razlicite verzije UNIX-a. Ovo su neki od njih:  
- `sh` - Bourne shell
- `csh` - C shell
- `ksh` - Korn shell
- `tcsh` - Tenex C shell / poboljsani C shell
- `zsh` - Z shell / extenzija za `bash`, `ksh` i `tchsh`
- `bash` - GNU Bourne again shell
- `fish` - Friendly interactive shell

Detaljnije o razlikama izmedju razlicith shellova mozete pogledati na linku: [Hyperpolyglot - Unix Shells: Bash, Fish, Ksh, Tcsh, Zsh](https://hyperpolyglot.org/unix-shells)  

Kad god ukucamo neku komandu u Terminal shella, shell (`bin/bash`) je odgovoran da pravilno izvrsi komandu. Aktivnosti koje shell izvrsava su:
- parsiranje komande
- procjenjivanje meta karaktera kao sto su zamjenski znakovi, specijalni karakter itd.
- obrada signala
- pokretanje programa za izvrsenje komande

Da bi provjerili koji shell koristimo, mozemo ukucati komandu `echo $SHELL`. Ova komanda ce nam vratiti putanju do shell programa koji se trenutno koristi.

```bash
$ echo $SHELL
/bin/bash
```
Prethodna komanda nam govori da koristimo `/bin/bash` odnosno Bash shell.  Sljedecom komndom mozemo provjeriti verziju Bash shell-a.  
```bash
$ bash --version

GNU bash, version 4.2.46(2)-release (x86_64-redhat-linux-gnu)
Copyright (C) 2011 Free Software Foundation, Inc.
License GPLv3+: GNU GPL version 3 or later <http://gnu.org/licenses/gpl.html>

This is free software; you are free to change and redistribute it.
There is NO WARRANTY, to the extent permitted by law.
```
### Bash shell konfiguracijski fajlovi
Shell konfiguracijski fajl je tekstualni fajl koji se koristi za konfigurisanje okruženja shell-a. Konfiguracijski fajlovi se koriste za postavljanje okruženja, definisanje shell promjenljivih, kreiranje aliasa, postavljanje putanja (PATH) i drugih varijabli, kao i za dodavanje funkcionalnosti shell-u korištenjem skripti i drugih dodataka. Kada se shell pokrene, prvo se čita konfiguracijski fajl kako bi se postavilo okruženje, prije nego što se shell prikaže na ekranu i korisnik može početi kucati komande. Konfiguracijski fajl se izvršava za svaki novi shell koji se pokrene, bilo da se shell pokrene na početku sesije, ili putem skripte ili terminala. Ovi fajlovi se nalaze u korisničkom direktoriju (home folder-u) i mogu biti uređeni korištenjem bilo kojeg tekst editora.

```bash
$ pwd
/home/centos

$ ls -la .*

-rw-------. 1 centos centos 6744 Feb 28 22:19 .bash_history
-rw-r--r--. 1 centos centos   18 Nov 24  2021 .bash_logout
-rw-r--r--. 1 centos centos  193 Nov 24  2021 .bash_profile
-rw-r--r--. 1 centos centos  259 Feb 28 19:07 .bashrc

```

- `.bashrc` - Ovaj fajl se izvrsava svaki put kada se pokrene Bash shell. Ovaj fajl se nalazi u korisnikovom home direktoriju. Ovaj fajl se koristi za postavljanje promenljivih okruzenja, aliasa, funkcija itd.

- `.bash_profile` - Ovaj fajl se izvrsava samo kada se pokrene Bash shell u interaktivnom modu. Ovaj fajl se nalazi u korisnikovom home direktoriju. Ovaj fajl se koristi za postavljanje promenljivih okruzenja, aliasa, funkcija itd.

- `.bash_history` - Ovaj fajl se nalazi u korisnikovom home direktoriju. Ovaj fajl sadrzi istoriju komandi koje je korisnik izvrsio u Bash shell-u.

- `.bash_logout` - Ovaj fajl se izvrsava kada se korisnik izloguje iz Bash shell-a. Ovaj fajl se nalazi u korisnikovom home direktoriju.

- `.profile` - Ako postoji, ovaj konfiguracijski fajl interno poziva `.bashrc`

Razliciti shellovi imaju razlicite nazive i putanje do konfiguracijskih fajlova. Ovo su neki od njih:   
- `sh` - `.profile`
- `csh` - `.cshrc`
- `ksh` - `.kshrc`
- `tcsh` - `.tcshrc`
- `zsh` - `.zshrc`
- `bash` - `.bashrc`
- `fish` - `.config/fish/config.fish`


```bash

### Hello World Shell Script

Kreirajte novi fajl sa nazivom `hello.sh` i dodajte sljedeci sadrzaj:
```bash
#!/bin/bash 
# Ovo je komtar unutar skripte
echo "Danas je: "
date
```
Kada pokrenemo ovu skriptu, dobijamo sljedeci rezultat odnosno output u terminalu:
```bash
$ ./hello.sh
Danas je:
Wed Mar  1 10:43:12 UTC 2023
```

Linija `#!/bin/bash` se naziva **shebang** i govori shell-u da koristi Bash shell za izvrsavanje skripte. Shebang je obavezna linija u svakoj shell skripti. Shell koristi ovu liniju da odredi koji shell program treba koristiti za izvrsavanje skripte. Trebalo bi da to uvijek bude prva linija u skripti.

Skriptu mozemo izvrsiti na nekoliko nacin:

1. `$ bash hello.sh` - Na ovaj nacin izvrsavamo skriptu koristeci Bash shell program tako sto smo proslijedili naziv skripte kao argument komandi `bash`.
2. `$ ./hello.sh` - Na ovaj nacin izvrsavamo skriptu koristeci Bash shell program tako sto smo proslijedili naziv skripte kao argument komandi `./`. Ova komanda ce pokrenuti skriptu ako je fajl izvrsiv. Ako fajl nije izvrsiv, dobijamo gresku `Permission denied`. Da bi fajl bio izvrsiv, potrebno je da mu dodamo prava za izvrsavanje. To mozemo uraditi sa komandom `chmod +x hello.sh`. Nakon toga mozemo izvrsiti skriptu sa komandom `./hello.sh`.

`./` - U UNIX sistemima, "./" predstavlja relativnu putanju do trenutnog direktorija u kojem se nalazimo u shell-u. "." predstavlja trenutni direktorij, a "/" predstavlja putanju.

Kada se koristi `./` zajedno sa nazivom izvršnog fajla (npr. "./hello.sh"), to znazi da se taj fajl pokreće iz trenutnog direktorija. Ovo je korisno kada se nalazimo u direktoriju u kojem se nalazi izvršni fajl, jer nam omogućava da pokrenemo fajl bez potrebe za kucanjem pune putanje do fajla.  

### Kompajler VS Interpreter  
- **Kompajliranje** - Programski jezici koji koriste kompajler (C, C++, Java ...) za izradu izvršnog fajla. Kompajler je program koji prevede izvorni kod u izvršni fajl. 

- **Interpreter** - Shell je zasnovan na interpreteru, svaka linija programa odnosno skripte je usnos u shell. Linija shell skripte se izvrsava jedna po jedna sekvencijalno. Cak i ako druga linija skripte ima gresku, shell interpreter ce izvrsiti prvu liniju.

### Environment Variables
**Environment variables** su varijable koje su definisane u operativnom sistemu i dostupne su svim procesima koji se pokrecu u tom okruzenju. One sadrze informacije koje se koriste za postavljanje okruzenja za procese koji se pokrecu u operativnom sistemu, kao što su putanje do direktorija, sistemski parametri, konfiguracijske postavke i druge informacije koje su potrebne da bi se programi izvršavali ispravno.

Environment variables su u osnovi nazivi koji su povezani sa odredjenim vrijednostima. Na primjer, `PATH` je environment variable koja sadrži putanje do direktorija u kojima se nalaze izvršni fajlovi, a `HOME` je environment variable koja sadrži putanju do korisničkog direktorija (home folder-a).

Environment variables se mogu definisati i mijenjati na razlicite nacine, ovisno o operativnom sistemu. U Unix sistemima se najcesce koristi `export` komanda kako bi se kreirale ili modifikovale environment variables.

Programi koji se izvršavaju u **UNIX** okruženju mogu koristiti environment variables da bi pristupili informacijama o okruženju u kojem se pokreću. Na primjer, programi koji se koriste u skriptama mogu koristiti environment variables kako bi pristupili informacijama o putanjama do direktorija ili drugim informacijama koje su neophodne da bi se programi izvršili ispravno.

#### PATH environment variable

**PATH** je jedna od najvaznijih environment varijabli na UNIX sistemima. Ona definiše putanje direktorija u kojima se operativni sistem traži izvrsne (executable) fajlove kad izrsavate komande u shell-u.

Kada korisnik unese neku komandu u shell, operativni sistem traži tu komandu u nizu direktorija definisanim u `PATH` varijabli, počevši od prvog direktorija u nizu, sve dok ne pronađe traženi fajl. Ako se fajl ne pronađe u nijednom direktoriju u PATH-u, operativni sistem će prijaviti grešku i prikazati poruku o grešci.

Standardne putanje koje su definisane u PATH-u obično uključuju direktorije poput `/usr/bin`, `/bin` i `/usr/local/bin`. Kada  korisnik unese neka komanda, npr. `ls`, OS ce pretražiti ove direktorije u `PATH-u`, i ako se fajl `ls` pronadje u jednom od tih direktorija, on će biti pokrenut.

```bash
$ which ls # whcih je komanda koja nam pokazuje putanju do izvrsnog fajla

alias ls='ls --color=auto'
	/usr/bin/ls #putanja na kojoj se nalazi ls executable fajl

$ echo $PATH

/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/home/centos/.local/bin:/home/centos/bin
# Vidimo da je putanja /usr/bin/ fajla u PATH-u i da ce se ta komanda  moze uspjesno izvrsiti
```

Putanje u PATH-u na UNIX sistemima su odvojene dvotackom `:`.

Korisnici mogu mijenjati PATH varijablu kako bi dodali nove direktorije u nju, omogucavajuci im da pokrenu programe koji se nalaze u drugim direktorijima osim standardnih direktorija. To se može uraditi dodavanjem novih direktorija u PATH putem komandne linije ili putem konfiguracijskih fajlova za shell, kao sto je `.bashrc`  

## 📖 Reading materials 
[Linux Command Line and Shell Scripting Bible, 3nd Edition](/books/linux-command-line-and-shell-scripting-bible-3rd-edition.pdf)

## 📹 Session recordings  
- [**WEEK-3-tier-1-group-1 video session recording**](https://youtu.be/HVXbqo21ED0) 



[:fast_forward: Homework Task](/devops-mentorship-program/02-february/week-2-280223/01-homework.md)  
[:fast_forward: Additional Reading](/devops-mentorship-program/02-february/week-2-280223/02-additional-reading.md)   
[:fast_forward: HOME - README.md](https://github.com/allops-solutions/devops-aws-mentorship-program#devops-mentorship-program)  
