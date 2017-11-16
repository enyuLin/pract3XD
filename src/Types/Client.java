package Types;


import java.io.Serializable;

public class Client implements Serializable{
	private  String user;
	private  String passaword;
	
	public  String getPassaword() {
		return passaword;
	}

	public  void setPassaword(String passaword) {
		passaword = passaword;
	}

	public  String getUser() {
		return user;
	}

	public Client(String user, String passaword) {
		this.user = user;
		this.passaword = passaword;
	}

	@Override
	public String toString() {
		return "Client{" +
				"user='" + user + '\'' +
				", passaword='" + passaword + '\'' +
				'}';
	}
}
