package main.Controller;

import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Model.Game.Poker_5_Stud;
import main.Model.Ranking.Hand_Ranks;
import main.Model.Ranking.Ranking;
import main.Model.Stack.Card;
import main.View.Object_Appearance.Player_Pane_Appearance;
import main.View.poker_game_1;

import java.util.ArrayList;

import static javafx.scene.transform.Rotate.Y_AXIS;

public class poker_game_1_controller {

    Poker_5_Stud model;
    poker_game_1 view;
    VBox box1;
    ArrayList<ArrayList<Card>> all_hands = null;
    Boolean once = true;


    public poker_game_1_controller(Poker_5_Stud model, poker_game_1 view, ArrayList<String> savedNames) {


        this.view = view;
        this.model = model;
        view.players.setSpacing(10);
        view.players.setPadding(new Insets(10, 10, 10, 10));


        view.winner_btn.setOnAction((event) -> {


            int winner_index;
            if (all_hands == null) {

                view.deck_txt.setText("You have to deal out!");

            } else {

                //SETTING the winner card Pane to Yellow and telling who's the winner in the deck_txt
                winner_index = model.getWinner(all_hands);
                view.deck_txt.setText("Player " + winner_index + " is winner!");


                VBox winner_pane = get_Specific_player_pane(winner_index);

                winner_pane.setStyle("-fx-background-color: #fffa00");


                for (int i = 1; i < (all_hands.size() + 1); i++) {

                    Label winner_loser_label = (Label) (((HBox) (get_Specific_player_pane(i)).getChildren().get(3)).getChildren().get(0));

                    if (winner_index == (i)) {
                        winner_loser_label.setText("Winner");
                    } else {
                        winner_loser_label.setText("loser");
                    }
                }


                view.deal_btn.setText("new Game");
                view.deal_btn.setDisable(false);

                //dealout(savedNames);



            }
        });

        Timeline time = new Timeline();
        time.setCycleCount(1);
        time.setAutoReverse(false);

        view.players.setMaxWidth(Double.MAX_VALUE);
        view.players.setMaxHeight(Double.MAX_VALUE);

        view.deal_btn.setOnAction((javafx.event.ActionEvent event) -> {

            if (once) {

                for (int i = 1; i < (all_hands.size() + 1); i++) {

                    HBox player_pane = (((HBox) (get_Specific_player_pane(i)).getChildren().get(1)));

                    for (int j = 0; j < (5); j++) {
                        Pane p = (Pane) player_pane.getChildren().get(j);

                        RotateTransition rt = new RotateTransition(Duration.millis(3000), p);
                        rt.setByAngle(-180);
                        rt.setAxis(Y_AXIS);
                        rt.setCycleCount(1);

                        rt.play();

/*
                        while (rt.getStatus()== Animation.Status.RUNNING){

                            if (rt.getCurrentTime().toMillis()>1500){

                            }
                        }
*/
                        if (rt.getCurrentTime().toMillis() > 1500) {
                            ((Pane) p.getChildren().get(0)).getChildren().remove(1);

                        }

                        rt.setOnFinished((javafx.event.ActionEvent event2) -> {
                            ((Pane) p.getChildren().get(0)).getChildren().remove(1);
                            view.deal_btn.setDisable(true);

                        });
                    }
                }
                once = false;

            } else {
                view.deal_btn.setText("Reveal Cards");
                dealout(savedNames);
                once = true;

            }
        });




/*
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest((event) -> {
            view.stop();
            Platform.exit();
        });
*/

        dealout(savedNames);


    }

    private void dealout(ArrayList<String> savedNames) {
        System.out.println("Players in our game: " + savedNames.size());


        if (view.players.getChildren() != null)
            view.players.getChildren().clear();


        all_hands = model.getHands(savedNames.size());


        for (int i = 0; i < all_hands.size(); i++) {
            Player_Pane_Appearance Player_Pane_Apperance = new Player_Pane_Appearance();

            HBox section;
            if (((all_hands.size() <= 5) && (i % 2 == 0)) || ((all_hands.size() > 5) && (i % 4 == 0))) {
                section = new HBox();
                // section.setStyle("-fx-background-color: #00" + i + "0ff");
                section.setPrefSize(1000, 300);
                view.players.getChildren().add(section);


            } else {


                section = (HBox) view.players.getChildren().get(view.players.getChildren().size() - 1);


            }


            section.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            AnchorPane.setTopAnchor(view.players, 10.0);
            AnchorPane.setBottomAnchor(view.players, 10.0);
            AnchorPane.setRightAnchor(view.players, 10.0);
            AnchorPane.setTopAnchor(section, 10.0);

            section.setSpacing(10);
            box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i));

            box1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


            box1.setStyle("-fx-background-color: #003700");
            box1.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(20))));


            Label lbl1 = (Label) (((HBox) box1.getChildren().get(0)).getChildren().get(0));
            lbl1.setText(savedNames.get(i));

            Label lbl2 = (Label) (((HBox) box1.getChildren().get(2)).getChildren().get(0));

            Ranking r = new Ranking();

            lbl2.setText("It is a " + (String.valueOf(Hand_Ranks.values()[Math.abs(r.rank_hand(all_hands.get(i)) - 10)])).replace("_", " "));

            //Label lbl3 = (Label) (((HBox) box1.getChildren().get(3)).getChildren().get(0));
            //lbl3.setText("You are a ...");


            section.getChildren().add(box1);


            //PlayerPane.setPlayer(model.getPlayer(i));


        }


        Stage stageTheLabelBelongs = (Stage) view.deck_txt.getScene().getWindow();

        stageTheLabelBelongs.sizeToScene();
    }


    private VBox get_Specific_player_pane(int index) {


        int go_to_winner_pane;
        if (index % 2 == 0)
            go_to_winner_pane = 1;
        else
            go_to_winner_pane = 0;
        int go_to_winner_section = (int) (Math.round((double) index / 2f) - 1);
        HBox section = (HBox) view.players.getChildren().get(go_to_winner_section);
        VBox winner_pane = (VBox) (section.getChildren().get(go_to_winner_pane));


        return winner_pane;

    }
}

