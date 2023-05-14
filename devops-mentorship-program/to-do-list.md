# TO DO List

**TO DO Lista za DevOps Mentorship Program i aktivnosti do kraja programa.**  
**NOTE:** Lista nije finalna i moze se mijenjati tokom trajanja programa. Takojder redoslijed predavanja je podlozan promijenama u zavisnosti
od dostupnosti gosta predavaca.  
**NOTE 2:** Office Hours cemo koristiti da posebnu paznju obratimo na AWS Certifikaciju odnosno pripremu i polaganje AWS SAA certifikat. Tako da ce  
Office Hours da se sastoje od 2 dijela:  
- Dio 1: AWS SAA Certifikacija  
- Dio 2: Q&A + Osvrt na ono sto se radilo u prethodne dvije sedmice  
---
## Sadrzaj
- [Lista spomenutih AWS servisa](##Lista-spomenutih-AWS-servisa)
- [x] [Week-1](##week-1)
- [x] [Week-2](##week-2)
- [x] [Week-3](##week-3)
- [x] [Week-4](##week-4)
- [x] [Week-5](##week-5)
- [x] [Week-6](##week-6)
- [x] [Week-7](##week-7)
- [x] [Week-8](##week-8) 
- [x] [Week-9](##week-9)
- [x] [Office Hours 15.04.2023.](##Office-Hours-15.04.2023.)    
- [x] [Week-10](##week-10)
- [x] [Week-11](##week-11)
- [x] [Office Hours 29.04.2023.](##Office-Hours-29.04.2023.)  
- [x] [Week-12](##week-12)
- [x] [Office Hours 13.05.2023.](##Office-Hours-13.05.2023.) 
- [ ] [Week-13](##week-13) 
- [ ] [Week-14](##week-14)
- [ ] [Office Hours 27.05.2023.](##Office-Hours-27.05.2023.) 
- [ ] [Week-15](##week-15)
- [ ] [Week-16](##week-16)
- [ ] [Week-17](##week-17) 
- [ ] [Office Hours 17.06.2023.](##Office-Hours-17.06.2023.) 
- [ ] [Week-18](##week-18)
- [ ] [Week-19](##week-19)
- [ ] [Office Hours 01.07.2023.](##Office-Hours-01.07.2023.) 
- [ ] [Week-20](##week-20)
- [ ] [Week-21](##week-21)
- [ ] [Office Hours 15.07.2023.](##Office-Hours-15.07.2023.) 
- [ ] [Week-22](##week-22)
- [ ] [Week-23](##week-23)
- [ ] [Office Hours 29.07.2023.](##Office-Hours-29.07.2023.) 
- [ ] [Dzenan Due Date 1.07.2023.](##Dzenan-Due-Date:-1.07.2023.)
---
## Lista spomenutih AWS servisa
- IAM (IAM Users, IAM Policy) - Begginers Level
- EC2 (AMI) - Begginers Level
- AWS Free Tier
- AWS Budgets / Costs Explorer
- AMI, kreiranje custom AMI image
- Simple Email Service (SNS) - Kreiranje topica i dobijanje email-a
- CloudWatch servis kreiranje billing alert-a
- Elastic Load Balancer (ELB) -> Application Load Balancer

---
## Week-1
[Week-1: Class Notes](/devops-mentorship-program/02-february/week-1-140223/00-class-notes.md)
#### Group-1-Tier-1 14.02.2023.  

#### Group-1-Tier-2 16.02.2023.  
---
## Week-2
#### Group-1-Tier-1 21.02.2023.  

#### Group-1-Tier-2 23.02.2023. 
---
## Week-3
#### Group-1-Tier-1 28.02.2023.  

#### Group-1-Tier-2 16.02.2023. 
---
## Week-4
#### Group-1-Tier-1 07.02.2023.  

#### Group-1-Tier-2 09.02.2023.  

---
## Week-5
#### Group-1-Tier-1 14.03.2023.  

#### Group-1-Tier-2 16.03.2023. 
---
## Week-6
#### Group-1-Tier-1 21.03.2023.  

#### Group-1-Tier-2 23.03.2023. 
---
## Week-7
#### Group-1-Tier-1 28.03.2023.  

#### Group-1-Tier-2 30.03.2023. 

---
## Week-8

#### Group-1-Tier-1 | 04.04.2023.
- ALB i Target Groups - kako se podesava, kako se povezuje sa EC2 instancama

#### Group-1-Tier-2 | 06.04.2023.
- Nastavak predavanja o AWS servisima od Utorka gdje smo podigli EC2 instance sa jednostavnom html stranicom i postavili ih iza Application Load Balancer (ALB) servisa. Demonstrirali smo HA izmedju razlicith AZ. Veceras cemo obraditi:
    - [x] Elastic Block Storage (EBS)
            - [x] Snapshots
            - [x] Create Volume from Snapshot
            - [x] Create AMI from Volume
            - [x] Exapand Volume
            - [x] Attach Volume to EC2 instance
            - [x] Attach Multiple Volumes to EC2 instance
            - [x] Pricing
    - [x] Austoscaling Groups (ASG) - kako se podesava, kako se povezuje sa ALB-om, kako se povezuje sa EC2 instancama
    - [x] ELB and AutoScaling Groups Pricing - "FREE za ASG", placate resurse koje koristite (EC2, CloudWatch...)


**Week-8 koristeni AWS servisi:**
 - [x] AWS EC2 -> Custom AMI
 - [x] Amzon Linux AMI
 - [x] ALB -> Target Groups
 - [x] EBS
 - [x] ASG
 - [x] ACM
 - [x] CloudTrail
 
---

## Week-9

#### Group-1-Tier-1 11.04.2023.
- [x] S3 Storage - Basics
- [ ] IAM deep dive
    - [ ] Pokazati na primjeru kopiranja objekata iz jednog S3 bucketa u drugi razliku u gresci koju dobijete kada imate `s3:ListBucket` i kad nemate `s3:ListBucket`. AWS docs [link](https://docs.aws.amazon.com/AmazonS3/latest/API/API_GetObject.html)

#### Group-1-Tier-2 13.04.2023.
- [x] Relacione Baze Podataka, instalacija MySQL-a, PostgreSQL-a na Amazon Linux EC2 instnaci
    - [x] Self Managed Baze podataka, problemi i izazovi
- [x] AWS RDS - Relational Database Service
- [ ] Database Migration Servis (DMS)
---
## Office Hours 15.04.2023.

- [x] Podesavanje SSL certifikata na nasu EC2 instancu gdje koristimo SSL let's encrypt certifikat
- [x] Prebacivanje SSL certifikata sa EC2 instance na ALB servis koristeci AWS Certificate Manager (ACM) servis

---
## Week-10
#### Group-1-Tier-1 18.04.2023.
- [x] RDS part 2

#### Group-1-Tier-2 20.04.2023.
- [x] Robert Zuljevic gost predavac deployment Java aplikacije na AWS-u
- Infrastruktura aplikacije:
    - EC2 instance
    - Access Key / Secret Access Key -> IAM Role
    - ASG
    - ALB
    - S3
    - Secrets Manager (Objasniti Secrets Manager vs Parameter Store )
    - Amazon RDS
    - Route 53
```
```bash
# ucitavanje razlicitih app konfiguracijskih profila. Bitno za CI/CD i razlicita okruzenja Dev/QA/Prod 

java -jar -Dspring.profiles.active=dev s3-webapp-demo-0.0.1-SNAPSHOT.jar
```  

------
## Week-11

#### Group-1-Tier-1 25.04.2023.
- [x] Komanda `aws secretsmanager get-secret-value --secret-id rds!db-f68a7098-4e7c-4d1d-906e-d75383c92e39`  je OK, ali je problem uzvicnik `rds!db`. Treba staviti escape backslash `rds\!db` - Hvala **Ismail Icanovic** 
- [x] S3 + CloudFront for static web site hosting
- [x] Elastic IPs
- [x] VPC Intro

#### Group-1-Tier-2 27.04.2023.
- [x] VPC Virtual Private Cloud Deep Dive
- [ ] VPC Lattice
---
## Office Hours 29.04.2023.
AWS Certification 
Gosti predavaci:
    - [Igor Ivanovic](https://www.linkedin.com/in/ivanovicigor/) 
    - Marin Radjenovic 

---
## Week-12 (pauza)

#### Group-1-Tier-1 2.05.2023.
Sljedece je potrebno prebaciti u neku od sedmica u nastavku jer smo ovo preskocili zbog
pauze koju smo pravili
```
- Unaprijedjenje infrastrukture za Java aplikaciju iz Week-10 (pogledati [AWS Web Application Architcture on AWS](https://d1.awsstatic.com/architecture-diagrams/ArchitectureDiagrams/web-application-architecture-on-aws-ra.pdf?did=wp_card&trk=wp_card))
- [Choosing the right health check with Elastic Load Balancing and EC2 Auto Scaling](https://aws.amazon.com/blogs/networking-and-content-delivery/choosing-the-right-health-check-with-elastic-load-balancing-and-ec2-auto-scaling/)
- VPC
    - Private + Public Subnets
    - NAT Gateway (Managed vs Self Managed)
    - Bastion Hosts
    - VPN (Open VPN, AWS Client VPN)
    - Bastion host
    - CloudWatch monitoring -> CloudWatch Insights (Log Insights)
    - KMS (Key Management Service) - Encryption at rest
    - WAF
    - Network Firewall
    - Amazon Shield
    - CloudTrail
    - S3 Logs
    - Amazon Athena (Querying S3 Logs)
    - Route53 (multi region failover, diferent routing policies)
```
## Week-12 [New Schedule]
#### Group-1-Tier-1 09.05.2023.
- [x] Marin Radjenovic - Serverless - AWS Lambda / Event Bridge  / SNS /  SQS 
   => Hello World Node JS lambda funkcija i deployment kroz AWS konzolu. 
   => EventBridge / CloudWatch Events (cron job)
   => SAM 
   => Demo AWS Lambda / SNS / SQS 
   

#### Group-1-Tier-1 11.05.2023.
- [x] Marin nastavlja tamo gdje je stao u utorak i sta nije stigao da odradi u Utorak.
- [x] Alma Beganovic - API Gateway,
---

## Office Hours 13.05.2023.
- Analiza rezultata ankete
- AWS SAA Certification - dodatno pojasnjenje
----
## Week-13

#### Group-1-Tier-1 16.05.2023.
- [Viktor Farcic](https://www.linkedin.com/in/viktorfarcic/) - Gost Predavac (devops state of mind) - 1h | Pocetak u 20h
- Route 53

#### Group-1-Tier-2 17.05.2023. # mora biti pomjereno
- Simple Email Service (SES)
- Urban Jurca


---
## Week-14

#### Group-1-Tier-1 23.05.2023.
- [ ]  Server Configuration Management
 - [ ] Ansible
 - [ ] Packer
 - [ ] EC2 Image Builder
#### Group-2-Tier-2 25.05.2023.
- CICD
---
## Office Hours 27.05.2023.

---
## Week-15
#### Group-1-Tier-1 30.05.2023.
- Cloud Formation (Infrastructure as Code) - Alma Beganovic / Dragan Pavlovic

#### Group-1-Tier-2 01.06.2023.
- Terrafrom IaC - Dragan Pavlovic
---
## Week-16
NOTE: Dzenan OOTO 
#### Group-1-Tier-1 06.06.2023.
- Marin Radjenovic - CDK (Cloud Development Kit)

#### Group-1-Tier-2 08.06.2023.
- Marin Radjenovic - Overview of Machine Learning services on AWS

---
Teme za sedmice koje slijede tek treba da se preciziraju.
Stvari koje su se propustile do sada a potrebno ih je obraditi:
- Unaprijediti AWS infrastrukturu Java aplikacije sto je bilo planirano za Week 12.
- Python on AWS
- Overivew of Security Services on AWS
- AWS Organisations

---
## Week-17

### Group-1-Tier-1 13.06.2023.
- Introduction to Docker

- Goran Opacic - Amazon Aurora, Aurora Serverless 
- Goran Opacic - DynamoDB
- Monitoring and Observability
    - Grafana (Dragan Pavlovic)
    - Data Dog vs New Relic

### Group-2-Tier-1 15.06.2023.
- ELK Stack
---
## Office Hours 17.06.2023.
- Pomjerili smo Office Hours sa 10.06.2023. na 17.06.2023. zbog toga sto je Dzenan OOTO u sedmici 5.06.2023. - 11.06.2023.

---
## Week-18
### Group-1-Tier-1 20.06.2023.
  - Kubernetes [ovo ce vjerovatno biti pomjereno za neku od kasnijih sedmica]
  - GitOps / ArgoCD / FluxCD [ovo ce vjerovatno biti pomjereno za neku od kasnijih sedmica]

### Group-2-Tier-1 22.06.2023.
- Nenad Djordjevic [Tek treba da se precizira tacan datum]
    - Golang i DevOps
---
## Week-19

### Group-1-Tier-1 27.06.2023.
- Teme i predavaci ce biti naknadno dodani

### Group-2-Tier-1 29.06.2023.
- Teme i predavaci ce biti naknadno dodani
---
## Office Hours 01.07.2023.
Obavjesitit polaznike o prebacivanju na 1 predavanje sedmicno. 
Predstaviti timeline za nastavak Mentorship Programa.
--- 
## Week-20
Prebaciti na 1 predavanje sedmicno.
### Tier-1 04.07.2023.
- Teme i predavaci ce biti naknadno dodani
---
## Week-21

### Tier 1 11.07.2023.
- Ranije je bilo planirano da ovo bude posljednje predavanje prvog ciklusa DevOps Menotrship Programa ali cemo to morati da prolongiramo jer neke teme nece stici da se obrade do ovog datuma. U skladu s tim potrebno je planirati daljnje aktivnosti pocevsi od Week-21.
- Review results and impresions.
- What next ? -> Break -> BI Weekly DevOps Office Hours / CI za DevOps Mentorship Program :)
---

## Office Hours 15.07.2023.

## Week-22
### Tier 1 18.07.2023.

---

## Week-23
### Tier 1 25.07.2023.

---
## Office Hours 29.07.2023.
Zavrsetak DevOps Mentorship Programa
- Next steps
## Dzenan Due Date: 1.07.2023.
- Mind map update sa stvarima koje smo presli i koje prelazimo
- Snimanje dodanih kratkih videa 15-20 min max za stvari koje su bitne a nisu se spomenule ili su preskocene
- Ispraviti greske u repozitoriju, povezati DevOps Learning Path sa Class Notes
- Dopuniti materijale i biljeske (materijali sa gdrive i individualnih biljeski)
- Napraviti yt video snimke i repozitoriji javnim
- Napisati blog post o DevOps Mentorship Programu
- Koristiti `mkdocs` za sajt sa materijalima. Pogledaj primjer (ovdje)[https://github.com/aws-observability/observability-best-practices]
---
## Offline Recordings TO DO List
Snimiti dodatne vide lekcije offline: 
- [ ] IMDS - Instance Metadata Service
        - Instance Metadata Service Version 1 (IMDSv1) – a request/response method  
        - Instance Metadata Service Version 2 (IMDSv2) – a session-oriented method  
- [ ] AWS Systems Manager, Bastion Hosts (SSM Agent)
    - [AWS Bites: 78: When do you need a bastion host?](https://open.spotify.com/episode/48vnERHfZtHVovUlG2quG0?si=1dcf9e9928a54a4d)
        - port knocking technique https://github.com/moxie0/knockknock
        - set up EC2 instance connect https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-connect-set-up.html
        - AWS IP address ranges https://docs.aws.amazon.com/vpc/latest/userguide/aws-ip-ranges.html
        - AWS Systems Manager Session Manager https://docs.aws.amazon.com/systems-manager/latest/userguide/session-manager.html / ECS Exec
        - aws/amazon-ssm-agent https://github.com/aws/amazon-ssm-agent
        - Inles Project on git https://github.com/inlets/
        - Basti on Github https://github.com/BohdanPetryshyn/basti
        - tailscale https://tailscale.com/
        - wireguard https://www.wireguard.com/
- [ ] VPN 
- [ ] Custom NAT instance
- [ ] Kako instalirati MySQL na Amazon Linux 3 EC2 instancu (+osnovna konfiguracija)
--- 
## TBD

- Jeff Gahan
- Viktorija Denchovska
- Goran Opacic - DynamoDB / NoSQL baze podataka
- Andrew Owen - Networking Operations Center (NOC) / Monitoring and Observability
- Darko Mesaros
---




