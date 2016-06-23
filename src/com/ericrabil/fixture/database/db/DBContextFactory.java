package com.ericrabil.fixture.database.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

import com.ericrabil.fixture.Fixture;
import com.ericrabil.fixture.api.config.DBConfig;
import com.ericrabil.fixture.database.DAOException;
import com.ericrabil.fixture.database.IContext;
import com.ericrabil.fixture.database.IContextFactory;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating DBContext objects.
 */
public class DBContextFactory implements IContextFactory {

	/** The ds. */
	private BasicDataSource ds;
	
	/** The query log. */
	private Map<String, LoggingConnection.LogEntry> queryLog;
	
	/** The fixture. */
	private Fixture fixture;

	/**
	 * Instantiates a new DB context factory.
	 *
	 * @param instance the instance
	 * @param config the config
	 */
	public DBContextFactory(Fixture instance, DBConfig config) {
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
		String user = config.db_user;
		String password = config.db_pass;
		String url = config.db_ip;
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

	/* (non-Javadoc)
	 * @see com.ericrabil.fixture.database.IContextFactory#createContext()
	 */
	@Override
	public IContext createContext() throws DAOException {
		try {
			// It's the responsibility of the context to make sure that the
			// connection is correctly closed
			// System.out.println(ds.toString());
			Connection conn = ds.getConnection();
			try (Statement stmt = conn.createStatement()) {
				stmt.execute("SET NAMES latin1");
			}

			return new DBContext(this.fixture, new LoggingConnection(conn, queryLog));
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
