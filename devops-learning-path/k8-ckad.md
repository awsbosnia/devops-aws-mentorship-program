# Certified Kubernetes Application Developer (CKAD) - Boris Bradić (January 2023.)

**Kursevi koji su korišteni prilikom pripreme:**

- [PluralSight - Certified Kubernetes Application Developer - Courses](https://app.pluralsight.com/search/?q=ckad&type=conference%2Cvideo-course%2Cguide%2Cinteractive-course%2Clab%2Cpath%2Cproject%2Cskilliq&m_sort=relevance&query_id=655c0858-3c09-48a8-9c33-9646e108bac7&source=user_typed)
- [KodeKloud](https://kodekloud.com/courses/certified-kubernetes-application-developer-ckad/)
- [ACG - A Cloud Guru](https://learn.acloud.guru/course/certified-kubernetes-application-developer/overview)

## _Review certifikacije:_

- K8s certifikacije su 100% hands-on.
- Nema klasičnih pitanja nego imate taskove.
- Offical docs možete koristiti, zato je jako bitno da se znate snalaziti kroz tu dokumentaciju, jer tamo postoje templates koji Vam mogu biti od koristi prilikom izrade solutiona.
- Kada se test pokrene otvara se VM, pokrećete terminal, browser sa docs i krećete task po task.
- Nema pravila za taskove. Možete preskočiti task ukoliko se na prvi pogled čini kao prekomplikovan. Ostavite ga, pa se vratite kasnije na isti.
- Ne nosi svaki task istu težinu na bodovanju.
- Ono što je bitno jeste da shvatite da postoji više 'clusters'. Šta to znači? Znači da se morate nalaziti u određenome contextu da biste radili svoj task. Uvijek na početku stoji ta komanda, pa, ako vidite da je 'isti' context Vi ipak potvrdite to komandom, da se ne bi desili neki eventualni problemi (niste promjenili context ili sl.).
- **Vaše promjene su trajne. Što znači da ono što odradite u određenom contextu će biti prisutno na nekom sljedećem tasku, neće se okruženje resetovati sa svakim novim taskom. Simulira se prod env, tako da svaka promjena je vidljiva. Jedan od primjera jeste, ako ste kreirali 'Pod' u specifičnom contextu, a niste ga obrisali, naći ćete taj 'Pod' kasnije nekad kad se vratite na taj isti context.**
- **Kucajte samo ono što je potrebno, jer ukoliko nešto pogriješite, nema reset. Pod ovime najviše izdvajam "Network Policy". Zašto? Network Policy ograničava traffic po nekim specifičnim pravilima koje definišete. Primjer toga jeste task gdje imate zadatak kreirati taj Network Policy, ali ukoliko ga ne podesite dobro, a odradite 'apply' komandu, možete blokirati čitav traffic da niste toga svjesni. Kasnije kada budete radili drugi task, možda je sve podešeno kako treba, ali neki Vaši testovi ne prolaze (naravno, provjeravate šta ste odradili, čisto radi sebe).**
- Proći KodeKloud testove, jako su korisni.
- _Prekucati sve komande, jer ćete na taj način steći rutinu kucanja i naravno nekih skraćenih komandi, koje štede vrijeme._
- Test traje 120 min, broj taskova nije poznat, dok se ne pokrene test.
