package edu.kwon.frmk.vaadin.gui.layout.crud;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.vaadin.server.Page;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.themes.ValoTheme;

import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntity;
import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntityService;
import edu.kwon.frmk.common.share.spring.util.I18N;
import edu.kwon.frmk.vaadin.component.factory.VaadinFactory;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.DeleteClickListener;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.EditClickListener;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.NewClickListener;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.RefreshClickListener;

/**
 * A Tab sheet layout for simple crud operation 
 * @author eduseashell
 *
 */
public abstract class AbstractTabSheetLayout<T extends AuditEntity> extends VerticalViewLayout implements EditClickListener, DeleteClickListener, NewClickListener, RefreshClickListener {

	private static final long serialVersionUID = 4135133118354559654L;
	
	private TabSheet tabsheet;
	private AbstractComponentContainer mainLayout;
	private List<AbstractComponentContainer> formLayouts = new ArrayList<>();
	
	private boolean needRefresh;
	private boolean autoRemoveFormLayout;
	
	@PostConstruct
	public void postConstruct() {
		init();
	}
	
	protected void init() {
		setSpacing(true);
		setAutoRemoveFormLayout(true);
		
		tabsheet = onCreateTabSheet();
		addComponent(tabsheet);
		
		this.mainLayout = buildMainLayout();
		addMainLayout();
	}
	
	protected TabSheet onCreateTabSheet() {
		TabSheet tabsheet = new TabSheet();
		tabsheet.setStyleName(ValoTheme.TABSHEET_FRAMED);
		tabsheet.addSelectedTabChangeListener(getSelectedTabChangeListener());
		return tabsheet;
	}
	
	/**
	 * SelectedTabChangeListener, CRUD Layout default implementation
	 * @return
	 */
	protected SelectedTabChangeListener getSelectedTabChangeListener() {
		return event -> {
			if (tabsheet.getSelectedTab() == mainLayout) {
				if (autoRemoveFormLayout) {
					removeFormLayouts();
				}
				if (isNeedRefresh()) {
					onRefreshMainLayout();
				}
			}
			initSelectedTab(tabsheet.getSelectedTab());
		};
	}
	
	/**
	 * Add Main Layout to the first tab
	 */
	protected void addMainLayout() {
		if (mainLayout != null) {
			addLayout(mainLayout);
		}
	}
	
	public void selectMainLayout() {
		setSelectedTab(mainLayout);
	}
	
	/**
	 * Add form layout to the tab after the main layout
	 * @param formLayout
	 */
	public void addFormLayout(AbstractComponentContainer formLayout) {
		if (!formLayouts.contains(formLayout)) {
			formLayouts.add(formLayout);
			addLayout(formLayout);
		}
		setSelectedTab(formLayout);
		initSelectedTab(formLayout);
	}
	
	/**
	 * Replace all previous FormLayouts with all the newly
	 * added FormLayouts and select the form provided
	 * @param formLayouts
	 * @param selectedForm
	 */
	public void setFormLayouts(List<AbstractComponentContainer> formLayouts, AbstractComponentContainer selectedForm) {
		removeFormLayouts();
		for (AbstractComponentContainer formLayout : formLayouts) {
			tabsheet.addTab(formLayout, formLayout.getCaption(), formLayout.getIcon());
		}
		this.formLayouts = formLayouts;
		if (this.formLayouts != null && !this.formLayouts.isEmpty()) {
			if (selectedForm == null) {
				selectedForm = formLayouts.get(0);
			}
			setSelectedTab(selectedForm);
			initSelectedTab(selectedForm);
		}
	}
	
	/**
	 * Remove all FormLayouts
	 */
	public void removeFormLayouts() {
		for (AbstractComponentContainer formLayout : formLayouts) {
			tabsheet.removeComponent(formLayout);
		}
		formLayouts.clear();
	}
	
	/**
	 * Remove the provided FormLayout
	 * If TabSheet not contains the form provided,
	 * Nothing is remove
	 * @param formLayout
	 */
	public void removeFormLayout(AbstractComponentContainer formLayout) {
		if (formLayouts.contains(formLayout)) {
			formLayouts.remove(formLayout);
			tabsheet.removeComponent(formLayout);
		}
	}
	
	public boolean isNeedRefresh() {
		return needRefresh;
	}

	public void setNeedRefresh(boolean needRefresh) {
		this.needRefresh = needRefresh;
	}
	
	public boolean isAutoRemoveFormLayout() {
		return autoRemoveFormLayout;
	}

	public void setAutoRemoveFormLayout(boolean autoRemoveFormLayout) {
		this.autoRemoveFormLayout = autoRemoveFormLayout;
	}
	
	private void addLayout(AbstractComponentContainer layout) {
		tabsheet.addTab(layout, layout.getCaption(), layout.getIcon());
	}
	
	public void setSelectedTab(AbstractComponentContainer selectedTab) {
		tabsheet.setSelectedTab(selectedTab);
	}
	
	protected void initSelectedTab(Component selectedTab) { }

	@Override
	public void onEditActionClicked() {
		if (getSelectedItemId() == null) {
			VaadinFactory.getNotification(I18N.string("edit"), I18N.string("msg.info.to.edit"))
				.show(Page.getCurrent());
		} else {
			onEditItem(getSelectedItemId());
		}
	}

	@Override
	public void onDeleteActionClicked() {
		if (getSelectedItemId() == null) {
			VaadinFactory.getNotification(I18N.string("delete"), I18N.string("msg.info.to.delete"))
				.show(Page.getCurrent());
		} else {
			onDeleteItem(getSelectedItemId());
			onRefreshMainLayout();
		}
	}
	
	protected void onDeleteItem(Long id) {
		getService().delete(id);
	}
	
	@Override
	public void onRefreshActionClicked() {
		onRefreshMainLayout();
	}
	
	protected abstract AbstractComponentContainer buildMainLayout();
	protected abstract void onEditItem(Long id);
	protected abstract void onRefreshMainLayout();
	protected abstract Long getSelectedItemId();
	protected abstract AuditEntityService<T> getService();

}
