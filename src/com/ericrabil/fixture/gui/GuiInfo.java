/*
 * Class built off of http://www.rgagnon.com/javadetails/java-0242.html
 */

package com.ericrabil.fixture.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiInfo extends Dialog implements ActionListener{
	private Button ok,can;
	public boolean isOK = false;
	
	public GuiInfo(Frame frame, String msg, boolean okcan){
		super(frame, "TEXT!2", true);
		setLayout(new BorderLayout());
		add("Center", new Label(msg));
		addOKCancelPanel(okcan);
		createFrame();
		pack();
		setVisible(true);
	}
	
	public GuiInfo(Frame frame, String msg){
		this(frame, "TEXT!3", false);
	}
	
	public void addOKCancelPanel(boolean okcan){
		Panel p = new Panel();
		p.setLayout(new FlowLayout());
		createOKButton(p);
		if(okcan == true){
			createCancelButton(p);
		}
		add("South", p);
	}
	
	public void createOKButton(Panel p){
		p.add(ok = new Button("OK"));
		ok.addActionListener(this);
	}
	
	public void createCancelButton(Panel p){
		p.add(can = new Button("Abort"));
		can.addActionListener(this);
	}
	
	public void createFrame(){
		Dimension d = getToolkit().getScreenSize();
		setLocation(d.width/3,d.height/3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok){
			isOK = true;
			setVisible(false);
		}else if(e.getSource() == can){
			setVisible(false);
		}
	}
}
