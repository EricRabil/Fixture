package com.ericrabil.fixture.database.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ericrabil.fixture.Fixture;
import com.ericrabil.fixture.database.DAOException;
import com.ericrabil.fixture.database.ILoginDAO;

public class DBLoginDAO implements ILoginDAO{
	private Connection conn;
	private Fixture f;
	
	public DBLoginDAO(Fixture fixture, Connection c){
		this.conn = c;
		this.f = fixture;
	}

	@Override
	public boolean writePermissions() throws DAOException {
		String sql = "INSERT INTO `logins`(`username`, `ip`) VALUES (?, 'n/a')";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, this.f.getDBConfig().db_user);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}
