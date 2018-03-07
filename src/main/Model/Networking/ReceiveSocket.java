package main.Model.Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveSocket {


    private final ServerSocket server;
    private Boolean stop = false;

    public ReceiveSocket(int port) throws IOException {
        server = new ServerSocket(port);


    }

    //todo test method: delete
    public static void main(String[] args) {

        ReceiveSocket server = null;
        try {
            server = new ReceiveSocket(3141);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.verbinde();


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
        BufferedReader rein = new BufferedReader(new InputStreamReader(socket
                .getInputStream()));
        PrintStream raus = new PrintStream(socket.getOutputStream());
        String s;

        while ((s = rein.readLine()) != null) {
            if (stop)
                break;

            raus.println(System.nanoTime() + "  that's a server message: " + s);
            /*if (!s.equals(""))
                break;*/
        }

    }

    public void stop() {
        stop = true;
    }

}


