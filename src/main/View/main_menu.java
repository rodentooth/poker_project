package main.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class main_menu {

    public Button onlineBtn;
    public ImageView deck_title;
    public ComboBox playerDropdown;
    public HBox text_field_line1;
    public HBox text_field_line2;
    public HBox text_field_line3;
    public HBox node4;
    public boolean playerNumberEntered = false;
    public Button offlineBtn;
    public VBox offlineBtn_box;
    public final ToggleGroup group;
    public ToggleButton tb1;
    public ToggleButton tb2;
    public VBox rightBox;
    public HBox leftBox;
    public Label enterPlayerNumber;
    public Label HowManyPlayers;
    public Label Names;


    public main_menu(Stage primaryStage) {



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
        deck_title = new ImageView(new Image("main/res/images/title.png"));
        deck_title.setFitHeight(300);
        deck_title.setPreserveRatio(true);








        container_title.setSpacing(20);
        container_title.setPadding(new Insets(20, 20, 20, 20));
        container_title.getChildren().add(deck_title);
        container_title.getChildren().add(toggleBox);
        container_title.setAlignment(Pos.CENTER);




        //Number of Players
        HBox container_game_setup = new HBox();
        HowManyPlayers = new Label("How many Players?");
        HowManyPlayers.setFont(Font.font("tahoma", FontWeight.BOLD, FontPosture.REGULAR, 25));
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
        container_game_setup.getChildren().add(HowManyPlayers);
        container_game_setup.getChildren().add(playerDropdown);
        container_game_setup.setAlignment(Pos.CENTER);
        container_game_setup.setSpacing(85);



        //Names
        HBox node2 = new HBox();
        Names = new Label("Names: ");
        Names.setFont(Font.font("tahoma", FontWeight.BOLD, FontPosture.REGULAR, 25));
        Names.setAlignment(Pos.CENTER);
        node2.getChildren().add(Names);
        node2.setAlignment(Pos.CENTER);


        //Textfields for names 1-3
        text_field_line1 = new HBox();
        text_field_line1.setAlignment(Pos.CENTER);
        text_field_line1.setSpacing(5);
        text_field_line1.setPadding(new Insets(0, 10, 0, 10));

        // Textfields for names 4-6
        text_field_line2 = new HBox();
        text_field_line2.setAlignment(Pos.CENTER);
        text_field_line2.setSpacing(5);
        text_field_line2.setPadding(new Insets(0, 10, 0, 10));

        // Textfields for names 7-10
        text_field_line3 = new HBox();
        text_field_line3.setAlignment(Pos.CENTER);
        text_field_line3.setSpacing(5);
        text_field_line3.setPadding(new Insets(0, 10, 0, 10));


        //Button to play offline
        offlineBtn_box = new VBox();
        offlineBtn = new Button("Play Offline!");
        offlineBtn.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Tahoma\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 20px;\n" +
                "    -fx-padding: 10 20 10 20;");
        offlineBtn.setPrefSize(190, 40);
        offlineBtn.setDisable(true);
        enterPlayerNumber = new Label("Please, enter number of players to continue.");
        enterPlayerNumber.setTextFill(Color.RED);
        enterPlayerNumber.setFont(Font.font("tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        offlineBtn_box.getChildren().addAll(offlineBtn, enterPlayerNumber);
        offlineBtn_box.setAlignment(Pos.CENTER);


        //Button to Play online
        onlineBtn = new Button("Play Online!");
        onlineBtn.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Tahoma\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 20px;\n" +
                "    -fx-padding: 10 20 10 20;");


        onlineBtn.setDisable(false);
        onlineBtn.setAlignment(Pos.CENTER);
        onlineBtn.setPrefSize(190, 50);


        HBox centerBox = new HBox();


        rightBox = new VBox();
        rightBox.setSpacing(25);
        rightBox.getChildren().addAll(container_game_setup, node2, text_field_line1, text_field_line2, text_field_line3, offlineBtn_box);
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setStyle("-fx-background-color: Green;" + "-fx-background-radius: 30;");

        rightBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(20))));



        rightBox.prefWidthProperty().bind(centerBox.widthProperty());


        Line middleLine = new Line();
        middleLine.setStartY(100);
        middleLine.setEndY(400);
        middleLine.setStrokeWidth(20);
        middleLine.setVisible(false);

        leftBox = new HBox();
        leftBox.getChildren().addAll(onlineBtn);
        leftBox.setAlignment(Pos.CENTER);
        leftBox.prefWidthProperty().bind(centerBox.widthProperty());
        leftBox.setStyle("-fx-background-color: Green;" + "-fx-background-radius: 30;");
        leftBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(20))));


        centerBox.getChildren().addAll(leftBox, middleLine, rightBox);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();

        root.setPadding(new Insets(20, 20, 20, 20));
        BorderPane.setMargin(leftBox, new Insets(0, 0, 0, 20));
        BorderPane.setMargin(rightBox, new Insets(0, 20, 0, 0));

        root.setStyle("-fx-background-image: url('main/res/images/background.jpg'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: cover, auto;");
        root.setTop(container_title);
        root.setCenter(centerBox);
        //root.setAlignment(centerBox,Pos.CENTER);


        Scene scene = new Scene(root, 1300, 900);
        primaryStage.setTitle("Poker Project - Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
