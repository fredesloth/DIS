package src.Talk_v5_UDP;

import src.Talk_v4_Name.Writer;

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


        boolean serverFound = false;
        while(!serverFound){
            nameSocket = new Socket("10.24.3.206", 6790);
            dataOutputStream = new DataOutputStream(nameSocket.getOutputStream());
            String string = input.readLine();
            fromNameServer = new BufferedReader(new InputStreamReader(nameSocket.getInputStream()));
            dataOutputStream.writeBytes(string + "\n");
            fromNameServer =new BufferedReader(new InputStreamReader(nameSocket.getInputStream()));
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
        src.Talk_v4_Name.Writer wt = new Writer(clientSocket);
        Reader rt = new Reader(clientSocket);
        wt.start();
        rt.start();
        //wt.join();
        //rt.join();
        //clientSocket.close();

    }

}
