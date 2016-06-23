package com.ericrabil.fixture.gui;

import com.ericrabil.fixture.Fixture;
import com.ericrabil.fixture.api.GuiInfoData;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class GuiInfo.
 */
public class GuiInfo{
	
	/** The infoscreen. */
	private Scene infoscreen;
	
	/** The stage. */
	private Stage stage;
	
	/** The fixture. */
	private Fixture fixture;
	
	/** The data. */
	private GuiInfoData data;
	
	/**
	 * Instantiates a new gui info.
	 *
	 * @param f the f
	 * @param d the d
	 */
	public GuiInfo(Fixture f, GuiInfoData d){
		this.fixture = f;
		this.stage = this.fixture.getStage();
		this.data = d;
		drawInfo();
		this.stage.setScene(infoscreen);
		this.stage.setResizable(false);
		this.stage.show();
	}
	
	/**
	 * Draw info.
	 */
	public void drawInfo(){
		Button ok = new Button("Ok");
		ok.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(data.getQuitCode() != -999){
            		System.exit(data.getQuitCode());
            	}else{
            		stage.hide();
            	}
            }
        }));
		Text message = data.getMessage();
		this.stage.setTitle(data.getTitle());
		VBox hBoxCombo = new VBox(8, message, ok);
		hBoxCombo.setAlignment(Pos.CENTER);
		StackPane stacked = new StackPane(hBoxCombo);
		this.infoscreen = new Scene(stacked, 512, 256);
	}

}
