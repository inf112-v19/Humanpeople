package inf112.skeleton.app.Cards;

import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.MovementCard;
import inf112.skeleton.app.Cards.RotationalCards;
import inf112.skeleton.app.GameObjects.Directions.Rotation;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    private  int numberOfCardsToDeal;

    public Deck() {
        this.cards =  new ArrayList<Card>();
        fillDeck();

    }

    public void fillDeck() {
        //Adding the straightforward movementcards to deck
        int priority = 490;
        for (int i = 0; i < 18; i++) {

            MovementCard movementCard = new MovementCard(priority, 1);
            cards.add(movementCard);
            priority = priority + 10;
        }
        priority = 670;
        for (int i = 0; i < 12; i++) {

            MovementCard movementCard = new MovementCard(priority, 2);
            cards.add(movementCard);
            priority = priority + 10;
        }
        priority = 790;
        for (int i = 0; i < 6; i++) {

            MovementCard movementCard = new MovementCard(priority, 3);
            cards.add(movementCard);
            priority = priority + 10;
        }
        //Adding 6 one-movement backwards cards to deck
         priority = 430;
        for (int i = 0; i < 6; i++) {

            MovementCard movementCard = new MovementCard(priority, true);
            cards.add(movementCard);
            priority = priority + 10;
        }
        //Adding 18 rightRotationalCards to deck
        priority = 80;
        for (int i = 0; i < 18; i++) {

            RotationalCards rotationalRightCards = new RotationalCards(priority, Rotation.RIGHT);
            cards.add(rotationalRightCards);
            priority = priority + 20;
        }
        //Adding 18 leftRotationalCards to deck
        priority = 70;
        for (int i = 0; i < 18; i++) {

            RotationalCards rotationalLeftCards = new RotationalCards(priority, Rotation.LEFT);
            cards.add(rotationalLeftCards);
            priority = priority + 20;
        }
        //Adding 6 UTurn cards to deck
        priority = 10;
        for (int i = 0; i < 6; i++) {

            RotationalCards rotationalUTurnCards = new RotationalCards(priority, Rotation.UTURN);
            cards.add(rotationalUTurnCards);
            priority = priority + 10;
        }


    }


    //Returns a list with multiple smaller decks of 9 cards
    public ArrayList<ArrayList<Card>> dealCardsToPlayers(int numberOfPlayers) {
        if(numberOfPlayers < 1) {
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

