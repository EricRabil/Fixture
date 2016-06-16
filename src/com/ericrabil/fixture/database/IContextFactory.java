package com.ericrabil.fixture.database;

public interface IContextFactory {
	public IContext createContext() throws DAOException;
}
