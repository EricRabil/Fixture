package com.ericrabil.fixture.api.config;

// TODO: Auto-generated Javadoc
/**
 * The Class DBConfig.
 */
public class DBConfig {
	
	/** The db user. */
	public String db_user;
	
	/** The db pass. */
	public String db_pass;
	
	/** The db ip. */
	public String db_ip;
	
	/** The db collation. */
	public String db_collation;

	/**
	 * Instantiates a new DB config.
	 */
	public DBConfig() {

	}

	/**
	 * Anything missing.
	 *
	 * @return true if the database IP is missing or the collation is missing
	 * @return false if all required values are present.
	 */
	public boolean anythingMissing() {
		if (db_ip == null
				|| db_collation == null) {
			return true;
		}
		if (db_ip == "" || db_collation == "") {
			return true;
		}
		return false;
	}
}
