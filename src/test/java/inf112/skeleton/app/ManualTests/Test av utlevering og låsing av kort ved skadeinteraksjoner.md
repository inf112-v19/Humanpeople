## Manuelle tester av skadeiteraksjon med antall utleverte kort
	og kort som låses.

Hensikt:
- Sjekke at spiller blir utdelt færre kort ved tatt skade.
- Sjekke at kort blir låst ved nok tatt skade.
- Sjekke at spiller blir utdelt flere kort ved reparasjon av skiftenøkkel.
- Sjekke at spiller blir utdelt flere kort ved reparasjon av power down.
- Sjekke at kort blir låst opp ved reparasjon av skiftenøkkel.
- Sjekke at kort blir låst opp ved reparasjon av power down.
- Sjekke at spiller får utdelt riktig antall kort etter destruksjon.

---
### Test av riktig antall utdelt kort etter destruksjon.

Hensikt:
- Sjekke at spiller får utdelt riktig antall kort etter destruksjon.

Test:

1. Kjør programmet og klikk på "Test" knappen.

2. Klikk på "C" tasten på tastaturet.
I konsollen skal det stå "Card selection = true".
!Viktig: Ikke klikk "C" mer enn en gang. Hvis dette gjøres, start applikasjonen om.! 

3. Kjør spilleren i nærmeste hull.

Resultat:
	Spilleren får ved gjennoppretting 2 skadepoeng
	og får derfor bare 7 kort.

---
### Test av skadeinteraksjoner med utdeling av færre kort

Hensikt:
- Sjekke at spiller får utdelt riktig antall kort etter skade.
- Sjekke at spiller låser kort ved nok skade tatt.

Test:

1. Kjør programmet og klikk på "Test" knappen.

2. Klikk på "C" tasten på tastaturet.
I konsollen skal det stå "Card selection = true".
!Viktig: Ikke klikk "C" mer enn en gang. Hvis dette gjøres, start applikasjonen om.! 

3. Klikk på "T" tasten på tastaturet slik at de andre spillerne ikke skyter spillebrikken.

4. Kjør spilleren opp i laseren over spilleren og avslutt runden med skadepoeng.

Resultat:
	Spilleren starter neste runde med antall færre kort lik antall skade tatt.
	Ved flere enn 4 skadepoeng blir kort låst.


---
### Test av opplåsing av kort ved powerdown

Hensikt:
- Sjekke at spiller får låst opp kort og får utdelt riktig antall kort etter power down.

Test:

1. Kjør programmet og klikk på "Test" knappen.

2. Klikk på "C" tasten på tastaturet.
I konsollen skal det stå "Card selection = true".
!Viktig: Ikke klikk "C" mer enn en gang. Hvis dette gjøres, start applikasjonen om.! 

3. Klikk på "T" tasten på tastaturet slik at de andre spillerne ikke skyter spillebrikken.

4. Kjør spilleren opp i laseren over spilleren. og avslutt runden med skade.

5. Gjør power down.

Resultat:
	Spilleren starter neste runde med maks antall kort.

---
### Test av opplåsing av kort ved powerdown

Hensikt:
- Sjekke at spiller får låst opp kort og får utdelt riktig antall kort etter power down.

Test:

1. Kjør programmet og klikk på "Test" knappen.

2. Klikk på "C" tasten på tastaturet.
I konsollen skal det stå "Card selection = true".
!Viktig: Ikke klikk "C" mer enn en gang. Hvis dette gjøres, start applikasjonen om.! 

3. Klikk på "T" tasten på tastaturet slik at de andre spillerne ikke skyter spillebrikken.

4. Kjør spilleren opp i laseren over spilleren. og avslutt runden med skade.

5. Kjør spilleren på skiftenøkkel og avslutt en runde i på skiftenøkkelen.

Resultat:
	Spilleren starter neste runde med antall kort lik 9 minus antall skadepoeng.