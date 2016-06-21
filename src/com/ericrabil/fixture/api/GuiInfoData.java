package com.ericrabil.fixture.api;

import javafx.scene.text.Text;

public class GuiInfoData {
	private String title;
	private Text message;
	private InfoType type;
	private int quitCode = -999;
	
	public GuiInfoData(){
		this.title = "Missing Title Value!"; this.message = new Text("Missing Message Value!"); this.type = InfoType.GENERIC;
	}
	public GuiInfoData(String title){
		this.title = title; this.message = new Text("Missing Message Value!"); this.type = InfoType.GENERIC;
	}
	public GuiInfoData(String t, String m){
		this.title = t; this.message = new Text(m); this.type = InfoType.GENERIC;
	}
	public GuiInfoData(String title, String message, InfoType type){
		this.title = title; this.message = new Text(message); this.type = type;
	}
	public GuiInfoData(String title, String message, InfoType type, int code){
		this.title = title; this.message = new Text(message); this.type = type; this.quitCode = code;
	}
	public GuiInfoData(String t, Text m){
		this.title = t; this.message = m; this.type = InfoType.GENERIC;
	}
	public GuiInfoData(String title, Text m, InfoType type){
		this.title = title; this.message = m; this.type = type;
	}
	public GuiInfoData(String title, Text m, InfoType type, int code){
		this.title = title; this.message = m; this.type = type; this.quitCode = code;
	}
	public String getTitle(){
		return this.title;
	}
	public Text getMessage(){
		return this.message;
	}
	public InfoType getType(){
		return this.type;
	}
	public int getQuitCode(){
		return this.quitCode;
	}
	public void setTitle(String t){
		this.title = t;
	}
	public void setMessage(Text m){
		this.message = m;
	}
	public void setType(InfoType type){
		this.type = type;
	}
	public void setQuitCode(int i){
		this.quitCode = i;
	}
}
