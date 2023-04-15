#	📝 Class notes
## 	📅 Date: 14.02.2023.

## Git Intro 
Git je alat koji je razvio Linus Torvalds da bi mu olaksao vodjenje jednog velikog i kompleksnog projekta – Linux kernela. Git je de-facto postao standardni alat za verzionisanje koda. 

### Git Osnovni pojmovi 
- **Init** – Inicijalizacija Git repozitorijuma. Inicijalizacija se obavlja komandom `git init` u direktorijumu u kojem se nalazi projekat. 
- **Repository** – Git repozitorijum je mesto gde se nalaze sve verzije projekta.
  - **Remote** – Remote je udaljeni/remote repozitorijum (npr. onaj koji ste kreirali na git serveru, GitHub-u etc.).
  - **Local** – Local je lokalni repozitorijum (incijaliziran na vasem lokalnom racunaru).
- **Add** – Add je proces dodavanja fajlova u staging area. Fajlovi se dodaju komandom `git add` ili `git add .` (dodaje sve fajlove u trenutnom direktorijumu).  
- **Staging Area** – Staging area je mesto gde se nalaze fajlovi koji ce biti dodati u commit. 
- **Commit** – Commit je verzija projekta. Commit se sastoji od snapshot-a projekta i metapodataka koji opisuju commit.
- **Push** – Push je proces slanja izmjena koda iz lokalnog repozitorijuma na udaljeni/remote repozitorijum (npr. na GitHub).
- **Branch** – Branch je nezavisna linija/grana razvoja projekta. Branch se koristi da bi se razvijale nove funkcionalnosti neovisno od glavne `main` grane, Branch/Grana se moze spojiti sa drugom granom/branch-om.
- **Pull** – Pull je proces preuzimanja izmjena koda iz udaljenog/remote repozitorijuma na lokalni repozitoriji (onaj na vasem racunaru).
- **Merge** – Merge je proces spajanja dve grane/branch-a u jednu granu/branch.
- **Pull Request** – Pull request je zahtjev za spajanje izmjena iz jedne grane/branch-a u drugu granu/branch.
- **Fork** – Fork je kopija projekta. Fork se koristi da bi se napravila kopija postojeceg projekta i nastavio rad na njemu bez uticaja na originalni projekat. Fork se moze spojiti sa originalnim projektom koristeci Pull Request.  

![Git](/resources/images/git-local-remote.png)
## Install Git on Windows  
Git za Windows OS mozete preuzeti sa sljedeceg [**linka**](https://git-scm.com/download/win).

Preuzmite [**PowerShell**](https://learn.microsoft.com/en-us/powershell/scripting/overview?view=powershell-7.3) da bi dobili terminal pogodniji za rad.
Na sljedecem [**linku**](https://www.youtube.com/watch?v=a-zX_qc2S-M&ab_channel=CameronMcKenzie) mozete ispratiti kako da kreirate `ssh kljuc` i podesiti vas GitHub profil za izvrsavanje git naredbi i autentifikaciju na udaljeni Git repozitoriji uz pomoc privatnog i javnog kljuca.

Mozete preuzeti i [**Windows Terminal**](https://apps.microsoft.com/store/detail/windows-terminal/9N0DX20HK701?hl=sr-cyrl-ba&gl=ba&rtc=1)

<details><summary>Kliknite za dodatne informacije git alata unutar Windows terminala</summary>
<p>  

Komande da podesite PowerShell na verziju `PS 7` i da se prikazuje ime branch-a u kojem se trenutno nalazite dok radite sa Git-om:

- Prvo preimenujte glavnu granu/branch iz `master` u `main`
  ```bash
  git branch -m master main
  git status
  ```

- Komanda za upgrade PS 5.1 u verziju PS 7 - u PS mora imati administratororske privilegije (i.e: Run PS as Administrator)
  ```PowerShell
  iex "& { $(irm https://aka.ms/install-powershell.ps1) } -UseMSI"
  ```
- Komande za prikaz imena branch-a/grane - (Run PS as Administrator)
```PowerShell
  > Import-Module posh-git -Scope AllUsers
  > Add-PoshGitToProfile -AllHosts
```

</p>
</details>
 
## Install Git on MacOS  
MacOS je Unix operativni sistem i njegov terminal je vec pogodan za koristenje. Da bi instalirali git na vasem Mac-u mozete korisiti [**brew**](https://brew.sh/) paket menadzer. 
```
$ brew install git
```

Na [**linku**](https://www.youtube.com/watch?v=nZYJKXXMvkM&ab_channel=TechPedia-HowtoTech) mozete ispratiti kako da kreirate ssh kljuc i podesite vas GitHub profil da mozete izvrsavati git commande.

```bash
$ ssh-keygen -t ed25519 -C "sqlheisenberg@gmail.com" #kreiranje ssh kljuca za korisnika sa email adresom sqlheisenberg@gmail.com
# Kad na ekranu dobijete poruku Generating public/private ed25519 key pair odgovorite sa enter na pitanja koja ce se pojaviti
# Ovo ce kreirati privatni i javni ssh kljuc u direktorijumu ~/.ssh/ sa nazivom id_ed25519 i id_ed25519.pub
$ cat .ssh/id_ed25519.pub #Ispisite vas javni kljuc na ekran i kopirajte ga a zatim upisite u vas GitHub profil
```

**Osnovne Git Naredbe**

```bash
$ git init #inicijalizacija git repozitorijuma

$ git status #prikaz statusa repozitorijuma

$ git add file-name #dodavanje fajla u staging area

$ git add . #dodavanje svih fajlova iz radnog direktorija ova u staging area

$ git commit -m"commit message" #kreiranje commit-a

$ git push #slanje izmjena na udaljeni/remote repozitorijum

$ git pull #preuzimanje izmjena sa udaljenog/remote repozitorijuma

$ git log #prikaz loga commit-ova

$ git log --oneline #prikaz loga commit-ova u jednoj liniji

$ git log --oneline --graph #prikaz loga commit-ova u jednoj liniji sa grafickim prikazom

$ git clone repo-url #kloniranje projekta sa udaljenog/remote repozitorijuma

$ git remote -v #prikaz povezanih udaljenih/remote repozitorijuma

$ git branch #prikaz trenutne grane/branch-a unutar kojeg se nalazite

$ git branch branch-name #kreiranje nove grane/branch-a od trenutne grane/branch-a na kojem se nalazite, necete biti automatski prebaceni na novu granu/branch

$ git merge branch-name #spajanje grane/branch-a sa trenutnom granom/branch-om

$ git checkout branch-name #prebacivanje na drugu granu/branch

$ git remote add origin repo-url #povezivanje lokalnog repozitorijuma sa udaljenim/remote repozitorijumom

$ git config --global user.name "username" #podesavanje korisnickog imena --global znaci da ce ove postavke biti primjenjene na svaki repozitorijum na vasem racunaru

```

  ![alt Git Cheat Sheet](/resources/images/git-cheat-sheet.jpg)


- [**Lucidchart diagram tier-1-group-1**](files/lucidchart-week-1-tier-1.pdf)
- [**Whiteboard tier-1-group-1**](files/whiteboard-week-1-tier-1.pdf)

## 📹 Session recordings
- [**WEEK-1-tier-1-group-1 video session recording**](https://youtu.be/jNPFe9vdRFI)  
- [**WEEK-1-tier-1-group-2 video session recording**](https://youtu.be/FDOho51mkuE)

## 📖 Learning materials  
-  [ **📖 Uvod u Git - Tomo Krajina**](/resources/books/github_knjiga_tomo_krajina.pdf)   
   - [**📖 Uvod u Git - Biljeske iz knjige**](/devops-mentorship-program/02-february/week-1-140223/files/uvod-u-git-notes.md)
- [**📖Git Notes for Professionals**](/resources//books/git-notes-for-professionals.pdf)
- [**📹 Git for Professionals Tutorial - Tools & Concepts for Mastering Version Control with Git**](https://www.youtube.com/watch?v=Uszj_k0DGsg)
- [**📖 git Documentation**](https://git-scm.com/docs/git)  

## ⚒️ Useful Tools  
- [VS Code Git History Extension](https://marketplace.visualstudio.com/items?itemName=donjayamanne.githistory)
- [VS Code Git Graph Extension](https://marketplace.visualstudio.com/items?itemName=mhutchie.git-graph)
- [VS Code GitLens — Git supercharged Extension](https://marketplace.visualstudio.com/items?itemName=eamodio.gitlens)  


[:fast_forward: Homework Task](/devops-mentorship-program/02-february/week-1-140223/01-homework.md)  
[:fast_forward: Additional Reading](/devops-mentorship-program/02-february/week-1-140223/02-additional-reading.md)   
[:fast_forward: HOME - README.md](../../../README.md)  
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)  


