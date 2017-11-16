package Application;

import Types.Client;
import Types.Message;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class TCPClient {


    public static void main(String argv[]) throws Exception {
        Client client;
        String name = "", password, info, destination;

        /*
        String sentence;
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		// Part de comunicació
		Socket clientSocket = new Socket("localhost", 6789);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); // per escriure, obtenir els canals
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n'); // li enviem al servidor
		modifiedSentence = inFromServer.readLine(); // esperant el servidor
		System.out.println("FROM SERVER: " + modifiedSentence);
		*/
        // --------------
        Socket clientSocket = new Socket("localhost", 6789);
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        ObjectOutputStream sendToServer = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
        /*
        BufferedReader inFromServer2 = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        DataOutputStream optionToServer = new DataOutputStream(clientSocket.getOutputStream());
        */
        Message message;
        String optionStr, optionStr2;
        int option = 10;
        while (option != 0) {
            showMenu();
            optionStr = inFromUser.readLine().trim();
            sendToServer.writeObject((String) optionStr);
            /*
            optionStr2 = (String)inFromServer.readObject();
            System.out.println(optionStr2);
            */
            option = Integer.parseInt(optionStr);
            System.out.println("Rebut:" + option);
            switch (option) {
                case 1:
                    // Register for  Server
                    System.out.println("Please enter a user name:");
                    name = inFromUser.readLine();
                    System.out.println("Please enter a password: ");
                    password = inFromUser.readLine();
                    client = new Client(name, password);
                    System.out.println(client);
                    sendToServer.writeObject(client);
                    message = (Message) inFromServer.readObject();
                    System.out.println(message);

                    break;
                case 2:
                    List<String> users = (List<String>) inFromServer.readObject();
                    System.out.println("Users: " + users);
                    System.out.println("Please choose a user to send the message:");
                    destination = inFromUser.readLine().trim();
                    System.out.println("Please write the message:");
                    info = inFromUser.readLine().trim();
                    message = new Message(info, name, destination);
                    sendToServer.writeObject(message);
                    break;
                case 3:
                    sendToServer.writeObject(name);
                    List<Message> msgNoReads =  (List<Message>) inFromServer.readObject();
                    System.out.println(msgNoReads);
                    msgNoReads.forEach(a->a.setRead(true));
                    sendToServer.writeObject(msgNoReads);
                    break;

                case 4:

                    break;

            }


        }


        //----------------
        clientSocket.close();
    }

    public static void showMenu() {
        System.out.println("\n--------- MENU ---------");
        System.out.println("\t 1. Register for  Server.");
        System.out.println("\t 2. Send a message.");
        System.out.println("\t 3. Read messages.");
        System.out.println("\t 4. Unregister from Server.  ");
        System.out.println("\t 0. Exit.");
        System.out.println("---------------------");
        System.out.println(" Please choose a option:");
    }
}