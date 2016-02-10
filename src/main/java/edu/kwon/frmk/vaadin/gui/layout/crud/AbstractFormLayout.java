package edu.kwon.frmk.vaadin.gui.layout.crud;

import javax.annotation.PostConstruct;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntity;
import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntityService;
import edu.kwon.frmk.common.share.spring.util.I18N;
import edu.kwon.frmk.vaadin.component.factory.VaadinFactory;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.SaveClickListener;

public abstract class AbstractFormLayout<T extends AuditEntity> extends VerticalLayout implements SaveClickListener {

	private static final long serialVersionUID = 2830613554936017962L;
	
	protected T entity;
	private AbstractTabSheetLayout<T> tabSheet;
	
	@PostConstruct
	public void postConstruct() {
		init();
	}
	
	protected void init() {
		setMargin(true);
		setSpacing(true);
		setIcon(FontAwesome.LIST_OL);
		
		buildDefaultCRUDBar();
		addComponent(initGUI());
	}
	
	protected void buildDefaultCRUDBar() {
		CrudBar crudBar = new CrudBar();
		crudBar.addSaveButton("save");
		crudBar.setSaveClickListener(this);
		addComponent(crudBar);
	}

	@Override
	public void onSaveActionClicked() {
		if (validate()) {
			onSaveAction();
			tabSheet.setNeedRefresh(Boolean.TRUE);
			// TODO catch jpa exception
		} else {
			String caption = I18N.string("save");
			String desc = I18N.string("msg.error.save.entity");
			VaadinFactory.getNotification(caption, desc, Type.WARNING_MESSAGE, FontAwesome.EXCLAMATION)
				.show(Page.getCurrent());
		}
	}
	
	protected void onSaveAction() {
		fillDataToEntity();
		if (entity == null) {
			throw new IllegalStateException("Entity cannot be null");
		}
		getService().save(entity);
		String caption = I18N.string("save");
		String desc = I18N.string("msg.success.save");
		VaadinFactory.getNotification(caption, desc)
			.show(Page.getCurrent());
	}
	
	public void setMainTabSheet(AbstractTabSheetLayout<T> tabSheet) {
		this.tabSheet = tabSheet;
	}
	
	public void reset() {
		entity = null;
	}
	
	protected abstract AbstractComponentContainer initGUI();
	public abstract void assignValues(Long entityId);
	protected abstract void fillDataToControls();
	protected abstract void fillDataToEntity();
	protected abstract boolean validate();
	protected abstract AuditEntityService<T> getService();
	
}
