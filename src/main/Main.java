package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Model.Game.Poker_5_Stud;

public class Main extends Application {

    Poker_5_Stud Game1;

    public static void main(String[] args) {
        launch(args);


        System.exit(1);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


        Game1 = new Poker_5_Stud();


    }
}
