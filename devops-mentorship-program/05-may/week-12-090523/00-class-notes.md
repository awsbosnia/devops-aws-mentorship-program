# 📝 Week 12 - Class notes
## 📅 Date: 09.05.2023.

## AWS Lambda  

## Amazon Event Bridge

## Amazon Simple Queue Service (SQS)
## Amazon Simple Notification Service (SNS)

## Amazon API Gateway

- API Gateway omogucava kreiranje i upravljanje APIs (engl. *Application Programming Interface*). API predstavlja softverski mehanizam koji pojednostavljuje *development* na nacin da radi apstrakciju detalja implementacije, prikazuje samo one detalje i objekte koji su potrebni developeru, te uspostavlja nacin komunikacije izmedju pruzatelja usluge i korisnika.

- API Gateway je *highly available* i *scalable* servis.

- **API Gateway Endpoint types**
    1. *Regional* - klijenti se nalaze u istoj regiji
    2. *Edge-optimized* - koristi CloudFront
    3. *Private* - moze se koristiti samo unutar VPC

- **RESTful API Gateway**
    1. *REST API*
        - developer ima punu kontrolu nad zahtjevima i odgovorima
    2. *HTTP API*
        - jednostavniji i jeftiniji za koristenje
        - manje kasnjenje
        - ne pruzaju mogunosti potpune kontrole nad zahtjevima i odgovorima

- **Autorizacija i autetifikacija API Gateway**
    1. *None* - otvoreni pristup
    2. *IAM* - koristenje *IAM* servisa i *IAM Credentials* za pristup
    3. *JWT* - JSON Web Tokens se koriste u pozadini za provjeru autenticnosti zahtjeva kada se koristi OpenID Connect ili OAuth 2.0. REST APIs mogu koristiti Amazon Cognito kao JWT *authorizer*. 
    4. *Lambda authorizers* - koristi se lambda funkcija za provjeru tokena ili se zahtijevaju parametri za odobravanje pristupa

- **Monitoring tools**
    1. *CloudWatch* - po *default*-u API Gateway svaku minutu salje odredjene metrike prema *CloudWatch* servisu
    2. *AWS X-Ray* - prati i analizira zahtjeve *end-to-end*
    3. *AWS Config* - procjena i pregled azuriranja konfiguracije
    4. *AWS CloudTrail* - upravlja pozivima koji su upuceni prema API Gateway-u od strane korisnika, role ili nekog drugog AWS servisa.

## 📹 Session recordings
- [**WEEK-12-tier-1-group-1 video session recording**](https://youtu.be/xEhI5pEvgXM)
- [**WEEK-12-tier-1-group-2 video session recording - PART I**](https://youtu.be/Fb6R3YXPxv0)  
- [**WEEK-12-tier-1-group-2 video session recording - PART II**](https://youtu.be/xysv_eSb1tQ)  

## 📖 Reading materials



[:fast_forward: Class Notes](/devops-mentorship-program/05-may/week-12-090523/00-class-notes.md)
[:fast_forward: Additional Reading](/devops-mentorship-program/05-may/week-12-090523/02-additional-reading.md)
[:fast_forward: HOME - README.md](../../../README.md)
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)
