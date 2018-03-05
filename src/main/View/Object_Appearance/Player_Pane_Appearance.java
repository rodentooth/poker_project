package main.View.Object_Appearance;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Controller.poker_game_1_controller;
import main.Model.Stack.Card;

import java.util.ArrayList;

public class Player_Pane_Appearance {

    public VBox Create_Plpa(ArrayList<Card> hand) {


        Card_Apperance card_creator = new Card_Apperance();


        Label player_number = new Label("Player xy");

        HBox player_number_box = new HBox();
        player_number_box.getChildren().add(player_number);
        player_number_box.setStyle("-fx-background-color: #02f41e");
        player_number_box.setAlignment(Pos.CENTER);



        HBox PlayerPane = new HBox();

        PlayerPane.setPrefSize(500, 150);

        PlayerPane.setPadding(new Insets(15, 12, 15, 12));

        PlayerPane.setSpacing(10);



        for (Card c : hand) {

            PlayerPane.getChildren().add(card_creator.Card_Apperance(c));
        }


        Label evaluated_hand = new Label("what hand does the player have");

        HBox evaluated_hand_box = new HBox();
        evaluated_hand_box.getChildren().add(evaluated_hand);
        evaluated_hand_box.setStyle("-fx-background-color: #00fff2");
        evaluated_hand_box.setAlignment(Pos.CENTER);

        Label win_lose = new Label("You win / you lose");

        HBox win_lose_box = new HBox();
        win_lose_box.getChildren().add(win_lose);
        win_lose_box.setStyle("-fx-background-color: #00fff2");
        win_lose_box.setAlignment(Pos.CENTER);


        VBox PlayerPaneAll = new VBox();
        PlayerPaneAll.getChildren().add(player_number_box);
        PlayerPaneAll.getChildren().add(PlayerPane);
        PlayerPaneAll.getChildren().add(evaluated_hand_box);
        PlayerPaneAll.getChildren().add(win_lose_box);


        return (PlayerPaneAll);
    }
}
