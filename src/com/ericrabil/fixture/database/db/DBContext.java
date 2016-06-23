package com.ericrabil.fixture.database.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.ericrabil.fixture.Fixture;
import com.ericrabil.fixture.database.IContext;

// TODO: Auto-generated Javadoc
/**
 * The Class DBContext.
 */
public class DBContext implements IContext {
	
	/** The fixture. */
	Fixture fixture;
	
	/** The connection. */
	Connection connection;

	/**
	 * Instantiates a new DB context.
	 *
	 * @param f the f
	 * @param c the c
	 */
	public DBContext(Fixture f, Connection c) {
		this.fixture = f;
		this.connection = c;
	}

	/* (non-Javadoc)
	 * @see com.ericrabil.fixture.database.IContext#close()
	 */
	@Override
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		return this.connection;
	}

	/* (non-Javadoc)
	 * @see com.ericrabil.fixture.database.IContext#getLoginDAO()
	 */
	@Override
	public DBLoginDAO getLoginDAO() {
		return new DBLoginDAO(this.fixture, this.connection);
	}
	
	@Override
	public DBDatabaseDAO getDatabaseDAO(){
		return new DBDatabaseDAO(this.fixture, this.connection);
	}

}
