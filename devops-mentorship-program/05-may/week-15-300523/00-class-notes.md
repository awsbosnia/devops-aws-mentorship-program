# 📝 Week 15 - Class notes
## 📅 Date: 30.05.2023.

## Infrastructure as Code (IaC)

- Jednostavniji nacin kreiranja i upravljanja AWS resursima. Sa jednim ***template***
-om  mogu se kreirati jako kompleksna okruzenja. 
- Nema dodatnih troskova, placate samo za resurse koje kreirate
- Tretiranje infrastrukture kao koda koristeci neki od kod editora (GitHub, AWS CodeCommit). Mogucnosti ***roll back**-a na posljednje stabilno stanje.

### CloudFormation

- **CloudFormation Template** 
    - predstavlja dokument napisan u JSON-u ili YAML-u
    - definise LOGICKE resurse - ono STA kreiramo
    - koristi se za kreiranje ***Stack***-ova

- **CloudFormation Stack**
    - koriste se za kreiranje FIZICKIH resursa iz logickih koji su definisani u ***template***-u
    - svaka izmjena nad logickih resursima unutar ***template*** fajla, nakon ***update***-a ***Stack***-a bice izvrsena nad fizickim resursima
    - brisanjem ***Stack***-a obrisati ce se i svi fizicki resursi

- **Parametri**
    - **Template Parametri** prihvataju ***input***, ulazne podatke koji su uneseni putem Console/CLI/API. Koriste se za kreiranje ili ***update*** ***Stack***-a.
    - **Pseudo Parametri** - upravljane od strane AWS i zavise od okruzenja u kojem se kreira ***Stack***

- **AWS CloudFormation ChangeSet** - omogucava pregled izmjena nad resursima prije izvrsavanja nad ***Stack***-om

- **Drift detection**- detekcija svih izmjena koje su izvrsene manuelno nad resursima koji su kreirani iz ***Stack***-a.

### Terrafrom

[:fast_forward: Class Notes](/devops-mentorship-program/05-may/week-15-300523/00-class-notes.md)
[:fast_forward: Additional Reading](/devops-mentorship-program/05-may/week-15-300523/02-additional-reading.md)
[:fast_forward: HOME - README.md](../../../README.md)
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)
