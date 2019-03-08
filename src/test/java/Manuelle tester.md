# Testing ved testscreen

### Skjerm og knapptest (menuScreen og testScreen, button)
Hensikt: 
- Sjekke at grafikken og vinduet oppf�rer seg "normalt" ved reskalering
- Sjekke at knapper fungerer ved reskalering
1. Kj�r programmet.
2. Klikk p� "TEST". Du skal n� se spillbrettet.
3. Reskaler vindu p� vanlig m�te og sjekk at alt p� skjermen holder de samme proposjonene.
4. Avslutt og start p� nytt.
5. Gjenta pkt. 3, deretter pkt. 2. 
6. Gjenta alt, men velg "PLAY" i stedet for "TEST"
Ferdig.

### Bevegelsestest (GameMap, Grid, Player, PlayerLayerObject, GroundLayerObject)
Hensikt: 
- Kunne bevege gr�nn brikke fritt rundt p� brettet
- Sjekke at rikitg kort gir riktig bevegelse
- Sjekke at kollisjon med vegger fungerer

1. Kj�r programmet og trykk "TEST". Du skal n� kunne bevege den gr�nne brikken med f�lgende tastetrykk:
"1": Beveg ett felt i n�verende retning (MOVE1)
"2": Beveg to felt i n�verende retning (MOVE2)
"3": Beveg tre felt i n�verende retning (MOVE3)
"R": Roter mot h�yre (ROTATERIGHT)
"L": Roter mot venstre (ROTATELEFT)
"U": Roter 180 grader (UTURN)
"B": Beveg ett felt bakover. Retning forblir uendret (BACKUP)

2. Utvid konsollvinduet til editoren din for � f�lge med p� informasjonen som blir gitt under bevegelse.

3. Trykk f�lgende tastekombinasjon p� tastaturet mens du underveis sjekker at informasjonen i konsollen er korrekt:
"1", "2", "L", "R", "R", "2", "L", "3", "3", "3", "U", "2", "3", "3", "B", "U", "B", "U", "B".

4. Du peker n� i retning s�r og st�r to felt nord for lysebl� brikke.
F�lgende informasjon om brikken skal st� i konsollen:

CONTROLLED TILE: 
Card type: BACKUP
Color: Green
Direction: SOUTH
Position: (2,4)

Resterende brikker p� spillbretter er uendret.


### Kollisjonstest med flere spillere
Hensikt: 
- Sjekke at bevegelse fungerer ved kollisjon med andre spillere. 
- Sjekke at posisjonen til de andre spillerene blir oppdatert og at retningen deres forblir uendret.

1. Kj�r programmet og trykk "TEST". 
2. Tast inn f�lgende kombinasjon:
"R", "1", "L", "3"
- Gr�nn brikke dytter ved MOVE3 m�rk bl� brikke tre felt retning nord.
3.Fortsett med f�lgende kombinasjon:
"U", "1", "R", "B"
- Gr�nn brikke dytter ved BACKUP lys bl� brikke ett felt retning �st.
4. Fortsett med f�lgende kombinasjon:
"L", "1", "L", "1", "L", "1", "2" 
- Gr�nn brikke dytter ved MOVE1 lys bl� brikke og gul brikke ett felt nord, deretter to felt nord ved MOVE2
5. Fortsett med f�lgende kombinasjon:
"B", "L", "3", "R", "1", "R", "3"
- Gr�nn brikke dytter ved MOVE3 m�rk bl� brikke to felt �st og blir stoppet av vegg.
6. Fortsett med f�lgende kombinasjon:
"R", "1", "L", "1", "L", "3", "2"
- Gr�nn brikke bytter alle tre brikker ved MOVE3 og MOVE2 fem felt nord

7. Sjekk at f�lgende informasjon st�r i konsoll:
-------------------
CONTROLLED TILE: 
Card type: MOVE2
Color: Green
Direction: NORTH
Position: (3,8)

-------------------
OTHER TILES: 
Color: Dark blue
Direction: NORTH
Position: (3,9)

Color: Light blue
Direction: NORTH
Position: (3,10)

Color: Yellow
Direction: NORTH
Position: (3,11)

8. Trykk vilk�rlig p� "1", "2" eller "3" og sjekk at informasjonen forblir lik pkt. 7 og at ingen brikker ender posisjon eller retning.


## Test playScreen
Hensikt:
- Sjekke at alt er i orden ogs� i playScreen

1. Kj�r programmet og trykk "PLAY"
2. Bruk "SPACE" for � kj�re gjennom en fase
3. Sjekk at spillere gj�r tilfeldige bevegelser og at ingen bryter regeler for kollisjon
