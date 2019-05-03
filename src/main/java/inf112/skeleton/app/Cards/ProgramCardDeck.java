package inf112.skeleton.app.Cards;

import inf112.skeleton.app.Player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Random;

public class ProgramCardDeck {
    private ArrayList<ProgramCard> programCardDeck;
    private static ProgramCardDeck singleInstance;

    private ProgramCardDeck() {
        programCardDeck = new ArrayList<>();
        newProgramCardDeck();
    }

    private void newProgramCardDeck() {
        for (int i = 0; i < 18; i++) {
            programCardDeck.add(new ProgramCard(ProgramType.MOVE1, (490 + (10 * i)),
                    "assets/cards/ProgramCards/Move1/move1p" + (490 + (10 * i)) + ".png"));
        }
        for (int j = 0; j < 12; j++) {
            programCardDeck.add(new ProgramCard(ProgramType.MOVE2, (670 + (10 * j)),
                    "assets/cards/ProgramCards/Move2/move2p" + (670 + (10 * j)) + ".png"));
        }
        for (int k = 0; k < 6; k++) {
            programCardDeck.add(new ProgramCard(ProgramType.MOVE3, (790 + (10 * k)),
                    "assets/cards/ProgramCards/Move3/move3p" + (790 + (10 * k)) + ".png"));
        }
        for (int l = 0; l < 6; l++) {
            programCardDeck.add(new ProgramCard(ProgramType.BACKWARD, (430 + (10 * l)),
                    "assets/cards/ProgramCards/backUp/backUp1p" + (430 + (10 * l)) + ".png"));
        }
        for (int n = 0; n < 18; n++) {
            programCardDeck.add(new ProgramCard(ProgramType.ROTATELEFT, (70 + (20 * n)),
                    "assets/cards/ProgramCards/rotateLeft/rotateLeftp" + (70 + (20 * n)) + ".png"));
        }
        for (int m = 0; m < 18; m++) {
            programCardDeck.add(new ProgramCard(ProgramType.ROTATERIGHT, (80 + (20 * m)),
                    "assets/cards/ProgramCards/rotateRight/rotateRightp" + (80 + (20 * m)) + ".png"));
        }
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

    public void giveOutCardsToAllPlayers(ArrayList<Player> players) {
        for (Player player : players)
            giveOutCardsToPlayer(player);
    }

    public void giveOutCardsToPlayer(Player player) {
        if (programCardDeck.size() < 10) {
            resetSingleInstance();
            shuffleDeck();
        }
        ArrayList<ProgramCard> playerDeck = new ArrayList<>();
        for (int i = 0; i < player.getPlayerDeck().NUMBER_OF_NEW_CARDS_TO_DECK; i++)
            playerDeck.add(takeRandomCard());

        player.getPlayerDeck().setDeck(playerDeck);
    }

    public int getSizeOfDeck() {
        return programCardDeck.size();
    }

    public ArrayList<ProgramCard> getDeck() {
        return this.programCardDeck;
    }

    public static ProgramCardDeck getProgramCardDeckSingleton() {
        if (singleInstance == null) {
            singleInstance = new ProgramCardDeck();
        }
        return singleInstance;
    }

    public void resetSingleInstance() {
        programCardDeck.clear();
        newProgramCardDeck();
    }
}
