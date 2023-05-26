# AWS CodeDeploy

- U ovom dijelu dokumentacije ukratko je objasnjen koncept AWS CodeDeploy servisa, za sto se koristi, na koji nacin funkcionise ...

## :memo: Sta je CodeDeploy i kako funkcionise? 

- **AWS CodeDeploy** je deployment servis koji automatizira deployment aplikacije na Amazon EC2 instance, on-premises instance, serverless Lambda funkcije ili Amazon ECS servise. U sustini mozemo deploy-ati: kod, serverless AWS Lambda funkcije, web i konfiguracione fajlove, executables, pakete, skripte i multimedijalne fajlove. 

- CodeDeploy servis moze deploy-ati aplikacioni sadrzaj koji ce runirati na serveru i spremljen je u S3 Bucketu, GitHub ili Bitbucket repozitoriju. CodeDeploy moze takodjer deploy-ati serverless Lambda funkciju. Ne trebamo napraviti izmjene na nas vec postojeci kod prije nego mozemo koristiti CodeDeploy servis.

- CodeDeploy uveliko olaksava da brze izbacimo nove feature, updateujemo verziju AWS Lambda funkcije, izbjegnemo downtime tokom deploymenta aplikacije itd., vise o tome mozete procitati na [oficijelnoj dokumentaciji.](https://docs.aws.amazon.com/codedeploy/latest/userguide/welcome.html)

- Funkcionise u skladu sa raznim sistemima za configuration management, source control, CI/CD i continuous deployment.

## :memo: Koji su benefiti CodeDeploy servisa?

- CodeDeploy nam dopusta server, serverless i container applications deployment. Bitno je spomenuti automated deployments odnosno CodeDeploy u potpunosti automatizira nas deployment preko development, test i produkcijskog environmenta. Takodjer CodeDeploy skalira sa nasom infrastrukturom tako da mozemo deployati na jednu ili doslovno hiljade instanci. Mozemo automatski ili rucno stopirati deployment i odraditi roll back ako imamo nekih greski. O puno vise benefita mozete procitati na [oficijelnoj dokumentaciji.](https://docs.aws.amazon.com/codedeploy/latest/userguide/welcome.html)

## :bulb: Praktican primjer - Deployment Amazon ECS servisa

- Kako mozemo deployat Amazon ECS aplikaciju koju smo prethodno kreirali? Prvi korak bi bio da update-ujemo nasu aplikaciju tako sto modifikujemo njen task definition file sa novim tag-om. Dalje koristimo CodeDeploy da deployamo update. 

- Tokom deploymenta CodeDeploy instalira nas update u novi replacement task set. Zatim prebacuje production traffic od originalne verzije naseg Amazon ECS servisa, koji je u svom originalnom task set-u na updateovanu verziju unutar replacement task set-a. 

- Takodjer tokom Amazon ECS deploymenta, CodeDeploy koristi load balancer koji je iskonfigurisan sa 2 target grupe i jednim production traffic listenerom. Dijagram ispod - preuzet sa [oficijelne AWS dokumentacije](https://docs.aws.amazon.com/codedeploy/latest/userguide/tutorial-ecs-deployment.html) pokazuje kako load balancer, production listener, target grupe i nas Amazon ECS aplikacija su 'povezani' prije nego deployment zapocne. 

![codedeployment-1](/aws/aws-service-notes/files/codedeployment-1.png)

- Nakon uspjesnog deploymenta, produkcijski traffic listener posluzuje traffic nasem novom replacement task setu i originalni task set je terminiran. Iduci dijagram pokazuje kako nasi resursi su povezani nakon uspjesnog deploymenta, a za vise informacija o tome sta se desava tokom Amazon ECS deploymenta provjerite [oficijelnu dokumentaciju.](https://docs.aws.amazon.com/codedeploy/latest/userguide/deployment-steps-ecs.html#deployment-steps-what-happens)

![codedeployment-2](/aws/aws-service-notes/files/codedeployment-2.png)

- Amazon ECS servis mozemo deployati putem AWS CLI-a, za vise informacija o tome mozete provjeriti [creating a servise using a blue/green deployment.](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/create-blue-green.html)

## :moneybag: Troskovi

- Za CodeDeploy na EC2 instancu, Lambdu i ECS nema troskova dok za CodeDeploy On-Premises placamo $0.02 po on-premises instance update - primjer deployment na tri instance je jednak tri instance update-a. Nema minimalnih troskova ili upfront commitments a vise o pricingu se moze procitati na [oficijelnoj AWS dokumentaciji.](https://aws.amazon.com/codedeploy/pricing/)

## :question: Troubleshooting & FAQs

- Na [troubleshooting](https://docs.aws.amazon.com/codedeploy/latest/userguide/troubleshooting.html) linku koji vodi prema oficijelnoj AWS dokumentaciji mozete procitati o tome kako da se rijese EC2/On-premises ili AWS Lambda deployment issues i mnogo drugih korisnih stvari. Preporuka je da se procitaju i na neki nacin dodaju u svoj knowledge base za buducu referencu, jer pored toga sto treba dokumentirati svoje error-e na koje nailazimo u radu, dobro je znati da ima dokument na oficijelnoj dokumentaciji koji se moze referencirati u slucaju problema. 
- Na drugom linku imate [Frequently Asked Questions](https://aws.amazon.com/codedeploy/faqs/) koji u sustini oslovljavaju pitanja poput sta je deployment group, revizija, AppSpec file i mnogo toga korisnog. 