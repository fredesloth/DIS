package Talk_v1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class TCPClient {

    public static void main(String[] args) throws Exception, IOException {

        String sentence = "";
        String modifiedSentence;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket;
        DataOutputStream outToServer;
        clientSocket = new Socket("10.24.3.206", 6789);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (!sentence.equals("close")) {
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
        }

        clientSocket.close();

    }

}
