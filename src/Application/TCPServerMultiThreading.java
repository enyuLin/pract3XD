package Application;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerMultiThreading {
	public static void main(String[] args) throws Exception{
		
		ServerSocket welcomeSocket = new ServerSocket(6789);
		
		while (true){
			Socket coonectionSocket = welcomeSocket.accept();
			new AttendPetition(coonectionSocket).start();
		}
				
	}

}
