package Types;

import java.io.Serializable;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  boolean isRead;
	private String content;
	private String from;
	private String destination;
	
	public Message(String content, String from, String destination) {
		this.content = content;
		this.from = from;
		this.destination = destination;
		isRead = false;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "Message [isRead=" + isRead + ", content=" + content + ",\n"
				+ " from=" + from + ", destination=" + destination
				+ "]";
	}
	
	
	
	
}
