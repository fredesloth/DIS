package src.Talk_v5_UDP;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class NameServer {

    public static void main(String[] args) throws Exception {
        ArrayList<ServerObject> serverObjects = new ArrayList<ServerObject>();

        ServerObject michael = new ServerObject("michael", "10.24.1.227");
        ServerObject torben = new ServerObject("torben", "10.24.3.206");
        serverObjects.add(michael);
        serverObjects.add(torben);

        DatagramSocket socket = new DatagramSocket(6790);
        byte[] sendData = new byte[1024];
        byte[] recieveData = new byte[1024];
        Socket connectionSocket = null;
        String name = "";

        while (true){
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            name = new String(receivePacket.getData()).trim();
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            DatagramPacket sendPacket = null;

            switch (name){
                case "list":
                    String string = serverObjects.toString();
                    sendData = string.getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port;
                    socket.send(sendPacket);
                    break;
                case "add":
                    DatagramPacket recieveNamePacket = new DatagramPacket(recieveData, recieveData.length);
                    String myIP = new String(recieveNamePacket.getData()).trim();
                    ServerObject serverObject = new ServerObject(myIP, IPAddress.getHostAddress());
                    serverObjects.add(serverObject);

                    sendData = serverObject.toString().getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    socket.send(sendPacket);
                    break;

                default:
                    sendData = findServer(name, serverObjects).getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    socket.send(sendPacket);
                    break;
            }

        }


    }

    private static String findServer(String name, ArrayList<ServerObject> servers) {
        for (ServerObject o : servers) {
            if (o.getName().equalsIgnoreCase(name)) {
                return o.getIp();
            }
        }
        return null;
    }


}
dsafdsa