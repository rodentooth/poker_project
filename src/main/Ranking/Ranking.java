package main.Ranking;

import main.Stack.Card;

import java.util.ArrayList;

public class Ranking {

    /*Method to determine the value of a hand.

    Evaluation:
   -1  = Not valid. Either wrong amount of cards in hand or something else.
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
            /*
            evaluate ties:

            if there are pairs, it evaluates the ranking of the pair. After that:

            if there are kickers in the hands, the highest, unique kicker counts ( if both have the same ACE as a kicker, the program determines the next smaller kicker until it has found an unique identifier ) if it can't find one, it's a tie,
            if there are no kickers in the hand, the highest card counts. if it's the same card, it's a tie.
            if it's a full house, the highest tripple pair counts. if the tripple pair is the same, it's a tie. <-todo not yet implemented
             */
            System.out.println("Hands are a tie.\n");

            int valueof_hand1 = 0;
            int valueof_hand2 = 0;

            //get the enum of the ranks (Hand_ranks are top down, rank_hand gives out Bottom-up, so we need to change that:)
            Hand_Ranks rank_hand1 = Hand_Ranks.values()[Math.abs(Ranking.rank_hand(hand1) - 10)];
            Hand_Ranks rank_hand2 = Hand_Ranks.values()[Math.abs(Ranking.rank_hand(hand2) - 10)];

            //check pairs if there are any, compare them and and delete them afterwards, so they won't be compared multiple times.
            while (has_kicker(rank_hand1) && valueof_hand1 == valueof_hand2 && get_highest_pair(hand1, rank_hand1) != null) {
                System.out.println("Evaluate highest pair...\n");

                //check again if it's null because stupid ide
                Card highestpair1 = get_highest_pair(hand1, rank_hand1);
                if (highestpair1 != null) {
                    valueof_hand1 = highestpair1.getRank().ordinal();
                    System.out.println("highest pair of hand 1 is: " + highestpair1.getRank());
                }

                Card highestpair2 = get_highest_pair(hand2, rank_hand2);
                if (highestpair2 != null) {
                    valueof_hand2 = highestpair2.getRank().ordinal();
                    System.out.println("highest pair of hand 2 is: " + highestpair2.getRank() + "\n");
                }

                if (valueof_hand1 == valueof_hand2) {
                    System.out.println("Both Pairs are of the same height. Evaluate next smaller Pair\n");
                    //check if there are any pairs left
                    if (get_highest_pair(hand1, rank_hand1) != null) {

                        //there are pairs to delete. because the elements are shifted every time element gets removed, we start by deleting the fourth card and move downwards.
                        Card first_card_in_pair_hand1 = get_highest_pair(hand1, rank_hand1);

                        if (rank_hand1 == Hand_Ranks.Four_of_a_kind)
                            hand1.remove(hand1.indexOf(first_card_in_pair_hand1) + 3);
                        if (rank_hand1 == Hand_Ranks.Three_of_a_kind)
                            hand1.remove(hand1.indexOf(first_card_in_pair_hand1) + 2);
                        hand1.remove(hand1.indexOf(first_card_in_pair_hand1) + 1);
                        hand1.remove(hand1.indexOf(first_card_in_pair_hand1));


                        //same for hand 2
                        Card first_card_in_pair_hand2 = get_highest_pair(hand2, rank_hand2);

                        if (rank_hand2 == Hand_Ranks.Four_of_a_kind)
                            hand2.remove(hand2.indexOf(first_card_in_pair_hand2) + 3);
                        if (rank_hand2 == Hand_Ranks.Three_of_a_kind)
                            hand2.remove(hand2.indexOf(first_card_in_pair_hand2) + 2);
                        hand2.remove(hand2.indexOf(first_card_in_pair_hand2) + 1);
                        hand2.remove(hand2.indexOf(first_card_in_pair_hand2));
                    }

                }

            }


            while (valueof_hand1 == valueof_hand2 && hand1.size() > 0) {

                if (has_kicker(rank_hand1)) {
                    System.out.println("No pairs left. Evaluating Kickers...\n");

                    //remove the kickers if there are any, and evaluate again.
                    valueof_hand1 = get_highest_card(hand1, rank_hand1).getRank().ordinal();
                    System.out.println("highest Kicker of hand 1 is: " + get_highest_card(hand1, rank_hand1).getRank());

                    valueof_hand2 = get_highest_card(hand2, rank_hand2).getRank().ordinal();
                    System.out.println("highest Kicker of hand 2 is: " + get_highest_card(hand2, rank_hand2).getRank() + "\n");

                    if (valueof_hand1 == valueof_hand2) {
                        System.out.println("Both kicker are of the same height. Evaluate next smaller Kicker\n");

                        //remove the kickers if there are any, and evaluate again.
                        hand1.remove(get_highest_card(hand1, rank_hand1));
                        hand2.remove(get_highest_card(hand2, rank_hand2));

                    } else {
                        Boolean winner = valueof_hand1 < valueof_hand2;
                        System.out.println(winner ? get_highest_card(hand1, rank_hand1).getRank() + " beats " + get_highest_card(hand2, rank_hand2).getRank() : get_highest_card(hand2, rank_hand2).getRank() + " beats " + get_highest_card(hand1, rank_hand1).getRank());

                    }
                } else {
                    //It's not a hand with a kicker in it. so we evaluate the highest card:
                    System.out.println("Evaluate the higher Rank...\n");

                    valueof_hand1 = get_highest_card(hand1, rank_hand1).getRank().ordinal();
                    System.out.println("highest Card of hand 1 is: " + get_highest_card(hand1, rank_hand1).getRank());

                    valueof_hand2 = get_highest_card(hand2, rank_hand2).getRank().ordinal();
                    System.out.println("highest Card of hand 2 is: " + get_highest_card(hand2, rank_hand2).getRank() + "\n");

                    if (valueof_hand1 != valueof_hand2) {

                        Boolean winner = valueof_hand1 < valueof_hand2;
                        System.out.println(winner ? get_highest_card(hand1, rank_hand1).getRank() + " beats " + get_highest_card(hand2, rank_hand2).getRank() : get_highest_card(hand2, rank_hand2).getRank() + " beats " + get_highest_card(hand1, rank_hand1).getRank());

                    } else {
                        //tie
                        break;
                    }
                }
            }

            //todo: insert code for full house evaluation here

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

        //Storage for counters
        int same_kind_counter_1=0;
        int same_kind_counter_2=0;
        int same_kind_counter_3=0;
        int same_kind_counter_4=0;


            //This booleans get triggered to count multiple pairs (Two pair, three of a kind or full house) or pairs at the end of the hand.
            // see comments below for more info
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
            This is a Pair at the end of the hand which can't be reached by counter 1,2 and 3 because every time a card next to the compared card is not the same as the compared card, the current counter stops to count for pairs.
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


    public static ArrayList<Card> sort_hand(ArrayList<Card> hand) {

        //sort algorithm used from my last semester code.
        //////////////////////
        //insertsort
        /////////////////////

        // loop through all numbers in the list
        for (int i = 0; i < hand.size(); i++) {
            //current index to a changeable index which counts down while comparing
            int j = i;
            //as long as the changeable index is above 0 and the number in the list at the index is lower than its neighbour below do the following:
            while (j > 0 && hand.get(j).getRank().ordinal() < hand.get(j - 1).getRank().ordinal()) {

                //save the number in the list at the index
                Card x = hand.get(j);

                //swap numbers
                hand.set(j, hand.get(j - 1));
                hand.set(j - 1, x);

                //decrease the changeable index
                j--;
            }
        }

        return hand;

    }


    private static Card get_highest_pair(ArrayList<Card> hand, Hand_Ranks rank) {

        //evaluate the highest Pair in case of a tie:

        //sort hand
        sort_hand(hand);

        /*
        loop to search highest pair for:
        - Four of a kind
        - Three of a kind
        - two pair
        - Pair
        - High hand
         */
        if (has_kicker(rank)) {

            for (int i = 0; i < hand.size() - 1; i++) {

                //If the next card is equal to the current card, the current card belongs to a pair
                if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
                    return hand.get(i);
                }
            }
            //no pairs found
            return null;
        }
        return null;
    }

    private static boolean has_kicker(Hand_Ranks rank) {

        /*here we evaluate if the hand is of the ranks:

        - Four of a kind
        - Three of a kind
        - two pair
        - Pair
        - High hand

        Which are all containing one or more kicker cards

        or if they are other cards.

        return true = has kicker in it
         */

        //get the enum rank of the card

        return rank == Hand_Ranks.Four_of_a_kind ||
                rank == Hand_Ranks.Three_of_a_kind ||
                rank == Hand_Ranks.Two_pair ||
                rank == Hand_Ranks.Pair ||
                rank == Hand_Ranks.High_card;
    }


    private static Card get_highest_card(ArrayList<Card> hand, Hand_Ranks rank) {


        //Todo: full house is evaluated by comparing its triple pair (just return the rank of tripple pair)
        //evaluate the highest card in case of a tie:

        //sort hand
        sort_hand(hand);

        /*
        loop to search kicker for:
        - Four of a kind
        - Three of a kind
        - two pair
        - Pair
        - High hand
         */
        if (has_kicker(rank)) {

            for (int i = 0; i < hand.size() - 1; i++) {

                //If the next card and the card before is not equal to the current card, the current card is a kicker
                if (i == 0 && hand.get(i).getRank() != hand.get(i + 1).getRank()) {
                    return hand.get(i);
                } else if (i != 0) {
                    if (hand.get(i).getRank() != hand.get(i + 1).getRank() && hand.get(i).getRank() != hand.get(i - 1).getRank()) {
                        return hand.get(i);
                    }
                }
            }
            if (hand.size() == 1) {
                return hand.get(0);
            }
        }/*
         kicker for:
        - Royal Flush
        - Straight Flush
        - Full House <----- todo: IS EVALUATED DIFFERENTLY!
        - Flush
        - Straight

        It's just the highest card. hand is sorted desc. so 0 is highest.
         */
        if (hand.size() > 0) {
            return hand.get(0);
        }
        return null;


    }

}
