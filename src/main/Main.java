package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.Model.Game.Poker_5_Stud;
import main.View.main_menu;

public class Main extends Application {

    Poker_5_Stud Game1;
    main_menu menu1;

    public static void main(String[] args) {
        launch(args);


        System.exit(1);

    }

    @Override
    public void start(Stage primaryStage) {


        //menu1 = new main_menu(primaryStage);
        Game1 = new Poker_5_Stud();


    }
}
