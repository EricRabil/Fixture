package com.ericrabil.fixture.api;

import java.util.UUID;

import com.ericrabil.fixture.api.exception.UUIDSecurityException;

public class Entry {
	private String key;
	private String value;
	
	private int db_uuid;
	
	private UUID uuid = null;
	
	public Entry(String k, String v, UUID id, int db_uuid){
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
	
	public UUID getUUID(){
		return this.uuid;
	}
	
	public void setUUID(UUID u) throws UUIDSecurityException{
		if(uuid == null){
			this.uuid = u;
		}else{
			//UUID cannot be changed after it has been set for security and stability purposes.
			throw new UUIDSecurityException("The UUID for Entry ID " + db_uuid + " cannot be changed after it has been set.");
		}
	}
}
