# 📝 Week 9 - Class notes
## 📅 Date: 11.04.2023.

## Amazon Simple Storage Service (Amazon S3)
Amazon S3 je object-level storage. Object-levle storage znaci da se podaci pohranjuju u "objektima" a ne hijerarhijskog strukturi direktiroja. Svaki objekt ukljucuje podatke i metapodatke koji opisuju objekt. Svaki objekt u S3-u ima svoj jedinstveni kljuc (key) koji se koristi za identifikaciju objekta. 
Amazon S3 mozemo koristiti za:
- Backup i arhiviranje podataka - Amazon S3 je dizajniran na nacin da omogucava 99.999999999% (11 9') durability i  99.99% availaiblity. 
- Media storage i streaming 
- Static website hosting
- Data lake

![S3 Object URL](/devops-mentorship-program/04-april/week-8-040423/files/s3-object-url.png)

### Tipovi S3 Storage-a

- S3 Standard - Za generalnu upotrebu i za podatke kojima cesto prostupamo
- S3 Standard-Infrequent Access (S3 Standard-IA) - Za podatke ciji durability nam je bitan ali im ne pristupamo toliko cesto
- S3 One Zone-Infrequent Access (S3 One Zone-IA) - Za podatke ciji durability nam je bitan ali im ne pristupamo toliko cesto. Razlika izmedju S3 Standard-IA i S3 One Zone-IA je sto S3 One Zone-IA ima samo jednu Availability Zone dok S3 Standard-IA ima 3 Availability Zone-a.
- S3 Glacier Instant Retrieval - za podatke koji su nam jako bitni ali im ne pristupamo toliko cesto. Podaci se mogu vratiti u roku od 1 minuta.
- S3 Glacier Flexible Retrieval - za podatke koji su nam jako bitni ali im ne pristupamo toliko cesto. Podacima mozete pristupiti koristeci expedited, standard ili bulk retrieval opcije. Expedited retrieval je najbrzi ali najskuplji, standard retrieval je srednje brz ali jeftiniji, bulk retrieval je najjeftiniji ali najsporiji.
- S3 Glacier Deep Archive - za podatke koji su nam jako bitni ali im ne pristupamo toliko cesto, odnosno mozemo ih arhivirati na duze vrijeme. Podaci se mogu vratiti u roku od 12 sati.
- S3 intelligent tiering - za podatke za koje nismo sigurni na koji nacim im pristupmao, S3 intelligent tiering automatski prebacuje podatke iz S3 Standard u S3 Standard-IA ili S3 One Zone-IA u zavisnosti od frekvencije pristupa podacima.

### S3 kontrola pristupa
![S3 Access Control](/devops-mentorship-program/04-april/week-8-040423/files/s3-access-control.png)

## IAM Deep Dive
[:infinity: DevOps Learning Path - AWS Identity and Access Management (IAM)](/aws/aws-service-notes/iam.md)

## 📹 Session recordings
- [**WEEK-9-tier-1-group-1 video session recording**](https://youtu.be/U7cS2TVmPtw)
- [**WEEK-9-tier-1-group-2 video session recording**]()

## 📖 Reading materials
- [📙 Amazon S3 FAQs](https://aws.amazon.com/s3/faqs/)
- [:infinity: DevOps Learning Path: Identity Access Management Service (IAM)](/aws/aws-service-notes/iam.md)
- [📙 Actions, resources, and condition keys for AWS services](https://docs.aws.amazon.com/service-authorization/latest/reference/reference_policies_actions-resources-contextkeys.html)
- [🌐  Complete AWS IAM Reference](https://iam.cloudonaut.io/)
- [📚 Effective IAM for AWS](resources/aws-books/effective-iam-for-aws.pdf)
- [📚The Practical IAM Guide](/resources/aws-books/the_practical_aws_iam_guide_v1.0.pdf)
- [📹 AWS re:Invent 2021 - A least privilege journey: AWS IAM policies and Access Analyzer](https://youtu.be/pKPiPplJNak)
- [📹 AWS re:Inforce 2022 - Security best practices with AWS IAM (IAM201)](https://youtu.be/SMjvtxXOXdU)
- [📹 AWS re:Invent 2018: [REPEAT 1] Become an IAM Policy Master in 60 Minutes or Less (SEC316-R1)](https://youtu.be/YQsK4MtsELU)
- [📹 IAM Policy Evaluation Series: AWS IAM policy language explaine - Part 1](https://youtu.be/qsF6Kauh2J4)
- [📹 IAM Policy Evaluation Series: policy evaluation chains | Amazon Web Services](https://youtu.be/71-Gjo6a5Cs)
- [📹 ]()



[:fast_forward: Class Notes](/devops-mentorship-program/04-april/week-9-110423/00-class-notes.md)
[:fast_forward: Additional Reading](/devops-mentorship-program/04-april/week-9-110423/02-additional-reading.md)
[:fast_forward: HOME - README.md](../../../README.md)
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)
