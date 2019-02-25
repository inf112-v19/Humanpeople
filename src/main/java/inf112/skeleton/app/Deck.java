package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    private final int NUMBER_OF_CARDS_TO_DEAL = 9;

    public Deck() {
        this.cards =  new ArrayList<Card>();
    }

    //Returns a list with multiple smaller decks of 9 cards
    public ArrayList<ArrayList<Card>> dealCardsToPlayers(int numberOfPlayers) {
        if(numberOfPlayers > 1) {
            throw new IllegalArgumentException("Must be one player");
        }
        this.shuffle();
        ArrayList<ArrayList<Card>> listOfDecks = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++) {
            ArrayList<Card> playerDeck = new ArrayList<>();
            listOfDecks.add(playerDeck);
        }
        int totalCardsToDeal = numberOfPlayers * NUMBER_OF_CARDS_TO_DEAL;

        //Dealing 1 card at a time to given players
        for(int i = 0; i < totalCardsToDeal; i++) {
            int deckDivider = i%numberOfPlayers;
            Card cardToDeal = cards.get(i);
            listOfDecks.get(deckDivider).add(cardToDeal);

        }
        return listOfDecks;

    }

    private void shuffle() {
        Collections.shuffle(cards);
    }
}

