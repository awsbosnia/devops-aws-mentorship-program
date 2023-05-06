# Kako da doprinesete ovom repozitoriju
Zahvaljujemo se na vasem interesovanju za doprinos i unaprijedjenje dokumentacije i materijala u ovom repozitoriju. Najbolji nacin da doprineste ovom repozitoriji je da otvorite **Pull Request** sa izmjenama koje mislite da bi bile korisne ili da kreirate **Issue** na GitHub-u sa prijedlozima za unaprijedjenje ili izmjene.

**Pull Request** i **Issue** ce biti pregledani od strane mentora ovog programa.

## Jezik
Za pisanje dokumentacije ovog repozitorija koristimo Bosanski, Hrvatski, Srpski, Crnogorski jezik sa ijekavski dijalektom i latinicnim pismom. Izbjegavajte upotrebu dijalektickih znakova i specijalnih karaktera (`č, ć, đ, š, ž`). Naslovi odredjenih poglavlja, rijeci i/ili izraza, zbog lakse pretrage i razumijevanja mogu biti napisani i na engleskom jeziku.

## Upotrebljene konvencije  
Za pisanje dokumentacije koristimo **Markdown sintaksu**. Za detaljnije informacije o Markdown sintaksi pogledajte [ovaj link](https://guides.github.com/features/mastering-markdown/). 

Kada zelimo da privucemo paznju na odredjeni dio teksta, mozemo ga oznaciti sa `**bold**` ili `*italic*` stilom.

Kada pisemo komande, naredbe, putanje oznacavamo ih sa \`backticks\` stilom npr: `ls -la`.

Kada pisemo blokove koda koristimo \`triple backticks\` stil npr:
```
#!/bin/bash
# Ovo je komentar
echo "Hvala za vas doprinos ovom repozitoriju!"
```

## Struktura repozitorija
Repozitoriji je organizovan u dvije glavne cjeline:
- **DevOps Mentorship Program** - ovdje se nalaze biljeske sa predavanja, zadaci za zadacu, materijale za dodatno ucenje i sve ono sto prolazimo i ucimo tokom DevOps Mentorship Programa. Struktura direktorija ja sljedeca:
```
-devops-mentorsip-program
|- 02-february # mjesec u kojem je predavanje odrzano
|  |- week-1-020123 # broj sedmice u kojoj je predavanje odrzano sa datumom u formatu DDMMYY
|  |  |- 00-class-notes.md # biljeske sa predavanja
|  |  |- 01-homework.md # zadaci za domacu zadaću
|  |  |- 02-additional-reading.md # dodatni materijali za dodatno ucenje
```

- **DevOps Engineering Learning Path** - Na internetu postoji veliki broj razlicith materijala, **Learning Paths**, **mind maps** za ucenje AWS-a, DevOps alata i tehnologija. Ne ulazeci u kvalitetu tih materijala i njihov sadrzaj, autori ovog repozitorija i mentori DevOps Mentorship Programa su pretocili svoje iskustvo iz prakse i rada sa pojedincima na njihovom putu ucenja i stasavanja u DevOps inzinjere kako bi kreirali jedinstveni **Learning Path** na jezicima koji se govore na prostoru Balkana i tako olaksali put svima onima koji zele da se bave ovim poslom. Sadrzaj cjelokupnog **Learning Path-a** je dostupan na sljedecem linku: [DevOps Engineering Learning Path](/table-of-contents.md). Dokumenti koji se nalaze unutar ove cjeline ce predstavljati nadopunu onoga sto smo radili na predavanjima za **DevOps Mentorship Program**.

## Kako da kreirate Pull Request
1. Kreirajte novi **fork** ovog repozitorija. Kliknite na dugme "Fork" na vrhu stranice da biste kreirali kopiju repozitorija u vašem računu na GitHub-u.
2. Klonirajte **fork** repozitoriji na vaš lokalni računar. Kliknite na dugme **Code** i kopirajte SSH adresu do vaseg forka. Nakon toga u terminalu izvrsite naredbu `git clone <URL>`. Npr. `git clone https://github.com/vase-github-korisnicko-ime/devops-aws-mentorship-program.git`
3. Kada ste klonirali fork repozitorija, kreirajte novu granu sa nazivom koja odgovara izmjenama koje zelite da napravite. Npr. `git checkout -b fix-typo-in-contributing-md`
4. Napravite izmjene koje zelite da dodate u repozitorij. Npr. `vim CONTRIBUTING.md`
5. Kada ste napravili izmjene, dodajte ih u **staging**. Npr. `git add CONTRIBUTING.md`
6. Kada ste dodali izmjene u **staging**, napravite **commit**. Npr. `git commit -m "Fix typo in CONTRIBUTING.md"`
7. Kada ste napravili **commit**, push-ujte izmjene na GitHub. Npr. `git push origin fix-typo-in-contributing-md`
8. Kada ste push-ovali izmjene na GitHub, kreirajte **Pull Request**. Kliknite na dugme **Compare & pull request** a zatim na dugme **Create pull request**.
9. Kada kreirate **Pull Request**, dodajte opis izmjena koje ste napravili. Npr. "Fix typo in CONTRIBUTING.md"
10. Kada ste dodali opis izmjena, kliknite na dugme **Create pull request**.
11. Postaljite vas **Pull Request** na pregled od strane mentora ovog programa unutar jednog od Slack kanala i tagujte nekog od mentora.

**NAPOMENA:** Vodite racuna da remote repo koji ste koristili za kreiranje **fork** repozitorija bude **up-to-date** sa originalnim repozitorijem. Kada kreirate **Pull Request**, provjerite da li ima novih izmjena u originalnom repozitoriju. Ako ima, napravite **pull** iz originalnog repozitorija i **merge** sa vasim **fork** repozitorijem. Npr. `git pull upstream master` i `git merge upstream/master`. Nakon toga push-ujte izmjene na GitHub. Npr. `git push origin fix-typo-in-contributing-md`

Vise informacija o tome kako da koristite **fork** repozitorije mozete pronaci na sljedecim linkovima:
- [Learn how to contribute to a project through forking](https://docs.github.com/en/get-started/quickstart/contributing-to-projects)
- [Step-by-step guide to contributing on GitHub](https://www.dataschool.io/how-to-contribute-on-github/)
- [Keeping your fork up to date](https://garrytrinder.github.io/2020/03/keeping-your-fork-up-to-date)
- [Configuring a remote repository for a fork](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/working-with-forks/configuring-a-remote-repository-for-a-fork)
- [Sync a fork of a repository to keep it up-to-date with the upstream repository](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/working-with-forks/syncing-a-fork)
- [Creating a pull request from a fork](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request-from-a-fork)
