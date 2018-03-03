package main.View;

import javafx.scene.Scene;
import javafx.stage.Stage;


public class View {


    private Stage stage;

    public View(Stage primaryStage) {

        this.stage = primaryStage;

    }

    public void changeScene(Scene scene) {

        stage.setScene(scene);
        stage.show();
    }
}