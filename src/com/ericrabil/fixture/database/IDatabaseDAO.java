package com.ericrabil.fixture.database;

import java.util.ArrayList;

import com.ericrabil.fixture.api.Database;
import com.ericrabil.fixture.api.Entry;

public interface IDatabaseDAO {
	public ArrayList<Entry> getEntries(Database db) throws DAOException;
	
	public ArrayList<Database> getDatabases() throws DAOException;
	
	public void addEntry(Entry e) throws DAOException;
	
	public void removeEntry(int i) throws DAOException;
	
	public void removeEntry(Entry e) throws DAOException;
}
