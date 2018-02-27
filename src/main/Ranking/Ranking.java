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

    public static int compare_hands(ArrayList<Card> hand1,ArrayList<Card> hand2){

        if (rank_hand(hand1)==-1){
            System.out.println("Hand 1 conatins more than 5 cards. But why?");
            return -1;
        }
        if (rank_hand(hand2)==-1){
            System.out.println("Hand 2 conatins more than 5 cards. But why?");
            return -1;
        }


        if (rank_hand(hand1)>rank_hand(hand2))
            return 1;

        else if (rank_hand(hand1)<rank_hand(hand2))
            return 2;

        else{
            //count together the values of the hands. The smaller value wins. (in the enums, Ace is first, so ace equals 0)
            System.out.println("hands are a tie. counting together the values");

            int valueof_hand1 = 0;
            int valueof_hand2 = 0;

            for(Card c :hand1){
                valueof_hand1 += c.getRank().ordinal();
            }
            for(Card c :hand2){
                valueof_hand2 += c.getRank().ordinal();
            }
            if(valueof_hand1<valueof_hand2){
                return 1;
            }
            else if(valueof_hand1>valueof_hand2){
                return 2;
            }
            else{
                return 0;
            }
        }
    }



        public static int rank_hand(ArrayList<Card> hand){

        if(hand.size()!=5)
        return -1;

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

        //Storage for counters
        int same_kind_counter_1=0;
        int same_kind_counter_2=0;
        int same_kind_counter_3=0;
        int same_kind_counter_4=0;



        //This booleans get triggered to count multiple pairs (Two pair, three of a kind or full house)
        Boolean countertrigger=false;
        //this is only important for three of a kind and two pair.
        Boolean countertrigger2=false;
        Boolean countertrigger3=false;


            //check if the first card is an ace, if it could be a royal flush:
        if(hand.get(0).getRank().ordinal()!=0)
            Royal_Flush =false;


        /*loop through the hand to see:
            if it could be a:
           - Royal Flush
           - Straight flush
           - Straight
           - Four of a Kind
           and to determine pairs.
         */
        for(int i=0;i<hand.size()-1;i++){

            //If it's not five card in sequence, it can't be the following:
            if(hand.get(i).getRank().ordinal()!=hand.get(i+1).getRank().ordinal()-1){
                Royal_Flush =false;
                Straight_Flush =false;
                Straight =false;
            }

            //if it's not the same suit all along, it cant be a straight flush and flush and royal flush
            if((hand.get(i).getSuit().ordinal()!=hand.get(i+1).getSuit().ordinal())) {
                Royal_Flush =false;
                Straight_Flush = false;
                Flush = false;
            }


            /*counting Pairs. It goes up to 4 counter, because: same_kind_counter_4 is for a pair that is at the end of our hand. practical example:

            Spades Ace
            Spades Nine
            Spades Eight
            Diamonds Four
            Hearts Four

            ▼▼▼▼▼▼▼▼
            Diamonds Four
            Hearts Four
            ▲▲▲▲▲▲▲▲
            This is a Pair at the end of the hand which can't be reached by counter 1,2 and 3 because everytime a card next to the compared card is not the same as the compared card, the current counter stops to count for pairs.
             */
            if(hand.get(i).getRank()==hand.get(i+1).getRank()) {
                if(!countertrigger) {
                    same_kind_counter_1++;
                }else {
                    if (!countertrigger2) {
                        same_kind_counter_2++;
                    }else {

                        if (!countertrigger3) {
                            same_kind_counter_3++;
                        } else {
                            same_kind_counter_4++;
                        }

                    }
                }
            }else{
                if(!countertrigger)
                    countertrigger=true;
                else
                    if(!countertrigger2)
                        countertrigger2=true;
                    else
                        countertrigger3=true;
            }
            //end counting Pairs.
        }

            //end loop through the hand


        //check counters to see if there are pairs

        if(same_kind_counter_1<3 && same_kind_counter_2<3) {
            Four_of_a_kind = false;
        }else{
            //It's four of a kind and nothing less
            Three_of_a_kind=false;
            Pair = false;
            Two_pair=false;
            Full_house=false;

        }

            //check if there are less than 3 similiar cards
        if(same_kind_counter_1<2 && same_kind_counter_2<2 && same_kind_counter_3<2){
            Three_of_a_kind=false;
            Full_house=false;
        }else{
            //It's three of a kind and nothing less
            Pair = false;
            Two_pair=false;

            //check if it's a full house
            if(same_kind_counter_1==1 || same_kind_counter_2==1 || same_kind_counter_3==1 || same_kind_counter_4==1) {
                Three_of_a_kind = false;
            }else{
                Full_house=false;
            }
            }

        //no pairs:
        if(same_kind_counter_1<1 && same_kind_counter_2<1 && same_kind_counter_3<1 && same_kind_counter_4<1) {
            Pair = false;
            Two_pair=false;
        }else{
            //two of a pair check
            if (
                    (same_kind_counter_1 == 1 && same_kind_counter_2 == 1) ||
                            (same_kind_counter_1 == 1 && same_kind_counter_3 == 1) ||
                            (same_kind_counter_1 == 1 && same_kind_counter_4 == 1) ||
                            (same_kind_counter_2 == 1 && same_kind_counter_3 == 1) ||
                            (same_kind_counter_2 == 1 && same_kind_counter_4 == 1) ||
                            (same_kind_counter_3 == 1 && same_kind_counter_4 == 1)) {
                //it's two pair and nothing less
                Pair = false;

            }else {
                //else it's a pair and nothing else
                Two_pair=false;
                }
        }

        //Full house evaluation
            if ((same_kind_counter_1 == 2 && same_kind_counter_2 == 1) || same_kind_counter_1 == 1 && same_kind_counter_2 == 2) {
            Two_pair=false;
            Three_of_a_kind=false;
            Pair = false;
        }

            //end check counters

        //check what to return:

        if(Royal_Flush)
            return 10;
        if(Straight_Flush)
            return 9;
        if(Four_of_a_kind)
            return 8;
        if(Full_house)
            return 7;
        if(Flush)
            return 6;
        if(Straight)
            return 5;
        if(Three_of_a_kind)
            return 4;
        if(Two_pair)
            return 3;
        if(Pair)
            return 2;


        return 1;
        }

    private static ArrayList<Card> sort_hand(ArrayList<Card> hand) {

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
