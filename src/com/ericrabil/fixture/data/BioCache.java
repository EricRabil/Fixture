package com.ericrabil.fixture.data;

import java.util.HashMap;

import com.ericrabil.fixture.api.User;

public class BioCache {
	private HashMap<User, String> bios;
	
	public BioCache(){
		this.bios = new HashMap<User, String>();
	}
	public BioCache(HashMap<User, String> b){
		this.bios = b;
	}
	
	public String getBio(User u){
		return this.bios.get(u);
	}
	
	public HashMap<User, String> getCache(){
		return this.bios;
	}
	
	public void removeBio(User u){
		this.bios.remove(u);
	}
}
