package com.ericrabil.fixture.database;

import com.ericrabil.fixture.database.db.DBDatabaseDAO;
import com.ericrabil.fixture.database.db.DBLoginDAO;

// TODO: Auto-generated Javadoc
/**
 * The Interface IContext.
 */
public interface IContext extends AutoCloseable {
	
	/* (non-Javadoc)
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close();
	
	/**
	 * Gets the login DAO.
	 *
	 * @return the login DAO
	 */
	public DBLoginDAO getLoginDAO();
	
	public DBDatabaseDAO getDatabaseDAO();
}
