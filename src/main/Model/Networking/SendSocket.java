package main.Model.Networking;

import main.Model.Stack.Card;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class SendSocket {

    //Inspiration from tutorial:  https://javabeginners.de/Netzwerk/Socketverbindung.php

    public PrintStream ps;

    int i = 0;

    public SendSocket(String host) {
        Socket socket = null;
        try {
            socket = new Socket(host, 3141);

            OutputStream raus = socket.getOutputStream();

            socket.setSoTimeout(30000);
            socket.setKeepAlive(true);
            //socket.connect(address);

            ArrayList<ArrayList<Card>> aplaycards = new ArrayList<>();

            new ObjectOutputStream(raus).writeObject(aplaycards);


            ps = new PrintStream(raus, true);
            ps.println("Hallo Welt!");
            ps.println("Hallo Otto!");


            InputStream rein = socket.getInputStream();
            System.out.println("verf\u00FCgbare Bytes: " + rein.available());
            BufferedReader buff = new BufferedReader(new InputStreamReader(rein));

            while (buff.ready()) {
                //System.out.println(buff.readLine());

                String modifiedSentence;
                while ((modifiedSentence = buff.readLine()) != null) {

                    System.out.println("FROM SERVER: " + modifiedSentence);

                }

            }

        } catch (UnknownHostException e) {
            System.out.println("Unknown Host...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOProbleme...");
            e.printStackTrace();
        } finally {
            if (socket != null)
                try {
                    socket.close();
                    System.out.println("Socket geschlossen...");
                } catch (IOException e) {
                    System.out.println("Socket nicht zu schliessen...");
                    e.printStackTrace();
                }
        }
    }

    //todo test method: delete
    public static void main(String[] args) {


        //SendSocket CS = new SendSocket("localhost");
        //send(CS.ps);


        PostRequest PR = new PostRequest();

        if (PR.send("http://apod.frozensparks.com/pokergame.php", "Fritz", "12323324")) {
            System.out.println("THIS IS TEH POST RESULT:  " + PR.postResult);
        }
    }

    public static void send(PrintStream ps) {


        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a text: ");

//once finished

        while (reader.hasNextLine()) {

            if (ps != null)
                ps.println(reader.next());


        }
        reader.close();

    }


}