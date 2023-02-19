# Uvod u Git - Biljeske

Ispod mozete pronaci kratke biljeske iz knjige [**Uvod u Git autora Tome Krajine**](/resources/books/github_knjiga_tomo_krajina.pdf). Navedena knjiga predstavlja sjajan izvor inofrmacija i moze posluziti kao odican resurs za ucenje o Git alatu. 

- [📖 1 UVOD](#1-uvod)

- [📖 2 VERZIONIRANJE KODA](#2-verzioniranje-koda-i-osnovni-pojmovi)

- [📖 3 INSTALACIJA, KONFIGURACIJA I PRVI PROJEKT](#3-instalacija-konfiguracija-i-prvi-projekt)

- [📖 4 SPREMANJE IZMJENA](#4-spremanje-izmjena)

- [📖 5 GRANANJE](#5-grananje)

- [📖 6 PREUZIMANJE IZMJENA](#6-preuzimanje-izmjena-iz-jedne-grane-u-drugu) 

- [📖 7 ISPOD HAUBE](#7-ispod-haube) 

- [📖 8 POVIJEST](#8-povijest) 

- [📖 9 UDALJENI REPOZITORIJI](#9-udaljeni-repozitoriji) 

- [📖 10 HIGIJENA REPOZITORIJA](#10-higijena-repozitorija) 

- [📖 11 PRIKAZ GRANA U GIT ALATIMA](#11-prikaz-grana-u-git-alatima) 

- [📖 12 ČESTA PITANJA](#12-česta-pitanja) 

- [📖 13 MANJE KORIŠTENE NAREDBE](#13-manje-korištene-naredbe) 

- [📖 14 DODACI](#14-dodaci) 

- [📖 15 TERMINOLOGIJA](#15-terminologija) 

# 📖 1 UVOD

- Git je alat koji je razvio Linus Torvalds da bi mu olakšao vođenje jednog velikog i kompleksnog projekta - Linux kernela.

- Neke od platformi koje koriste Git u pozadini i kreirane su za hosting projekata: GitHub, Google Code, Bitbucket, Sourceforge, Micrsoft CodePlex.

# 📖 2 VERZIONIRANJE KODA I OSNOVNI POJMOVI

## Šta je verzioniranje koda?

- Svaka aplikacija koja ima stvarnog korisnika kojemu rješava neki stvarni problem ima i korisničke zahtjeve koje ne možemo predvidjeti u trenutku kada krenemo pisati program, što dovodi do toga da je potrebno kreirati par verzija kako bi zadovoljili korisnikove zahtjeve. 

- Kada korisnik odluči da mu trenutna verzija ne odgovara, vraćamo se korak unazad i započinjemo novu verziju, odnosno granu projekta te nastavljamo projekt sa tom izmjenom, što je samo jedan od mogućih scenarija.

## Linearno verzioniranje koda

- Linearno verzioniranje je idealna situacija u kojoj tačno znamo kako aplikacija treba izgledati. LVK nalazimo u situacijama kada nemamo kontrolu nad time koja je verzija programa instalirana kod klijenta. 

## Grafovi, grananje i spajanje grana

- U knjizi su korišteni grafovi kao ilustracije. Svaka tačka na grafu je stanje projekta i svaki graf pokazuje stanje povijesti projekta sa izmjenama na čvorovima. Pored toga prikazane su glavna i eksperimentalna grana. Na svim grafovima glavna grana je donja.

- Jedna od velikih prednosti gita je lakoća stvaranja novih grana i preuzimanje izmjena iz jedne u drugu granu. Tako je programerima jednostavno pristupiti problemu sa dva različita načina gdje bi napravili zasebnu granu za svaki pristup.

- Druga velika prednost čestog grananja je kad dodaje se neka nova funkcionalnost koja zahtijeva puno izmjena koje ne želimo odmah stavljati u glavnu granu programa.

- Kad završimo novu funkcionalnost, u glavnu granu preuzimamo sve izmjene iz sporedne. Na taj način često imamo više grana pored glavne i sporedne - grane za različite funkcionalnosti, eksperimente, posebne grane gdje isprobavamo izmjene koje su napravili drugi programeri, za ispravljanje bugova itd. 

## Mit o timu i sustavima za verzioniranje

- Argumenti kojim se ruši mit da sustavi za verzioniranje koda su potrebni samo kad na nekom projektu radi više ljudi. 

# 📖 3 INSTALACIJA, KONFIGURACIJA I PRVI PROJEKT

## Instalacija

[Link prema oficijelnom git sajtu.](http://git-scm.com/download)

## Prvi git repozitorij

- Svaki direktorij (ili folder) može postati git repozitorij. 

- Kreiranje novog direktorija i git repozitorija u istom:
```
    $ mkdir moj-prvi-projekt

    $ cd moj-prvi-projekt

    $ git init
```

- Sa `git init` kreiran je novi direktorij `.git` u kojem se čuva cijela povijest, sve grane, čvorovi i komentari, apsolutno sve vezano za repozitorij.

## Git naredbe

- Git naredbe uvijek imaju sljedeći format:
`git <naredba> <opcija1> <opcija2>`

- Za svaku git naredbu možemo dobiti help section sa:
`git help <naredba>`

- npr. `git help config`

## Osnovna konfiguracija

- `git config` se koristi za postavljanje konfiguracije.

- Postavke mogu biti:
	- globalne - vezane uz korisnika na računalu.
	- `git config --global <naziv> <vrijednost>`
	- spremljene u `.gitconfig` u `home` direktoriju.

	- lokalne - vezane uz jedan jedini projekt.
	- `git config <naziv> <vrijednost>`
	- spremljene u `.git` direktorij gdje se nalazi naš repozitorij.

- Kako bi drugi korisnici na projektu znali ko je pravio commitove na kodu trebamo postaviti ime i email adresu koja će u povijesti biti zapamćena uz svaku spremljenu izmjenu:

	- `git config --global user.name "Ana Anić"`
	- `git config --global user.email "ana.anic@privatna.domena.com"`

- Sa `color.ui` možemo podesiti da ispis git naredbi bude obojan:

	- `git config --global color.ui auto`

- Sa `merge.tool` određujemo koji će program se koristiti u slučaju konflikta (o tome više kasnije).
	
	- `git config --global merge.tool gvimdiff`

## .gitignore

- U datoteci `.gitignore` ispisujemo sve ono što smatramo da ne treba biti dio povijesti projekta. Koristimo `#` za upisivanje komentara u `.gitignore`.

	- `# Vim privremene datoteke:`
	- `*.swp`

# 📖 4 SPREMANJE IZMJENA

- Kada smo prvi put inicirali projekt sa `git init` dodali smo nekoliko datoteka i spremili ih. Trenutak kad se odlučimo spremiti novo stanje projekta u naš repozitorij se naziva `commit`. Svoje lokalne promjene commitamo odnosno spremamo u lokalni repozitorij na našem računalu. 

## Status

- `git status` se koristi da bismo provjerili imamo li uopšte nešto za spremiti.

```
$ git status
# On branch master
nothing to commit (working directory clean)

```

- Da smo napravili tri izmjene na projektu, npr. izmijenili datoteke README.md, setup.py te obrisali TODO.txt, u outputu, tj. dijelu output-a `git status` bi pokazao:

```
modified: README.md
deleted: TODO.txt
modified: setup.py

```
- Najbitniji podatak je sa linije u kojim piše `modified:` i `deleted:` jer to su datoteke koje smo mijenjali ali ne još commitali.

- `git diff` koristimo ako želimo pogledati koje su tačno razlike u tim datotekama u odnosu na stanje kakvo je snimljeno u zadnjoj verziji u repozitoriju.


- primjer: `diff --git a/README.md b/README.md`

- U output-u, linije koje počinju sa `diff` govore o kojim datotekama se radi.
- Linije koje započinju sa `-` (crveno) su linije koda koje su obrisane.
- Linije koje počinju sa `+` (u plavom) su one koje su dodane.
- Rezultat `diff` naredbe su samo linije koda koje smo izmijenili i nekoliko linija oko njih. Ako želimo npr. 10 linija oko izmijenjenih dijelova koda:

	- `git diff -U10`

## Indeks

- Treba imati na umu da git ne čuva datoteke kao nekakav apstraktni pojam nego kao stanja, odnosno verzije datoteka. Za jednu te istu datoteku git čuva njena različita stanja onako kako se mijenjala kroz povijest. 

- Poseban 'međuprostor' u gitu u koji se 'stavljaju' datoteke koje ćemo commitati se naziva `index`.

- Postoje tri različita mjesta u kojim se čuvaju konkretna stanja pojedinih datoteka:

	- Git repozitorij čuva različita stanja iste datoteke ili povijest datoteke.
	- Radna verzija repozitorija.
	- Poseban međuprostor za commit - index, gdje privremeno spremamo trenutno stanje datoteka prije nego ih commitamo.

- U literaturi ćemo često naći i naziv `staging area` ili `cache`. 

- `git status` je naredba namijenjena pregledavanju statusa indeksa i radne verzije projekta. 

	- `git status`

- Ako je stanje na radnoj verziji projekta potpuno isto kao u zadnjoj verziji git repozitorija, `git status` će imati output da nemamo ništa za commitati. 
- U suprotnom reći će koje datoteke su izmijenjene, a na nama je da sad u undeks stavimo samo one datoteke koje ćemo u sljedećem koraku commitati.

## Spremanje u indeks

- Recimo da smo izmijenili datoteku `uvod.tex`. Nju možemo staviti u indeks sa:

	- `git add uvod.tex`

- U ispisu će biti dio koda gdje piše `Changes to be committed:` i tu nalazimo popis datoteka koje smo stavili u indeks. Nakon toga spremni smo za commit.

- Ako spremamo cijeli direktorij datoteka:

	- `git add naziv_direktorija/*`

- Ili ako želimo dodati apsolutno sve što se nalazi u našoj radnoj verziji:

	- `git add .`

## Micanje iz indeksa

- Da maknemo datoteku iz indeksa:

	- `git reset HEAD -- <datoteka1> <datoteka2> ...`

- Događat će nam se situacija da smo promijenili neku datoteku, no kasnije zaključimo da ta izmjena nije bila potrebna. I sad je ne želimo spremiti nego vratiti u prethodno stanje:

	- `git checkout HEAD -- <datoteka1> <datoteka2> ...`

## O indeksu i stanju datoteka

- Ako želimo osvježiti indeks sa zadnjom verzijom datoteke:

	- `git add <datoteka>`

- Ukratko, indeks je prostor u kojeg spremamo stanja datoteka, takav skup datoteka treba predstavljati neku logičku cjelinu koju ćemo spremiti u repozitorij. To spremanje je jedan commit.

## Prvi commit

- Izmjene spremamo sa:

	- `git commit -m "Nova verzija"`

- U stringu nakon `-m` moramo unijeti komentar uz svaku promjenu i možemo provjeriti status projekta sa `git status`.

## Datoteke koje ne želimo u repozitoriju

- Ako smo greškom spremili datoteku koja tmo ne treba biti, međutim ne želimo je obrisati sa našeg diska nego samo ne želimo njenu povijest u repozitoriju, prvo je trebamo dodati u `.gitignore`, međutim ona je i dalje u repozitoriju. Primjer sa test.pyc, postupak je:

	- `git rm --cached test.pyc`

- Naredba `git rm --cached` sprema 'obrisano stanje' datoteke u indeks. Sad tu izmjenu treba commitati da bi git znao da od ovog trenutka nadalje datoteku može obrisati iz svoje povijesti.
- `git status` će se ponašati kao da datoteka ne postoji.


## Povijest projekta

- Naredbu `git log` koristimo da pogledamo sve prethodne commitove.

	- `git log`

- Bitno je znati da u gitu svaki commit ima jedinstven string koji ga identificira. Taj string ima 40 alfanumeričkih znakova.

## Ispravljanje zadnjeg commita

- Sa gitom nove izmjene možemo dodati u već postojeći commit:

	- `git commit --amend -m 'Nova verzija, promijenjen README.md'`

- Ovaj `--amend` gitu govori da izmijeni zadnji commit u povijesti tako da sadrži i izmjene koje je već imao i izmjene koje smo upravo dodali. Sa `git log` možemo provjeriti šta se dogodilo.

	- `git commit --amend` nam omogućava da u zadnji commit dodamo neku datoteku ili čak i maknemo datoteku koju smo prethodno commitali. 

## Git gui

- Naredbom `git gui` otvara se user interface. Više o tome na str. 36;

## Clean

- Naredba `git clean` služi da bi iz radnog direktorija obrisali sve one datoteke koje nisu dio trenutne verzije repozitorija, što je korisno kad želimo obrisati privremene datoteke koje su rezultat kompajliranja ili privremene direktorije.

- Za spisak o tome šta će biti izbrisano koristimo naredbu `git clean -n`. Ako dodamo `-x` naredba će ukloniti i datoteke koje su popisane u `.gitignore`. Dakle, prvo:

	- `git clean -n`, pa zatim:

	- `git clean -f` da se brišu datoteke.

- Možemo obrisati privremene datoteke ili 1 direktorij sa:

	- `git clean -f -- <direktorij>`


# 📖 5 GRANANJE

## Popis grana projekta

- Jedna od velikih prednosti gita je što omogućuje jednostavan i brz rad sa višestrukim granama. 

- Naredbu `git branch` koristimo ako želimo vidjeti koje točno grane našeg projekta trenutno postoje. Primjer sa jednom granom:

```
    $ git branch
    * master
``` 

- Svaki git repozitorij u početku ima jednu jedinu granu i ona se uvijek zove master (ili main). Asterisk ili zvjezdica označava granu na kojoj se trenutno nalazimo.

## Nova grana

- Naredbom `git branch <naziv_grane>` dodajemo novu granu.

## Prebacivanje sa grane na granu

- Naredbom `git checkout <naziv_grane>` se prebacujemo na novu granu. Analogno, na glavnu granu se vraćamo sa `git checkout master`.

- Kada se prebacimo na novu granu, možemo uredno commitati svoje izmjene i sve što tu radimo neće biti vidljivo na `master`grani.

## Prebacivanje na granu i tekuće izmjene

- Postoji nekoliko situacija u kojima git neće dopustiti prebacivanje. Najčešća je kada u dvije grane imamo dvije različite verzije iste datoteke, a tu smo datoteku u tekućoj grani izmijenili i ostavili necommitanu.

- U praksi najbolje je prebacivati se sa grane na granu tek nakon što smo commitali sve izmjene. 

## Brisanje grane

- Naredbom `git branch -D <naziv_grane>` brišemo granu. Naredbom `git branch` provjeravamo je li grana uklonjena.

- Primijetimo samo da ne možemo obrisati granu na kojoj se trenutačno nalazimo.

## Preuzimanje datoteke iz druge grane

- Relativno česta situacija je da želimo preuzeti samo 1 ili više datoteka iz druge grane ali ne želimo preći na drugu granu. To možemo sa:

	- `git checkout <naziv_grane> -- <datoteka1> <datoteka2> ...`

- Primjer - ako smo na master grani a treba nam .classpath datoteka iz eksperiment grane:

	- `git checkout eksperiment -- .classpath`

# 📖 6 PREUZIMANJE IZMJENA IZ JEDNE GRANE U DRUGU

- Termin merge u gitu se koristi kada izmjene iz jedne grane preuzmemo u drugu granu. Nakon mergea dvije grane nastavljaju svoj život - jedino što se sve izmjene koje su do tog trenutka rađene u jednoj grani preuzimaju u drugu granu.

## Git merge

- Recimo da sve izmjene iz grane eksperimentalna-grana želimo u master grani. To se radi naredbom `git merge` a da bi to napravili trebamo se nalaziti u onoj grani u koju želimo preuzeti izmjene - u našem slučaju master grani i tada pišemo:

	- `git merge eksperimentalna-grana`

- Rezultat naredbe je rekapitulacija procesa preuzimanja izmjena npr. koliko linija je dodano, obrisano, koliko datoteka je dodano itd.
- Ako je `git merge` prošao bez grešaka, to automatski dodaje novi commit u grafu, ne mora se 'ručno' commitati.

- Rezultat mergeanja:
	- u eksperimentalnoj grani smo izmijenili datoteku, u master nismo - izmjene iz eksperimentalne će dodati se u master.
	- u eksperimentalnoj grani smo dodali datoteku - ta datoteka će biti dodana u master.
	- u eksperimentalnoj grani smo izbrisali datoteku - datoteka će biti izbrisana u glavnoj.
	- u eksperimentalnoj grani smo izmijenili i preimenovali datoteku, a u master samo izmijenili - ako izmjene u kodu nisu bile konfliktne onda u master grani datoteka će preimenovati se i sadržavati će izmjene iz obje grane.
	- u eksperimentalnoj grani smo obrisali datoteku, u glavnoj je izmijenili - konflikt.

## Što se dogodi kad ..

- U slučaju da imamo konflikt `git merge` naredba će izbaciti u crveno obojan dio za kojeg git ne zna kako ga mergeati. Sa `HEAD` je označeno stanje iz trenutne grane.

- `git mergetool` je program sa kojim lakše rješavamo konflikte.
- Git sam po sebi nema ugrađenih alata za vizuelni prikaz i rješavanje konflikata, ali postoje zasebni programi u tu svrhu koje možemo postaviti kao zadani merge.tool alat:

	- `git config --global merge.tool /putanja/do/programa`

- Jedan od primjera je vimdiff tool. Nakon što se riješi konflikt, izmijenjene datoteke treba commitati.

## Merge, branch i povijest projekta

- U praksi svaka od grana ima svoju povijest, a kako se povećava broj grana tako organizacija projekta postaje veći izazov, zato programeri grane koje više ne koriste brišu.

- Tu može doći do problema, npr. eksperimentalna grana ima svoju povijest, a u trenutku mergea sve izmjene iz te grane su se 'prelile' u master granu. Postoje situacije u kojima moramo razgranatu povijest svesti na linearnu. To možemo riješiti sa nečim što se zove `rebase`. Da bi bolje shvatili šta znači rebase, treba objasniti fast forward - posebnu vrstu mergea.

## Fast forward

- Smisao mergeanja je u tome da neke izmjene iz jedne grane preuzmemo u drugu. Međutim, iako u primjeru imamo dvije grane - one čine jednu crtu - imaju jednu linearnu povijest koja se proteže kroz obje grane. U slučaju da se i obriše eksperimentalna grana - npr. varijanta, cijela njena povijest se nalazi u master.

- Git sam odlučuje je li potrebno izvršiti fast-forward merge i izvršava ga. Želimo li ga izbjeći, pišemo:

	- `git merge --no-ff varijanta`

## Rebase

- Rebase je način da pomaknemo mesto od kud smo granali neku granu.
- Radi se na sljedeći način - trebamo biti postavljeni u grani koju želimo 'pomaknuti'. Zatim koristimo naredbu `git rebase <grana>`, gdje je <grana> ona grana na kojoj kasnije treba izvršiti fast-forward. Želimo li granu test 'pomaknuti' na kraj grane master - tj. izvršiti rebase:

	- `git rebase master`

- Ovdje mogu nastati problemi slični klasičnom mergeu, npr. konflikt. Nakon što se konflikt ispravi, nastavljamo dalje:

	- `git rebase --continue`

- I poslije toga smo slobodni izvesti merge koji će u ovom slučaju sigurno biti fast-forward.
- Ako rebase ima previše konflikata - možda se odlučimo na prekid rebasea i vraćanje repozitorija u stanje prije nego što smo ga pokrenuli:

	- `git rebase --abort`

## Rebase ili ne rebase?

- Ukoliko želimo jednostavniju (linearnu) povijest projekta radit ćemo rebase, ali trebamo na umu imati da rebase mijenja povijest projekt. Nekoliko nepisanih pravila kad se rebase može raditi:

	- Rebase radite samo ako su svi commitovi koji će biti mijenjani naši vlastiti commitovi.
	- Ako je ikako moguće, rebase raditi na branchevima koje još nismo pushali na udaljene repozitorije.

## Rebase i rad na standardnim sustavima za verzioniranje.

- Postoji jedan način korištenja gita u kojem je rebase nužan - to je ako koristimo git kao CVS, subversion ili TFS klijent.

## Cherry-pick

- Dodatna posebna vrsta mergea. Primjer ako želimo `merge` samo jednog određenog commita, taj commit ima svoj string koji ga određuje, prvo pronađemo taj string:

	- `git log eksperimentalna-grana`

- u logu se vidi string. Prebacimo se u granu u koju želimo preuzeti samo izmjene iz tog commita i izvršimo sljedeću naredbu:

	- `git cherry-pick <commit>`

## Merge bez commita

- Ako se odlučimo da ne želimo rebase - u povijesti ćemo imati puno grana i čvorova u kojima se one spajaju. Bilo bi lijepo kad bi umjesto Merge branch 'eksperimentalna-grana' imali smisleniji komentar koji bi bolje opisao ono što smo tačno napravili u toj grani.

- To može na način da merge izvršimo sa:

	- `git merge eksperimentalna-grana --no-commit`

- Na taj način će merge se izvršiti ali neće se sve commitati. Sad možemo commitati sa svojim komentarom. Detalj na kojeg treba pripaziti je što, ako je došlo do fast-forward mergeanja, onda `--no-commit` nema efekta, zato je bolje koristiti sljedeću sintaksu:

	- `git merge eksperimentalna-grana --no-ff --no-commit`


## Squash merge

- Detaljno objašnjenje ima u knjizi na str. 64, ali koliko sam razumio ako određeni commit ima dva prethodnika i želimo da taj commit ima izmjene iz grane eksperimentalna-grana, ali ne želimo da isti ima referencu na tu granu, to dobijemo sa:

	- `git merge --squash eksperimentalna-grana`

## TAGOVI

- Tag je jeda od načina klasifikacije dokumenata gdje standardni način je hijerarhijsko klasificiranje.

- U gitu radimo sa poviješću projekata pa ćemo tu povijest i tagirati. Preciznije, tagirati ćemo čvorove našeg grafa povijesti projekta - commitove. Tag mora biti jedinstven i jednom iskorišteni tag se ne može više koristiti. 

- Kao što znamo, u gitu svaki commit ima svoj identifikator. Međutim taj string (od 40 alfanumeričkih znakova) je nama ljudima besmislen. Nama su smisleni komentari uz kod, međutim oni nisu jedinstveni. 

- Dakle, tag je ništa drugo nego neki kratki naziv za određeni commit, drugim riječima tag je alias za neki commit. Sa tagovima radimo uz naredbu git tag gdje dobijemo popis svih definiranih tagova.

- Sljedećom naredbom dodajemo novi tag:
	
	- `git tag testni-tag`

- ovom naredbom brišemo prethodni tag:

	- `git tag -d testni-tag`

- Da vratimo projekt privremeno na stanje kad smo definirali tag 1.0:

	- `git checkout 1.0`

- Sad ovdje možemo stvoriti novu granu sa:

	- `git branch <grana>`

- ili vratiti projekt u zadnje stanje sa:

	- `git checkout HEAD`

# 📖 7 ISPOD HAUBE

## Kako biste vi ...

- Teorija o tome kako bi dizajnirali i implementirali vlastiti sustav za verzioniranje koda. Opširnije na str. 68;

- Takozvane delte su izmijenjene datoteke.

## SHA1

- `SHA1` spada pod hash funkcije, kao argument uzima string i iz njega izračunava drugi string dužine 40 znakova. 

- Primjer: `974ef0ad8351ba7b4d402b8ae3942c96d667e199`.

- `SHA1` ima sljedeća svojstva:

	- Nije jedinstvena. 
- Kada se dobije rezultat funkcije, npr. `974ef0ad8351ba7b4d402b8ae3942c96d667e199`, iz njega je nemoguće izračunati string iz kojeg je nastala.

- Git nije ništa drugo nego graf `SHA1` stringova, od kojih svaki jedinstvenon identificira neko stanje projekta i izračunati su iz tog stanja.

- Budući da je svaki commit `SHA1` sadržaja projekta u nekom trenutku, kad bi netko htio neopaženo promijeniti povijest projekta morao bi i njegov `SHA` identifikator.

## Grane

- Na osnovu samo jednog commita moguće je saznati cijelu povijest neke grane, dakle, dovoljno nam je imati reference na zadnje commitove svih grana u repozitoriju, da bi mogli saznati povijest cijelog projekta. Zato gitu grane i nisu ništa drugo nego reference na njihove zadnje commitove.

## Reference

- U gitu se može slobodno koristiti i samo prvih nekoliko znakova SHA1 referenci umjesto cijelog 40-znamenkastog stringa, što znači da:

	- `git cherry-pick 974ef0ad8351ba7b4d402b8ae3942c96d667e199`

- je isto kao:

	- `git cherry-pick 974ef0`

## HEAD

- `HEAD` je referenca na trenutni commit. Obično je to zadnji commit u grani u kojoj se nalazimo, ali može biti i bilo koji drugi. Ukoliko pokazuje na commit koji nije zadnji u grani - onda se kaže da je repozitorij u detached HEAD stanju.

- Ukoliko nam treba referenca na predzadnji commit, mogli bi pogledati git log i tamo naći njegov SHA1. Postoji i lakši način: HEAD~1. 
- Predzadnji commit je HEAD~2 itd.

- Primjer, ako se prebacimo na stanje u predzadnjem commitu, to možemo napraviti sa: 

	- `git checkout HEAD~1`

- za git smo sada u detached HEAD stanju, smijemo čak i commitati ali te izmjene neće biti dio ni jedne grane, ako ih trebamo sačuvati jednostavno kreiramo novu granu sa git branch i repozitorij više neće biti u detached HEAD stanju, a izmjene koje smo napravili su sačuvane u novoj grani.

- Da provjerimo izmjene koje su se desile između sadašnjeg stanja grana i stanja od prije 10 commitova:

	- `git diff HEAD~10 HEAD`

## .git direktorij

- U ovom direktoriju imamo:

	- `.git/config` gdje su spremljene sve lokalne postavke.
	- `.git/objects` koji sadrži sve verzije svih datoteka i svih commitova našeg projekta. Postoje 4 vrste objekata: commit, tag, tree i blob.
	- Commit i tag sadrže metapodatke vezane uz ono što im sam naziv kaže. Blob sadrži binarni sadržaj neke datoteke, dok je tree popis datoteka.
	- .git/refs sadrži datoteke sa referencama na sve grane, tagove i grane udaljenih repozitorija koji se nalaze u .git/objects direktoriju.

## HEAD 

- Datoteka `.git/HEAD` u stvari nije obična datoteka nego samo simbolički link na neku od datoteka unutar `.git/refs`, konkretno na jednu od onih datoteka koja sadrži referencu na granu u kojoj se trenutno nalazimo.

## .git/hooks

- Direktorij koji sadrži shell skripte koje želimo izvršiti u trenutku kada se dogode neki važni događaji na našem repozitoriju.

# 📖 8 POVIJEST

- Naredbom `git log` možemo vidjeti povijest commitova grane u kojoj se trenutno nalazimo međutim imamo par detaljnijih naredbi za proučavanje povijesti poput:

## Diff

- Naredba git diff nam ispisuje razliku između radne verzije repozitorija - tj. stanja projekta kakvo je trenutno na našem računalu i zadnje verzije u repozitoriju. 

	- `git diff`

- Drugi način korištenja je provjera razlike između dva commita:

	- `git diff master testna-grana`

- Da provjerimo izmjene između predzadnjeg i pred-predzadnjeg commit-a:

	- `git diff HEAD~2 HEAD~1`

- ili između pred-predzadnjeg i sadašnjeg:

	- `git diff HEAD~2`

- ili izmjene između 974ef0ad8351ba7b4d402b8ae3942c96d667e199 i testna-grana:

	- `git diff 974ef testna-grana`

## Log

- Naredbom `git log <naziv-grane>` dobiti ćemo kratak ispis povijesti te grane.

- Za povijest grane - bez zadnjih 5 unosa, radimo referencu na peti commit unazad:

	- `git log HEAD~5`

- Za povijest grane testna-grana bez zadnjih 10 unosa:

	- `git log testna-grana~10`

- Želimo li povijest samo nekoliko zadnjih unosa, npr. 10:

	- `git log -10 testna-grana`

- ili samo za trenutnu granu:

	- `git log -10`

## Whatchanged

- Naredba `git whatchanged` je vrlo slična `git log`, jedino što uz svaki commit ispisuje i popis svih izmijenjenih datoteka.

	- `git whatchanged`

## Pretraživanje povijesti

- Dva kriterija za pretraživanje. 

- Prvi prema tekstu komentara uz commitove:

	- `git log --grep=<regularni-izraz>`

- Npr. tražimo sve commitove koji u commit komentarima sadrže riječ graph:

	- `git log --grep=graph`

- Drugi scenarij je odgovor na pitanje - kad se u kodu prvi put spomenuo neki string? 

	- `git log -S<string>`

- Npr. ko je prvi napisao funkciju get_image_x_size:

	- `log -Sget_image_x_size`

- Za pretraživanje stringa sa razmacima:

	- `git log -S"get image width"`

- Kako pogledati što se točno desilo u određenom commitu:

	- `git 76cf8`


### Gitk

- `gitk` je pomoćni programčić koji nam grafički prikazuje povijest trenutne grane. Pokreće se:

	- `gitk`

- Za povijest određenog commita:

	- `gitk 974ef0ad8351ba7b4d402b8ae3942c96d667e199`

- Za povijest određene grane:

	- `gitk testna-grana`

- Za povijest svih grana:

	- `gitk --all`

# Blame

- Naredbom `git blame <datoteka>` dobiti ćemo ispis neke datoteke sa detaljima o tome tko, kad i u kojem commitu je napravio ili izmijenio pojedinu liniju u toj datoteci.

	- `git blame __init__.py`

- U svakom projektu datoteke mijenjaju imena. Tako da kod koji je pisan u jednoj datoteci ponekad završi u datoteci nekog trećeg imena. Ukoliko želimo znati i u kojoj su datoteci linije naše trenutne datoteke prvi put se pojavile:

	- `git blame -C <datoteka>`

## Digresija o premještanju datoteka

- Bitna napomena - git ima ugrađenu heuristiku koja prati je li datoteka u nekom commitu preimenovna. Ukoliko u novom commit pronađe da je jedna datoteka obrisana - proučiti će koje datoteke su u istom commitu nastale. 
- Ako se sadržaj poklapa u dovoljno velikom postotku linija, git će sam zaključiti da se radi o preimenovanju datoteke a ne o datoteci koja se prvi put pojavljuje u repozitoriju. 
-  To je princip na osnovu kojeg git blame -C 'zna' u kojoj datoteci se neka linija prvi put pojavila. Zato možemo koristiti naredbe mv ili move.

	- `git mv <stara-datoteka> <nova-datoteka>`

## Preuzimanje datoteke iz povijesti

- Recimo da verzija od prije 5 commitova je bila bolja nego ova trenutno, kako da je vratimo iz povijesti i commitamo u novo stanje projekta?

- Koristimo `git checkout` naredbu koja se osim za prebacivanje sa grane na granu može koristiti i za preuzimanje neke datoteke iz prošlosti:

	- `git checkout HEAD~5 -- pjesma.txt`

- Ako nam treba ta datoteka kakva je bila u predzadnjem commitu grane test:

	- `git checkout test~1 -- pjesma.txt`

## 'Teleportiranje' u povijest

- Otkrili smo bug, ne znamo gdje točno u kodu, ali znamo da se taj bug nije prije manifestirao. Međutim, ne znamo točno kada je bug započeo.

- Da 'teleportiramo' cijeli prijekat na neko stanje kakvo je bilo prije n commitova:

	- `git checkout HEAD~10`

- U suštini vraćamo se unazad sve dok ne nađemo trenutak (commit) u kojem je bug stvoren.

- Kada nađemo taj commit, tu možemo kreirati novu granu sa git branch ili se vatiti na najnovije stanje sa: 

	- `git checkout HEAD~20`

- ako buga sad nemamo, znamo da se pojavio negdje između dvadesetog i desetog zadnjeg commita. 

## Reset

- Da uklonimo zadnji commit:

	- `git reset --hard HEAD~1`

- Da se vratimo na 974ef0ad8351ba7b4d402b8ae3942c96d667e199 i uklonimo sve commitove koji su se desili nakon njega:

	- `git reset --hard 974ef0a`

- Cijela poenta naredbe git reset je da pomiče HEAD - tj. referencu na zadnji commit u trenutnoj grani. 

## Revert

- Svaki commit mijenja povijest projekta tako da izmjene dodaje na kraju grane.

- Recimo da je commit neispravan i  treba vratiti na prethodno stanje - revertati samo zadnji commit:

	- `git revert HEAD`

- Da revertamo sa SHA1 referencom commita:

	- `git revert 402b8ae39`

## Izrazi sa referencama

- `HEAD` je referenca na trenutni commit, znači `HEAD` je isto što i master.

- `HEAD~1` je referenca na predzadnji a `HEAD~2` referenca na pred-pred-zadnji.

- Znak `~` je operacija između reference na commit i nekog broja 'n' a rezultat je commit koji se desio n koraka u povijest.

- Te operacije možemo i konkatenirati, dakle `HEAD~2~3` je ekvivalentno `HEAD~5`.

- Druga korisna operacija nad commitovima je `^`. Za razliku od ~ koja ide na n-ti korak prije trenutnog, `^` nam daje n-tog roditelja.

- I ovdje isto možemo konkatenirati operacije:

	- `master~1` je predzadnji commit.
	- `master~1^1` je isto što i `master~2`
	- `master~1^2` je isto što i `HEAD~1^2`
	- `master~1^2` je isto što i `HEAD~1^2`

- Gdje god neka git naredba traži referencu na commit, možemo koristiti sljedeće izraze umjesto SHA1 string:

	- `gitk master~1^2` ili
	- `git cherry-pick master~3`

- Često nam treba odgovor na pitanje šta smo commitali prije mjesec dana i sl., za to se koristi @ sa opisnom oznakom vremena, npr.

	- `git log master@{"1 day ago"}`
	- `git log ebab9a46829b8d19ebe1a826571e7803ae723c3b@{"5 moths ago"}`
	- `git log branch@{2010-05-05}`

## Reflog

- Reflog je povijest svih commitova na koje je pokazivao HEAD. S naredbom:

	- `git reflog`

- možemo vidjeti SHA1 identifikatore svih commitova na kojim je vaš repo bio do sada.

- Dakle, svaki put kad se prebacimo na novu granu, ili neki commit, git pamti gdje smo tačno bili, ukoliko smo obrisali neki branch i kasnije shvatili da to nismo htjeli, njegovi commitovi su vjerovatno još uvijek u lokalnom repozitoriju.

- Sa ovom naredbom ih možemo naći i iz njih ponovo napraviti novu lokalnu granu.

# 📖 9 UDALJENI REPOZITORIJI

## Naziv i adresa repozitorija

- Prva stvar koju ćemo obraditi je kloniranje udaljenog repozitorija. Npr. adresa udaljenog repozitorija ove knjige je:

	- https://tkrajina@github.com/tkrajina/uvod-u-git.git

- Svaki udaljeni repozitorij sa kojim ćemo imati posla mora imati i svoje kratko ime - alias. Nešto kao origin ili vanjin-repozitorij i sl. Nazivi su naš slobodan izbor.

- Na neke udaljene repozitorije ćemo slati svoje izmjene - ako imamo ovlasti, a sa nekim ćemo izmjene preuzimati u svoj repozitorij.

## Kloniranje repozitorija

- Kloniranje je postupak sa kojim kopiramo cijeli repozitorij sa udaljene lokacije na naše lokalno računalo. 

- Kopirati repozitorij je jednostavno, dovoljno je u neki direktorij kopirati .git direktorij drugog repozitorija, onda na novoj kopiranoj lokaciji izvršiti:

	- `git checkout HEAD`

- Pravo kloniranje je za nijansu drugačije. Recimo da kloniranje je kopiranje udaljenog repozitorija, ali tako da novi lokalni repozitorij ostaje svjestan da je on kopija nekog udaljenog repozitorija. Klonirani repozitorij čuva informaciju o repozitoriju iz kojeg je kloniran. Ta informacija će mu kasnije olakšati da na udaljeni repozitorij šalje svoje izmjene i od njega preuzima izmjene. 

- Ova naredba:

	- git clone git://github.com/tkrajina/uvod-u-git.git

- će kopirati projekt, zajedno sa cijelom poviješću na naše računalo.

## Struktura kloniranog repozitorija

- Od trenutka kad smo klonirali svoj repozitorij - za nas postoje dva repozitorija. Naš dio svijeta su samo ova dva sa kojima direktno imamo posla. Jedan je udaljeni kojeg smo klonirali, drugi je lokalni koji se nalazi pred nama.

- Naredbom:
	- `git branch -a`

- ispisujemo sve grane koje su nam trenutno dostupne u lokalnom repozitoriju.

- Novost ovdje je `remotes/origin/master`.

- Ovo remotes znači da iako imamo pristup toj grani na lokalnom repozitoriju, ona je samo kopija grane master u repozitoriju origin. 

- Imamo dva repozitorija - lokalni i udaljeni. Udaljeni ima samo granu master, a lokalni ima dvije kopije te grane.

- U lokalnom master ćemo mi commitati naše izmjene a u origin/master se nalazi kopija udaljenog origin/master u koju nećemo commitati.

- Ovaj origin/master ćemo s vremena na vrijeme osvježavati tako da imamo ažurno stanje udaljenog repozitorija.

## Djelomično kloniranje povijesti repozitorjia

- Naredba `git clone` može potrajati dosta dugo, posebno ako repozitorij sadrži sve commitove koji mogu sezati do preko 10 godina unazad.

- Želimo li skinuti projekt samo zato da bi pogledali njegov kod a ne zanima nas cijela povijest - moguće je klonirati nekoliko zadnjih commitova sa:

	- `git clone --depth 5 --no-hardlinks git://github.com/tkrajina/uvod-u-git.git`

- Svrha djelomičnog kloniranja je da bismo skinuli kod nekog projekta samo da ga proučimo a ne da bi se na njemu nešto ozbiljno radilo.

## Fetch

- Git je zamišljen kao sustav koji ne zahtijeva stalni pristup internetu. U većini operacija od nas se očekuje da iniciramo interakciju sa drugim repozitorijima.

- Kao što smo mi inicirali kloniranje, tako i mi moramo inicirati ažuriranje grane origin/master sa `git fetch` naredbom.

	- `git fetch`

- `origin/master` je osvježen tako da mu je stanje isto kao i master udaljenog repozitorija.

- Sa `origin/master` možemo raditi skoro sve kao i sa ostalim lokalnim granama. Možemo npr. pogledati njegovu povijest sa:

	- `git log origin/master`

- Možemo pogledati razlike između njega i naše trenutne grane:

	- `git diff origin/master`

- U `origin/master` ne bi smjeli commitati ali ipak jedna radnja koju trebamo raditi sa origin/master je da izmjene iz nje preuzimamo u naš lokalni master, možemo se prebaciti sa njega koristeći:

	- `origin/master`

- I sad smo tek u master dobili stanje udaljenog master. Općenito to je postupak kojeg ćemo često ponavljati:

	- `git fetch`

- da bismo osvježili svoj lokalni origin/master, gdje možemo malo proučiti njegovu povijest i izmjene koje uvodi u povijest i potom da bi te izmjene unijeli u naš lokalni repozitorij:

	- `git merge origin/master`

## Pull

- Tipični redoslijed naredbi koje ćemo izvršiti svaki put kad želimo preuzeti izmjene iz udaljenog repozitorija:

	- `git fetch`
	- `git merge origin/master`

- Gore navedene naredbe se mogu objediniti u:

	- `git pull`

## Push

- Prva radnja sa kojom aktivno mijenjamo neki udaljeni repozitorij, tj. prebacujemo izmjene na udaljeni repozitorij:

	- `git push origin master`

## Push tagova

- Naredbom

	- `git push origin master`

- šaljemo na udaljeni origin repozitorij samo izmjene u grani master. Ponekad želimo s lokalnog repozitorija poslati tagove na udaljeni repozitorij:

	- `git push origin --tags`

- Ako želimo na remote repozitoriju obrisati neki tag:

	- `git push origin :refs/tags/moj-tag`

## Rebase origni/master

- Želimo li da se neki commitovi (primjer f i g) vide u povijesti udaljenog projekta kao zasebni čvorovi:

	- `git checkout master`
	- `git rebase origin/master`
	- `git push origin master`

## Prisilan push

- Nakon što smo proučili ono što je vlasnik udaljenog repozitorija napravio u commitovima, ponekad ćemo zaključiti da to jednostavno ne valja i najradije bi sada pregazili njegove commitove sa našim:

	- `git push -f origin master`

- Ovakvo ponašanje nije uvijek baš poželjno jer:

	- Možda smo pogriješili u svom zaključku.

	- Niko ne voli da mu se pregaze njegove izmjene. Bolje javiti se vlasniku repozitorija i objasniti što ne valja i dogovoriti da on reverta, resetira ili ispravi izmjene.

## Rad sa granama

- Treba imati na umu da kloniranjem dobijamo samo kopiju lokalnog master, dok se sve grane čuvaju pod origin/. Dakle imamo origin/master i origin/grana. Broj i naziv grana možemo vidjeti sa:

	- `git branch -a`

- Remote granu trebamo granati u naš lokalni repozitorij i tek onda sa njom početi raditi. 

	- `git checkout origin/grana`
	- `git branch grana`
	- `git checkout grana`

- Zadnje dvije naredbe smo mogli skratiti u git checkout -b grana.

- Prije push-a:

	- `git fetch`

- za osvježavanje i `origin/master i origin/grana`, zatim:

	- `git merge origin/grana`

- i na kraju:

	- `git push origin grana`

## Brisanje udaljene grane

- Koristimo posebnu varijantu naredbe git push:

	- `git push origin :grana-koju-zelimo-obrisati`

- Isto kao kad pushamo izmjene na tu granu jedino što dodajemo dvotačku ispred naziva grane.

- Ukoliko uz fetch želimo da se brišu sve grane koje je neko drugi obrisao na udaljenom repozitoriju:

	- `git fetch --prune`

## Udaljeni repozitoriji

- Kada kloniramo repozitorij imamo neka ograničenja - najvažnije je to što možemo imati samo jedan origin.

- Šta ako želimo imati posla sa više udaljenih repozitorija? Odnosno, šta ako imamo više programera sa kojim želimo sarađivati a od kojih svako ima svoj repozitorij?

- Drugim riječima sad pomalo ulazimo u onu priču o gitu kao distribuiranom sustavu za verzioniranje.

## Dodavanje i brisanje udaljenih repozitorija

- Primjer rada sa dva programera. Prvi korak koji možemo napraviti je klonirati jedan od njihovih repozitorija:

	- `git clone https://github.com/korisnik/projekt.git`

- Međutim mi želimo imati posla i sa repozitorijem drugog korisnika, za to ćemo i njega dodati kao remote:

	- `git remote add bojanov-repo git@domena.com:projekt`

- I sad imamo dva udaljena repozitorija origin i bojanov-repo. S obzirom da smo drugi nazvali prema imenu njegovog vlasnika, možda ćemo htjeti i origin nazvati tako, npr. Karlin repozitorij:

	- `git remote rename origin karlin-repo`

- Popis svih repozitorija sa kojim imamo posla dobijemo sa:

	- `git remote show`

- Kao i sa origin gdje smo kloniranjem dobili lokalne kopije udaljenih grana, i ovdje ćemo ih imati, ali ovaj put će lokacije biti bojanov-repo/master i karlin-repo/master. Njih dvoje osvježavamo sa:

	- `git fetch karlin-repo`
	- `git fetch bojanov-repo`

- Kad želimo isprobati neke izmjene koje je Karla napravila a nismo ih spremili u naš repo:

	- `git checkout karlin-repo/master`

- Želimo li preuzeti njene izmjene:

	- `git checkout master`
	- `git merge karlin-repo/master`

## Fetch, merge, pull i push sa udaljenim repozitorijima

- Rad sa fetch, merge, pull i push je rad sa udaljenim repozitorijem.

- Specifičnost je što u kloniranom repozitoriju možemo uvijek računati da imamo referencu na origin koju dobijemo pri kloniranju, a druge udaljene repozitorije moramo ručno dodati sa git remote add.

- Primjer, udaljeni repozitorij je ivanov-repo:

	- `fetch ivanov-repo`

- Nakon što osvježimo lokalne kopjie grana u ivanov-repo, merge radimo:

	- `git merge ivanov-repo/master`

- ili

	- `git merge ivanov-repo/grana`

- Pull:

	- `git pull ivanov-repo master`

- ili

	- `git pull ivanov-repo grana`

- Push

	- `git push ivanov-repo master`

- ili

	- `git push ivanov-repo grana`

## Pull request

- Pull request nije ništa drugo nego kratka poruka vlasniku nekog udaljenog repozitrija koja sadržava adresu našeg repozitorija, opis izmjena koje smo napravili i prijedlog da on te izmjene preuzme u svoj repozitorij.

- Github ima automatizirani mehanizam za pull request.

## Bare repozitorij

- Bare repozitorij je posebna vrsta repozitrija koji nema radnu verziju projekta, tj. taj repozitorij nikad nije checkoutan a sadrži samo .git direktorij.

- Bare repozitorij je repozitorij koji je zamišljen da bude na nekom serveru, a ne da se na njemu direktno commita. 

- Drugim riječima, nisu svi direktoriji jednaki, postoje oni lokalni na kojima programiramo i radimo stvari i postoje oni udaljeni na koje pushamo i sa kojih fetchamo i pullamo.

- Konvertirati neki repozitorij u bare je jednostavno:

	- `git config --bool core.bare true`

# 📖 10 HIGIJENA REPOZITORIJA

## Grane

- Iako nam Git omogućava da imamo neograničen broj grana, ljudski um nije sposoban vizualizirati si više od 5 do 10 njih. 

- Nađemo li se sa više od 15 grana sigurno je dio njih tu samo zato što smo ih zaboravili obrisati. Ako nismo sigurni je li nam u nekoj grani ostala možda još kakva izmjena koju trebamo vratiti u master, možemo koristiti naredbu:

	- `git branch --merged master`

- Dobiti ćemo ispis svih grana čije izmjene su u potpunosti mergeane u master. 

- Da dobijemo ispis grana koje nisu u potpunosti mergeane u neku drugu granu:

	- `git branch --no-merged <naziv_grane>`

## Git gc

- gc skraćeno garbace collection koje treba da 'počistimo':

	- `git gc`

- Ovu naredbu treba pokrenuti sa vremena na vrijeme.

## Povijest i brisanje grana

- Postoje dva načina za brisanje grana.
- Prvi koji bezuvjetno briše granu grana:

	- `git branch -D grana`

- Drugi koji će obrisati granu samo ako jest potpuno mergeana:

	- `git branch -d grana`

## Squash merge i brisanje grana

- Želimo li u povijesti projekta sačuvati izmjene iz neke grane, ali ne i njenu povijest, to se može sa:

	- `git merge --squash.`

## Bisect

- Git naredba koja se koristi kad tražimo izmjenu u kodu koja je uzrokovala greške (bug) u programu.

- Trenutno se nalazimo u grani u kojoj imamo bug, gitu dajemo do znanja da želimo započeti bisect i da je trenutni commit loš:

	- `git bisect start`
	- `git bisect bad`

- Prebacujemo se na stanje za koje smo sigurni da se bug nije manifestiralo:

	- `git checkout b9cee8abaf1c6ffc8b7d9bbb63cafb2c0cbdbdd0`

- Dajemo do znanja da je dobar commit:

	- `git bisect good`

- Git nas sad prebacuje na neki commit između ta dva. Nama je irelevantno koji je točno, jedino što trebamo je isprobati pojavljuje li se bug u kodu koji je trenutno checkoutan.

- Ako se bug manifestirao, pišemo:

	- `git bisect bad`

- U sljedećem koraku smo na commit gdje buga nema i pišemo:

	- `git bisect good`

- i dalje:

	- `git bisect good`

- sve dok u nekom trenutku ne dođemo do krivca:

	- `git bisect bad`

- Naprimjer, krivac je commit b87db36d71038074a1c478c9f9a329d5c1685a02 i da bi točno pogledali šta je tada promijenjeno možemo:

	- `git diff b87db36d7~1 b87db36d7`

	- ili

	- `gitk b87db36d7`

- Ukoliko želimo prekinuti bisect i vratiti se na commit gdje smo bili kad smo započeli, naredba je:

	- `git bisect reset`

## Automatski bisect

- Automatizira se  naredbom:

	- `git bisect run <naredba>`

- Pretpostavka je da prethodno je određen bad i good commit.

- Važno je da naredba koju pokrećemo završava sa statusom 0 ukoliko je stanje ispravno ili brojem od 1 do 127 ukoliko nije. 

## Digresija o atomarnim commitovima

- Bisect nam neće otkriti točan uzrok problema, samo će vam reći koje su se izmijene dogodile kad je problem nastao ali i to je često dovoljno kod traženja pravog uzroka.

- Radimo li commitove koji imaju tisuće promijenjenih linija onda je puno teže naći uzrok - ali tu je kriv pristup jer nikad ne bi smjeli snimati više od jedne izmjene u koraku - svaki commit bi trebao predstavljati jednu jedinu logičku cjelinu. 

- Drugim riječima treba raditi atomarne commitove i na taj način izmjene u kodu će uvijek biti male i bisect će nam sa puno većom preciznošću moći pomoći kod traženja uzroka problema.

# 📖 11 PRIKAZ GRANA U GIT ALATIMA

- Tomo ovdje objašnjava na koji način se radi sa `gitk grafičkim alatom`, str. 136 - 140;

# 📖 12 ČESTA PITANJA

- Nekoliko situacija - tj. problemi koji se pojavljuju u radu sa gitom.

## Jesmo li pushali svoje izmjene na udaljeni repozitorij?

- Može se desiti da commitamo lokalno promjene ali ne pushamo na glavni repozitorij.

- Problem je u tome što nije nigdje jasno vidljivo jesu li izmjene iz njegove master grane pushane na udaljeni repozitorij.

- Jednostavan način da se to provjeri je da provjerimo odnos između master i origin/master grane sa:

	- `git fetch`

- Ako je: 

	- master ispred origin/master - imamo više commitova od udaljenog repozitorija i možemo ih pushati.

	- master iza origin/master - imamo manje commitova od udaljenog repozitorija i trebamo ih pokupiti sa udaljenog repozitorija koristeći pull ili rebase.

	- master i origin/master se nalaze na dvije grane koje su međusobno divergirale - u tom slučaju imamo izmjene koje još nismo pushali ali trebamo prije toga napraviti pull.

	- master  origin/master pokazuju na isti čvor - tada je lokalno stanje potpuno isto ko i stanje udaljenog repozitorija.

## Commitali smo u krivu granu

- Slučajno smo commitali u master a trebali smo u unicode-fix granu. Pretpostavimo da su zadnja dva commita iz master ona koja želimo prebaciti u ovu drugu granu.

- Rješenje je jednostavno, prvo se prebacimo na unicode-fix granu:

	- `git checkout unicode-fix`

- Zatim preuzimamo jedan po jedan ta dva commita u trenutnu granu:

	- `git cherry-pick  master~1`
	- `git cherry-pick master`

- Kad smo te commitove prebacilil u željenu granu, trebamo maknuti iz one u kojoj su neželjeni:

	- `git checkout master`

- i obrišemo ih sa:

	- `git reset --hard master~2`

- što tu granu resetira na stanje u `master~2` a to je predzadnji commit.

## Commitali smo u granu X, ali te commitove želimo prebaciti u novu granu

- Commitali smo u master, ali u jednom trenutku smo zaključili da te izmjee ne želimo tu. Želimo stvoriti novu granu koja će nam sačuvati te commitove, a master resetirati na isto stanje kao i u udaljenom repozitoriju. Idemo redom, sa:

	- `git branch nova-grana`

- ćemo kreirati novu granu iz master. Te dvije grane su trenutno potpuno iste, sačuvali smo commitove iz master u drugoj grani.

- S obzirom da nam stanje u master treba biti isto kao u origin/master, prvo ćemo se potruditi da lokalno imamo ažurno stanje udaljenog repozitorija:

	- `git fetch`

- i sad izjednačujemo master i origin/master

	- `git reset --hard origin/master`

## Imamo necommitane izmjene i git nam ne da prebacivanje na drugu granu

- Imamo li necommitanih izmjena, git ponekad neće dopustiti prebacivanje sa grane na granu. To zaobilazimo sa dva načina.

- Prvi način bi bio sa git stash dok drugi je da ipak commitamo. Problem sa ovim pristupom je što ćemo imati djelomični commit sa poluzavršenim kodom, međutim kad se naknadno vratimo na ovu granu - možemo posao završiti i commitati sa:

	- `git commit --amend -m "Novi komentar ...."`

- I osvježili smo prethodni polovični commit.

## Zadnjih n commitova treba "stisnuti" u jedan commit

- Sa git log možemo pronaći SHA1 identifikator zadnjeg commita kojeg želimo ostaviti netaknutog, tj. sve commitove nakon njega želimo stisnuti u jedan commit. Neka je to npr. 15694d32935f07cc66dbc98fdd7b3b248d885492.

- Treba pripaziti da lokalno u repozitoriju nemamo nikakvih necommitanih izmjena i da se nalazimo u pravoj grani a zatim:

	- `git reset --soft 15694d32935f07cc66dbc98fdd7b3b248d885492`

- Git će nas sad vratiti u povijest, ali datoteke će ostaviti u istom stanju u kakvom su bile snimljene. Sad ih možemo commitati iznova i dobiti ćemo što smo tražili.

## Pushali smo u remote repozitorij izmjenu koju nismo htjeli

- Kad smo lokalno napravili izmjenu koju nismo htjeli - možemo koristiti git reset --hard, međutim ako smo našu izmjenu pushali onda je najbolje napraviti:

	- `git revert <nas_commit>`

- Alternativa bi bila:

	- `git reset --hard <commit>`
	- `git push -f origin <grana>`

## Mergeali smo, a nismo htjeli

- Najčešće je dovoljno:

	- `git reset --hard HEAD^1`

- Naravno trebamo provjeriti je li HEAD^1 upravo onaj commit na kojeg želimo vratiti naš branch.

## Ne znamo gdje smo commitali

- Rješenje bi bilo koristit reflog i sa ovom naredbom možemo naći SHA1 izgubljenog commita i napraviti cherry-pick u tekuću granu ili novi branch.

# 📖 13 MANJE KORIŠTENE NAREDBE

- Detaljniji opis naredbi možemo dobiti sa `git help`.

## Filter branch

- Naredba sa kojom se može promijeniti cijela povijest projekta.

	- `git filter-branch`

## Shortlog

- Ispisuje rekapitulaciju commitova prema autoru.

## Format-patch

- koristi se kad šaljemo patch emailom.

	- `git format-patch`

## Am

- Radnja suprotna onome što radimo sa format-patch, primili smo patcheve emailom i sad ih pretvaramo u commitove sa:

	- `git am`

## Fsck

- Provjerava ispravnost nekog objekta ili cijelog repozitorija. 

## Instaweb

- Pokreće jednostavno web sučelje za pregled povijesti repozitorija.

	- `git instaweb`

## Name-rev

- Imamo određeni commit u našem repozitoriju, i ako želimo npr da znamo koji je peti commit verzije 1 ili drugi commit nakon što smo branchali granu test, koristiti ćemo:

	- `git name-rev`

## Stash

- Želimo se prebaciti na drugu granu a imamo tekuće izmjene, koristimo git stash kojim privremeno spremimo izmjene koje smo radili i kad se kasnije vratimo na prvobitnu granu prethodno spremljene izmjene možemo vratiti nazad.

## Submodule

- Koristimo da u svoj repozitorij dodamo neki drugi repozitorij.

	- `git submodule`

## Rev-list

- Daje spisak svih dostupnih commitova za zadani commit objekt.

# 📖 14 DODACI

## Git hosting 

- Github, BitBucket, Google Code, Sourceforge, Codeplex.

## Vlastiti server

- Može se koristiti vlastiti server za hostovanje git koda. 

## Git shell

- Omogućuje korisniku ssh pristup i korištenje naredbi, npr. treba nam ssh pristup na naš vlastiti server ili trebamo omogućiti ssh pristup nekom drugom korisniku koji će da fetcha, pulla ili merga naš kod.

- U drugom slučaju, kada pushamo kod na Github, najjednostavniji način je koristiti ssh preko shell.

## Certifikati

- Praktično, da svaki put ne bi tipkali lozinku, možemo naš javni ssh certifikat kopirati na udaljeno računalo.

- U našem home direktoriju bi trebali imati .ssh direktorij. Ukoliko nije tamo, naredba:

	- `ssh-keygen -t dsa`

- će ga kreirati zajedno sa javnim certifikatom `id_rsa.pub`. Sadržaj ove datoteke trebamo kopirati u ssh authorized keys na udaljenom računalu.

## Git plugin

- Primjer dodavanja novih naredbi na unixoidnim računalima:

```
mkdir moj-git-plugin
cd moj-git-plugin
touch git-gladan-sam
# Tu bi sad trebalo editirati skriptu git-gladan-sam...
chmod +x git-gladan-sam
export PATH=$PATH:~/moj-git-plugin
```

- Posljednju liniju dodajemo u inicijalizacijsku skriptu (.bashrc) tako da bude dostupna i nakon restarta.

## Git i Mercurial

- Razlike između gita i Mercuriala koji je veoma sličan gitu. Više o tome na str. 153.

## 📖 15 TERMINOLOGIJA

- `Bare` repozitorij je repozitorij koji nije predviđen da ima radnu verziju projekta. Njegov smisao je da bude na neko serveru i da se na njega može pushati i sa njega pullati i fetchati.

- `Bisect` je binarno pretraživanje povijesti u potrazu za izmjenama koje su izazvale neku grešku.

- `Branch` je grana.

- `Cherry-pick` je preuzimanje izmjena iz samo jednog commita druge grane.

- `Commit` je spremanje izmjena na projektu u sustav za verzioniranje.

- Čvor je commit, ali koristi se kad se povijest projekta prikazuje grafom.

- `Diff` je pregled izmjena između dva commita ili dvije grane ili dva stanja iste grane.

- `Fast-forward` je proces koji se događa kad vršimo merge dva grafa, pri čemu je zadnji čvor ciljne grane ujedno i točka grananja dva grafa.

- `Fetch` je preuzimanje izmjena (commitova) sa udaljenog repozitorija na lokalni.

- `Log` je pregled izmjena koje su se desile između commitova u nekoj grani. Ili pregled izmjena između radne verzije i stanja u repozitoriju.

- `Indeks` je međuprostor u kojeg spremamo izmjene prije nego što ih commitamo.

- `Pull` je kombinacija fetcha i mergea. S njime se izmjene  sa udaljenog repozitorija preuzimaju u lokalnu granu.

- `Pull request` je zahtjev vlasniku udaljenog repozitorija na kojeg nemamo ovlasti pushati da preuzme izmjene koje smo mi napravili.

- `Push` je slanje lokalnih commitova na udaljeni repozitorij.

- `Radna verzija repozitorija` je stanje direktorija našeg projekta. Ono može i ne mora biti jednako zadnjem snimljenom stanju u grani repozitorija u kojoj se trenutno nalazimo.

- `Rebase` je proces kojim točku grananja jednog grafa pomičemo na kraj drugog grafa.

- `Referenca` je informacija na osnovu koje možemo jedinstveno odrediti neki commit ili granu ili tag.

- `Reset` je vraćanje stanja repozitorija na neko stanje. I to ne privremeno vraćanje nego baš izmjenu povijesti repozitorija pri čemu se briše zadnjih nekoliko commitova iz povijesti.

- `Revert` je spremanje izmjene koja poništava izmjene snimljene u nekom prethodnom commitu.

- `Repozitorij` je projekt koji je snimljen u nekom sustavu za verzioniranje koda. Repozitorij sadržava cijelu povijest projekta.

- `Staging area` je sinonim za indeks.

- `Squash merge` je merge, ali na način da novostvoreni čvor nema referencu na granu iz koje su izmjene preuzete.

- `Tag` je oznaka iliti imenovana referenca na neki commit.

## **Ostale stranice**
- [Class Notes](/devops-mentorship-program/02-february/week-1-140223/00-class-notes.md)  
- [Homework Task](/devops-mentorship-program/02-february/week-1-140223/01-homework.md)  
- [Additional Reading](/devops-mentorship-program/02-february/week-1-140223/02-additional-reading.md)  
