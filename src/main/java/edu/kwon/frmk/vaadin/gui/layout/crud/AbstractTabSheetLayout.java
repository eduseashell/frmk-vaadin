package edu.kwon.frmk.vaadin.gui.layout.crud;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.themes.ValoTheme;

/**
 * A Tab sheet layout for simple crud operation 
 * @author eduseashell
 *
 */
public abstract class AbstractTabSheetLayout extends VerticalViewLayout {

	private static final long serialVersionUID = 4135133118354559654L;
	
	private TabSheet tabsheet;
	private AbstractComponentContainer mainLayout;
	private List<AbstractComponentContainer> formLayouts = new ArrayList<>();
	private boolean needRefresh;
	private boolean autoRemoveFormLayout;
	
	public AbstractTabSheetLayout() {
		setSpacing(true);
//		formLayouts = new ArrayList<>();
		
		tabsheet = new TabSheet();
		tabsheet.setStyleName(ValoTheme.TABSHEET_FRAMED);
		this.mainLayout = buildTableLayout();
		if (mainLayout != null) {
//			tableLayout.setMainPanel(this); TODO set main tab sheet to table layout
		}
		addMainLayout();
		tabsheet.addSelectedTabChangeListener(getSelectedTabChangeListener());
		addComponent(tabsheet);
	}
	
	/**
	 * SelectedTabChangeListener, CRUD Layout default implementation
	 * @return
	 */
	protected SelectedTabChangeListener getSelectedTabChangeListener() {
		return new SelectedTabChangeListener() {
			private static final long serialVersionUID = 3506958302776470207L;
			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				if (tabsheet.getSelectedTab() == mainLayout) {
					if (autoRemoveFormLayout) {
						removeFormLayouts();
					}
					if (isNeedRefresh()) {
						onRefreshMainLayout();
					}
					addMainLayout();
				}
				initSelectedTab(tabsheet.getSelectedTab());
			}
		};
	}
	
	/**
	 * Add Main Layout to the first tab
	 */
	protected void addMainLayout() {
		tabsheet.addTab(mainLayout, mainLayout.getCaption(), mainLayout.getIcon());
	}
	
	public void selectMainLayout() {
		tabsheet.setSelectedTab(0);
	}
	
	/**
	 * Add form layout to the tab after the main layout
	 * @param formLayout
	 */
	public void addFormLayout(AbstractComponentContainer formLayout) {
		if (!formLayouts.contains(formLayout)) {
//			formLayout.setMainPanel(this); TODO formlayout
			formLayouts.add(formLayout);
			tabsheet.addTab(formLayout, formLayout.getCaption(), formLayout.getIcon());
		}
		tabsheet.setSelectedTab(formLayout);
		initSelectedTab(formLayout);
	}
	
	/**
	 * Replace all previous FormLayouts with all the newly
	 * added FormLayouts and select the form provided
	 * @param formLayouts
	 * @param selectedForm
	 */
	public void addFormLayouts(List<AbstractComponentContainer> formLayouts, AbstractComponentContainer selectedForm) {
		removeFormLayouts();
		for (AbstractComponentContainer formLayout : formLayouts) {
			tabsheet.addTab(formLayout, formLayout.getCaption(), formLayout.getIcon());
		}
		this.formLayouts = formLayouts;
		if (this.formLayouts != null && !this.formLayouts.isEmpty()) {
			if (selectedForm == null) {
				selectedForm = formLayouts.get(0);
			}
			tabsheet.setSelectedTab(selectedForm);
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
	
	protected abstract AbstractComponentContainer buildTableLayout();
	protected abstract void onNewActionClicked();
	protected abstract void onEditActionClicked(Long entityId);
	protected abstract void onDeleteActionClicked(Long entityId);
	protected abstract void onRefreshMainLayout();
	protected abstract void initSelectedTab(Component selectedTab);

}