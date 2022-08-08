/**
 * @author ricecakesreece
 */

package PokerGame;

public class Card {
    private String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
//    private String[] suits = {"♥", "♠", "♦", "♣"};
    private String[] suits = {" Hearts", " Spades", " Diamonds", " Clubs"};

    private int rank;
    private int suit;

    public Card(int rank, int suit) {
    	if(rank > 12 || rank < 0 || suit > 3 || suit < 0)
    		throw new IllegalArgumentException("Invalid card value.");
    	
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return ranks[rank] + suits[suit];
    }
}
