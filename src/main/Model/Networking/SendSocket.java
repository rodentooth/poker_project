package main.Model.Networking;

import main.Model.Stack.Card;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class SendSocket {

    ArrayList<ArrayList<Card>> hands;

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

            //


            ps = new PrintStream(raus, true);
            ps.println("Hallo Welt!");
            ps.println("Hallo Otto!");
            ps.flush();

            InputStream rein = socket.getInputStream();
            //System.out.println("verf\u00FCgbare Bytes: " + rein.available());
            //BufferedReader buff = new BufferedReader(new InputStreamReader(rein));


            //FileInputStream fis = new FileInputStream();
            ObjectInputStream ois = new ObjectInputStream(rein);




            while (true) {
                //System.out.println(buff.readLine());

                hands = new ArrayList<>();


                //int i = ois.readInt();
                hands = (ArrayList) ois.readObject();
                //Date date = (Date) ois.readObject();

                ois.close();

                /*String modifiedSentence;
                while ((modifiedSentence = buff.readLine()) != null) {

                    hands.add(modifiedSentence);
                    System.out.println("FROM SERVER: " + modifiedSentence);

                }
*/
/*
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ObjectInput in = null;
                try {
                    in = new ObjectInputStream(bis);
                    hands = (ArrayList<ArrayList<Card>>) in.readObject();
                } finally {
                    try {
                        if (in != null) {
                            in.close();
                        }
                    } catch (IOException ex) {
                        // ignore close exception
                    }
                }
*/

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

    //todo test method: delete
    public static void main(String[] args) {

/*
        SendSocket CS = new SendSocket("178.197.232.180");
        send(CS.ps);
*/


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