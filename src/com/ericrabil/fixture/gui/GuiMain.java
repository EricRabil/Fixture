package com.ericrabil.fixture.gui;

import com.ericrabil.fixture.Fixture;

import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Class GuiMain.
 */
public class GuiMain {
	private Fixture f;
	private String title = "Fixture Database Viewer";
	private Scene mainscene;
	private Stage primaryStage;
	
	public GuiMain(Fixture fixture){
		this.f = fixture;
		this.primaryStage = this.f.getStage();
		this.draw();
		this.primaryStage.setScene(mainscene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
	}
	
	private void draw(){
		
	}
	
	
}
