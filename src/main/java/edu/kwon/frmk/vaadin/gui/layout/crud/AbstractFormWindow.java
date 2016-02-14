package edu.kwon.frmk.vaadin.gui.layout.crud;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntity;
import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntityService;
import edu.kwon.frmk.common.share.spring.util.I18N;
import edu.kwon.frmk.vaadin.component.factory.VaadinFactory;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.CancelClickListener;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.SaveClickListener;

public abstract class AbstractFormWindow<T extends AuditEntity> extends Window implements SaveClickListener, CancelClickListener {

	private static final long serialVersionUID = -3781713958824276632L;
	
	protected T entity;
	private PreSaveListener preSaveListener;
	private PostSaveListener postSaveListener;
	
	@PostConstruct
	public void postConstruct() {
		init();
	}
	
	protected void init() {
		setModal(true);
		setResizable(false);
		setIcon(FontAwesome.LIST_ALT);
		
		VerticalViewLayout content = new VerticalViewLayout();
		content.setSpacing(true);
		content.setMargin(true);
		content.addComponent(initGUI());
		
		CrudBar actionBar = buildDefaultCRUDBar();
		content.addComponent(actionBar);
		content.setComponentAlignment(actionBar, Alignment.MIDDLE_RIGHT);
		
		setContent(content);
	}
	
	protected CrudBar buildDefaultCRUDBar() {
		CrudBar crudBar = new CrudBar();
		crudBar.addSaveButton("save");
		crudBar.addCancelButton("cancel");
		crudBar.setSaveClickListener(this);
		crudBar.setCancelClickListener(this);
		return crudBar;
	}

	@Override
	public void onSaveActionClicked() {
		if (validate()) {
			PreSaveListener preLst = preSaveListener;
			if (preLst != null) preLst.preSaveAction();
			
			onSaveAction();
			close();
			
			PostSaveListener postLst = postSaveListener;
			if (postLst != null) postLst.postSaveAction();
			
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
	
	@Override
	public void onCancelActionClicked() {
		// TODO confirm before close?
		close();
	}
	
	public void reset() {
		entity = null;
	}
	
	public void show() {
		center();
		UI.getCurrent().addWindow(this);
	}
	
	protected abstract AbstractComponentContainer initGUI();
	public abstract void assignValues(Long entityId);
	protected abstract void fillDataToControls();
	protected abstract void fillDataToEntity();
	protected abstract boolean validate();
	protected abstract AuditEntityService<T> getService();
	
	public interface PreSaveListener extends Serializable {
		
		void preSaveAction();
		
	}
	
	public interface PostSaveListener extends Serializable {
		
		void postSaveAction();
		
	}

	public PreSaveListener getPreSaveListener() {
		return preSaveListener;
	}

	public void setPreSaveListener(final PreSaveListener preSaveListener) {
		this.preSaveListener = preSaveListener;
	}

	public PostSaveListener getPostSaveListener() {
		return postSaveListener;
	}

	public void setPostSaveListener(final PostSaveListener postSaveListener) {
		this.postSaveListener = postSaveListener;
	}

}
