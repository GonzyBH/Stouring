package com.junior.stouring;


public class User {

	private int Id;
	private String FirstName;
	private String LastName;
	private String Email;
	private String Password;
	
	public User(
			int pId,
			String pFirstName,
			String pLastName,
			String pEmail,
			String pPassword) 
	{
		super();
		this.Id = pId;
		this.FirstName = pFirstName;
		this.LastName = pLastName;
		this.Email = pEmail;
		this.Password = pPassword;
	}
	
	public int getId(){
		return Id;
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
