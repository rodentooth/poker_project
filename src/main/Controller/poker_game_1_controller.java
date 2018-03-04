package main.Controller;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Model.Game.Poker_5_Stud;
import main.View.poker_game_1;

import java.util.Optional;

public class poker_game_1_controller {

    Poker_5_Stud model;
    poker_game_1 view;


    public poker_game_1_controller(Poker_5_Stud model, poker_game_1 view) {

        this.view = view;
        this.model = model;


        view.deal_btn.setOnAction((event) -> {

            System.out.println("You clicked me!");
            view.deck_txt.setText("dealing out...");

            TextInputDialog dialog = new TextInputDialog("2");
            dialog.setTitle("Numbers of Player");
            dialog.setHeaderText("Choose, how many players should be in this game!");
            dialog.setContentText("Please enter a value equal or above 2, but not higher than 10:");

            int playerNumber = 0;
// Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                System.out.println("Players in our game: " + result.get());
                if (Integer.valueOf(result.get()) == (int) Integer.valueOf(result.get())) {
                    playerNumber = Integer.valueOf(result.get());

                }
            }


// The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name -> System.out.println("Your name: " + name));


            for (int i = 0; i < playerNumber; i++) {

                HBox section;
                if (i % 2 == 0) {
                    section = new HBox();
                    System.out.println("i can be divided ");
                    section.setStyle("-fx-background-color: #0000ff");
                    section.setPrefSize(1000, 200);
                    view.players.getChildren().add(section);


                } else {

                    section = (HBox) view.players.getChildren().get(view.players.getChildren().size() - 1);

                }


                HBox PlayerPane = new HBox();
                PlayerPane.setPrefSize(500, 200);
                PlayerPane.setStyle("-fx-background-color: #00" + i + "f00");

                PlayerPane.setPadding(new Insets(15, 12, 15, 12));

                PlayerPane.setSpacing(10);

                section.getChildren().add(PlayerPane);

                for (int j = 0; j < 5; j++) {

                    HBox cardPane = new HBox();
                    cardPane.setStyle("-fx-background-color: #" + j + "f000" + j);

                    cardPane.setPrefSize(100, 200);

                    PlayerPane.getChildren().add(cardPane);
                }

                //PlayerPane.setPlayer(model.getPlayer(i));


            }


            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // OR
            Stage stageTheLabelBelongs = (Stage) view.deck_txt.getScene().getWindow();


        });



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

