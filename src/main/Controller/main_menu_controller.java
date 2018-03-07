package main.Controller;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Model.Game.Poker_5_Stud;
import main.View.main_menu;
import main.View.poker_game_1;

import java.util.ArrayList;

public class main_menu_controller {

    Poker_5_Stud model;
    main_menu view;

    int number_of_players;
    ArrayList<String> savedNames = new ArrayList<>();

    public main_menu_controller(Poker_5_Stud model, main_menu view) {

        this.view = view;
        this.model = model;


        view.playerDropdown.setOnAction((event) -> {

            System.out.println(view.playerDropdown.getValue());
            number_of_players = Integer.valueOf((String) view.playerDropdown.getValue());

            for (int i = 0; i < number_of_players; i++) {
                TextField playerName = new TextField();
                playerName.setPromptText("Player " + (i + 1));
                view.node3.getChildren().add(playerName);
            }


        });


        view.play.setOnAction((event) -> {

            for (int i = 0; i < number_of_players; i++) {

                savedNames.add(((TextField) (view.node3.getChildren().get(i))).getText());
                System.out.println(savedNames.get(i));
            }

            System.out.println("You clicked me!");
            view.deck_txt.setText("That's the main menu");


            //Here I want to swap the screen!

            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // OR
            Stage stageTheLabelBelongs = (Stage) view.deck_txt.getScene().getWindow();
            // these two of them return the same stage
            // Swap screen::


            poker_game_1 gameView = new poker_game_1(stageTheLabelBelongs);
            poker_game_1_controller game_1_controller = new poker_game_1_controller(model, gameView);


            //stageTheLabelBelongs.setScene(new Scene(new Pane()));


        });

/*
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest((event) -> {
            view.stop();
            Platform.exit();
        });
*/

    }
}

