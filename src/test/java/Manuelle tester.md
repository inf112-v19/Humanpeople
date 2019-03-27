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
- Sjekke at riktig kort gir riktig bevegelse
- Sjekke at kollisjon med vegger fungerer

1. Kj�r programmet og trykk "TEST". Du skal n� kunne velge bevegelser til den gr�nne brikken med f�lgende tastetrykk:
"1": Beveg ett felt i n�verende retning (MOVE1)
"2": Beveg to felt i n�verende retning (MOVE2)
"3": Beveg tre felt i n�verende retning (MOVE3)
"R": Roter mot h�yre (ROTATERIGHT)
"L": Roter mot venstre (ROTATELEFT)
"U": Roter 180 grader (UTURN)
"B": Beveg ett felt bakover. Retning forblir uendret (BACKUP)
Når du har valgt 5 bevegelser, så kan du trykke space og en fase vil bli gjennomført

2. Utvid konsollvinduet til editoren din for � f�lge med p� informasjonen som blir gitt under bevegelse.

3. Trykk f�lgende tastekombinasjon p� tastaturet mens du underveis sjekker at informasjonen i konsollen er korrekt:
"1", "2", "L", "R", "R", "space" , "2", "L", "3", "3", "3", "space", 
"U", "2", "3", "3", "B", "space",  "U", "B", "U", "B", "U", "space".

4. Du peker n� i retning nord og st�r to felt nord for lysebl� brikke.
F�lgende informasjon om brikken skal st� i konsollen:

CONTROLLED TILE: 
Card type: BACKUP
Color: Green
Direction: NORTH
Position: (2,4)

Resterende brikker p� spillbretter er uendret.


### Kollisjonstest med flere spillere
Hensikt: 
- Sjekke at bevegelse fungerer ved kollisjon med andre spillere. 
- Sjekke at posisjonen til de andre spillerene blir oppdatert og at retningen deres forblir uendret.

1. Kj�r programmet og trykk "TEST". 
2. Tast inn f�lgende kombinasjon:
"3", "3", "L", "1", "R" , "space"
- Gr�nn brikke nede  til venstre for gul brikke med retning nord
3.Fortsett med f�lgende kombinasjon:
"3", "R", "3", "3", "3", "space"
- Gr�nn brikke dytter gul brikke helt bort til lyseblå brikke, deretter dytter han begge 1 felt og blir stoppet av veggen.
4. Fortsett med f�lgende kombinasjon:
"L", "1", "R", "1", "L", "space".
- Gr�nn brikke står nå nord for gul brikke, med retning nord. 
5. Fortsett med f�lgende kombinasjon:
"B", "B", "U", "1", "2", "space"
- Gr�nn brikke dytter nå gul brikke 2 felt med å rygge, tar en uturn og dytter gul brikke 3 felt videre.






## Test playScreen
Hensikt:
- Sjekke at alt er i orden ogs� i playScreen

1. Kj�r programmet og trykk "PLAY"
2. Bruk "SPACE" for � kj�re gjennom en fase
3. Sjekk at spillere gj�r tilfeldige bevegelser og at ingen bryter regeler for kollisjon

