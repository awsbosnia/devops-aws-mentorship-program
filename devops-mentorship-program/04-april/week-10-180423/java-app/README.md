# S3 Demo Webapp

## Opis

Svrha ove aplikacije je da prikaže upotrebu AWS SDK-a, specifično oko pristupa i upravljanja S3-a, kao i upotrebe Secret Manager-a kako ne bi smo imali šifre i slične osetljive podatke jasno vidljive.

## Build

Preduslov za build-anje aplikacije je da su na sistemu instalirani Java 17 i Maven.

Kako bi build-ali aplikaciju potrebno je da pozovemo:
```
mvn clean package
```

Nakon što se komanda izvrši njen rezultat, odnosno artifakt, ćemo naći na putanji koja se ispiše:
```
[INFO] Building jar: {PUTANJA DO PROJEKTA}/target/s3-webapp-demo-0.0.1-SNAPSHOT.jar
```
## Pokretanje

Kako bi se aplikacija pokrenula na EC2 instanci (koja ima podešenu rolu koja ima pristup S3-u i Secret Manager-u) potrebno je pozvati:
```
java -jar s3-webapp-demo-0.0.1-SNAPSHOT.jar
```
Za lokalno pokretanje potrebno je pozvati:
```
export AWS_REGION=eu-central-1
java -jar -DAWS_ACCESS={VREDNOST} -DAWS_SECRET={VREDNOST} -Dspring.profiles.active=local target/s3-webapp-demo-0.0.1-SNAPSHOT.jar
```
U oba slučaja, aplikacija je uspešno pokrenuta kada vidimo nešto slično ispisano:
```
2023-04-11T09:44:27.226-04:00  INFO 20081 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-04-11T09:44:27.244-04:00  INFO 20081 --- [           main] d.m.p.s.S3WebappDemoApplication          : Started S3WebappDemoApplication in 6.834 seconds (process running for 7.488)
```
## Pristupanje

Dovoljno je da u omiljenom pretraživaču otvorimo http://localhost:8080/users