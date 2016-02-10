package edu.kwon.frmk.vaadin.gui.layout.crud;

import java.io.Serializable;

public interface CrudListener extends Serializable {
	
	void onNewActionClicked();
	void onEditActionClicked();
	void onDeleteActionClicked();
	void onSaveActionClicked();
	void onCancelActionClicked();
	void onRefreshActionClicked();

}
