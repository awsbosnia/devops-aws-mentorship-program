# AWS Solution Architect Associate - Exam Notes

Exam date: April 2021
Kreiranje novog računa i zaštita root user-a
(da li se koriste IAM Access Keys, MFA i slično)

EC2:
prepoznati ispravnu naredbu u slučaju da EC2 instanca nema pristup podacima
169.254.169.254/latest/meta-data
169.254.169.254/user-data/iam-info 
EC2 Placement Groups (različite situacije u kojima treba prepoznati kad se koja koristi; visoka propusnost, niska latencija i sl)

S3:
Par pitanja u vezi enkripcije prije nego se podaci pošalju na S3 (encryption at rest)
Prebacivanje između različitih S3 Object Storage Classes 
(primjer o neke količine podataka, kolikdana se koristi učestalo, koliko godina je potrebno sačuvati podatke i slično)

AWS Shield:
za koju vrstu napada se koristi

Exam date: June 2022
-odrediti sta mozemo uraditi u slucaju da nam reserved instance nije vise potrebna
- imamo jednu dx konekciju i 3 peered vpc, na koji nacin uspostaviti najotporniju vezu on premisses sa svim vpc, uzimajuci u obzir da je potrebno da bude sto isplativije rijesenje
- imamo 3 tier app i sqs izmedju njih, na jednom tasku dolaze iste poruke vise puta. sta je uzrok. 
- kako doozvoliti samo premium korisnicima da pristupe sadrzaju u s3
- veliki dio pitanja je bio odrediti odgovarajuci database za scenario
-  na koji nacin prosiriti storage sa on premisses na aws tako da arhiva bude na aws a frekventna data ostane na on premisses
- na koji način pristupiti resursima u s3 bez da se prenos podataka vrsi preko interneta
- par pitanja bilo je odrediti koji tip hdd ili ssd koristiti za datu infrastrukturu uzimajuci u obzir navedene potrebe (koji tačno scenarij je bio , ne mog se sjetiti)
- određivanje najisplativijeg storagge clas.
-koji su benefiti koristenja RDS multi az deploy
- kako postaviti sqs u multi tier app
- analiza resource policy ( sta IAM može u navedenom bucketu) 
- najbrža intervencija u slučaju napada sa detektovanog range-a ip adresa
- kakve postavke in/out odabrati ako zelimo da se bastionu može pristupiti samo sa korporacijskih ip adresa. 
- 2 pitanja su bila, ne mogu tacno da se sjetim scenarija, ali traži se određivanje kombinacije storage za data i načina kojim se vrsi query nad data







Exam date: June 2022


AWS Macie
AWS SageMaker
AWS Textract
AWS Kinesis Firehouse/Data Stream
Pitanje bude tipa real time processing kada moramo iz razlicith sourcova da radimo pa je odgovor Firehouse dok Data streams ide iz jednog
AWS Kinesis Data Analytics
AWS Comperhand medical(bilo nesta oko medical fajlova pa da se ovaj izabere)
Storovanje Seasion data u koji storage 
DynamoDb i elasticcache
Step Function vs SWF kad koji iskoristit 
Glaven razlike su Step Functioon(serverless/short running,less complex)
AWS(uses servers/asyhtonicus/complex custom)
Pitanje koji tip endpointa je s3 i dynamo db (gateway) ostali su interface
Sta upaliti da imamo “object using write once read many”(WORM)
Object lock s3
Sa verzionisanjem da li svaki objekat moze imati razlicite rention periode
Sta je potrebno za VPC peering site to site konekciju (prerequesites)
Public ip na customer gatewayu na on premises 
i virtual private gateway na vpcu
Sta se placa kad je Ec2 u hibrenate state
EBS volumes i elastic ip koji je na njemu
Ako nam treba high Iops koji tip isntance ce se koristi
Ponudjeno (memory/.compute/general/storage) Storage tacno (msm)
Volume Gateawy i volume gateway in cached mode
Bilo je pitanja oko Fsx Luster i Fsx Windows file server koji kad iskoritit
Bilo je ponudjeno koji ce se iskoristi ako nam treba da se poveze na manged active directory i to je ovaj Fsx windows file server
Koji Raid iskoristi 0 ili 1 za data mirroring
raid 0 je za improved perfomance 
raid 1 je za mirroring
Sa cime cloudfront origin moze biti
s3,ec2,elb,lambda@edge
Default time za coldown period kod auto scalling grupe
300 sekundi
Bila su pitanja oko scalinga kad iskoristi target tracking a kad step scaling
Kako najlakse automatizirati kreaciju i renetion period ili brisanje snapshotova kod EBS(Data Lifecycle Manager DLM)
Sqs Retention period,gubitak nekih posle 7 dana jer je defaultni 4 dana pa se treba povecati na vise dana max je 14 dana
Visiblity timeout za SQS i long pooling kako se stavlja
Izabrati serverless servise od awsa ponudjeni tacni su s3 i dynamodb
kako se moze konfigurisati instanca bez povezivanja na svaki ec2 instancu (run command)
Koji gateawy koristi da se povuku s3 ojbekti kroz nfs i smb protkole(File gateway)
Kako enkriptovati data in transit dok accessuje data iz amazon efs
koristi se onaj mount helper da enkriptuje data in transit
Na sta s3 moze slati notifikacije
Sns,sqs,lambda i eventbridge
Bilo je pitanje koji routing iskoristi iz route53 tacnije pitanje koji bi bio najbolje za blue green deployment
Weighted routing 
I jos jedno pitanje je bilo kad hocemo da route53 vraca na dns queires sa vecim broje healthy recorda (mutlivalue)
Kao data dva vpca pa onda imamo on premises i kako to povezati u jedan gateway koji ce actovati kao hub(transit Gateway)

EXAM DATE: JULY 2022.

Generalno je ispit jako fokusiran na detalje, šta znači na detalje. Jedan primjer jeste CF tj. kako automatizovati kreiranje infrastrukture kroz template, pa dobijemo sva četiri odgovora koja se razlikuju u gotovo samo jednome detalju.

Za test je potrebno biti odmoran/na jer imamo 65 pitanja po 2 minute brzo prođe zaista, a pitanja su mnogo tricky. Obratiti pažnju da li se traži efikasnost, cost, dostupnost. Postoje odgovori koji su po svojoj strukturi točni, ali ne ispunjavaju tipa cost efektivnost. 

S3 Encryption
CloudFormation
RDS
VPC
ASG
AWS Config
Systems Manager
Secrets Manager
EC2
EFS
SQS
CloudWatch Alarmi
Athena
DynamoDB
R53



EBS - ako imamo privremeno korištenje storage koju vrstu koristiti. Bilo je i pitanje koji disk koristiti za boot instance.
Da li možemo jedan ebs volume nakačiti na više instanci. 
Snapshot. Je li EBS one AZ ili se nalazi kroz više AZ. 

NACL - kako omogućiti konekciju in i out rules. 

Bastion host - za šta koristimo, koju razinu sigurnosti pruža, još jedno pitanje vezano za bastion host, kako ograničavamo pristup samo partnerskim kompanijama prema našem sistemu kroz bastion host. 

RDS - kako možemo skalirati bazu podataka. 

S3 - kako zaštiti brisanje podataka iz bucketa.

EFS - bursting i provisioning. Dostupnost kroz više AZ. Access points. 

Athena - kako vrši query podataka, da li mijenja podatke prilikom query operacije, te koja je prednost i cijena korištenja ovoga servisa. 

Lambda - trik pitanje koje navodi izvršavanje Lambda funkcije duže od 30 minuta, kako optimizovati troškove. Lambda funkcija ima max. izvršavanja 15 min. 


Direct Connect, EBS, EC2, VPC - ukoliko pristupamo podacima preko navednih servisa koju vrsta enkripcije za public internet je potrebno koristiti. Po defaultu ovi servisi nemaju doticaj s public internetom osim, ako mi to ne dopustimo. 

Baze podataka općenito - par pitanja gdje se navodi skaliranje, relacioni podatci, promjena schema baza. Na osnovu tih parametara možemo odlučiti da li potrebno NoSQL ili relacionu bazu koristiti.

SQS - retention period 14 dana. Long and short pooling,

Kinesis - potrebno voditi računa da li se traži real time obrada podataka ili postoji neki delay.
Također pitanje zašto prilikom korištenja Kinesis servisa nakon 24 sata ne vidimo podatke. 

Par pitanja također o ASG. Zašto imamo nekontroliranu kreaciju instanci, pa je bilo je pitanje kako podesiti ASG da u slučaju pada jedne AZ podigne dovoljno instanci u drugim AZ. 

Load Balanceri - većina pitanja se bazirala na to kako da jednako rasporedimo saobraćaj po instancama. Jedno zanimljivo pitanje vezano za LB koje mi je baš ostalo u sjećanju.

 Ukoliko imamo više AZ s nejednakim brojem instanci, te uočimo da u jednoj zoni imamo veću iskorištenost resursa nego u drugoj, kako riješiti taj problem, tj. kako podesiti LB tako da sve instance koristi jednako. Bilo je odgovora za Health Checks, pa i tipova instanci, te cross zone lb. Koliko se sjećam ja sam tu stavio cross zone. 

VPC Peering - kako omogućiti komunikaciju između više VPC, a da smanjimo admin overhead. Transit Gateway.

Još jedno pitanje gdje se navodi komunikacija kroz VPCove, tj. da li je komunikacija tranzitna kroz VPCs. Nije, jer je potrebno napraviti peering između VPCs za koje želimo ostvariti komunikaciju, a dodatak tome jeste podešavanje route tabla i SG, zavisno što je potrebno.

VPN - prijenost podataka s on-prema u AWS s mrežom od 100 MB, ukoliko je potrebno više od 1.25 GBs potrebno je koristiti neko drugo riješenje. 

Cluster group - aplikacije koje zahtjevaju veliku computing snagu. 
