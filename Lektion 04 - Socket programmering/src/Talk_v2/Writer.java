package src.Talk_v2;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Writer extends Thread {
    private BufferedReader input;
    private Socket socket;
    private String sentence = "";
    private DataOutputStream dataOutputStream;

    public Writer(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(System.in));
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while(!sentence.equalsIgnoreCase("close")){
            try {
                sentence = input.readLine();
                dataOutputStream.writeBytes(sentence + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
