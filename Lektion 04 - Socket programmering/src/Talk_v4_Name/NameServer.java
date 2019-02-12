package src.Talk_v4_Name;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class NameServer {

    public static void main(String[] args) throws Exception {
        ArrayList<ServerObject> serverObjects = new ArrayList<ServerObject>();

        ServerObject michael = new ServerObject("michael", "10.24.1.227");
        ServerObject torben = new ServerObject("torben", "10.24.3.206");
        serverObjects.add(michael);
        serverObjects.add(torben);

        ServerSocket socket = new ServerSocket(6790);
        Socket connectionSocket;
        BufferedReader input;
        DataOutputStream dataOutputStream;

        String name = "";
        while (true){
            connectionSocket = socket.accept();
            input = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            dataOutputStream = new DataOutputStream(connectionSocket.getOutputStream());
            name = input.readLine();
            dataOutputStream.writeBytes(findServer(name, serverObjects) + "\n");

            switch (name){
                case "list":
                    String string = serverObjects.toString();
                    dataOutputStream.writeBytes(string + "\n");
                    break;
                case "add":
                    socket.getInetAddress();
                    String ip = socket.getInetAddress().toString();
                    name = input.readLine();
                    serverObjects.add(new ServerObject(name, ip));
                    dataOutputStream.writeBytes(serverObjects.toString());
                    break;

                default:
                    dataOutputStream.writeBytes(findServer(name, serverObjects) + "\n");
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
