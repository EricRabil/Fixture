package com.ericrabil.fixture.gui;

import com.ericrabil.fixture.Fixture;
import com.ericrabil.fixture.api.GuiInfoData;
import com.ericrabil.fixture.api.InfoType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GuiLaunch {
	private Scene launchscreen;
	private Stage stage;
	private Fixture fixture;
	private String db_pass;
	
	public GuiLaunch(Fixture fixture){
		this.fixture = fixture;
		this.stage = this.fixture.getStage();
		this.drawLaunch();
		this.stage.setScene(launchscreen);
        this.stage.setResizable(false);
        this.stage.show();
	}
	
	public void drawLaunch(){
		Label title = new Label("Fixture Database Viewer");
        title.setFont(new Font("System", 24));
        PasswordField field = new PasswordField();
        field.setPromptText("Please enter the password for " + this.fixture.getDBConfig().db_user);
        field.setMaxWidth(220);
        
        Button start = new Button("Launch");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	db_pass = field.getText();
            	fixture.getDBConfig().db_pass = db_pass;
            	stage.setScene(null);
            	if(fixture.connectToDB()){
            		fixture.postConnection();
            		fixture.handleWrite();
            	}
            }
        });
        VBox hBoxCombo = new VBox(8, title, start, field);
        hBoxCombo.setAlignment(Pos.CENTER);
        StackPane stacked = new StackPane(hBoxCombo);
        launchscreen = new Scene(stacked, 512, 512);
	}
}
