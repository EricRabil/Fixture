package com.ericrabil.fixture.gui;

public class GuiPopup {
	private GuiType t;
	private String tt;
	private String m;
	public GuiPopup(GuiType type, String title, String message){
		this.t= type;
		this.tt = title;
		this.m = message;
	}
}
