package src.Talk_v4_Name;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) throws Exception{


        Socket nameSocket = new Socket("10.24.1.227", 6790);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream dataOutputStream = new DataOutputStream(nameSocket.getOutputStream());
        String string = input.readLine();
        System.out.println(string);
        dataOutputStream.writeBytes(string + "\n");
        BufferedReader fromNameServer = new BufferedReader(new InputStreamReader(nameSocket.getInputStream()));

        Socket clientSocket= new Socket(fromNameServer.readLine(), 6789);

        Writer wt = new Writer(clientSocket);
        Reader rt = new Reader(clientSocket);


        wt.start();
        rt.start();
        //wt.join();
        //rt.join();
        //clientSocket.close();

    }

}
