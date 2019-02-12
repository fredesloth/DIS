package src.Talk_v5_UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReaderThread extends Thread {

    private Socket socket;
    private String sentence = "";
    private BufferedReader inFromWriter;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.inFromWriter = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    @Override
    public void run() {
        while(!sentence.equalsIgnoreCase("close")){
            try {
                sentence = inFromWriter.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(sentence);
        }
    }
}

