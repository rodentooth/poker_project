package main.Controller;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Model.Game.Poker_5_Stud;
import main.Model.Ranking.Hand_Ranks;
import main.Model.Ranking.Ranking;
import main.Model.Stack.Card;
import main.Model.Stack.Rank;
import main.Model.Stack.Suit;
import main.View.Object_Appearance.Player_Pane_Appearance;
import main.View.main_menu;
import main.View.poker_game_1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

import static javafx.scene.transform.Rotate.Y_AXIS;
import static javafx.scene.transform.Rotate.Z_AXIS;

class poker_game_1_controller {

    private Poker_5_Stud model;
    private poker_game_1 view;
    ArrayList<ArrayList<Card>> all_hands = null;
    private Boolean once = true;
    private Boolean goBacktoMainMenu = false;
    private Label lbl2;
    private int revealedCards = 0;
    boolean bradmode;

    poker_game_1_controller(Poker_5_Stud model, poker_game_1 view, ArrayList<String> savedNames, boolean braode) {


        this.view = view;
        this.model = model;
        view.players.setSpacing(10);
        view.players.setPadding(new Insets(10, 10, 10, 10));
        view.deal_btn.setText("Reveal Cards");
        bradmode = braode;
        view.deck_txt.setTextFill(Color.WHITE);

        if (bradmode) {
            view.players.setStyle("-fx-background-image: url('main/res/images/cheersbg2.gif'); ");

            view.winner_btn.setFocusTraversable(false);
            final Boolean[] once = {true};
            view.winner_btn.setOnMouseEntered(event -> {
                if (once[0]) {

                    for (int i = 1; i < (all_hands.size() + 1); i++) {

                        VBox player_pane = get_Specific_player_pane(i);

                        RotateTransition rt = new RotateTransition(Duration.millis(1500), player_pane);
                        rt.setByAngle(3000);

                        if (i > 5 && i <= 8)
                            rt.setByAngle(-3000);

                        rt.setAxis(Z_AXIS);
                        rt.setCycleCount(Animation.INDEFINITE);
                        rt.play();
                        once[0] = false;
                    }


                }

                if (view.winner_btn_box.getAlignment() == Pos.CENTER || view.winner_btn_box.getAlignment() == Pos.CENTER_RIGHT)
                    view.winner_btn_box.setAlignment(Pos.CENTER_LEFT);
                else
                    view.winner_btn_box.setAlignment(Pos.CENTER_RIGHT);

            });
        }

        view.winner_btn.setOnAction((event) -> {


            if (!goBacktoMainMenu) {
                int winner_index;


                if (all_hands == null) {

                    view.deck_txt.setText("You have to deal out!");

                } else {


                    Ranking r = new Ranking();

                    winner_index = model.getWinner(all_hands);

                    int W1 = -1;
                    int W2 = -1;


                    if (winner_index > 10) {
                        String s = String.valueOf(winner_index);
                        W1 = Integer.valueOf(String.valueOf(s.charAt(0)));
                        W2 = Integer.valueOf(String.valueOf(s.charAt(1)));
                        view.deck_txt.setText(savedNames.get(W1 - 1) + " and " + savedNames.get(W2 - 1) + " are winners!");


                        VBox winner_pane1 = get_Specific_player_pane(W1);
                        winner_pane1.setStyle("-fx-background-color: #94bd00");

                        VBox winner_pane2 = get_Specific_player_pane(W2);
                        winner_pane2.setStyle("-fx-background-color: #94bd00");


                    } else {
                        view.deck_txt.setText(savedNames.get(winner_index - 1) + " is winner!");

                        VBox winner_pane = get_Specific_player_pane(winner_index);

                        winner_pane.setStyle("-fx-background-color: #94bd00");
                    }



                    for (int i = 1; i < (all_hands.size() + 1); i++) {

                        Label winner_loser_label = (Label) (((HBox) (get_Specific_player_pane(i)).getChildren().get(3)).getChildren().get(0));

                        if (winner_index == (i) || i == W1 || i == W2) {
                            winner_loser_label.setText("Winner");
                        } else {
                            winner_loser_label.setText("Loser");
                        }
                        if ((savedNames.get(i - 1).equalsIgnoreCase("ria"))) {
                            winner_loser_label.setText("Maybe they can. Just call woof();");

                        }
                    }


                    view.winner_btn.setText("Play again");
                    view.deal_btn.setText("Back to main menu");
                    view.deal_btn.setDisable(false);

                    //dealout(savedNames);


                }
                goBacktoMainMenu = true;
            } else {

                goBacktoMainMenu = false;
                view.deal_btn.setText("Reveal Cards");
                dealout(savedNames);
                once = true;
                view.winner_btn.setText("Get Winner");
                view.winner_btn.setDisable(true);


            }
        });


        view.players.setMaxWidth(Double.MAX_VALUE);
        view.players.setMaxHeight(Double.MAX_VALUE);

        view.deal_btn.setOnAction((javafx.event.ActionEvent event) -> {

            if (once) {

                for (int i = 1; i < (all_hands.size() + 1); i++) {

                    HBox player_pane = (((HBox) (get_Specific_player_pane(i)).getChildren().get(1)));

                    animate_pane(i, player_pane);
                    view.deal_btn.setDisable(true);

                }
                once = false;
                view.winner_btn.setDisable(false);


            } else {

                main_menu_controller controller = new main_menu_controller(model, new main_menu(view.s));

            }
        });



        dealout(savedNames);


    }

    private void dealout(ArrayList<String> savedNames) {
        revealedCards = 0;
        view.winner_btn.setDisable(true);

        System.out.println("Players in our game: " + savedNames.size());


        if (view.players.getChildren() != null)
            view.players.getChildren().clear();


        all_hands = model.getHands(savedNames.size());

        for (int i = 0; i < all_hands.size(); i++) {
            Player_Pane_Appearance Player_Pane_Apperance = new Player_Pane_Appearance();

            HBox section;
            if (((all_hands.size() <= 5) && (i % 2 == 0)) || ((all_hands.size() > 5) && (i % 4 == 0))) {
                section = new HBox();
                section.setPrefSize(1000, 300);
                view.players.getChildren().add(section);


            } else {


                section = (HBox) view.players.getChildren().get(view.players.getChildren().size() - 1);


            }

            VBox box1;

            section.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            AnchorPane.setTopAnchor(view.players, 10.0);
            AnchorPane.setBottomAnchor(view.players, 10.0);
            AnchorPane.setRightAnchor(view.players, 10.0);
            AnchorPane.setTopAnchor(section, 10.0);

            section.setSpacing(10);


            if (savedNames.get(i).equalsIgnoreCase("brad")) {
                all_hands.set(i, new ArrayList<Card>(Arrays.asList(new Card(Suit.Clubs, Rank.Ace), new Card(Suit.Clubs, Rank.King), new Card(Suit.Clubs, Rank.Queen), new Card(Suit.Clubs, Rank.Jack), new Card(Suit.Clubs, Rank.Ten))));
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 1);

                box1.setStyle("-fx-background-image: url('main/res/images/cheersbg.gif'); " + "-fx-background-radius: 30;" + "-fx-border-radius: 30;");

            } else if (savedNames.get(i).equalsIgnoreCase("Emanuel")) {
                all_hands.set(i, new ArrayList<Card>(Arrays.asList(new Card(Suit.Diamonds, Rank.Ace), new Card(Suit.Diamonds, Rank.King), new Card(Suit.Diamonds, Rank.Queen), new Card(Suit.Diamonds, Rank.Jack), new Card(Suit.Diamonds, Rank.Ten))));
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 1);

                box1.setStyle("-fx-background-image: url('main/res/images/emanuelbg.gif'); " + " -fx-background-radius: 30;" + " -fx-border-radius: 30;");

            } else if (savedNames.get(i).equalsIgnoreCase("Royal Flush")) {
                all_hands.set(i, new ArrayList<Card>(Arrays.asList(new Card(Suit.Diamonds, Rank.Ace), new Card(Suit.Diamonds, Rank.King), new Card(Suit.Diamonds, Rank.Queen), new Card(Suit.Diamonds, Rank.Jack), new Card(Suit.Diamonds, Rank.Ten))));
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 0);

            } else if (savedNames.get(i).equalsIgnoreCase("Straight Flush")) {
                all_hands.set(i, new ArrayList<Card>(Arrays.asList(new Card(Suit.Spades, Rank.Six), new Card(Suit.Spades, Rank.Seven), new Card(Suit.Spades, Rank.Eight), new Card(Suit.Spades, Rank.Nine), new Card(Suit.Spades, Rank.Ten))));
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 0);

            } else if (savedNames.get(i).equalsIgnoreCase("Four of a Kind")) {
                all_hands.set(i, new ArrayList<Card>(Arrays.asList(new Card(Suit.Clubs, Rank.Queen), new Card(Suit.Spades, Rank.Queen), new Card(Suit.Diamonds, Rank.Queen), new Card(Suit.Hearts, Rank.Queen), new Card(Suit.Spades, Rank.Ten))));
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 0);

            } else if (savedNames.get(i).equalsIgnoreCase("Full House")) {
                all_hands.set(i, new ArrayList<Card>(Arrays.asList(new Card(Suit.Hearts, Rank.Jack), new Card(Suit.Spades, Rank.Jack), new Card(Suit.Diamonds, Rank.Jack), new Card(Suit.Spades, Rank.Ace), new Card(Suit.Diamonds, Rank.Ace))));
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 0);

            } else if (savedNames.get(i).equalsIgnoreCase("Flush")) {
                all_hands.set(i, new ArrayList<Card>(Arrays.asList(new Card(Suit.Spades, Rank.Six), new Card(Suit.Spades, Rank.Ace), new Card(Suit.Spades, Rank.Eight), new Card(Suit.Spades, Rank.Jack), new Card(Suit.Spades, Rank.Ten))));
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 0);

            } else if (savedNames.get(i).equalsIgnoreCase("Straight")) {
                all_hands.set(i, new ArrayList<Card>(Arrays.asList(new Card(Suit.Diamonds, Rank.Six), new Card(Suit.Spades, Rank.Seven), new Card(Suit.Clubs, Rank.Eight), new Card(Suit.Spades, Rank.Nine), new Card(Suit.Hearts, Rank.Ten))));
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 0);

            } else if (savedNames.get(i).equalsIgnoreCase("Three of a Kind")) {
                all_hands.set(i, new ArrayList<Card>(Arrays.asList(new Card(Suit.Spades, Rank.Six), new Card(Suit.Hearts, Rank.Six), new Card(Suit.Clubs, Rank.Six), new Card(Suit.Spades, Rank.Nine), new Card(Suit.Hearts, Rank.Ten))));
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 0);

            } else if (savedNames.get(i).equalsIgnoreCase("Two Pair")) {
                all_hands.set(i, new ArrayList<Card>(Arrays.asList(new Card(Suit.Spades, Rank.Six), new Card(Suit.Hearts, Rank.Six), new Card(Suit.Clubs, Rank.King), new Card(Suit.Diamonds, Rank.King), new Card(Suit.Spades, Rank.Ten))));
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 0);

            } else if (savedNames.get(i).equalsIgnoreCase("Pair")) {
                all_hands.set(i, new ArrayList<Card>(Arrays.asList(new Card(Suit.Spades, Rank.Seven), new Card(Suit.Diamonds, Rank.Seven), new Card(Suit.Spades, Rank.Eight), new Card(Suit.Spades, Rank.Nine), new Card(Suit.Spades, Rank.Ten))));
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 0);

            } else if (savedNames.get(i).equalsIgnoreCase("High Card")) {
                all_hands.set(i, new ArrayList<Card>(Arrays.asList(new Card(Suit.Spades, Rank.Queen), new Card(Suit.Diamonds, Rank.Jack), new Card(Suit.Hearts, Rank.Three), new Card(Suit.Spades, Rank.Eight), new Card(Suit.Clubs, Rank.Nine))));
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 0);

            } else if (savedNames.get(i).equalsIgnoreCase("ria")) {
                //all_hands.set(i,null);
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 1);
                Label winner_loser_label = (Label) (((HBox) (box1).getChildren().get(3)).getChildren().get(0));
                winner_loser_label.setText("Can dogs play cards?");
                HBox ria_cards = (HBox) (box1).getChildren().get(1);
                (box1).getChildren().get(2).setVisible(false);
                for (int y = 0; y < 5; y++) {
                    ((HBox) ria_cards.getChildren().get(y)).getChildren().get(0).setVisible(false);
                }

                box1.setStyle("-fx-background-image: url('main/res/images/riabg.png'); " + " -fx-background-radius: 30;" + " -fx-border-radius: 30;");

            } else if (savedNames.get(i).equalsIgnoreCase("joel")) {
                box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 1);
                box1.setStyle("-fx-background-image: url('main/res/images/joelbg.gif'); " + "-fx-background-radius: 30;" + "-fx-border-radius: 30;");

            } else {
                if (bradmode)
                    box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 2);
                else
                    box1 = Player_Pane_Apperance.Create_Plpa(all_hands.get(i), 0);

                box1.setStyle("-fx-background-color: #003700;" +
                        "-fx-background-radius: 20;");

            }

            if (((all_hands.size() <= 5))) {
                box1.setMinSize(600, 250);
                section.setMaxSize(1000, 300);


            } else {

                ((HBox) box1.getChildren().get(1)).setMinSize(300, 70);
                ((HBox) box1.getChildren().get(1)).setMaxSize(300, 70);
                ((HBox) box1.getChildren().get(1)).setSpacing(10);
                box1.setMinSize(330, 80);
                box1.setMaxSize(330, 80);
                section.setMinSize(1000, 200);

                section.setMaxSize(1000, 200);

            }

            final Boolean[] hasTurned = {false};
            box1.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    if (!hasTurned[0] && view.winner_btn.isDisable()) {
                        once = false;
                        hasTurned[0] = true;
                        HBox player_pane = (((HBox) (box1.getChildren().get(1))));
                        view.deal_btn.setDisable(true);
                        animate_pane(0, player_pane);
                        if (revealedCards >= all_hands.size() - 1) {
                            view.winner_btn.setDisable(false);
                        } else {
                            revealedCards++;

                        }

                    }
                }

            });

            box1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


            box1.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(20))));


            Label lbl1 = (Label) (((HBox) box1.getChildren().get(0)).getChildren().get(0));
            lbl1.setText(savedNames.get(i));


            lbl2 = (Label) (((HBox) box1.getChildren().get(2)).getChildren().get(0));


            Ranking r = new Ranking();
            lbl2.setText("It is a " + (String.valueOf(Hand_Ranks.values()[Math.abs(r.rank_hand(all_hands.get(i)) - 10)])).replace("_", " "));
            lbl2.setVisible(false);
            //Label lbl3 = (Label) (((HBox) box1.getChildren().get(3)).getChildren().get(0));
            //lbl3.setText("You are a ...");


            section.getChildren().add(box1);


            //PlayerPane.setPlayer(model.getPlayer(i));


        }


        Stage stageTheLabelBelongs = (Stage) view.deck_txt.getScene().getWindow();

        stageTheLabelBelongs.sizeToScene();

        if (all_hands.size() > 5) {
            stageTheLabelBelongs.setMinWidth(1400);


        }
    }

    static VBox getvBox(int index, ObservableList<Node> children, ArrayList<ArrayList<Card>> all_hands) {


        //TODO Hervorheben in dokumentation, algorithmus um player Pane zu finden

        if (((all_hands.size() <= 5))) {

            //nur 2er linien
            int go_to_winner_pane;
            if (index % 2 == 0)
                go_to_winner_pane = 1;
            else
                go_to_winner_pane = 0;
            int go_to_winner_section = (int) (Math.round((double) index / 2f) - 1);
            HBox section = (HBox) children.get(go_to_winner_section);
            VBox winner_pane = (VBox) (section.getChildren().get(go_to_winner_pane));


            return winner_pane;


        } else if (((all_hands.size() > 5) && (all_hands.size() % 4 == 0))) {
            //4er und 2er linien
            int go_to_winner_pane;
            if (index <= 4)
                go_to_winner_pane = index - 1;
            else
                go_to_winner_pane = index - 5;

            int go_to_winner_section;
            if (index <= 4)
                go_to_winner_section = 0;
            else
                go_to_winner_section = 1;


            HBox section = (HBox) children.get(go_to_winner_section);
            VBox winner_pane = (VBox) (section.getChildren().get(go_to_winner_pane));

            return winner_pane;


        } else {
            //3erlinien


            int go_to_winner_pane = 0;
            int go_to_winner_section = 0;

            for (int i = 0; i < children.size() - 1; i++) {

                HBox section = (HBox) children.get(i);

                if (section.getChildren().size() < index) {
                    go_to_winner_section++;
                    go_to_winner_pane += section.getChildren().size();
                    index -= section.getChildren().size();
                }
                go_to_winner_pane = index - 1;


            }

            HBox section = (HBox) children.get(go_to_winner_section);
            VBox winner_pane = (VBox) (section.getChildren().get(go_to_winner_pane));

            return winner_pane;

        }

    }

    private void animate_pane(int i, HBox player_pane) {

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

            if (bradmode) {
                rt_fg.setByAngle(i * 360);

                rt_fg.setCycleCount(Animation.INDEFINITE);
                rt_fg.setDuration(Duration.millis(1000));
            }

            //FadeTransition ft = new FadeTransition(Duration.millis(1500),background);


            SequentialTransition s = new SequentialTransition(rt, rt_fg);


            Timer timer = new Timer((j + 1) * (i + 1) * 100, e -> {
                s.play();
            });
            //timer.setInitialDelay();
            timer.setRepeats(false);
            timer.start();


            s.setOnFinished(event -> {
                if (!bradmode)
                    ((HBox) ((Pane) player_pane.getParent()).getChildren().get(2)).getChildren().get(0).setVisible(true);
            });
        }


    }
    private VBox get_Specific_player_pane(int index) {


        return getvBox(index, view.players.getChildren(), all_hands);

    }
}

