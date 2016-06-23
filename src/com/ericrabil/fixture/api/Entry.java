package com.ericrabil.fixture.api;

public class Entry {
	private String key;
	private String value;
	
	private int db_uuid;
	
	private int uuid;
	
	public Entry(String k, String v, int id, int db_uuid){
		this.key = k;
		this.value = v;
		this.uuid = id;
		this.db_uuid = db_uuid;
	}
	
	public String getKey(){
		return this.key;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public void setKey(String k){
		this.key = k;
	}
	
	public void setValue(String v){
		this.value = v;
	}
	
	public int getDBUUID(){
		return this.db_uuid;
	}
	
	public int getUUID(){
		return this.uuid;
	}
}
