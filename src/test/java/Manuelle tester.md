# Testing ved testscreen

### Skjerm og knapptest (menuScreen og testScreen, button)
Hensikt: 
- Sjekke at grafikken og vinduet oppfører seg "normalt" ved reskalering
- Sjekke at knapper fungerer ved reskalering
1. Kjør programmet.
2. Klikk på "TEST". Du skal nå se spillbrettet.
3. Reskaler vindu på vanlig måte og sjekk at alt på skjermen holder de samme proposjonene.
4. Avslutt og start på nytt.
5. Gjenta pkt. 3, deretter pkt. 2. 
6. Gjenta alt, men velg "PLAY" i stedet for "TEST"
Ferdig.

### Bevegelsestest (GameMap, Grid, Player, PlayerLayerObject, GroundLayerObject)
Hensikt: 
- Kunne bevege grønn brikke fritt rundt på brettet
- Sjekke at rikitg kort gir riktig bevegelse
- Sjekke at kollisjon med vegger fungerer

1. Kjør programmet og trykk "TEST". Du skal nå kunne bevege den grønne brikken med følgende tastetrykk:
"1": Beveg ett felt i nåverende retning (MOVE1)
"2": Beveg to felt i nåverende retning (MOVE2)
"3": Beveg tre felt i nåverende retning (MOVE3)
"R": Roter mot høyre (ROTATERIGHT)
"L": Roter mot venstre (ROTATELEFT)
"U": Roter 180 grader (UTURN)
"B": Beveg ett felt bakover. Retning forblir uendret (BACKUP)

2. Utvid konsollvinduet til editoren din for å følge med på informasjonen som blir gitt under bevegelse.

3. Trykk følgende tastekombinasjon på tastaturet mens du underveis sjekker at informasjonen i konsollen er korrekt:
"1", "2", "L", "R", "R", "2", "L", "3", "3", "3", "U", "2", "3", "3", "B", "U", "B", "U", "B".

4. Du peker nå i retning sør og står to felt nord for lyseblå brikke.
Følgende informasjon om brikken skal stå i konsollen:

CONTROLLED TILE: 
Card type: BACKUP
Color: Green
Direction: SOUTH
Position: (2,4)

Resterende brikker på spillbretter er uendret.


### Kollisjonstest med flere spillere
Hensikt: 
- Sjekke at bevegelse fungerer ved kollisjon med andre spillere. 
- Sjekke at posisjonen til de andre spillerene blir oppdatert og at retningen deres forblir uendret.

1. Kjør programmet og trykk "TEST". 
2. Tast inn følgende kombinasjon:
"R", "1", "L", "3"
- Grønn brikke dytter ved MOVE3 mørk blå brikke tre felt retning nord.
3.Fortsett med følgende kombinasjon:
"U", "1", "R", "B"
- Grønn brikke dytter ved BACKUP lys blå brikke ett felt retning øst.
4. Fortsett med følgende kombinasjon:
"L", "1", "L", "1", "L", "1", "2" 
- Grønn brikke dytter ved MOVE1 lys blå brikke og gul brikke ett felt nord, deretter to felt nord ved MOVE2
5. Fortsett med følgende kombinasjon:
"B", "L", "3", "R", "1", "R", "3"
- Grønn brikke dytter ved MOVE3 mørk blå brikke to felt øst og blir stoppet av vegg.
6. Fortsett med følgende kombinasjon:
"R", "1", "L", "1", "L", "3", "2"
- Grønn brikke bytter alle tre brikker ved MOVE3 og MOVE2 fem felt nord

7. Sjekk at følgende informasjon står i konsoll:
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

8. Trykk vilkårlig på "1", "2" eller "3" og sjekk at informasjonen forblir lik pkt. 7 og at ingen brikker ender posisjon eller retning.


## Test playScreen
Hensikt:
- Sjekke at alt er i orden også i playScreen

1. Kjør programmet og trykk "PLAY"
2. Bruk "SPACE" for å kjøre gjennom en fase
3. Sjekk at spillere gjør tilfeldige bevegelser og at ingen bryter regeler for kollisjon
