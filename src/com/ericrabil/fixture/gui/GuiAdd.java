package com.ericrabil.fixture.gui;

import com.ericrabil.fixture.Fixture;
import com.ericrabil.fixture.api.Database;

import javafx.stage.Popup;
import javafx.stage.PopupBuilder;

public class GuiAdd {
	
	private Fixture f;
	private Database d;
	
	public GuiAdd(Fixture fixture, Database database){
		this.f = fixture;
		this.d = database;
	}
	
	private void draw(){
		Popup pop = PopupBuilder.create().content(contentNode).width(50).height(100).autoFix(true).build();
		pop.show(stage);
	}
}
