package com.ericrabil.fixture.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import com.ericrabil.fixture.Fixture;

public class Gui {
	private Fixture f;
	private String title;
	
	private int w;
	private int h;
	private JFrame jf;
	
	public Gui(Fixture instance, String windowTitle, int width, int height){
		this.f = instance;
		this.title = windowTitle;
		this.w = width;
		this.h = height;
	}
	
	public JFrame makeFrame(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.setTitle(title);
		frame.setSize(w, h);
		this.jf = frame;
		return frame;
	}
	
	public JFrame getFrame(){
		return this.jf;
	}
	
}
