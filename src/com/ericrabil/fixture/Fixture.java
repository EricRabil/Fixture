package com.ericrabil.fixture;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.ericrabil.fixture.api.GuiInfoData;
import com.ericrabil.fixture.api.InfoType;
import com.ericrabil.fixture.api.Releases;
import com.ericrabil.fixture.api.Usage;
import com.ericrabil.fixture.api.config.DBConfig;
import com.ericrabil.fixture.database.DAOException;
import com.ericrabil.fixture.database.IContext;
import com.ericrabil.fixture.database.IContextFactory;
import com.ericrabil.fixture.database.db.DBContextFactory;
import com.ericrabil.fixture.gui.GuiInfo;
import com.ericrabil.fixture.gui.GuiLaunch;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.sourceforge.yamlbeans.YamlException;
import net.sourceforge.yamlbeans.YamlReader;
import net.sourceforge.yamlbeans.YamlWriter;

public class Fixture {
	private String name = "Fixture";
	
	private Stage window;
	
	private String version = "1.0.0";
	private Releases type = Releases.ALPHA;
	private String description = "Fixture is a program used for data storage and organization.";

	private DBConfig dbConfig;

	// In Milliseconds
	private int startTime;

	private IContextFactory ctfact;

	private Usage use;

	public Fixture(Usage use, Stage stage) {
		boolean skip = false;
		window = stage;
		System.out.println("Initializing " + name + " " + version + " " + type.toString().toLowerCase());
		this.use = use;
		this.startTime = (int) System.currentTimeMillis();
		try {
			System.out.println("Instantiating file reader");
			YamlReader dbReader;
			try{
			dbReader = new YamlReader(new FileReader("db.yml"));
			}catch(FileNotFoundException e){
			new FileWriter("db.yml");
			dbReader = new YamlReader(new FileReader("db.yml"));
			}
			System.out.println("Reading SQL config");
			DBConfig conf = dbReader.read(DBConfig.class);
			if (conf == null || conf.anythingMissing()) {
				Text txt = new Text("The SQL configuration was missing or invalid; db.yml will be overwritten. Please reconfigure db.yml and restart Fixture.");
				txt.setWrappingWidth(500);
				GuiInfoData data = new GuiInfoData("Fixture DB", txt, InfoType.ERR, 23);
				GuiInfo info = new GuiInfo(this, data);
				skip = true;
				//System.out.println("The SQL configuration was invalid; Fixture.yml will be overwritten");
				YamlWriter writer = new YamlWriter(new FileWriter("db.yml"));
				DBConfig sample = new DBConfig();
				sample.db_collation = "utf8";
				sample.db_ip = "jdbc:mysql://127.0.0.1:3306/tregmine_test?autoReconnect=true";
				sample.db_pass = "enter_when_running_fixture";
				sample.db_user = "fixuser";
				writer.write(sample);
				writer.close();
				System.out.println("Please reconfigure Fixture DB and try again.");
			} else {
				System.out.println("SQL config is properly formatted; proceeding");
				conf.db_pass = "";
				this.dbConfig = conf;
			}
			if(!skip){
			dbReader.close();
			GuiLaunch launch = new GuiLaunch(this);
			}
		} catch (YamlException | IOException e) {
			e.printStackTrace();
		}
	}
	public boolean connectToDB(){
		this.ctfact = new DBContextFactory(this, this.dbConfig);
		try {
			this.ctfact.createContext();
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Stage getStage(){
		return this.window;
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
