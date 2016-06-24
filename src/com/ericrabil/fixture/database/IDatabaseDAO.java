package com.ericrabil.fixture.database;

import java.util.ArrayList;

import com.ericrabil.fixture.api.Database;
import com.ericrabil.fixture.api.Entry;

public interface IDatabaseDAO {
	public ArrayList<Entry> getEntries(Database db) throws DAOException;
	
	public ArrayList<Database> getDatabases() throws DAOException;
	
	public void removeEntry(Entry e) throws DAOException;

	public void addEntry(String key, String value, Database db) throws DAOException;

	public void updateEntry(Entry e, String key, String value) throws DAOException;

	public Database createDatabase(String name);
}
