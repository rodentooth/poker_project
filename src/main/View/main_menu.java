package main.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class main_menu {

	public Button play;
	public Label deck_txt;
    public ComboBox playerDropdown;
    public HBox node3;

    public main_menu(Stage primaryStage) {


        //Titel
        HBox node0 = new HBox();
        deck_txt = new Label("Poker 5 Stud");
        node0.getChildren().add(deck_txt);

        //Number of Players
        HBox node1 = new HBox();
        Label lbl2 = new Label("How many Players?");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "2",
                        "3",
                        "4"
                );
        playerDropdown = new ComboBox(options);
        node1.getChildren().add(lbl2);
        node1.getChildren().add(playerDropdown);

        //Names
        HBox node2 = new HBox();
        Label lbl3 = new Label("Names: ");
        node2.getChildren().add(lbl3);

        //Textfields for names
        node3 = new HBox();



        //Button to Play
        HBox node4 = new HBox();
        play = new Button("Play!");
        node4.getChildren().add(play);

		/*GridPane grdpne = new GridPane();
    	grdpne.getStyleClass().add("grdpne");
        GridPane grdpne2 = new GridPane();
        grdpne2.getStyleClass().add("grdpne");




		deck_txt = new Label("Main Menu");
    	controlArea.getChildren().add(grdpne);

		//controlArea.getChildren().add(flwpne);
		controlArea.getChildren().add(deck_txt);
		controlArea.getChildren().add(play);

        controlArea.getChildren().add(grdpne2);
*/

        VBox root = new VBox();
        root.getChildren().add(node0);
        root.getChildren().add(node1);
        root.getChildren().add(node2);
        root.getChildren().add(node3);
        root.getChildren().add(node4);


        Scene scene = new Scene(root, 500, 300);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
