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
- [ ] [Week-9](##week-9)
- [ ] [Office Hours 15.04.2023.](##Office-Hours-15.04.2023.)    
- [ ] [Week-10](##week-10)
- [ ] [Week-11](##week-11)
- [ ] [Office Hours 29.04.2023.](##Office-Hours-29.04.2023.)  
- [ ] [Week-12](##week-12)
- [ ] [Week-13](##week-13)
- [ ] [Office Hours 13.05.2023.](##Office-Hours-13.05.2023.)  
- [ ] [Week-14](##week-14)
- [ ] [Week-15](##week-15)
- [ ] [Office Hours 27.05.2023.](##Office-Hours-27.05.2023.) 
- [ ] [Week-16](##week-16)
- [ ] [Week-17](##week-17) 
- [ ] [Week-18](##week-18)
- [ ] [Office Hours 17.06.2023.](##Office-Hours-17.06.2023.) 
- [ ] [Week-19](##week-19)
- [ ] [Week-20](##week-20)
- [ ] [Week-21](##week-21)
- [ ] [Dzenan Due Date 1.07.2023.](##Dzenan-Due-Date:-1.07.2023.)
- [ ] [Office Hours 1.01.2023.](##Office-Hours-01.07.2023.) 
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
- AWS EC2 -> Custom AMI
- Amzon Linux AMI
- ALB -> Target Groups
- EBS
- ASG
- ACM
- CloudTrail
- CloudWatch CPU alarms
---

## Week-9

#### Group-1-Tier-1 11.04.2023.
- IAM deep dive

#### Group-1-Tier-2 13.04.2023.
- Relacione Baze Podataka, instalacija MySQL-a, PostgreSQL-a na Amazon Linux EC2 instnaci
    - Self Managed Baze podataka, problemi i izazovi
- AWS RDS - Relational Database Service
- Database Migration Servis (DMS)
---
## Office Hours 15.04.2023.

- [ ] Podesavanje SSL certifikata na nasu EC2 instancu gdje koristimo SSL let's encrypt certifikat
- [ ] Prebacivanje SSL certifikata sa EC2 instance na ALB servis koristeci AWS Certificate Manager (ACM) servis
- [ ] IMDS - Instance Metadata Service
        - Instance Metadata Service Version 1 (IMDSv1) – a request/response method  
        - Instance Metadata Service Version 2 (IMDSv2) – a session-oriented method  
---
## Week-10
#### Group-1-Tier-1 18.04.2023.
- Server Configuration Management
    - Ansible
    - Packer
    - EC2 Image Builder

#### Group-1-Tier-2 20.04.2023.
- Robert Zuljevic gost predavac deployment Java aplikacije na AWS-u
- Infrastruktura aplikacije:
    - EC2 instance
    - Access Key / Secret Access Key -> IAM Role
    - ASG
    - ALB
    - S3
    - Secrets Manager (Objasniti Secrets Manager vs Parameter Store )
    - Amazon RDS
    - Route 53
------

## Week-11

#### Group-1-Tier-1 25.04.2023.
- VPC Virtual Private Cloud Deep Dive
- VPC Lattice

#### Group-1-Tier-2 27.04.2023.
- Milan Pavlovic - Uvod u Docker
---
## Office Hours 29.04.2023.
AWS Certification 
Gosti predavaci:
    - [Igor Ivanovic](https://www.linkedin.com/in/ivanovicigor/) 
    - Marin Radjenovic 

---
## Week-12

#### Group-1-Tier-1 2.05.2023.
- Unaprijedjenje infrastrukture za Java aplikaciju iz Week-10
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

#### Group-1-Tier-2 04.05.2023.
- Marin Radjenovic - Serverless / AWS Lambda / API Gateway / SQS
- Alma Beganovic - API Gateway 
---
## Week-13

#### Group-1-Tier-1 9.05.2023.
- CI/CD
- Halid Osmanovic - Jenkins
- AWS CodePipeline, Code Build, Code Deploy
- Github actions (pre-commit hooks)

#### Group-1-Tier-2 11.05.2023.
- CloudFront + S3 - Static Web Site Hosting
---
## Office Hours 13.05.2023.

---
## Week-14

#### Group-1-Tier-1 16.05.2023.
- Alma Beganovic - Infrastructure as Code (CloudFormation)

#### Group-2-Tier-2 18.05.2023.
- Dragan Pavlovic - Infrastructure as Code (Terraform)
---
## Week-15
#### Group-1-Tier-1 23.05.2023.
- Migrate Java app from EC2 to ECS
- [Viktor Farcic](https://www.linkedin.com/in/viktorfarcic/) - Gost Predavac (devops state of mind) - 1h | Pocetak u 20h


#### Group-1-Tier-2 25.05.2023.
- Migrate Java app from ECS to ECS Fargate
---
## Office Hours 27.05.2023.

---
## Week-16
### Group-1-Tier-1 30.05.2023.
- Boris Bradic - Kubernetes

### Group-1-Tier-2 01.06.2023.
- Boris Bradic - Kubernetes / EKS
---
## Week-17
NOTE: Dzenan OOTO 
#### Group-1-Tier-1 06.06.2023.
- Marin Radjenovic - CDK (Cloud Development Kit)

#### Group-1-Tier-2 06.06.2023.
- Marin Radjenovic - Overview of Machine Learning service on AWS

---

## Week-18
### Group-1-Tier-1 30.05.2023.
- Monitoring and Observability
    - Grafana (Dragan Pavlovic)
    - Data Dog vs New Relic

### Group-2-Tier-1 1.06.2023.
- ELK Stack
---
## Office Hours 17.06.2023.
- Pomjerili smo Office Hours sa 10.06.2023. na 17.06.2023. zbog toga sto je Dzenan OOTO u sedmici 5.06.2023. - 11.06.2023.

---
## Week-19
### Group-1-Tier-1 06.06.2023.
- Urban Jurca
  - DevOps Culture and Practices
  - GitOps / ArgoCD / FluxCD

### Group-2-Tier-1 8.06.2023.
- Nenad Djordjevic
    - Golang i DevOps
---
## Week-20

### Group-1-Tier-1 13.06.2023.
- Teme i predavaci ce biti naknadno dodani

### Group-2-Tier-1 15.06.2023.
- Teme i predavaci ce biti naknadno dodani
---
## Week-21

### Group-1-Tier-1 20.06.2023.
### Group-2-Tier-1 15.06.2023.
- Teme i predavaci ce biti naknadno dodani

### Group-1-Tier-2 22.06.2023.
- Teme i predavaci ce biti naknadno dodani
---
## Week-22

### Group-1-Tier-1 + Group-1-Tier-2 27.06.2023.
- Review results and impresions.
- What next ? -> Break -> BI Weekly DevOps Office Hours / CI za DevOps Mentorship Program :)
---
## Week-23

## Dzenan Due Date: 1.07.2023.
- Ispraviti greske u repozitoriju
- Dopuniti materijale i biljeske
- Napraviti yt video snimke i repozitoriji javnim
- Napisati blog post o DevOps Mentorship Programu
---
## Office Hours 01.07.2023.
--- 
## TBD

- Jeff Gahan
- Viktorija Denchovska
- Goran Opacic - DynamoDB / NoSQL baze podataka
- Andrew Owen - Networking Operations Center (NOC) / Monitoring and Observability
- Darko Mesaros
---




