## Manuelle tester av skadeiteraksjon med antall utleverte kort
	og kort som l�ses.

Hensikt:
- Sjekke at spiller blir utdelt f�rre kort ved tatt skade.
- Sjekke at kort blir l�st ved nok tatt skade.
- Sjekke at spiller blir utdelt flere kort ved reparasjon av skiften�kkel.
- Sjekke at spiller blir utdelt flere kort ved reparasjon av power down.
- Sjekke at kort blir l�st opp ved reparasjon av skiften�kkel.
- Sjekke at kort blir l�st opp ved reparasjon av power down.
- Sjekke at spiller f�r utdelt riktig antall kort etter destruksjon.

---
### Test av riktig antall utdelt kort etter destruksjon.

Hensikt:
- Sjekke at spiller f�r utdelt riktig antall kort etter destruksjon.

Test:

1. Kj�r programmet og klikk p� "Test" knappen.

2. Klikk p� "C" tasten p� tastaturet.
I konsollen skal det st� "Card selection = true".
!Viktig: Ikke klikk "C" mer enn en gang. Hvis dette gj�res, start applikasjonen om.! 

3. Kj�r spilleren i n�rmeste hull.

Resultat:
	Spilleren f�r ved gjennoppretting 2 skadepoeng
	og f�r derfor bare 7 kort.

---
### Test av skadeinteraksjoner med utdeling av f�rre kort

Hensikt:
- Sjekke at spiller f�r utdelt riktig antall kort etter skade.
- Sjekke at spiller l�ser kort ved nok skade tatt.

Test:

1. Kj�r programmet og klikk p� "Test" knappen.

2. Klikk p� "C" tasten p� tastaturet.
I konsollen skal det st� "Card selection = true".
!Viktig: Ikke klikk "C" mer enn en gang. Hvis dette gj�res, start applikasjonen om.! 

3. Klikk p� "T" tasten p� tastaturet slik at de andre spillerne ikke skyter spillebrikken.

4. Kj�r spilleren opp i laseren over spilleren og avslutt runden med skadepoeng.

Resultat:
	Spilleren starter neste runde med antall f�rre kort lik antall skade tatt.
	Ved flere enn 4 skadepoeng blir kort l�st.


---
### Test av oppl�sing av kort ved powerdown

Hensikt:
- Sjekke at spiller f�r l�st opp kort og f�r utdelt riktig antall kort etter power down.

Test:

1. Kj�r programmet og klikk p� "Test" knappen.

2. Klikk p� "C" tasten p� tastaturet.
I konsollen skal det st� "Card selection = true".
!Viktig: Ikke klikk "C" mer enn en gang. Hvis dette gj�res, start applikasjonen om.! 

3. Klikk p� "T" tasten p� tastaturet slik at de andre spillerne ikke skyter spillebrikken.

4. Kj�r spilleren opp i laseren over spilleren. og avslutt runden med skade.

5. Gj�r power down.

Resultat:
	Spilleren starter neste runde med maks antall kort.

---
### Test av oppl�sing av kort ved powerdown

Hensikt:
- Sjekke at spiller f�r l�st opp kort og f�r utdelt riktig antall kort etter power down.

Test:

1. Kj�r programmet og klikk p� "Test" knappen.

2. Klikk p� "C" tasten p� tastaturet.
I konsollen skal det st� "Card selection = true".
!Viktig: Ikke klikk "C" mer enn en gang. Hvis dette gj�res, start applikasjonen om.! 

3. Klikk p� "T" tasten p� tastaturet slik at de andre spillerne ikke skyter spillebrikken.

4. Kj�r spilleren opp i laseren over spilleren. og avslutt runden med skade.

5. Kj�r spilleren p� skiften�kkel og avslutt en runde i p� skiften�kkelen.

Resultat:
	Spilleren starter neste runde med antall kort lik 9 minus antall skadepoeng.