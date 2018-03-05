package main.Controller;

import javafx.scene.Node;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Model.Game.Poker_5_Stud;
import main.Model.Stack.Card;
import main.View.Object_Appearance.Player_Pane_Appearance;
import main.View.poker_game_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class poker_game_1_controller {

    Poker_5_Stud model;
    poker_game_1 view;

    ArrayList<ArrayList<Card>> all_hands = null;



    public poker_game_1_controller(Poker_5_Stud model, poker_game_1 view) {

        this.view = view;
        this.model = model;


        view.winner_btn.setOnAction((event) -> {


        	int winner_int;
            if (all_hands == null) {

                view.deck_txt.setText("You have to deal out!");

            } else {

                //SETTING the winner card Pane to Yellow and telling who's the winner in the deck_txt
            	winner_int = model.get_winner(all_hands);
                view.deck_txt.setText("Player " + winner_int + " is winner!");
                
                int go_to_winner_pane;
                if(winner_int%2==0)
                	go_to_winner_pane=1;
                else
                	go_to_winner_pane=0;


                int go_to_winner_section = (int) (Math.round((double) winner_int / 2f) - 1);
                HBox section = (HBox) view.players.getChildren().get(go_to_winner_section);
                HBox winner_pane = (HBox) (section.getChildren().get(go_to_winner_pane));
                winner_pane.setStyle("-fx-background-color: #fffa00");



            }
        });


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
            result.ifPresent(number -> System.out.println("Player amount: " + number));


            if (view.players.getChildren() != null)
                view.players.getChildren().clear();




            all_hands = model.getHands(playerNumber);

            playerNumber = all_hands.size();



            for (int i = 0; i < playerNumber; i++) {
                Player_Pane_Appearance Player_Pane_Apperance = new Player_Pane_Appearance();

                HBox section;
                if (i % 2 == 0) {
                    section = new HBox();
                    section.setStyle("-fx-background-color: #00" + i + "0ff");
                    section.setPrefSize(1000, 200);
                    view.players.getChildren().add(section);


                } else {


                    section = (HBox) view.players.getChildren().get(view.players.getChildren().size() - 1);


                }

                HBox box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i));
                box1.setStyle("-fx-background-color: #00" + i + "f00");

                section.getChildren().add(box1);

                //PlayerPane.setPlayer(model.getPlayer(i));


            }


            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // OR
            Stage stageTheLabelBelongs = (Stage) view.deck_txt.getScene().getWindow();

            stageTheLabelBelongs.sizeToScene();

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

