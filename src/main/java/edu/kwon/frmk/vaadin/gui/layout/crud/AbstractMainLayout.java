package edu.kwon.frmk.vaadin.gui.layout.crud;

import java.util.List;

import javax.annotation.PostConstruct;

import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.VerticalLayout;

import edu.kwon.frmk.vaadin.component.select.Column;
import edu.kwon.frmk.vaadin.component.select.SimpleTable;

/**
 * AbstractMainLayout provide a table layout
 * @author eduseashell
 *
 */
public abstract class AbstractMainLayout<ID> extends VerticalLayout {

	private static final long serialVersionUID = 2678419846978098731L;
	
	private ActionBar crudBar;
	private AbstractSearchPanel searchPanel;
	protected SimpleTable table;
	
//	private Resource icon; TODO icon on Table Layout
	private Item selectedItem;
	private ID selectedItemId;
	
	@PostConstruct
	public void PostConstruct() {
		init();
	}
	
	protected void init() {
		setMargin(true);
		setSpacing(true);
		
		crudBar = onCreateActionBar();
		if (crudBar != null) {
			addComponent(crudBar);
		}
		
		searchPanel = onCreateSearchPanel();
		if (searchPanel != null) {
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
	protected AbstractSearchPanel onCreateSearchPanel() {
		return null;// TODO default search layout
	}
	
	protected SimpleTable onCreateTable() {
		SimpleTable table = new SimpleTable(getTableCaption());
		table.addColumns(buildTableColumn());
		return table;
	}
	
	@SuppressWarnings("unchecked")
	protected ItemClickListener getTableItemClickListener() {
		return event -> {
			selectedItem = event.getItem();
			selectedItemId = (ID) event.getItemId();
			if (event.isDoubleClick()) {
				onTableDoubleClick();
			}
		};
	}
	
	protected <T> void renderTableRows(List<T> rows) {
		table.removeAllItems();
		if (rows != null) {
			rows.stream().forEach(row -> renderRow(table.addItem(getItemId(row)), row));
		}
	}
	
	protected String getTableCaption() {
		return getCaption();
	}
	
	public Item getSelectedItem() {
		return selectedItem;
	}

	public ID getSelectedItemId() {
		return selectedItemId;
	}
	
	public void setCrudListener(CrudListener listener) {
		if (crudBar instanceof CrudBar) {
			((CrudBar) crudBar).setCrudListener(listener);
		}
	}
	
	protected abstract void onTableDoubleClick();
	protected abstract List<Column> buildTableColumn();
	public abstract <T> void renderRow(Item item, T row);
	protected abstract <T> ID getItemId(T rowEntity);

}
