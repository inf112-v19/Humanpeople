#Manuelle tester for PowerDown

Hensikt:
- Sjekke at spilleren ikke mottar nye kort og ikke beveger seg etter en runde etter powerDown er valgt
- Sjekke at spilleren får tilbake MAX_HEALTH ved PowerDown
- Sjekke at spilleren kan vinne spillet
- Sjekke at tannhjul roterer spillerne
- Sjekke at tannhjul ikke roterer spillerne hvis de ikke står på tannhjulet ved endt fase

### Test av ingen bevegelse ved powerDown

Hensikt:
- Sjekke at spiller ikke beveger seg når PowerDown har blitt valgt
- Sjekke at spiller ikke mottar kort i powerDown runde

Test:

1. Kjør programmet og klikk på "SinglePlayer"-knappen.

2. Velg 5 tilfeldige kort

3. Trykk på "Power Down"-knappen

Resultat:

	Spilleren beveger seg i henhold til de 5 kortene som ble valgt.
	Den påfølgende runden skal alle kortene klareres fra skjærmen (spilleren kan ikke velge kort).
	Spilleren står stille ut runden

	MERK: Hvis spilleren står på rullebånd eller kommer i kolisjon med andre spillere vil spilleren flytte på seg.
	      Dette er i henhold med spillets regler.

---

### Test av MAX_HEALTH ved PowerDown

Hensikt:
- Sjekke at spiller får MAX_HEALTH ved powerDown

Test:

1. Kjør programmet og klikk på "SinglePlayer"-knappen.

2. Velg MOVE 1 som første kort. Velg deretter 4 tilfeldige kort.
   (Hvis "MOVE 1"-kort ikke kommer opp på skjærmen, start testen på nytt).

3. Trykk på "Power Down"-knappen

Resultat:

	Spilleren beveger seg en tile fremover og havner på et rullebånd. Rullebåndet dytter spilleren i et hull.
	Spillere er nå "destroyed" og mister all "health".

	Etter runden er over vil spilleren motta MAX_HEALTH minus 2 (har alle damageTokens utenom 2).
	Deretter er spilleren "powered down" og mottar MAX_HEALTH (har alle damageTokens).
	Spilleren får MAX_HEALTH ved starten av runden og brukeren vil dermed ikke se at spilleren har MAX_HEALTH - 2

	MERK: Spilleren kan bli skutt med laser av de andre spillerne. Dette vil redusere antall damageTokens spilleren har.

---

### Test av vinn-mekanisme

Hensikt:
- Sjekke at spilleren kan vinne spillet

Test:

1. Kjør programmet og klikk på "SinglePlayer"-knappen.

2. Velg kort og beveg spilleren til flagg nr. 1

3. Velg kort og beveg spilleren til flagg nr. 2

4. Velg kort og beveg spilleren til flagg nr. 3

Resultat:

    Tekst skrives over skjermen med "Congratulations! Player green has won!",
    og spilleren fjernes fra brettet.
    Spillet fortsetter i bakgrunnen.

---

### Test av rotasjon ved endt fase på høyre-tannhjul

Hensikt:
- Sjekke at spilleren roterer til høyre (med klokken) ved endt fase på høyre-tannhjul

Test:

1. Kjør programmet og klikk på "Test"-knappen.

2. Tast inn følgende kombinasjon:
   "3", "3", "1", "U", "U", "Space"

Resultat:

	Spilleren beveger til høyre-tannhjul og roterer med klokken.
	Spilleren beveger seg deretter en tile og går til høyre for tannhjulet
	(fordi tannhjulet roterte spilleren den retningen).
	Spilleren deretter tar 2 "u-turns" (ingen betydning).

---

### Test av rotasjon ved endt fase på venstre-tannhjul

Hensikt:
- Sjekke at spilleren roterer til vensre (mot klokken) ved endt fase på venstre-tannhjul

Test:

1. Kjør programmet og klikk på "Test"-knappen.

2. Tast inn følgende kombinasjon:
   "3", "2", "2", "1", "U", "Space"

Resultat:

	Spilleren beveger til venstre-tannhjul og roterer mot klokken.
	Spilleren beveger seg deretter en tile og går til venstre for tannhjulet
	(fordi tannhjulet roterte spilleren den retningen).
	Spilleren deretter tar 1 "u-turns" (ingen betydning).

---

### Test av ingen rotasjon når spiller går over tannhjul utenom endt fase

Hensikt:
- Sjekke at spilleren ikke roterer på tannhjul når fasen ikke er over

Test:

1. Kjør programmet og klikk på "Test"-knappen.

2. Tast inn følgende kombinasjon:
   "3", "2", "3", "U", "U", "Space"

Resultat:

	Spilleren beveger seg over tannhjul uten rotasjon.
	Spilleren deretter tar 2 "u-turns" (ingen betydning).

---