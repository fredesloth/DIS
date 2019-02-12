package src.Talk_v4_Name;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) throws Exception{


        Socket nameSocket = null;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream dataOutputStream = null;
        //System.out.println(string);
        //dataOutputStream.writeBytes(string + "\n");
        BufferedReader fromNameServer = null;

        fromNameServer = new BufferedReader(new InputStreamReader(nameSocket.getInputStream()));

        boolean serverFound = false;
        while(!serverFound){
            String string = input.readLine();
            dataOutputStream.writeBytes(string + "\n");
            switch (string){
                case "list":
                    System.out.println(fromNameServer.readLine());
                break;

                case "add":
                    System.out.println("Intast et navn: ");
                    String str = input.readLine();
                    dataOutputStream.writeBytes(str + "\n");
                    System.out.println(fromNameServer.readLine());
                break;

                default:
                    serverFound = true;
                break;
            }
        }


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
