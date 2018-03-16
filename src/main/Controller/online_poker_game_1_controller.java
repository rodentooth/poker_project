package main.Controller;

import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Model.Game.Online_Poker_5_Stud;
import main.Model.Game.Poker_5_Stud;
import main.Model.Ranking.Hand_Ranks;
import main.Model.Ranking.Ranking;
import main.Model.Stack.Card;
import main.View.Object_Appearance.Player_Pane_Appearance;
import main.View.main_menu;
import main.View.poker_game_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javafx.scene.transform.Rotate.Y_AXIS;
import static main.Controller.poker_game_1_controller.getvBox;

public class online_poker_game_1_controller {

    Online_Poker_5_Stud model;
    poker_game_1 view;
    VBox box1;
    ArrayList<ArrayList<Card>> all_hands = null;
    Boolean once = true;
    ArrayList<String> savedNames;


    public online_poker_game_1_controller(Online_Poker_5_Stud model, poker_game_1 view, ArrayList<String> savedNames) {



        this.savedNames = savedNames;
        this.view = view;
        this.model = model;
        view.players.setSpacing(10);
        view.players.setPadding(new Insets(10, 10, 10, 10));

        view.deal_btn.setDisable(true);
        view.winner_btn.setDisable(true);

        final long[] timeThen = {System.currentTimeMillis()};
        final long[] waitingtime = {0};
        all_hands = model.getOnlineCards();

        view.deal_btn.setText("Reveal Cards");


        Thread one = new Thread(() -> {

            while (all_hands.size() == 0 || savedNames.size() != 2) {

                long counter = System.currentTimeMillis() - timeThen[0];
                if (counter > 1000) {
                    timeThen[0] = System.currentTimeMillis();


                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            view.deck_txt.setText("Waiting for Opponent..." + (waitingtime[0]++));
                        }
                    });

                }
            all_hands = model.getOnlineCards();
                if (!model.getOppName().equals("null")) {
                    if (model.AmIHost())
                        savedNames.add(0, model.getOppName());
                    else
                        savedNames.add(model.getOppName());

                }

            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    continue_game();
                }
            });
        }
        );

        one.start();

    }

    private void continue_game() {

        view.deal_btn.setDisable(false);


        view.winner_btn.setOnAction((event) -> {


            int winner_index;
            if (all_hands == null) {

                view.deck_txt.setText("You have to deal out!");

            } else {

                //SETTING the winner card Pane to Yellow and telling who's the winner in the deck_txt
                winner_index = model.getWinner(all_hands);
                view.deck_txt.setText(savedNames.get(winner_index - 1) + " is winner!");


                VBox winner_pane = get_Specific_player_pane(winner_index);

                winner_pane.setStyle("-fx-background-color: #94bd00");


                for (int i = 1; i < (all_hands.size() + 1); i++) {

                    Label winner_loser_label = (Label) (((HBox) (get_Specific_player_pane(i)).getChildren().get(3)).getChildren().get(0));

                    if (winner_index == (i)) {
                        winner_loser_label.setText("Winner");
                    } else {
                        winner_loser_label.setText("loser");
                    }
                }


                view.deal_btn.setText("Exit");
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


                        ImageView background = (ImageView) (((Pane) p.getChildren().get(0)).getChildren()).get(1);
                        ImageView foreground = (ImageView) (((Pane) p.getChildren().get(0)).getChildren()).get(0);


                        RotateTransition rt = new RotateTransition(Duration.millis(1500), background);
                        rt.setByAngle(-90);
                        rt.setAxis(Y_AXIS);
                        rt.setCycleCount(1);

                        RotateTransition rt_fg = new RotateTransition(Duration.millis(1500), foreground);
                        rt_fg.setByAngle(-90);
                        rt_fg.setAxis(Y_AXIS);
                        rt_fg.setCycleCount(1);

                        //FadeTransition ft = new FadeTransition(Duration.millis(1500),background);


                        SequentialTransition s = new SequentialTransition(rt, rt_fg);


                        Timer timer = new Timer((j + 1) * (i + 1) * 100, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //timer.stop();

                                s.play();


                            }
                        });
                        //timer.setInitialDelay();
                        timer.setRepeats(false);

                        rt.setOnFinished((javafx.event.ActionEvent event2) -> {
                            //rt_fg.play();

                        });

                        view.deal_btn.setDisable(true);
                        long startTime = System.nanoTime();
/*
                        while (rt.getStatus()== Animation.Status.RUNNING){

                            if (rt.getCurrentTime().toMillis()>1500){

                            }
                        }

*/


                        timer.start();

                    }
                }
                once = false;
                view.winner_btn.setDisable(false);


            } else {

                Poker_5_Stud model = new Poker_5_Stud();
                main_menu_controller controller = new main_menu_controller(model, new main_menu(view.s));

            }
        });




/*
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest((event) -> {
            view.stop();
            Platform.exit();
        });
*/

        dealout(all_hands);


    }

    public void dealout(ArrayList<ArrayList<Card>> all_hands) {

        this.all_hands = all_hands;


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

            section.getChildren().add(box1);


            //PlayerPane.setPlayer(model.getPlayer(i));


        }


        Stage stageTheLabelBelongs = (Stage) view.deck_txt.getScene().getWindow();

        stageTheLabelBelongs.sizeToScene();
    }


    private VBox get_Specific_player_pane(int index) {


        return getvBox(index, view.players.getChildren(), all_hands);

    }
}

