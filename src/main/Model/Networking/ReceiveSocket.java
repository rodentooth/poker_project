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

    //todo test method: delete
    public static void main(String[] args) {
/*
        ReceiveSocket server = null;
        try {
            server = new ReceiveSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.verbinde();

*/
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

        Object o = hands;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(hands);
            out.flush();
            //byte[] yourBytes = bos.toByteArray();


            BufferedReader rein = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            PrintStream raus = new PrintStream(socket.getOutputStream());
            String s;

            while ((s = rein.readLine()) != null) {
                if (stop)
                    break;

                new ObjectOutputStream(raus).writeObject(hands);
                //raus.println(System.nanoTime() + "  that's a server message: " + s);
            /*if (!s.equals(""))
                break;*/
            }


        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }


    }

    public void stop() {
        stop = true;
    }

}


