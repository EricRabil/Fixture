package com.ericrabil.fixture.api.exception;

public class DatabaseExistsException extends Exception{
	
	private String name;
	private String nodename;
	
	public DatabaseExistsException(String n, String nn){
		super("A database already exists with the name " + n + " or node-name " + nn);
		this.name = n;
		this.nodename = nn;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getNodeName(){
		return this.nodename;
	}

}
