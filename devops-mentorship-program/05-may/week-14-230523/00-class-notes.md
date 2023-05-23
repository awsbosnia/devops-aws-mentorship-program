# 📝 Week 14 - Class notes
## 📅 Date: 23.05.2023.

## Machine Images / Slike Servera
Svaki server kojeg pokrenete bilo unutar on-prem okruzenja ili u cloud okruzenju je baziran na nekoj slici servera (eng. Machine image - Zbog lakseg razumijevanja i ujednacenosti sa standardnom IT terminologijom u nastavku cemo koristiti termin **_Image_**).
Image predstavlja sablon odnosno template koji unutar sebe sadrzi prekonfigurisani operativni sistem i predinstlairane potrebne aplikacije. Image koristite da krairate nove ili klonirate postojece hostove.  U zavisnosti od toga koju platformu koristite Image moze biti u razlicitim formatima. Npr. AWS koristi AMI (Amazon Machine Image) format, dok Azure koristi VHD (Virtual Hard Disk) format, vmWare koristi VMDK (Virtual Machine Disk) format itd. 

![AMI](/devops-mentorship-program/05-may/week-14-230523/files/AMI.jpeg)
### Golden Image
Golden Image predstavlja predifisnisanu i pre-konfigurisani image koji cemo korisiti kao osnovu za sve hostove koje kreiramo. 

### Packer
Packer je besplatni open-source alat, razvijen od komapnije HashiCorp i napisan u programskom jeziku GO, koji se koriste za kreiranje Images. Packer vam dozvoljava da kreirate iste images za razlicite platforme. Npr. mozete kreirati isti image za AWS, Azure, vmWare, VirtualBox itd. Packer je takodje integriran sa razlicitim alatima za konfigurisanje servera kao sto su Ansible, Chef, Puppet, SaltStack itd. Packer koristi JSON konfiguracione fajlove za definisanje sta zelite da se nalazi unutar image-a. Packer je cross-platform alat i moze se koristiti na Windows, Linux i Mac OS operativnim sistemima. Packer mozete koristiti za razlicite scenarije i taskove medjutim dva najecsca razloga zasto da koristite Packer su konzistentnost images koje kreirate i mogucnost integracije u vas CI/CD pipeline.

Korisne packer komande:
```bash
$ packer build -var-file=variables.json template.json
$ packer fmt .
$ packer validate template.json
$ packer build -on-error=ask -debug your-template.json
```
## Configuration Managment / Server Configuration Management / Upravljanje Konfiguracijama Servera
Kako bi bili u mogucnosti da upravljavmo konfiguracijama (eng: configuration management) velikog broja servera na automatizovan, konzistentan, skalabilian i nacin koji mozemo jednostavno da reporoduciramo potreban su nam alati za upravljanje konfiguracijama servera odnosno server konfiguration menadzment alati.
Upravljanje konfiguracijama servera je proces automatskog upravljanja konfiguracijama servera. Ovaj proces se sastoji od dva dijela:
- **Provizioniranje** - Instalacija OS-a i aplikacija na servere
- **Konfigurisanje** - Konfigurisanje aplikacija na serverima

### Imperative vs Declarative
Imperativni i deklarativni pristupi su dvije različite paradigme koje se koriste u upravljanju serverima i automatizaciji. Osnovne razlike su:

- **Imperative** - Imperativni pristup: U ovom pristupu, definirate kako postići željeno stanje. To znači da se fokusirate na korake ili operacije koje trebate izvršiti kako biste postigli željeno stanje. Na primjer, možete reći: "Instalirajte X softver, zatim konfigurirajte Y datoteku na ovaj način, a zatim pokrenite Z servis". Ovaj pristup je korak-po-korak i često uključuje ručnu intervenciju ili niz skripti koje izvršavaju specifične zadatke.

Primjer imperativnog pristupa:  

```bash
$ export PDSH_SSH_ARGS_APPEND="-i /Users/ddzevlan/Downloads/week-8.pem"  # SSH key za AWS instancu
$ pdsh -w ^hosts.txt sudo yum -y install java # hostname komanda na svim serverima
```

- **Declarative** Deklarativni pristup: U ovom pristupu, definisete sta zelite postici, ali ne i kako to postici. Na primjer, umjesto da definisete niz koraka za postavljanje servera, samo definisete zeljeno stanje (npr. "X softver treba biti instaliran, Y datoteka treba izgledati ovako, Z servis treba biti pokrenut"), a alat za upravljanje konfiguracijama (kao što su Ansible, Puppet, Chef ili SaltStack) odgovoran je za utvrđivanje kako postići to stanje. Ako stanje već postoji (npr. ako je softver već instaliran), alat neće ništa raditi; ako stanje ne postoji, alat će poduzeti potrebne korake za postizanje tog stanja.

Deklarativni pristup je često poželjniji u upravljanju serverima i DevOps-u, jer omogućuje veću konzistentnost, pouzdanost i automatizaciju. Također olakšava upravljanje velikim brojem servera, jer samo trebate definirati željeno stanje, a ne brinuti se o pojedinačnim koracima potrebnim za postizanje tog stanja.

### Popularni alati za upravljanje konfiguracijama servera  
Postoji nekoliko popularnih alata za upravljanje infrastrukturom. Evo nekoliko najpoznatijih:

- **Ansible**: Ansible je alat za upravljanje konfiguracijama koji koristi deklarativni pristup i ne zahtijeva instalaciju agenta na ciljanim serverima. Koristi se za automatizaciju postavljanja i konfiguracije servera, aplikacija i drugih IT potreba. Ansible koristi YAML za pisanje skripti, što ga čini lako čitljivim i razumljivim.

- **Puppet**: Puppet je jedan od najstarijih alata za upravljanje konfiguracijama i koristi deklarativni pristup. Puppet koristi vlastiti opisni jezik i zahtijeva instalaciju agenta na svakom serveru koji upravlja.

- **Chef**: Chef je još jedan popularan alat za upravljanje konfiguracijama. Chef koristi Ruby kao jezik za pisanje "receita" (skripti) koje definiraju konfiguraciju sistema. Kao Puppet, Chef također koristi model sa agentom.

- **SaltStack (Salt)**: Salt je sličan Ansible u mnogim aspektima, uključujući upotrebu YAML-a za definiranje konfiguracija. Međutim, Salt može raditi u modu s agentom (kao Puppet i Chef) ili bez agenta (kao Ansible).

### Immutable vs Mutable Infrastructure
Koncepti mutabilne i imutabilne infrastrukture odnose se na način na koji se upravlja promjenama u infrastrukturi. Evo osnovne razlike:

- **Mutable Infrastructure** - Mutabilna infrastruktura: Tradicionalni pristup upravljanju infrastrukturom je mutabilan. To znači da se serveri i aplikacije mijenjaju tijekom vremena. Na primjer, kada se ažurira softver, dodaju se nove značajke ili popravljaju sigurnosni propusti, te promjene se primjenjuju direktno na postojeću infrastrukturu. Ova promjena može biti ručna ili automatizirana (koristeći alate za upravljanje konfiguracijama kao što su Ansible, Puppet, ili Chef), ali rezultat je isti: postojeći entiteti se mijenjaju tijekom vremena.

- **Immutable Infrastructure** - Imutabilna infrastruktura: Sa druge strane, imutabilna infrastruktura tretira infrastrukturu kao nepromjenjivu: jednom kada se entitet (kao što je server ili kontejner) pokrene, on se ne mijenja. Umjesto ažuriranja postojeće infrastrukture, nova verzija infrastrukture se izgrađuje od nule, a zatim zamjenjuje staru verziju. To znači da nema "in-place" ažuriranja, konfiguracijskih promjena, ili popravaka na postojećim entitetima. Kada su potrebne promjene, novi entiteti se stvaraju sa novom konfiguracijom i zamjenjuju stari entiteti. Ovaj pristup često se koristi u kontekstu "infrastructure as code" i DevOps praksi.

Imutabilna infrastruktura može poboljšati pouzdanost i predvidljivost infrastrukture, jer eliminira mogućnost konfiguracijske razlike odnosno "drift" (kada se stanje infrastrukture mijenja u nepredvidljive načine tijekom vremena) i smanjuje utjecaj ljudskih pogrešaka. Međutim, zahtijeva i sofisticiranije alate i procese, kao što su kontinuirana integracija i isporuka (CI/CD), te virtualizacija ili kontejnerizacija.

### Ansible
Ansible je alat za upravljanje konfiguracijama servera koji koristi deklarativni pristup i ne zahtijeva instalaciju agenta na serverima. Ansible koristi YAML za pisanje skripti, što ga čini lako čitljivim i razumljivim. Ansible koristi DSL (Domain Specific Language) 


#### **[Ansible configuration file](https://docs.ansible.com/ansible/latest/reference_appendices/config.html)**   

Datoteka `ansible.cfg` je konfiguracijska datoteka koja se koristi za konfigurisanje različitih opcija i postavki za Ansible. Ova datoteka može biti prisutna u vasem radnom direktoriju ili u `/etc/ansible` direktoriju (globalna konfiguracija).

Konfiguracijski fajl mozete generisati koristeci:
```bash
$ ansible-config init --disabled -t all > ansible.cfg
```

Unutar datoteke `ansible.cfg` mozete definisati različite opcije i postavke koje će utjecati na izvrsavanje Ansible-a.

[defaults] sekcija: Ova sekcija sadrži opće postavke za Ansible, kao što su putanje do inventory fajla, moduli koje koristi, izvršni direktorij i drugo. Primjeri opcija unutar [defaults] sekcije:

inventory: Putanja do inventara koji se koristi kao zadani.
remote_user: Zadani SSH korisnik za konekciju na remote hostove.
module_name: Zadani naziv modula koji se koristi za izvršavanje zadataka.
roles_path: Putanja do direktorija koji sadrži Ansible role.
[privilege_escalation] sekcija: Ova sekcija se koristi za konfiguriranje privilegiranog izvršavanja (escalation) zadatka. Ovdje možete postaviti opcije kao što su become (omogući privilegirano izvršavanje) i become_method (metoda koja se koristi za privilegirano izvršavanje, npr. sudo).

[ssh_connection] sekcija: Ova sekcija se koristi za konfiguriranje SSH veze s ciljnim čvorovima. Ovdje možete postaviti opcije kao što su ssh_args (dodatni argumenti za SSH) i pipelining (omogući ili onemogući pipelining).

[ansible_become] sekcija: Ova sekcija se koristi za konfiguriranje opcija privilegiranog izvršavanja. Ovdje možete postaviti opcije kao što su become (omogući privilegirano izvršavanje), become_method (metoda za privilegirano izvršavanje) i become_user (korisnik koji će postati privilegirani korisnik).

Ovo su samo neke od opcija koje se mogu nalaziti u datoteci ansible.cfg. Možete prilagoditi ove opcije prema svojim potrebama ili dodati druge opcije koje su relevantne za vaše okruženje i konfiguraciju.

Napomena: Ako datoteka `ansible.cfg` ne postoji, Ansible će koristiti zadane vrijednosti i postavke.

### Ansible Playbooks
Ansible Playbook je prvi fajl koji cete napisati kada krenete da radite sa Ansiblom. Playbook je izraz koji se koristi za fajl koji predstavlja skriptu sa izmjenama koje zelimo napraviti. Playbook pisemo u YAML formatu. YAML je fromat slican JSON-u s tim da je lakse za citanje i pisanje.
**NOTE: Validan JSON fajl je validan YAML fajl, ali ne i obrnuto.**

##### Anatomija Ansible Playbook-a  

Ansible Playbook se sastoji od nekoliko elemenata:
- `name` - Komentar koji opisuje i govori sta playbook radi
- `become` - Opcija koja omogucava privilegirano izvrsavanje. Po defaultu je vrijednost `false`. Mozete je promijeniti na `true` ili `yes` sto znaci da ce se komande izvrsavati kao root korisnik. To je losa praksa.
- `hosts` - Lista hostova na kojima ce se izvrsavati Ansible taskovi. Hostovi se mogu navesti u obliku liste ili grupisani.
- `vars` - Varijable koje se koriste u playbooku. Varijable se mogu definisati u samom playbooku ili u posebnom fajlu.
- `tasks` - Lista taskova koji se izvrsavaju na hostovima. Taskovi se izvrsavaju po redoslijedu u kojem su navedeni.

### Ansible Modules
Ansible Modules su skripte koje dolaze zapakovane skupa sa Ansiblom i omogucavaju izvrsavanje razlicitih operacija na ciljnim hostovima. 
Da bi vidjeli dokumentaciju modula izvrsite komandu:
```bash
$ ansible-doc <module_name>
```
#### Ansible Roles
Ansible Roles su najbolji nacin za organizovanje i ponovno koristenje Ansible koda. Ansible role je direktorij koji sadrzi sve sto je potrebno da bi se izvrsio odredjeni zadatak. 

#### Ansible Inventory file
Ansible inventory je datoteka koja sadrži listu hostova na kojima će se izvrsavati Ansible taskovi. Ansible inventory može biti u razlicitim formatima, kao što su INI, YAML ili JSON. Ansible inventory moze sadrzavati i grupisane hostove, sto vam omogucava da grupisete hostove prema razlicitim kreterijima (npr. development, production itd).


#### Ansible Commands
```bash
$ ansible-playbook webserver.yml 
$ ansible-playbook webserver.yml --check # dry run mode
$ ansible-playbook -i inventory/prod.ini  webserver.yml
$ ansible all -a "hostname"
$ ansible-vault encrypt <file>
$ ansible-vault view <file> | ansible-vault edit <file> 
```

### EC2 Image Builder
EC2 Image Builder je AWS-ov servis koji vam omogućuje da automatizirate proces kreiranja, održavanja i ažuriranja sigurnih i upotrebljivih AMI-ja (Amazon Machine Images). EC2 Image Builder vam omogućuje da definišete konfiguraciju operativnog sistema, softvera i ažuriranja paketa, a zatim da kreirate AMI-je na osnovu te konfiguracije. 

### AWS Systems Manager
AWS Systems Manager je AWS-ov servis koji vam omogućuje da centralizirano upravljate vašim AWS resursima. AWS Systems Manager vam omogućuje da automatizirate operacije i upravljate konfiguracijama vasih resursa unutar AWS cloud okruzenja.

## 📹 Session recordings
- [**WEEK-14-tier-1-group-1 video session recording**]()
- [**WEEK-14-tier-1-group-2 video session recording**]()  
  
## 📖 Reading materials  
- [**Golden Image or Foil Ball? (repost)**](https://madstop.com/post/85950592485/golden-image-or-foil-ball-repost)
- [**Packer Docs: Build an Image**](https://developer.hashicorp.com/packer/tutorials/aws-get-started/aws-get-started-build-image)  
- [**Packer Docs: Amazon AMI Builder**](https://developer.hashicorp.com/packer/plugins/builders/amazon)  
- [**Automated, immutable, and declarative**](https://justingarrison.com/blog/2022-11-04-immutable-declarative-automated/)  


[:fast_forward: Class Notes](/devops-mentorship-program/05-may/week-14-230523/00-class-notes.md)
[:fast_forward: Additional Reading](/devops-mentorship-program/05-may/week-14-230523/02-additional-reading.md)
[:fast_forward: HOME - README.md](../../../README.md)
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)
