# AWS CodeBuild

- U ovom dijelu dokumentacije objasnjen je **AWS CodeBuild** servis, u sustini - zasto ga upotrebljavamo, kako se pokrece i funkcionise, neki big picture o tome kako zapravo radi u praksi - sa linkom prema nekim najcescim problemima sa kojima se susrecemo i kako ih rijesiti. 

## :memo: Sta je AWS CodeBuild?

- **AWS CodeBuild** je menadzovan build servis na cloudu, odnosno **CI (continuous integration)** servis koji kompajlira nas source code, pokrece unit testove i proizvodi artefakte koji su spremni za deployment.

- CodeBuild eliminise potrebu za provizioniranjem, menadzovanjem i skaliranjem nasih build servera, pruza nam prepakiran build environment za popularne programske jezike i build toolove kao sto su: Apache Maven, Gradle i drugi. Mozemo takodjer customizirati build environment unutar CodeBuilda da koristimo svoje vlastite build toolove. Code Build automatski skalira kako bi podrzao peak build requests. 

- Treba spomenuti **tri glavna benefita:** 

    - **U potpunosti menadzovan** - znaci da eliminira potrebu da se postavi, patch-uje, odradi update ili menadzuje vlastiti build server. 
    - **On demand** - znaci da CodeBuild skalira *on demand* kako bi podrzao build zahtjeve i potrebe. Platimo samo za broj minuta koji potrosimo koristeci servis.
    - **Out of the box** - znaci da pruza prekonfigurisan build environment za najpopularnije programske jezike. Sve sto trebamo je point-ati prema nasoj build skripti kako bi zapoceli svoj prvi build. 

## :memo: Kako se koristi? 

- Mozemo koristiti AWS CodeBuild ili AWS CodePipeline konzolu da pokrenemo CodeBuild, naravno da bi automatizirali pokretanje mozemo koristiti AWS CLI ili AWS SDKs.

- Na dijagramu preuzetom iz [oficijelne dokumentacije](https://docs.aws.amazon.com/codebuild/latest/userguide/welcome.html) mozemo vidjeti dodan CodeBuild kao build ili test stage pipeline-a unutar AWS CodePipeline-a. 

![codebuild-pipeline](/aws/aws-service-notes/files/codebuild-pipeline.png)

- Za razliku od **CodeBuild-a koji je CI (continuous integration) servis**, **CodePipeline je CD (continous delivery) servis** koji koristimo da modeliramo, vizualiziramo i automatiziramo korake potrebne za release naseg koda. **Pipeline** je workflow konstrukt koji opisuje kako izmjene u kodu prolaze kroz razlicite procese. U sustini mozemo automatizirati nas release proces koristeci AWS CodePipeline da testiramo nas kod i onda pokrecemo nas build sa AWS CodeBuild. Vise o tome imate na [oficijelnoj dokumentaciji.](https://docs.aws.amazon.com/codebuild/latest/userguide/how-to-create-pipeline.html)

- Unutar CodeBuild konzole mozemo pronaci resurse, poput repozitorija, build projekata, aplikacija za deployment i naravno pipeline-a. Napomena da samo vidimo resurse za koje imamo permisije da vidimo. Vise informacija o tome kako mozemo provjeriti resurse unutar CodeBuild konzole [imamo na oficijelnoj dokumentaciji.](https://docs.aws.amazon.com/codebuild/latest/userguide/console-resources.html)

## :bulb: Praktican primjer 

- Na dijagramu ispod vidimo sta se zapravo desava kada pokrenemo build unutar CodeBuild servisa:

![codebuild-diagram](/aws/aws-service-notes/files/codebuild-diagram.png)

- **1** - prvo naravno dodajemo build project CodeBuildu. Taj **build project** ukljucuje informacije kako ce build runirati, tj. gdje ce biti source code, koji build environment da se koristi, koje komande ce biti izvrsene i gdje ce biti spremljen build output. **Build environment** predstavlja kombinaciju operativnog sistema, runtime-a programskog jezika i alata koje CodeBuild koristi da pokrene build. 

- **2** - dalje vidimo da CodeBuild koristi **build project** da kreira **build environment.**

- **3** - poslije toga CodeBuild preuzima (downloaduje) source code u build environment i zatim koristi **buildspec** ili build specifikaciju definisanu unutar build projekta ili ukljucenu direktno u source code. **Buildspec** mozemo zamisliti kao kolekciju build komandi i drugih podesavanja u YAML formatu koji CodeBuild koristi da pokrene build. 

- **4** - Ako imamo neki build output, build environment ga uploaduje na S3 bucket. Build environment takodjer moze izvrsiti taskove koje preciziramo unutar buildspec-a, npr. da posalje build notifikaciju na Amazon SNS. 

- **5** - Dok je build pokrenut, build environment salje informacije CodeBuildu i naravno loguje se event unutar CloudWatch Logs.

- **6** - Dalje, dok je build pokrenut mi mozemo koristiti CodeBuild konzolu, AWS CLI ili AWS SDK da dobijemo neke povratne informacije o build-u od CodeBuild servisa, CodePipeline ili neke druge informacije iz Amazon CloudWatch Logova.

- Dijagram i prethodnih 6 koraka o tome kako AWS CodeBuild funkcionise su preuzeti iz [oficijelne dokumentacije.](https://docs.aws.amazon.com/codebuild/latest/userguide/concepts.html)

## :moneybag: Troskovi

- Kad su troskovi u pitanju AWS CodeBuild koristi jednostavan **pay-as-you-go** model. Sta to znaci? Znaci da nema placanja unaprijed (upfront costs) ili minimalnih fees, nego iskljucivo placamo samo za resurse koje koristimo, npr. naplacuju nam se compute resursi na osnovu toga koliko dugo nasem buildu treba da se izvrsi. Vise o pricingu imamo [na oficijelnoj dokumentaciji.](https://aws.amazon.com/codebuild/pricing/)

## :question: Troubleshooting & FAQs

- Na [troubleshooting](https://docs.aws.amazon.com/codebuild/latest/userguide/troubleshooting.html) linku koji vodi prema oficijelnoj AWS dokumentaciji mozete procitati nekih 20-ak najcescih error-a. Preporuka je da se procitaju i na neki nacin dodaju u svoj knowledge base za buducu referencu, jer pored toga sto treba dokumentirati svoje error-e na koje nailazimo u radu, dobro je znati da ima dokument na oficijelnoj dokumentaciji koji se moze referencirati u slucaju problema. 
- Na drugom linku imate [Frequently Asked Questions](https://aws.amazon.com/codebuild/faqs/) koji u sustini oslovljavaju pitanja poput kako da set-ujemo svoj prvi build i plejadu drugih relevantnih pitanja za bolje razumijevanje ovog servisa. 




