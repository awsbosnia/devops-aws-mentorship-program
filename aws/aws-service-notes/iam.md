# Identity Access Management (IAM) 

## Jezik IAM Policy-a
IAM Policy je napisan u JSON formatu. Svaki IAM Policy se sastoji od jednog ili vise **Statements**. Unutar **Statement** dijela imate pet primarnih elemenata:
* **Effect** - `The Why` - Oznacava da li ce request biti Allow / Allowed ili Deny / Denied
* **Principal** - `The Who` - Ovaj element oznacava entitet / Entity kojem je pristup dozvoljen ili odbijen, primjetiti cete da ponekad Principa element nije sadrzan unutar Policy Statemnt-a. 
* **Action** - `The What` - Oznacava jednu ili vise Akcija koje su dozvoljene / Allowed ili odbijene / Denied.
* **Resource** - `The Where` - Oznacava jedan ili vise AWS resursa na koje se odnosi / primjenjuje Policy Statement.
* **Condition** - `The When` -  Condition element oznacava konektekst unutar kojeg ce pristup biti razmotren. Npr. Statement u primjeru ispod se primjenjuje samo ukoliko je vrijednost za AWS sigurni prenost podataka (AWS Secure Transport) jednaka `true`. 

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
- `authrisation context`:

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
