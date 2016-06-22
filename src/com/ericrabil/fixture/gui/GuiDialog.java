package com.ericrabil.fixture.gui;

import com.ericrabil.fixture.api.GuiInfoData;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;

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
			alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
	            @Override
	            public void handle(DialogEvent event) {
	            	if(d.hasQuitCode()){
	            		System.exit(d.getQuitCode());
	            	}
	            }
	        });
		}else{
			alert.show();
			alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
	            @Override
	            public void handle(DialogEvent event) {
	            	if(d.hasQuitCode()){
	            		System.exit(d.getQuitCode());
	            	}
	            }
	        });
		}
		
	}
}
