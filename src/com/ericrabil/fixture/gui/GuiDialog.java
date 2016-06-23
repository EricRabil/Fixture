package com.ericrabil.fixture.gui;

import com.ericrabil.fixture.api.GuiInfoData;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class GuiDialog.
 */
public class GuiDialog {
	
	/** The d. */
	private GuiInfoData d;
	
	/** The wait. */
	private boolean wait;
	
	/**
	 * Instantiates a new gui dialog.
	 *
	 * @param data the data
	 */
	public GuiDialog(GuiInfoData data){
		this.d = data;
		this.draw();
		this.wait = false;
	}
	
	/**
	 * Instantiates a new gui dialog.
	 *
	 * @param data the data
	 * @param w the w
	 */
	public GuiDialog(GuiInfoData data, boolean w){
		this.d = data;
		this.draw();
		this.wait = w;
	}
	
	/**
	 * Draw.
	 */
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
