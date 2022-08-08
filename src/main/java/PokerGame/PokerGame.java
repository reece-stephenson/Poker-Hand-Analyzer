/**
 * @author ricecakesreece
 */

package PokerGame;

//Each different game of poker has its own number of cards and matchHand() implementation
public class PokerGame extends Game {

 public PokerGame() {
     this.numCards = 5;
     this.drawHand();
 }

 public String matchHand(){
 	if(currHand.size() != numCards)
 		throw new IllegalArgumentException("This game of poker requires 5 cards to match.");

     // Get how many of each rank there are in the hand
     int[] ranks = new int[14];

     // Variables for checking hands
     int[] orderedRanks = new int[5];
     boolean flush=true, straight=false;
     int sameCards=1,sameCards2=1;
     int largeGroupRank=0,smallGroupRank=0;
     int index=0;
     int topStraightValue=0;

     // Gathering information about the hand in order to evaluate the hand
     for (int i = 0; i < 14; i++) {
         ranks[i]=0;
     }

     for (int i = 0; i < 5; i++) {
         ranks[currHand.get(i).getRank()]++;
     }

     for (int i = 0; i < 4; i++) {
         if (currHand.get(i).getSuit() != currHand.get(i+1).getSuit())
             flush=false;
     }

     for (int i = 13; i >= 1; i--) {
         if (ranks[i] > sameCards) {

             // If sameCards was not the default value
             if (sameCards != 1) {
                 sameCards2 = sameCards;
                 smallGroupRank = largeGroupRank;
             }

             sameCards = ranks[i];
             largeGroupRank = i == 1 ? 14 : i;

         } else if (ranks[i] > sameCards2) {
             sameCards2 = ranks[i];
             smallGroupRank = i == 1 ? 14 : i;
         }
     }

     if (ranks[1]==1) {
         orderedRanks[index]=14;
         index++;
     }

     for (int i = 13; i >= 2; i--) {
         if (ranks[i]==1) {
             orderedRanks[index]=i;
             index++;
         }
     }

     // Can't have straight if lowest value is more than 10
     for (int i=0; i<=9; i++) {
         if (ranks[i]==1 && ranks[i+1]==1 && ranks[i+2]==1 && ranks[i+3]==1 && ranks[i+4]==1) {
             straight=true;
             topStraightValue=i+4;
             break;
         }
     }

     if (ranks[10]==1 && ranks[11]==1 && ranks[12]==1 && ranks[13]==1 && ranks[1]==1) {
         straight=true;
         topStraightValue=14; //higher than king
     }

     // Actual hand evaluation
     if (straight && flush)
         return "Straight flush";

     if (sameCards == 4 )
         return "Four of a kind";

     if (sameCards == 3 && sameCards2 == 2 )
         return "Full house";

     if (flush && !straight)
         return "Flush";

     if (straight && !flush)
         return "Straight";

     if (sameCards == 3 && sameCards2 != 2)
         return "Three of a kind";

     if (sameCards == 2 && sameCards2 == 2) //two pair
         return "Two pair";

     if (sameCards == 2 && sameCards2 == 1)
         return "One pair";

     return "High card";
 }

}
