#	📝 Class notes
## 	📅 Date: 14.02.2023.

## **Git Intro** 

## **Install Git on Windows:**  
Git mozete preuzeti sa sledeceg [**linka**](https://git-scm.com/download/win).

Preuzmite [**PowerShell**](https://learn.microsoft.com/en-us/powershell/scripting/overview?view=powershell-7.3) da bi dobili terminal pogodniji za rad.
Na ovome [**linku**](https://www.youtube.com/watch?v=a-zX_qc2S-M&ab_channel=CameronMcKenzie) mozete ispratiti kako da kreirati ssh kljuc i podesite vas GitHub profil da mozete izvrsavati git commande.

Takodje mozete preuzeti i [**Windows Terminal**](https://apps.microsoft.com/store/detail/windows-terminal/9N0DX20HK701?hl=sr-cyrl-ba&gl=ba&rtc=1)

<details><summary>Ovde kliknite za dodatne informacije kako da podesite prikazivanje brache unutar terminala</summary>
<p>

Evo par komandi da podesite PowerShell na najnoviju verziju PS 7 i da se prikazuje ime branch-a u kojem se trenutno nalazite dok radite sa Git-om. Pošto je ovdje glavni branch master, mora se preimenovati na main pa imate na kraju i tu komandu.

Komanda za upgrade ps 5.1 na najnoviju verziju ps7 - u PS mora imati administrator privilegije (run PS as administrator)
```ruby
iex "& { $(irm https://aka.ms/install-powershell.ps1) } -UseMSI"
```
Komande za prikaz main i drugih git branches - isto PS run as administrator
```ruby
> Import-Module posh-git -Scope AllUsers
> Add-PoshGitToProfile -AllHosts
```

Main branch se prikazuje kao master
```ruby
git branch -m master main
git status
```


</p>
</details>
 
## **Install Git on MacOS:**  
MacOS je Unix operativni sistem i njegov terminal je vec pogodan za koristenje. Da bi instalirali git na vasem Mac-u mozete korisiti [**brew**](https://brew.sh/) paket menadzer. 
```
$ brew install git
```

Na [**linku**](https://www.youtube.com/watch?v=nZYJKXXMvkM&ab_channel=TechPedia-HowtoTech) mozete ispratiti kako da kreirate ssh kljuc i podesite vas GitHub profil da mozete izvrsavati git commande.

**Basic Git Commands**
```
git init
git add
git commit 
git pull
git push 
git clone
git remote -v
```
- [**Lucidchart diagram tier-1-group-1**](files/lucidchart-week-1-tier-1.pdf)
- [**Whiteboard tier-1-group-1**](files/whiteboard-week-1-tier-1.pdf)

## 📖 Learning materials
- [**📹 Git for Professionals Tutorial - Tools & Concepts for Mastering Version Control with Git**](https://www.youtube.com/watch?v=Uszj_k0DGsg)
- [**📖 git Documentation**](https://git-scm.com/docs/git)


## 📹 Session recordings
[**tier-1-group-1 video session recording**](https://youtu.be/jNPFe9vdRFI)
[**tier-1-group-2 video session recording**](https://youtu.be/FDOho51mkuE)
