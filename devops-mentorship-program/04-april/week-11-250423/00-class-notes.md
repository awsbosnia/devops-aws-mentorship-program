# 📝 Week 11 - Class notes
## 📅 Date: 25.04.2023.

## Amazon CloudFront

### Hosting static website on S3 with CloudFront

## Amazon Virtual Private Cloud (VPC)
**Amazon Virtual Private Cloud** ili skraceno **Amazon VPC** je AWS servis koji vam omogucava da kreirate i definisete virtuelnu mrezu unutar AWS cloud-a te da unutar te mreze smjestite vase resurse.
Vi imate potpunu kontrolu nad tom mrezom u smislu odabira IP adresnog opsega koji ce VPC da koristi te definisanja pravila pristupa resursima unutar VPC-a. 

Amazon VPC je regionalni servis, sto znaci da kada kreirate VPC morate da odaberete AWS regiju unutar koje se on nalazi. VPC moze da obuhvate sve dostupne Availibility Zone (AZ) unutar te regije. Mozete kreirati vise VPC-eva unutar jedne regije i svaki je logicki izolovan od onog drugog. 

Prilikom kreiranja VPC-a morate definisati IPv4 opseg adresa (CIDR - Classless Inter-Domain Routing block) koje ce VPC da koristi.

Kada kreirate VPC sa zeljenim opsegom adresa, taj opseg vise ne mozete da mijenjate.

Kada kreirate Vas AWS racun, defaultni VPC je kreiran za vas. Preporuka od strane AWS-a je da ne brisete defaultni VPC jer se neki pojedini resursi unutar AWS racuna kreiraju unutar tog defaultnog VPC-a osim ukoliko prilikom njihovog kreiranja vi drugacije eksplicitno ne odredite.

**Amazon VPC se sastoji od sljedecih komponenati:**

- Subnets  
- Route Tables   
- IP Addressing   
- Security Groups  
- Network Access Control Lists ACLs  
- Internet Gateway  
- Network Address Translation (NAT) Instances and NAT Gateways  
- Egress Only Internet Gateways (EIGWs)  
- Virtual Private Gateways, Customer Gateways, Virtual Private Networks (VPNs)  
- VPC Endpoints  
- VPC Peering   
- Placement Groups  
- Elastic Network Interfaces  
- Dynamic Host Configuration Protocol (DHCP) Option Sets  
- Amazon Domain Name Service (DNS) Server  
- VPC Flow Logs  

#### Subnets 

Subnet ili podmreza predstavlja dio VPC-a koji se nalazi unutar jedne Availibity Zone. Dakle za razliku od VPC-a koji obuhvata citavu regiju i sve AZ unutar nje, subnet moze da obuhvata samo jednu AZ unutar te regije. 
Prilikom kreiranja subneta morate da odaberete AZ kojoj taj subnet pripada te da alocirate zeljene IP adrese iz opsega dodjeljenog VPC-u prilikom kreiranja VPC-a.
Pa ukoliko smo za VPC odabrali CIDR blok 10.0.0.0/16 za subnet unutar tog VPC-a cemo odabrati manji adresni opseg npr: 10.0.0.0/24

**NOTE: AWS ce rezervisati prve i zadnje cetiri IP adrese unutar svakog subneta kojeg kreirate za internu upotrebu.**

- **Javni / Public Subnet** - Pridruzena ruting tablela sadrzi rutu do internet gateway-a (igw)

- **Privatni / Private Subnet** - Pridruzena ruting tabela nema rutu do internet gateway-a (igw) a moze sadrzavati rutu do NAT instance odnosno NAT gatway-a

#### Route Tables / Ruting tabele

Svaki subnet unutar vaseg VPC-a sadrzi logicki konstruktor koji se naziva **implicit ruter**. Implicit ruter je mjesto na kojem se donose odluke o rutiranju na nivou subneta.
**Upravljanje rutiranjem se vrsi uz pomoc ruting tabela.**
Vama je omoguceno da kreirate ruting tabele i tako definisete zeljene rute. Korisnicki kreirane ruting table mogu biti pridruzene jednom ili  vise subneta.
Svaki VPC sadrzi glavnu (main) ruting tabelu koju mozete mijenjati. Glavna ruting tabela se koristi za sve subnete unutar VPC-a kojima nije eksplicitno dodjeljena korisnicki kreirana route tabela.
Korisnicki kreirana ruting tablea moze biti oznacena kao glavna sto omogucava da svi novi subneti koji kreirate automatski koriste tu ruting tableu bez da im je eksplicitno dodjelite.

#### IP Addressing / IP Adresiranje

Amazon VPC podrzava IPv4 i IPv6 protokol adresiranja. Prilikom kreiranja nove EC2 instance njoj se pridruzuje IPv4 privatna adresa iz opsega definisanog subnetom u kojem se ta EC2 instanca nalazi. 
Imate mogucnost definisanja privatne IP adrese ukoliko je ta adresa dostupna i naravno ukoliko se nalazi unutar definisanog opsega.
Amazon EC2 instance takodjer mogu imati i javnu IPv4 adresu. Te adrese se dodjeljuju automatski prilikom kreiranja. Na nivou subneta imate mogucnost da definisete da li ce prilikom kreiranja resursa unutar subneta automatski biti dodjeljena javna ipv4 adresa.
Vodite racuna da automatski dodjeljene javne IPv4 adrese nisu fixne, te se mogu promijeniti bez prethodne najave ili prilikom izmjena stanja EC2 instance. 

Ukoliko vam je potrebna fixna javna IP adresa imate mogucnost da alocirate IPv4 javnu adresu koja je rezervisane od strane AWS-a, nakon sto alocirali javnu adresu imate mogucnost da je pridruzite EC2 instanci. Tako alocirane javne ip adrese postaju pridruzene vasem racunu i one se ne mijenjaju bez obzira sta vi uradite sa instancom kojoj je ta adresa pridruzena. Takve adrese se nazivaju Elastic IP Addresses. 

#### Security Groups / Sigurnosne grupe

Security Groups odnosno sigurnosne grupe predstavljaju virtuelni firewall koji kontrolise ulazni i izlazni saobracaj prema AWS resursima i EC2 instancama.
Security grupe su na nivou instance, dakle uz pomoc security grupa ne mozete definisati pristup citavom subnetu nego samo resursima kojima je ta security grupa pridruzena.

Uz pomoc security grupa imate mogucnost definisanja saobracaja kojeg zelite prihvatiti ali nemate mogucnost da definisete koji saobracaj zelite odbiti. (za to se koriste access control liste).

Takodjer vazno je napomenuti da su sigurnosne grupe stateful sto znaci da ukoliko posaljete neki request prema vasoj EC2 instanci, saobracaj koji dolazi od strane EC2 instance kao odgovor na taj request je dozvoljen bez obzira na izlazna pravila vase sigurnosne grupe.

#### Network Access Control Lists (NACLs)

Network Access Control Lists ili NACL je dodatni nivou sigurnosti koji se za razliku od security grupa nalazi na na nivou subnet-a.
ACL predstavlja numericki poredanu listu pravila kroz koju AWS prolazi, pocevsi od najmanjeg numerinsanog pravila, prilikom donosenja odluke da li je saobracaj dozvoljen prema i izvan subneta koji je pridruzen toj ACL.

Svaka ACL ima finalno DENY odnosno pravilo za odbijanje koje ne mozete promijeniti.

Svaki vpc ima autmatski kreiranju standardnu ACL koja je pridruzena svakom subnetu unutar tog VPC-a i ona dozvoljava sav dolazni i odlazni saobracaj. 

Kada kreirate novu ACL ona ce odbijati sav dolazni i odlazni saobracaj dok vi ne kreirate pravila koja kazu drugacije.

Razlike izmedju Security Grupe i Network Access Control Lista


| Security Gruoup | Network Access Control List (NACLs) |
| ----------------|-------------------------------------|
| Rade na nivou mreznog interfejsa | Nalaze se na nivou subnet-a |
| Podrzavaju samo pravila za dozvoljavanje saobracaja |Podrzavaju pravila za dozvoljavanje i za odbijanje saobracaja|
| Stateful: Povratni saobracaj je automatski dozvoljen bez obzira na izlazna pravila| Stateless: Povratni saobracaj mora biti eksplicitno dozvoljen sa izlaznim pravilom |
| AWS prolazi kroz sva pravila prije nego donese odluku da li je upuceni saobracaj dozvoljen ili nije| AWS prolazi kroz numericki poredana pravila pocevsi od najmanje vrijednosti da bi odlucio da li je saobracaj dozvoljen. Onog trenutka kad naidje na pravilo koje dozvoljava ili odbija saobracaj koji je upucen prestaje sa daljnjom provjerom. Poredak pravila je jako bitan! |

#### Internet Gateway (IGW)

Internet Gateway predstavlja horizontalno skalabilnu, redudantnu i visko dostupnu komponentu VPC-a koja dozvoljava komunikaciju izmedju instanci unutar VPC-a i interenta. 
Kada je saobracaj poslan sa EC2 instance prema internetu, igw prevodi privatnu ip4 adresu u pridruzenu javnu ip4. IGW radi to jedan na jedan mapiranje izmedju javnih i privatnih adresa. Kada instanca prima saobracaj sa interenta internet gateway prevodi destinacijsku javnu IPv4 adresu u odgovarajucu privatnu adresu EC2 instance i prosljedjuje saobracaj unutar VPC-a.

Da bi unutar vaseg VPC-a kreirali javni subnet (podmerezu) gdje ce resursi koji se nalaze unutar istog biti dostupni sa interneta i imati izlaz na internet VPC mora imati pridruzen igw. Pored toga potrebno je definisati pravilo rutiranja unutar ruting tabele gdje se sav ne-lokalni saobracaj (0.0.0.0/0) salje na igw.

Pored navedenog neophodno je i da pravila definisana unutar ACL i SG dozvoljavaju zeljeni saobracaj.

Jos jedan od preduslova da bi instanca bila u mogucnosti da salje i prima saobracaj sa interneta koji mora biti ispunjen je da ona ima pridruzenu javnu IP adresu.



#### NAT Instances and NAT Gateways

Prema definiciji ntiti jedna instanca koja se nalazi u privatnom subnetu ne moze da komunicira direktno sa interentom koristeci internet gateway. Sto je jos vaznije konekcije koje dolaze van vaseg VPC-a ne mogu da komuniciraju sa instanca koje se nalaze unutar privatnog subnet-a.

Postje slucajevi kao sto su preuzimanja sigurnosnih azuriranja, instalacije aplikacija sa interneta, kada je neopohodno omoguciti instancama koje se nalaze unutar privatnog subneta izlaz na interent. 

Da bi to postigli neophodno je da koristimo NAT instance ili NAT gateway.

NAT gateway je AWS servis koji je dizajniran da bude visoko dostupan unutar AZ i ima zadatak da omoguci izlaz na interent resursima koji se nalaze unutar privatnog subneta.

Prije pojave NAT serivsa, da bi instancama unutar privatnog subneta omogucili izlaz na interent bilo je neophodno da manuelno kreirate NAT instancu i da je samostalno odrzavate.


## 📹 Session recordings
- [**WEEK-11-tier-1-group-1 video session recording**](https://youtu.be/DVJ7UGoHL9Q)
- [**WEEK-11-tier-1-group-2 video session recording**](https://youtu.be/uN5Upkmhblw)  

## 📖 Reading materials
- [📙 Amazon CloudFront FAQs](https://aws.amazon.com/cloudfront/faqs/)
- [📙 Amazon VPC FAQs](https://aws.amazon.com/vpc/faqs/)

[:fast_forward: Class Notes](/devops-mentorship-program/04-april/week-11-250423/01-homework.md)
[:fast_forward: Additional Reading](/devops-mentorship-program/04-april/week-11-250423/02-additional-reading.md)
[:fast_forward: HOME - README.md](../../../README.md)
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)
