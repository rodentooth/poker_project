package main.Model.Game;

import main.Model.Stack.Card;
import main.Model.Stack.Stack;

import java.util.ArrayList;

import static main.Model.Game.Online_Poker_5_Stud.getWinnerAsInt;

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

        return getWinnerAsInt(Cards);

    }


}
