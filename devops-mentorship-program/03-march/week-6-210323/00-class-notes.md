# 📝 Class notes
## 📅 Date: 21.03.2023.

## Uvod u Cloud
**Racunarstvo u oblaku** ili **cloud computing** odnosno **cloud**, je najjednostavnije receno mogucnost da **preko interneta koristite racunarske, mrezne i druge resurse bez mogucnosti fizickog pristupa hardverskim uredjajima koji vam pruzaju trazene resurse.** Kako se resursima pristupa preko javne mreze, odnosno interneta, za cloud koristimo i precizniji izraz **public-javni cloud**.
## Amazon Web Services (AWS)
**Amazon Web Servisi (AWS)** predstavljaju web servise, koji vam omogucavaju da koristite mrezne, racunarske i resurse za pohranu podataka preko interneta. Historija AWS-a vuce korijenje od 2002. godine, kada je kompanija Amazon.com programerima ponudila beta verziju SOAP i XML interfejsa, preko kojeg bi mogli da pristupe Amazon katalogu i zarade kroz [Amazon Associates](https://affiliate-program.amazon.com/) program. Krajem 2004. godine, učinili su javno dostupnim Simple Queue Service (SQS), a zatim početkom i na ljeto 2006. godine objavili još dva servisa, Amazon S3 i Amazon EC2. Mozemo reci da je tako sve pocelo.

Vise o historiji Amazon Web Servisa možete pročitati u blog postu Jeffa Barra, AWS Chief Evangeliste, [My First 12 Years at Amazon.com](http://jeff-barr.com/2014/08/19/my-first-12-years-at-amazon-dot-com/).

### AWS Infrastruktura
AWS posjeduje data centre rasirene po cijelom svijetu. Data centri su grupisani po regijama - `regions` (Europe London, Europe Paris, Europe Frankfurt, US Northern Virginia itd), gdje svaka od regija mora da sadrzi minimalno dva fizički odvojena i nezavisna data centra koja se nazivaju Availibliy Zone (AZ). Availibility Zone i AWS regije su međusobno povezane redudantnim brzim linkovima, koji omogućavaju brzu privatnu mrezu sa minimalnim kasnjenjima u prenosu podataka, kao i komunikaciju servisa koji se nalaze u razlicitim regijama.
**Regije** se oznacavaju koristeci **AWS Region Name** - npr.  `Europe (Frankfurt)` i **AWS Region Code** - npr. `eu-central-1`. **Availablity Zone** se označavaju na način da se na ime regije doda slovo (`a`, `b`, `c`, `d`, `e`, itd.) kao sufix (npr. `eu-central-1a`).
## 📖 Reading materials
- [Racunarstvo u oblaku - Cloud](https://sqlheisenberg.com/racunarstvo-u-oblaku-cloud/index.html)
- [Uvod u Amazon Web Servise (AWS)](https://sqlheisenberg.com/uvod-u-amazon-web-servise-aws/index.html)
- [My First 12 Years at Amazon.com](http://jeff-barr.com/2014/08/19/my-first-12-years-at-amazon-dot-com/)
## 📹 Session recordings
- [**WEEK-6-tier-1-group-1 video session recording**](https://youtu.be/no5T7CzRumI)
- [**WEEK-6-tier-1-group-2 video session recording**]()

[:fast_forward: Class Notes](/devops-mentorship-program/03-march/week-5-210323/00-class-notes.md)
[:fast_forward: Additional Reading](/devops-mentorship-program/03-march/week-5-210323/02-additional-reading.md)
[:fast_forward: HOME - README.md](../../../README.md)
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)
