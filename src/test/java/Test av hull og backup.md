Manuelle tester for backupinteraksjoner og destruksjon:
Hensikt: 
- Sjekke at spiller blir destruert ved � g� p� hull
- Sjekke at spilleren ikke fortsetter � bevege seg etter � ha blitt destruert
- Sjekke at spiller returnerer til backup punkt etter � ha blitt destruert
- Sjekke at spiller blir destruert selv da den pr�ver � g� over hullet
- Sjekke at spiller legger igjen backup etter � ha endt en fase p� ett flagg
- Sjekke at spiller ikke legger igjen backup hvis den g�r over flagget men 
	ikke avslutter fasen p� flagget
- Sjekke at annen spiller kan legge igjen backup gitt at den ender fasen p� flagg
- Sjekke at annen spiller blir destruert ved bevegelse over hull
- Sjekke at annen spiller ikke legger igjen backup hvis den beveger seg over 
	flagget men ikke avslutter fasen p� flagget
- Sjekke at annen spiller returnerer til backup etter at den er blitt destruert
- Sjekke at annen spiller ikke fortsetter � runden etter destruksjon

---
###Test
Test av destruksjon bevegelse p� hull, returnering til backup og slutt av fase.
Hensikt:
- Sjekke at spiller blir destruert ved � g� p� hull
- Sjekke at spilleren ikke fortsetter � bevege seg etter � ha blitt destruert
- Sjekke at spiller returnerer til backup punkt etter � ha blitt destruert

1. Kj�r programmet og klikk p� "Test" knappen.

2. Tast inn f�lgende kombinasjon:
  "3" "R" "1" "3" "L"

Resultat:
	Spilleren blir destruert da den g�r p� hullet etter � ha fulgt
	instruksjonene "3" "R" "1". Blir flyttet til backup. Utf�rer ikke
	resten av instruksjonene.
---


---
###Test
Test av destruksjon ved fors�k p� � g� over hull
Hensikt:
- Sjekke at spiller blir destruert selv da den pr�ver � g� over hullet

1. Kj�r programmet og klikk p� "Test" knappen.

2. Tast inn f�lgende kombinasjon:
  "3" "R" "3" "3" "L"

Resultat:
	Spilleren blir destruert da den g�r p� hullet etter � ha fulgt
	instruksjonene "3" "R" og 1 trinn av instuksjonen "3".
	Blir flyttet til backup. Utf�rer ikke resten av instruksjonene.
---


---
###Test
Test av oppdatering av backup ved endt fase p� backup
Hensikt:
- Sjekke at spiller legger igjen backup etter � ha endt en fase p� ett flagg

1. Kj�r programmet og klikk p� "Test" knappen.

2. Tast inn f�lgende kombinasjon:
   "R" "1" "3" "3" "3"
Resultat:
	Spilleren g�r p� flagg nr.1 og ender fasen her, etter instruksjon "R " "1".
	Her blir backup oppdatert. Spilleren faller i hullet etter instruksjon "3".
	Spilleren blir flyttet til oppdatert backup.
---


---
###Test
Test av oppdatering av backup ved flytting over flagg
Hensikt:
- Sjekke at spiller ikke legger igjen backup hvis spiller g�r over flagget 
men ikke avslutter fasen p� flagget.

1. Kj�r programmet og klikk p� "Test" knappen.

2. Tast inn f�lgende kombinasjon:
   "R" "3" "3" "3" "3"
Resultat:
	Spilleren g�r over flagg nr.1 og faller i hullet etter instruksjon "R" "3".
	Backup blir ikke flyttet/oppdatert. Spilleren blir flyttet til backup.
---


---
###Test
Test av oppdatering av backup av annen spiller
Hensikt:
- Sjekke at annen spiller kan legge igjen backup gitt at den ender fasen p� flagg
- Sjekke at annen spiller blir destruert ved bevegelse over hull

1. Kj�r programmet og klikk p� "Test" knappen.

2. Tast inn f�lgende kombinasjon:
   "R" "R" "1" "L" "3"

3. Tast inn f�lgende kombinasjon:
   "3" "3" "L" "1" "R"

4. Tast inn f�lgende kombinasjon:
   "1" "L" "1" "L" "3"

5. Tast inn f�lgende kombinasjon:
   "2" "L" "1" "R" "1"

6. Tast inn f�lgende kombinasjon:
   "R" "3" "1" "1" "1"

Resultat:
	Spiller(Gr�nn) dytter spiller(M�rkebl�) til flagg nr.2. Backup for spiller(M�rkebl�)
	blir oppdatert. Spiller(Gr�nn) dytter spiller(M�rkel�) ned i hull. Begge spillere
	returneres til backup.
---


---
###Test
Test av ikke legge igjen backup
Hensikt:
- Sjekke at annen spiller ikke legger igjen backup hvis den beveger seg over 
	flagget men ikke avslutter fasen p� flagget
- Sjekke at annen spiller returnerer til backup etter at den er blitt destruert
- Sjekke at annen spiller ikke fortsetter � runden etter destruksjon

1. Kj�r programmet og klikk p� "Test" knappen.

2. Tast inn f�lgende kombinasjon:
   "R" "R" "1" "L" "3"

3. Tast inn f�lgende kombinasjon:
   "3" "3" "L" "1" "R"

4. Tast inn f�lgende kombinasjon:
   "1" "L" "1" "L" "3"

5. Tast inn f�lgende kombinasjon:
   "1" "3" "R" "1" "L"

6. Tast inn f�lgende kombinasjon:
   "1" "L" "1" "R" "1"

7. Tast inn f�lgende kombinasjon:
   "L" "1" "L" "1" "1"

Resultat:
	Spiller(Gr�nn) dytter spiller(M�rkebl�) over flagg nr.2. Backup blir ikke oppdatert.
	Spiller(Gr�nn) dytter spiller(M�rkebl�) i hull. Spiller(M�rkebl�) blir
	flyttet til backup og stopper bevegelse i runden.
---