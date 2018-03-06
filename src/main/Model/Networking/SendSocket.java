package main.Model.Networking;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendSocket {

    //Inspiration from tutorial:  https://javabeginners.de/Netzwerk/Socketverbindung.php

    public PrintStream ps;

    int i = 0;

    public SendSocket() {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 3141);

            OutputStream raus = socket.getOutputStream();

            socket.setSoTimeout(30000);
            socket.setKeepAlive(true);
            //socket.connect(address);


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

                    send("hallo ");

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


        SendSocket CS = new SendSocket();

    }

    public void send(String s) {

        if (ps != null)
            ps.println(s + " " + (i++));

    }


}