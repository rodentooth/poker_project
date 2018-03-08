package main.Model.Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class getIPaddress {

    public String ip() {

        String ip = null; //you get the IP as a String

        URL whatismyip = null;
        BufferedReader in = null;

        try {
            whatismyip = new URL("http://checkip.amazonaws.com");

            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));


            ip = in.readLine();


        } catch (IOException e) {
            e.printStackTrace();

        }
        System.out.println(ip);

        return ip;
    }
}
