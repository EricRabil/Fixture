package com.ericrabil.fixture.api;

import java.util.ArrayList;

import com.ericrabil.fixture.Fixture;
import com.ericrabil.fixture.database.DAOException;
import com.ericrabil.fixture.database.IContext;
import com.ericrabil.fixture.database.db.DBDatabaseDAO;

public class Database {
	private String name;
	
	private String sql_id;
	
	private int entries;
	private ArrayList<Entry> entryList = new ArrayList<Entry>();
	
	private Fixture f;
	
	private int uuid;
	
	public Database(Fixture fix, String sqlId, String niceName, ArrayList<Entry> values, int id){
		this.f = fix;
		this.sql_id = sqlId;
		this.name = niceName;
		this.entryList = values;
		if(this.entryList == null){
		this.entries = 0;
		}else{
		this.entries = this.entryList.size();
		}
		this.uuid = id;
	}
	
	public String getSQLID(){
		return this.sql_id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Entry> getEntries(){
		return this.entryList;
	}
	
	public void setEntries(ArrayList<Entry> entries){
		this.entryList = entries;
		this.entries = this.entryList.size();
	}
	
	public int totalEntries(){
		return this.entries;
	}
	
	public int getUUID(){
		return this.uuid;
	}
	
	public void addEntry(Entry e){
		try(IContext ctx = this.f.createContext()){
			DBDatabaseDAO dbdao = ctx.getDatabaseDAO();
		}catch(DAOException ex){
			ex.printStackTrace();
		}
	}
	
	public void removeEntry(Entry e){
		
	}
	
	private void initList(){
		try(IContext ctx = f.createContext()){
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
