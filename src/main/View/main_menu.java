package main.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class main_menu {

	public Button play;
	public Label deck_txt;

    public main_menu(Stage primaryStage) {


		VBox controlArea = new VBox();
		controlArea.setAlignment(Pos.CENTER);

		GridPane grdpne = new GridPane();
    	grdpne.getStyleClass().add("grdpne");


		play = new Button("Play!");

		deck_txt = new Label("Main Menu");
    	controlArea.getChildren().add(grdpne);


		//controlArea.getChildren().add(flwpne);
		controlArea.getChildren().add(deck_txt);
		controlArea.getChildren().add(play);


    	BorderPane root = new BorderPane();
		root.setCenter(controlArea);


        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
