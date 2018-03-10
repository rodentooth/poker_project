package main.View.Object_Appearance;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.Model.Stack.Card;

public class Card_Apperance {

    public Pane Card_Apperance(Card c) {

        Pane p = new Pane();

        VBox inside_card2 = new VBox();

        //inside_card.setPadding(new Insets(15, 12, 15, 12));

        ImageView imageView2 = new ImageView();
        imageView2.setFitWidth(100);
        imageView2.setPreserveRatio(true);
        imageView2.setSmooth(true);
        imageView2.setCache(true);


        Image shortImageUrl = new Image("Card_Images/" + c.getSuit().toString() + "/" + c.getSuit().toString() + "_" + c.getRank().toString() + ".png");
        imageView2.setImage(shortImageUrl);

        /*
        String card = new String(c.getSuit().toString() + c.getRank().toString());
        Image image = new Image("Card_Images/Spades/Spades_Ace.png");
        Image image1 = new Image("Card_Images/Spades/Spades_King.png");
        Image image2 = new Image("Card_Images/Spades/Spades_Queen.png");
        Image image3 = new Image("Card_Images/Spades/Spades_Jack.png");
        Image image4 = new Image("Card_Images/Spades/Spades_Ten.png");
        Image image5 = new Image("Card_Images/Spades/Spades_Nine.png");
        Image image6 = new Image("Card_Images/Spades/Spades_Eight.png");
        Image image7 = new Image("Card_Images/Spades/Spades_Seven.png");
        Image image8 = new Image("Card_Images/Spades/Spades_Six.png");
        Image image9 = new Image("Card_Images/Spades/Spades_Five.png");
        Image image10 = new Image("Card_Images/Spades/Spades_Four.png");
        Image image11 = new Image("Card_Images/Spades/Spades_Three.png");
        Image image12 = new Image("Card_Images/Spades/Spades_Two.png");
        Image image13 = new Image("Card_Images/Hearts/Hearts_Ace.png");
        Image image14 = new Image("Card_Images/Hearts/Hearts_Eight.png");
        Image image15 = new Image("Card_Images/Hearts/Hearts_Five.png");
        Image image16 = new Image("Card_Images/Hearts/Hearts_Four.png");
        Image image17 = new Image("Card_Images/Hearts/Hearts_Jack.png");
        Image image18 = new Image("Card_Images/Hearts/Hearts_King.png");
        Image image19 = new Image("Card_Images/Hearts/Hearts_Nine.png");
        Image image20 = new Image("Card_Images/Hearts/Hearts_Queen.png");
        Image image21 = new Image("Card_Images/Hearts/Hearts_Seven.png");
        Image image22 = new Image("Card_Images/Hearts/Hearts_Six.png");
        Image image23 = new Image("Card_Images/Hearts/Hearts_Ten.png");
        Image image24 = new Image("Card_Images/Hearts/Hearts_Three.png");
        Image image25 = new Image("Card_Images/Hearts/Hearts_Two.png");
        Image image26 = new Image("Card_Images/Diamonds/Diamonds_Ace.png");
        Image image27 = new Image("Card_Images/Diamonds/Diamonds_Eight.png");
        Image image28 = new Image("Card_Images/Diamonds/Diamonds_Fivr.png");
        Image image29 = new Image("Card_Images/Diamonds/Diamonds_Four.png");
        Image image30 = new Image("Card_Images/Diamonds/Diamonds_Jack.png");
        Image image31 = new Image("Card_Images/Diamonds/Diamonds_King.png");
        Image image32 = new Image("Card_Images/Diamonds/Diamonds_Nine.png");
        Image image33 = new Image("Card_Images/Diamonds/Diamonds_Queen.png");
        Image image34 = new Image("Card_Images/Diamonds/Diamonds_Seven.png");
        Image image35 = new Image("Card_Images/Diamonds/Diamonds_Six.png");
        Image image36 = new Image("Card_Images/Diamonds/Diamonds_Ten.png");
        Image image37 = new Image("Card_Images/Diamonds/Diamonds_Three.png");
        Image image38 = new Image("Card_Images/Diamonds/Diamonds_Two.png");
        Image image39 = new Image("Card_Images/Clubs/Clubs_Ace.png");
        Image image40 = new Image("Card_Images/Clubs/Clubs_Eight.png");
        Image image41 = new Image("Card_Images/Clubs/Clubs_Five.png");
        Image image42 = new Image("Card_Images/Clubs/Clubs_Four.png");
        Image image43 = new Image("Card_Images/Clubs/Clubs_Jack.png");
        Image image44 = new Image("Card_Images/Clubs/Clubs_King.png");
        Image image45 = new Image("Card_Images/Clubs/Clubs_Nine.png");
        Image image46 = new Image("Card_Images/Clubs/Clubs_Queen.png");
        Image image47 = new Image("Card_Images/Clubs/Clubs_Seven.png");
        Image image48 = new Image("Card_Images/Clubs/Clubs_Six.png");
        Image image49 = new Image("Card_Images/Clubs/Clubs_Ten.png");
        Image image50 = new Image("Card_Images/Clubs/Clubs_Three.png");
        Image image51 = new Image("Card_Images/Clubs/Clubs_Two.png");



        switch (card) {
            case "SpadesAce":
                imageView2.setImage(image);
                break;
            case "SpadesKing":
                imageView2.setImage(image1);
                break;
            case "SpadesQueen":
                imageView2.setImage(image2);
                break;
            case "SpadesJack":
                imageView2.setImage(image3);
                break;
            case "SpadesTen":
                imageView2.setImage(image4);
                break;
            case "SpadesNine":
                imageView2.setImage(image5);
                break;
            case "SpadesEight":
                imageView2.setImage(image6);
                break;
            case "SpadesSeven":
                imageView2.setImage(image7);
                break;
            case "SpadesSix":
                imageView2.setImage(image8);
                break;
            case "SpadesFive":
                imageView2.setImage(image9);
                break;
            case "SpadesFour":
                imageView2.setImage(image10);
                break;
            case "SpadesThree":
                imageView2.setImage(image11);
                break;
            case "SpadesTwo":
                imageView2.setImage(image12);
                break;
            case "HeartsAce":
                imageView2.setImage(image13);
                break;
            case "HeartsKing":
                imageView2.setImage(image14);
                break;
            case "HeartsQueen":
                imageView2.setImage(image15);
                break;
            case "HeartsJack":
                imageView2.setImage(image16);
                break;
            case "HeartsTen":
                imageView2.setImage(image17);
                break;
            case "HeartsNine":
                imageView2.setImage(image18);
                break;
            case "HeartsEight":
                imageView2.setImage(image19);
                break;
            case "HeartsSeven":
                imageView2.setImage(image20);
                break;
            case "HeartsSix":
                imageView2.setImage(image21);
                break;
            case "HeartsFive":
                imageView2.setImage(image22);
                break;
            case "HeartsFour":
                imageView2.setImage(image23);
                break;
            case "HeartsThree":
                imageView2.setImage(image24);
                break;
            case "HeartsTwo":
                imageView2.setImage(image25);
                break;
            case "DiamondsAce":
                imageView2.setImage(image26);
                break;
            case "DiamondsKing":
                imageView2.setImage(image27);
                break;
            case "DiamondsQueen":
                imageView2.setImage(image28);
                break;
            case "DiamondsJack":
                imageView2.setImage(image29);
                break;
            case "DiamondsTen":
                imageView2.setImage(image30);
                break;
            case "DiamondsNine":
                imageView2.setImage(image31);
                break;
            case "DiamondsEight":
                imageView2.setImage(image32);
                break;
            case "DiamondsSeven":
                imageView2.setImage(image33);
                break;
            case "DiamondsSix":
                imageView2.setImage(image34);
                break;
            case "DiamondsFive":
                imageView2.setImage(image35);
                break;
            case "DiamondsFour":
                imageView2.setImage(image36);
                break;
            case "DiamondsThree":
                imageView2.setImage(image37);
                break;
            case "DiamondsTwo":
                imageView2.setImage(image38);
                break;
            case "ClubsAce":
                imageView2.setImage(image39);
                break;
            case "ClubsKing":
                imageView2.setImage(image40);
                break;
            case "ClubsQueen":
                imageView2.setImage(image41);
                break;
            case "ClubsJack":
                imageView2.setImage(image42);
                break;
            case "ClubsTen":
                imageView2.setImage(image43);
                break;
            case "ClubsNine":
                imageView2.setImage(image44);
                break;
            case "ClubsEight":
                imageView2.setImage(image45);
                break;
            case "ClubsSeven":
                imageView2.setImage(image46);
                break;
            case "ClubsSix":
                imageView2.setImage(image47);
                break;
            case "ClubsFive":
                imageView2.setImage(image48);
                break;
            case "ClubsFour":
                imageView2.setImage(image49);
                break;
            case "ClubsThree":
                imageView2.setImage(image50);
                break;
            case "ClubsTwo":
                imageView2.setImage(image51);
                break;

        }
*/

        //Text Rank = new Text(c.getRank().toString());
        //Text Suit = new Text(c.getSuit().toString());

        inside_card2.getChildren().add(imageView2);
        inside_card2.setPrefSize(100, 200);

        inside_card2.setStyle("-fx-background-color: #FFFFFF");

        p.getChildren().add(inside_card2);

        return inside_card2;
    }

    public Pane Empty_Card_Apperance(Card c) {

        VBox inside_card = new VBox();

        Image image = new Image("Card_Images/cardBack.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);


        inside_card.setPrefSize(100, 200);
        inside_card.getChildren().add(imageView);
        //pic ine

        System.out.println(c.getSuit().toString() + c.getRank().toString());
        return inside_card;

    }
}
