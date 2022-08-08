/**
 * @author ricecakesreece
 */

package PokerTests;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import PokerGame.Card;
import PokerGame.PokerGame;

public class PokerGameTests {

	PokerGame testGame = new PokerGame();

	// The new deck should be 52 cards in length
	@Test
	public void newDeckSizeTest() {
		testGame.setNewDeck();

		assertEquals(52, testGame.getDeck().size());
	}

	// All cards in the deck should be unique
	@Test
	public void noDuplicatesDeckTest() {
		testGame.setNewDeck();

		ArrayList<Card> deck = testGame.getDeck();
		boolean dupe = false;

		Set<String> set = new HashSet<String>();
	    for (Card card: deck) if (!set.add(card.toString())) dupe = true;

		assertFalse(dupe);
	}

	// currHand should be of length 5 for PokerGame
	@Test
	public void currHandLengthTest() {
		assertEquals(5, testGame.getHand().size());
	}

	// matchHand Straight Flush case
	@Test
	public void straightFlushTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();

		testHand.add(new Card(10,3)); // J♣
		testHand.add(new Card(9,3)); // 10♣
		testHand.add(new Card(8,3)); // 9♣
		testHand.add(new Card(7,3)); // 8♣
		testHand.add(new Card(6,3)); // 7♣

		testGame.setHand(testHand);
		assertEquals("Straight flush", testGame.matchHand());
	}

	// matchHand Four of a Kind case
	@Test
	public void fourOfAKindTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();

		testHand.add(new Card(4,3)); // 5♣
		testHand.add(new Card(4,1)); // 5♠
		testHand.add(new Card(4,2)); // 5♦
		testHand.add(new Card(4,0)); // 5♥
		testHand.add(new Card(1,1)); // 2♠

		testGame.setHand(testHand);
		assertEquals("Four of a kind", testGame.matchHand());
	}

	// matchHand Full house case
	@Test
	public void fullHouseTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();

		testHand.add(new Card(5,3)); // 6♣
		testHand.add(new Card(5,1)); // 6♠
		testHand.add(new Card(5,2)); // 6♦
		testHand.add(new Card(12,0)); // K♥
		testHand.add(new Card(12,1)); // K♠

		testGame.setHand(testHand);
		assertEquals("Full house", testGame.matchHand());
	}


	// matchHand Flush case
	@Test
	public void flushTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();

		testHand.add(new Card(10,2)); // J♦
		testHand.add(new Card(8,2)); // 9♦
		testHand.add(new Card(7,2)); // 8♦
		testHand.add(new Card(3,2)); // 4♦
		testHand.add(new Card(2,2)); // 3♦

		testGame.setHand(testHand);
		assertEquals("Flush", testGame.matchHand());
	}

	// matchHand Straight case
	@Test
	public void straightTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();

		testHand.add(new Card(9,2)); // 10♦
		testHand.add(new Card(8,1)); // 9♠
		testHand.add(new Card(7,0)); // 8♥
		testHand.add(new Card(6,2)); // 7♦
		testHand.add(new Card(5,3)); // 6♣

		testGame.setHand(testHand);
		assertEquals("Straight", testGame.matchHand());
	}

	// matchHand Three of a kind case
	@Test
	public void threeOfAKindTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();

		testHand.add(new Card(11,3)); // Q♣
		testHand.add(new Card(11,1)); // Q♠
		testHand.add(new Card(11,0)); // Q♥
		testHand.add(new Card(8,0)); // 9♥
		testHand.add(new Card(1,1)); // 2♠

		testGame.setHand(testHand);
		assertEquals("Three of a kind", testGame.matchHand());
	}

	// matchHand Two pair case
	@Test
	public void twoPairTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();

		testHand.add(new Card(10,0)); // J♥
		testHand.add(new Card(10,1)); // J♠
		testHand.add(new Card(2,3)); // 3♣
		testHand.add(new Card(2,1)); // 3♠
		testHand.add(new Card(1,0)); // 2♥

		testGame.setHand(testHand);
		assertEquals("Two pair", testGame.matchHand());
	}

	// matchHand One pair case
	@Test
	public void onePairTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();

		testHand.add(new Card(9,1)); // 10♠
		testHand.add(new Card(9,0)); // 10♥
		testHand.add(new Card(7,1)); // 8♠
		testHand.add(new Card(6,0)); // 7♥
		testHand.add(new Card(3,3)); // 4♣

		testGame.setHand(testHand);
		assertEquals("One pair", testGame.matchHand());
	}

	// matchHand High card case
	@Test
	public void highCardTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();

		testHand.add(new Card(12,2)); // K♦
		testHand.add(new Card(11,2)); // Q♦
		testHand.add(new Card(6,1)); // 7♠
		testHand.add(new Card(3,1)); // 4♠
		testHand.add(new Card(2,0)); // 3♥

		testGame.setHand(testHand);
		assertEquals("High card", testGame.matchHand());
	}

	// Case for invalid cards
	@Test(expected = IllegalArgumentException.class)
	public void invalidCardTest() {
		Card invalidCard = new Card(13,1);
	}

	// Case for trying to draw more cards than in the deck
	@Test(expected = IllegalArgumentException.class)
	public void invalidDrawTest() {
		testGame.setNewDeck();

		for(int i = 0; i < 11; i++) {
			testGame.drawHand();
		}
	}

	// Case for invalid hand size
	@Test(expected = IllegalArgumentException.class)
	public void invalidHandTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();

		testHand.add(new Card(12,2)); // K♦
		testHand.add(new Card(11,2)); // Q♦
		testHand.add(new Card(6,1)); // 7♠
		testHand.add(new Card(3,1)); // 4♠

		testGame.setHand(testHand);
		testGame.matchHand();
	}

}
