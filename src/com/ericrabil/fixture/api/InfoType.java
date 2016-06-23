package com.ericrabil.fixture.api;

import javafx.scene.control.Alert.AlertType;

// TODO: Auto-generated Javadoc
/**
 * The Enum InfoType.
 */
public enum InfoType {

/** The warn. */
WARN(AlertType.WARNING), 
 /** The info. */
 INFO(AlertType.INFORMATION), 
 /** The err. */
 ERR(AlertType.ERROR), 
 /** The generic. */
 GENERIC(AlertType.NONE), 
 /** The confirm. */
 CONFIRM(AlertType.CONFIRMATION);

/** The type. */
private AlertType type;

/**
 * Instantiates a new info type.
 *
 * @param t the t
 */
private InfoType(AlertType t){
	this.type = t;
}

/**
 * Gets the type.
 *
 * @return the type
 */
public AlertType getType(){
	return type;
}
}
