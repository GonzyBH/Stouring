package com.junior.stouring;


public class User {


	private String FirstName;
	private String LastName;
	private String Email;
	private String Password;
	
	public User(
			String pFirstName,
			String pLastName,
			String pEmail,
			String pPassword) 
	{
		super();
		this.FirstName = pFirstName;
		this.LastName = pLastName;
		this.Email = pEmail;
		this.Password = pPassword;
	}
	

	
	public String getFirstName(){
		return FirstName;
	}
	
	public String getLastName(){
		return LastName;
	}
	
	public String getEmail(){
		return Email;
	}
	
	public String getPassword(){
		return Password;
	}
	
}
