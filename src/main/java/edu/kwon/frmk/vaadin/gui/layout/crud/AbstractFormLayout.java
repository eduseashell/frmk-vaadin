package edu.kwon.frmk.vaadin.gui.layout.crud;

import javax.annotation.PostConstruct;

import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractFormLayout extends VerticalLayout implements CrudListener {

	private static final long serialVersionUID = 2830613554936017962L;
	
	private AbstractTabSheetLayout tabSheet;
	
	@PostConstruct
	public void postConstruct() {
		init();
	}
	
	protected void init() {
		setMargin(true);
		setSpacing(true);
		
		buildDefaultCRUDBar();
		addComponent(initGUI());
	}
	
	protected void buildDefaultCRUDBar() {
		CrudBar crudBar = new CrudBar();
		crudBar.addSaveButton("save");
		crudBar.setCrudListener(this);
		addComponent(crudBar);
	}

	@Override
	public void onSaveActionClicked() {
		if (validate()) {
			onSaveAction();
			tabSheet.setNeedRefresh(Boolean.TRUE);
		} else {
			// TODO show error on save
		}
	}
	
	protected abstract AbstractComponentContainer initGUI();
	public abstract void assignValues(Long entityId);
	protected abstract boolean validate();
	protected abstract void onSaveAction();
	public abstract void reset();
	
	@Override
	public void onNewActionClicked() { }

	@Override
	public void onEditActionClicked() { }

	@Override
	public void onDeleteActionClicked() { }

}
