package com.ericrabil.fixture.database;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating IContext objects.
 */
public interface IContextFactory {
	
	/**
	 * Creates a new IContext object.
	 *
	 * @return the i context
	 * @throws DAOException the DAO exception
	 */
	public IContext createContext() throws DAOException;
}
