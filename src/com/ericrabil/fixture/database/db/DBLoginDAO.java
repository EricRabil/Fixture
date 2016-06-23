package com.ericrabil.fixture.database.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ericrabil.fixture.Fixture;
import com.ericrabil.fixture.database.DAOException;
import com.ericrabil.fixture.database.ILoginDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class DBLoginDAO.
 */
public class DBLoginDAO implements ILoginDAO{
	
	/** The conn. */
	private Connection conn;
	
	/** The f. */
	private Fixture f;
	
	/** The exc. */
	private SQLException exc;
	
	/**
	 * Instantiates a new DB login DAO.
	 *
	 * @param fixture the fixture
	 * @param c the c
	 */
	public DBLoginDAO(Fixture fixture, Connection c){
		this.conn = c;
		this.f = fixture;
	}

	/* (non-Javadoc)
	 * @see com.ericrabil.fixture.database.ILoginDAO#writePermissions()
	 */
	@Override
	public boolean writePermissions() throws DAOException {
		String sql = "INSERT INTO `logins`(`username`, `ip`) VALUES (?, 'n/a')";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, this.f.getDBConfig().db_user);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			this.exc = e;
			return false;
		}
	}
	
	/**
	 * Gets the exception.
	 *
	 * @return the exception
	 */
	public SQLException getException(){
		SQLException ex = this.exc;
		this.exc = null;
		return ex;
	}

}
