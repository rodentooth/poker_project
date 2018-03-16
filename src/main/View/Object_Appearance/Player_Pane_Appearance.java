package main.View.Object_Appearance;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.Model.Stack.Card;

import java.util.ArrayList;

public class Player_Pane_Appearance {

    public VBox Create_Plpa(ArrayList<Card> hand) {


        Card_Apperance card_creator = new Card_Apperance();

        Label player_number = new Label("Player xy" );
        player_number.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        player_number.setStyle("-fx-background-color: #f4d539");
        player_number.setAlignment(Pos.CENTER);
        player_number.setMaxSize(500, 20);



        HBox player_number_box = new HBox();
        player_number_box.getChildren().add(player_number);
        player_number_box.setStyle("-fx-background-color: #02f41e");
        player_number_box.setAlignment(Pos.CENTER);

        player_number_box.setMaxSize(500, 20);



        HBox PlayerPane = new HBox();

        //PlayerPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //PlayerPane.setMaxSize(500, 100);

        //PlayerPane.setMinSize(300, 80);

        PlayerPane.setPadding(new Insets(0, 12, 10, 12));




        for (Card c : hand) {

            Pane p = card_creator.Card_Apperance(c);

            p.setMinSize(80, 130);
            //p.setMaxSize(120, 260);
            PlayerPane.getChildren().add(p);
        }
        PlayerPane.setSpacing(20);



        Label evaluated_hand = new Label("what hand does the player have");

        HBox evaluated_hand_box = new HBox();
        evaluated_hand_box.getChildren().add(evaluated_hand);
        evaluated_hand_box.setStyle("-fx-background-color: #00fff2");
        evaluated_hand_box.setAlignment(Pos.CENTER);

        Label win_lose = new Label("You are a...");
        win_lose.setMaxSize(500, 20);


        HBox win_lose_box = new HBox(8);
        win_lose_box.getChildren().add(win_lose);
        win_lose_box.setStyle("-fx-background-color: #ff7700");
        win_lose_box.setAlignment(Pos.CENTER);
        win_lose_box.setMaxSize(500, 20);


        VBox PlayerPaneAll = new VBox(10);

        player_number_box.prefWidthProperty().bind(PlayerPaneAll.widthProperty());
        PlayerPane.prefWidthProperty().bind(PlayerPaneAll.widthProperty());
        evaluated_hand_box.prefWidthProperty().bind(PlayerPaneAll.widthProperty());
        win_lose_box.prefWidthProperty().bind(PlayerPaneAll.widthProperty());

        player_number_box.setMaxSize(600, 20);
        PlayerPane.setMaxSize(600, 200);

        evaluated_hand.setMaxSize(600, 20);
        evaluated_hand_box.setMaxSize(600, 20);
        win_lose_box.setMaxSize(600, 20);

        PlayerPaneAll.setPadding(new Insets(10, 10, 10, 10));
        PlayerPaneAll.setSpacing(20);
        PlayerPaneAll.getChildren().add(player_number_box);
        PlayerPaneAll.getChildren().add(PlayerPane);
        PlayerPaneAll.getChildren().add(evaluated_hand_box);
        PlayerPaneAll.getChildren().add(win_lose_box);
        PlayerPaneAll.setStyle("-fx-background-radius: 30;" + "-fx-border-radius: 30;");

        return (PlayerPaneAll);
    }
}
