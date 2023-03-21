# Podesavanje i Konfiguracija Laptopa

## Terminal

Terminal koji koristimo na MacOS-u je iTerm2. Preporucujemo da ga instalirate i koristite. [Download](https://iterm2.com/downloads.html)

## Homebrew

Homebrew je paket menadzer koji nam omogucava da instaliramo razne alate i aplikacije na MacOS-u. Preporucujemo da ga instalirate i koristite. [Download](https://brew.sh/)

## ZSH

ZSH je shell koji koristimo na MacOS-u. Preporucujemo da ga instalirate i koristite. [Download](https://ohmyz.sh/)

Pogledajte sljedece materijale kako bi konfigurisali ZSH i iTerm2:

- [Jazz Up Your “ZSH” Terminal In Seven Steps — A Visual Guide](https://www.freecodecamp.org/news/jazz-up-your-zsh-terminal-in-seven-steps-a-visual-guide-e81a8fd59a38/)
- [Beautifying your terminal with Zsh, Prezto, & Powerlevel9k](https://medium.com/@oldwestaction/beautifying-your-terminal-with-zsh-prezto-powerlevel9k-9e8de2023046)

### ZSH Plugins

Ovo je liste ZSH Plugins koje preporucujemo da instalirate.

- git
- aws
- dnf
- zsh-autosuggestions
- zsh-syntax-highlighting

#### Dodatna podesavanja terminala

Da bi omogucili shift-arow selection unutar terminala potrebno je unutar iterm2 Settings, unutar opcije `Keys-Key Bindings` dodati `Keyboard Shortcut` sa zeljenom kombinacijom, recimo :

- `⌥ + ←` -> Action: Move start of selection back -> Move by word
- `⌥ + →` -> Action: Move end of selection forward > Move by word

ili bilo koja druga zeljena kombinacija tipki sa navedenom akcijom.

Takodjer jako korisna opcija je dodati mogucnost "undo" radnje unutar terminala sa tipkama `⌘ + Z`. Procedura je gotovo ista samo je razlika u akciji koja ce da se obavi:

- `⌘ + Z` -> Action: Send Hex Codes -> `0x1f`

## IDE

IDE (Integrated Development Environment) je okruzenje koje nam omogucava da pisemo kod, da ga testiramo i da ga pokrecemo. Ukoliko zelite da koristite IDE za pisanje koda, preporucujemo da instalirate [Visual Studio Code](https://code.visualstudio.com/).

### Visual Studio Code Extensions

Ovo je liste VS Code Extensions koje preporucujemo da instalirate.

- [GitLens](https://marketplace.visualstudio.com/items?itemName=eamodio.gitlens)
- [AWS Toolkit](https://marketplace.visualstudio.com/items?itemName=AmazonWebServices.aws-toolkit-vscode)
- [Docker](https://marketplace.visualstudio.com/items?itemName=ms-azuretools.vscode-docker)
- [Terraform](https://marketplace.visualstudio.com/items?itemName=HashiCorp.terraform)
- [CloudFormation](https://marketplace.visualstudio.com/items?itemName=aws-scripting-guy.cform)
- [YAML](https://marketplace.visualstudio.com/items?itemName=redhat.vscode-yaml)
- [DotENV](https://marketplace.visualstudio.com/items?itemName=mikestead.dotenv)
- [GitGraph](https://marketplace.visualstudio.com/items?itemName=mhutchie.git-graph)
- [Git History](https://marketplace.visualstudio.com/items?itemName=donjayamanne.githistory)
- [Go](https://marketplace.visualstudio.com/items?itemName=golang.go)
- [HashiCorp Terraform](https://marketplace.visualstudio.com/items?itemName=HashiCorp.terraform)
- [Ident-rainbow](https://marketplace.visualstudio.com/items?itemName=oderwat.indent-rainbow)
- [Kubernetes](https://marketplace.visualstudio.com/items?itemName=ms-kubernetes-tools.vscode-kubernetes-tools)
- [Jinja](https://marketplace.visualstudio.com/items?itemName=wholroyd.jinja)
- [Material Icon Theme](https://marketplace.visualstudio.com/items?itemName=PKief.material-icon-theme)
- [Peacock](https://marketplace.visualstudio.com/items?itemName=johnpapa.vscode-peacock)
- [Python](https://marketplace.visualstudio.com/items?itemName=ms-python.python)
- [Regex Previewer](https://marketplace.visualstudio.com/items?itemName=chrmarti.regex)
- [TabOut](https://marketplace.visualstudio.com/items?itemName=albert.TabOut)
- [Panda Theme](https://marketplace.visualstudio.com/items?itemName=tinkertrain.theme-panda)

#### Korisne aplikacije za bolju produktivnost

- [Linear mouse](https://linearmouse.app)

  - Ako je na MAC-u podeseno "natural scroling" za Trackpad ali nakon toga skrolanje sa misom nije nimalo prirodno nego je iz nekog razloga kontra prirodnom, ova apliakcija nudi jako jednostavno rjesenje sa odvojenim postavkama za ova dva uredjaja.

- [Maccie Clipboard Manager](https://maccy.app)
  - Jedna od najbitnijih apliakcija za produktivan rad je `Clipboad Manager`. Pomocu ove aplikacije mozete kopirati mnostvo razlicitih textova, slika i fileova i nakon toga uraditi paste toga gdje god vam zatreba pomocu jednog klika. Ne trebate razmisljati da li vam je nesto ostalo u "clipboardu" ili ne, jednostavno sve ce biti tu uvijek spremno za "paste" uz mogucnost pretrazivanja.
  - Trenutno besplatan alat koji zadovoljava sve ove funkcionalsnosti je `Maccie` iako je na AppStore 12$, moze se dobiti besplatno sa njihove stranice sa opcijom - Download Now.
