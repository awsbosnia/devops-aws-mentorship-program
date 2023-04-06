# 📝 Week 7 - Class notes
#### 📅 Date: 28.03.2023.

## Elastic Compute Cloud (EC2) - Basics
**Elastic Compute Cloud (EC2)** je spada u kategoriju **IaaS** (Infrastructure as a Service) servisa. EC2 je AWS servis koji vam omogucava kreiranje i koristenje racunarskih resursa (CPU, RAM, GPU, Networking). EC2 instacu cesto nazivamo i virtuelnom masinom u cloudu.

**Amazon Machine Image (AMI)** konfiguracija / template / sablon, koji koristimo da bi na osnovu njega brzo i jednostavno kreirali EC2 instancu. **AMI** sadrzi informacije o operativnom sistemu, arhitekturi (32/64-bit), ranije predinstaliranim aplikacijama itd. AMI moze biti Javni, Privatni ili Dijeljeni.

**Instance Types / Tipovi EC2 instanci** predstavlja velicinu resursa vase EC2 sa stanovista resursa. Postoji vise razlicitih tipova EC2 instnaci koje su podjeljene u nekoliko porodica/kategorija. Tipovi instanci se oznacavaju, imenuju, kodovima npr: `t2.micro`, `m5.large`, `c5.2xlarge` itd.

![Oznacavanje instanci](/devops-mentorship-program/03-march/week-7-280323/files/instance-types.png)
**Slika preuzeta iz AWS dokumentacije** [Instance types](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/instance-types.html)

Sjajan blog post na temu imenovanja EC2 instanci pogledajte na sljedecem linku [Amazon EC2 names explained](https://justingarrison.com/blog/2023-02-23-ec2-names-explained/)
## Identity Access Management (IAM) - Basics

**Identinty Access Management (IAM)** je AWS servis koji vam omogucava **autentifikaciju** i **autorizaciju** za rad sa AWS servisima unutar vaseg AWS racuna.

Kada posaljete zahtjev prema AWS API-iju, bilo da radite sa servisima koristeci AWS konzolu,SDKs (Software Development Kit) ili AWS Command Line Interface (AWS CLI), IAM servis je taj koji verifikuje vas identitet i provjerava da li vam je dozvoljeno izvrsavanje zeljene akcije.

IAM dakle kontrolise **KO (autentifikacija)** moze pristupiti vasem AWS racunu  i **KOJE AKCIJE (autorizacija)** moze napraviti unutar AWS racuna.

Postoje dva nacina da se verfikuje IAM korisnik unutar AWS racuna:
- Korisnicko ime i lozinka
- Kljucevi za pristup (Access Keys)

**AWS Principals** predstavlja **osobu** ili **aplikaciju** koja koristi AWS `root korisnika`, `IAM korisnika` ili `IAM rolu` da se prijavi unutar AWS racuna i napravi API poziv prema nekom od servisa.

Kada AWS racunu pristupate koristeci email adresu i password koji ste upisali prilikom kreiranja racuna, koristite `root korisnika / root user`.  Root korisnik se kreira automatski sa vasim AWS racunom. Taj korisnik ima pune administratorske privilegije nad AWS racunom i moze da pristupi svim servisima ukljucujuci i dio namjenjen za placanje. Sve nove korisnike koje kreirate, kreirate koristeci IAM servis, medjutim oni nemaju privilegije za pristup resursima osim da se prijave na AWS racun sve dok im ih vi ne dodjelite koristeci `IAM Policy`.
Najbolja praksa i preporuka je da root korisnika nikada ne koristite za dnevni menadzment i rad sa AWS racunom. Bitno je da zapamtite da unutar AWS racuna postoje odredjene akcije koje mozete napraviti samo koristeci root korisnika. Vise o tome pogledajte na sljedecem linku unutar AWS dokumentacije [Tasks that require root user credentials](https://docs.aws.amazon.com/accounts/latest/reference/root-user-tasks.html)

Opcija pristupa IAM korisnika dijelu za placanje unutar AWS racuna mora bit omogucena na nivou AWS racuna od strane root korisnika. Dok se to ne dogodi, IAM korisnik bez obzira na privilegije koje ima ne moze da pristupi dijelu za placanje i troskove unutar AWS racuna.
Vise o tome kako da to uradite mozete pronaci u AWS dokumentaciji [IAM tutorial: Delegate access to the billing console](https://docs.aws.amazon.com/IAM/latest/UserGuide/tutorial_billing.html)

**IAM Policy** je objekat unutar AWS-a koji kada se pridruzi identitetu (`IAM User`, `IAM Group`, `IAM Rola`) ili resursu (`EC2 instance`, `S3 bucket`) **definise njihova prava pristupa**. Kada identitet ili resurs pravi upit prema AWS API-ju, AWS na osnovu IAM polisija ocjenjuje, prvo da li uopste ima prava da napravi taj poziv a zatim i koja su mu prava odnosno akcije dozvoljene da napravi.

Korisnici i role unutar IAM servisa posjeduju vlastiti `arn` **(amazon resource name)** i zato se zovu pravim identitetima dok je uloga **grupa organizatorske prirode** i one sluze administratorima da grupisu korisnike i na taj nacin lakse odrzavaju njihova prava pristupa dodjeljujuci rolu na nivou grupe a ne na nivou pojedinacnog korisnika.

IAM servis, pored navedenih mogucnosti, ima i ulogu da kooridinira sa **AWS Security Token Service (STS)** servisom i na taj nacin omoguci da vanjski entiteti mogu da pristupe AWS resursima unutar AWS racuna.

## 📹 Session recordings
- [**WEEK-7-tier-1-group-1 video session recording**](https://youtu.be/fpaFBM9oWfE)
- [**WEEK-7-tier-1-group-2 video session recording**](https://youtu.be/e8EBvLyQ8c8)

## 📖 Reading materials

- [:orange_book: Amazon EC2 FAQ](https://aws.amazon.com/ec2/faqs/)
- [:orange_book: AWS Identity and Access Management (IAM) FAQs](https://aws.amazon.com/iam/faqs/)
- [📹 Kako da kreirate i spojite se na EC2 instancu](https://youtu.be/dxcsN8HQk1o)
- [📹 Uvod u Identity and Access Management (IAM) servis](https://youtu.be/i67-zDAyZNc)

[:fast_forward: Class Notes](/devops-mentorship-program/03-march/week-7-280323/00-class-notes.md)
[:fast_forward: Additional Reading](/devops-mentorship-program/03-march/week-7-280323/02-additional-reading.md)
[:fast_forward: HOME - README.md](../../../README.md)
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)
