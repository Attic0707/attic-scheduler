package model;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7351729135012380019L;
	
	private static int count = 0;
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private char[] password;
	
	public User(String firstName, String lastName, String userName, char[] password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.id = count;
		count++;
	}
	
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getUserName() {
		return userName;
	}
	public char[] getPassword() {
		return password;
	}
	public int getUserCount() {
		return count;
	}
}