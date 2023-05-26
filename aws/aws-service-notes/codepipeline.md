# AWS CodePipline

- U ovom dijelu dokumentacije ukratko je objasnjen CodePipeline servis, za sto se koristi, nacin na koji funkcionise sa prakticnim primjerom, kakvi su troskovi prilikom upotrebe i naravno gdje mozemo pronaci FAQ ili provjeriti neka rjesenja za najcesce error-e koje dobijemo prilikom upotrebe.

## :memo: Sta je AWS CodePipeline?

- Po [oficijelnoj AWS dokumentaciji](https://docs.aws.amazon.com/codepipeline/latest/userguide/welcome.html) CodePipeline je CD (continuous delivery) servis koji koristimo da modeliramo, vizualiziramo i automatiziramo korake potrebne za release naseg softwarea. Koristeci ovaj servis mozemo veoma brzo da modeliramo i konfigurisemo razlicite faze (stages) software release procesa. CodePipeline automatizira korake potrebne da radimo izmjene i release naseg softvera kontinuirano, zbog toga "CD" u nazivu za - continuous delivery.  

- U ovom dijelu [oficijelne AWS dokumentacije](https://docs.aws.amazon.com/codepipeline/latest/userguide/concepts-continuous-delivery-integration.html) imate opsirno objasnjenje o tome sta je CI/CD, te kako se CodePipeline uklapa u neku siru sliku u tom procesu. 

## :bulb: Praktican primjer 

- U nastavku, preuzeto iz [oficijelne AWS dokumentacije](https://docs.aws.amazon.com/codepipeline/latest/userguide/concepts-devops-example.html) imamo objasnjenje kako funkcionise "two-stage" pipeline i kako mozemo kombinirati CodePipeline sa GitHubom i sl.

- Naprimjer, ovaj pipeline moze imati prvi **source stage** koji imenujemo **Source** i drugi stage koji mozemo imenovati **Prod**. Sta pipeline radi? Ovaj pipeline updateuje aplikaciju sa najnovijim izmjenama i deploya najnoviju verziju. Prije nego deploya najnoviju verziju, pipeline build-a i testira web aplikaciju. U primjeru ispod takodjer cemo vidjeti kako je grupa developera postavila infrastructure template i source code za web aplikaciju unutar GitHub repozitorija pod nazivom MyRepository. 

![codepipeline-diagram](/aws/aws-service-notes/files/codepipeline-diagram.png)

- Uzmimo npr. da developer push-a neki fix na index page web aplikacije i desi se sljedece:

- **1** - Vidimo da aplikacijski source code se nalazi unutar repozitorija koji je konfigurisan kao GitHub source action unutar pipeline-a. Kada developeri pushaju commits na repozitorij, CodePipeline detektuje izmjenu i trigeruje pipeline koji pocinje izmjene na **Source Stage-u.**

- **2** - Dalje, GitHub source action se zavrsava uspjesno i pojavljuju se **output artifacts** tj. aplikacijski fajlovi iz repozitorija koji su dalje iskoristeni kao **input artifacts** na kojim se radi u **Prod Stage.**

- **3** - Sad pipeline execution prelazi iz **Source Stage** na **Prod Stage.** Prva akcija na ovom stage-u pokrece build projekt kreiran unutar CodeBuild-a a koji je konfigurisan kao build action unutar pipeline-a. Build task povlaci build environment image i gradi web aplikaciju u virtual container.

- **4** - Sljedeca akcija unutar **Prod Stage-a** je unit test projekt kreiran u CodeBuildu i konfigurisan kao test akcija unutar pipeline-a. 

- **5** - Nakon sto kod prodje test, ako je deploy akcija uspjesna iduca akcija je integracijski test. Ova akcija poziva shell skripte koje instaliraju i pokrecu test tool, kao sto je npr. link checker, prema web aplikaciji. Poslije toga ako je sve uspjesno proslo, output je izgradjena web aplikacija i rezultati prethodnih testova. 

## :moneybag: Troskovi

- Sa AWS CodePipeline nemamo placanja unaprijed, platimo onoliko koliko koristimo. Prema [oficijelnoj dokumentaciji](https://aws.amazon.com/codepipeline/pricing/) CodePipeline kosta $1 po aktivnom pipelineu mjesecno, s tim da su pipeline-i besplatni prvih 30 dana nakon kreiranja kako bi se potaklo i ohrabrilo eksperimentisanje. 

- Free Tier ima jedan besplatan pipeline svaki mjesec. 

## :question: Troubleshooting & FAQs

- Na [troubleshooting](https://docs.aws.amazon.com/codepipeline/latest/userguide/troubleshooting.html) linku koji vodi prema oficijelnoj AWS dokumentaciji mozete procitati nekih 10-ak najcescih error-a. Preporuka je da se procitaju i na neki nacin dodaju u svoj knowledge base za buducu referencu, jer pored toga sto treba dokumentirati svoje error-e na koje nailazimo u radu, dobro je znati da ima dokument na oficijelnoj dokumentaciji koji se moze referencirati u slucaju problema. 
- Na drugom linku imate [Frequently Asked Questions](https://aws.amazon.com/codepipeline/faqs/) koji u sustini oslovljavaju pitanja poput sta je artifakt, mozemo li kopirati postojeci pipeline, sta je tranzicija itd.  