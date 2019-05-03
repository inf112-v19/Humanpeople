## Test av brettlaser og spillerlaser
Hensikt:
- Sjekke at brettlasere tegnes på brettet
- Sjekke at spillerlasere skytes og tegnes ved faseslutt
- Sjekke at spillere tar skade av brettlaser
- Sjekke at spillere tar skade av spillerlaser
- Sjekke at spillere ikke tar skade uten å være i kontakt med brettlaser eller spillerlaser
- Sjekke at spillerlaser ikke går gjennom vegger
- Sjekke at spillerlaser går gjennom flagg og hull
- Sjekke at spillerlaser ikke går gjennom spillere
 


### Brettlaser
Hensikt:
- Sjekke at brettlasere tegnes på brettet
- Sjekke at spillere tar skade av brettlaser
- Sjekke at spillere ikke tar skade uten å være i kontakt med brettlaser eller spillerlaser

1. Kjør programmet og trykk "TEST". Kontroller at det er tegnet en horisontal laser over tre felt rett nord for grønn brikke.
2. Trykk på "T" for å skru av spillerlasere.
3. Tast inn følgende kombinasjon:
"1", "1", "U", "U", "U", "space"
4. Klikk på "J" og kontroller i konsollen at damage token for grønn brikke er 9 samt at du har 9 gule og 1 grå damage token i userInterface. Hold "TAB" og sjekk at informasjonen stemmer med statusboard.
5. Tast inn følgende kombinasjon:
"2", "1", "U", "U", "U", "space"
6. Kontroller på samme måte som i pkt 4. Du skal ha tatt èn mer skade denne gangen.
7. Tast inn følgende kombinasjon:
"2", "U", "U", "U", "U", "space"
8. Kontroller på samme måte som pkt. 4. Du skal ha tatt ytterligere 5 skade.
9. Tast inn følgende kombinasjon:
"U", "U", "U", "U", "U", "space"
10. Kontroller at grønn spiller ble ødelagt og returnerte til backup.

**Sjekk at spiller IKKE tar skade ved bevegelse i nærheten av laser:
11. Start spillet på nytt og følg pkt. 1. og pkt. 2.
12. Tast inn følgende kombinasjon:
"R", "2", "L", "2", "L", "space"
"3", "U", "3", "R", "2", "space"
13. Kontroller på samme måte som i pkt. 4. Denne gangen skal ikke spilleren ha tatt skade.



### Spillerlaser
Hensikt:
- Sjekke at spillere tar skade av spillerlaser
- Sjekke at spillerlaser ikke går gjennom vegger
- Sjekke at spillerlaser går gjennom flagg og hull
- Sjekke at spillerlasere skytes og tegnes ved faseslutt
- Sjekke at spillere ikke tar skade uten å være i kontakt med brettlaser eller spillerlaser

1. Kjør programmet og trykk "TEST"
2. Klikk på "H" og kontroller at det tegnes laser fra alle brikkene i retningen de peker.
3. Kontroller at laseren fra pkt. 2. forsvinner fra brettet og at brettlaseren forblir på sin posisjon.
4. Klikk på "J" og kontroller at gul og lyseblå spiller har 9 damage tokens. Hold "TAB" og sjekk at informasjonen stemmer med statusboard.
5. Gjenta pkt. 2. og pkt. 4. ni ganger. Kontroller at gul og lyseblå brikke er blitt grå og at deres damage tokens er redusert til 0.
6. Klikk på "H" og kontroller at de to grå brikkene ikke skyter laser, mens grønn og mørkeblå brikke fortsatt skyter.
7. Klikk "J" og kontroller at informasjonen er uendret siden sist. Hold "TAB" og sjekk at informasjonen stemmer med statusboard.
(siden det ikke enda er gjennomført en fase eller runde, vil de grå/ødelagte brikkene forbli inaktive fram til nåværende runde er over).
8. Tast inn følgende kombinasjon og observer at grønn spiller skyter mot mørkeblå spiller:
"R", "B", "B", "B", "B", "space"
9. Kontroller at laseren ikke tegnes forbi veggen mellom grønn og mørkeblå spiller.
10. Trykk "J" og kontroller at mørkeblå spiller er uskadd og har 10 damage tokens. Hold "TAB" og sjekk at informasjonen stemmer med statusboard.
11. Avslutt. Kjør programmet og trykk "TEST". Trykk "T" for å skru av laser.
12. Tast inn følgende kombinasjon:
"2", "3", "2", "1", "R", "space"
"2", "R", "B", "B", "B", "space"
13. Trykk "T" for å aktivere laser -> trykk "H" og deretter "J" og kontroller at grønn spiller skyter og skader gul spiller og at lyseblå spiller er uskadd. Hold "TAB" og sjekk at informasjonen stemmer med statusboard. Mørkeblå spiller skal fortsatt bli skutt og ta skade av lyselå spiller.


