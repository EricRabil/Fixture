package com.ericrabil.fixture.database.db;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

import com.ericrabil.fixture.Fixture;
import com.ericrabil.fixture.database.DAOException;
import com.ericrabil.fixture.database.IContext;
import com.ericrabil.fixture.database.IContextFactory;

public class DBContextFactory implements IContextFactory {

	private BasicDataSource ds;
	private Map<String, LoggingConnection.LogEntry> queryLog;
	private Fixture fixture;

	public DBContextFactory(Fixture instance) {
		queryLog = new HashMap<>();

		String driver = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driver).newInstance();
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);
		} catch (IllegalAccessException ex) {
			throw new RuntimeException(ex);
		} catch (InstantiationException ex) {
			throw new RuntimeException(ex);
		}
		String user = fixture.getDBConfig().db_user;
		String password = fixture.getDBConfig().db_pass;
		String url = fixture.getDBConfig().db_ip;

		ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setMaxActive(5);
		ds.setMaxIdle(5);
		ds.setDefaultAutoCommit(true);

		this.fixture = instance;
	}

	@Override
	public IContext createContext() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
