package main.View.Object_Appearance;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import main.Model.Stack.Card;

public class Card_Apperance {

    public Pane Card_Apperance(Card c) {


        HBox container = new HBox();
        Pane inside_card2 = new Pane();
        //inside_card2.setAlignment(Pos.CENTER);


        //inside_card.setPadding(new Insets(15, 12, 15, 12));

        ImageView imageView2 = new ImageView();
        //imageView2.setFitWidth(50);
        imageView2.setPreserveRatio(true);
        imageView2.setSmooth(true);
        imageView2.setCache(true);
        imageView2.fitWidthProperty().bind(inside_card2.widthProperty());


        System.out.println("main/res/Card_Images/" + c.getSuit().toString() + "/" + c.getSuit().toString() + "_" + c.getRank().toString() + ".png");

        Image shortImageUrl = new Image("main/res/Card_Images/" + c.getSuit().toString() + "/" + c.getSuit().toString() + "_" + c.getRank().toString() + ".png");


        imageView2.setImage(shortImageUrl);

        imageView2.getTransforms().add(new Rotate(90, Rotate.Y_AXIS));


        ImageView imageView = new ImageView(new Image("main/res/Card_Images/cardBack.png"));
        //imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.fitWidthProperty().bind(inside_card2.widthProperty());


        imageView2.getTransforms().add(new Translate(-50, 0, 0));
        //imageView.getTransforms().add(new Translate(50,0,0));


        inside_card2.getChildren().add(imageView2);
        inside_card2.getChildren().add(imageView);

        inside_card2.setPrefSize(100, 200);
        inside_card2.setMaxSize(100, 200);

        //inside_card2.setStyle("-fx-background-color: #FFFFFF");
        container.setMaxSize(100, 200);
        container.getChildren().add(inside_card2);


        return container;
    }

    /*
    public Pane Empty_Card_Apperance(Card c) {

        VBox inside_card = new VBox();

        Image image = new Image("main/res/Card_Images/cardBack.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        //imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);


        inside_card.setPrefSize(100, 200);
        inside_card.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        inside_card.getChildren().add(imageView);
        //pic ine

        System.out.println(c.getSuit().toString() + c.getRank().toString());
        return inside_card;

    }
    */
}
