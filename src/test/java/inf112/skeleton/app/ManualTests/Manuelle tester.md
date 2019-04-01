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

### Sjekk at spiller beveger seg ett felt om gangen
1. Kjør programmet og trykk på "TEST"
2. Tast inn følgenede kombinasjon: "2", "2", "2", "2", "2", "space"
3. Se at grønn brikke beveger seg ett felt om gangen


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
Når du har valgt 5 bevegelser, så kan du trykke space og en runde vil bli gjennomført

2. Du kan trykke på "I" for å få informasjon om brikkene på brettet

3. Trykk f�lgende tastekombinasjon p� tastaturet mens du underveis sjekker at informasjonen i konsollen er korrekt:
"B", "R", "3", "2", "L" "space" , "1", "3", "R", "1", "L", "space", 
"3", "L", "3", "L", "1", "space",  "R", "1", "R", "2", "U", "space".

4. Dersom du trykker på "I" skal f�lgende informasjon om brikken st� i konsollen:

CONTROLLED TILE: 
Color: Green
Direction: SOUTH
Position: (3,8)

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

6. Trykk på "i", og sjekk at du får følgende informasjon:

-------------------
CONTROLLED TILE: 
Color: Green
Direction: SOUTH
Position: (10,6)

-------------------
OTHER TILES: 
Color: Dark blue
Direction: NORTH
Position: (10,1)

Color: Light blue
Direction: NORTH
Position: (11,10)

Color: Yellow
Direction: NORTH
Position: (10,5)
-------------------








## Test playScreen
Hensikt:
- Sjekke at alt er i orden ogs� i playScreen

1. Kj�r programmet og trykk "PLAY"
2. Du kan nå velge kort ved å dra dem fra oppe til høyre, og legge dem i slots. Fra venstre til høyre representerer programmet ditt 1-5. Når du trykker play skal en runde gjennomføres.
3. Sjekk at draganddropen fungerer fint, og dersom du slipper et kort nærme et slot, så skal den snappe inn på plass. Du skal kunne dra kort til og fra hånden. 
4. Sjekk at du ikke kan trykke på play uten at fem kort er valgt. Sjekk at play-knappen ikke fungerer mens en runde er i gang. 
5. Sjekk at grønn brikke beveger seg korrekt etter programmet du har lagt frem. 
6. Følg med på at kollisjon med andre brikker, og vegger fungerer som normalt
7. Etter runden er gjennomført får du delt ut nye kort, og du kan velge nytt program

