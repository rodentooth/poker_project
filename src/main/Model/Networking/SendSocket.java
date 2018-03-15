package main.Model.Networking;

import main.Model.Stack.Card;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class SendSocket {

    static ArrayList<ArrayList<Card>> hands;

    //Inspiration from tutorial:  https://javabeginners.de/Netzwerk/Socketverbindung.php

    public PrintStream ps;

    int i = 0;

    public SendSocket(String host, ArrayList<String> savedNamed) {
        Socket socket = null;
        try {
            socket = new Socket(host, 12700);

            OutputStream raus = socket.getOutputStream();

            socket.setSoTimeout(30000);
            socket.setKeepAlive(true);


            //


            ps = new PrintStream(raus, true);
            ps.println(savedNamed.get(0));
            ps.flush();

            InputStream rein = socket.getInputStream();

            ObjectInputStream ois = new ObjectInputStream(rein);


            hands = new ArrayList<>();

            while (true) {

                hands = (ArrayList) ois.readObject();

                if (hands != null) {
                    ois.close();
                    socket.close();
                    break;
                }
            }

        } catch (UnknownHostException e) {
            System.out.println("Unknown Host...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOProbleme...");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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


    public ArrayList<ArrayList<Card>> getOnlineCards() {

        return hands;
    }


}