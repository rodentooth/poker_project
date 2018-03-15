package main.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class main_menu {

    public Button onlineBtn;
	public Label deck_txt;
    public ComboBox playerDropdown;
    public HBox node3;
    public HBox node4;
    public boolean playerNumberEntered = false;
    public Button offlineBtn;
    public HBox offlineBtn_box;

    public main_menu(Stage primaryStage) {


        //TODO Knopf für online game  UND NÄÄÄme ich wirde schwarz ma

        //Offline/Online
        final ToggleGroup group = new ToggleGroup();


        ToggleButton tb1 = new ToggleButton("Online");
        tb1.setToggleGroup(group);
        tb1.setSelected(true);

        ToggleButton tb2 = new ToggleButton("Offline");
        tb2.setToggleGroup(group);

        HBox toggleBox = new HBox();
        toggleBox.getChildren().addAll(tb1, tb2);
        toggleBox.setAlignment(Pos.CENTER);


        //Titel
        VBox container_title = new VBox();
        deck_txt = new Label("Poker 5 Stud");
        container_title.getChildren().add(deck_txt);
        container_title.getChildren().add(toggleBox);
        container_title.setAlignment(Pos.CENTER);




        //Number of Players
        HBox container_game_setup = new HBox();
        Label lbl2 = new Label("How many Players?");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "2",
                        "3",
                        "4",
                        "5",
                        "6",
                        "7",
                        "8",
                        "9",
                        "10"
                );
        playerDropdown = new ComboBox(options);
        //playerDropdown.getSelectionModel().selectFirst();
        container_game_setup.getChildren().add(lbl2);
        container_game_setup.getChildren().add(playerDropdown);
        container_game_setup.setAlignment(Pos.CENTER);



        //Names
        HBox node2 = new HBox();
        Label lbl3 = new Label("Names: ");
        lbl3.setAlignment(Pos.CENTER);
        node2.getChildren().add(lbl3);
        node2.setAlignment(Pos.CENTER);


        //Textfields for names
        node3 = new HBox();
        node3.setAlignment(Pos.CENTER);


        //Button to play offline
        offlineBtn_box = new HBox();
        offlineBtn = new Button("Play Offline");
        offlineBtn.setDisable(true);
        Label enterPlayerNumber = new Label("Please, enter number of players to continue.");
        offlineBtn_box.getChildren().addAll(offlineBtn, enterPlayerNumber);
        offlineBtn_box.setAlignment(Pos.CENTER);


        //Button to Play online
        onlineBtn = new Button("Play Online!");
        onlineBtn.setDisable(true);
        onlineBtn.setAlignment(Pos.CENTER);


        HBox centerBox = new HBox();


        VBox rightBox = new VBox();
        rightBox.setSpacing(25);
        rightBox.getChildren().addAll(container_game_setup, node2, node3, offlineBtn_box);
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setStyle("-fx-background-color: #fffa00");
        rightBox.prefWidthProperty().bind(centerBox.widthProperty());


        HBox leftBox = new HBox();
        leftBox.getChildren().addAll(onlineBtn);
        leftBox.setAlignment(Pos.CENTER);
        leftBox.prefWidthProperty().bind(centerBox.widthProperty());


        Line middleLine = new Line();
        middleLine.setStartY(100);
        middleLine.setEndY(400);

        centerBox.getChildren().addAll(leftBox, middleLine, rightBox);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(container_title);
        root.setCenter(centerBox);
        //root.setAlignment(centerBox,Pos.CENTER);


        Scene scene = new Scene(root, 1200, 700);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
