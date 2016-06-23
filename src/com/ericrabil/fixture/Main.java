package com.ericrabil.fixture;

import com.ericrabil.fixture.api.Usage;

import javafx.application.Application;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main extends Application{
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Fixture fixture = new Fixture(Usage.PERSONAL, primaryStage);
	}
}
