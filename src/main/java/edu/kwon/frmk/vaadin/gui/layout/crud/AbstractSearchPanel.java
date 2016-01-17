package edu.kwon.frmk.vaadin.gui.layout.crud;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import edu.kwon.frmk.common.data.jpa.repository.entities.root.RootEntity;
import edu.kwon.frmk.common.data.jpa.repository.entities.root.RootSpecification;
import edu.kwon.frmk.common.share.spring.util.I18N;
import edu.kwon.frmk.vaadin.component.factory.VaadinFactory;

public abstract class AbstractSearchPanel<T extends RootEntity> extends Panel {

	private static final long serialVersionUID = -1788720241817053229L;
	
	private Button btnSearch;
	private Button btnReset;
	
	private SearchListener searchListener;
	
	public AbstractSearchPanel() {
		init();
	}

	protected void init() {
		setCaption(I18N.string("search"));
		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSpacing(true);
		setContent(content);

		content.addComponent(initGUI());
		content.addComponent(buildSearchAndResetLayout());
	}
	
	private HorizontalLayout buildSearchAndResetLayout() {
		btnSearch = VaadinFactory.getButtonPrimary("search");
		btnSearch.setIcon(FontAwesome.SEARCH);
		btnSearch.setClickShortcut(KeyCode.ENTER);
		btnSearch.addClickListener(e -> {
			SearchListener listener = searchListener;
			if (listener != null) {
				listener.onSearch();
			}
		});
		
		btnReset = VaadinFactory.getButton("reset");
		btnReset.setIcon(FontAwesome.REFRESH);
		btnReset.addClickListener(e -> reset());

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		buttonLayout.addComponent(btnSearch);
		buttonLayout.addComponent(btnReset);

		return buttonLayout;
	}
	
	public void setSearchListener(SearchListener listener) {
		searchListener = listener;
	}
	
	protected abstract Component initGUI();
	protected abstract RootSpecification<T> getSpecification();
	protected abstract void reset();
	
	public interface SearchListener {
		
		void onSearch();
		
	}
	
}
