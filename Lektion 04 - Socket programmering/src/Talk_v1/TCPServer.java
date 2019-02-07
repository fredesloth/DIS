package Talk_v1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class TCPServer {

	public static void main(String[] args) throws Exception {

		String clientSentence = "";
		String reply = "";

		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));


		Socket connectionSocket = welcomeSocket.accept();

		while(!clientSentence.equals("close")){
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			clientSentence = inFromClient.readLine();
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			System.out.println(capitalizedSentence);
			outToClient.writeBytes(capitalizedSentence);
			if(!clientSentence.isEmpty()){
				System.out.println(clientSentence);
			}

			reply = inFromServer.readLine();
			outToClient.writeBytes(reply + "\n");
		}

	}

}
