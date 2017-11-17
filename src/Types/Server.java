package Types;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Server implements Serializable {
    private Map<String,Client> users;
    private Map<String, ClientMessage> server;

    public Server() {
        users = new HashMap<>();
        this.server = new HashMap<>();
    }

    
    public Map<String, ClientMessage> getServer() {
        return server;
    }

    // Method for a user to register in Server ("Donar-se d'alta al servidor")
    public boolean register(Client c){
        if (!users.containsKey(c.getUser())) {
            users.put(c.getUser(), c);
            server.put(c.getUser(),new ClientMessage(c));
            return true;
        } else
            return false;
    }

    // Method for a user to unregister in Server ("Donar-se de baixa al servidor")
    public void unregister(Client c){
        if (users.containsKey(c.getUser()))
            users.remove(c.getUser(), c);
    }

    public boolean verify(Client c){
        return true;
    }

    public List<String> showUsers(){
        List<String> result = new LinkedList<>();
        for (String s: users.keySet())
            result.add(s);
        return result;
    }

    public void addMessage(Message m){
        server.get(m.getDestination()).send(m);
    }

    public List<Message> getMessages(String name){
        return server.get(name).getMessages().stream().filter(m -> m.isRead() == false).collect(Collectors.toList());

    }

    public void updateMessages(String name, List<Message> messages){
        server.get(name).setMessages(messages);
    }



}
