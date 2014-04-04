/**
 * 
 */
package com.doodleproject.dt.shared;

import java.io.Serializable;

/**
 * @author Cesco
 *
 */
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -265015353956007953L;
	private String name;
	private String username;
	private String password;
	private String mail;


	public User(){
		this.setName("empty");
		this.setUsername("empty");
		this.setPassword("empty");
		this.setMail("empty");	
	}

	public User(String name,String username,String password,String mail){
		this.setName(name);
		this.setUsername(username);
		this.setPassword(password);
		this.setMail(mail);	
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
