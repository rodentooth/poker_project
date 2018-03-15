package main.Model.Networking;

import main.Model.Stack.Card;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ReceiveSocket {


    private final ServerSocket server;
    private Boolean stop = false;
    ArrayList<ArrayList<Card>> hands;

    public ReceiveSocket(int port, int backlog, String bindAddr, ArrayList<ArrayList<Card>> hands) throws IOException {
        server = new ServerSocket(port, backlog, InetAddress.getByName(bindAddr));

        this.hands = hands;


    }



    public void verbinde() {


        while (true) {
            if (stop)
                break;
            System.out.println("ok");
            Socket socket = null;
            try {

                socket = server.accept();
                reinRaus(socket);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null)
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    private void reinRaus(Socket socket) throws IOException {

        ObjectOutput out = null;


        //byte[] yourBytes = bos.toByteArray();


            BufferedReader rein = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            String s;

            while ((s = rein.readLine()) != null) {
                if (stop)
                    break;

                new ObjectOutputStream(socket.getOutputStream()).writeObject(hands);
                //raus.println(System.nanoTime() + "  that's a server message: " + s);
            /*if (!s.equals(""))
                break;*/
            }
        stop = true;

        }


    public void stop() {
        stop = true;
    }

}


