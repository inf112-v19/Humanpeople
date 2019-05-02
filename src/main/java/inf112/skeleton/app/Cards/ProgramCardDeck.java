package inf112.skeleton.app.Cards;

import inf112.skeleton.app.Player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Random;

public class ProgramCardDeck {
    private ArrayList<ProgramCard> programCardDeck;


    public ProgramCardDeck() {
        programCardDeck = new ArrayList<>();
        newProgramCardDeck();
    }

    private void newProgramCardDeck() {
        //Add Move1 cards (18) p(490-650)
        for (int i = 0; i < 18; i++) {
            programCardDeck.add(new ProgramCard(ProgramType.MOVE1, (490 + (10 * i)),
                    "assets/cards/ProgramCards/Move1/move1p" + (490 + (10 * i)) + ".png"));
        }
        //Add move2 cards (12) p(670-780)
        for (int j = 0; j < 12; j++) {
            programCardDeck.add(new ProgramCard(ProgramType.MOVE2, (670 + (10 * j)),
                    "assets/cards/ProgramCards/Move2/move2p" + (670 + (10 * j)) + ".png"));
        }
        //Add move3 cards (6) p(790-840)
        for (int k = 0; k < 6; k++) {
            programCardDeck.add(new ProgramCard(ProgramType.MOVE3, (790 + (10 * k)),
                    "assets/cards/ProgramCards/Move3/move3p" + (790 + (10 * k)) + ".png"));
        }
        //Add backup cards (6) p(430-480)
        for (int l = 0; l < 6; l++) {
            programCardDeck.add(new ProgramCard(ProgramType.BACKWARD, (430 + (10 * l)),
                    "assets/cards/ProgramCards/backUp/backUp1p" + (430 + (10 * l)) + ".png"));
        }
        //Add rotate left cards (18) p(70-410)
        for (int n = 0; n < 18; n++) {
            programCardDeck.add(new ProgramCard(ProgramType.ROTATELEFT, (70 + (20 * n)),
                    "assets/cards/ProgramCards/rotateLeft/rotateLeftp" + (70 + (20 * n)) + ".png"));
        }
        //Add rotate right cards (18) p(80-420)
        for (int m = 0; m < 18; m++) {
            programCardDeck.add(new ProgramCard(ProgramType.ROTATERIGHT, (80 + (20 * m)),
                    "assets/cards/ProgramCards/rotateRight/rotateRightp" + (80 + (20 * m)) + ".png"));
        }
        //Add u turn cards (6) p(10-60)
        for (int o = 0; o < 6; o++) {
            programCardDeck.add(new ProgramCard(ProgramType.UTURN, (10 + (10 * o)),
                    "assets/cards/ProgramCards/uTurn/uTurnp" + (10 + (10 * o)) + ".png"));
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(this.programCardDeck);
    }

    public ProgramCard takeCard(int index) {
        if (getSizeOfDeck() < 1)
            throw new NoSuchElementException("There are no more cards to take.");

        if (index >= getSizeOfDeck())
            throw new IndexOutOfBoundsException("The index is too high.");

        ProgramCard card = programCardDeck.get(index);
        programCardDeck.remove(index);
        return card;
    }

    public ProgramCard takeTopCard() {
        if (getSizeOfDeck() < 1)
            throw new NoSuchElementException("There are no more cards to take.");

        ProgramCard topCard = programCardDeck.get(0);
        programCardDeck.remove(0);
        return topCard;
    }


    public ProgramCard takeRandomCard() {
        if (getSizeOfDeck() < 1)
            throw new NoSuchElementException("There are no more cards to take.");

        Random r = new Random();
        int index = r.nextInt(getSizeOfDeck());
        ProgramCard randomCard = takeCard(index);
        return randomCard;
    }

    /**
     * Give out cards to each player
     *
     * @param players
     */
    public void giveOutCardsToAllPlayers(ArrayList<Player> players) {
        if (getSizeOfDeck() < 70)
            newProgramCardDeck();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            ArrayList<ProgramCard> playerDeck = new ArrayList<>();

            for (int j = 0; j < PlayerDeck.MAX_NUMBER_CARDS_IN_DECK; j++)
                playerDeck.add(takeRandomCard());

            player.getPlayerDeck().setDeck(playerDeck);
        }
    }

    /**
     * Give out cards (fill the playerDeck) to a player
     *
     * @param player
     */
    public void giveOutCardsToPlayer(Player player) {
        if (getSizeOfDeck() < 70)
            newProgramCardDeck();

        ArrayList<ProgramCard> playerDeck = new ArrayList<>();
        for (int i = 0; i < PlayerDeck.MAX_NUMBER_CARDS_IN_DECK; i++)
            playerDeck.add(takeRandomCard());

        player.getPlayerDeck().setDeck(playerDeck);
    }

    public int getSizeOfDeck() {
        return programCardDeck.size();
    }

    public ArrayList<ProgramCard> getDeck() {
        return this.programCardDeck;
    }
}
