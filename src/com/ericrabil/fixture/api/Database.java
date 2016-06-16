package com.ericrabil.fixture.api;

import com.ericrabil.fixture.Fixture;

public class Database {
	private Fixture fixture;
	
	private String sql_db;
	private String sql_user;
	private String sql_pass;
	
	private DBType type;
	
	public Database(Fixture f, String sqldb, String sqlu, String sqlp, DBType t){
		this.fixture = f;
		this.sql_db = sqldb;
		this.sql_user = sqlu;
		this.sql_pass = sqlp;
		this.type = t;
	}
}
