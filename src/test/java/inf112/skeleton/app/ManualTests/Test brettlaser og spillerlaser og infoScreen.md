## Test av brettlaser og spillerlaser
Hensikt:
- Sjekke at brettlasere tegnes p� brettet
- Sjekke at spillerlasere skytes og tegnes ved faseslutt
- Sjekke at spillere tar skade av brettlaser
- Sjekke at spillere tar skade av spillerlaser
- Sjekke at spillere ikke tar skade uten � v�re i kontakt med brettlaser eller spillerlaser
- Sjekke at spillerlaser ikke g�r gjennom vegger
- Sjekke at spillerlaser g�r gjennom flagg og hull
- Sjekke at spillerlaser ikke g�r gjennom spillere
 


### Brettlaser
Hensikt:
- Sjekke at brettlasere tegnes p� brettet
- Sjekke at spillere tar skade av brettlaser
- Sjekke at spillere ikke tar skade uten � v�re i kontakt med brettlaser eller spillerlaser

1. Kj�r programmet og trykk "TEST". Kontroller at det er tegnet en horisontal laser over tre felt rett nord for gr�nn brikke.
2. Trykk p� "T" for � skru av spillerlasere.
3. Tast inn f�lgende kombinasjon:
"1", "1", "U", "U", "U", "space"
4. Klikk p� "J" og kontroller i konsollen at damage token for gr�nn brikke er 9 samt at du har 9 gule og 1 gr� damage token i userInterface. Hold "TAB" og sjekk at informasjonen stemmer med statusboard.
5. Tast inn f�lgende kombinasjon:
"2", "1", "U", "U", "U", "space"
6. Kontroller p� samme m�te som i pkt 4. Du skal ha tatt �n mer skade denne gangen.
7. Tast inn f�lgende kombinasjon:
"2", "U", "U", "U", "U", "space"
8. Kontroller p� samme m�te som pkt. 4. Du skal ha tatt ytterligere 5 skade.
9. Tast inn f�lgende kombinasjon:
"U", "U", "U", "U", "U", "space"
10. Kontroller at gr�nn spiller ble �delagt og returnerte til backup.

**Sjekk at spiller IKKE tar skade ved bevegelse i n�rheten av laser:
11. Start spillet p� nytt og f�lg pkt. 1. og pkt. 2.
12. Tast inn f�lgende kombinasjon:
"R", "2", "L", "2", "L", "space"
"3", "U", "3", "R", "2", "space"
13. Kontroller p� samme m�te som i pkt. 4. Denne gangen skal ikke spilleren ha tatt skade.



### Spillerlaser
Hensikt:
- Sjekke at spillere tar skade av spillerlaser
- Sjekke at spillerlaser ikke g�r gjennom vegger
- Sjekke at spillerlaser g�r gjennom flagg og hull
- Sjekke at spillerlasere skytes og tegnes ved faseslutt
- Sjekke at spillere ikke tar skade uten � v�re i kontakt med brettlaser eller spillerlaser

1. Kj�r programmet og trykk "TEST"
2. Klikk p� "H" og kontroller at det tegnes laser fra alle brikkene i retningen de peker.
3. Kontroller at laseren fra pkt. 2. forsvinner fra brettet og at brettlaseren forblir p� sin posisjon.
4. Klikk p� "J" og kontroller at gul og lysebl� spiller har 9 damage tokens. Hold "TAB" og sjekk at informasjonen stemmer med statusboard.
5. Gjenta pkt. 2. og pkt. 4. ni ganger. Kontroller at gul og lysebl� brikke er blitt gr� og at deres damage tokens er redusert til 0.
6. Klikk p� "H" og kontroller at de to gr� brikkene ikke skyter laser, mens gr�nn og m�rkebl� brikke fortsatt skyter.
7. Klikk "J" og kontroller at informasjonen er uendret siden sist. Hold "TAB" og sjekk at informasjonen stemmer med statusboard.
(siden det ikke enda er gjennomf�rt en fase eller runde, vil de gr�/�delagte brikkene forbli inaktive fram til n�v�rende runde er over).
8. Tast inn f�lgende kombinasjon og observer at gr�nn spiller skyter mot m�rkebl� spiller:
"R", "B", "B", "B", "B", "space"
9. Kontroller at laseren ikke tegnes forbi veggen mellom gr�nn og m�rkebl� spiller.
10. Trykk "J" og kontroller at m�rkebl� spiller er uskadd og har 10 damage tokens. Hold "TAB" og sjekk at informasjonen stemmer med statusboard.
11. Avslutt. Kj�r programmet og trykk "TEST". Trykk "T" for � skru av laser.
12. Tast inn f�lgende kombinasjon:
"2", "3", "2", "1", "R", "space"
"2", "R", "B", "B", "B", "space"
13. Trykk "T" for � aktivere laser -> trykk "H" og deretter "J" og kontroller at gr�nn spiller skyter og skader gul spiller og at lysebl� spiller er uskadd. Hold "TAB" og sjekk at informasjonen stemmer med statusboard. M�rkebl� spiller skal fortsatt bli skutt og ta skade av lysel� spiller.


