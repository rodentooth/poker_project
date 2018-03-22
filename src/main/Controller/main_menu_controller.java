package main.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Model.Game.Online_Poker_5_Stud;
import main.Model.Game.Poker_5_Stud;
import main.Model.Stack.Card;
import main.View.main_menu;
import main.View.poker_game_1;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

public class main_menu_controller {

    Poker_5_Stud model;
    main_menu view;
    private int cheerscount = 0;
    public boolean bradmode = false;


    int number_of_players;
    public ArrayList<String> savedNames = new ArrayList<>();
    ArrayList<ArrayList<Card>> all_hands = null;
    VBox box1;

    //HALLO
    public main_menu_controller(Poker_5_Stud model, main_menu view) {

        this.view = view;
        this.model = model;

        cheerscount = 0;
        view.leftBox.requestFocus();

        view.playerDropdown.setDisable(false);


        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];


        view.deck_title.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (cheerscount < 5) {
                    cheerscount++;
                } else {
                    view.deck_title.setImage(new Image("main/res/images/cheers.gif"));
                    view.deck_title.getParent().getParent().setStyle("-fx-background-image: url('main/res/images/cheersbg.gif'); ");


                    bradmode = true;



                    Thread one = new Thread(() -> {


                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {


                                Media sound = new Media(Paths.get("src/main/res/music/8bit.mp3").toUri().toString());
                                mediaPlayer[0] = new MediaPlayer(sound);
                                mediaPlayer[0].play();

                            }
                        });
                    }
                    );

                    one.start();

                    Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.926), evt -> view.rightBox.setStyle("-fx-background-color: #0025ff;" + "-fx-background-radius: 30;")),
                            new KeyFrame(Duration.seconds(0.926), evt -> view.tb2.setStyle("-fx-background-color: Yellow")),
                            new KeyFrame(Duration.seconds(1.852), evt -> view.rightBox.setStyle("-fx-background-color: RED;" + "-fx-background-radius: 30;")),
                            new KeyFrame(Duration.seconds(1.852), evt -> view.tb2.setStyle("-fx-background-color: #0025ff")),
                            new KeyFrame(Duration.seconds(2.778), evt -> view.rightBox.setStyle("-fx-background-color: Yellow;" + "-fx-background-radius: 30;")),
                            new KeyFrame(Duration.seconds(2.778), evt -> view.tb2.setStyle("-fx-background-color: Red")));
                    timeline1.setCycleCount(Animation.INDEFINITE);
                    timeline1.play();


                    view.tb1.setVisible(false);
                    view.leftBox.setVisible(false);




                }
            }
        });


        view.tb1.setOnAction((ActionEvent e) -> {
            activateOnline();
            view.centerBox.getChildren().clear();
            view.centerBox.getChildren().add(view.leftBox);
        });
        view.leftBox.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                activateOnline();

            }
        });

        view.tb2.setOnAction((ActionEvent e) -> {
            activateOffline();
            view.centerBox.getChildren().clear();
            view.centerBox.getChildren().add(view.rightBox);
        });
        view.rightBox.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                activateOffline();

            }
        });


        view.Names.setVisible(false);
        view.offlineBtn.setVisible(false);


        view.playerDropdown.setOnAction((event) -> {

            view.text_field_line1.getChildren().clear();
            view.text_field_line2.getChildren().clear();
            view.text_field_line3.getChildren().clear();


            try {
                System.out.println(view.playerDropdown.getValue());
                number_of_players = Integer.valueOf((String) view.playerDropdown.getValue());


                for (int i = 0; i < number_of_players; i++) {
                    TextField playerName = new TextField();
                    playerName.setPromptText("Player " + (i + 1));
                    if (i + 1 <= 3) {
                        view.text_field_line1.getChildren().add(playerName);
                    } else if (i + 1 > 3 && i + 1 <= 6) {
                        view.text_field_line2.getChildren().add(playerName);
                    } else if (i + 1 > 6) {
                        view.text_field_line3.getChildren().add(playerName);
                    }

                }

                view.Names.setVisible(true);
                view.offlineBtn.setVisible(true);
                view.offlineBtn_box.getChildren().get(1).setVisible(false);
                view.offlineBtn.setDisable(false);


                if (!view.offlineBtn.isDisable()) {
                    view.offlineBtn_box.getChildren().get(1).setVisible(false);
                }

            } catch (NumberFormatException e) {
                System.out.println("You have changed the game mode. Nothing to worry about.");


            }

        });


        view.onlineBtn.setOnAction((event) -> {


            for (int i = 0; i < number_of_players; i++) {


                if (!(((TextField) (view.text_field_line1.getChildren().get(i))).getText().isEmpty())) {
                    savedNames.add(((TextField) (view.text_field_line1.getChildren().get(i))).getText());
                    System.out.println(savedNames.get(i));
                } else {
                    ((TextField) (view.text_field_line1.getChildren().get(i))).setText("Player " + (i + 1));
                    savedNames.add(((TextField) (view.text_field_line1.getChildren().get(i))).getText());
                    System.out.println(savedNames.get(i));

                }
            }


            System.out.println("You clicked me!");


            //Here I want to swap the screen!

            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // OR
            //Stage stageTheLabelBelongs = (Stage) view.deck_txt.getScene().getWindow();
            // these two of them return the same stage
            // Swap screen::

            poker_game_1 gameView = new poker_game_1(stageTheEventSourceNodeBelongs);

            /*            poker_game_1_controller game_1_controller = new poker_game_1_controller(model, gameView, savedNames);
             */

            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Text Input Dialog");
            dialog.setHeaderText("Look, a Text Input Dialog");
            dialog.setContentText("Please enter your name:");

// Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            try {
                if (result.get().equals("")) {
                    savedNames.add("Unnamed Player");

                } else if (result.isPresent()) {
                    savedNames.add(result.get());
                    System.out.println("Your name: " + result.get());
                }
            } catch (NoSuchElementException e) {
                savedNames.add("Unnamed Player");
            }

// The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name -> System.out.println("Your name: " + name));


            Online_Poker_5_Stud model2 = new Online_Poker_5_Stud(stageTheEventSourceNodeBelongs, savedNames);
            online_poker_game_1_controller game_2_controller = new online_poker_game_1_controller(model2, gameView, savedNames);

            //stageTheLabelBelongs.setScene(new Scene(new Pane()));


        });

        ///////////////////////////////////////offline modus
        view.offlineBtn.setOnAction((event) -> {


            for (int i = 0; i < number_of_players && i < 3; i++) {

                if (!(((TextField) (view.text_field_line1.getChildren().get(i))).getText().isEmpty())) {
                    savedNames.add(((TextField) (view.text_field_line1.getChildren().get(i))).getText());
                    System.out.println(savedNames.get(i));
                } else {
                    ((TextField) (view.text_field_line1.getChildren().get(i))).setText("Player " + (i + 1));
                    savedNames.add(((TextField) (view.text_field_line1.getChildren().get(i))).getText());
                    System.out.println(savedNames.get(i));
                }
            }

            if (number_of_players > 3) {
                for (int i = 0; i < (number_of_players - 3) && i < 3; i++) {

                    if (!(((TextField) (view.text_field_line2.getChildren().get(i))).getText().isEmpty())) {
                        savedNames.add(((TextField) (view.text_field_line2.getChildren().get(i))).getText());
                        System.out.println(savedNames.get(i + 3));
                    } else {
                        ((TextField) (view.text_field_line2.getChildren().get(i))).setText("Player " + (i + 4));
                        savedNames.add(((TextField) (view.text_field_line2.getChildren().get(i))).getText());
                        System.out.println(savedNames.get(i + 3));
                    }
                }
            }
            if (number_of_players > 6) {
                for (int i = 0; i < (number_of_players - 6) && i < 6; i++) {
                    if (!(((TextField) (view.text_field_line3.getChildren().get(i))).getText().isEmpty())) {
                        savedNames.add(((TextField) (view.text_field_line3.getChildren().get(i))).getText());
                        System.out.println(savedNames.get(i + 6));
                    } else {
                        ((TextField) (view.text_field_line3.getChildren().get(i))).setText("Player " + (i + 7));
                        savedNames.add(((TextField) (view.text_field_line3.getChildren().get(i))).getText());
                        System.out.println(savedNames.get(i + 6));
                    }
                }
            }


            System.out.println("You clicked me!");


            //Here I want to swap the screen!

            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // OR
            //Stage stageTheLabelBelongs = (Stage) view.deck_txt.getScene().getWindow();
            // these two of them return the same stage
            // Swap screen::

            poker_game_1 gameView = new poker_game_1(stageTheEventSourceNodeBelongs);

            poker_game_1_controller game_1_controller = new poker_game_1_controller(model, gameView, savedNames, bradmode);


            //Online_Poker_5_Stud model2 = new Online_Poker_5_Stud();
            //online_poker_game_1_controller game_2_controller = new online_poker_game_1_controller(model2, gameView, savedNames);

            //stageTheLabelBelongs.setScene(new Scene(new Pane()));


        });
        /////////////////////////////////////////////finito///////////////

    }

    private void activateOnline() {

        view.tb1.setSelected(true);
        view.tb2.setSelected(false);

        view.offlineBtn.setDisable(true);
        view.offlineBtn.setVisible(false);

        view.onlineBtn.setDisable(false);
        view.playerDropdown.setDisable(false);

        view.Names.setVisible(false);
        view.offlineBtn.setVisible(false);

        view.playerDropdown.getSelectionModel().clearSelection();


        //view.leftBox.setStyle("-fx-background-color: #73a400;" + "-fx-background-radius: 30;");
        //view.rightBox.setStyle("-fx-background-color: #006200;" + "-fx-background-radius: 30;");
        view.text_field_line1.getChildren().clear();
        view.offlineBtn_box.getChildren().get(1).setVisible(true);


    }

    private void activateOffline() {
        if (!view.rightBox.getStyle().equals("-fx-background-color: #73a400;" + "-fx-background-radius: 30;")) {


            view.tb1.setSelected(false);
            view.tb2.setSelected(true);

            view.onlineBtn.setDisable(false);
            view.playerDropdown.setDisable(false);
            view.Names.setVisible(false);
            view.offlineBtn.setVisible(false);

            // view.rightBox.setStyle("-fx-background-color: #73a400;" + "-fx-background-radius: 30;");
            // view.leftBox.setStyle("-fx-background-color: #006200;" + "-fx-background-radius: 30;");

        }
    }
}

