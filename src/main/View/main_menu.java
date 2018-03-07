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


        //TODO Knopf für online game  UND NÄÄÄme ich wirde schwarz ma


        //Titel
        HBox container_title = new HBox();
        deck_txt = new Label("Poker 5 Stud");
        container_title.getChildren().add(deck_txt);

        //Number of Players
        HBox container_game_setup = new HBox();
        Label lbl2 = new Label("How many Players?");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "2",
                        "3",
                        "4"
                );
        playerDropdown = new ComboBox(options);
        container_game_setup.getChildren().add(lbl2);
        container_game_setup.getChildren().add(playerDropdown);

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
        //todo ^^^^^^^^^ nie kommentierte code pushe. Jooooo das hanni nur gmacht dass mr en no hend falls das nid guet isch woni gmacht han...
        // wenner unnötig isch > lösche
        // wenn ned > bruche


        /**
         * todo VARIABLENÄME KOLLEG was isch do nid guet? das sind doch alles nodes vode vbox oder?^^
         */
        VBox root = new VBox();
        root.getChildren().add(container_title);
        root.getChildren().add(container_game_setup);
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
