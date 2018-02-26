package main.Stack;

import java.util.ArrayList;

public class Stack {

    //creates new stack of cards
    ArrayList<Card> createStack(){

        //initiate new ArrayList
        ArrayList<Card> stack =new ArrayList<>();


        //loops to create a new card for every Color, Rank and Suit:
        for(int color = 0; color <= Color.values().length; color++){
            for(int rank = 0; rank <= Rank.values().length; rank++){
                for(int suit = 0; suit <= Suit.values().length; suit++){

                    //adding a card:
                    stack.add(
                            new Card(
                                    Color.values()[color],Suit.values()[suit],Rank.values()[rank]
                            )
                    );

                }
            }
        }


        //todo shuffle the cards

        return stack;
    }
}
