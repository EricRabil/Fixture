package com.ericrabil.fixture.gui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.ericrabil.fixture.Fixture;
import com.ericrabil.fixture.api.Database;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Class GuiMain.
 */
public class GuiMain {
	private Fixture f;
	private Scene mainscene;
	private Stage primaryStage;
	private JFrame window;
	private HashMap<Label, Database> dbMap = new HashMap<Label, Database>();
	
	public GuiMain(Fixture fixture){
		this.f = fixture;
		this.primaryStage = this.f.getStage();
		this.draw();
		this.primaryStage.setScene(mainscene);
        this.primaryStage.setResizable(false);
        this.primaryStage.centerOnScreen();
        this.primaryStage.show();
	}
	
	private void draw(){
		/**Container arrangement = window.getContentPane();
		arrangement.setLayout(new BorderLayout());**/
		ListView<Node> dbList = new ListView<>();
		for(Database db : this.f.getDatabases()){
			Label label = new Label(db.getName());
			this.dbMap.put(label, db);
			dbList.getItems().add(label);
		}
		Button start = new Button("View");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Label label = (Label) dbList.getSelectionModel().getSelectedItem();
            	Database db = dbMap.get(label);
//            	System.out.println(db.getSQLID());
//            	System.out.println(label.getText());
            	new GuiDBView(f, db);
            }
        });
		VBox hBoxCombo = new VBox(16, dbList, start);
        hBoxCombo.setAlignment(Pos.CENTER);
        StackPane stacked = new StackPane(hBoxCombo);
        mainscene = new Scene(stacked, 1024, 512);
	}
	
	
}
