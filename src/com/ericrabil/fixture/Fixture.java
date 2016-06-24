package com.ericrabil.fixture;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ericrabil.fixture.api.Database;
import com.ericrabil.fixture.api.Entry;
import com.ericrabil.fixture.api.GuiInfoData;
import com.ericrabil.fixture.api.InfoType;
import com.ericrabil.fixture.api.Releases;
import com.ericrabil.fixture.api.Usage;
import com.ericrabil.fixture.api.config.DBConfig;
import com.ericrabil.fixture.database.DAOException;
import com.ericrabil.fixture.database.IContext;
import com.ericrabil.fixture.database.IContextFactory;
import com.ericrabil.fixture.database.db.DBContextFactory;
import com.ericrabil.fixture.database.db.DBDatabaseDAO;
import com.ericrabil.fixture.database.db.DBLoginDAO;
import com.ericrabil.fixture.gui.GuiDialog;
import com.ericrabil.fixture.gui.GuiInfo;
import com.ericrabil.fixture.gui.GuiLaunch;
import com.ericrabil.fixture.gui.GuiMain;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.sourceforge.yamlbeans.YamlException;
import net.sourceforge.yamlbeans.YamlReader;
import net.sourceforge.yamlbeans.YamlWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class Fixture.
 */
public class Fixture {
	
	/** The name. */
	private String name = "Fixture";
	
	/** The window. */
	private Stage window;
	
	/** The version. */
	private String version = "1.0.1";
	
	/** The type. */
	private Releases type = Releases.ALPHA;
	
	/** The description. */
	private String description = "Fixture is a program used for data storage and organization.";

	/** The db config. */
	private DBConfig dbConfig;
	
	private HashMap<Integer, Database> dbMap = new HashMap<Integer, Database>();
	
	/** The can write. */
	private boolean canWrite;
	
	private boolean missingTable;

	/** The start time. */
	// In Milliseconds
	private int startTime;

	/** The ctfact. */
	private IContextFactory ctfact;

	/** The use. */
	private Usage use;
	
	private ArrayList<Database> databases;

	/**
	 * Instantiates a new fixture.
	 *
	 * @param use the use
	 * @param stage the stage
	 */
	public Fixture(Usage use, Stage stage) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Fixture");
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
			new FileWriter("db.yml").close();
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
				sample.db_ip = "jdbc:mysql://127.0.0.1:3306/fixture?autoReconnect=true";
				writer.write(sample);
				writer.close();
				System.out.println("Please reconfigure Fixture DB and try again.");
			} else {
				System.out.println("SQL config is properly formatted; proceeding");
				conf.db_pass = "";
				conf.db_user = "";
				this.dbConfig = conf;
				System.out.println(this.dbConfig.db_user);
			}
			if(!skip){
				dbReader.close();
				GuiLaunch launch = new GuiLaunch(this);
			}
		} catch (YamlException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateDatabases(){
		try(IContext ctx = this.createContext()){
			DBDatabaseDAO dbdao = ctx.getDatabaseDAO();
			this.databases = dbdao.getDatabases();
			this.dbMap.clear();
			for(Database db : this.databases){
				this.dbMap.put(db.getUUID(), db);
			}
		}catch(DAOException e){
			e.printStackTrace();
		}
	}
	
	public Database getDatabase(int uuid){
		return this.dbMap.get(uuid);
	}
	
	public void runMain(){
		try(IContext ctx = this.createContext()){
			DBDatabaseDAO databasedao = ctx.getDatabaseDAO();
			this.databases = databasedao.getDatabases();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GuiMain main = new GuiMain(this);
	}
	
	public ArrayList<Database> getDatabases(){
		return this.databases;
	}
	
	/**
	 * Connect to DB.
	 *
	 * @return true, if successful
	 */
	public boolean connectToDB(){
		
		this.ctfact = new DBContextFactory(this, this.dbConfig);
		try {
			this.ctfact.createContext();
			return true;
		} catch (DAOException e) {
			GuiInfoData data = new GuiInfoData("Failed to connect", new Text(e.toString()), InfoType.ERR);
			data.setQuitCode(142);
			GuiDialog dialog = new GuiDialog(data, true);
			e.printStackTrace();
			//System.exit(413);
			return false;
		}
	}
	
	/** The lgdao. */
	private DBLoginDAO lgdao;
	
	/**
	 * Post connection.
	 */
	public void postConnection(){
		try(IContext ctx = this.createContext()){
			DBLoginDAO login = ctx.getLoginDAO();
			this.lgdao = login;
			this.canWrite = login.writePermissions();
		} catch (DAOException e) {
			this.canWrite = false;
			e.printStackTrace();
		}
	}
	
	public void missingTableDialog(){
		this.missingTable =true;
		GuiInfoData data = new GuiInfoData("Database isn't setup.", new Text("Fixture is missing a table that is required for normal operation. Please re-configure the database and try agian."), InfoType.WARN);
		data.addHeader(this.lgdao.getException().getMessage());
		data.setQuitCode(43);
		GuiDialog dialog = new GuiDialog(data, true);
	}
	
	/**
	 * Handle write.
	 */
	public void handleWrite(){
		if(!this.canWrite && !this.missingTable){
			GuiInfoData data = new GuiInfoData("No Write Permission", new Text("Fixture will be in read-only mode during this session because the account you connected with does not have write access."), InfoType.WARN);
			data.addHeader(this.lgdao.getException().getMessage());
			GuiDialog dialog = new GuiDialog(data, true);
		}
	}
	
	/**
	 * Gets the stage.
	 *
	 * @return the stage
	 */
	public Stage getStage(){
		return this.window;
	}

	/**
	 * On disable.
	 */
	public void onDisable() {
		this.window.hide();
		System.out.println("Good-bye!");
	}

	/**
	 * Creates the context.
	 *
	 * @return the i context
	 * @throws DAOException the DAO exception
	 */
	public IContext createContext() throws DAOException {
		return this.ctfact.createContext();
	}

	/**
	 * Gets the context factory.
	 *
	 * @return the context factory
	 */
	public IContextFactory getContextFactory() {
		return this.ctfact;
	}

	/**
	 * Start time millis.
	 *
	 * @return the int
	 */
	public int startTimeMillis() {
		return this.startTime;
	}

	/**
	 * Gets the DB config.
	 *
	 * @return the DB config
	 */
	public DBConfig getDBConfig() {
		return this.dbConfig;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the release type.
	 *
	 * @return the release type
	 */
	public Releases getReleaseType() {
		return this.type;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the usage.
	 *
	 * @return the usage
	 */
	public Usage getUsage() {
		return this.use;
	}
}
