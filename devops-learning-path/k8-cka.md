# Certified Kubernetes Administrator (CKA) - Boris Bradić (January 2023.)

- [Boris Notes](https://docs.google.com/document/d/1YDHgzr8-nMiYT0SS2LNwTuD_mq5rGruOqTOkJw5lPJo/edit?usp=sharing)

**Kursevi koji su korišteni prilikom pripreme:**

- [PluralSight - Certified Kubernetes Administrator](https://app.pluralsight.com/paths/certificate/certified-kubernetes-administrator)
- [KodeKloud](https://kodekloud.com/courses/certified-kubernetes-administrator-cka/)
- [ACG - A Cloud Guru](https://www.pluralsight.com/cloud-guru/courses/certified-kubernetes-administrator-cka)

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
