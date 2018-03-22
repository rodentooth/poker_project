package main.Model.Game;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Model.Networking.PostRequest;
import main.Model.Networking.ReceiveSocket;
import main.Model.Networking.SendSocket;
import main.Model.Ranking.Hand_Ranks;
import main.Model.Ranking.Ranking;
import main.Model.Stack.Card;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Online_Poker_5_Stud {


    SendSocket CS;
    ReceiveSocket server = null;
    String Oppname = "null";

    //search for opponents:
    //Create Sendsocket with server target to get possible opponents

    Boolean AmIHost;

    public Online_Poker_5_Stud(Stage stage, ArrayList<String> savedNamed) {

        Poker_5_Stud PokerGame = new Poker_5_Stud();

        PostRequest PR = new PostRequest();

        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println("IP:  " + ip);

        if (PR.send("http://apod.frozensparks.com/pokergame.php", savedNamed.get(0), ip)) {
            System.out.println("THIS IS TEH POST RESULT:  " + PR.postResult);

            if ((PR.postResult).contains(",")) {
                AmIHost = false;
                String string = PR.postResult;
                String[] parts = string.split(",");
                String IP = parts[0]; // 004
                Oppname = parts[1]; // 034556

                System.out.println("Trying to connect to " + IP);

                CS = new SendSocket(IP, savedNamed);
                //send(CS.ps);


            } else {

                AmIHost = true;
                System.out.println("Create Host, Waiting for connections...");

                try {
                    server = new ReceiveSocket(12700, 5, InetAddress.getLocalHost().getHostAddress(), PokerGame.getHands(2));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                HBox grid = new HBox();

                grid.setPadding(new Insets(0, 10, 0, 10));

                grid.getChildren().add(new Label("Waiting for other players..."));

/*
                //Set up the product preview image loader
                BufferedImage bufferedImage;
                File file = new File("main/res/images/circle-loading.gif");
                try {
                    bufferedImage = ImageIO.read(file);
                    grid.getChildren().add(new ImageView(SwingFXUtils.toFXImage(bufferedImage, null)));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

*/
                grid.getChildren().add(new ImageView(new Image("main/res/images/circle-loading.gif")));


                Thread one = new Thread(() -> server.verbinde());

                one.start();




            }
        }





    }

    public ArrayList<ArrayList<Card>> getOnlineCards() {
        if (AmIHost)
            return server.getOnlineCards();
        else
            return CS.getOnlineCards();
    }

    public String getOppName() {
        if (AmIHost)
            return server.getOppName();
        else
            return Oppname;
    }

    static int getWinnerAsInt(ArrayList<ArrayList<Card>> Cards) {
        int W = 0;
        Ranking r = new Ranking();

        for (int i = 1; i < Cards.size(); i++) {
            System.out.println("\nPlayer " + (W + 1) + " Plays against " + (1 + i) + "");


            int winner = r.compare_hands(Cards.get(W), Cards.get(i));
            if (winner == 1)
                System.out.println("Player " + (W + 1) + " wins over Player " + (1 + i));
            if (winner == 2) {
                System.out.println("Player " + (1 + i) + " wins over player " + (1 + W));
                W = i;


            }
            if (winner == 0) {
                System.out.println("Tie! Save the other winner and go on");
                int W2 = 0;
                for (ArrayList<Card> B : Cards) {
                    int winner2 = r.compare_hands(Cards.get(W2), B);
                    if (winner2 == 1)
                        System.out.println("Hand 1 wins!");
                    if (winner2 == 2)
                        W2 = Cards.indexOf(B);

                }
                if (W2 == W) {
                    System.out.println("Player " + Cards.indexOf(Cards.get(i)) + " and Player " + Cards.indexOf(Cards.get(W)) + " are winner!!");
                    String s = (i + 1) + "" + (W + 1);
                    return Integer.valueOf(s);
                }
            }
        }
        System.out.println("Player " + (W + 1) + " wins with " + (String.valueOf(Hand_Ranks.values()[Math.abs(r.rank_hand(Cards.get(W)) - 10)])).replace("_", " "));


        return (W + 1);
    }

    public int getWinner(ArrayList<ArrayList<Card>> Cards) {

        return getWinnerAsInt(Cards);

    }


    public boolean AmIHost() {
        return (AmIHost);
    }


    ///get hosts
    ///if host is found, delete host IP on server
    ///if host is zero, become a host, and begin to wait for opponents.

    //php: 1. look for ip addresses
    // if found: transmit IP & delete it
    //If not found: write IP in list, transmit message to start host countdown ( 60 second, waiting for opponent....)

    //create game

    //Evaluate winner

    //Cleanup
}
