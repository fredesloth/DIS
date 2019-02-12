package src.Talk_v5_UDP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;


public class TCPClientTalk {

    public static void main(String[] args) throws Exception, IOException {


        DatagramSocket nameSocket = new DatagramSocket();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream stream;

        InetAddress IPAddress = InetAddress.getByName("10.24.1.227");
        DatagramPacket sendPacket;
        DatagramPacket receivePacket = null;
        String string ="";

        boolean serverFound = false;
        while (!serverFound) {
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            string = input.readLine();
            sendData = string.getBytes();
            sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, 6790);
            nameSocket.send(sendPacket);



            switch (string) {
                case "list":
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    nameSocket.receive(receivePacket);
                    string = new String(receivePacket.getData()).trim();
                    System.out.println(string);
                    break;
                case "add":
                    System.out.println("Write name here");
                    string = input.readLine().trim();
                    sendData = string.getBytes();
                    sendPacket = new DatagramPacket(sendData,
                            sendData.length, IPAddress, 6790);
                    nameSocket.send(sendPacket);

                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    nameSocket.receive(receivePacket);
                    string = new String(receivePacket.getData()).trim();
                    System.out.println(string);
                    break;
                default:
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    nameSocket.receive(receivePacket);
                    string = new String(receivePacket.getData()).trim();
                    serverFound = true;
                    break;
            }
        }


        Socket clientSocket = new Socket(string, 6789);
        WriterThread writer = new WriterThread(clientSocket);
        ReaderThread reader = new ReaderThread(clientSocket);

        writer.start();
        reader.start();

    }

}
