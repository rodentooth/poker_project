package main.Model.Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Stack {

    //creates new stack of cards
    public static ArrayList<Card> createStack(){

        //initiate new ArrayList
        ArrayList<Card> stack =new ArrayList<>();

        //loops to create a new card for every Rank and Suit:
        for(int rank = 0; rank < Rank.values().length; rank++){
            for(int suit = 0; suit < Suit.values().length; suit++){

                //adding a card:
                stack.add(
                        new Card(
                                Suit.values()[suit],Rank.values()[rank]
                        )
                );
            }
        }


        //randomize stack with a seed of the current time
        //TODO long seed = System.nanoTime();
        long seed = 3473;
        Collections.shuffle(stack,new Random(seed));

        //return the shuffled stack, ready to use.
        return stack;
    }
}
