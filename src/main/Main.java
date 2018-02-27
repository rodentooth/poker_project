package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Ranking.Ranking;
import main.Stack.*;

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
        //ArrayList<Card> stack = Stack.createStack();

        ArrayList<Card> stack = new ArrayList<>();
        stack.add(new Card(Suit.Hearts, Rank.Nine));
        stack.add(new Card(Suit.Clubs, Rank.Nine));
        stack.add(new Card(Suit.Diamonds, Rank.Nine));
        stack.add(new Card(Suit.Clubs, Rank.Ten));
        stack.add(new Card(Suit.Clubs, Rank.Jack));

        ArrayList<Card> stack2 = new ArrayList<>();
        stack2.add(new Card(Suit.Hearts, Rank.Ten));
        stack2.add(new Card(Suit.Clubs, Rank.Ten));
        stack2.add(new Card(Suit.Diamonds, Rank.Ten));
        stack2.add(new Card(Suit.Clubs, Rank.Nine));
        stack2.add(new Card(Suit.Clubs, Rank.Jack));




        //Outprint the stack


        //Outprint the rank:
        System.out.println("Hand 1 has: "+Ranking.rank_hand(stack));
        System.out.println("Hand 2 has: "+Ranking.rank_hand(stack2));

        System.out.println(Ranking.compare_hands(stack,stack2));




    }
}
