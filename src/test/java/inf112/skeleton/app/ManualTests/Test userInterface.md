## Test av userInterface
Hensikt:
- Sjekke at userInterface fungerer sammen med spillet
- Sjekke at "PLAY" og "POWER DOWN" fungerer
- Sjekke at en kan velge kort ved drag and drop og kjøre spillet basert på disse.
- Sjekke at damage tokens oppdateres grafisk ved skade
- Sjekke at life tokens oppdateres grafisk ved ødeleggelse
- Sjekke at flagg oppdateres ved besøk

### Kortvelging
Hensikt:
- Sjekke at userInterface fungerer sammen med spillet
- Sjekke at en kan velge kort ved drag and drop og kjøre spillet basert på disse.
- Sjekke at "PLAY" og "POWER DOWN" fungerer

1. Kjør programmet og trykk "TEST"
2. Du kan nå velge kort ved å dra dem fra oppe til høyre, og legge dem i vilkårlige slots. Fra venstre til høyre representerer programmet ditt 1-5. Når du trykker play skal en runde gjennomføres.
3. Sjekk at draganddropen fungerer fint, og dersom du slipper et kort nærme et slot, så skal den snappe inn på plass. Du skal kunne dra kort til og fra hånden. Du skal også kunne bytte plass på kortene du har valgt i det nedre feltet eller slippe et nytt kort over et valgt kort for å erstatte det.
4. Sjekk at du ikke kan trykke på play uten at fem kort er valgt. Sjekk at play-knappen ikke fungerer mens en runde er i gang. 
5. Sjekk at grønn brikke beveger seg korrekt etter programmet du har lagt frem. 
6. Følg med på at kollisjon med andre brikker, og vegger fungerer som normalt
7. Etter runden er gjennomført får du delt ut nye kort, og du kan velge  nytt program
8. Legg til et nytt program og trykk "POWER DOWN". 
9. Kontroller at den grønne brikken følger programmet denne runden og at den har power down runden etterpå. Når den har power down skal det ikke være synlige kort på skjermen og "PLAY"- og "POWER DOWN"-knappene skal ikke fungere.
10. Kontroller at kortene er tilbake og at du kan fortsette å spille. 

### Life tokens
Hensikt:
- Sjekke at life tokens oppdateres grafisk ved ødeleggelse

1. Kjør programmet og trykk "TEST"
2. Tast inn følgende kombinasjon:
"R", "3", "3", "3", "3", "space"
- Du skal ha gått ned i et hull, blitt ødelagt og returnert til backup.
3. Kontroller at du nå har 2 grønne og 1 rød life token. 
4. Tast inn følgende kombinasjon:
"3","3", "3", "3", "3", "space"
5. Kontroller at du nå har 1 grønn og 2 røde life tokens og at du står på backup posisjonen.
6. Gjenta punkt 4 
7. Observer at du nå har 0 grønne og 3 røde life tokens samt at grønn spiller ikke lenger er på brpettet. Spillet skal kjøre videre med de andre spillerene.


### Damage tokens
Hensikt:
- Sjekke at damage tokens oppdateres grafisk ved skade

1. Kjør programmet og trykk "TEST"
2. Klikk på "T" for å skru av laserskyting (laser på brett blir ikke skrudd av)
3. Tast inn følgende kombinasjon:
"1", "1", "1", "1", "1", "space"
4. Kontroller at du har 9 gule og 1 grå damage token
5. Tast inn følgende kombinasjon:
"U", "2", "1", "1", "1", "space"
6. Kontroller at du har 8 gule og 2 grå damage tokens
7. Repeter pkt. 5. tre gonger til. Kontroller at du har 5 gule og 5 grå damage tokens der siste grå har teksten "LOCKED".
8. Repeter pkt 5. en gang og tast deretter inn følgende kombinasjon:
"B", "1", "B", "1", "B", "space"
9. Kontroller at du har en gul og 9 grå damage tokens der de fem siste grå har teksten "LOCKED".
10. Tast in følgende kombinasjon:
"B", "1", "B", "B", "B", "space"
11. Kontroller at den siste gule damage token ble rød og hadde teksten "DEAD" og at den grønne brikken returnerte til backup. 
Etter runden er ferdig kontrollerer du at du har to grønne og en rød life samt 8 gule og 2 grå damage tokens.
12. Kjør testen på nytt, men la laserskyting være på. Kontroller at du mister damage tokens på samme måte som tidligere dersom du blir truffet av et skudd.

### Flaggsjekk
Hensikt:
- Sjekke at flagg oppdateres ved besøk

1. Kjør programmet og trykk "TEST"
2. Klikk på "T" for å skru av laserskyting (laser på brett blir ikke skrudd av)
3. Tast inn følgende kombinasjon:
"R", "1", "L", "1", "R", "space"
4. Kontroller at det er en grønn hake ved det første flagget i user interface samt at backupen til den grønne brikken er flyttet til dette flagget på brettet.
5. Tast inn følgende kombinasjon:
"3", "U", "3", "R", "1", "space"
6. Kontroller at alle flaggene har en grønn hake og at backupen til den grønne brikken nå er plassert ved det tredje flagget.
Kontroller også at både life tokens og damage tokens er nullstilt samt at testen "Congratulations! Player green has won!" står midt på brettet
Spillet kjører videre i bakgrunnen.