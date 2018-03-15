package main.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Model.Game.Online_Poker_5_Stud;
import main.Model.Game.Poker_5_Stud;
import main.Model.Stack.Card;
import main.View.main_menu;
import main.View.poker_game_1;

import java.util.ArrayList;

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

        view.playerDropdown.setDisable(true);

        view.tb1.setOnAction((ActionEvent e) -> {
            view.offlineBtn.setDisable(true);
            view.onlineBtn.setDisable(false);
            view.playerDropdown.setDisable(true);
            view.leftBox.setStyle("-fx-background-color: #fffa00");
            view.rightBox.setStyle("-fx-background-color: Green");
            view.node3.getChildren().clear();
            view.offlineBtn_box.getChildren().add(view.enterPlayerNumber);

        });

        view.tb2.setOnAction((ActionEvent e) -> {
            view.onlineBtn.setDisable(true);
            view.playerDropdown.setDisable(false);
            view.rightBox.setStyle("-fx-background-color: #fffa00");
            view.leftBox.setStyle("-fx-background-color: Green");
        });





        view.playerDropdown.setOnAction((event) -> {

            view.node3.getChildren().clear();

            System.out.println(view.playerDropdown.getValue());
            number_of_players = Integer.valueOf((String) view.playerDropdown.getValue());

            for (int i = 0; i < number_of_players; i++) {
                TextField playerName = new TextField();
                playerName.setPromptText("Player " + (i + 1));
                view.node3.getChildren().add(playerName);
            }

            view.offlineBtn.setDisable(false);


            if (view.offlineBtn.isDisable() == false) {
                view.offlineBtn_box.getChildren().remove(1);
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


            Online_Poker_5_Stud model2 = new Online_Poker_5_Stud();
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
}

