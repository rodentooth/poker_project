package main.View.Object_Appearance;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import main.Model.Stack.Card;

import java.util.ArrayList;

public class Player_Pane_Appearance {

    public HBox Create_Plpa(ArrayList<Card> hand) {


        Card_Apperance card_creator = new Card_Apperance();

        HBox PlayerPane = new HBox();
        PlayerPane.setPrefSize(500, 200);

        PlayerPane.setPadding(new Insets(15, 12, 15, 12));

        PlayerPane.setSpacing(10);

        for (Card c : hand) {

            PlayerPane.getChildren().add(card_creator.Card_Apperance(c));
        }
        return (PlayerPane);
    }
}
