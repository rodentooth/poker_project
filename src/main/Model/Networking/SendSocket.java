package main.Model.Networking;

import main.Model.Stack.Card;

import java.io.*;
import java.net.InetAddress;
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
            socket = new Socket(host, 12700);

            OutputStream raus = socket.getOutputStream();

            socket.setSoTimeout(30000);
            socket.setKeepAlive(true);
            //socket.connect(address);

            ArrayList<ArrayList<Card>> aplaycards = new ArrayList<>();

            //  new ObjectOutputStream(raus).writeObject(aplaycards);


            ps = new PrintStream(raus, true);
            ps.println("Hallo Welt!");
            ps.println("Hallo Otto!");
            ps.flush();

            InputStream rein = socket.getInputStream();
            //System.out.println("verf\u00FCgbare Bytes: " + rein.available());
            BufferedReader buff = new BufferedReader(new InputStreamReader(rein));

            while (true) {
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

/*
        SendSocket CS = new SendSocket("178.197.232.180");
        send(CS.ps);
*/



        PostRequest PR = new PostRequest();

        getIPaddress p = new getIPaddress();

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

                SendSocket CS = new SendSocket(IP);
                send(CS.ps);


            } else {

                System.out.println("Create Host, Waiting for connections...");

                ReceiveSocket server = null;
                try {
                    server = new ReceiveSocket(12700, 5, InetAddress.getLocalHost().getHostAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                server.verbinde();

            }
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