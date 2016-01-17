package edu.kwon.frmk.vaadin.gui.layout.crud;

import java.util.List;

import javax.annotation.PostConstruct;

import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.VerticalLayout;

import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntity;
import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntityService;
import edu.kwon.frmk.vaadin.component.table.Column;
import edu.kwon.frmk.vaadin.component.table.SimpleTable;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractSearchPanel.SearchListener;

/**
 * AbstractMainLayout provide a table layout
 * @author eduseashell
 *
 */
public abstract class AbstractMainLayout<T extends AuditEntity> extends VerticalLayout implements SearchListener {

	private static final long serialVersionUID = 2678419846978098731L;
	
	private AbstractTabSheetLayout<T> tabSheet;
	private ActionBar crudBar;
	private AbstractSearchPanel<T> searchPanel;
	protected SimpleTable table;
	
	private Item selectedItem;
	private Long selectedItemId;
	
	@PostConstruct
	public void PostConstruct() {
		init();
	}
	
	protected void init() {
		setMargin(true);
		setSpacing(true);
		setIcon(FontAwesome.LIST_ALT);
		
		crudBar = onCreateActionBar();
		if (crudBar != null) {
			addComponent(crudBar);
		}
		
		searchPanel = onCreateSearchPanel();
		if (searchPanel != null) {
			searchPanel.setSearchListener(this);
			addComponent(searchPanel);
		}
		
		table = onCreateTable();
		if (table != null) {
			addComponent(table);
			table.addItemClickListener(getTableItemClickListener());
		}
	}
	
	protected ActionBar onCreateActionBar() {
		CrudBar crudBar = new CrudBar();
		crudBar.addDefaultCrudBar();
		return crudBar;
	}
	
	/**
	 * Override to provide custom search panel
	 * @return
	 */
	protected AbstractSearchPanel<T> onCreateSearchPanel() {
		return null;// TODO default search layout
	}
	
	protected SimpleTable onCreateTable() {
		SimpleTable table = new SimpleTable(getTableCaption());
		table.addColumns(buildTableColumn());
		return table;
	}
	
	protected ItemClickListener getTableItemClickListener() {
		return event -> {
			selectedItem = event.getItem();
			selectedItemId = (Long) event.getItemId();
			if (event.isDoubleClick()) {
				onTableDoubleClick();
			}
		};
	}
	
	protected void renderTableRows(List<T> rows) {
		table.removeAllItems();
		if (rows != null) {
			rows.stream()
				.forEach(row -> renderRow(table.addItem(row.getId()), row));
		}
	}
	
	protected void onTableDoubleClick() {
		tabSheet.onEditActionClicked();
	}
	
	@Override
	public void onSearch() {
		refresh();
	}
	
	public void refresh() {
		renderTableRows(getService().findAll(searchPanel.getSpecification()));
	}
	
	public void setMainTabSheet(AbstractTabSheetLayout<T> tabSheet) {
		this.tabSheet = tabSheet;
	}
	
	public void setCrudListener(CrudListener listener) {
		if (crudBar instanceof CrudBar) {
			((CrudBar) crudBar).setCrudListener(listener);
		}
	}
	
	protected String getTableCaption() { return getCaption(); }
	public Item getSelectedItem() { return selectedItem; }
	public Long getSelectedItemId() { return selectedItemId; }
	
	protected abstract List<Column> buildTableColumn();
	public abstract void renderRow(Item item, T row);
	protected abstract AuditEntityService<T> getService();

}
