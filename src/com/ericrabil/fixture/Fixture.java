package com.ericrabil.fixture;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.ericrabil.fixture.api.Releases;
import com.ericrabil.fixture.api.Usage;
import com.ericrabil.fixture.api.config.DBConfig;
import com.ericrabil.fixture.database.DAOException;
import com.ericrabil.fixture.database.IContext;
import com.ericrabil.fixture.database.IContextFactory;
import com.ericrabil.fixture.database.db.DBContextFactory;

import net.sourceforge.yamlbeans.YamlException;
import net.sourceforge.yamlbeans.YamlReader;
import net.sourceforge.yamlbeans.YamlWriter;

public class Fixture {
	private String name = "Fixture";
	private String version = "1.0.0";
	private Releases type = Releases.ALPHA;
	private String description = "Fixture is a program used for data storage and organization.";

	private DBConfig dbConfig;

	// In Milliseconds
	private int startTime;

	private IContextFactory ctfact;

	private Usage use;

	public Fixture(Usage use) {
		System.out.println("Initializing " + name + " " + version + " " + type.toString().toLowerCase());
		this.use = use;
		this.startTime = (int) System.currentTimeMillis();
		try {
			System.out.println("Instantiating file reader");
			YamlReader dbReader = new YamlReader(new FileReader("db.yml"));
			System.out.println("Reading SQL config");
			DBConfig conf = dbReader.read(DBConfig.class);
			if (conf == null || conf.anythingMissing()) {
				System.out.println("The SQL configuration was invalid; Fixture.yml will be overwritten");
				YamlWriter writer = new YamlWriter(new FileWriter("db.yml"));
				DBConfig sample = new DBConfig();
				sample.db_collation = "utf8";
				sample.db_ip = "0.0.0.0";
				sample.db_name = "fixture";
				sample.db_pass = "fixpass";
				sample.db_port = "3306";
				sample.db_user = "fixuser";
				writer.write(sample);
				writer.close();
				System.out.println("Please reconfigure Fixture DB and try again.");
				System.exit(23);
			} else {
				System.out.println("SQL config is properly formatted; proceeding");
				this.dbConfig = conf;
			}
			dbReader.close();
			this.ctfact = new DBContextFactory(this);
		} catch (YamlException | IOException e) {
			e.printStackTrace();
		}
	}

	public void onDisable() {

	}

	public IContext createContext() throws DAOException {
		return this.ctfact.createContext();
	}

	public IContextFactory getContextFactory() {
		return this.ctfact;
	}

	public int startTimeMillis() {
		return this.startTime;
	}

	public DBConfig getDBConfig() {
		return this.dbConfig;
	}

	public String getVersion() {
		return this.version;
	}

	public String getName() {
		return this.name;
	}

	public Releases getReleaseType() {
		return this.type;
	}

	public String getDescription() {
		return this.description;
	}

	public Usage getUsage() {
		return this.use;
	}
}
