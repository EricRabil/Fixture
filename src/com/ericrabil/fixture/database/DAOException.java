package com.ericrabil.fixture.database;

// TODO: Auto-generated Javadoc
/**
 * The Class DAOException.
 */
public class DAOException extends Exception {
	
	/** The query. */
	private String query = null;

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param message the message
	 * @param query the query
	 */
	public DAOException(String message, String query) {
		super(message);

		this.query = query;
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param query the query
	 * @param e the e
	 */
	public DAOException(String query, Throwable e) {
		super(e);

		this.query = query;
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param e the e
	 */
	public DAOException(Throwable e) {
		super(e);
	}

	/**
	 * Gets the query.
	 *
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}
}