package main.Model.Game;

import main.Model.Ranking.Hand_Ranks;
import main.Model.Ranking.Ranking;
import main.Model.Stack.Card;
import main.Model.Stack.Stack;

import java.util.ArrayList;

public class Poker_5_Stud {


    public Poker_5_Stud() {
        /**Game test with 2 hands. see other test below! */

        //generate new stack of cards
        ArrayList<Card> stack = Stack.createStack();

        //first hand dealing out
        ArrayList<Card> hand1 = new ArrayList<>();
        /*hand1.add(new Card(Suit.Hearts, Rank.Ace));
        hand1.add(new Card(Suit.Hearts, Rank.King));
        hand1.add(new Card(Suit.Hearts, Rank.Queen));
        hand1.add(new Card(Suit.Hearts, Rank.Jack));
        hand1.add(new Card(Suit.Hearts, Rank.Ten));
        */
        for (int i = 0; i < 5; i++) {
            hand1.add(stack.get(0));
            stack.remove(0);
        }


        //second hand dealing out
        ArrayList<Card> hand2 = new ArrayList<>();
        /*hand2.add(new Card(Suit.Hearts, Rank.Ace));
        hand2.add(new Card(Suit.Hearts, Rank.King));
        hand2.add(new Card(Suit.Hearts, Rank.Queen));
        hand2.add(new Card(Suit.Hearts, Rank.Jack));
        hand2.add(new Card(Suit.Hearts, Rank.Ten));
           */

        for (int i = 0; i < 5; i++) {
            hand2.add(stack.get(0));
            stack.remove(0);
        }

        /*

        //Outprint the hands
        for (Card c : Ranking.sort_hand(hand1))
            System.out.println(c.toString());

        System.out.println("\n");

        for (Card c : Ranking.sort_hand(hand2))
            System.out.println(c.toString());

        System.out.println("\n");


        //Outprint the rank:
        System.out.println("Hand 1 has: " + (String.valueOf(Hand_Ranks.values()[Math.abs(Ranking.rank_hand(hand1) - 10)])).replace("_", " "));
        System.out.println("Hand 2 has: " + String.valueOf(Hand_Ranks.values()[Math.abs(Ranking.rank_hand(hand2) - 10)]).replace("_", " "));


        System.out.println("\n");


        int winner = Ranking.compare_hands(hand1, hand2);
        if (winner == 1)
            System.out.println("Hand 1 wins!");

        if (winner == 2)
            System.out.println("Hand 2 wins!");

        if (winner == 0)
            System.out.println("It's a tie!");

        if (winner == -1)
            System.out.println("mmh there's an error. but why?");
*/


        //////////////////////////////
        //TEST WITH MULTIPLE PLAYERS//
        //////////////////////////////
        int players = 10;//////////////
        //MAX 10 PLAYERS!!////////////
        //////////////////////////////


        ArrayList<Card> test_stack = Stack.createStack();


        ArrayList<ArrayList<Card>> Player_Cards = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            Player_Cards.add(new ArrayList<>());
            for (int j = 0; j < 5; j++) {
                Player_Cards.get(i).add(test_stack.get(0));
                test_stack.remove(0);
            }
        }

        Ranking r = new Ranking();

        System.out.println("There are " + players + " Players in this game. Their Cards are:\n");

        for (ArrayList<Card> A : Player_Cards) {
            System.out.println("Cards of player NO. " + (Player_Cards.indexOf(A) + 1) + " :\n");
            for (Card C : A) {
                System.out.println(C.toString());
            }
            System.out.println("Player  NO. " + (Player_Cards.indexOf(A) + 1) + " has: " + (String.valueOf(Hand_Ranks.values()[Math.abs(r.rank_hand(A) - 10)])).replace("_", " "));

            System.out.println();
        }

        int W = 0;
        for (int i = 1; i < Player_Cards.size(); i++) {
            System.out.println("\nPlayer " + (W + 1) + " Plays against " + (1 + i) + "");

            //TODO -> If we pass the Hand array lists directly, they get edited directly.


            int winner = r.compare_hands(Player_Cards.get(W), Player_Cards.get(i));
            if (winner == 1)
                System.out.println("Player " + (W + 1) + " wins over Player " + (1 + i));
            if (winner == 2) {
                System.out.println("Player " + (1 + i) + " wins over player " + (1 + W));
                W = i;


            }
            if (winner == 0) {
                System.out.println("Tie!");
                int W2 = 0;
                for (ArrayList<Card> B : Player_Cards) {
                    int winner2 = r.compare_hands(Player_Cards.get(W2), B);
                    if (winner2 == 1)
                        System.out.println("Hand 1 wins!");
                    if (winner2 == 2)
                        W = Player_Cards.indexOf(B);

                }
                if (W2 == W) {
                    System.out.println("Player " + Player_Cards.indexOf(Player_Cards.get(i)) + " and Player " + Player_Cards.indexOf(Player_Cards.get(W)) + " are winner!!");
                }
            }
        }
        System.out.println("Player " + (W + 1) + " wins with " + (String.valueOf(Hand_Ranks.values()[Math.abs(r.rank_hand(Player_Cards.get(W)) - 10)])).replace("_", " "));


        //TEST ENDE
        /////////////////

    }

}
