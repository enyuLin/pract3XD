package Application;

import Types.*;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;


public class AttendPetition extends Thread {
    private Socket socket;
    private Server server;


    public AttendPetition(Socket s) {
        this.socket = s;
        server = new Server();
    }

    public void run() {
        try {

			String optionStr;
			Message message;
			int option;
			/*
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream())); // llegir del socket
			DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
			*/
            ObjectInputStream inFromClient = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outToClient = new ObjectOutputStream(socket.getOutputStream());
			// connectionSocket.getInputStream()
            while (true) {
                optionStr = (String) inFromClient.readObject(); // punt bloquejant, server esperant q li envia alguna cosa
                System.out.println("Rebut: " + optionStr);
                // Test of server
                // outToClient.writeBytes(optionStr);
                option = Integer.parseInt(optionStr);
                System.out.println(option);
                //---------------------
                switch (option) {
                    case 1:
                        Client client;
                        client = (Client) inFromClient.readObject();
                        System.out.println("Received:" + client);
                        boolean isRegOK = server.register(client);
                        System.out.println("bool"+isRegOK);
                        String registContent = (isRegOK) ? "Registration accepted!" : "You are already registered.";
                        Message okRegister = new Message(registContent, "Server", client.getUser());
                        outToClient.writeObject(okRegister);
                        break;
                    case 2:
                        List<String> clients = server.showUsers();
                        outToClient.writeObject(clients);
                        message = (Message) inFromClient.readObject();
                        System.out.println("mess: " + message.getContent());
                        System.out.println(message);
                        server.addMessage(message);
                        break;
                    case 3:
                        String name = (String) inFromClient.readObject();
                        List<Message> msgNoReads = server.getMessages(name);
                        System.out.println(msgNoReads);
                        outToClient.writeObject(msgNoReads);
                        List<Message> msgReads = (List<Message>) inFromClient.readObject();
                        server.updateMessages(name, msgReads);

                        break;

                    case 4:

                        break;
                }
            }



            //----------------------

            //socket.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

}
