package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.Controller.Controller;
import main.Model.Game.Poker_5_Stud;
import main.View.main_menu;

public class Main extends Application {

    Poker_5_Stud model;
    main_menu view;
    Controller controller;

    public static void main(String[] args) {
        launch(args);


        System.exit(1);

    }

    @Override
    public void start(Stage primaryStage) {

        view = new main_menu(primaryStage);
        model = new Poker_5_Stud();
        controller = new Controller();

    }
}
