package main.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class poker_game_1 {

    public Button winner_btn;
    public Label deck_txt;
    public Button deal_btn;
    public VBox players;
    private HBox controlArea;


    public poker_game_1(Stage primaryStage) {
// create the player pane (maybe a separate class? and then just call the panes depending on how many players are in the game)
        players = new VBox();
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
        grdpne.setPrefSize(1000, 50);



        winner_btn = new Button("Get Winner");
        deal_btn = new Button("Deal");
        deck_txt = new Label("Deck");


        controlArea.getChildren().add(grdpne);
        grdpne.add(deck_txt, 0, 0, 1, 1);
        grdpne.add(winner_btn, 1, 0, 1, 1);
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

