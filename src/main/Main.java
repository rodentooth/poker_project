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
        stack.add(new Card(Suit.Clubs, Rank.Two));
        stack.add(new Card(Suit.Clubs, Rank.Ace));
        stack.add(new Card(Suit.Hearts, Rank.Queen));
        stack.add(new Card(Suit.Hearts, Rank.King));
        stack.add(new Card(Suit.Spades, Rank.Nine));


        //Outprint the unsorted stack
        for(Card c : (stack))
            System.out.println(c.toString());

        System.out.println("Sorting......");

        //Outprint the stack
        for(Card c : Ranking.sort_hand(stack))
            System.out.println(c.toString());



    }
}
