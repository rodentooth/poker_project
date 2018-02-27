package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Ranking.Hand_Ranks;
import main.Ranking.Ranking;
import main.Stack.Card;
import main.Stack.Stack;

import java.util.ArrayList;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {
        //launch(args);

        //generate new stack of cards
        ArrayList<Card> stack = Stack.createStack();

        //first hand dealing out
        ArrayList<Card> hand1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hand1.add(stack.get(0));
            stack.remove(0);
        }

        //second hand dealing out
        ArrayList<Card> hand2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hand2.add(stack.get(0));
            stack.remove(0);
        }


        //Outprint the hands
        for (Card c : Ranking.sort_hand(hand1))
            System.out.println(c.toString());
        System.out.println("Hand 1 has: " + Hand_Ranks.values()[Math.abs(Ranking.rank_hand(hand1) - 10)]);

        System.out.println("\n");

        for (Card c : Ranking.sort_hand(hand2))
            System.out.println(c.toString());
        System.out.println("Hand 2 has: " + Hand_Ranks.values()[Math.abs(Ranking.rank_hand(hand2) - 10)]);


        System.out.println("\n");



        //Outprint the rank:
        System.out.println("Hand 1 has: " + Ranking.rank_hand(hand1));
        System.out.println("Hand 2 has: " + Ranking.rank_hand(hand2));


        int winner = Ranking.compare_hands(hand1, hand2);
        if (winner == 1)
            System.out.println("Hand 1 wins!");

        if (winner == 2)
            System.out.println("Hand 2 wins!");

        if (winner == 0)
            System.out.println("It's a tie!");

        if (winner == -1)
            System.out.println("mmh there's an error. but why?");


        System.exit(1);

    }
}
