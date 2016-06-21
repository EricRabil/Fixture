package com.ericrabil.fixture;

import com.ericrabil.fixture.api.Usage;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		Fixture fixture = new Fixture(Usage.PERSONAL, primaryStage);
	}
}
