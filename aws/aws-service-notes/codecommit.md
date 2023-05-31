# Sta je AWS CodeCommit

AWS CodeCommit je 'version control' servis od strane Amazon Web Services (AWS) i koji se moze koristiti za privatno skladistenje i upravljanje resursima (poput dokumenata, source koda i binarnih datoteka) u cloudu.

CodeCommit je sigurna, visoko skalabilna i upravljiva (managed) usluga za kontrolu source koda koja hosta privatne Git repositorije. CodeCommit eliminise potrebu za upravljanjem vlastitim sistemom za kontrolu source koda ili brigu o skaliranju infrastrukture. CodeCommit se moze koristiti za skladistenje svega, od koda do binarnih datoteka. Podržava standardnu funkcionalnost Gita, tako da se bez ikakvih problema integrira sa vec postojecim Git alatima.

Sta sve sa CodeCommitom mozemo procitajte [ovdje](https://docs.aws.amazon.com/codecommit/latest/userguide/welcome.html#welcome-introducing)


#

# CodeCommit, Git, biranje pravog AWS servisa za vase potrebe

Kao servis zasnovan na Gitu, CodeCommit je dobro prilagodjen vecini potreba za verzionim kontrolama. Ne postoje proizvoljna ogranicenja velicine fajla, vrste fajla i velicine repozitorijuma. Međudjim, postoje inherentna ogranicenja Gita koja mogu negativno uticati na performanse odredjenih vrsta operacija. Potencijalno smanjenje performansi repozitorijuma CodeCommita moze se izbjeci tako što ce se izbjegavati njegovo koriscenje za slucajeve u kojima su druge AWS usluge bolje prilagodjene zadatku. Takodjer, mogu se optimizovati performanse Gita za kompleksne repositorije. Evo nekih slucajeva u kojima Git, a samim tim i CodeCommit, mozda nisu najbolje rjesenje, ili gde bismo mozda trebali poduzeti dodatne korake radi optimizacije za Git.

![use-case](/files/1.use-case.png)

# Kako CodeCommit radi?

CodeCommit pruza konzolu za jednostavno kreiranje repositorija i pregled postojećih repositorija i branchova. U nekoliko jednostavnih koraka, korisnik moze pronaci informacije o repozitoriju i klonirati ga na svoj racunar, stvarajuci lokalni repozitorij gdje se mogu izvrsiti promjene a nakon toga pushati u CodeCommit repozitorij. Korisnik/ici mogu raditi sa putem CLI na svojim lokalnim racunarima ili koristiti GUI.

Sljedeća slika prikazuje kako koristiti development masinu, AWS CLI ili CodeCommit konzolu i CodeCommit uslugu za kreiranje i upravljanje repositorijima:

![how-does-codecommit-work](/files/2.how-does-codecommit-work.png)

- Koristite AWS CLI ili CodeCommit konzolu za kreiranje CodeCommit repositorija.

- S vase development masine koristite Git naredbu `git clone`, specificirajuci ime CodeCommit repositorija. Time se stvara lokalni repozitorij koji je povezan s CodeCommit repositorijem.

- Koristite lokalni repozitorij na development masini za izmjenu (dodavanje, uredjivanje i brisanje) datoteka, a zatim pokrenite `git add` da biste lokalno pripremili izmijenjene datoteke. Pokrenite `git commit` da biste lokalno potvrdili izmjene, a zatim pokrenite `git push` da biste poslali datoteke na CodeCommit repozitorij.

- Preuzmite promjene od drugih korisnika. Pokrenite `git pull` da biste sinhronizovali datoteke u CodeCommit repozitoriju s vasim lokalnim repositorijem. Time osiguravate da radite s najnovijom verzijom datoteka.

- Mozete koristiti AWS CLI ili CodeCommit konzolu za pracenje i upravljanje vasim repositorijima.

# Kako je CodeCommit drugaciji od versioniranja fajlova u Amazon S3?

CodeCommit je optimiziran za timski razvoj softvera. On upravlja grupama promjena na više datoteka, sto moze ukljucivati ​​promjene koje se istovremeno dogadjaju s promjenama koje izvrsavaju drugi developeri. Verzioniranje u Amazon S3 omogucuje vracanje prethodnih verzija datoteka, ali nije fokus na znacajke kolaborativnog pracenja datoteka koje su potrebne timovima za razvoj softvera.

# Troskovi

Bilo ko s AWS accountom moze besplatno zapoceti sa servisom AWS CodeCommit. Vaš racun dobiva 5 aktivnih korisnika besplatno mjesecno (unutar ogranicenja), nakon cega placate N/A za svakog dodatnog aktivnog korisnika mjesecno. Nema unaprijed definiranih naknada ili obaveza.

![pricing](/files/3.pricing.png)

**Primjer** 

Ako vas racun ima 6 aktivnih korisnika tijekom odredjenog mjeseca, tada ce vam biti naplacen 1$ (pretpostavljajuci da nema dodatnih naknada ili prekoracenja).

## Dodatni troskovi

Ako vam treba vise pohrane ili Git requesta za korisnike, dodatna pohrana će biti naplacena prema sljedecem:

$0.06 po GB-mjesecu
$0.001 po Git requestu

Vise o troskovima, mozete procitati [ovdje](https://aws.amazon.com/codecommit/pricing/)

# Troubleshooting CodeCommita

[Ovdje](https://docs.aws.amazon.com/codecommit/latest/userguide/troubleshooting.html) mozete pogledati smjernice, odnosno osnovne primjere troubleshootiranja servisa CodeCommit. 