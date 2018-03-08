package main.View.Object_Appearance;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Model.Stack.Card;

public class Card_Apperance {

    public Pane Card_Apperance(Card c) {

        Pane p = new Pane();

        VBox inside_card = new VBox();

        //inside_card.setPadding(new Insets(15, 12, 15, 12));

        Text Rank = new Text(c.getRank().toString());
        Text Suit = new Text(c.getSuit().toString());

        inside_card.getChildren().addAll(Suit, Rank);
        inside_card.setPrefSize(100, 200);

        inside_card.setStyle("-fx-background-color: #FFFFFF");

        p.getChildren().add(inside_card);

        return inside_card;
    }

    public Pane Empty_Card_Apperance(Card c) {

        VBox inside_card = new VBox();
        inside_card.setPrefSize(100, 200);

        //pic ine
        return inside_card;

    }
}
