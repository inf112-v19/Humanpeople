## Manuelle tester for backupinteraksjoner, hull og destruksjon:

Hensikt: 
- Sjekke at spiller blir destruert ved å gå på hull
- Sjekke at spilleren ikke fortsetter å bevege seg etter å ha blitt destruert
- Sjekke at spiller returnerer til backup punkt etter å ha blitt destruert
- Sjekke at spiller blir destruert selv da den prøver å gå over hullet
- Sjekke at spiller legger igjen backup etter å ha endt en fase på ett flagg
- Sjekke at spiller ikke legger igjen backup hvis den går over flagget men 
	ikke avslutter fasen på flagget
- Sjekke at annen spiller kan legge igjen backup gitt at den ender fasen på flagg
- Sjekke at annen spiller blir destruert ved bevegelse over hull
- Sjekke at annen spiller ikke legger igjen backup hvis den beveger seg over 
	flagget men ikke avslutter fasen på flagget
- Sjekke at annen spiller returnerer til backup etter at den er blitt destruert
- Sjekke at annen spiller ikke fortsetter å runden etter destruksjon

---
### Test av destruksjon bevegelse på hull, returnering til backup og slutt av fase.

Hensikt:
- Sjekke at spiller blir destruert ved å gå på hull
- Sjekke at spilleren ikke fortsetter å bevege seg etter å ha blitt destruert
- Sjekke at spiller returnerer til backup punkt etter å ha blitt destruert

Test:

1. Kjør programmet og klikk på "Test" knappen.

2. Tast inn følgende kombinasjon:
  "3" "R" "1" "3" "L" "Space"

Resultat:

	Spilleren blir destruert da den går på hullet etter å ha fulgt
	instruksjonene "3" "R" "1". Blir flyttet til backup. Utfører ikke
	resten av instruksjonene.
	
---

### Test av destruksjon ved forsøk på å gå over hull

Hensikt:
- Sjekke at spiller blir destruert selv da den prøver å gå over hullet

Test:

1. Kjør programmet og klikk på "Test" knappen.

2. Tast inn følgende kombinasjon:
  "3" "R" "3" "3" "L" "Space"

Resultat:

	Spilleren blir destruert da den går på hullet etter å ha fulgt
	instruksjonene "3" "R" og 1 trinn av instuksjonen "3".
	Blir flyttet til backup. Utfører ikke resten av instruksjonene.
	
---

### Test av oppdatering av backup ved endt fase på backup

Hensikt:
- Sjekke at spiller legger igjen backup etter å ha endt en fase på ett flagg


Test:

1. Kjør programmet og klikk på "Test" knappen.

2. Tast inn følgende kombinasjon:
   "R" "1" "3" "3" "3" "Space"
   
Resultat:

	Spilleren går på flagg nr.1 og ender fasen her, etter instruksjon "R " "1".
	Her blir backup oppdatert. Spilleren faller i hullet etter instruksjon "3".
	Spilleren blir flyttet til oppdatert backup.
	
---

### Test av oppdatering av backup ved flytting over flagg

Hensikt:
- Sjekke at spiller ikke legger igjen backup hvis spiller går over flagget 
men ikke avslutter fasen på flagget.

Test:

1. Kjør programmet og klikk på "Test" knappen.

2. Tast inn følgende kombinasjon:
   "R" "3" "3" "3" "3" "Space"
   
Resultat:

	Spilleren går over flagg nr.1 og faller i hullet etter instruksjon "R" "3".
	Backup blir ikke flyttet/oppdatert. Spilleren blir flyttet til backup.
	
---


### Test av oppdatering av backup av annen spiller

Hensikt:
- Sjekke at annen spiller kan legge igjen backup gitt at den ender fasen på flagg
- Sjekke at annen spiller blir destruert ved bevegelse over hull

Test:

1. Kjør programmet og klikk på "Test" knappen.

2. Tast inn følgende kombinasjon:
   "R" "R" "1" "L" "3" "Space"

3. Tast inn følgende kombinasjon:
   "3" "3" "L" "1" "R" "Space"

4. Tast inn følgende kombinasjon:
   "1" "L" "1" "L" "3" "Space"

5. Tast inn følgende kombinasjon:
   "2" "L" "1" "R" "1" "Space"

6. Tast inn følgende kombinasjon:
   "R" "3" "1" "1" "1" "Space"

Resultat:

	Spiller(Grønn) dytter spiller(Mørkeblå) til flagg nr.2. Backup for spiller(Mørkeblå)
	blir oppdatert. Spiller(Grønn) dytter spiller(Mørkelå) ned i hull. Begge spillere
	returneres til backup.
	
---

### Test for annen spiller ikke legge igjen backup

Hensikt:
- Sjekke at annen spiller ikke legger igjen backup hvis den beveger seg over 
	flagget men ikke avslutter fasen på flagget
- Sjekke at annen spiller returnerer til backup etter at den er blitt destruert
- Sjekke at annen spiller ikke fortsetter å runden etter destruksjon

Test:

1. Kjør programmet og klikk på "Test" knappen.

2. Tast inn følgende kombinasjon:
   "R" "R" "1" "L" "3" "Space"

3. Tast inn følgende kombinasjon:
   "3" "3" "L" "1" "R" "Space"

4. Tast inn følgende kombinasjon:
   "1" "L" "1" "L" "3" "Space"

5. Tast inn følgende kombinasjon:
   "1" "3" "R" "1" "L" "Space"

6. Tast inn følgende kombinasjon:
   "1" "L" "1" "R" "1" "Space"

7. Tast inn følgende kombinasjon:
   "L" "1" "L" "1" "1" "Space"

Resultat:

	Spiller(Grønn) dytter spiller(Mørkeblå) over flagg nr.2. Backup blir ikke oppdatert.
	Spiller(Grønn) dytter spiller(Mørkeblå) i hull. Spiller(Mørkeblå) blir
	flyttet til backup og stopper bevegelse i runden.
	
---

### Test for at en spiller kan dø
Hensikt:
    - Spillere som har mistet alle life tokens, "død", skal ikke kunne bevege seg
    - Hver gang en spiller lander i et hull kan den ikke bevege seg videre i den nåværende runden.
      En spiller har 3 life tokens og regnes som død etter alle disse er mistet.

Test:

    1. Kjør programmet og klikk på "Test" knappen.

    2. Tast inn følgende kombinasjon:
    "R" "3" "3" "3" "3" "Space"

    3. Tast inn følgende kombinasjon:
    "3" "3" "3" "3" "3" "Space"

    4. Tast inn følgende kombinasjon:
    "3" "3" "3" "3" "3" "Space"

    5. Tast inn følgende kombinasjon (eller hvilkensomhelst annen kombinasjon av 1,2,3,L,R,U og deretter SPACE):
    "3" "3" "3" "3" "3" "Space"

Resultat:
    - Spiller lander i et hull hver runde (etter hver inputlinje)
    -Se at spiller ikke beveget seg etter siste input ble gitt
    - Spiller er "død"

---

Tester av: Eirik
