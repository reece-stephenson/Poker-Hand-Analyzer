/**
 * @author ricecakesreece
 */

package PokerGame;

import java.util.*;

// Game is abstract so that different variations of poker can be implemented
public abstract class Game {

	// Number of cards to be drawn should be decided by child classes
    protected int numCards;
    
    protected ArrayList<Card> deck;
    protected ArrayList<Card> currHand;

    public Game() {
        this.setNewDeck();
        this.shuffleDeck();
    }

    public void setNewDeck() {
        deck = new ArrayList<Card>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                deck.add(new Card(j, i));
            }
        }
    }

    public void shuffleDeck() {
        System.out.println("Shuffling deck... ");

        // Handy shuffle method from collections
        Collections.shuffle(deck);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
    
    public void setHand(ArrayList<Card> cards) {
    	if(cards.size() == numCards)
    		currHand = cards;
    	else 
    		throw new IllegalArgumentException("Incorrect number of cards provided.");
    }
    
    public ArrayList<Card> getHand() {
    	return this.currHand;
    }

    public void drawHand() {
        currHand = new ArrayList<Card>();

        if (deck.size() >= numCards) {
	        for (int i = 0; i < numCards; i++) {
	            currHand.add(deck.remove(0));
	        }	
        }
        else
        	throw new IllegalArgumentException("Not enough cards, please create a new deck.");
    }
    
    @Override
    public String toString() {
        String outp = "";

        for (int i = 0; i < numCards-1; i++) {
            outp += currHand.get(i).toString() + ", ";
        }
        
        if(numCards >= 1)
        	outp += currHand.get(numCards-1).toString();

        return outp;
    }

    // Child classes can implement the hand matching algorithm as needed by the rules of the game
    public abstract String matchHand();
}
