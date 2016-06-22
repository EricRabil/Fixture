package com.ericrabil.fixture.api;

import javafx.scene.control.Alert.AlertType;

public enum InfoType {
WARN(AlertType.WARNING), INFO(AlertType.INFORMATION), ERR(AlertType.ERROR), GENERIC(AlertType.NONE), CONFIRM(AlertType.CONFIRMATION);
private AlertType type;

private InfoType(AlertType t){
	this.type = t;
}

public AlertType getType(){
	return type;
}
}
