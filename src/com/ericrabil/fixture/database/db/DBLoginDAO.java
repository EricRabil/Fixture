package com.ericrabil.fixture.database.db;

import java.sql.Connection;

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
		String stmt = "INSERT INTO `logins`(`username`, `ip`) VALUES ('" + this.f.getDBConfig().db_user + "', )";
		return false;
	}

}
