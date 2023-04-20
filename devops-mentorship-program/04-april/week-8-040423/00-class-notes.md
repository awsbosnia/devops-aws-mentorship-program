# 📝 Week 8 - Class notes
#### 📅 Date: 04.04.2023.

## Amazon Elastic Load Balancer
**Elastic Load Balancer (ELB)** vrsi automatsku distribuciju dolazeceg saobracaja ka **ciljanoj grupi** - **target groups**, koju mogu ciniti EC2 instance, *containers* i sl. a sve s ciljem postizanja visoke dostupnosti aplikacije u vise razlicitih *Availability zona* i sto boljim performansama. U daljem tekstu, kao sadrzaj target grupe koristice se EC2 instance.

ELB vrsi stalnu provjeru dostupnosti tzv. **health check** nasih EC2 instanci unutar target grupe.
**Health check** su periodicni zahtjevi upuceni ka EC2 instancama. Onog trenutka kada jedna od instanci prestane sa radom, ELB je oznacava kao `unhealthy` i prestaje sa prosljedjivanjem sadrzaja ka toj instanci. U zavisnosti od konfiguracije, ELB moze pokusati da uradi `restore` kako bi instanca bila vracena u stanje `healthy` poduzimanjem odredjenih akcija ili ce istu terminirati i zamijeniti je novom instancom.
Postavljanjem ELB-a ispred naših EC2 instanci, pravimo dodatni vid zaštite podataka, spriječavamo nedozvoljene pristupe i eventualne gubitke podataka. 

U zavisnosti od potreba aplikacije  možemo koristiti jednu od 3 vrste load balancer-a.

### Application Load Balancer

**Application Load Balancer (ALB)** koristimo u slučaju kada radimo sa protokolima Aplikacijskog sloja OSI modela npr. kada trebamo prosljedjivati HTTP/HTTPS zahtjeve upucene nasoj aplikaciji.

![Application Load Balancer](files/alb-tg.png)

Upoznajmo se sa pojomovima:


* `listener` komponenta ALB koja slusa dolazeci saobracaj na definisanom `port`-u, kao sto je `port 80` za HTTP i `port 443` za HTTPS saobracaj. Moze biti konfigurisan na nacin da sadrzi vise `listener rules` za rutiranje saobracaja ka `target grupama`
* `Listener Rule` set pravila/uslova koja je potrebno ispuniti kako bi se saobracaj proslijedio odredjenoj `target grupi`
* `target group` ciljna grupa ili logicka cjelina EC2 instanci kojima ce ALB prosljedjivati svoje zahtjeve
* `target` EC2 instanca koja je registrovana unutar `target grupe`

Kada klijent posalje zahtjev ka ALB, `listener` osluskuje na unaprijed definisanom portu za protokol. Zahtjev koji stigne na navedeni port, obradjuje se u skladu sa uslovima u `listener rules` i ako ispunjava uslove, ALB ce proslijediti saobracaj odgovarajucoj `target grupi` koja sadrzi registrovane `targets` tj. EC2 instance koje prihvataju saobracaj. 


Sam pojam **load balancing** govori nam da se radi o balansiranju dolazećeg saobraćaja, za šta svi load balancer-i koriste odgovarajuće algoritme. Izbor pravog balancing algoritma direktno utice na performanse nase aplikacije. Najcesce koristeni su **Round-Robin** i **Least Outstanding Request**. 

**Round-Robin**
* najjednostavniji *sheduling* algoritam koji jednako distribuira dolazeće zahtjeve ka svim registrovanim EC2 instancama unutar `target group` vrseci ciklicne prolaze kroz EC2 instance

Na dijagramu ispod, prikazano je kako round-robin algoritam  prosljedjuje zahtjeve. U pocetku vidimo 5 EC2 instanci gdje svaka dobija po jedan zahtjev, a nakon što se doda nova, 6 EC2 instanca ona će dobiti zahtjev i krug se nastavlja dalje.
![ALB-round-robin](files/alb-rr-gif.gif)

**Round-Robin pros | cons**

:white_check_mark: Jednostavan i lak za razumijevanje
:white_check_mark: Sve `targets` dobijaju jednak broj zahtjeva
:x: `targets` moraju biti iste velične i sličnih performansi
:x: Zahtjevi moraju imati isti `load` i `latency` što često nije moguće jer API dozvoljavaju više request metoda. Na primjer, `POST` metodi treba više vremena za procesuiranje podataka nego `GET` metodi.

**Least Outstanding Request (LOR)**
* *sheduling* algoritam koji provjerava trenutni `workload` svake EC2 instance unutar `target group` i bira onu instancu koja ima trenutno najmanji workload tj. najmanj broj neodgovorenih zahtjeva (*eng. outstanding request*) te moze obraditi dolazeci zahtjev najprije. 

Dijagram pokazuje kako LOR algoritam prosljedjuje zahtjeve. Prolazenjem kroz sve EC2 instance, provjerava se koja ima najmanji workload. U ovom slucaju je to EC2 instanca `TASK 2`. U slucaju kada se doda nova instanca, ista ce da primi sve dolazece zahtjeve dok ne bude jednaka po workload-u sa ostalim instancama tj. dok se ne postigne balans.

![ALB-round-robin](files/alb-lor-gif.gif)

**LOR pros | cons**

:white_check_mark: Redukuje kasnjenje tj. `latency`
:x: problem kod dodavanja novih instanci, jer moze doci do `flood`- previse zahtjeva za obradu po instanci
:x: Health check moraju da se rade dovoljno brzo kako bi sprijecili da instanca koja ce uskoro biti terminirana, uspije dobiti zahtjev jer ima najmanji workload i brze odgovara

### Network Load Balancer
**Network Load Balancer (NLB)** funkcionise na transportnom nivou OSI modela i prosljedjuje TCP/UDP saobracaj registrovanim `targets` kao sto su EC2 instance, *containers* i IP adrese, uspostavljanjem TCP/UDP konekcije izmedju klijenta i `targets`
* koristi se u slucaju kada imamo dio resursa na on-permises data-centru sto zahtjeva upravljanje TCP i UDP saobracajem
* NLB je u mogucnosti da podrzi milione zahtjeva po sekundi, s jako malim kasnjenjem 

### Gateway Load Balancer
**Gateway Load Balancer (GWLB)** radi na mreznom nivou OSI modela i omogucava izvrsavanje i skaliranje **3rd party appliances** - firewalls, intrusion detection systems, prevention systems

GLB ima dvije glavne komponente:
* **Gateway Load Balancer endpoints (GWLBE)** smjestenom u VPC-u u kojem zelimo pratiti saobracaj i moze se dodati u `route table` kao `next hop`. Uloga GWLBE jeste da preusmjeri paket ka GWLB koji ce daljim koracima proslijediti paket na provjeru. Takodje, ako je paket siguran za prosljedjivanje serveru tj. nasoj aplikaciji, GWLB ce paket vratiti ka GWLBE koji paket proslijedi dalje ka ALB-u a on dalje ka `target group`. 
* **Gateway Load Balancer (GWLB)** enkapsulira sav saobracaj koji ide od GWLBE ka `targets` koristeci **[GENEVE](https://datatracker.ietf.org/doc/html/rfc8926)** protokol.
**GENEVE** protokol omogucava da paketi ostanu nepromjenjeni, sa istim source IP, destination IP kao kada su i kreirane i poslate. To omogucava da security appliances urade pravu provjeru paketa podataka, izvrse analize i prilagode pakete po potrebi. 
## Amazon EC2 Auto Scaling
**Amazon EC2 Auto Scaling** pomaze nam da u svakom trenutku imamo dostupan odgovarajuci broj EC2 instanci kako bismo omogucili normalno funkcionisanje i visoku dostupnost aplikacije.
* **Auto Scaling group (ASG)** je kolekcija EC2 instanci, čiji broj odredjuje velicinu ASG-a na nacin da podesimo parametre:
`minimum number` kao najmanji broj instanci koje ASG moze imati, a to je po preporuci **2 EC2 instance**. 
`desired number` je broj EC2 instanci koje zelimo imati kreirane odmah na pocetku i cilj je odrzavati ovaj broj konzistentnim sto je vise moguce. Ovaj broj mora biti u rasponu **[ minimum number - maximum number )**
`maximum number` najveci broj instanci koje mozemo imati i ujedno maksimalna velicina ASG

![Auto Scaling](files/asg-graph.png)


### Launch Template
Prije kreiranja Auto Scaling group potrebno je da kreiramo **Launch Template** sto predstavlja jedan vid sablona koji sadrzi informacije poput - ID AMI image, instance type u nasem slucaju `t2.micro`, key pair za login, security groups, EBS volume za podizanje EC2 instanci. 
Za razliku od `Launch Configuration`, `Launch Template` omogucava kreiranje vise verzija template-a.

### Auto Scaling Policies 
**Auto Scaling Policies** predstavljaju unaprijed definisane korake koji se izvrsavaju ako je neki od naznacenih uslova ispunjen, sto se odnosi na automatsko podizanje ili terminiranje EC2 instanci tzv. skaliranje kapaciteta Auto Scaling grupe.

U primjeru zadatka za TASK 7 upoznali smo se sa **Dynamic scaling policies** koji daje instrukcije EC2 Auto Scaling da prati naznacenu Cloud Watch metriku i definise koje akcije je potrebno preduzeti u slucaju kada CloudWatch Alarm bude u stanju  `Alarm`. 
EC2 Auto Scaling se brine o tome da kapacitet ASG nikada ne bude manji od `minimum size limit` i veci od `maximum size limit`. 
Kapacitet se mjeri na dva nacina: 
1. **Capacity in terms of instances** gdje se radi sa brojem instanci koje se dodaju ili terminiraju
2. **Capacity units ( instance weighting is applied)** kada se radi o tezini tj. unaprijed definisanim vrijednostima kapaciteta `vCPU` i `Memory`. 
Vise o *instance weighting* procitati [ovdje](https://docs.aws.amazon.com/autoscaling/ec2/userguide/ec2-auto-scaling-mixed-instances-groups-instance-weighting.html)

**Tipovi Dynamic scaling policies**
1. **Target tracking scaling** - povecava i smanjuje trenutni kapacitet ASG na osnovu Cloud Watch metrike i `target value`. 
Na primjer, kao `target metric` mozemo postaviti `CPUUtilization` i staviti `50%` sto znaci da kada CPU bude preko 50% potrebno je da scaling policy poveca broj instanci unutar ASG kako bi se CPU smanjio i odrzavao na vrijednosti sto blizoj 50%. Suprotno, ako CPU padne ispod 50%, potrebno je terminirati instance do broja oznacenog kao `minimum number`. 
2. **Step scaling** - povecava i smanjuje trenutni kapacitet ASG na osnovu `step adjustments`. Definisemo speficicni prag tj. `treshold` za metriku, gdje je svaki `treshold` povezan sa specificnim scaling podesavanjem, kao sto je podizanje ili terminiranje instanci. 
Na primjer, ako bismo `treshold` postavili `70% CPU utilization` desice se `trigger` tj. okidac koji ce da poveca broj instanci. Ako postavimo jos jedan `treshold` na `80% CPU utilization` ako CPU nastavi da raste, kada dostigne `80%` podici ce se jos jedna instanca.
3. **Simple scaling** - definisemo fiksnu kriticnu vrijednost `single scaling adjustment`koja ce dovesti do povecanja ili terminiranja instanci sa mogucnoscu definisanja `cooldown perioda` izmedju svake scaling aktivnosti.

Kako bismo pokrili vise scenarija, moguce je da ASG ima vise scaling policy-a. 
U slucaju kada dva scaling policy-a dodju u konflikt tj. situaciji kada jedan scaling policy radi **scale in** i povecava broj instanci, a drugi radi **scale out** i smanjuje broj instanci - EC2 Auto Scaling bira policy koji ce zadovoljiti oba uslova ili ostaviti najveci broj instanci unutar ASG.  

 
## 📹 Session recordings
- [**WEEK-8-tier-1-group-1 video session recording**](https://youtu.be/RHxli_5ya98)
- [**WEEK-8-tier-1-group-2 video session recording**](https://youtu.be/CybYyZlCNAU)

## 📖 Reading materials
- [📙 Elastic Load Balancing FAQs](https://aws.amazon.com/elasticloadbalancing/faqs/)
- [📙 Network Load Balancer](https://docs.aws.amazon.com/elasticloadbalancing/latest/network/introduction.html)
- [📙 Gateway Load Balancer](https://docs.aws.amazon.com/elasticloadbalancing/latest/network/introduction.html)
- [📙 Dynamic scaling policy](https://docs.aws.amazon.com/autoscaling/ec2/userguide/as-scale-based-on-demand.html)
- [📹Cantril Gateway Load Balancer](https://www.youtube.com/watch?v=GZzt0iJPC9Q)

[:fast_forward: Class Notes](/devops-mentorship-program/04-april/week-8-040423/00-class-notes.md)
[:fast_forward: Additional Reading](/devops-mentorship-program/04-april/week-8-040423/02-additional-reading.md)
[:fast_forward: HOME - README.md](../../../README.md)
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)
