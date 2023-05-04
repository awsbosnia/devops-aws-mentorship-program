# 📝 Class notes  
## 📅 Date: 28.02.2023.  
### Bash / Shell Scripting

**Shell** je program koji obezbjedjuje korisniku direktnu interakciju sa operativnim sistemom. Na pocetku je Unix OS koristio shell program pod nazivom **Bourne shell**. Danas se koristi **Bash** shell program koji je kompatibilan sa Bourne shell-om. **Bash** shell je standardni shell program na vecini Linux distribucija.  
Postoji vise razlicitih shell programa koji su razvijani za razlicite verzije UNIX-a. Ovo su neki od njih:  
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
Prethodna komanda nam govori da koristimo `/bin/bash` odnosno Bash shell.  Sljedecom komandom mozemo provjeriti verziju Bash shell-a.  
```bash
$ bash --version

GNU bash, version 4.2.46(2)-release (x86_64-redhat-linux-gnu)
Copyright (C) 2011 Free Software Foundation, Inc.
License GPLv3+: GNU GPL version 3 or later <http://gnu.org/licenses/gpl.html>

This is free software; you are free to change and redistribute it.
There is NO WARRANTY, to the extent permitted by law.
```
### Bash shell konfiguracijski fajlovi
Shell konfiguracijski fajl je tekstualni fajl koji se koristi za konfigurisanje okruzenja shell-a. Konfiguracijski fajlovi se koriste za postavljanje okruzenja, definisanje shell promjenljivih, kreiranje aliasa, postavljanje putanja (PATH) i drugih varijabli, kao i za dodavanje funkcionalnosti shell-u koristenjem skripti i drugih dodataka. Kada se shell pokrene, prvo se cita konfiguracijski fajl kako bi se postavilo okruzenje, prije nego sto se shell prikaze na ekranu i korisnik moze poceti kucati komande. Konfiguracijski fajl se izvrsava za svaki novi shell koji se pokrene, bilo da se shell pokrene na pocetku sesije, ili putem skripte ili terminala. Ovi fajlovi se nalaze u korisnickom direktoriju (home folder-u) i mogu biti uredjeni koristenjem bilo kojeg tekst editora.

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
# Ovo je komentar unutar skripte
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

Kada se koristi `./` zajedno sa nazivom izvrsnog fajla (npr. "./hello.sh"), to znaci da se taj fajl pokrece iz trenutnog direktorija. Ovo je korisno kada se nalazimo u direktoriju u kojem se nalazi izvrsni fajl, jer nam omogucava da pokrenemo fajl bez potrebe za kucanjem pune putanje do fajla.  

### Kompajler VS Interpreter  
- **Kompajler** - Kompajler je program koji prevede izvorni kod u izvrsni fajl. Programski jezici koji koriste kompajler (C, C++, Java ...) za izradu izvrsnog fajla.  

- **Interpreter** - Interpreter je program koji u toku izvrsavanja prevodi jednu po jednu naredbu. Shell je zasnovan na interpreteru, svaka linija programa odnosno skripte je usnos u shell. Linije shell skripte se izvrsavaju jedna po jedna sekvencijalno. Cak i ako druga linija skripte ima gresku, shell interpreter ce izvrsiti prvu liniju.

### Environment Variables
**Environment variables** su varijable koje su definisane u operativnom sistemu i dostupne su svim procesima koji se pokrecu u tom okruzenju. One sadrze informacije koje se koriste za postavljanje okruzenja za procese koji se pokrecu u operativnom sistemu, kao sto su putanje do direktorija, sistemski parametri, konfiguracijske postavke i druge informacije koje su potrebne da bi se programi izvrsavali ispravno.

Environment variables su u osnovi nazivi koji su povezani sa odredjenim vrijednostima. Na primjer, `PATH` je environment variable koja sadrzi putanje do direktorija u kojima se nalaze izvrsni fajlovi, a `HOME` je environment variable koja sadrzi putanju do korisnickog direktorija (home folder-a).

Environment variables se mogu definisati i mijenjati na razlicite nacine, ovisno o operativnom sistemu. U Unix sistemima se najcesce koristi `export` komanda kako bi se kreirale ili modifikovale environment variables.

Programi koji se izvrsavaju u **UNIX** okruzenju mogu koristiti environment variables da bi pristupili informacijama o okruzenju u kojem se pokrecu. Na primjer, programi koji se koriste u skriptama mogu koristiti environment variables kako bi pristupili informacijama o putanjama do direktorija ili drugim informacijama koje su neophodne da bi se programi izvrsili ispravno.

#### PATH environment variable

**PATH** je jedna od najvaznijih environment varijabli na UNIX sistemima. Ona definise putanje direktorija u kojima se operativni sistem trazi izvrsne (executable) fajlove kad izvrsavate komande u shell-u.

Kada korisnik unese neku komandu u shell, operativni sistem trazi tu komandu u nizu direktorija definisanim u `PATH` varijabli, pocevsi od prvog direktorija u nizu, sve dok ne pronadje trazeni fajl. Ako se fajl ne pronadje u nijednom direktoriju u PATH-u, operativni sistem ce prijaviti gresku i prikazati poruku o gresci.

Standardne putanje koje su definisane unutar environment varijable `PATH` obicno ukljucuju direktorije poput `/usr/bin`, `/bin` i `/usr/local/bin`. Kada korisnik unese neku komandu, npr. `ls`, OS ce pretraziti direktorije navedene u `PATH` putanji unutar kojih ce traziti izvrsni (executable) fajl komande `ls`. Ako se izvrsni fajl za komandu `ls` nalazi u jednom od tih direktorija komanda `ls` ce biti uspjesno izvrsena.

```bash
$ which ls # which je komanda koja nam pokazuje putanju do izvrsnog fajla

alias ls='ls --color=auto'
	/usr/bin/ls #putanja na kojoj se nalazi ls executable fajl

$ echo $PATH

/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/home/centos/.local/bin:/home/centos/bin
# Vidimo da je putanja /usr/bin/ fajla u PATH-u i da ce se ls komanda  moze uspjesno izvrsiti
```

Putanje u PATH-u na UNIX sistemima su odvojene dvotackom `:`.

Korisnici mogu mijenjati `PATH` varijablu kako bi dodali nove direktorije u nju, omogucavajuci im da pokrenu programe koji se nalaze u drugim direktorijima osim standardnih direktorija. To se moze uraditi dodavanjem novih direktorija u `PATH` putem komandne linije odnosno terminala ili putem konfiguracijskih fajlova za `shell`, kao sto je `.bashrc`  

## 📖 Reading materials 
[Linux Command Line and Shell Scripting Bible, 3nd Edition](/resources/books/linux_command_line_and_shell_scripting_bible_3rd_edition.pdf)

## 🛠️ Tools
- [explainshell.com](https://explainshell.com/)

## 📹 Session recordings  
- [**WEEK-3-tier-1-group-1 video session recording**](https://youtu.be/HVXbqo21ED0)  
- [**WEEK-3-tier-1-group-2 video session recording**](https://youtu.be/QYeX0iP6rXY)


[:fast_forward: Homework Task](/devops-mentorship-program/02-february/week-3-280223/01-homework.md)  
[:fast_forward: Additional Reading](/devops-mentorship-program/02-february/week-3-280223/02-additional-reading.md)   
[:fast_forward: HOME - README.md](../../../README.md)  
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)  
