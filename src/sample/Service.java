package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Service implements Runnable {
    private Socket maSocket;

    Service(Socket s) {
        maSocket = s;
    }
    @Override
    public void run() {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(maSocket.getInputStream()));
            String qui = bf.readLine();
            System.out.println(qui + " : vient de se connecter");
            Thread.sleep(7000);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(maSocket.getOutputStream()));
            pw.println("j'ai bien reçu ton message " + qui);
            pw.close();
            bf.close();
            maSocket.close();
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
            e.printStackTrace();
            System.exit(1);
        }
    }
}
