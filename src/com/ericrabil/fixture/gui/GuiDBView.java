package com.ericrabil.fixture.gui;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.ericrabil.fixture.Fixture;
import com.ericrabil.fixture.api.Database;
import com.ericrabil.fixture.api.Entry;
import com.ericrabil.fixture.database.DAOException;
import com.ericrabil.fixture.database.IContext;
import com.ericrabil.fixture.database.db.DBDatabaseDAO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiDBView {
	private Fixture f;
	private Database db;
	
	private HashMap<Label, Entry> entryMap = new HashMap<Label, Entry>();
	
	private Scene dbscene;
	private Stage primaryStage;
	
	public GuiDBView(Fixture fixture, Database database){
		this.f = fixture;
		this.db = database;
		this.primaryStage = this.f.getStage();
		this.draw();
		this.primaryStage.setScene(dbscene);
        this.primaryStage.setResizable(false);
        this.primaryStage.centerOnScreen();
        this.primaryStage.show();
	}
	
	private void draw(){
		ListView<Node> keyList = new ListView<>();
		ListView<Node> valList = new ListView<>();
		ArrayList<Label> addLabelsKey = new ArrayList<Label>();
		ArrayList<Label> addLabelsValue = new ArrayList<Label>();
		for(Entry e : this.db.getEntries()){
			Label l = new Label(e.getKey());
			this.entryMap.put(l, e);
			addLabelsKey.add(l);
			Label lv = new Label(e.getValue());
			this.entryMap.put(lv, e);
			addLabelsValue.add(lv);
			keyList.getItems().add(l);
			valList.getItems().add(lv);
		}
		Button add = new Button("Add");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            	String key = JOptionPane.showInputDialog("What is the variable?", null);
            	String value = JOptionPane.showInputDialog("What is the value?", null);
            	try(IContext ctx = f.createContext()){
            		DBDatabaseDAO dbdao = ctx.getDatabaseDAO();
            		dbdao.addEntry(key, value, db);
            		GuiDBView dbview = new GuiDBView(f, db);
            	}catch(DAOException e){
            		e.printStackTrace();
            	}
            }
        });
		Button edit = new Button("Edit");
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        Button delete = new Button("Delete");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        HBox list = new HBox(8, keyList, valList);
        list.setAlignment(Pos.CENTER);
        HBox options = new HBox(8, add, edit, delete);
        options.setAlignment(Pos.CENTER);
        VBox hBoxCombo = new VBox(8, list, options);
        hBoxCombo.setAlignment(Pos.CENTER);
        StackPane stacked = new StackPane(hBoxCombo);
        dbscene = new Scene(stacked, 1024, 512);
	}
	
	private void refresh(){
		
	}
}
