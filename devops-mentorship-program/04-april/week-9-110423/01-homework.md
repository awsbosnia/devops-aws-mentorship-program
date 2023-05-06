# 📝 Week 9 - Homework / Task assigment  
## 📅 Date: 11.04.2023.  
## TASK-8: Implement SSL Let's Encrypt, migrate to AWS SSM
Za kompletiranje Task-8 potrebno je napraviti sljedece:
**IAM User 1** ce svoje resurse da kreira unutar `eu-central-1` regiona.
**IAM User 2** ce svoje resurse da kreira unutar `us-east-1` regiona.
**IAM User 3** ce svoje resurse da kreira unutar `eu-west-1` regiona

Svaki do AWS resursa koje kreirate pored taga `Name` mora sadrzavati i tagove `CreatedBy: Ime Prezime` i `Email:vas@email.com`


- [ ] Od AMI image `ec2-ime-prezime-web-server` napravite novu EC2 instancu `ec2-ime-prezime-task-8`

- [ ] Kreirati DNS record `<ime>-<prezime>.awsmostar.com` za Hosted Zone awsmostar.com (`Hosted zone ID:
Z04001321ARL17ES9XJBO`) koji ce da pokazuje na EC2 instancu koju ste krairali. Za kreiranje DNS zapisa korisite AWS CLI AWS kredencijale koji se nalaze unutar [sljedeceg excel file-a](https://docs.google.com/spreadsheets/d/1YzmzW7vRNn4Q6LKNGs3LA7NSyZO75W_8Itk_3jN0bY4/edit#gid=307496750). AWS CLI konfigurisite tako da koristite named profile aws-bosnia. Kako da podesite AWS CLI i vise o CLI profilima mozete vidjeti [ovdje](https://docs.aws.amazon.com/toolkit-for-visual-studio/latest/user-guide/keys-profiles-credentials.html)
Za ovaj dio taska mozete da iskorisite [change-resource-record-sets](https://docs.aws.amazon.com/cli/latest/reference/route53/change-resource-record-sets.html#) AWS CLI komandu. Kada ste dodali novi DNS record njegov Name i Value ispiste uz pomoc komande `aws route53 list-hosted-zones` i alata `jq` gdje cete prikazati samo Name i Value za DNS record koji ste vi kreirali odnosno za vase domensko ime.

- [ ] Na EC2 instanci `ec2-ime-prezime-task-8` kreirati Let's Encrypt SSL certifikat za vasu domenu. Neophodno je omoguciti da se nodejs aplikaciji moze pristupiti preko linka `https://<ime>-<prezime>.awsmostar.com`, to verifikujte skrinsotom gdje se vidi validan certifikat u browseru.

- [ ] Omoguciti autorenewal SSL certifikata 

- [ ] Koristeci openssl komande prikazati koji SSL certitikat koristite i datum njegovog isteka. Probajte korisitit razlicite openssl komande (HINT: Biljeskama za office hours imate knjigu u kojoj mozete pronaci recepte za razlicite openssl komande)

- [ ] Importujte Lets Encrypt SSL certifikat unutar AWS Certified Managera.

- [ ] Kreirajte Load Balancer gdje cete na nivou Load Balancera da koristite SSL cert koji ste ranije importovali. (Hint: NGINX config je nophodno auzrirati). Load Balancer u pozadini koristi EC2 instancu `ec2-ime-prezime-task-8`, nije potrebno kreirati ASG. 

- [ ] Koristeci openssl komande prikazati koji SSL certitikat koristite za vasu domenu i datum njegovog isteka. 

- [ ] Kreirajte novi SSL certifikat unutar AWS Certified Managera, azurirajte ALB da koristi novi SSL cert koji ste kreirali.

- [ ] Koristeci openssl komande prikazati koji SSL certitikat koristite za vasu domenu i datum njegovog isteka. 

- [ ] Kada zavrsite sa taskom kreirajte AMI image pod nazivom `ami-ec2-ime-prezime-task-8` i terminirajte resurse koje ste koristili za izradu taska. 
 
Task se smatra kompletiranim kada kreirate PR koji sadrzi koristene komande u tekstualno formatu i skrinsote na kojima se vidi izvrsavanje tih komandi i dobijeni output. 


[:fast_forward: Class Notes](/devops-mentorship-program/04-april/week-9-110423/00-class-notes.md)
[:fast_forward: Additional Reading](/devops-mentorship-program/04-april/week-9-110423/02-additional-reading.md)
[:fast_forward: HOME - README.md](../../../README.md)
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)
