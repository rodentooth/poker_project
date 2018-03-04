package main.Controller;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Model.Game.Poker_5_Stud;
import main.Model.Stack.Card;
import main.View.Card_Apperance;
import main.View.poker_game_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class poker_game_1_controller {

    Poker_5_Stud model;
    poker_game_1 view;


    public poker_game_1_controller(Poker_5_Stud model, poker_game_1 view) {

        this.view = view;
        this.model = model;


        view.deal_btn.setOnAction((event) -> {

            List<Integer> choices = new ArrayList<>();
            choices.add(2);
            choices.add(3);
            choices.add(4);
            choices.add(5);
            choices.add(6);
            choices.add(7);
            choices.add(8);
            choices.add(9);
            choices.add(10);


            view.deck_txt.setText("dealing out...");

            ChoiceDialog<Integer> dialog = new ChoiceDialog<>(2, choices);
            dialog.setTitle("Numbers of Player");
            dialog.setHeaderText("Choose, how many players should be in this game!");
            dialog.setContentText("Please enter a value equal or above 2, but not higher than 10:");

            int playerNumber = 0;
// Traditional way to get the response value.
            Optional<Integer> result = dialog.showAndWait();
            if (result.isPresent()) {
                System.out.println("Players in our game: " + result.get());
                if (result.get() == (int) result.get()) {
                    playerNumber = result.get();

                }
            }

            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name -> System.out.println("Your name: " + name));

            ArrayList<ArrayList<Card>> all_hands = model.getHands(playerNumber);

            playerNumber = all_hands.size();

            Card_Apperance card_creator = new Card_Apperance();


            for (int i = 0; i < playerNumber; i++) {

                HBox section;
                if (i % 2 == 0) {
                    section = new HBox();
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

                for (Card c : all_hands.get(i)) {


                    PlayerPane.getChildren().add(card_creator.Card_Apperance(c));
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

