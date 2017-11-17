package Types;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ClientMessage implements Serializable {
    private Client client;
    private List<Message> messages = new LinkedList<>();

    public ClientMessage(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        for (Message m: messages){
            if (this.messages.contains(m)) {
                int index = this.messages.indexOf(m);
                this.messages.get(index).setRead(true);
            } else {
                this.messages.add(m);
            }
        }
    }

    public void send(Message m){
        messages.add(m);
    }
}
