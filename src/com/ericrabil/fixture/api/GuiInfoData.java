package com.ericrabil.fixture.api;

import javafx.scene.text.Text;

// TODO: Auto-generated Javadoc
/**
 * The Class GuiInfoData.
 */
public class GuiInfoData {
	
	/** The title. */
	private String title;
	
	/** The message. */
	private Text message;
	
	/** The type. */
	private InfoType type;
	
	/** The header. */
	private String header = null;
	
	/** The has header. */
	private boolean hasHeader = false;
	
	/** The quit code. */
	private int quitCode = -999;
	
	/**
	 * Instantiates a new gui info data.
	 */
	public GuiInfoData(){
		this.title = "Missing Title Value!"; this.message = new Text("Missing Message Value!"); this.type = InfoType.GENERIC;
	}
	
	/**
	 * Instantiates a new gui info data.
	 *
	 * @param title passes the title and makes the rest of the values generic.
	 */
	public GuiInfoData(String title){
		this.title = title; this.message = new Text("Missing Message Value!"); this.type = InfoType.GENERIC;
	}
	
	/**
	 * Instantiates a new gui info data.
	 *
	 * @param t passes the title
	 * @param m passes the message
	 */
	public GuiInfoData(String t, String m){
		this.title = t; this.message = new Text(m); this.type = InfoType.GENERIC;
	}
	
	/**
	 * Instantiates a new gui info data.
	 *
	 * @param title the title
	 * @param message the message
	 * @param type the type
	 */
	public GuiInfoData(String title, String message, InfoType type){
		this.title = title; this.message = new Text(message); this.type = type;
	}
	
	/**
	 * Instantiates a new gui info data.
	 *
	 * @param title the title
	 * @param message the message
	 * @param type the type
	 * @param code the code
	 */
	public GuiInfoData(String title, String message, InfoType type, int code){
		this.title = title; this.message = new Text(message); this.type = type; this.quitCode = code;
	}
	
	/**
	 * Instantiates a new gui info data.
	 *
	 * @param t the t
	 * @param m the m
	 */
	public GuiInfoData(String t, Text m){
		this.title = t; this.message = m; this.type = InfoType.GENERIC;
	}
	
	/**
	 * Instantiates a new gui info data.
	 *
	 * @param title the title
	 * @param m the m
	 * @param type the type
	 */
	public GuiInfoData(String title, Text m, InfoType type){
		this.title = title; this.message = m; this.type = type;
	}
	
	/**
	 * Instantiates a new gui info data.
	 *
	 * @param title the title
	 * @param m the m
	 * @param type the type
	 * @param code the code
	 */
	public GuiInfoData(String title, Text m, InfoType type, int code){
		this.title = title; this.message = m; this.type = type; this.quitCode = code;
	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public Text getMessage(){
		return this.message;
	}
	
	/**
	 * Checks for quit code.
	 *
	 * @return true, if successful
	 */
	public boolean hasQuitCode(){
		return this.quitCode != -999;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public InfoType getType(){
		return this.type;
	}
	
	/**
	 * Gets the quit code.
	 *
	 * @return the quit code
	 */
	public int getQuitCode(){
		return this.quitCode;
	}
	
	/**
	 * Sets the title.
	 *
	 * @param t the new title
	 */
	public void setTitle(String t){
		this.title = t;
	}
	
	/**
	 * Adds the header.
	 *
	 * @param x the x
	 */
	public void addHeader(String x){
		this.header = x;
		this.hasHeader = true;
	}
	
	/**
	 * Checks for header.
	 *
	 * @return true, if successful
	 */
	public boolean hasHeader(){
		return this.hasHeader;
	}
	
	/**
	 * Gets the header.
	 *
	 * @return the header
	 */
	public String getHeader(){
		return this.header;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param m the new message
	 */
	public void setMessage(Text m){
		this.message = m;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(InfoType type){
		this.type = type;
	}
	
	/**
	 * Sets the quit code.
	 *
	 * @param i the new quit code
	 */
	public void setQuitCode(int i){
		this.quitCode = i;
	}
}
