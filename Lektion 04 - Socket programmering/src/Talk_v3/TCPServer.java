package src.Talk_v3;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) throws Exception {
		ServerSocket welocmeSocket = new ServerSocket(6789);
		Socket connectionSocket = welocmeSocket.accept();

		Writer writer = new Writer(connectionSocket);
		Reader reader = new Reader(connectionSocket);

		reader.start();
		writer.start();
	}

}
