# AWS Cloud Practicioner - Exam Notes

Ovdje mozete pronaci biljeske sa ispita ljudi koji su vec izasli na ispit i polozili ga. Ideja ovih biljeski je da vam daju uvid u tip pitanja / servise koje trebate poznavati kako bi uspjesno polozili ispit.   

## Exam Date April 2022

1. Integrisanje on premisess infrastrukrure na AWS - direct connect
2. Kad je cijena glavni kriterij, na šta se obraća pažnja kod pohrane podataka - odabrati odgovarajuci s3 tier
3. Kompanija želi u infrastrukturi samo resurse koji se mogu replicirati globalno 
4. Najisplativiji način prenosa podataka (50TB) 
5. Koji od 6 pillars definira otpornost arhitekture na razne vidove katastrofa
6. Da bi sprijecili trajni gubitak podataka slucajnim  brisanjem ili overwrite , šta koristiti- versioning
7.  Koji servis stiti od ddos napada
8. Koji support plan koristiti ako zelimo dostupnu pomoc 24/7 i downtime ne smije biti iznad 30 min 


1.Kompanija hoce da deplojuje aplikaction sa relaticonom bazom na aws.Aplikaciski leyer zahtjeva pristup operativnom sistemu base da bi pokrenuo skripte pa da se koji deployment se treba koristit za bazu.
	ponudjeno:dynamodb,rds,ec2 i msm da je s3 
	Tacan odgovor ec2.

2.Koji od ponudjenih servisa treba da pecuje operativni sistem korisnik.
	ponudjeno: ec2,dynamodb,lambda,fargate
	odgovor: ec2
3.Bilo je par pitanja o shared resposiblity i Customer vs AWS
	Shared resposability je sledece kako sam ja ucio:
	patch managment,awarnes & Training,Configuration managment i Security Comlience
	AWS:Protection of Infrastrcuutre,Compute Capacity Avalibility,NTP
	Customer:Clientside/server side encyptiyon,System Patching,network and Firewall,Network Traffic protection,Security Traning

4.Za sta se koristi AWS Artificat,

5.Kompanija koristi MySQL bazu u AWS i baza se mora lako skalirati i imati automatic backup enabled.
	Ponudjeno:DynamoDB,athena, Aurora i neka glupost zadnja je bila
	Tacan odgovor Aurora
6.Korisnik hoce da launchuje tri instance EC2 iza ELB koji deployment treba koristit.
	Bilo je razlitih deplyomenta ali tacan je bio da pokrene instance preko par AZ u jednom regionu
7.Bilo je pitanje oko troskova pretezno Cost Explorer da se prepozna.
8.Bilo je oko S3 da se izabere dobar tier
9.Bilo je pitanja oko planova tipa ako treba 1h response koji izabrati tier(business)
10.BIloo je pitanje oko GuardDuty
11.Bilo je pitanje za koordinirate taskove koji servis koristis AWS SWF je tacan
12.Bilo je pitanje Koji servis moze d anapravi aplikaciju brzo sa pre configurisanim resursima
	Tacan odgovor: Lightsail
13.Billo je pitanje oko DDOS protekcije koji servis koristi AWS SHIELD.
14.Bilo je pitanje da se izaberu iz 5 ponudjenih odgovora koji su well arhitected Frameworci:
	Opertional Exelence,Security,Reliablity,PErfomance Efficenncy,Cost Optimization,Sustanibility su tacni pa ako znas sve lako proberes.
15.Bilo je pitanje oko VPC Peeringa  da se repozna kad koristit ga
16.Bilo je pitanje koji servis koristi machine learning,Tacna odgovor SageMaker
17.Bilo je pitanje koji tip cloud computing je Elastnic Beanstalk(Paas tacan)
18.Bilo je par pitanja o IAM grupama da se prepozna kad koristi
19.Bilo je par pitanja o Iam roles kad koristit zbog sigurnosti.
20.Bilo je pitanje o ETL a tacan odgovor jje AWS Glue
21.BIlo je pitanje na kojem nivou radi Elastic Load balancer (7)
22.Bilo je pitanje razlika izmedju edge lokacije i AZ
23.Bilo je pitanje o AWS Configu
24.Preko cega se moze pristupit SOc2 reportima AWs Insepctor odgovor tacan
25.Koji servis moze pokrenuti managed instance chiefs i pupeta (opsWork)
26.Par pitanja o SNS 
27.I pitanje o Golden Images koji servisi ga mogu koristi,EC2,RDS,EBS je tacna odgovor
28.i B ilo je o Snowball uredjajima sta koristimo za exobytes prenos podataka odgovor tacan je snowmobile a za petabytes ne snowball.
29.BIlo je ponudjeno par pitanje o Agility,elasticity,high availability i Realibility


## Exam date: March 2022.

Firma ima bazu podataka on prem. Prebacuje se na cloud.
Treba prebaciti 60 TB koji servis ce za to iskoristiti.

Sta koristimo kako bi pratili troskove i predvidili budget za nasu kompaniju,

Well architected framework pitanja

Zasto sluzi internet pristup VPC

SQS servis
S3 scenario pitanje koju vrstu odabrati ako je cost eff a pristup podataka je sad i odma

Aplikacija se treba vrtiti 1 godinu koju vrstu resursa koristiti da bude cost eff

EC2 instance kako ih rasporediti da bude visoka dostupnost globalno

Kakav benefit imaju korisnici koji prelaze sa on spot infrastructure na cloud

U sklopu cega je VPC

VPN

Sta spada pod cloud watch

Sta je vezano za cloud trail

Firma zeli da ima sto manji downtime i da pruza svoje usluge max moguce cemu ona tezi
 Inbound outbound rules
Lambda
Shared responsibility model 


## Exam date: 2020.

Kada želi da expanduje sa jedne u drugu Regional zone, kako treba započeti taj proces?
Tačan odgovor: Treba premjestiti jednu AZ u drugi region
Netačani odgovori: Potpisati ugovor, Javiti se Account menadžeru…

Koji servisi ublažavaju DDoS napad?
Tačan odgovor (od ponuđenih): AWS WAF i CloudFront

Za koliko će se vremena naplatiti rad EC2 instance sa AMD jezgrom ako je korištena 3 sata, 5 minuta i 6 sekundi?
Tačan odgovor: 3 sata i 6 minuta (Jer se računa po minutu)

Kako se naziva infrastruktura koja se nalazi u jednom području geografski su udaljene infrastrukture ali povezane visoko frekventnim mrežnim kablovima, imaju nezavistan rad jedna od druge, kao što su izlaz na Internet, struja i slično.
Tačan odgovor: AZ

Kada enterprise korisnik koji želi da kreira hibridni cloud, koji servis će najbolje služiti ako želi dedicated mrežnu konekciju između njegovog dana centra i AWS infrastrukture?
Tačan odgovor: AWS DirectConnect

Ukoliko posumnjate da je neki korisnik kompromitovan, koje dvije stvari ćete uraditi?
Tačan odgovor: Zamjeniti ključeve i kontaktirati AWS Support

Netačni odgovori: Obrisati sve log-ove iz CloudTrail servisa, isključiti MFA

Koji servis ćete koristiti ukoliko želite sa on-premise dana centra migrirate exabyte podatke na cloud storage?
Tačan odgovor: Snowmobile

AWS Certified Cloud Practitioner Exam Guide-pages-543-579,585-607.pdf - Google Drive
Link sa dva testa po 65 pitanja i odgovorima
