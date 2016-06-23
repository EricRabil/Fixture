package com.ericrabil.fixture.gui;

import com.ericrabil.fixture.Fixture;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class GuiLaunch.
 */
public class GuiLaunch {
	
	/** The launchscreen. */
	private Scene launchscreen;
	
	/** The stage. */
	private Stage stage;
	
	/** The fixture. */
	private Fixture fixture;
	
	/** The db pass. */
	private String db_pass;
	
	/**
	 * Instantiates a new gui launch.
	 *
	 * @param fixture provides the instance with data such as DB config and the stage.
	 */
	public GuiLaunch(Fixture fixture){
		this.fixture = fixture;
		this.stage = this.fixture.getStage();
		this.drawLaunch();
		this.stage.setScene(launchscreen);
        this.stage.setResizable(false);
        this.stage.show();
	}
	
	/**
	 * Draw launch.
	 */
	public void drawLaunch(){
		Label title = new Label("Fixture Database Viewer");
        title.setFont(new Font("System", 34));
        TextField field = new TextField();
        field.setPromptText("Username");
        field.setMaxWidth(220);
        PasswordField field2 = new PasswordField();
        field2.setPromptText("Password");
        field2.setMaxWidth(220);
        field2.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent arg0) {
				if(arg0.getCode().equals(KeyCode.ENTER)){
					fixture.getDBConfig().db_user = field.getText();
	            	fixture.getDBConfig().db_pass = field2.getText();
	            	stage.setScene(null);
	            	if(fixture.connectToDB()){
	            		fixture.postConnection();
	            		fixture.handleWrite();
	            		fixture.runMain();
	            	}
				}
			}
        });
        
        Button start = new Button("Launch");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	//db_pass = field.getText();
            	fixture.getDBConfig().db_user = field.getText();
            	fixture.getDBConfig().db_pass = field2.getText();
            	stage.setScene(null);
            	if(fixture.connectToDB()){
            		fixture.postConnection();
            		fixture.handleWrite();
            		fixture.runMain();
            	}
            }
        });
        VBox hBoxCombo = new VBox(8, title, field, field2, start);
        hBoxCombo.setAlignment(Pos.CENTER);
        StackPane stacked = new StackPane(hBoxCombo);
        launchscreen = new Scene(stacked, 512, 512);
	}
}
