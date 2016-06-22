package com.ericrabil.fixture.database;

import com.ericrabil.fixture.database.db.DBLoginDAO;

public interface IContext extends AutoCloseable {
	@Override
	public void close();
	
	public DBLoginDAO getLoginDAO();
}
