package main.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class main_menu {

	public Button play;
	public Label deck_txt;
    public ComboBox playerDropdown;
    public HBox node3;
    public HBox node4;
    public boolean playerNumberEntered = false;

    public main_menu(Stage primaryStage) {


        //TODO Knopf für online game  UND NÄÄÄme ich wirde schwarz ma


        //Titel
        HBox container_title = new HBox();
        deck_txt = new Label("Poker 5 Stud");
        container_title.getChildren().add(deck_txt);

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

        //Names
        HBox node2 = new HBox();
        Label lbl3 = new Label("Names: ");
        node2.getChildren().add(lbl3);

        //Textfields for names
        node3 = new HBox();



        //Button to Play
        node4 = new HBox();
        play = new Button("Play Online!");
        Button offlineBtn = new Button("Play Offline");
        Label enterPlayerNumber = new Label("Please, enter number of players to continue.");
        play.setDisable(true);
        node4.getChildren().add(play);
        node4.getChildren().add(offlineBtn);
        node4.getChildren().add(enterPlayerNumber);









        /*
         * todo VARIABLENÄME KOLLEG was isch do nid guet? das sind doch alles nodes vode vbox oder?^^
         */
        VBox root = new VBox();
        root.getChildren().add(container_title);
        root.getChildren().add(container_game_setup);
        root.getChildren().add(node2);
        root.getChildren().add(node3);
        root.getChildren().add(node4);


        Scene scene = new Scene(root, 700, 400);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
