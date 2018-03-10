package main.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class poker_game_1 {

    public Button winner_btn;
    public Label deck_txt;
    public Button deal_btn;
    public VBox players;
    private HBox controlArea;
    public AnchorPane anchorpane;
    public BorderPane root;


    public poker_game_1(Stage primaryStage) {
// create the player pane (maybe a separate class? and then just call the panes depending on how many players are in the game)
        players = new VBox();
      //  players.setPrefSize(1000, 200);

        players.setStyle("-fx-background-color: #0061ff");


        anchorpane = new AnchorPane();

        //players.getChildren().add(btn);


//    	controlArea = new HBox();
//    	controlArea.setId("hbox-1");
//    	Button shuffle_btn = new Button("Shuffle");
//    	Button deal_btn = new Button("Deal");
//    	Label deck_txt = new Label("Deck");
//    	controlArea.getChildren().addAll(deck_txt, shuffle_btn, deal_btn);

        controlArea = new HBox();
      // controlArea.setSpacing(50);
        controlArea.setAlignment(Pos.CENTER);


        controlArea.setAlignment(Pos.CENTER);

        controlArea.getStyleClass().add("controlArea");
        controlArea.setPrefSize(1000, 50);


        VBox winner_btn_box = new VBox();
        winner_btn_box.setAlignment(Pos.CENTER);
        winner_btn_box.setStyle("-fx-background-color: #fffa00");
        winner_btn_box.prefWidthProperty().bind(controlArea.widthProperty());
        winner_btn = new Button("Get Winner");
        winner_btn_box.getChildren().add(winner_btn);

        VBox deal_btn_box = new VBox();
        deal_btn_box.setAlignment(Pos.CENTER);
        deal_btn_box.setStyle("-fx-background-color: #02f41e");
        deal_btn_box.prefWidthProperty().bind(controlArea.widthProperty());
        deal_btn = new Button("Deal");
        deal_btn_box.getChildren().add(deal_btn);


        VBox deck_txt_box = new VBox();
        deck_txt_box.setAlignment(Pos.CENTER_LEFT);
        deck_txt_box.prefWidthProperty().bind(controlArea.widthProperty());
        deck_txt_box.setStyle("-fx-background-color: #ff0000");

        deck_txt = new Label("Deck");
        deck_txt_box.getChildren().add(deck_txt);


        controlArea.getChildren().add(deck_txt_box);
        controlArea.getChildren().add(winner_btn_box);
        controlArea.getChildren().add(deal_btn_box);


        root = new BorderPane();
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

