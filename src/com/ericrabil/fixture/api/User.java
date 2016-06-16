package com.ericrabil.fixture.api;

import java.util.ArrayList;
import java.util.HashMap;

import com.ericrabil.fixture.Fixture;

public class User {
	private Fixture fixture;
	
	private String username;
	
	private ArrayList<Settings> settings = new ArrayList<Settings>();
	
	private Rank permission;
	private HashMap<ExtraValues, String> extra = new HashMap<ExtraValues, String>();
	
	public User(Fixture fixture, String user){
		this.username = user;
		//Build user profile
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getBio(){
		//Compatibility and ease of access
		return fixture.getBioCache().getBio(this);
	}
}
