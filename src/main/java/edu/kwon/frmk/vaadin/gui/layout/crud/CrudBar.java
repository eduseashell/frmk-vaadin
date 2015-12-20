package edu.kwon.frmk.vaadin.gui.layout.crud;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

import edu.kwon.frmk.vaadin.factory.VaadinFactory;

/**
 * 
 * @author eduseashell
 *
 */
public class CrudBar extends ActionBar {

	private static final long serialVersionUID = 316881922286118621L;
	
	private CrudListener listener;
	
	public CrudBar addDefaultCrudBar() {
		addNewButton("new");
		addEditButton("edit");
		addDeleteButton("delete");
		return this;
	}
	
	public Button addNewButton(String caption) {
		Button btnNew = VaadinFactory.getButtonDanger(caption);
		btnNew.setIcon(FontAwesome.PLUS);
		btnNew.addClickListener(e -> { if (listener != null) listener.onNewActionClicked(); });
		addButton(btnNew);
		return btnNew;
	}
	
	public Button addEditButton(String caption) {
		Button btnEdit = VaadinFactory.getButton(caption);
		btnEdit.setIcon(FontAwesome.EDIT);
		btnEdit.addClickListener(e -> { if (listener != null) listener.onEditActionClicked(); });
		addButton(btnEdit);
		return btnEdit;
	}
	
	public Button addDeleteButton(String caption) {
		Button btnDelete = VaadinFactory.getButton(caption);
		btnDelete.setIcon(FontAwesome.TRASH_O);
		btnDelete.addClickListener(e -> { if (listener != null) listener.onDeleteActionClicked(); });
		addButton(btnDelete);
		return btnDelete;
	}
	
	public Button addSaveButton(String caption) {
		Button btnSave = VaadinFactory.getButtonPrimary(caption);
		btnSave.setIcon(FontAwesome.SAVE);
		btnSave.addClickListener(e -> { if (listener != null) listener.onSaveActionClicked(); });
		addButton(btnSave);
		return btnSave;
	}
	
	public void setCrudListener(CrudListener listener) {
		this.listener = listener;
	}

}
