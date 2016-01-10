package edu.kwon.frmk.vaadin.gui.layout.crud;

import javax.annotation.PostConstruct;

import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.VerticalLayout;

import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntity;
import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntityService;

public abstract class AbstractFormLayout<T extends AuditEntity> extends VerticalLayout implements CrudListener {

	private static final long serialVersionUID = 2830613554936017962L;
	
	protected T entity;
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
	
	protected void onSaveAction() {
		fillDataToEntity();
		if (entity == null) {
			throw new IllegalStateException("Entity cannot be null");
		}
		getService().save(entity);
	}
	
	public void setMainTabSheet(AbstractTabSheetLayout tabSheet) {
		this.tabSheet = tabSheet;
	}
	
	protected abstract AbstractComponentContainer initGUI();
	public abstract void assignValues(Long entityId);
	protected abstract void fillDataToControls();
	protected abstract void fillDataToEntity();
	protected abstract boolean validate();
	protected abstract AuditEntityService<T> getService();
	
	@Override
	public void onNewActionClicked() { }

	@Override
	public void onEditActionClicked() { }

	@Override
	public void onDeleteActionClicked() { }
	
	public void reset() {
		entity = null;
	}
	
}
