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
import com.ericrabil.fixture.database.db.DBLoginDAO;
import com.ericrabil.fixture.gui.GuiDialog;
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
	
	private String version = "1.0.1";
	private Releases type = Releases.ALPHA;
	private String description = "Fixture is a program used for data storage and organization.";

	private DBConfig dbConfig;
	
	private boolean canWrite;

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
				sample.db_pass = "enter_when_running_fixture";
				sample.db_user = "fixuser";
				writer.write(sample);
				writer.close();
				System.out.println("Please reconfigure Fixture DB and try again.");
			} else {
				System.out.println("SQL config is properly formatted; proceeding");
				conf.db_pass = "";
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
	
	public void postConnection(){
		try(IContext ctx = this.createContext()){
			DBLoginDAO login = ctx.getLoginDAO();
			this.canWrite = login.writePermissions();
		} catch (DAOException e) {
			this.canWrite = false;
			e.printStackTrace();
		}
	}
	
	public void handleWrite(){
		if(!this.canWrite){
			GuiInfoData data = new GuiInfoData("No Write Permission", new Text("Fixture will be in read-only mode during this session because the account you connected with does not have write access."), InfoType.WARN);
			GuiDialog dialog = new GuiDialog(data, true);
		}
	}
	
	public Stage getStage(){
		return this.window;
	}

	public void onDisable() {
		this.window.hide();
		System.out.println("Good-bye!");
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
