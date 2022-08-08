/**
 * @author ricecakesreece
 */

package PokerGame;

public class Main {
    public static void main(String[] args)
    {
        PokerGame pokerGame = new PokerGame();

        System.out.println("\nYou have " + pokerGame.toString());
        System.out.println("This hand is a: " + pokerGame.matchHand());
    }
}
