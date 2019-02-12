package src.Talk_v5_UDP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class NameServer {

    public static void main(String[] args) throws IOException {
        ArrayList<ServerObject> list = new ArrayList<>();
        ServerObject michael = new ServerObject("michael", "10.24.1.227"); //SKAL VÆRE ENS EGEN IP
        ServerObject torben = new ServerObject("torben", "10.24.3.206");
        list.add(michael);
        list.add(torben);



        DatagramSocket dnsSocket = new DatagramSocket(6790);
        String searchName="";
        while (true) {
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            System.out.println(searchName);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            dnsSocket.receive(receivePacket);
            searchName = new String(receivePacket.getData()).trim();
            System.out.println(searchName);


            InetAddress IPaddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            DatagramPacket sendPacket;


            switch (searchName) {
                case "list":
                    String string = list.toString();
                    sendData = string.getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPaddress, port);
                    dnsSocket.send(sendPacket);
                    break;
                case "add":
                    receivePacket = new DatagramPacket(receiveData, receiveData.length, IPaddress, port);
                    dnsSocket.receive(receivePacket);
                    String newIP = new String(receivePacket.getData()).trim();
                    System.out.println(newIP);
                    ServerObject object = new ServerObject(newIP, IPaddress.getHostAddress());
                    list.add(object);

                    sendData = object.toString().getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPaddress, port);
                    dnsSocket.send(sendPacket);
                    break;
                default:
                    string = findServer(searchName, list);
                    sendData = string.getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPaddress, port);
                    dnsSocket.send(sendPacket);
                    break;
            }

        }

    }


    public static String findServer(String name, ArrayList<ServerObject> list) {
        for (ServerObject server : list) {
            if (server.getName().equalsIgnoreCase(name)) {
                return server.getIp().trim();
            }
        }

        return null;
    }


}
