package main.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class poker_game_1 {

    public Button shuffle_btn;
    public Label deck_txt;
    public Button deal_btn;
    public HBox players;
    private HBox controlArea;


    public poker_game_1(Stage primaryStage) {
// create the player pane (maybe a separate class? and then just call the panes depending on how many players are in the game)
        players = new HBox();
        players.setStyle("-fx-background-color: #0000ff");

        players.setPrefSize(1000, 200);

        players.setStyle("-fx-background-color: #ff0000");


        //players.getChildren().add(btn);


//    	controlArea = new HBox();
//    	controlArea.setId("hbox-1");
//    	Button shuffle_btn = new Button("Shuffle");
//    	Button deal_btn = new Button("Deal");
//    	Label deck_txt = new Label("Deck");
//    	controlArea.getChildren().addAll(deck_txt, shuffle_btn, deal_btn);

        controlArea = new HBox();
        GridPane grdpne = new GridPane();
        grdpne.getStyleClass().add("grdpne");


        shuffle_btn = new Button("Shuffle");
        deal_btn = new Button("Deal");
        deck_txt = new Label("Deck");


        controlArea.getChildren().add(grdpne);
        grdpne.add(deck_txt, 0, 0, 1, 1);
        grdpne.add(shuffle_btn, 1, 0, 1, 1);
        grdpne.add(deal_btn, 2, 0, 1, 1);


        BorderPane root = new BorderPane();
        root.setCenter(players);
        root.setBottom(controlArea);


        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

