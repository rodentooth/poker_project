package main.Model.Networking;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PostRequest {
    public String postResult;


    public PostRequest() {

    }

    public boolean send(String host, String name, String ip) {
        //source code: https://www.coderblog.de/sending-data-from-java-to-php-via-a-post-request/
        try {
            // open a connection to the site
            URL url = new URL(host);
            URLConnection con = url.openConnection();
            // activate the output
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            // send your parameters to your site
            ps.print("NAME=" + name);
            ps.print("&IP=" + ip);

            // we have to get the input stream in order to actually send the request
            //con.getInputStream();


            InputStream rein = con.getInputStream();
            System.out.println("verf\u00FCgbare Bytes: " + rein.available());
            BufferedReader buff = new BufferedReader(new InputStreamReader(rein));

            while (buff.ready()) {
                //System.out.println(buff.readLine());

                while ((postResult = buff.readLine()) != null) {

                    System.out.println("FROM SERVER: " + postResult);

                }

            }

            // close the print stream
            ps.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
