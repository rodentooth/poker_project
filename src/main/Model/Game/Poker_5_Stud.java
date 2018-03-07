package main.Model.Game;

import main.Model.Ranking.Hand_Ranks;
import main.Model.Ranking.Ranking;
import main.Model.Stack.Card;
import main.Model.Stack.Stack;

import java.util.ArrayList;

public class Poker_5_Stud {


    int playerNumb = 0;

    public Poker_5_Stud() {

    }

    public ArrayList<ArrayList<Card>> getHands(int players) {


        ArrayList<Card> test_stack = Stack.createStack();


        ArrayList<ArrayList<Card>> Player_Cards = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            Player_Cards.add(new ArrayList<>());
            for (int j = 0; j < 5; j++) {
                Player_Cards.get(i).add(test_stack.get(0));
                test_stack.remove(0);
            }
        }

        return Player_Cards;
    }

    public int getWinner(ArrayList<ArrayList<Card>> Cards) {

        int W = 0;
        Ranking r = new Ranking();

        for (int i = 1; i < Cards.size(); i++) {
            System.out.println("\nPlayer " + (W + 1) + " Plays against " + (1 + i) + "");


            int winner = r.compare_hands(Cards.get(W), Cards.get(i));
            if (winner == 1)
                System.out.println("Player " + (W + 1) + " wins over Player " + (1 + i));
            if (winner == 2) {
                System.out.println("Player " + (1 + i) + " wins over player " + (1 + W));
                W = i;


            }
            if (winner == 0) {
                System.out.println("Tie!");
                int W2 = 0;
                for (ArrayList<Card> B : Cards) {
                    int winner2 = r.compare_hands(Cards.get(W2), B);
                    if (winner2 == 1)
                        System.out.println("Hand 1 wins!");
                    if (winner2 == 2)
                        W = Cards.indexOf(B);

                }
                if (W2 == W) {
                    System.out.println("Player " + Cards.indexOf(Cards.get(i)) + " and Player " + Cards.indexOf(Cards.get(W)) + " are winner!!");
                }
            }
        }
        System.out.println("Player " + (W + 1) + " wins with " + (String.valueOf(Hand_Ranks.values()[Math.abs(r.rank_hand(Cards.get(W)) - 10)])).replace("_", " "));


        return (W + 1);

    }


}
