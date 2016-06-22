package com.ericrabil.fixture.gui;

import com.ericrabil.fixture.api.GuiInfoData;

import javafx.scene.control.Alert;

public class GuiDialog {
	private GuiInfoData d;
	private boolean wait;
	public GuiDialog(GuiInfoData data){
		this.d = data;
		this.draw();
		this.wait = false;
	}
	public GuiDialog(GuiInfoData data, boolean w){
		this.d = data;
		this.draw();
		this.wait = w;
	}
	
	private void draw(){
		Alert alert = new Alert(d.getType().getType());
		alert.setTitle(d.getTitle());
		alert.setHeaderText(d.getHeader());
		alert.setContentText(d.getMessage().getText());
		if(this.wait){
		alert.showAndWait();
		}else{
			alert.show();
		}
	}
}
