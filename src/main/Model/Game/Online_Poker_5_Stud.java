package main.Model.Game;

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

import static main.Model.Networking.SendSocket.send;

public class Online_Poker_5_Stud {


    SendSocket CS;
    //search for opponents:
    //Create Sendsocket with server target to get possible opponents


    public Online_Poker_5_Stud() {

        Poker_5_Stud PokerGame = new Poker_5_Stud();

        PostRequest PR = new PostRequest();

        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println("IP:  " + ip);

        if (PR.send("http://apod.frozensparks.com/pokergame.php", "Emanuel", ip)) {
            System.out.println("THIS IS TEH POST RESULT:  " + PR.postResult);

            if ((PR.postResult).contains(",")) {
                String string = PR.postResult;
                String[] parts = string.split(",");
                String IP = parts[0]; // 004
                String part2 = parts[1]; // 034556

                System.out.println("Trying to connect to " + IP);

                CS = new SendSocket(IP);
                send(CS.ps);


            } else {

                System.out.println("Create Host, Waiting for connections...");

                ReceiveSocket server = null;
                try {
                    server = new ReceiveSocket(12700, 5, InetAddress.getLocalHost().getHostAddress(), PokerGame.getHands(2));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                server.verbinde();

            }
        }





    }

    public ArrayList<ArrayList<Card>> getOnlineCards() {
        return CS.getOnlineCards();
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
                System.out.println("Tie!");
                int W2 = 0;
                for (ArrayList<Card> B : Cards) {
                    int winner2 = r.compare_hands(Cards.get(W2), B);
                    if (winner2 == 1)
                        System.out.println("Hand 1 wins!");
                    if (winner2 == 2)
                        W = Cards.indexOf(B);

                }
                if (W2 == W) {
                    System.out.println("Player " + Cards.indexOf(Cards.get(i)) + " and Player " + Cards.indexOf(Cards.get(W)) + " are winner!!");
                }
            }
        }
        System.out.println("Player " + (W + 1) + " wins with " + (String.valueOf(Hand_Ranks.values()[Math.abs(r.rank_hand(Cards.get(W)) - 10)])).replace("_", " "));


        return (W + 1);
    }

    public int getWinner(ArrayList<ArrayList<Card>> Cards) {

        return getWinnerAsInt(Cards);

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
