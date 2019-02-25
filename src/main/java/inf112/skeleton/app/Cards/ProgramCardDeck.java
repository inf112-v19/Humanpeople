package inf112.skeleton.app.Cards;

import java.util.ArrayList;
import java.util.Collections;

public class ProgramCardDeck {
	private ArrayList<ProgramCard> programCardDeck;

	
	public ProgramCardDeck() {
		programCardDeck = new ArrayList<ProgramCard>();
		newProgramCardDeck();
	}
	
	private void newProgramCardDeck() {
		//Add Move1 cards (18) p(490-650)
		for (int i=0; i<18; i++) {
			programCardDeck.add(new ProgramCard(ProgramType.MOVE1, (490+(10*i))));
		}
		//Add move2 cards (12) p(670-780)
		for (int j=0; j<12; j++) {
			programCardDeck.add(new ProgramCard(ProgramType.MOVE2, (670+(10*j))));
		}
		//Add move3 cards (6) p(790-840)
		for (int k=0; k<6; k++) {
			programCardDeck.add(new ProgramCard(ProgramType.MOVE3, (790+(10*k))));
		}
		//Add backup cards (6) p(430-480)
		for (int l=0; l<6; l++) {
			programCardDeck.add(new ProgramCard(ProgramType.BACKUP, (790+(10*l))));
		}
		//Add rotate right cards (18) p(80-420)
		for (int m=0; m<18; m++) {
			programCardDeck.add(new ProgramCard(ProgramType.ROTATERIGHT, (80+(20*m))));
		}
		//Add rotate left cards (18) p(70-410)
		for (int n=0; n<18; n++) {
			programCardDeck.add(new ProgramCard(ProgramType.ROTATELEFT, (70+(20*n))));
		}
		//Add u turn cards (6) p(10-60)
		for (int o=0; o<6; o++) {
			programCardDeck.add(new ProgramCard(ProgramType.UTURN, (10+(10*o))));
		}
	}
	
	public void shuffleDeck() {
		Collections.shuffle(this.programCardDeck);
	}
	
	public ProgramCard takeTopCard() {
		ProgramCard topCard = programCardDeck.get(0);
		programCardDeck.remove(0);
		return topCard;
	}
	
	public int getSizeOfDeck() {
		return programCardDeck.size();
	}
}
