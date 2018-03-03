package main.Controller;

import javafx.scene.Node;
import javafx.stage.Stage;
import main.Model.Game.Poker_5_Stud;
import main.View.poker_game_1;

public class poker_game_1_controller {

    Poker_5_Stud model;
    poker_game_1 view;

    public poker_game_1_controller(Poker_5_Stud model, poker_game_1 view) {

        this.view = view;
        this.model = model;


        view.shuffle_btn.setOnAction((event) -> {

            System.out.println("You clicked me!");
            view.deck_txt.setText("Shuffeling...");


            //Here I want to swap the screen!

            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // OR
            Stage stageTheLabelBelongs = (Stage) view.deck_txt.getScene().getWindow();
            // these two of them return the same stage
            // Swap screen::


            //poker_game_1 gameView = new poker_game_1(stageTheLabelBelongs);
            //controller = new main_menu_controller(model, gameView);


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

