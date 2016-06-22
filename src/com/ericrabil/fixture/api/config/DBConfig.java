package com.ericrabil.fixture.api.config;

public class DBConfig {
	public String db_user;
	public String db_pass;
	public String db_ip;
	public String db_collation;

	public DBConfig() {

	}

	public boolean anythingMissing() {
		if (db_user == null || db_pass == null || db_ip == null
				|| db_collation == null) {
			return true;
		}
		if (db_user == "" || db_ip == "" || db_collation == "") {
			return true;
		}
		return false;
	}
}
