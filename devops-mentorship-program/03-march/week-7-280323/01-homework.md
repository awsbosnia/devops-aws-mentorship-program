# 📝 Homework / Task assigment
## 📅 Date: 28.03.2023.

### [TASK-6: Create an EC2 instance and deploy simple nodejs app #45](https://github.com/allops-solutions/devops-aws-mentorship-program/issues/45)
**Napomene:**
- IAM User 1 ce svoje resurse da kreira unutar `eu-central-1` regiona.
- IAM User 2 ce svoje resurse da kreira unutar `us-east-1` regiona
- IAM User 3 ce svoje resurse da kreira unutar `eu-west-1` regiona
- Svaki do AWS resursa koje kreirate pored taga `Name` mora sadrzavati i tagove `CreatedBy: Ime Prezime` i `Email:vas@email.com`

- [ ] **AWS Account Owners / IAM User 1** Azurirajte permsije za `Tier2` **IAM Group** korisnike na nacin da grupi dodajte sljedeca 2 customer managed IAM Policy-a:

  - [tier2.create-ec2-instance-us1](https://github.com/allops-solutions/devops-aws-mentorship-program/blob/main/devops-mentorship-program/03-march/week-7-280323/files/tier2.create-ec2-instance-us1.json)
  - [tier2.selfmanage-iam-user.policy](https://github.com/allops-solutions/devops-aws-mentorship-program/blob/main/devops-mentorship-program/03-march/week-7-280323/files/tier2.selfmanage-iam-user.policy.json)

- [ ] **IAM User 1 / IAM User 2 / IAM User 3** - Azurirajte svog IAM Usera i dodajte **MFA virtual device**. To mozete uraditi unutar IAM Dashboard dijela preko opcije `Assigne MFA`.

- [ ] **IAM User 1 / IAM User 2 / IAM User 3** Kreirajte CloudWatch billing alerte koji ce vam na email poslati notifikaciju ukoliko predvidjeni / estimate AWS troskovi predju 5$ mjesecno. Naziv CloudWatch alarma treba da bude: `cw-cost-alert-ime-prezime`
**NOTE:** Upute kako da to uradite mozete pronaci na video sa [office hours sesije odrzane u Subotu](https://youtu.be/3OxR5wjBplE) ili unutar videa dostupnog na linku [ovdje](https://youtu.be/UlrmPXX4-LM).

- [ ] **IAM User 1 i IAM User 2** - Kreirajte EC2 instancu tipa `t2.micro` koristeci AMI Image `Amazon Linux 3` gdje vasa EC2 instanca da ima sljedece osobine:

  - `Name: ec2-ime-prezime-web-server`
  - Security Group sa inbound pravilima koja dozvoljavaju sav dolazni saobracaj za portove: `22` i `80`. Ime za Secruting Grup je: `sec-group-web-server`
  - Key Pair Name: `ime-prezime-web-server-key`
  - EBS volume size `14 GiB` `gp3`

- [ ] **IAM User 1 i IAM User 2** - Na EC2 instanci `ec2-ime-prezime-web-server` uradite deployment aplikacije `nodejs-simple-app` dostupne na [nodejs-simple-app.](https://github.com/allops-solutions/nodejs-simple-app)

- [ ] **IAM User 1 i IAM User 2** - Nakon sto zavrsite sa deploymentom aplikacije napravite screenshot gdje se vidi da ste pristupili aplikaciji kroz browser koristeci Public IP vase EC2 instance te zatim stopirajte EC2 instancu.

Task se smatra kompletiranim kada postavite sljedece screenshote:
- [ ] Browser gdje se vidi da ste pristupili aplikaciji koja se nalazi na EC2 instanci
- [ ] EC2 dashboard gdje se vidi EC2 instanca u statusu `stopped`
- [ ] CloudWatch Alarm koji ste kreirali za troskove.


[:fast_forward: Class Notes](/devops-mentorship-program/03-march/week-5-280323/00-class-notes.md)
[:fast_forward: Additional Reading](/devops-mentorship-program/03-march/week-5-280323/02-additional-reading.md)
[:fast_forward: HOME - README.md](../../../README.md)
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)
