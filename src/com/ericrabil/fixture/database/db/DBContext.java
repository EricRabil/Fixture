package com.ericrabil.fixture.database.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.ericrabil.fixture.Fixture;
import com.ericrabil.fixture.database.IContext;

public class DBContext implements IContext {
	Fixture fixture;
	Connection connection;

	public DBContext(Fixture f, Connection c) {
		this.fixture = f;
		this.connection = c;
	}

	@Override
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	@Override
	public DBLoginDAO getLoginDAO() {
		return new DBLoginDAO(this.fixture, this.connection);
	}

}
