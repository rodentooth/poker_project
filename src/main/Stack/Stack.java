package main.Stack;

import java.util.ArrayList;
import java.util.Collection;
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

                //defining the color of the cards. If spades or clubs, color is black, else it's Red
                Color c = Color.Black;
                if(suit==1 || suit==3)
                    c = Color.Red;

                //adding a card:
                stack.add(
                        new Card(
                                c,Suit.values()[suit],Rank.values()[rank]
                        )
                );
            }
        }


        //randomize stack with a seed of the current time
        long seed = System.nanoTime();
        Collections.shuffle(stack,new Random(seed));

        //return the shuffled stack, ready to use.
        return stack;
    }
}
