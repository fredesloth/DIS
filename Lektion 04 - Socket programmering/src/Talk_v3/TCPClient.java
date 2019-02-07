package src.Talk_v3;

import java.io.IOException;
import java.net.Socket;


public class TCPClient {

    public static void main(String[] args) throws Exception, IOException {

        Socket clientSocket = new Socket("10.24.3.206", 6789);


        Writer writer = new Writer(clientSocket);
        Reader reader = new Reader(clientSocket);

        writer.start();
        reader.start();

    }

}
