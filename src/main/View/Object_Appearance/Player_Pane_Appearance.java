package main.View.Object_Appearance;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import main.Model.Stack.Card;

import java.util.ArrayList;

public class Player_Pane_Appearance {

    public VBox Create_Plpa(ArrayList<Card> hand, int b) {


        Card_Apperance card_creator = new Card_Apperance();

        Label player_number = new Label("Player xy" );
        player_number.setTextFill(Color.WHITE);

        player_number.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        player_number.setAlignment(Pos.CENTER);
        player_number.setMaxSize(500, 20);
        player_number.setFont(Font.font("tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 30));



        HBox player_number_box = new HBox();
        player_number_box.getChildren().add(player_number);
        player_number_box.setStyle("-fx-background-color: #000000");
        player_number_box.setAlignment(Pos.CENTER);

        player_number_box.setMaxSize(500, 20);



        HBox PlayerPane = new HBox();
        //PlayerPane.setStyle("-fx-background-color: #f0f550");




        PlayerPane.setPadding(new Insets(0, 12, 10, 12));


        for (Card c : hand) {
            Pane p = card_creator.Card_Apperance(c, b);
            //p.setMinSize(30, 60);
            //p.setMaxSize(100, 200);
            PlayerPane.getChildren().add(p);
        }
        PlayerPane.setSpacing(20);

        PlayerPane.setMaxSize(500, 200);
        PlayerPane.setPrefSize(500, 200);

        PlayerPane.setMinSize(300, 60);
        //PlayerPane.prefHeightProperty().bind(((Pane)PlayerPane.getChildren().get(1)).heightProperty());

        Label evaluated_hand = new Label("what hand does the player have");

        evaluated_hand.setTextFill(Color.WHITE);

        HBox evaluated_hand_box = new HBox();
        evaluated_hand_box.getChildren().add(evaluated_hand);
        evaluated_hand_box.setStyle("-fx-background-color: #000000");
        evaluated_hand_box.setAlignment(Pos.CENTER);

        Label win_lose = new Label();
        win_lose.setFont(Font.font("tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        win_lose.setTextFill(Color.WHITE);


        win_lose.setMaxSize(500, 20);


        HBox win_lose_box = new HBox(8);
        win_lose_box.setStyle("-fx-background-color: #000000");

        win_lose_box.getChildren().add(win_lose);
        //win_lose_box.setStyle("-fx-background-color: #ff7700");
        win_lose_box.setAlignment(Pos.CENTER);
        win_lose_box.setMaxSize(500, 20);
        win_lose_box.setPadding(new Insets(0, 0, 0, 0));


        VBox PlayerPaneAll = new VBox(10);

        player_number_box.prefWidthProperty().bind(PlayerPaneAll.widthProperty());
        PlayerPane.prefWidthProperty().bind(PlayerPaneAll.widthProperty());
        evaluated_hand_box.prefWidthProperty().bind(PlayerPaneAll.widthProperty());
        win_lose_box.prefWidthProperty().bind(PlayerPaneAll.widthProperty());

        PlayerPaneAll.prefHeightProperty().bind(PlayerPane.heightProperty());

        PlayerPaneAll.setMaxSize(600, 250);


        player_number_box.setMaxSize(650, 20);
        PlayerPane.setMaxSize(600, 200);

        evaluated_hand.setMaxSize(600, 20);
        evaluated_hand_box.setMaxSize(650, 20);
        win_lose_box.setMaxSize(650, 20);

        PlayerPaneAll.setPadding(new Insets(0, 0, 0, 0));
        //PlayerPaneAll.setSpacing(20);
        PlayerPaneAll.getChildren().add(player_number_box);
        PlayerPaneAll.getChildren().add(PlayerPane);
        PlayerPaneAll.getChildren().add(evaluated_hand_box);
        PlayerPaneAll.getChildren().add(win_lose_box);
        PlayerPaneAll.setStyle("-fx-background-radius: 30;" + "-fx-border-radius: 30;");
        PlayerPaneAll.setStyle("-fx-background-color: #00f790");


        return (PlayerPaneAll);
    }
}
