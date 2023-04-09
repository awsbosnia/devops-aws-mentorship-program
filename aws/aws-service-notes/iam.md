# Identity Access Management (IAM) 
**Identinty Access Management (IAM)** je AWS servis koji vam omogucava **autentifikaciju** i **autorizaciju** za rad sa AWS servisima unutar vaseg AWS racuna.

Kada posaljete zahtjev prema AWS API-iju, bilo da radite sa servisima koristeci AWS konzolu,SDKs (Software Development Kit) ili AWS Command Line Interface (AWS CLI), IAM servis je taj koji verifikuje vas identitet i provjerava da li vam je dozvoljeno izvrsavanje zeljene akcije.

IAM dakle kontrolise **KO (autentifikacija)** moze pristupiti vasem AWS racunu  i **KOJE AKCIJE (autorizacija)** moze napraviti unutar AWS racuna.

## Jezik IAM Policy-a
IAM Policy je napisan u JSON formatu. Svaki IAM Policy se sastoji od jednog ili vise **Statements**. Unutar **Statement** dijela mozemo imati sljedece elemenate:
* **Effect** - `The Why` - Oznacava da li ce request biti Allow / Allowed ili Deny / Denied
* **Principal** - `The Who` - Ovaj element oznacava entitet / Entity kojem je pristup dozvoljen ili odbijen, primjetiti cete da ponekad Principa element nije sadrzan unutar Policy Statemnt-a. Ovaj element je jedino dostupan unutar **resource based** i **vpc-endpoint** polisija.  Postoje cetiri moguca tipa **Principa** elementa:
    - **AWS** - Koristite AWS tip Principal Elementa da referencirate druge AWS racune, role, korisnike kao i Session Principals. AWS racun unutar principal elemnta moze biti napisan na dva nacina:
        - `arn:aws:iam::123456789012:root` - ARN koji sadrzi ID AWS racuna
        - `123456789012` - ID AWS racuna bez `-` znakova
    U oba ova slucaja principal je AWS account sa ID-em `123456789012`
    - **Service** - Service Principal se koristi na nivou resursa da dozvoli pristup AWS servisu. Kada napisete AWS policy koji koristi Service Principal type obicno bi trebali ukljuciti Condition key elemnet koji specificira source ARN ili source Account odakle request dolazi. Npr:
    ```json
    {
        "Effect": "Allow",
        "Principal": {
            "Service": "cloudtrail.amazonaws.com"
        },
        "Action": "s3:PutObject",
        "Resource": "arn:aws:s3:::MOJ-S3-BUCKET/*",
        "Condition": {
            "ArnEquals": {
                "aws:SourceArn": "arn:aws:cloudtrail:us-east-1:123456789012:trail/MOJ-TRAIL"
            }
        }
    }
    ```
    To se koristi iz razloga da se izbjegne [**confused deputy problem**](https://docs.aws.amazon.com/IAM/latest/UserGuide/confused-deputy.html) o cemu smo vise pisali u nastavku u dijelu [Important Actions - iam:PassRole](##Important-Actions-iam:PassRole)
    - **Federated** - Koristi se samo unutar IAM Trust Policies kako bi se dodijelio pristup za Web Idenity Session Principles od strane Web Idenity provajdera ili SAML Session Principle od strane SAML provajdera. 
    - **Canonical User** (ne bi trebao da se koristi jer samo nekoliko slucajeva zahtijeva upotrebu ovog tipa Principal elementa)
* **NotPrincipal** - Postoji i obrnuta varijanta **Principal** elementa. Medjutim nije ga preporuceno koristiti jer danas ne postoji use-case unutar AWS-a gdje bi ovaj element bio potreban. Umjesto toga mozete imati policy koji izgleda kao na primjeru ispod:
```json
{
    "Effect": "Deny",
    "Principal": "*",
    "Action": "s3:GetObject",
    "Resource": "arn:aws:s3:::MOJ-S3-BUCKET/*"
    "Condition": {
        "ArnNotEquals": {
            "aws:arn": "arn:aws:iam::123456789012:role/MOJA-ROLA"
        }
    }
}
```  
* **Action** - `The What` - Oznacava jednu ili vise Akcija koje su dozvoljene / Allowed ili odbijene / Denied. Set akcija koje su navedene unautar Action dijela se evaluira kao logicko `OR`. Postoje razlicite reporezentacije Action elementa, npr. `s3:PutObject`, `s3:*`, `s3:Put*`, `s3:*Object`, `s3:*Bucket`, `*`. Vrijednost Action elementa je podjeljena u dva dijela, `service prefix na lijevoj strani : ime akcije na desnoj strani`. Npr. `s3:PutObject` oznacava da je `service prefix` `s3` a `ime akcije` je `PutObject`. `*` oznacava wildcard za sve akcije.
**NotAction** - Obrnuta varijanta **Action** elementa. Oznacava da je akcija koja je navedena u **NotAction** elementu zabranjena / Denied. Vise akcija unutar **Not Action** elementa se evaluiraju kao logicko `NOR`.
* **Resource** - `The Where` - Oznacava jedan ili vise AWS resursa na koje se odnosi / primjenjuje Policy Statement. Resursi se specificiraju koristeci [ARN (Amazon Resource Name)](https://docs.aws.amazon.com/IAM/latest/UserGuide/reference-arns.html). ARN se pise u formatu `arn:partition:service:region:account-id:resource-type/resource-id`. Kada pogledate ARN razlicitih AWS Servisa vidjeti cete da nemaju svi servisi sve dijelove ARN-a. Npr: ARN S3 bucketa `arn:aws:s3:::MOJ-S3-BUCKET` ne ukljucuje account-id ili region. Umjesto kopletnog arn koda mozete korisiti i `*` wildcard da zamijenite dio ili citav ARN. **Resource element se evaluira kao logicko `OR` unutar jednog Statement-a.**
    - **ARN Fromat pojasnjenje:**
        - `partition` - odnosi se na to gdje se AWS resurs nalazi, npr. `aws` za AWS Region, `aws-cn` za AWS resurse u Kini, `aws-us-gov` za AWS Goverment resurse.
        - `service` - govori nam o kojem AWS servisu se radi
        - `region` - kod regiona u kojem se nalazi resurs. Npr. `us-east-1` za US East (N. Virginia) Region
        - `account-id` - ID AWS racuna bez `-` znakova
        - `resource-type` - govori nam o tipu resursa, npr. `s3` za S3 servis, `ec2` za EC2 servis itd.
        - `resource-id` - govori nam o ID-u resursa, npr. `MOJ-S3-BUCKET` za S3 Bucket, `i-1234567890abcdef0` za EC2 Instance itd. `resurce-id` moze biti Ime resursa, ID resursa ili [putanja](https://docs.aws.amazon.com/IAM/latest/UserGuide/reference-arns.html#arns-paths).  

* **NotResource** - Predstavlja inverzni element **Resource** elementa. Oznacava da je resurs koji je naveden u **NotResource** elementu zabranjen / Denied. Vise resursa unutar **NotResource** elementa se evaluiraju kao logicko `NOR`. Na njega mozete gledati i kao na **Exception** element. Ako pogledamo primjer:
    ```json
    {
        "Sid": "MojStatement"                            
        "Effect": "Denny",                                  # <- Zabrani akcije 
        "Action": ["s3:PutObject",
                "s3:GetObject"],
        "NotResource": "arn:aws:s3:::IME-S3-BUCKETA/*"      # <- Osim na resursu koji je naveden u NotResource elementu
    } 
    ```
    Budite pazljivi sa koristenjem **NotResource** sa **Allow Effect** jer na taj nacin dodjeljuju pristup svim resursima osim onih koji su navedeni u **NotResource** elementu.  

* **Condition** - `The When` -  Condition element oznacava konektekst unutar kojeg ce pristup biti razmotren odnosno kada ili pod kojim uslovom ce policy biti primjenjen. Npr. Statement u primjeru ispod se primjenjuje samo ukoliko je vrijednost za AWS sigurni prenos podataka (AWS Secure Transport) jednaka `true`, odnosno ako je request poslan preko HTTPS/TLS protokola. Polisije koji imaju **Condition** element mozete citati i na nacin da kazete: "Izvrsi sljedece akcije ali samo ako..."

    ```json
            {
                "Effect": "Allow",
                "Principal": {
                    "AWS": "arn:aws:iam::11111111:role/ImeIAMRole"
                },
                "Action": ["s3:PutObject",
                        "s3:GetObject"],
                "Resource": "arn:aws:s3:::IME-S3-BUCKETA/*",
                "Condition": {
                    "Bool": {
                        "aws:SecureTransport": "true"
                    }
                }
            }
    ```
    **Condition** element moze da se sastoji od nekoliko elemenata. Npr:  

    ```json
    {
        "Effect": "Allow",
        "Action": "iam:CreateRole",
        "Resource": "*",
        "Condition": {
            "StringEquals": {              # <- Operator
                "aws:RequestTag/Team": [   # <- Key
                    "DevOps",              # <- Value
                    "SRE"
                ]
            }
        }
    }
    ```  
    ili

    ```json
    {
        "Effect": "Allow",
        "Action": "iam:CreateRole",
        "Resource": "*",
        "Condition": {
            "StringNotEquals": {              
                "aws:RequestTag/Team": [   
                    "DevOps",              
                    "SRE"
                ]
            }
        }
    }
    ``` 
    ili

    ```json
    {
        "Effect": "Allow",
        "Action": "s3:CreateBucket",
        "Resource": "*",
        "Condition": {
            "StringEquals": {              
                "aws:PrincipalAccount":"111111111111",
                "aws:SourceVpc":"vpc-1234567890abcdef0"
            },
            "StringLike": {              
                "aws:PrincipalTag/Team":"Team-*"
            }
        }
    }
    ```
    ili
    ```json
    {
        "Effect": "Deny",
        "Action": "s3:CreateBucket",
        "Resource": "*",
        "Condition": {
            "StringNotEquals": {              
                "aws:PrincipalAccount":"111111111111",
                "aws:SourceVpc":"vpc-1234567890abcdef0"
            },
            "StringNotLike": {              
                "aws:PrincipalTag/Team":"Team-*"
            }
        }
    }
    ```
    - **Operator** - Definise koje poredjenje IAM treba napraviti prije nego pokusa da poredi vrijednosti u vasem polisiju sa vrijednostima unutar **authorisation-context**. Postoji vise razlicitih [condition operatora](https://docs.aws.amazon.com/IAM/latest/UserGuide/reference_policies_elements_condition_operators.html), koji operator cete koristiti zavisi od tipa koju vrijednost operatora ima unutar **authorisation-context-a**. Mozete koristiti razlicite sufixe sa operatorima `Equals` ili `NotEquals` ili `Like` da bi dobili tacno podudaranje ili wildcard podudaranje. Kako bi znali koji `condition-key` mozemo koristiti sa kojim IAM pristupom i resursom pogledajte [Service Authorization Reference](https://docs.aws.amazon.com/service-authorization/latest/reference/reference.html). Unutar Service Authorization Reference dokumentacije za svaki AWS servis mozete pogledati koji `condition-key` mozete koristiti sa kojim tipom. Npr za [EC2 servis](https://docs.aws.amazon.com/service-authorization/latest/reference/list_amazonec2.html#amazonec2-policy-keys) u koloni Type vidite koji tip mozete da koristite da bi te radili predjenje. **Condition keys** koji pocinju sa `aws:` mogu se koristiti sa svim AWS servisima.

    ![Authorisation Context Matching](/resources/images/authorisation-context-matching-iam-policy.png)

    - **Key** - Definise koja vrijednost iz **authorisation-context** treba da se poredi sa vrijednostima u vasem polisiju.
    - **Value** - Definise vrijednost koja se poredi sa vrijednostima u vasem polisiju. Da bi se polisiji mogao izvrisiti, vrijednosti u polisiju moraju biti jednake vrijednostima u **authorisation-context-u**.  


    
## Kategorije / Vrste IAM Policy-a
Postoji nekoliko kategorija / vrsta IAM Policy-a, npr. policy koji je pridruzen IAM User-u, IAM Role ili IAM Group te policy koji su pridruzeni AWS resursima, npr. S3 Bucket, SQS...
* **Identity Based Policy** - Za polisije koji su pridruzeni korisniku, roli ili grupi kazemo da se zovu **Identity Based Policy**, jer se radi o polisijima koji su direktno zakaceni za identitet korisnika, role ili grupe i sadrze dozvole o akcijama koje korisnik, rola ili grupa korisnika moze da napravi. 
* **Resource Based Policy** - Za polisije koji su pridruzeni AWS resursima kazemo da se zovu **Resource Based Policy**, jer se radi o polisijima koji su zakaceni na AWS resurs. 
    - **VPC endpoint policies** - predstavljaju polisije koji kontrolisu ko i moze pristupiti i koristiti odredjeni VPC endpoint.
* **Service Control Policies (SPCs)** - Postoje i **Service Control Policies (SPCs)** koje se primjenjuju na nivou AWS organizacije i predstavljaju siru sigurnosnu politiku te se zahvaljujuci tome sto se dodjeljuju na nivou za AWS Organisation servisa primjenjuju se na vise AWS racuna unutar iste organizacije.
* **Permission Boundaries** - predstavljaju napredna mogucnost polisija koja se obicno koristi za delegiranje prava za kreiranje IAM entiteta 
* **Session policies** - predstavljaju polisije koji se primjenjuju na pojedinacnu sesiju kada dolazi do Assume IAM Role. 

## IAM Policy Statements
Svaki tip polisija koji je nabrojan iznad moze da sadrzi jedan ili vise Statement-a. Unutar Statementa se osim iznad nabrojanih primarnih elemenata mogu nalaziti i sljedeci opcionalni elementi:
* **Sid** - Oznacava jedinstveni identifikator za Statement. 

Primjer `idenity-based` policy statement-a:
```json
{
    "Sid": "IdentitifkatorZaMojIAMPolisi",       # <- Opcionalan parametar
    "Effect": "Allow",                           # <- Obavezan parametar
    "Action": "s3:GetObject",                    # <- Obavezan parametar
    "Resource": "arn:aws:s3:::IME-S3-BUCKETA/*", # <- Obavezan parametar
    "Condition": {                               # <- Opcionalan parametar
        "Bool": {
            "aws:SecureTransport": "true"
        }
    }
}
```
Primjetite da ovaj polisi Statement ne sadrzi **Principal** element, to je zato sto se u ovom slucaju pricipal implicitan jer je sam polisiji pridruzen principal-u. 

Primjer `resource-based` policy statement-a:
```json
{
    "Sid": "IdentitifkatorZaMojIAMPolisi",       # <- Opcionalan parametar
    "Effect": "Allow",                           # <- Obavezan parametar
    "Principal": {                               # <- Obavezan parametar
        "AWS": "1111111111"
    },
    "Action": "s3:GetObject",                    # <- Obavezan parametar
    "Resource": "arn:aws:s3:::IME-S3-BUCKETA/*", # <- Obavezan parametar
    "Condition": {                               # <- Opcionalan parametar
        "Bool": {
            "aws:SecureTransport": "true"
        }
    }
}
```
Za razliku od `idenity-based` polisija, `resource-based` poslisiji mora da sadrzi **Principal** element. To je zato sto bilo koji **Principa** moze pokusati da napravi poziv prema resursu i onda je na nama da koristimo principal element da definisemo ko **MOZE** ili **NE MOZE** da pristupi resursu.

U nastavku je dat primjer kompletnog IAM polisija koji se sastoji od vise Statement dijelova.

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "Statement1",
            "Effect": "Allow",
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::IME-S3-BUCKETA/*"
        },
        {
            "Sid": "Statement2",
            "Effect": "Allow",
            "Action": "dynamodb:PutItem",
            "Resource": "arn:aws:dynamodb:us-east-1:1111111111:table/IME-DynamoDB-TABELE"
        }
    ]
}
```
Za razliku od dijelova polisija koje ste vidjeli iznad, u ovom kompletnom polisiju se nalazi i dio `"Version": "2012-10-17"` koji oznacava sintaksu i pravila jezika kojim je napisan polisij. Vise o tome mozete vidjeti u AWS dokumentaciji na [sljedecm linku](https://docs.aws.amazon.com/IAM/latest/UserGuide/reference_policies_elements_version.html). Kako se nas polisiji sastoiji od vise Statment dijelova, onda poslije elementa `"Version": "2012-10-17"` dolazi element `"Statement": []` koji sadrzi niz Statement dijelova.  

## IAM Policy Evaluation

Postoje dva pravila evaluacije IAM polisija:
- Najmanje jedan Statement sa Effect-om Allow mora da se podudari da bi se se akcija koju pokusavamo da napravimo kroz nas request dozvolila.
- Podudarajuci Statement sa Effect-om Deny ima prednost u odnosu na Statement sa Effect-om Allow.

Nije bitno u kojem poretku sa napisani Statement-i, ako se Effect Deny podudara sa nasim requestom, onda ce se request odbiti.

Kada **Principal** napravi request prema AWS-u, AWS skuplja informacije o tom requestu i stavlja te informacije o requestu u ono sto nazivamo **authorisation context**. Autrisation context ukljucuje informacije o **Akciji** ili operaciji koju principa zeli da napravi, to moze biti akcija unutar AWS konzole ili kroz AWS CLI ili AWS API. Dalje sadrzi informacije o **resursu** nad kojim ce akcije biti napravljene. Sadrzi informacije o principalu, odnsosno osobi ili aplikaciji, koja zeli da napravit tu akciju. Informacije o principalu ukljucuju i polisiji koji je pridruzen entitetu kojeg je principal koristio za sign in. Pored navedenog **authorisation context** sadrzi informacije o IP adresi. Ispod je dat jedan ilustrativni primjer **authorisation context-a**:
```text
Principal: AKIAACCESSKEY
Action: s3:CreateBucket
Resource: arn:aws:s3:::IME-S3-BUCKETA
Context:
    aws:UserId=AKIAACCESSKEY:DzenanSession
    aws:PrincipalAccount=1111111111
    aws:PrincipalOrgId=o-example
    aws:PrincipalARN=arn:aws:iam::1111111111:role/Dzenan
    aws:MultiFactorAuthPresent=false
    aws:CurrentTime=2023-05-08T12:00:00Z
    aws:EpochTime=1234567890
```
IAM radi poredjenje **authorization context-a** sa **IAM polisijem** da bi saznao koji se Statement iz IAM polisija podudara i na kraju da  bi odlucio da li je request Allowed ili Denied. 

**Request 1:** 
Korsnik **Dzenan** koristi svoju IAM rolu  da bi dohvatio objekat **file.txt** iz S3 bucket-a pod naziovom **DZENAN-S3-BUCKET**. Korisnik Dzenan ima identity-policy sa sljedecim statemnt dijelovima zakacen za svoju IAM Rolu:
```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "Statement1",
            "Effect": "Allow",
            "Action": ["s3:GetObject","s3:PutObject"]
            "Resource": "arn:aws:s3:::DZENAN-S3-BUCKET/*"
        },
        {
            "Sid": "Statement2",
            "Effect": "Deny",
            "Action": "s3:PutObject",
            "Resource": "*"
        },
        {
            "Sid": "Statement3",
            "Effect": "Allow",
            "Action": "dynamodb:CreateTable",
            "Resource": "*"
        },
        {
            "Sid": "Statement4",
            "Effect": "Allow",
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::DRUGI-BUCKET/*"
        },
    ]
}
```
## Authorisation context 

```text
Principal: AJIAACCESSKEY
Action: s3:GetObject
Resource: arn:aws:s3:::DZENAN-S3-BUCKET/file.txt
Context:
    aws:UserId=AKIAACCESSKEY:DzenanSession
    aws:PrincipalAccount=1111111111
    aws:PrincipalOrgId=o-example
    aws:PrincipalARN=arn:aws:iam::1111111111:role/Dzenan
    aws:MultiFactorAuthPresent=false
    aws:CurrentTime=2023-05-08T12:00:00Z
    aws:EpochTime=1234567890
    aws:SourceIP=...
    aws:PrincipalTag/dept=123
    aws:PrincipalTag/project=blue
```
Da bi utvrdio da li je request **Allow** ili **Deny** IAM ce raditi poredjenje ovog **authrisation context-a** sa svakim **statement**-om iz **identity-policy**-ja. IAM ce takodjer raditi poredjenje **authrisation context-a** i sa ostalim polisijama koje su spomenuti iznad ako oni postoje. 

![IAM Policy Matching](/resources/images/iam_context_matching.png)

Rezultati evaluacije requesta korisnika odnosno poredjenja **authrisation context-a** sa **identity-policy**-jem je sljedeci:

|                 | Effect                  | Matches? | 
|-----------------|-------------------------|----------|
| **Statement1**  | **Allow**               | **Yes**  |
| Statement2      | Deny                    | No       |
| Statement3      | Allow                   | Ignored  |
| Statement4      | Allow                   | Ignored  |   

Vidimo da se samo Statement1 poklapa sa authorization context-om i kako je Effect Allow, onda ce request biti Allowed.
Ako uporedimo sljedeci **authorization context** sa istim **identity-policy**-jem vidimo da se u `Statement1` dijelu `Resource` iz **authorization context-a** ne poklapa sa `Resource` iz **identity-policy**-ja. 

```text
Principal: AJIAACCESSKEY
Action: s3:CreateBucket
Resource: arn:aws:s3:::DZENAN-S3-BUCKET
Context:
    aws:UserId=AKIAACCESSKEY:DzenanSession
    aws:PrincipalAccount=1111111111
    aws:PrincipalOrgId=o-example
    aws:PrincipalARN=arn:aws:iam::1111111111:role/Dzenan
    aws:MultiFactorAuthPresent=false
    aws:CurrentTime=2023-05-08T12:00:00Z
    aws:EpochTime=1234567890
    aws:SourceIP=...
    aws:PrincipalTag/dept=123
    aws:PrincipalTag/project=blue
```
Ukoliko ne postoji bar jedna podudarnost izmedju **authorization context-a** i **identity-policy**-ja za Allow statement, onda ce request biti Denied (Implicit Denied).
Kada dodje do podudarnosti za Deny Statement, svi ostali Statement se mogu ignorisati. Ukoliko vidimo da imamo dva matching statments, jedan Allow i jedan Deny, onda ce request biti Denied (Explicit Denied). 

```text
Principal: AJIAACCESSKEY
Action: s3:PutObject
Resource: arn:aws:s3:::DZENAN-S3-BUCKET/my-file.txt
Context:
    aws:UserId=AKIAACCESSKEY:DzenanSession
    aws:PrincipalAccount=1111111111
    aws:PrincipalOrgId=o-example
    aws:PrincipalARN=arn:aws:iam::1111111111:role/Dzenan
    aws:MultiFactorAuthPresent=false
    aws:CurrentTime=2023-05-08T12:00:00Z
    aws:EpochTime=1234567890
    aws:SourceIP=...
    aws:PrincipalTag/dept=123
    aws:PrincipalTag/project=blue
```
Moguci rezultati evaluacije requesta korisnika odnosno poredjenja **authrisation context-a** sa **identity-policy**-jem su sljedeci:

* Emplicit allow - Najanje jedan Allow Statement, nema podudarajucih Denuy Statements
* Implicit Deny - Nema podudarajucih Allow ili Deny Statements
* Emplicit Deny - Najmanje jedan podudarajuci Deny Statement, ne postoji Explicit Allow Statement

```text
Principal: AJIAACCESSKEY
Action: iam:CreateRole
Resource: arn:aws:iam:1111111111:role/Dzenan
Context:
    aws:UserId=AKIAACCESSKEY:DzenanSession
    aws:PrincipalAccount=1111111111
    aws:PrincipalOrgId=o-example
    aws:PrincipalARN=arn:aws:iam::1111111111:role/Dzenan
    aws:MultiFactorAuthPresent=true
    aws:SecureTransport=true
    aws:PrincipalTag/dept=123
    aws:PrincipalTag/project=blue
    aws:TagKeys=dept,project
```

## Important Actions - iam:PassRole

Akcija **iam:PassRole** je specijalna akcija koja se koristi za delegiranje / dodjelu prava, ili kako se to naziva u AWS dokumentaciji radi se o **permission only** akciji. Ova akcija podrzava opciju specificiranja role koju korisnik moze da delegira / dodijli drugom korisniku. Ova akcija se koristi u kombinaciji sa **resource-based policy**-jima. 
Npr. zamislite da imate scenario u gdje postoji IAM Rola `Administrators` koja ima administratorske privilegije nad vasim AWS racunom. Dalje zamislite da imate korisnika A koji ima permisije da kreira EC2 instancu i da toj instanci dodijeli IAM Rolu. 
Prilikom kreiranja EC2 instance, korinsik A ima opciju da odabere IAM Rolu koja ce biti pridruzena toj EC2 instanci. U tom slucaju se moze dogoditi da korisnik A, koji nije Administrator, moze da dodijeli IAM Rolu `Administrators` EC2 instanci. Tada bi korisnik koji nema administratorske privilegije, mogao da se spoji na tu EC2 instancu i sa nje izvrsi akcije koje mu orginalno nisu bile dozvoljene kroz njegovu rolu. Da bi sprijecili ovaj scenario, IAM zahtijeva da korisniku A, kroz `iam:PassRole` akciju bude dozvoljeno da EC2 instanci dodijeli navedenu rolu. Ako korisnik A nema dodjeljenu `iam:PassRole` akciju gdje je navedena Administratorska IAM Rola, on tu role nece moci dodijeliti EC2 instanci koju je kreirao. Dakle `iam:PassRole` akcija daje permisije korisnicima da proslijede rolu drugom servisu ili resursu. 

Zahvaljujuci tome, `iam:PassRole` akcija se koristi za sprijecavanje sigurnosnog propusta koji je poznat pod nazivom [**confused deputy problem**](https://docs.aws.amazon.com/IAM/latest/UserGuide/confused-deputy.html).

## API Actions vs IAM Actions
Za bilo koju akciju / komnadu koju korisnik zeli da izvrsi nad AWS servisima koristi se API akcija. Kada napravite poziv prema AWS API-u, AWS API ce autorizovati jednu ili vise IAM akcija  Npr. za kreiranje EC2 instance koristi se API akcija `ec2:RunInstances`. Obicno su nazivi API akcija jednaki nazivu IAM akcija uz sljedece izuzetke. 

| API Action | IAM Action(s) |
| --- | --- |
| S3 CopyObject | s3:ListBucket, s3:GetObject, s3:PutObject |
| S3 ListBuckets | s3:ListAllMyBuckets |
| Lambda Invoke | lambda:InvokeFunction |

Npr: API akcija `S3 CopyObject` zahtijeva `s3:GetObject` permisije nad S3 bucketom iz kojeg kopirate objekat / podatke, `s3:PutObject` nad S3 bucketom u koji kopirate objekat / podatke kao i `s3:ListBucket` privilegije. 
Postoje IAM akcije koje imaju nesto drugacije ime nego njihov API action ekvivalent. Jedan od primjera je **Lambda Invoke** API akcija koja zahtijeva `lambda:InvokeFunction` IAM akciju. Preporuka je da provjerite AWS Dokumentaciju kako bi vidjeli koje tacno IAM akcije trebate kako bi korisnik mogao da izvrsi API akciju koju zelite. 

## IAM Policy Evaluation: Policy Evaluation Chains
Prvi principal koji pravi request je **Role session**. **IAM Role** uvijek ima odgovarajucu sesiju koja je ustvari principal koji pravi request. IAM Rola sama po sebi ne pravi request bez sesije. Sljedeci principal koji pravi request je **IAM User**. IAM Users nemaju sesije i oni prave requestove. **Federated user (using sts:GetFederationToken)** je principal koji je dobio pristupne podatke koristeci `sts:GetFederationToken` API. Ovo nije korisnik koji je federated od strane idenity provajdera. Vecina AWS korisnika ne koristi cesto ovaj tip Federated korisnika. **Anonymous** principal predstavlja korisnika koji pravi ne-autorizovani request prema AWS-u. **Root** korinsik je specijalni tip principala i ne bi trebao biti koristen za svakodnevnu upotrebu jer se radi o korinsiku koji ima sve privilegije nad AWS racunom.





## Security best practices with AWS IAM
## IAM Tips and Tricks
[How can I troubleshoot access denied or unauthorized operation errors with an IAM policy?](https://aws.amazon.com/premiumsupport/knowledge-center/troubleshoot-iam-policy-issues/)  
[How to read encoded authorization error messages in AWS](https://arpadt.com/articles/decode-encoded-error-message)  

## Resources
- [AWS re:Invent 2018: [REPEAT 1] Become an IAM Policy Master in 60 Minutes or Less (SEC316-R1)](https://youtu.be/YQsK4MtsELU)
- [IAM Policy Evaluation Series: AWS IAM policy language explained - Part 1](https://youtu.be/qsF6Kauh2J4)
- [IAM Policy Evaluation Series: policy evaluation chains](https://youtu.be/71-Gjo6a5Cs)
- [AWS re:Inforce 2022 - Security best practices with AWS IAM (IAM201)](https://youtu.be/SMjvtxXOXdU)
- [AWS Identity and Access Management Documentation](https://docs.aws.amazon.com/IAM/latest/UserGuide/introduction.html)  
- [How to monitor and query IAM resources at scale – Part 1](https://aws.amazon.com/blogs/security/how-to-monitor-and-query-iam-resources-at-scale-part-1/)  
- [How to monitor and query IAM resources at scale – Part 2](https://aws.amazon.com/blogs/security/how-to-monitor-and-query-iam-resources-at-scale-part-2/)
- [GetAccountAuthorizationDetails](https://docs.aws.amazon.com/IAM/latest/APIReference/API_GetAccountAuthorizationDetails.html)

# AWS Documentation
- [Service Authorisation Reference](https://docs.aws.amazon.com/service-authorization/latest/reference/reference.html)
