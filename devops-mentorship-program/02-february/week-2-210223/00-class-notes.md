# 📝 Class notes
## 📅 Date: 21.02.2023.
### Linux / UNIX Operativni sistemi

**UNIX** je stabilan, mocan i fleksibilan operativni sistem visokih performansi pogodan za
izvrsavanje kriticnih aplikacija od visoke vaznosti. **UNIX** je cvrsto povezan sa mreznim
servisima TCP/IP protokola, cime je u potpunosti promijenjena slika **UNIX**  servera i radnog
okruženja iz prošlosti. Umjesto servera sa klasicnim serijskim terminalima UNIX server se
nalazi u mreži, pri cemu sa radnim stanicama ostvaruje vezu preko LAN/WAN mreže i
TCP/IP skupa protokola. Veina velikih svetskih proizvodjaca racunara razvija specificnu
varijantu UNIX operativnog sistema, što ukazuje na njegov kvalitet, popularnost i
rasprostranjenost. Vecina UNIX sistema, poput Red Hat Enteprise, je komercijalna -
korisnik mora da plati licencu za korišenje, a izvorni kod nije rasploziv. To su razlozi
narastajuce popularnosti Linux sistema koji zadržava vecinu dobrih osobina UNIX
sistema, a dodatno se odlikuje raspolozivim izvornim kodom i prakticno besplatnim
korišenjem. Zbog toga danas veina proizvoaca racunara osim sopstvene komercijalne
verzije UNIX sistema nudi i podršku za Linux. Linux se najcesce koristi kao operativni
sistem na radnim stanicama ili serverima u manjoj ili srednjoj klasi servera, a jedna od
oblasti dominantne primjene, u kojoj veliki broj korisnika podržava i promoviše Linux kao
bazicni server, su Internet servisi. **(dio iz knjige [Linux i UNIX - B. Djordjevic, D.Pleskonjic, N.Macek - Beograd 2004](/resources/books/os-unix-i-linux-beograd-el-skola.pdf))**

**Komande koje vam mogu biti korisne u svakodnevnom radu:**

```bash
$ ssh [ip or hostname] #Secure shell, an encrypted network protocol allowing for remote login and command execution
$ ssh -vvv #verbose for troubleshooting access
```   
```bash
$ sudo su - #Switch to root user
```

```bash
$ whoami #Displays your logged in user id
```
```bash
cd / #Change directory to the root of the filesystem
cd target  #Change directory to “target” directory
cd ~ #Change directory to your home directory
```
```bash
ls # Directory listing
ls -l 
ls -la #Long listing, displays file ownership Displays hidden files/directories
```

```bash
$ pwd #Print Working Directory 
```


`$ grep` [grep command - slika](/resources/images/grep.jpg)    
`$ find` [find command - slika](/resources/images/find.jpg)      
[Find i Grep - Notability biljeske](/resources/notability/bash_notes.pdf)  

```bash
$ chmod #Change persmissions 
$ chown #Chenge owner
```

 - Struktura Linux/Unix file sistema

  ![alt Linux Directory Structure](/resources/images/linux-dirs.jpeg)  


## 📖 Reading materials
- [Linux i UNIX - B. Djordjevic, D.Pleskonjic, N.Macek - Beograd 2004](/resources/books/os-unix-i-linux-beograd-el-skola.pdf) 
- [Uvod u Linux](/resources/papers/uvod_u_linux_srce.pdf)   
- [UNIX and Linux System Administration Handbook, 5th Edition](/resources/books/unix-linux-sys-admin-handbook.pdf)   
- [UNIX Operativni Sistemi](http://os.etf.rs/POS/tutorials/srdjan/kurs/html/sadrzaj.html)   
- [yum cheat sheet](/resources/chaet-sheets/yum-cheatsheet.pdf)  
- [Linux Performance Analysis in 60,000 Milliseconds](https://netflixtechblog.com/linux-performance-analysis-in-60-000-milliseconds-accc10403c55)   
## 📹 Session recordings  
- [**WEEK-2-tier-1-group-1 video session recording**](https://youtu.be/VWUv7sISfs0)  

## **Ostale stranice**
- [Homework Task](/devops-mentorship-program/02-february/week-2-210223/01-homework.md)  
- [Go to Additional Reading](/devops-mentorship-program/02-february/week-2-210223/02-additional-reading.md)   
- [HOME](https://github.com/allops-solutions/devops-aws-mentorship-program#devops-mentorship-program)  
