package main.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Model.Game.Online_Poker_5_Stud;
import main.Model.Game.Poker_5_Stud;
import main.Model.Stack.Card;
import main.View.main_menu;
import main.View.poker_game_1;

import java.util.ArrayList;
import java.util.Optional;

public class main_menu_controller {

    Poker_5_Stud model;
    main_menu view;

    int number_of_players;
    public ArrayList<String> savedNames = new ArrayList<>();
    ArrayList<ArrayList<Card>> all_hands = null;
    VBox box1;

    //HALLO
    public main_menu_controller(Poker_5_Stud model, main_menu view) {

        this.view = view;
        this.model = model;
        view.leftBox.requestFocus();

        view.playerDropdown.setDisable(true);

        view.tb1.setOnAction((ActionEvent e) -> {
            activateOfflein();
        });
        view.leftBox.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                activateOfflein();

            }
        });

        view.tb2.setOnAction((ActionEvent e) -> {
            activateOnlein();
        });
        view.rightBox.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                activateOnlein();

            }
        });


        view.Names.setVisible(false);
        view.offlineBtn.setVisible(false);


        view.playerDropdown.setOnAction((event) -> {

            view.node3.getChildren().clear();

            System.out.println(view.playerDropdown.getValue());
            number_of_players = Integer.valueOf((String) view.playerDropdown.getValue());

            for (int i = 0; i < number_of_players; i++) {
                TextField playerName = new TextField();
                playerName.setPromptText("Player " + (i + 1));
                view.node3.getChildren().add(playerName);
            }

            view.Names.setVisible(true);
            view.offlineBtn.setVisible(true);
            view.offlineBtn_box.getChildren().get(1).setVisible(false);
            view.offlineBtn.setDisable(false);


            if (!view.offlineBtn.isDisable()) {
                view.offlineBtn_box.getChildren().get(1).setVisible(false);
            }

        });


        view.onlineBtn.setOnAction((event) -> {


            for (int i = 0; i < number_of_players; i++) {


                if (!(((TextField) (view.node3.getChildren().get(i))).getText().isEmpty())) {
                    savedNames.add(((TextField) (view.node3.getChildren().get(i))).getText());
                    System.out.println(savedNames.get(i));
                } else {
                    ((TextField) (view.node3.getChildren().get(i))).setText("Player " + (i + 1));
                    savedNames.add(((TextField) (view.node3.getChildren().get(i))).getText());
                    System.out.println(savedNames.get(i));

                }
            }


            System.out.println("You clicked me!");
            view.deck_txt.setText("That's the main menu");


            //Here I want to swap the screen!

            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // OR
            Stage stageTheLabelBelongs = (Stage) view.deck_txt.getScene().getWindow();
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
            if (result.get().equals("")) {
                savedNames.add("Unnamed Player");

            } else if (result.isPresent()) {
                savedNames.add(result.get());
                System.out.println("Your name: " + result.get());
            }

// The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name -> System.out.println("Your name: " + name));


            Online_Poker_5_Stud model2 = new Online_Poker_5_Stud(stageTheEventSourceNodeBelongs, savedNames);
            online_poker_game_1_controller game_2_controller = new online_poker_game_1_controller(model2, gameView, savedNames);

            //stageTheLabelBelongs.setScene(new Scene(new Pane()));


        });

        ///////////////////////////////////////offline modus
        view.offlineBtn.setOnAction((event) -> {


            for (int i = 0; i < number_of_players; i++) {


                if (!(((TextField) (view.node3.getChildren().get(i))).getText().isEmpty())) {
                    savedNames.add(((TextField) (view.node3.getChildren().get(i))).getText());
                    System.out.println(savedNames.get(i));
                } else {
                    ((TextField) (view.node3.getChildren().get(i))).setText("Player " + (i + 1));
                    savedNames.add(((TextField) (view.node3.getChildren().get(i))).getText());
                    System.out.println(savedNames.get(i));

                }
            }


            System.out.println("You clicked me!");
            view.deck_txt.setText("That's the main menu");


            //Here I want to swap the screen!

            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // OR
            Stage stageTheLabelBelongs = (Stage) view.deck_txt.getScene().getWindow();
            // these two of them return the same stage
            // Swap screen::

            poker_game_1 gameView = new poker_game_1(stageTheEventSourceNodeBelongs);

            poker_game_1_controller game_1_controller = new poker_game_1_controller(model, gameView, savedNames);


            //Online_Poker_5_Stud model2 = new Online_Poker_5_Stud();
            //online_poker_game_1_controller game_2_controller = new online_poker_game_1_controller(model2, gameView, savedNames);

            //stageTheLabelBelongs.setScene(new Scene(new Pane()));


        });
        /////////////////////////////////////////////finito///////////////

/*
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest((event) -> {
            view.stop();
            Platform.exit();
        });
*/

    }

    private void activateOfflein() {

        view.tb1.setSelected(true);
        view.tb2.setSelected(false);

        view.offlineBtn.setDisable(true);
        view.onlineBtn.setDisable(false);
        view.playerDropdown.setDisable(true);

        view.Names.setVisible(false);
        view.offlineBtn.setVisible(false);

        view.playerDropdown.getSelectionModel().clearSelection();


        view.leftBox.setStyle("-fx-background-color: #73a400;" + "-fx-background-radius: 30;");
        view.rightBox.setStyle("-fx-background-color: #006200;" + "-fx-background-radius: 30;");
        view.node3.getChildren().clear();
        view.offlineBtn_box.getChildren().get(1).setVisible(true);


    }

    private void activateOnlein() {
        view.tb1.setSelected(false);
        view.tb2.setSelected(true);

        view.onlineBtn.setDisable(true);
        view.playerDropdown.setDisable(false);
        view.Names.setVisible(false);
        view.offlineBtn.setVisible(false);

        view.rightBox.setStyle("-fx-background-color: #73a400;" + "-fx-background-radius: 30;");
        view.leftBox.setStyle("-fx-background-color: #006200;" + "-fx-background-radius: 30;");
    }
}

