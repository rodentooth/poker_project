package main.Ranking;

import main.Stack.Card;

import java.util.ArrayList;

public class Ranking {

    /*Method to determine the value of a hand.

    Evaluation:
    0  = Not valid. Either wrong amount of cards in hand or something else.
    1  = Lowest rank
    10 = Highest rank

     */
    public int rank_hand(ArrayList<Card> hand){

        if(hand.size()!=5)
        return 0;

        //sort the hand
        sort_hand(hand);



        Boolean Royal_Flush = true;
        Boolean Straight_Flush = true;
        Boolean Four_of_a_kind = true;
        Boolean Full_house = true;
        Boolean Flush = true;
        Boolean Straight = true;
        Boolean Three_of_a_kind = true;
        Boolean Two_pair = true;
        Boolean Pair = true;
        Boolean High_card = true;


        //check if it could be a royal flush:
        if(hand.get(0).getRank().ordinal()!=0)
            Royal_Flush =false;


        /*loop through the hand to see if it could be a:
           - Royal Flush
           - Straight flush
           - Straight
         */
        for(int i=0;i<hand.size();i++){

            //If it's not five card in sequence, it can't be the following:
            if(hand.get(i).getRank().ordinal()!=hand.get(i+1).getRank().ordinal()){
                Royal_Flush =false;
                Straight_Flush =false;
                Straight =false;
            }

            //if it's not the same suit all along, it cant be a straight flush
            if((hand.get(i).getSuit().ordinal()!=hand.get(i+1).getSuit().ordinal()))
                Straight_Flush =false;


        }

        //todo the other hand rankings



        return 0;
    }

    public static ArrayList<Card> sort_hand(ArrayList<Card> hand){

        //sort algorithm used from my last semester code.
        //////////////////////
        //insertsort
        /////////////////////

            // loop through all numbers in the list
            for(int i=0;i<hand.size();i++){
                //current index to a changeable index
                int j=i;
                //as long as the changeable index is above 0 and the number in the list at the index is lower than its neighbour below do the following:
                while(j>0 && hand.get(j).getRank().ordinal()    <   hand.get(j-1).getRank().ordinal()){

                    //save the number in the list at the index
                    Card x =hand.get(j);

                    //swap numbers
                    hand.set(j,hand.get(j-1));
                    hand.set(j-1,x);

                    //decrease the changeable index
                    j--;
                }
            }

            return hand;



    }



}
