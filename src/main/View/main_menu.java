package main.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
    public final ToggleGroup group;
    public ToggleButton tb1;
    public ToggleButton tb2;
    public VBox rightBox;
    public HBox leftBox;
    public Label enterPlayerNumber;

    public main_menu(Stage primaryStage) {


        //TODO Knopf für online game  UND NÄÄÄme ich wirde schwarz ma

        //Offline/Online
        group = new ToggleGroup();


        tb1 = new ToggleButton("Online");
        tb1.setToggleGroup(group);


        tb2 = new ToggleButton("Offline");
        tb2.setToggleGroup(group);

        HBox toggleBox = new HBox();
        toggleBox.getChildren().addAll(tb1, tb2);
        toggleBox.setAlignment(Pos.CENTER);


        //Titel
        VBox container_title = new VBox();
        deck_txt = new Label("Poker 5 Stud");
        deck_txt.setFont(Font.font("tahoma", FontWeight.BOLD, FontPosture.REGULAR, 100));
        container_title.setSpacing(20);
        container_title.setPadding(new Insets(20, 20, 20, 20));
        container_title.getChildren().add(deck_txt);
        container_title.getChildren().add(toggleBox);
        container_title.setAlignment(Pos.CENTER);




        //Number of Players
        HBox container_game_setup = new HBox();
        Label lbl2 = new Label("How many Players?");
        lbl2.setFont(Font.font("tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));
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
        container_game_setup.setSpacing(40);



        //Names
        HBox node2 = new HBox();
        Label lbl3 = new Label("Names: ");
        lbl3.setFont(Font.font("tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));
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
        enterPlayerNumber = new Label("Please, enter number of players to continue.");
        enterPlayerNumber.setTextFill(Color.RED);
        enterPlayerNumber.setFont(Font.font("tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        offlineBtn_box.getChildren().addAll(offlineBtn, enterPlayerNumber);
        offlineBtn_box.setAlignment(Pos.CENTER);


        //Button to Play online
        onlineBtn = new Button("Play Online!");
        onlineBtn.setDisable(true);
        onlineBtn.setAlignment(Pos.CENTER);


        HBox centerBox = new HBox();


        rightBox = new VBox();
        rightBox.setSpacing(25);
        rightBox.getChildren().addAll(container_game_setup, node2, node3, offlineBtn_box);
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setStyle("-fx-background-color: Green");

        rightBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(20))));



        rightBox.prefWidthProperty().bind(centerBox.widthProperty());


        leftBox = new HBox();
        leftBox.getChildren().addAll(onlineBtn);
        leftBox.setAlignment(Pos.CENTER);
        leftBox.prefWidthProperty().bind(centerBox.widthProperty());
        leftBox.setStyle("-fx-background-color: Green");
        leftBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(20))));


        centerBox.getChildren().addAll(leftBox, rightBox);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-image: url('main/res/images/background.jpg'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: cover, auto;");
        root.setTop(container_title);
        root.setCenter(centerBox);
        //root.setAlignment(centerBox,Pos.CENTER);


        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setTitle("Poker Project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
