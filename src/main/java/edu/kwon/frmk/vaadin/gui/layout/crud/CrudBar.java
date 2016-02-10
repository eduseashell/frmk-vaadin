package edu.kwon.frmk.vaadin.gui.layout.crud;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

import edu.kwon.frmk.vaadin.component.factory.VaadinFactory;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.CancelClickListener;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.DeleteClickListener;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.EditClickListener;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.NewClickListener;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.RefreshClickListener;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.SaveClickListener;

/**
 * 
 * @author eduseashell
 *
 */
public class CrudBar extends ActionBar {

	private static final long serialVersionUID = 316881922286118621L;
	
	private NewClickListener newClickListener;
	private EditClickListener editClickListener;
	private DeleteClickListener deleteClickListener;
	private SaveClickListener saveClickListener;
	private CancelClickListener cancelClickListener;
	private RefreshClickListener refreshClickListener;
	
	public CrudBar addDefaultCrudBar() {
		addNewButton("new");
		addEditButton("edit");
		addDeleteButton("delete");
		return this;
	}
	
	public Button addNewButton(String caption) {
		Button btnNew = VaadinFactory.getButtonDanger(caption);
		btnNew.setIcon(FontAwesome.PLUS);
		btnNew.addClickListener(e -> {
			NewClickListener Listener = newClickListener;
			if (Listener != null) Listener.onNewActionClicked();
		});
		addButton(btnNew);
		return btnNew;
	}
	
	public Button addEditButton(String caption) {
		Button btnEdit = VaadinFactory.getButton(caption);
		btnEdit.setIcon(FontAwesome.EDIT);
		btnEdit.addClickListener(e -> {
			EditClickListener Listener = editClickListener;
			if (Listener != null) Listener.onEditActionClicked();
		});
		addButton(btnEdit);
		return btnEdit;
	}
	
	public Button addDeleteButton(String caption) {
		Button btnDelete = VaadinFactory.getButton(caption);
		btnDelete.setIcon(FontAwesome.TRASH_O);
		btnDelete.addClickListener(e -> {
			DeleteClickListener Listener = deleteClickListener;
			if (Listener != null) Listener.onDeleteActionClicked();
		});
		addButton(btnDelete);
		return btnDelete;
	}
	
	public Button addSaveButton(String caption) {
		Button btnSave = VaadinFactory.getButtonPrimary(caption);
		btnSave.setIcon(FontAwesome.SAVE);
		btnSave.addClickListener(e -> {
			SaveClickListener Listener = saveClickListener;
			if (Listener != null) Listener.onSaveActionClicked();
		});
		addButton(btnSave);
		return btnSave;
	}
	
	public Button addCancelButton(String caption) {
		Button btnCancel = VaadinFactory.getButton(caption);
		btnCancel.setIcon(FontAwesome.TIMES);
		btnCancel.addClickListener(e -> {
			CancelClickListener Listener = cancelClickListener;
			if (Listener != null) Listener.onCancelActionClicked();
		});
		addButton(btnCancel);
		return btnCancel;
	}
	
	public Button addRefreshButton(String caption) {
		Button btnRefresh = VaadinFactory.getButton(caption);
		btnRefresh.setIcon(FontAwesome.REFRESH);
		btnRefresh.addClickListener(e -> {
			RefreshClickListener Listener = refreshClickListener;
			if (Listener != null) Listener.onRefreshActionClicked();
		});
		addButton(btnRefresh);
		return btnRefresh;
	}

	public void setNewClickListener(final NewClickListener newClickListener) {
		this.newClickListener = newClickListener;
	}

	public void setEditClickListener(final EditClickListener editClickListener) {
		this.editClickListener = editClickListener;
	}

	public void setDeleteClickListener(final DeleteClickListener deleteClickListener) {
		this.deleteClickListener = deleteClickListener;
	}

	public void setSaveClickListener(final SaveClickListener saveClickListener) {
		this.saveClickListener = saveClickListener;
	}

	public void setCancelClickListener(final CancelClickListener cancelClickListener) {
		this.cancelClickListener = cancelClickListener;
	}

	public void setRefreshClickListener(final RefreshClickListener refreshClickListener) {
		this.refreshClickListener = refreshClickListener;
	}

}
