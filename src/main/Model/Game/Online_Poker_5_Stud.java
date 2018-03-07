package main.Model.Game;

import main.Model.Networking.PostRequest;
import main.Model.Networking.SendSocket;

public class Online_Poker_5_Stud {



    //search for opponents:
    //Create Sendsocket with server target to get possible opponents


    public Online_Poker_5_Stud() {
        PostRequest PR = new PostRequest();

        if (PR.send("http://apod.frozensparks.com/pokergame.php", "NAME", "IP")) {

        }


        SendSocket CS = new SendSocket("");


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
